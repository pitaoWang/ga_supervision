package com.atosenet.ga.company.mgt.controller.ga.company;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import com.atosenet.ga.company.core.auth.shiro.common.utils.HttpUtils;
import com.atosenet.ga.company.core.common.model.response.ApiResponse;
import com.atosenet.ga.company.db.model.ga.company.Aircraft;
import com.atosenet.ga.company.db.model.ga.company.AircraftFlySumView;
import com.atosenet.ga.company.db.model.ga.employe.Employee;
import com.atosenet.ga.company.db.repos.ga.company.AircraftFlySumRepository;
import com.atosenet.ga.company.db.repos.ga.company.AircraftRepository;
import com.atosenet.ga.company.mgt.config.Constants;
import com.atosenet.ga.company.mgt.service.ga.company.AircraftService;
import com.atosenet.ga.company.mgt.service.ga.company.dto.AircraftCheckDTO;
import com.atosenet.ga.company.mgt.service.ga.employe.response.DataResponse;
import com.atosenet.ga.company.mgt.util.ExcelExport;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@CrossOrigin
@RequestMapping("/eim/api/aircraft")
@Api(tags="航空器信息")
public class AircraftController {
	public static final Logger logger = LoggerFactory.getLogger(AirportController.class);
//	@Value("${app.bpm.url}")
//	private String bpmUrl;
	@Autowired
	AircraftRepository aircraftRepository;

	@Autowired
	AircraftService aircraftService;

	@Autowired
	AircraftFlySumRepository aircraftFlySumRepository;

	@RequestMapping(value = { "list", "" }, method = RequestMethod.GET)
	@ApiIgnore
//	@ApiOperation(value = "获取所有航空器信息")
	public List<Aircraft> getAllTest() {
		return aircraftRepository.findList();
	}
	
	    @RequestMapping(value = "/search/findByCompanyId", method = RequestMethod.GET)
	    @ApiOperation(value = "根据企业id获取航空器列表")
	    @ApiIgnore
		@ApiImplicitParams({
			@ApiImplicitParam(value="企业id",name="companyId",dataType="String",paramType="query")	
		})
		public DataResponse<List<Aircraft>> findByCompanyId(
				@RequestParam(value = "companyId", required = false) String companyId) throws ParseException {
			DataResponse<List<Aircraft>> response = new DataResponse<>();
			List<Aircraft> datas = null;
			try {
				datas = aircraftService.findByCompanyId(companyId);
				response.setData(datas);
				response.setResultCode(Constants.OK);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
				response.setMessage(e.getMessage());
			}
			return response;
			
		}
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ApiIgnore
	@ApiOperation(value = "主键ID获取当前航空器信息")
	public Aircraft getById(@PathVariable("id") Long id) {
		return aircraftRepository.findOne(id);
	}

	@RequestMapping(value = "/one/{id}", method = RequestMethod.DELETE)
	@ApiIgnore
	@ApiOperation(value = "删除当前航空器信息")
	public void deleteById(@PathVariable("id") Long id) {
		aircraftService.delete(id);
		
	}
	
	@RequestMapping(value = "{list}", method = RequestMethod.DELETE)	
	@ApiOperation(value = "批量删除当前航空器信息")
	public void deleteByIds(@RequestBody List<Aircraft> list) {
		aircraftService.deletes(list);
		
	}

	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "新增/编辑航空器信息")
	@Transactional(readOnly = false)
	@ApiIgnore
	public DataResponse<Aircraft> save(@RequestBody AircraftCheckDTO info,HttpServletRequest request) {
		
      long start = System.currentTimeMillis();
		
		DataResponse<Aircraft> response = new DataResponse<Aircraft>();
		
		try 
		{
			aircraftService.saveAircraft(info);
			
		} catch (Exception e) {
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		}
		finally 
		{
			System.out.print("time = "+(System.currentTimeMillis() - start));
		}
		
		return response;
	}
	
	
	@RequestMapping(value = "save",method = RequestMethod.POST)
	@ApiOperation(value = "新增/编辑航空器信息")
	@Transactional(readOnly = false)
	public DataResponse<Aircraft> saves(@RequestBody Aircraft aircraft,HttpServletRequest request) {
		
      long start = System.currentTimeMillis();
		
		DataResponse<Aircraft> response = new DataResponse<Aircraft>();
		
		try 
		{
			aircraftService.save(aircraft);
			
		} catch (Exception e) {
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		}
		finally 
		{
			System.out.print("time = "+(System.currentTimeMillis() - start));
		}
		
		return response;
	}

//	/**
//	 * 根据企业ID获取航空器列表
//	 * 
//	 * @param companyId
//	 * @return
//	 */
//	@RequestMapping(value = "company", method = RequestMethod.GET)
//	@ApiOperation(value = "根据企业ID获取航空器列表")
//	public Page<Aircraft> getBycompanyId() {
//		Pageable pageable = new PageRequest(1, 5);
//		Page<Aircraft> st = aircraftService.findAircraftByCompany(1l, pageable);
//		return st;
//	}

	/**
	 * 根据企业ID获取和查询条件获取航空器列表
	 * 
	 * @param companyId
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/search/companyname", method = RequestMethod.GET)
	@ApiIgnore
	@ApiOperation(value = "获取航空器信息" ,notes = "根据公司ID和输入的查询条件查询航空器信息")
	@ApiImplicitParams({
			@ApiImplicitParam(value="公司ID",name="companyids",dataType="String"),
			@ApiImplicitParam(value="前台输入查询条件(国籍登记证号或型号的关键字)",name="copnameoricn",dataType="String")
	})
	public Page<Aircraft> getBycompanyIdAndname(@RequestParam(value = "pageno", required = false) String pageno,
			@RequestParam(value = "pagesize", required = false) String pagesize,
			@RequestParam(value = "companyids", required = false) String companyids,
			@RequestParam(value = "copnameoricn", required = false) String copnameoricn, HttpServletRequest request)
			throws UnsupportedEncodingException {
		Pageable pageable = new PageRequest(Integer.parseInt(pageno), Integer.parseInt(pagesize));
		Page<Aircraft> st = null;
		if (copnameoricn == null || "".equals(copnameoricn)) {
			st = aircraftService.findAircraftByCompany(companyids, pageable);
		} else {
			st = aircraftService.findAircraftByCompanyAndinfo(companyids,copnameoricn, pageable);
		}
		return st;
	}
	
	@RequestMapping(value = "/search/companyids", method = RequestMethod.GET)
	@ApiOperation(value = "获取航空器信息" ,notes = "根据公司ID查询航空器信息分页")
	@ApiImplicitParams({
			@ApiImplicitParam(value="公司ID",name="companyids",dataType="String",paramType="query")
	})
	public Page<Aircraft> getBycompanyId(@RequestParam(value = "pageno", required = false) String pageno,
			@RequestParam(value = "pagesize", required = false) String pagesize,
			@RequestParam(value = "companyids", required = false) String companyids,
			HttpServletRequest request)
			throws UnsupportedEncodingException {
		Pageable pageable = new PageRequest(Integer.parseInt(pageno), Integer.parseInt(pagesize));
		Page<Aircraft> st = null;
		
			st = aircraftService.findAircraftByCompany(companyids, pageable);
		
		return st;
	}
	
	

//	@SuppressWarnings("unchecked")
//	@ApiIgnore
//	public void addFlag(Page<Aircraft> st,HttpServletRequest request) {
//		String url = bpmUrl + "api/bpm/processRun/businessKey.ht";
//		for (Aircraft af : st.getContent()) {
//			try {
//				HashMap<String, String> map = new HashMap<>();
//				map.put("businessKey", af.getAircraftId() + "");
//				map.put("defId", "10000000500892");
//				ApiResponse<?> docRes = HttpUtils.get(url, map, request);
//				if (docRes != null) {
//					Map<String, String> maps = (HashMap<String, String>) docRes.getData();
//					af.setFlag(Integer.parseInt(maps.get("status")));
//				}
//			} catch (NumberFormatException e) {
//				e.printStackTrace();
//			}
//		}
//	}

//	/**
//	 * 根据几个企业ID获取航空器列表
//	 * 
//	 * @param companyId
//	 * @return
//	 */
//	@RequestMapping(value = "/search/companyids", method = RequestMethod.GET)
//	@ApiOperation(value = "获取当前航空器信息")
//	public Page<Aircraft> getBycompanyIds(@RequestParam(value = "pageno", required = false) String pageno,
//			@RequestParam(value = "pagesize", required = false) String pagesize,
//			@RequestParam(value = "companyids", required = false) String companyids,
//			@RequestParam(value = "copnameoricn", required = false) String copnameoricn) {
//		Pageable pageable = new PageRequest(Integer.parseInt(pageno), Integer.parseInt(pagesize));
//		return aircraftService.findAircraftBycompanyIds(copnameoricn, companyids, pageable);
//	}

	/**
	 * 根据企业ID获取航空器型号及数量
	 * @param companyids
	 * @return List<Object>
	 */
	@RequestMapping(value = "/search/findaircraftmodelbycompanyids", method = RequestMethod.GET)
	@ApiIgnore
	@ApiOperation(value = "获取公司名下同型号航空器的数量",notes = "返回两个参数，第一个参数为航空器型号，第二个参数为该型号航空器数量")
	@ApiImplicitParam(value="公司ID(可以是','分隔的多个id，可以是单个id)",name="companyids",dataType="String")
	public List<Object> findModelByCompanyids(
			@RequestParam(value = "companyids", required = false) String companyids) {
		return aircraftService.findModelByCompanyids(companyids);
	}

	/**
	 * 根据企业ID获取航空器类别及数量
	 * @param companyId
	 * @return List<Object>
	 */
	@RequestMapping(value = "/search/findaircraftclassbycompany", method = RequestMethod.GET)
	@ApiIgnore
	@ApiOperation(value = "获取公司名下同类别航空器数量",notes = "返回两个参数，第一个参数为航空器类别，第二个参数为该型号航空器数量")
	@ApiImplicitParam(value="公司ID(可以是','分隔的多个id，可以是单个id)",name="companyIds",dataType="String")
	public List<Object> getAircraftClassNumByCompany(
			@RequestParam(value = "companyIds", required = false) String companyIds) {
		return aircraftService.getAircraftClassNumByCompany(companyIds);
	}

	@ExceptionHandler(EmptyResultDataAccessException.class)
	@ApiIgnore
	public String emptyResultDataAccessException(Throwable t) {
		return "error";
	}

	@RequestMapping(value = "/search/searchAircrafts", method = RequestMethod.GET)
	@ApiIgnore
	@ApiOperation(value = "查询所有航空器的信息")
	public DataResponse<List<Aircraft>> searchAircrafts() {
		DataResponse<List<Aircraft>> response = new DataResponse<List<Aircraft>>();
		try {
			List<Aircraft> list = new ArrayList<Aircraft>();
			list = aircraftService.searchAircrafts();
			response.setData(list);
			response.setResultCode(Constants.OK);
		} catch (Exception e) {
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
	@RequestMapping(value = "/search/searchAircraftsByCompanyName", method = RequestMethod.GET)
	@ApiOperation(value = "根据公司名称查询航空器信息")
	@ApiIgnore
	@ApiImplicitParam(value="公司名称",name="companyName",dataType="String")
	public DataResponse<List<Aircraft>> searchAircraftsByCompanyId(@RequestParam(value = "companyName") String companyName){
		DataResponse<List<Aircraft>> response = new DataResponse<List<Aircraft>>();
		try {
			List<Aircraft> list = new ArrayList<Aircraft>();
			list = aircraftService.searchAircraftsByCompanyName(companyName);
			response.setData(list);
			response.setResultCode(Constants.OK);
		} catch (Exception e) {
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
	/**
	 * 根据多个企业ID和开始截止时间获取视图中航空器飞行总时长
	 * @param companyIds
	 * @param startDate
	 * @param endDate
	 * @return DataResponse<List<Object>>
	 */
	@RequestMapping(value = "/search/findallaircraftfly", method = RequestMethod.GET)
	@ApiIgnore
	@ApiOperation(value = "获取公司同型号航空器飞行总时长",notes = "返回两个参数，第一个参数为航空器型号，第二个参数为该型号航空器飞行总时长")
	@ApiImplicitParams({
		@ApiImplicitParam(value="公司ID(可以是','分隔的多个id，可以是单个id)",name="companyIds",dataType="String"),
		@ApiImplicitParam(value="查询开始时间",name="startDate",dataType="String"),
		@ApiImplicitParam(value="查询截止时间",name="endDate",dataType="String"),
	})
	public DataResponse<List<Object>> findlistByCompany(
			@RequestParam(value = "companyIds", required = false) String companyIds,
			@RequestParam(value = "startDate", required = false) String startDate,
			@RequestParam(value = "endDate", required = false) String endDate) {
		DataResponse<List<Object>> response = new DataResponse<List<Object>>();
		try {
			List<Object> list = new ArrayList<Object>();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			Date sdate = sdf.parse(startDate);
			Date edate = sdf.parse(endDate);
			list = aircraftFlySumRepository.countIdAndTime(companyIds, sdate, edate);
			response.setData(list);
			response.setResultCode(Constants.OK);
		} catch (Exception e) {
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
	@RequestMapping(value = "/search/findallaircraftflys", method = RequestMethod.GET)
	@ApiIgnore
	@ApiOperation(value = "获取公司同型号航空器飞行总时长",notes = "返回两个参数，第一个参数为航空器型号，第二个参数为该型号航空器飞行总时长")
	@ApiImplicitParams({
		@ApiImplicitParam(value="公司ID(可以是','分隔的多个id，可以是单个id)",name="companyIds",dataType="String"),
		@ApiImplicitParam(value="查询开始时间",name="startDate",dataType="String"),
		@ApiImplicitParam(value="查询截止时间",name="endDate",dataType="String"),
	})
	public DataResponse<List<Object>> findlistByCompanys(
			@RequestParam(value = "companyIds", required = false) String companyIds,
			@RequestParam(value = "startDate", required = false) String startDate,
			@RequestParam(value = "endDate", required = false) String endDate) {
		DataResponse<List<Object>> response = new DataResponse<List<Object>>();
		try {
			List<Object> list = new ArrayList<Object>();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			Date sdate = sdf.parse(startDate);
			Date edate = sdf.parse(endDate);
			list = aircraftFlySumRepository.countIdAndTimes(companyIds, sdate, edate);
			response.setData(list);
			response.setResultCode(Constants.OK);
		} catch (Exception e) {
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
	/**
	 * 根据多个企业ID与开始结束时间分页获取航空器列表
	 * @param pageno
	 * @param pagesize
	 * @param companyIds
	 * @param startDate
	 * @param endDate
	 * @return Page<AircraftFlySumView>
	 * @throws ParseException
	 */
	@RequestMapping(value = "/search/findviewlisttable", method = RequestMethod.GET)
	@ApiIgnore
	@ApiOperation(value = "查询公司航空器飞行总时长及航空器基本信息")
	@ApiImplicitParams({
		@ApiImplicitParam(value="公司ID(可以是','分隔的多个id，可以是单个id)",name="companyIds",dataType="String"),
		@ApiImplicitParam(value="查询开始时间",name="startDate",dataType="String"),
		@ApiImplicitParam(value="查询截止时间",name="endDate",dataType="String"),
	})
	public Page<AircraftFlySumView> findViewListByCompany(
			@RequestParam(value = "pageno", required = false) String pageno,
			@RequestParam(value = "pagesize", required = false) String pagesize,
			@RequestParam(value = "companyIds", required = false) String companyIds,
			@RequestParam(value = "startDate", required = false) String startDate,
			@RequestParam(value = "endDate", required = false) String endDate) throws ParseException {
		Pageable pageable = new PageRequest(Integer.parseInt(pageno), Integer.parseInt(pagesize));
		Page<AircraftFlySumView> st = null;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date sdate = sdf.parse(startDate);
		Date edate = sdf.parse(endDate);
		st = aircraftFlySumRepository.findAircraftFlySumList(companyIds,sdate,edate,pageable);
		return st;
	}
	
	/**
	 * 根据登记号查找航空器信息
	 * @param registernumber
	 * @param airworthinessCertificateNumber
	 * @param radioLicenceNumber
	 * @return DataResponse<Aircraft>
	 */
	@RequestMapping(value = "/search/isexistaircraft", method = RequestMethod.GET)
	@ApiIgnore
	@ApiOperation(value = "根据登记号查找航空器信息")
	@ApiImplicitParam(value="国籍登记证号",name="registernumber",dataType="String")
	public DataResponse<List<Aircraft>> isExistAircraft(@RequestParam(value = "registernumber", required = false) String registernumber){
		List<Aircraft> st = null;
		DataResponse<List<Aircraft>> response = new DataResponse<List<Aircraft>>();
		try{
			st = aircraftService.isExistAircraft(registernumber);
			response.setData(st);
			response.setResultCode(Constants.OK);
		} catch (Exception e) {
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
	/**
	 * 获取所有航空器所属公司列表
	 */
	@RequestMapping(value = "/search/airbelongcompanyname", method = RequestMethod.GET)
	@ApiIgnore
	@ApiOperation(value = "获取所有航空器所属公司名称",notes = "返回公司名称")
	public  DataResponse<List<Object>> airbelongcompanyname(){
		DataResponse<List<Object>> response = new DataResponse<List<Object>>();
		List<Object> st;
		try{
			st = aircraftService.airbelongcompanyname();
			response.setData(st);
			response.setResultCode(Constants.OK);
		} catch (Exception e) {
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
	@RequestMapping(value = "/search/findaircraftlistbycompanyid/{companyid}", method = RequestMethod.GET)
	@ApiIgnore
	@ApiOperation(value = "根据企业ID获取该企业所有的航空器列表")
	@ApiImplicitParams({
		@ApiImplicitParam(value="企业id",name="companyid",dataType="String")	
	})
	public DataResponse<List<Aircraft>> selectAircraftByCompanyId(@PathVariable("companyid") String companyid){
		long start = System.currentTimeMillis();	
		DataResponse<List<Aircraft>> response = new DataResponse<List<Aircraft>>();
		List<Aircraft>  list;
		try{
			Integer companyId = Integer.parseInt(companyid);
			list =  aircraftService.findByCompanyId(companyId);
			response.setData(list);
			response.setResultCode(Constants.OK);
		}catch(Exception e){
			e.printStackTrace();
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		}finally{
			System.out.print("time = "+(System.currentTimeMillis()-start));
		}
		return response;
	}
	
	@RequestMapping(value = "/search/findaircraftbyregnumber/{regnumber}", method = RequestMethod.GET)
	@ApiOperation(value = "根据航空器注册号获取航空器基本信息")
	@ApiImplicitParams({
		@ApiImplicitParam(value="航空器注册号",name="regnumber",dataType="String",paramType="path")	
	})
	public DataResponse<Aircraft> findByRegNumber(@PathVariable("regnumber") String regnumber){
		long start = System.currentTimeMillis();	
		DataResponse<Aircraft> response = new DataResponse<Aircraft>();
		Aircraft aircraft = new Aircraft();
		try{
			aircraft =  aircraftService.findByRegNumber(regnumber);
			response.setData(aircraft);
			response.setResultCode(Constants.OK);
		}catch(Exception e){
			e.printStackTrace();
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		}finally{
			System.out.print("time = "+(System.currentTimeMillis()-start));
		}
		return response;
	}
	
	@RequestMapping(value = "/search/findaircraftbyregnumbers", method = RequestMethod.GET)
	@ApiIgnore
	@ApiOperation(value = "根据航空器注册号获取航空器基本信息")
	@ApiImplicitParams({
		@ApiImplicitParam(value="航空器注册号",name="regNumber",dataType="String"),
		@ApiImplicitParam(value="航空器id",name="aircaftId",dataType="Long"),
		@ApiImplicitParam(value="公司id",name="companyId",dataType="Long")
	})
	public DataResponse<Aircraft> findByRegNumbers(@RequestParam("regNumber") String regNumber,@RequestParam("aircaftId") Long aircaftId,@RequestParam("companyId") Long companyId){
		long start = System.currentTimeMillis();	
		DataResponse<Aircraft> response = new DataResponse<Aircraft>();
		Aircraft aircraft = new Aircraft();
		try{
			aircraft =  aircraftService.findByRegNumberAndAircaftId(regNumber,aircaftId,companyId.intValue());
			response.setData(aircraft);
			response.setResultCode(Constants.OK);
		}catch(Exception e){
			e.printStackTrace();
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		}finally{
			System.out.print("time = "+(System.currentTimeMillis()-start));
		}
		return response;
	}
	@RequestMapping(value = "/search/findaircraftbyregnumberss", method = RequestMethod.GET)
	@ApiIgnore
	@ApiOperation(value = "根据航空器注册号获取航空器基本信息")
	@ApiImplicitParams({
		@ApiImplicitParam(value="航空器注册号",name="regNumber",dataType="String"),
		@ApiImplicitParam(value="公司id",name="companyId",dataType="Long")
	})
	public DataResponse<Aircraft> findByRegNumberss(@RequestParam("regNumber") String regNumber,@RequestParam("companyId") Long companyId){
		long start = System.currentTimeMillis();	
		DataResponse<Aircraft> response = new DataResponse<Aircraft>();
		Aircraft aircraft = new Aircraft();
		try{
			aircraft =  aircraftService.findByRegNumberAndCompanyId(regNumber,companyId.intValue());
			response.setData(aircraft);
			response.setResultCode(Constants.OK);
		}catch(Exception e){
			e.printStackTrace();
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		}finally{
			System.out.print("time = "+(System.currentTimeMillis()-start));
		}
		return response;
	}
	@RequestMapping(value = "/search/findaircraftlistbysuperid/{superid}", method = RequestMethod.GET)
	
	@ApiOperation(value = "根据监管局ID返回航空器列表")
	@ApiImplicitParams({
		@ApiImplicitParam(value="监管局id",name="superid",dataType="String",paramType="path")
		
	})
	public DataResponse<List<Aircraft>> findBySuperId(@PathVariable(value="superid",required=false)String superid){
		long start = System.currentTimeMillis();	
		DataResponse<List<Aircraft>> response = new DataResponse<List<Aircraft>>();
		List<Aircraft>  list;
		try{
			list =  aircraftService.findBySuperId(Long.parseLong(superid));
			response.setData(list);
			response.setResultCode(Constants.OK);			
		}catch(Exception e){
			e.printStackTrace();
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		}finally{
			System.out.print("time = "+(System.currentTimeMillis()-start));
		}
		return response;
	}
	
@RequestMapping(value = "/search/findaircraftlistbymanagerid/{managerid}", method = RequestMethod.GET)
	
	@ApiOperation(value = "根据管理局ID返回航空器列表")
	@ApiImplicitParams({
		
		@ApiImplicitParam(value="管理局id",name="managerid",dataType="String",paramType="path")
	})
	public DataResponse<List<Aircraft>> findByManagerId(@PathVariable(value="managerid",required=false)String managerid){
		long start = System.currentTimeMillis();	
		DataResponse<List<Aircraft>> response = new DataResponse<List<Aircraft>>();
		List<Aircraft>  list;
		try{
			list =  aircraftService.findByManagerId(Long.parseLong(managerid));
			response.setData(list);
			response.setResultCode(Constants.OK);			
		}catch(Exception e){
			e.printStackTrace();
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		}finally{
			System.out.print("time = "+(System.currentTimeMillis()-start));
		}
		return response;
	}

	/**
	 * 根据企业ID获取航空器列表导出
	 * 
	 * @param companyId
	 */
	@RequestMapping(value="search/downAircraftExcel",method = RequestMethod.POST)
	@ApiIgnore
	@ApiOperation(value = "根据企业ID获取航空器列表导出")
	public void getBycompanyId(@RequestParam final String companyId,@RequestParam final String regNumber,
			 @RequestParam  String aircraftStatus,HttpServletRequest res,HttpServletResponse rep) {
		try 
		{
			
			List<Aircraft> list = aircraftService.findAircraftByInfos(companyId,regNumber,aircraftStatus);
		  String name ="航空器列表";
	      ExcelExport<Aircraft> exports = new ExcelExport<Aircraft>() {};
	      exports.export(name, list, res, rep);
		} catch (Exception e) {
			e.printStackTrace();
			
		}

	}
	 @RequestMapping(value="search/findAircraftByInfo",method = RequestMethod.GET)
	 @ApiIgnore	
	 @ApiOperation(value = "支持按航空器注册号、航空器状态等进行查询的航空器信息(带分页)")
		@ApiImplicitParams({
			@ApiImplicitParam(
					value="企业系统id",name="companyId",dataType="String",paramType="query"),
			@ApiImplicitParam(
					value="航空器注册号",name="regNumber",dataType="string",paramType="query"),
			@ApiImplicitParam(
					value="航空器状态",name="aircraftStatus",dataType="string",paramType="query"),			
			
			@ApiImplicitParam(
					value="分页的每页数量",name="pageSize",dataType="String",paramType="query"),
			@ApiImplicitParam(
					value="当前页",name="currentPage",dataType="String",paramType="query"),
			
		})
	 public DataResponse<List<Aircraft>> findAircraftByInfo(@RequestParam final String companyId,@RequestParam final String regNumber,
			 @RequestParam  String aircraftStatus,
			 @RequestParam final Integer currentPage, @RequestParam("pageSize") final Integer pageSize){
			long start = System.currentTimeMillis();
			
			DataResponse<List<Aircraft>> response = new DataResponse<List<Aircraft>>();
			
			try 
			{
				
				Pageable pageable = new PageRequest(currentPage-1, pageSize);
				
					List<Aircraft> list = aircraftService.findAircraftByInfo(companyId,regNumber,aircraftStatus,pageable);
			    response.setData(list);
				response.setResultCode(Constants.OK);	
			} catch (Exception e) {
				e.printStackTrace();
				response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
				response.setMessage(e.getMessage());
			}
			finally 
			{
				System.out.print("time = "+(System.currentTimeMillis() - start));
			}
			
			return response;
		}
	 @RequestMapping(value="search/mut",method = RequestMethod.GET)
	 public DataResponse<List<Object>> mut(String name){
			long start = System.currentTimeMillis();
			
			DataResponse<List<Object>> response = new DataResponse<List<Object>>();
			
			try 
			{
				
					List<Object> list = aircraftService.mut(name);
			    response.setData(list);
				response.setResultCode(Constants.OK);	
			} catch (Exception e) {
				e.printStackTrace();
				response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
				response.setMessage(e.getMessage());
			}
			finally 
			{
				System.out.print("time = "+(System.currentTimeMillis() - start));
			}
			
			return response;
		}
	 
	 @RequestMapping(value = "search/searchAll", method = RequestMethod.GET)
		@ApiIgnore
		@ApiImplicitParam(value = "id", name = "companyId", dataType = "string", paramType = "query")
		public DataResponse<List<String>> getBycompanyIda(@RequestParam String companyId) {
			DataResponse<List<String>> response = new DataResponse<List<String>>();
			try {
				List<String> list = null;

				list = aircraftService.findallLists(companyId);

				response.setData(list);

				response.setResultCode(Constants.OK);

			} catch (Exception e) {
				response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
				response.setMessage(e.getMessage());
			}
			return response;
		}
	 
	 @RequestMapping(value = "search/searchfly", method = RequestMethod.GET)
		@ApiIgnore
		@ApiImplicitParam(value = "id", name = "companyId", dataType = "string", paramType = "query")
		public DataResponse<List<String>> getBycompanyIdas(@RequestParam String companyId) {
			DataResponse<List<String>> response = new DataResponse<List<String>>();
			try {
				List<String> list = null;

				list = aircraftService.findflyLists(companyId);

				response.setData(list);

				response.setResultCode(Constants.OK);

			} catch (Exception e) {
				response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
				response.setMessage(e.getMessage());
			}
			return response;
		}
	 
	 
}
