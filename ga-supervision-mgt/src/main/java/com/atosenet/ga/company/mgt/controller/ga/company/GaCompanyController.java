package com.atosenet.ga.company.mgt.controller.ga.company;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atosenet.ga.company.core.common.model.response.ApiResponse;
import com.atosenet.ga.company.db.model.ga.company.CompanyBusinessType;
import com.atosenet.ga.company.db.model.ga.company.GaCompany;
import com.atosenet.ga.company.db.model.ga.employe.Employee;
import com.atosenet.ga.company.db.model.sys.Org;
import com.atosenet.ga.company.mgt.config.Constants;
import com.atosenet.ga.company.mgt.service.ga.company.CompanyBusinessTypeService;
import com.atosenet.ga.company.mgt.service.ga.company.GaCompanyService;
import com.atosenet.ga.company.mgt.service.ga.employe.EmployeeService;
import com.atosenet.ga.company.mgt.service.ga.employe.response.DataResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@CrossOrigin
@RequestMapping("/eim/api/company")
@Api(tags = "公司信息")
public class GaCompanyController {

	@Autowired
	GaCompanyService gaCompanyService;
	@Autowired
	CompanyBusinessTypeService companyBusinessTypeService;
	@Autowired
	EmployeeService eService;
	
	
	

	@RequestMapping(value = "pagelist", method = RequestMethod.GET)
	// public Page<GaCompany> getPagegaCompany(@RequestBody Integer
	// page,@RequestBody Integer size)
	// public Page<GaCompany> getPagegaCompany(@PathVariable("page") int
	// page,@PathVariable("size") int size)
	@ApiImplicitParams({ @ApiImplicitParam(value = "页码", name = "page", dataType = "String"),
			@ApiImplicitParam(value = "页面长度", name = "size", dataType = "String"), })
	@ApiIgnore
	public Page<GaCompany> getPagegaCompany(HttpServletRequest request, HttpServletResponse response) {
		String page = request.getParameter("page");
		String size = request.getParameter("size");
		System.out.println("获取所有公司信息");
		Pageable pageable = new PageRequest(Integer.parseInt(page), Integer.parseInt(size));
		return gaCompanyService.findPageAll(pageable);
	}

	@RequestMapping(value = { "list", "" }, method = RequestMethod.GET)
	@ApiOperation(value = "获取所有公司信息")
	@ApiIgnore
	public List<GaCompany> getAllgaCompany() {
		System.out.println("获取所有公司信息");
		return gaCompanyService.findAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "根据企业id获取通航企业基本信息")
	@ApiImplicitParams({ @ApiImplicitParam(value = "公司ID", name = "id",paramType = "path", dataType = "String") })
	public GaCompany getById(@PathVariable("id") String id) {
		return gaCompanyService.get(Integer.parseInt(id));
	}
	
	@RequestMapping(value = "/search/byids", method = RequestMethod.GET)
	@ApiOperation(value = "根据企业id获取通航企业基本信息")
	@ApiIgnore
	@ApiImplicitParams({ @ApiImplicitParam(value = "公司ID", name = "id",paramType = "path", dataType = "String") })
	public List<GaCompany> getByIds(String companyId) {
		
		return gaCompanyService.gets(companyId);
	}

	@RequestMapping(value = "/search/infobyname", method = RequestMethod.GET)
	@ApiOperation(value = "根据企业名称获取通航企业基本信息")
	@ApiImplicitParams({ @ApiImplicitParam(value = "公司名称", name = "name",paramType = "query", dataType = "String") })
	public GaCompany getByname(@RequestParam("name") String name) {
		return gaCompanyService.getbyname(name);
	}

	@RequestMapping(value = "/search/orgname", method = RequestMethod.GET)
	@ApiOperation(value = "根据类型获取所有组织信息")
	@ApiIgnore
	public List<Org> getOrgByType(@RequestParam("orgtype") long type) {
		return gaCompanyService.findOrgByOrgType(type);
	}

	@RequestMapping(value="save",method = RequestMethod.POST)
	@ApiOperation(value = "保存公司信息")
	//@Transactional(readOnly = false)
	@ApiImplicitParams({ @ApiImplicitParam(value = "公司信息", name = "gaCompany", dataType = "GaCompany") })
	@ApiIgnore
	public void save(@RequestBody GaCompany gaCompany, HttpServletRequest request) {
		System.out.println("保存公司信息");
		System.out.println(gaCompany.getName());
		gaCompanyService.save(gaCompany,request);
		System.out.println("gaCompany.getCompanyId()   " + gaCompany.getCompanyId());
		companyBusinessTypeService.deleteByCompanyId(gaCompany.getCompanyId());

		if (gaCompany.getBusinessType() != null && gaCompany.getBusinessType() != "") {
			System.out.println(">>>>>>>>>>>>>>" + gaCompany.getBusinessType());
			String[] types = gaCompany.getBusinessType().trim().split(",");

			if (types.length > 0) {
				for (int i = 0; i < types.length; i++) {
					CompanyBusinessType com = new CompanyBusinessType();
					com.setAuthorityId(gaCompany.getAuthorityId());
					com.setSupervisionId(gaCompany.getSupervisionId());
					com.setCompanyId(gaCompany.getCompanyId());
					try {
						com.setBusinessType(Short.parseShort(types[i]));
						companyBusinessTypeService.save(com);
					} catch (Exception e) {
						// TODO: handle exception
						e.getMessage();
					}
				}
			}
		}

	}
	
	@RequestMapping(value = "/savecompanyinfo", method = RequestMethod.POST)
	@ApiOperation(value = "修改通航企业基本信息")
	@ApiImplicitParams({ @ApiImplicitParam(value = "企业信息对象", name = "gaCompany",paramType = "body", dataType = "GaCompany"),				
    })
	public DataResponse saveCompanyInfo(@RequestBody GaCompany gaCompany){
		DataResponse res = new DataResponse();
		GaCompany gc = new GaCompany();
		try{
			gc = gaCompanyService.get(gaCompany.getCompanyId());
			if(null!=gc){
				GaCompany gacompany = gaCompanyService.saveCompanyInfo(gaCompany);
				res.setData(gacompany);
				res.setResultCode(Constants.OK);
			}else{
		
				res.setMessage("没有对应的企业,不能修改企业信息");
			}
		}catch(Exception e){
			res.setMessage(e.getMessage());
			res.setResultCode(Constants.INTERNAL_SERVER_ERROR);
		}
		return res;
	}

	/**
	 * 向company表保存公司属性
	 * 
	 * @return
	 */
	@RequestMapping(value = "savegaconpany", method = RequestMethod.GET)
	@ApiOperation(value = "保存公司类型")
	@ApiImplicitParams({ @ApiImplicitParam(value = "公司类型", name = "type", dataType = "GaCompany"),
			@ApiImplicitParam(value = "组织机构ID", name = "orgId", dataType = "GaCompany"), })
	@ApiIgnore
	public ApiResponse<String> savegaconpany(HttpServletRequest request, HttpServletResponse response) {
		String type = request.getParameter("type");
		String orgId = request.getParameter("orgId");
		GaCompany gacompany = gaCompanyService.findOrgByOrgid(Long.parseLong(orgId));
		if (gacompany != null) {
			gacompany.setCompanyType(type);
			gaCompanyService.savecompany(gacompany);
		} else {
			gacompany = new GaCompany();
			gacompany.setOrgId(Long.parseLong(orgId));
			gacompany.setCompanyType(type);
			gaCompanyService.savecompany(gacompany);
		}
		ApiResponse<String> api = new ApiResponse<String>();
		api.setResultCode(200);
		return api;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "删除当前公司")
	@ApiImplicitParams({ @ApiImplicitParam(value = "公司ID", name = "id", dataType = "id") })
	@ApiIgnore
	public void deleteById(@PathVariable("id") int id) {
		gaCompanyService.delete(id);

	}

	@ExceptionHandler(EmptyResultDataAccessException.class)
	public String emptyResultDataAccessException(Throwable t) {
		return "error";
	}

	@RequestMapping(value = "/listbyautid/{authorityId}", method = RequestMethod.GET)
	@ApiOperation(value = "获取所有公司信息通过管理局ID")
	@ApiImplicitParams({ @ApiImplicitParam(value = "管理局ID", name = "authorityId",paramType="path", dataType = "String") })
	public List<GaCompany> findgaCompanyByauthorityId(@PathVariable("authorityId") String authorityId) {
		return gaCompanyService.findByAuthorityIdList(Long.parseLong(authorityId));
	}

	@RequestMapping(value = "/listbysupid/{supervisionId}", method = RequestMethod.GET)
	@ApiOperation(value = "获取所有公司信息通过监管局ID")
	@ApiImplicitParams({ @ApiImplicitParam(value = "监管局ID", name = "supervisionId",paramType="path", dataType = "String") })
	public List<GaCompany> findgaCompanyBysupervisionId(@PathVariable("supervisionId") String supervisionId) {
		return gaCompanyService.findBySupervisionIdList(Long.parseLong(supervisionId));
	}
	
	//by dgw
	/*@RequestMapping(value = "/getgroupinfo/{supervisionId}", method = RequestMethod.GET)
	@ApiOperation(value = "根据管理局ID获取所有监管企业的数量和对应的企业类型；")
	@ApiImplicitParams({ @ApiImplicitParam(value = "监管局ID", name = "supervisionId", dataType = "Long") })
	@ApiIgnore
	public List<Object> getGroupInfoBysupervisionId(@PathVariable("supervisionId") Long supervisionId) {
		return gaCompanyService.getGroupInfo(supervisionId);
	}*/

	/**
	 * 获取所有公司信息通过企业ids带分页
	 * 
	 * @param supervisionId
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/search/listbysupid", method = RequestMethod.GET)
	@ApiOperation(value = "获取个类型公司信息通过监管局ID和orgtype带分页")
	@ApiImplicitParams({ @ApiImplicitParam(value = "监管局ID", name = "supervisionId", dataType = "Long"),
			@ApiImplicitParam(value = "公司id或公司名称(模糊查询)", name = "companyName", dataType = "String"),
			@ApiImplicitParam(value = "法定代表人", name = "legalPerson", dataType = "String"),	})
	@ApiIgnore
	public Page<GaCompany> getBycompanyIdAndname(@RequestParam(value = "pageno", required = false) String pageno,
			@RequestParam(value = "pagesize", required = false) String pagesize,
			@RequestParam(value = "supervisionId", required = false) String supervisionId,
			@RequestParam(value = "companyName", required = false) String companyName,
			@RequestParam(value = "legalPerson", required = false) String legalPerson
			) throws UnsupportedEncodingException {
		Pageable pageable = new PageRequest(Integer.parseInt(pageno), Integer.parseInt(pagesize));
		Page<GaCompany> st = null;
		if ((companyName == "" || companyName.length() <= 0) &&(legalPerson==""||legalPerson.length()<=0)) {
			st = gaCompanyService.selectBySupervisionId(Long.parseLong(supervisionId), pageable);			
		} 
		 if((companyName != "" || companyName.length() > 0) &&(legalPerson==""||legalPerson.length()<=0)){
			st = gaCompanyService.selectBySupervisionIdAndName(Long.parseLong(supervisionId), pageable, companyName);		
		}
		 if((companyName == "" || companyName.length() <= 0) &&(legalPerson!=""||legalPerson.length()>0)){
			 st = gaCompanyService.selectBySupervisionIdAndLegalPerson(Long.parseLong(supervisionId), pageable, legalPerson);
		 }
		 
		 if((companyName != "" || companyName.length() >0) &&(legalPerson!=""||legalPerson.length()>0)){
			 st = gaCompanyService.selectBySupervisionIdAndCompanyNameAndLegalPerson(Long.parseLong(supervisionId), pageable, companyName, legalPerson);
		 }
		return st;
	}

	@RequestMapping(value = "/search/authorityId", method = RequestMethod.GET)
	@ApiOperation(value = "获取个类型公司信息通过监管局ID和orgtype带分页")
	@ApiImplicitParams({ @ApiImplicitParam(value = "管理局ID", name = "authorityId", dataType = "Long"),
			@ApiImplicitParam(value = "公司id或公司名称(模糊查询)", name = "keyword", dataType = "String"), })
	@ApiIgnore
	public Page<GaCompany> getByauthorityIdAndname(@RequestParam(value = "pageno", required = false) String pageno,
			@RequestParam(value = "pagesize", required = false) String pagesize,
			@RequestParam(value = "authorityId", required = false) String authorityId,
			@RequestParam(value = "keyword", required = false) String keyword) throws UnsupportedEncodingException {
		Pageable pageable = new PageRequest(Integer.parseInt(pageno), Integer.parseInt(pagesize));
		Page<GaCompany> st = null;
		if (keyword == null || keyword.length() <= 0) {
			st = gaCompanyService.selectByauthorityId(Long.parseLong(authorityId), pageable);

			return st;
		} else {
			st = gaCompanyService.selectBauthorityIdAndName(Long.parseLong(authorityId), pageable, keyword);
			return st;
		}
	}

	/**
	 * 更新企业拥有的所有飞行基地 机场 维修基地 起降点
	 */
	@RequestMapping(value = "/save/flybaseent", method = RequestMethod.GET)
	@ApiOperation(value = "更新企业拥有的所有飞行基地 机场 维修基地 起降点")
	@ApiImplicitParams({ @ApiImplicitParam(value = "飞行基地ID", name = "flybaseid", dataType = "String"),
			@ApiImplicitParam(value = "公司id", name = "companyid", dataType = "String"),
			@ApiImplicitParam(value = "机场id", name = "airport", dataType = "String"),
			@ApiImplicitParam(value = "维修基地id", name = "maintainid", dataType = "String"),
			@ApiImplicitParam(value = "起降点id", name = "landingid", dataType = "String"), })
	@ApiIgnore
	public void saveFlybase(@RequestParam("flybaseid") String flybaseid, @RequestParam("companyid") String companyid,
			@RequestParam("airport") String airport, @RequestParam("maintainid") String maintainid,
			@RequestParam("landingid") String landingid, HttpServletRequest request) {
		gaCompanyService.savebase(flybaseid, airport, maintainid, landingid, companyid, request);
	}

	/**
	 * 获得企业类型
	 */
	@RequestMapping(value = "/search/getcountbytype", method = RequestMethod.GET)
	@ApiOperation(value = "通过监管id获得该监管局下所有企业类型的数量")
	@ApiImplicitParams({ @ApiImplicitParam(value = "监管局ID", name = "listbysupid", dataType = "String"), })
	@ApiIgnore
	public List<Object> getcountbytype(@RequestParam("listbysupid") String listbysupid) {
		return gaCompanyService.getcountbytype(Long.parseLong(listbysupid));
	}

	@RequestMapping(value = "/search/getcountbytypeandauthorityId", method = RequestMethod.GET)
	@ApiOperation(value = "通过管理局id获得该监管局下所有企业类型的数量")
	@ApiImplicitParams({ @ApiImplicitParam(value = "管理局ID", name = "authorityId", dataType = "String"), })
	@ApiIgnore
	public List<Object> getcountbytypeandauthorityId(@RequestParam("authorityId") String authorityId) {
		return gaCompanyService.getcountbytypeandauthorityId(Long.parseLong(authorityId));
	}
	
	@RequestMapping(value = "/search/getlistByCompanyid", method = RequestMethod.GET)
	@ApiOperation(value = "通过企业id获取员工列表")
	@ApiImplicitParams({ @ApiImplicitParam(value = "企业ID", name = "companyid", dataType = "String", paramType="query"), })
	public List<Employee> getEmployeeByCompanyId(@RequestParam("companyid")String companyid){
		return eService.getEmployeeByCompanyId(Long.parseLong(companyid));
	}
	
	//根据管理局名称获取管理局id
	@RequestMapping(value="/search/getgIdByName", method = RequestMethod.GET)
	@ApiOperation(value="根据管理局名称获取管理局id")
	@ApiImplicitParams({ @ApiImplicitParam(value = "管理局名称", name = "name", dataType = "String", paramType="query"), })
	public DataResponse<Long> getgIdByName(@RequestParam("name")String name){
		DataResponse<Long> response = new DataResponse<>();
		try {
		Org	org = this.gaCompanyService.findGorgByName(name);
		
		if(org != null){
			response.setData(org.getId());
			response.setResultCode(Constants.OK);	
		}
		return response;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			return response;
		}
	}
	
	//根据监管局名称获取监管局id
		@RequestMapping(value="/search/getsIdByName", method = RequestMethod.GET)
		@ApiOperation(value="根据监管局名称获取监管局id")
		@ApiImplicitParams({ @ApiImplicitParam(value = "监管局名称", name = "name", dataType = "String", paramType="query"), })
		public DataResponse<Long> getsIdByName(@RequestParam("name")String name){
			DataResponse<Long> response = new DataResponse<>();
			try {
			Org	org = this.gaCompanyService.findSorgByName(name);
			if(org != null){
				response.setData(org.getId());
				response.setResultCode(Constants.OK);	
			}
			return response;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
				return response;
			}
		}
	
		
		@RequestMapping(value = "/search/listbycomids", method = RequestMethod.GET)
		@ApiOperation(value = "根据企业ids查询企业信息")
		@ApiImplicitParams({ @ApiImplicitParam(value = "企业ids", name = "companyIds", dataType = "String"),
				@ApiImplicitParam(value = "公司id或公司名称(模糊查询)", name = "companyName", dataType = "String"),
				@ApiImplicitParam(value = "法定代表人", name = "legalPerson", dataType = "String"),	})
		@ApiIgnore
		public Page<GaCompany> getByCompanyIds(@RequestParam(value = "pageno", required = false) String pageno,
				@RequestParam(value = "pagesize", required = false) String pagesize,
				@RequestParam(value = "companyIds", required = false) String companyIds,
				@RequestParam(value = "companyId", required = false) String companyId,
				@RequestParam(value = "legalPerson", required = false) String legalPerson
				) throws UnsupportedEncodingException {
			String[] companyids = companyIds.split(",");
			Pageable pageable = new PageRequest(Integer.parseInt(pageno), Integer.parseInt(pagesize));
			Page<GaCompany> st = null;
			if ((companyId==""|| companyId.length() <= 0) &&(legalPerson==""||legalPerson.length()<=0)) {
				//st = gaCompanyService.selectByCompanyIds(companyids, pageable);
				st = gaCompanyService.selectByCompanyIds1(companyids, pageable);
			} 
			 if((companyId!=""|| companyId.length() > 0) &&(legalPerson==""||legalPerson.length()<=0)){
				//st = gaCompanyService.selectByCompanyIdsAndId(Long.parseLong(companyId), pageable);
				 st = gaCompanyService.selectByCompanyIdsAndId1(Long.parseLong(companyId), pageable);
			}
			 if((companyId==""|| companyId.length() <= 0) &&(legalPerson!=""||legalPerson.length()>0)){
				 //st = gaCompanyService.selectByCompanyIdsAndLegalPerson(companyids, legalPerson, pageable);
				 st = gaCompanyService.selectByCompanyIdsAndLegalPerson1(companyids, legalPerson, pageable);
			 }
			 
			 if((companyId!=""|| companyId.length() >0) &&(legalPerson!=""||legalPerson.length()>0)){
				 //st = gaCompanyService.selectAllByCompanyIds(companyids, Long.parseLong(companyId), legalPerson, pageable);
				 st = gaCompanyService.selectAllByCompanyIds1(companyids, Long.parseLong(companyId), legalPerson, pageable);
			 }
			return st;
		}
		
		@RequestMapping(value = "/search/listbycomids1", method = RequestMethod.GET)
		@ApiOperation(value = "查询下拉框企业名称")
		public List<GaCompany> getByCompanyIds1(@RequestParam("companyIds")String companyIds){			
			String[] companyids = companyIds.split(",");
			return gaCompanyService.selectByCompanyIds1(companyids);
		}
		
		@RequestMapping(value="/search/BySupervisionId", method=RequestMethod.GET)
		public DataResponse<List<GaCompany>> getBySupervisionId(@RequestParam String supervisionId){
			DataResponse<List<GaCompany>> response = new DataResponse<>();
			
			try {
				List<GaCompany> list = this.gaCompanyService.findBySupervisionIdList(Long.parseLong(supervisionId));
				response.setResultCode(Constants.OK);
				response.setData(list);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
				response.setMessage(e.getMessage());
			}
			return  response;
		}
		
		@RequestMapping(value = "/staticflightbysupervisionIds",method = RequestMethod.GET)
		@ApiIgnore
		@ApiOperation(value = "根据监管局统计飞行时间")
		public List<Object> staticFlightTimeBySupervisionIds(@RequestParam("supervisionIds")String supervisionIds){
			String[] supervisionids = supervisionIds.split(",");
			return gaCompanyService.staticFlightTimeBySupervisionIds(supervisionids);
		}
		
		@RequestMapping(value = "/staticflightbysupervisionId",method = RequestMethod.GET)
		@ApiIgnore
		@ApiOperation(value = "根据监管局id统计飞行时间")
		public List<Object> staticFlightTimeBySupervisionId(@RequestParam("supervisionId")String supervisionId){
			
			return gaCompanyService.staticFlightTimeBySupervisionId(supervisionId);
		}
		
		@RequestMapping(value = "/staticflightbysupidsandcase",method=RequestMethod.GET)
		@ApiIgnore
		@ApiOperation(value = "根据监管局ids和日期统计飞行时间")
		public List<Object> staticFlightTimeBySupIdsAndCase(@RequestParam("supervisionIds")String supervisionIds,
				@RequestParam("beginDate")String beginDate,@RequestParam("endDate")String endDate,
				@RequestParam("flightNature")String flightNature,@RequestParam("supervisionId")String supervisionId
				){
			String[] supervisionids = supervisionIds.split(",");
			List<Object> obj = null;
			if( (beginDate!=""||!beginDate.equals(""))&&(endDate!=""||!endDate.equals(""))&&(flightNature==""||flightNature.equals(""))&&(supervisionId==""||supervisionId.equals("")) )
			obj = gaCompanyService.staticFlightTimeBySupIdsAndDate(supervisionids, beginDate, endDate);
			
			if( (beginDate==""||beginDate.equals(""))&&(endDate==""||endDate.equals(""))&&(flightNature!=""||!flightNature.equals(""))&&(supervisionId==""||supervisionId.equals("")) )
			obj = gaCompanyService.staticFlightTimeBySupIdsAndNature(supervisionids, Integer.parseInt(flightNature));
			
			if( (beginDate==""||beginDate.equals(""))&&(endDate==""||endDate.equals(""))&&(flightNature==""||flightNature.equals(""))&&(supervisionId!=""||!supervisionId.equals("")) )
			obj = gaCompanyService.staticFlightTimeBySupervisionId(supervisionId);
			
			if( (beginDate!=""||!beginDate.equals(""))&&(endDate!=""||!endDate.equals(""))&&(flightNature!=""||!flightNature.equals(""))&&(supervisionId==""||supervisionId.equals("")) )
			obj = gaCompanyService.staticFlightTimeBySupIdsAndDateAndNature(supervisionids, beginDate, endDate, Integer.parseInt(flightNature));
			
			if( (beginDate!=""||!beginDate.equals(""))&&(endDate!=""||!endDate.equals(""))&&(flightNature==""||flightNature.equals(""))&&(supervisionId!=""||!supervisionId.equals("")) )
			obj = gaCompanyService.staticFlightTimeByDateAndSupId(supervisionId, beginDate, endDate);
			
			if( (beginDate==""||beginDate.equals(""))&&(endDate==""||endDate.equals(""))&&(flightNature!=""||!flightNature.equals(""))&&(supervisionId!=""||!supervisionId.equals("")) )
			obj = gaCompanyService.staticFlightTimeByNatureAndSupId(supervisionId, Integer.parseInt(flightNature));
			
			if( (beginDate!=""||!beginDate.equals(""))&&(endDate!=""||!endDate.equals(""))&&(flightNature!=""||!flightNature.equals(""))&&(supervisionId!=""||!supervisionId.equals("")) )
			obj = gaCompanyService.staticFlightTimeByAll(supervisionId, beginDate, endDate, Integer.parseInt(flightNature));	
			return obj;
		}
		
		@RequestMapping(value = "staticflightcountsbydateandcompanyId",method = RequestMethod.GET)
		@ApiIgnore
		@ApiOperation(value = "根据日期和企业id统计企业飞行时长")
		public List<Object> staticFlightCountsByDateAndCompanyId(@RequestParam("beginDate")String beginDate,@RequestParam("endDate")String endDate,@RequestParam("companyId")String companyId,@RequestParam("flightNatures")String flightNatures){
			List<Object> obj = null;
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd");
			String[] companyIds = companyId.split(",");
			String[] flightnatures = flightNatures.split(",");
			try {
				sf.parse(beginDate);
				sf1.parse(endDate);
				obj = gaCompanyService.staticFlightCountsByDateAndCompanyId(sf.parse(beginDate), sf1.parse(endDate), companyIds,flightnatures);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return obj;
		}
		
		@RequestMapping(value = "/staticBusinessTypeBySuperIds",method = RequestMethod.GET)
		@ApiIgnore
		@ApiOperation(value = "根据监管局ids统计企业经营类别")
		public Object staticBusinessTypeBySuperIds(@RequestParam("superIds")String superIds){
			String[] superids = superIds.split(",");
			return gaCompanyService.staticBusinessTypeBySuperIds(superids);
		}
		
		@RequestMapping(value = "/staticflight",method = RequestMethod.GET)
		@ApiIgnore
		@ApiOperation(value = "根据监管局ids初始化统计飞行时间")
		public List<Object> staticFlight(@RequestParam("supervisionIds")String supervisionIds,@RequestParam("beginDate")String beginDate,@RequestParam("endDate")String endDate,@RequestParam("flightNature")String flightNature){
			String[] supervisionids = supervisionIds.split(",");
			String[] FlightNatures = flightNature.split(",");			
			return gaCompanyService.staticFlight(supervisionids, beginDate, endDate, FlightNatures);
		}
		
		@RequestMapping(value = "/staticflights",method = RequestMethod.GET)
		@ApiIgnore
		@ApiOperation(value = "根据企业ids初始化统计飞行时间")
		public List<Object> staticFlights(@RequestParam("companyId")String companyId,@RequestParam("beginDate")String beginDate,@RequestParam("endDate")String endDate){
			
					
			return gaCompanyService.staticFlights(companyId, beginDate, endDate);
		}
		
		@RequestMapping(value = "/staticflightsv",method = RequestMethod.GET)
		@ApiIgnore
		@ApiOperation(value = "根据企业ids初始化统计飞行时间")
		public List<Object> staticFlightsv(@RequestParam("companyId")String companyId,@RequestParam("beginDate")String beginDate,@RequestParam("endDate")String endDate){
			
					
			return gaCompanyService.staticFlightsv(companyId, beginDate, endDate);
		}
		
		@RequestMapping(value = "/selectcompanysBysupervisionids",method = RequestMethod.GET)
		@ApiIgnore
		@ApiOperation(value = "根据监管局ids查询所有企业")
		public List<Object> selectCompanysBySupervisionIds(@RequestParam("supervisionIds")String supervisionIds){
			String[] supervisionids = supervisionIds.split(",");
			return gaCompanyService.selectCompanysBySupervisionIds(supervisionids);
		}
		
		@RequestMapping(value = "/selectcompanynamesbycompanyids",method = RequestMethod.GET)
		@ApiIgnore
		@ApiOperation(value = "根据企业ids查询企业名称")
		public List<Object> selectCompanyNamesByCompanyIds(@RequestParam("companyIds")String companyIds){
			String[] companyids = companyIds.split(",");
			return gaCompanyService.selectCompanyNamesByCompanyIds(companyids);
		}
		
		@RequestMapping(value = "/selectsupvisionnamebysupid",method = RequestMethod.GET)
		@ApiIgnore
		@ApiOperation(value = "根据部门id查询名称")
		public String selectSupvisionNameBySupId(@RequestParam("id")String id){
			return gaCompanyService.selectSupvisionNameBySupId(id);
		}
}
