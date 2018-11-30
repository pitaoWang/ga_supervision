package com.atosenet.ga.company.mgt.controller.ga.employe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atosenet.ga.company.core.common.model.response.BaseResponse;
import com.atosenet.ga.company.db.model.ga.company.GaCompany;
import com.atosenet.ga.company.db.model.ga.company.vo.GaCompanyVO;
import com.atosenet.ga.company.db.model.ga.employe.PeriodicalCheckRecord;
import com.atosenet.ga.company.db.model.ga.employe.PersonExamRecord;
import com.atosenet.ga.company.db.model.ga.employe.PersonQulificationInfo;
import com.atosenet.ga.company.db.model.ga.employe.PhysicalRecord;
import com.atosenet.ga.company.db.model.ga.employe.Pilot;
import com.atosenet.ga.company.db.model.ga.employe.SkilledCheckRecord;
import com.atosenet.ga.company.db.model.ga.employe.UpdateCheckRecord;
import com.atosenet.ga.company.db.model.ga.employe.WorkRecord;
import com.atosenet.ga.company.db.model.ga.employe.vo.PilotListVO;
import com.atosenet.ga.company.mgt.config.Constants;
import com.atosenet.ga.company.mgt.service.ga.company.GaCompanyService;
import com.atosenet.ga.company.mgt.service.ga.employe.PeriodicalCheckRecordService;
import com.atosenet.ga.company.mgt.service.ga.employe.PersonExamRecordService;
import com.atosenet.ga.company.mgt.service.ga.employe.PersonQulificationInfoService;
import com.atosenet.ga.company.mgt.service.ga.employe.PhysicalRecordService;
import com.atosenet.ga.company.mgt.service.ga.employe.PilotService;
import com.atosenet.ga.company.mgt.service.ga.employe.SkilledCheckRecordService;
import com.atosenet.ga.company.mgt.service.ga.employe.UpdateCheckRecordService;
import com.atosenet.ga.company.mgt.service.ga.employe.WorkRecordService;
import com.atosenet.ga.company.mgt.service.ga.employe.dto.EmployeePilotDTO;
import com.atosenet.ga.company.mgt.service.ga.employe.response.DataResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@CrossOrigin
@RequestMapping("/eim/api/pilot")
@Api(tags="飞行人员的维护(包含单个人员全部信息)")
@ApiIgnore
public class PilotController {
	@Autowired
	SkilledCheckRecordService skilledCheckRecordService;
	@Autowired
	WorkRecordService workRecordService;
	
	@Autowired
	UpdateCheckRecordService updateCheckRecordService;
	@Autowired
	PeriodicalCheckRecordService periodicalCheckRecordService;
	@Autowired
	PilotService pilotService;
	
	@Autowired
	PhysicalRecordService physicalRecordService;
	
	@Autowired
	PersonQulificationInfoService personQulificationInfoService;
	@Autowired
	GaCompanyService gaCompanyService;
	@Autowired
	PersonExamRecordService personExamRecordService;
	/**
	 * 新增飞行员
	 * @param pilot
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
    @ApiOperation(value = "创建飞行员")
	@ApiIgnore
	public DataResponse<Pilot> savePilot(@RequestBody Pilot pilot,HttpServletRequest request)
	{
		long start = System.currentTimeMillis();
		
		DataResponse<Pilot> response = new DataResponse<Pilot>();
		
		try 
		{
	        Pilot result = pilotService.sava(pilot,request);
			response.setData(result);
			
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
	
	//根据employeeid查询飞行员
	@RequestMapping(value="/search/byEmployeeId", method=RequestMethod.GET)
	@ApiOperation(value = "根据人员id获取飞行员信息")
	public DataResponse<Pilot> queryByEmployeeId(@RequestParam("id") String employeeId){
		
		DataResponse<Pilot> response = new DataResponse<Pilot>();
		
		try {
			Pilot pilot = this.pilotService.getByEmployeeId(Long.parseLong(employeeId));
			response.setData(pilot);
			response.setResultCode(Constants.OK);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		}
		return response;
	}

	
	
	@RequestMapping(value="/search/{id}",method = RequestMethod.GET)
	@ApiOperation(value = "获取飞行员信息和员工信息")
	@ApiImplicitParam(
			value="id",name="人员的系统id",dataType="Long",paramType="path")
	public DataResponse<EmployeePilotDTO> getPilotAndEmployee(@PathVariable("id") Long employeeId)
	{
		long start = System.currentTimeMillis();
		
		DataResponse<EmployeePilotDTO> response = new DataResponse<EmployeePilotDTO>();
		
		try 
		{
			EmployeePilotDTO employeePilot=pilotService.queryEmployeeByEmployeeId(employeeId);
			response.setData(employeePilot);
			response.setResultCode(Constants.OK);	
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
	
	/**
	 * 根据employeeId查询飞行员
	 * @param employeeId
	 * @return
	 */
	@RequestMapping(value="{id}",method = RequestMethod.GET)
	@ApiOperation(value = "获取单个人员信息包含执照体检的一些信息")
	@ApiImplicitParam(value="id",name="人员的系统id",dataType="Long",paramType="path")
	public DataResponse<Map<String,Object>> getPilotById(@PathVariable("id") Long employeeId, HttpServletRequest request) {
		String page = request.getParameter("page");
		String size = request.getParameter("size");
		Pageable pageable = new PageRequest(Integer.parseInt(page), Integer.parseInt(size));
		long start = System.currentTimeMillis();
		//,@RequestParam final Integer page, @RequestParam("pageSize") final Integer pageSize
		DataResponse<Map<String,Object>> response = new DataResponse<Map<String,Object>>();
		Map<String,Object> pilotMap=new HashMap<String, Object>();
		try {
			//飞行员详情
			EmployeePilotDTO employee=pilotService.queryEmployeeByEmployeeId(employeeId);
			pilotMap.put("employee", employee);
			if(employee.getEmployee() != null && employee.getPilot() != null) {
				long companyId    = employee.getEmployee().getCompanyId();
				long pilotId      = employee.getPilot().getPilotId();
				//熟练检查记录  改为分页获取  TODO
				Page<SkilledCheckRecord> skilledCheckInfo = skilledCheckRecordService.findBypilotId(pilotId, pageable);
				pilotMap.put("skilledCheckInfo", skilledCheckInfo);
				//定期检查记录
				List<PeriodicalCheckRecord> periodicalCheckInfo = periodicalCheckRecordService.findBypilotId(pilotId);
				pilotMap.put("periodicalCheckInfo", periodicalCheckInfo);
				//跟新检查记录
				List<UpdateCheckRecord> updateCheckInfo = updateCheckRecordService.findBypilotId(pilotId);
				pilotMap.put("updateCheckInfo", updateCheckInfo);
			}
			
			//执照信息列表
			List<PersonQulificationInfo> PersonQulificationInfo = personQulificationInfoService.findByEmployeeId(employeeId);
			pilotMap.put("PersonQulificationInfolist", PersonQulificationInfo);
			
			//飞行员体检信息     改为分页
			Page<PhysicalRecord> prList = physicalRecordService.findByEmployeeId(employeeId, pageable);
			pilotMap.put("prList", prList);
			
			//工作信息
			List<WorkRecord> workRecord = workRecordService.findByEmployeeId(employeeId);
			pilotMap.put("workRecordInfo", workRecord);
			
			//飞行员考试信息      改为分页
			Page<PersonExamRecord> peList = personExamRecordService.findByEmployeeId(employeeId, pageable);
			pilotMap.put("peList", peList);

			response.setData(pilotMap);
			response.setResultCode(Constants.OK);
			
		} catch (Exception e) {
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		} finally {
			System.out.print("time = "+(System.currentTimeMillis() - start));
		}
		
		return response;
	}
	
	/**
	 * 根据employeeId查询飞行员
	 * @param employeeId
	 * @return
	 */
	@RequestMapping(value="/one/{id}",method = RequestMethod.GET)
	@ApiOperation(value = "获取单个人员信息包含执照体检的一些信息")
	@ApiIgnore
	@ApiImplicitParam(value="id",name="人员的系统id",dataType="Long",paramType="path")
	public DataResponse<Map<String,Object>> getPilotByIds(@PathVariable("id") Long employeeId, HttpServletRequest request) {
		
		
		long start = System.currentTimeMillis();
		//,@RequestParam final Integer page, @RequestParam("pageSize") final Integer pageSize
		DataResponse<Map<String,Object>> response = new DataResponse<Map<String,Object>>();
		Map<String,Object> pilotMap=new HashMap<String, Object>();
		try {
			//飞行员详情
			EmployeePilotDTO employee=pilotService.queryEmployeeByEmployeeId(employeeId);
			pilotMap.put("employee", employee);
			if(employee.getEmployee() != null && employee.getPilot() != null) {
				long companyId    = employee.getEmployee().getCompanyId();
				long pilotId      = employee.getPilot().getPilotId();
				//定期检查记录
				List<PeriodicalCheckRecord> periodicalCheckInfo = periodicalCheckRecordService.findBypilotId(pilotId);
				pilotMap.put("periodicalCheckInfo", periodicalCheckInfo);
				//跟新检查记录
				List<UpdateCheckRecord> updateCheckInfo = updateCheckRecordService.findBypilotId(pilotId);
				pilotMap.put("updateCheckInfo", updateCheckInfo);
			}
			
			//执照信息列表
			List<PersonQulificationInfo> PersonQulificationInfo = personQulificationInfoService.findByEmployeeId(employeeId);
			pilotMap.put("PersonQulificationInfolist", PersonQulificationInfo);
			
			//飞行员体检信息     改为分页
			List<PhysicalRecord> prList = physicalRecordService.findByEmployeeId(employeeId);
			pilotMap.put("prList", prList);
			
			//工作信息
			List<WorkRecord> workRecord = workRecordService.findByEmployeeId(employeeId);
			pilotMap.put("workRecordInfo", workRecord);
			
			//飞行员考试信息      改为分页
			List<PersonExamRecord> peList = personExamRecordService.findByEmployeeId(employeeId);
			pilotMap.put("peList", peList);

			response.setData(pilotMap);
			response.setResultCode(Constants.OK);
			
		} catch (Exception e) {
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		} finally {
			System.out.print("time = "+(System.currentTimeMillis() - start));
		}
		
		return response;
	}
	
	
	
	
	/**
	 * 删除飞行员
	 * @param id
	 * @return
	 */
	@RequestMapping(value="{id}",method = RequestMethod.DELETE)
	@ApiOperation(value = "删除飞行员")
	@ApiIgnore
	public BaseResponse deletePilotById(@PathVariable("id") Long id)
	{
		long start = System.currentTimeMillis();
		
		BaseResponse response = new BaseResponse();
		
		try 
		{
	        pilotService.delete(id);
			
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
	
	/**
	 * 分页查询莫个监管局监管的所有企业的所有飞行员，返回基本信息
	 * @param orgId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value={"/search/companyPilot",""},method =RequestMethod.GET)
	@ApiOperation(value = "分页查询监管局监管企业所有飞行员")
	@ApiImplicitParams({
		@ApiImplicitParam(
				value="监管企业的系统id",name="supervisionId",dataType="string",paramType="query"),
		@ApiImplicitParam(
				value="分页的每页数量",name="pageSize",dataType="int",paramType="query"),
		@ApiImplicitParam(
				value="当前页",name="page",dataType="int",paramType="query"),
		@ApiImplicitParam(
				value="人员类型（飞行人员，机务人员等）",name="jobTitle",dataType="string",paramType="query"),
		@ApiImplicitParam(
				value="搜索关键字",name="keyword",dataType="string",paramType="query"),
	})
	public DataResponse<List<PilotListVO>> searchCompanyPilot(@RequestParam final String supervisionId,@RequestParam final Integer page, @RequestParam("pageSize") final Integer pageSize,@RequestParam final String keyword,@RequestParam final String jobTitle )
	{
		Long supervisionIdi=null;
		if(supervisionId!=null){
			 supervisionIdi=Long.parseLong(supervisionId);	
		}
		long start = System.currentTimeMillis();	
		//DataResponse<List<PilotListVO>> response = new DataResponse<List<PilotListVO>>();	
		DataResponse<List<PilotListVO>> response=new DataResponse<List<PilotListVO>>();	
		try 
		{
			Integer offset = (page - 1) * pageSize;
			List<PilotListVO> pilotListVOs = null;
			Set<GaCompanyVO> companyVOList=new HashSet<GaCompanyVO>();
			//获得监管局底下的企业列表
			List<GaCompany> gaCompanyList = gaCompanyService.findBySupervisionIdList(supervisionIdi);
			if(gaCompanyList!=null&&gaCompanyList.size()>0) {
				List<Integer> companyIdList = new ArrayList<Integer>();
				for (GaCompany gaCompany : gaCompanyList) {	
					companyIdList.add(gaCompany.getCompanyId());
				}
				if(keyword == null || keyword.length() <= 0) {
					//根据企业列表获取企业的飞行员列表
					pilotListVOs = pilotService.queryPilotList(companyIdList,offset, pageSize,jobTitle);
					
				}else {
					//获得此监管局底下所有的满足keyword员工的列表，并获得此员工的企业列表								
					pilotListVOs = pilotService.querySerchPilotList(companyIdList,keyword,offset, pageSize,jobTitle);	
					for(PilotListVO pilotListVO : pilotListVOs) {
						Integer companyId=pilotListVO.companyId;
						GaCompanyVO gaCompanyVO=new GaCompanyVO();
						for(GaCompany gaCompany : gaCompanyList) {
							if(companyId==gaCompany.getCompanyId()) {
								gaCompanyVO.setCompanyId(companyId);
								gaCompanyVO.setName(gaCompany.getName());
								companyVOList.add(gaCompanyVO);
								continue;
							}
						}
					}
					
				} 
			}			
			if(companyVOList.size()>0) {
				gaCompanyList= null;  
				List<GaCompanyVO> gaList = new ArrayList<GaCompanyVO>();
				gaList.addAll(companyVOList); 
				response.setDataOther(gaList);
			}
			response.setData(pilotListVOs);
			
			response.setResultCode(Constants.OK);
			
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
	/**
	 * 分页查询企业所有飞行员的数量
	 * @param companyId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value={"/search/companyPilotcount",""},method =RequestMethod.GET)
	@ApiOperation(value = "分页查询监管局监管企业所有飞行员数量")
	@ApiImplicitParams({
		@ApiImplicitParam(
				value="监管企业的系统id",name="supervisionId",dataType="string",paramType="query"),
		@ApiImplicitParam(
				value="分页的每页数量",name="pageSize",dataType="int",paramType="query"),
		@ApiImplicitParam(
				value="当前页",name="page",dataType="int",paramType="query"),
		@ApiImplicitParam(
				value="人员类型（飞行人员，机务人员等）",name="jobTitle",dataType="string",paramType="query"),
		@ApiImplicitParam(
				value="搜索关键字",name="keyword",dataType="string",paramType="query"),
	})
	public DataResponse<Object> getPilotcount1(@RequestParam final String supervisionId,@RequestParam final Integer page, @RequestParam("pageSize") final Integer pageSize,@RequestParam final String keyword,@RequestParam final String jobTitle )
	{
		
		Long supervisionIdi=null;
		if(supervisionId!=null){
			 supervisionIdi=Long.parseLong(supervisionId);	
		}
		long start = System.currentTimeMillis();	
		//DataResponse<List<PilotListVO>> response = new DataResponse<List<PilotListVO>>();	
		DataResponse<Object> response=new DataResponse<Object>();	
		try 
		{
			Integer offset = (page - 1) * pageSize;
			Integer acount=null;
			//获得监管局底下的企业列表
			List<GaCompany> gaCompanyList = gaCompanyService.findBySupervisionIdList(supervisionIdi);
			if(gaCompanyList!=null&&gaCompanyList.size()>0) {
				List<Integer> companyIdList = new ArrayList<Integer>();
				for (GaCompany gaCompany : gaCompanyList) {	
					companyIdList.add(gaCompany.getCompanyId());
				}
				if(keyword == null || keyword.length() <= 0) {
					//根据企业列表获取企业的飞行员列表
					acount = pilotService.getacountpliot(companyIdList,offset, pageSize,jobTitle);
					
				}else {
					//获得此监管局底下所有的满足keyword员工的列表，并获得此员工的企业列表								
					acount = pilotService.getacountpliot3(companyIdList,keyword,offset, pageSize,jobTitle);
			
					
				} 
			}			
			response.setData(acount);
			
			response.setResultCode(Constants.OK);
			
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
	
	@RequestMapping(value={"/search/companyPilotByautidcount",""},method =RequestMethod.GET)
	@ApiOperation(value = "分页查询管理局企业所有飞行员")
	@ApiImplicitParams({
		@ApiImplicitParam(
				value="管理企业的系统id",name="authorityId",dataType="string",paramType="query"),
		@ApiImplicitParam(
				value="分页的每页数量",name="pageSize",dataType="int",paramType="query"),
		@ApiImplicitParam(
				value="当前页",name="page",dataType="int",paramType="query"),
		@ApiImplicitParam(
				value="人员类型（飞行人员，机务人员等）",name="jobTitle",dataType="string",paramType="query"),
		@ApiImplicitParam(
				value="搜索关键字",name="keyword",dataType="string",paramType="query"),
	})
	public DataResponse<Object> searchCompanyPilotByautid(@RequestParam final String authorityId,@RequestParam final Integer page, @RequestParam("pageSize") final Integer pageSize,@RequestParam final String keyword,@RequestParam final String jobTitle )
	{
		Long authorityIdi=null;
		if(authorityId!=null){
			authorityIdi=Long.parseLong(authorityId);	
		}
		long start = System.currentTimeMillis();	
		//DataResponse<List<PilotListVO>> response = new DataResponse<List<PilotListVO>>();	
		DataResponse<Object> response=new DataResponse<Object>();	
		try 
		{   
			Integer acount=null;
			Integer offset = (page - 1) * pageSize;
			//获得监管局底下的企业列表
			List<GaCompany> gaCompanyList = gaCompanyService.findByAuthorityIdList(authorityIdi);
			if(gaCompanyList!=null&&gaCompanyList.size()>0) {
				List<Integer> companyIdList = new ArrayList<Integer>();
				for (GaCompany gaCompany : gaCompanyList) {	
					companyIdList.add(gaCompany.getCompanyId());
				}
				if(keyword == null || keyword.length() <= 0) {
					//根据企业列表获取企业的飞行员列表
					acount = pilotService.getacountpliot(companyIdList,offset, pageSize,jobTitle);
					
				}else {
					//获得此监管局底下所有的满足keyword员工的列表，并获得此员工的企业列表								
					acount = pilotService.getacountpliot3(companyIdList,keyword,offset, pageSize,jobTitle);
			
					
				} 
			}	
			response.setData(acount);
			
			response.setResultCode(Constants.OK);
			
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
	
	
	@RequestMapping(value={"/search/companyPilotByautid",""},method =RequestMethod.GET)
	@ApiOperation(value = "分页查询管理局企业所有飞行员的数量")
	@ApiImplicitParams({
		@ApiImplicitParam(
				value="管理企业的系统id",name="authorityId",dataType="string",paramType="query"),
		@ApiImplicitParam(
				value="分页的每页数量",name="pageSize",dataType="int",paramType="query"),
		@ApiImplicitParam(
				value="当前页",name="page",dataType="int",paramType="query"),
		@ApiImplicitParam(
				value="人员类型（飞行人员，机务人员等）",name="jobTitle",dataType="string",paramType="query"),
		@ApiImplicitParam(
				value="搜索关键字",name="keyword",dataType="string",paramType="query"),
	})
	public DataResponse<List<PilotListVO>> searchCompanyPilotByautidcount(@RequestParam final String authorityId,@RequestParam final Integer page, @RequestParam("pageSize") final Integer pageSize,@RequestParam final String keyword,@RequestParam final String jobTitle )
	{
		Long authorityIdi=null;
		if(authorityId!=null){
			authorityIdi=Long.parseLong(authorityId);	
		}
		long start = System.currentTimeMillis();	
		//DataResponse<List<PilotListVO>> response = new DataResponse<List<PilotListVO>>();	
		DataResponse<List<PilotListVO>> response=new DataResponse<List<PilotListVO>>();	
		try 
		{
			Integer offset = (page - 1) * pageSize;
			List<PilotListVO> pilotListVOs = null;
			Set<GaCompanyVO> companyVOList=new HashSet<GaCompanyVO>();
			//获得监管局底下的企业列表
			List<GaCompany> gaCompanyList = gaCompanyService.findByAuthorityIdList(authorityIdi);
			if(gaCompanyList!=null&&gaCompanyList.size()>0) {
				List<Integer> companyIdList = new ArrayList<Integer>();
				for (GaCompany gaCompany : gaCompanyList) {	
					companyIdList.add(gaCompany.getCompanyId());
				}
				if(keyword == null || keyword.length() <= 0) {
					//根据企业列表获取企业的飞行员列表
					pilotListVOs = pilotService.queryPilotList(companyIdList,offset, pageSize,jobTitle);
					
				}else {
					//获得此监管局底下所有的满足keyword员工的列表，并获得此员工的企业列表								
					pilotListVOs = pilotService.querySerchPilotList(companyIdList,keyword,offset, pageSize,jobTitle);	
					for(PilotListVO pilotListVO : pilotListVOs) {
						Integer companyId=pilotListVO.companyId;
						GaCompanyVO gaCompanyVO=new GaCompanyVO();
						for(GaCompany gaCompany : gaCompanyList) {
							if(companyId==gaCompany.getCompanyId()) {
								gaCompanyVO.setCompanyId(companyId);
								gaCompanyVO.setName(gaCompany.getName());
								companyVOList.add(gaCompanyVO);
								continue;
							}
						}
					}
					
				} 
			}			
			if(companyVOList.size()>0) {
				gaCompanyList= null;  
				List<GaCompanyVO> gaList = new ArrayList<GaCompanyVO>();
				gaList.addAll(companyVOList); 
				response.setDataOther(gaList);
			}
			response.setData(pilotListVOs);
			
			response.setResultCode(Constants.OK);
			
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
	
	/**
	 * 分页查询企业所有飞行员，返回基本信息
	 * @param companyId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value={"/search/company",""},method =RequestMethod.GET)
	@ApiOperation(value = "分页查询企业所有飞行员")
	@ApiImplicitParams({
		@ApiImplicitParam(
				value="企业的系统id",name="companyId",dataType="Long",paramType="query"),
		@ApiImplicitParam(
				value="分页的每页数量",name="pageSize",dataType="int",paramType="query"),
		@ApiImplicitParam(
				value="当前页",name="page",dataType="int",paramType="query"),
		@ApiImplicitParam(
				value="人员类型（飞行人员，机务人员等）",name="jobTitle",dataType="string",paramType="query"),
		@ApiImplicitParam(
				value="搜索关键字",name="keyword",dataType="string",paramType="query"),
	})
	public DataResponse<List<PilotListVO>> searchCompanyPilot(@RequestParam final Long companyId,@RequestParam final Integer page, @RequestParam("pageSize") final Integer pageSize,@RequestParam final String keyword,@RequestParam final String jobTitle)
	{
		long start = System.currentTimeMillis();	
		DataResponse<List<PilotListVO>> response = new DataResponse<List<PilotListVO>>();				
		try 
		{
			Integer offset = (page - 1) * pageSize;
			List<PilotListVO> pilotListVOs;
			if(keyword == null || keyword.length() <= 0) {
				pilotListVOs = pilotService.queryPilot4Company(companyId,offset, pageSize,jobTitle);
			}else {
				pilotListVOs = pilotService.querySerchPilot4Company(companyId,keyword,offset, pageSize,jobTitle);
			} 
						
			response.setData(pilotListVOs);
			response.setResultCode(Constants.OK);
			
		} catch (Exception e) {
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		}
		finally 
		{
			System.out.print("time = "+(System.currentTimeMillis() - start));
		}
		long end = System.currentTimeMillis();
		System.out.println(end-start);
		return response;
	}
	
	@RequestMapping(value={"/search/companyPilotcountBycompanyId",""},method =RequestMethod.GET)
	@ApiOperation(value = "分页查询企业所有飞行员数量")
	@ApiImplicitParams({
		@ApiImplicitParam(
				value="企业的系统id",name="companyId",dataType="Long",paramType="query"),
		@ApiImplicitParam(
				value="分页的每页数量",name="pageSize",dataType="int",paramType="query"),
		@ApiImplicitParam(
				value="当前页",name="page",dataType="int",paramType="query"),
		@ApiImplicitParam(
				value="人员类型（飞行人员，机务人员等）",name="jobTitle",dataType="string",paramType="query"),
		@ApiImplicitParam(
				value="搜索关键字",name="keyword",dataType="string",paramType="query"),
	})
	public DataResponse<Object> getPilotcount2(@RequestParam final Long companyId,@RequestParam final Integer page, @RequestParam("pageSize") final Integer pageSize,@RequestParam final String keyword,@RequestParam final String jobTitle)
	{
		
	
		long start = System.currentTimeMillis();	
		//DataResponse<List<PilotListVO>> response = new DataResponse<List<PilotListVO>>();	
		DataResponse<Object> response=new DataResponse<Object>();	
		try 
		{
			Integer offset = (page - 1) * pageSize;
			Integer acount=null;
			//获得监管局底下的企业列表
		
			
			
				if(keyword == null || keyword.length() <= 0) {
					//根据企业列表获取企业的飞行员列表
					acount = pilotService.getacountpliot2(companyId,offset, pageSize,jobTitle);
					
				}else {
					//获得此监管局底下所有的满足keyword员工的列表，并获得此员工的企业列表								
					acount = pilotService.getacountpliot3(companyId,keyword,offset, pageSize,jobTitle);
			
					
				} 			
			response.setData(acount);
			
			response.setResultCode(Constants.OK);
			
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
	
	/**
	 * 分页查询企业飞行员，返回飞行员企业、执照等详细
	 * @param supervisionId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/search/company/more",method = RequestMethod.GET)
	@ApiOperation(value = "分页查询企业飞行员段时间内的飞行记录")
	@ApiImplicitParams({
		@ApiImplicitParam(
				value="企业的系统id",name="companyId",dataType="Long",paramType="query"),
		@ApiImplicitParam(
				value="分页的每页数量",name="pageSize",dataType="int",paramType="query"),
		@ApiImplicitParam(
				value="当前页",name="page",dataType="int",paramType="query"),
		@ApiImplicitParam(
				value="时间类型的字符串(yyyy-mm-dd)",name="begaindata",dataType="string",paramType="query"),
		@ApiImplicitParam(
				value="时间类型的字符串(yyyy-mm-dd",name="enddate",dataType="string",paramType="query"),
	})
	public DataResponse<List<PilotListVO>> searchCompanyPilotMore(@RequestParam final Long companyId,@RequestParam final Integer page, @RequestParam("pageSize") final Integer pageSize,@RequestParam final String begaindata,@RequestParam final String enddate)
	{
		long start = System.currentTimeMillis();
		
		DataResponse<List<PilotListVO>> response = new DataResponse<List<PilotListVO>>();
		
		try 
		{
			Integer offset = (page - 1) * pageSize;
			List<PilotListVO> pilots = pilotService.queryPilot4CompanyWithLicense(companyId, offset, pageSize,begaindata,enddate);
			
			response.setData(pilots);
			
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
	
	//飞行员飞行记录统计  根据企业id查询
	@RequestMapping(value="/search/company/statistic",method = RequestMethod.GET)
	public DataResponse<List<PilotListVO>> statistic(
			@RequestParam final Long companyId,
			@RequestParam final Integer page,
			@RequestParam("pageSize") final Integer pageSize,
			@RequestParam final String begaindata,
			@RequestParam final String enddate,
			@RequestParam final String type){
		
		DataResponse<List<PilotListVO>> response = new DataResponse<List<PilotListVO>>();
		Integer offset = (page - 1) * pageSize;
		try {
			List<PilotListVO> pilots = pilotService.statistic(companyId, offset, pageSize,begaindata,enddate,type);
			response.setData(pilots);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		}

		return response;
	}
	
	//飞行人员统计  根据管理局id查询
	@RequestMapping(value="/search/company/statisticByauthorityId",method = RequestMethod.GET)
	public DataResponse<List<PilotListVO>> statisticByauthorityId(
			@RequestParam final Long authorityId,
			@RequestParam final Integer page,
			@RequestParam("pageSize") final Integer pageSize,
			@RequestParam final String begaindata,
			@RequestParam final String enddate,
			@RequestParam final String type){
		
		DataResponse<List<PilotListVO>> response = new DataResponse<List<PilotListVO>>();
		Integer offset = (page - 1) * pageSize;
		try {
			List<PilotListVO> pilots = pilotService.statisticByauthorityId(authorityId, offset, pageSize,begaindata,enddate,type);
			response.setData(pilots);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		}

		return response;
	}
	
	//飞行人员统计  根据监管局id查询
	@RequestMapping(value="/search/company/statisticBysupervisionId",method = RequestMethod.GET)
	public DataResponse<List<PilotListVO>> statisticBysupervisionId(
			@RequestParam final Long supervisionId,
			@RequestParam final Integer page,
			@RequestParam("pageSize") final Integer pageSize,
			@RequestParam final String begaindata,
			@RequestParam final String enddate,
			@RequestParam final String type){
		
		DataResponse<List<PilotListVO>> response = new DataResponse<List<PilotListVO>>();
		Integer offset = (page - 1) * pageSize;
		try {
			List<PilotListVO> pilots = pilotService.statisticBysupervisionId(supervisionId, offset, pageSize,begaindata,enddate,type);
			response.setData(pilots);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		}

		return response;
	}
	
	//飞行人员统计  根据企业id查询
		@RequestMapping(value="/search/company/statisticBycompanyId",method = RequestMethod.GET)
		public DataResponse<List<PilotListVO>> statisticBycompanyId(
				@RequestParam final String companyId,
				@RequestParam final String beginDate,
				@RequestParam final String endDate
				){
			
			DataResponse<List<PilotListVO>> response = new DataResponse<List<PilotListVO>>();
			
			try {
				List<PilotListVO> pilots = pilotService.statisticBycompanyId(companyId, beginDate,endDate);
				response.setData(pilots);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
				response.setMessage(e.getMessage());
			}

			return response;
		}
	
	
		//飞行人员统计  根据企业id查询
				@RequestMapping(value="/search/company/statisticBycompanyIds",method = RequestMethod.GET)
				public DataResponse<List<PilotListVO>> statisticBycompanyIds(
						@RequestParam final String companyId,
						@RequestParam final String beginDate,
						@RequestParam final String endDate
						){
					
					DataResponse<List<PilotListVO>> response = new DataResponse<List<PilotListVO>>();
					
					try {
						List<PilotListVO> pilots = pilotService.statisticBycompanyIds(companyId, beginDate,endDate);
						response.setData(pilots);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
						response.setMessage(e.getMessage());
					}

					return response;
				}
			
		
	/*@RequestMapping(value="/search/company/statistic",method = RequestMethod.GET)
	public DataResponse<List<PilotListVO>> statistic(){
		DataResponse<List<PilotListVO>> response = new DataResponse<List<PilotListVO>>();
		//Integer offset = (page - 1) * pageSize;
		List<PilotListVO> pilots = pilotService.statistic();
		return null;
	}*/
	/*@RequestMapping(value="/search/company/morecount",method = RequestMethod.GET)
	@ApiOperation(value = "分页查询企业飞行员段时间内的飞行记录的数量")
	@ApiImplicitParams({
		@ApiImplicitParam(
				value="企业的系统id",name="companyId",dataType="Long",paramType="query"),
		@ApiImplicitParam(
				value="分页的每页数量",name="pageSize",dataType="int",paramType="query"),
		@ApiImplicitParam(
				value="当前页",name="page",dataType="int",paramType="query"),
		@ApiImplicitParam(
				value="时间类型的字符串(yyyy-mm-dd)",name="begaindata",dataType="string",paramType="query"),
		@ApiImplicitParam(
				value="时间类型的字符串(yyyy-mm-dd",name="enddate",dataType="string",paramType="query"),
	})
	public DataResponse<Object> searchmorePilotMoreCount(@RequestParam final Long companyId,@RequestParam final String begaindata,@RequestParam final String enddate)
	{
		long start = System.currentTimeMillis();
		DataResponse<Object> response = new DataResponse<Object>();
		try 
		{
			Integer count=null;
				count = pilotService.queryPilot4CompanyWithLicensecount(companyId,begaindata,enddate);	
			response.setData(count);
			
		} catch (Exception e) {
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		}
		finally 
		{
			System.out.print("time = "+(System.currentTimeMillis() - start));
		}
		return response;
	}*/
	
	@RequestMapping(value="/search/company/morecount",method = RequestMethod.GET)
	@ApiOperation(value = "分页查询企业飞行员段时间内的飞行记录的数量")
	@ApiImplicitParams({
		@ApiImplicitParam(
				value="企业的系统id",name="companyId",dataType="Long",paramType="query"),
		@ApiImplicitParam(
				value="分页的每页数量",name="pageSize",dataType="int",paramType="query"),
		@ApiImplicitParam(
				value="当前页",name="page",dataType="int",paramType="query"),
		@ApiImplicitParam(
				value="时间类型的字符串(yyyy-mm-dd)",name="begaindata",dataType="string",paramType="query"),
		@ApiImplicitParam(
				value="时间类型的字符串(yyyy-mm-dd",name="enddate",dataType="string",paramType="query"),
	})
	public DataResponse<Object> searchmorePilotMoreCount(@RequestParam final Long companyId,@RequestParam final String begaindata,@RequestParam final String enddate,@RequestParam final String type)
	{
		long start = System.currentTimeMillis();
		DataResponse<Object> response = new DataResponse<Object>();
		try 
		{
			Integer count=null;
				//count = pilotService.queryPilot4CompanyWithLicensecount(companyId,begaindata,enddate);
			count = pilotService.getCount(companyId, begaindata, enddate, type);
			
			response.setData(count);
			
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
	
	//分页查询 根据管理局 id
	@RequestMapping(value="/search/company/morecountByauthorityId",method = RequestMethod.GET)
	@ApiOperation(value = "分页查询企业飞行员段时间内的飞行记录的数量")
	@ApiIgnore
	@ApiImplicitParams({
		@ApiImplicitParam(
				value="企业的系统id",name="companyId",dataType="Long",paramType="query"),
		@ApiImplicitParam(
				value="分页的每页数量",name="pageSize",dataType="int",paramType="query"),
		@ApiImplicitParam(
				value="当前页",name="page",dataType="int",paramType="query"),
		@ApiImplicitParam(
				value="时间类型的字符串(yyyy-mm-dd)",name="begaindata",dataType="string",paramType="query"),
		@ApiImplicitParam(
				value="时间类型的字符串(yyyy-mm-dd",name="enddate",dataType="string",paramType="query"),
	})
	public DataResponse<Object> searchmorePilotMoreCountByauthorityId(@RequestParam final Long authorityId,@RequestParam final String begaindata,@RequestParam final String enddate,@RequestParam final String type)
	{
		long start = System.currentTimeMillis();
		DataResponse<Object> response = new DataResponse<Object>();
		try 
		{
			Integer count=null ; 
				//count = pilotService.queryPilot4CompanyWithLicensecount(companyId,begaindata,enddate);
			count = pilotService.getCountByauthorityId(authorityId, begaindata, enddate, type);
			
			response.setData(count);
			
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
	
	//分页查询 根据监管局 id
		@RequestMapping(value="/search/company/morecountBysupervisionId",method = RequestMethod.GET)
		@ApiOperation(value = "分页查询企业飞行员段时间内的飞行记录的数量")
		@ApiIgnore
		@ApiImplicitParams({
			@ApiImplicitParam(
					value="企业的系统id",name="companyId",dataType="Long",paramType="query"),
			@ApiImplicitParam(
					value="分页的每页数量",name="pageSize",dataType="int",paramType="query"),
			@ApiImplicitParam(
					value="当前页",name="page",dataType="int",paramType="query"),
			@ApiImplicitParam(
					value="时间类型的字符串(yyyy-mm-dd)",name="begaindata",dataType="string",paramType="query"),
			@ApiImplicitParam(
					value="时间类型的字符串(yyyy-mm-dd",name="enddate",dataType="string",paramType="query"),
		})
		public DataResponse<Object> searchmorePilotMoreCountBysupervisionId(@RequestParam final Long supervisionId,@RequestParam final String begaindata,@RequestParam final String enddate,@RequestParam final String type)
		{
			long start = System.currentTimeMillis();
			DataResponse<Object> response = new DataResponse<Object>();
			try 
			{
				Integer count=null;
					//count = pilotService.queryPilot4CompanyWithLicensecount(companyId,begaindata,enddate);
				count = pilotService.getCountBysupervisionId(supervisionId, begaindata, enddate, type);
				
				response.setData(count);
				
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
	
	
	
	
	@RequestMapping(value="/search/company/authmore",method = RequestMethod.GET)
	@ApiOperation(value = "分页管理企业飞行员时间段内飞行记录")
	@ApiImplicitParams({
		@ApiImplicitParam(
				value="管理企业的系统id",name="authorityId",dataType="string",paramType="query"),
		@ApiImplicitParam(
				value="分页的每页数量",name="pageSize",dataType="int",paramType="query"),
		@ApiImplicitParam(
				value="当前页",name="page",dataType="int",paramType="query"),
		@ApiImplicitParam(
				value="时间类型的字符串(yyyy-mm-dd)",name="begaindata",dataType="string",paramType="query"),
		@ApiImplicitParam(
				value="时间类型的字符串(yyyy-mm-dd",name="enddate",dataType="string",paramType="query"),
	})
	public DataResponse<List<PilotListVO>> searchauthPilotMore(@RequestParam final String authorityId,@RequestParam final Integer page, @RequestParam("pageSize") final Integer pageSize,@RequestParam final String begaindata,@RequestParam final String enddate)
	{
		long start = System.currentTimeMillis();
		Long authorityIdi=null;
		if(authorityId!=null){
			authorityIdi=Long.parseLong(authorityId);	
		}
		
		DataResponse<List<PilotListVO>> response = new DataResponse<List<PilotListVO>>();
		
		try 
		{
			List<PilotListVO> list = new ArrayList<PilotListVO>();

			Integer offset = (page - 1) * pageSize;
			List<GaCompany> gaCompanyList = gaCompanyService.findByAuthorityIdList(authorityIdi);
			if(gaCompanyList!=null&&gaCompanyList.size()>0) {
				List<Integer> companyIdList = new ArrayList<Integer>();
				for (GaCompany gaCompany : gaCompanyList) {	
					companyIdList.add(gaCompany.getCompanyId());
				}
			
				list = pilotService.queryPilot4CompanyWithLicense(companyIdList, offset, pageSize,begaindata,enddate);	
		
				} 
			else {
				list=null;
			}
			
			
			response.setData(list);
			
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
	
	@RequestMapping(value="/search/company/authcount",method = RequestMethod.GET)
	@ApiOperation(value = "分页管理企业飞行员时间段内飞行记录的数量")
	@ApiImplicitParams({
		@ApiImplicitParam(
				value="管理企业的系统id",name="authorityId",dataType="string",paramType="query"),
		@ApiImplicitParam(
				value="分页的每页数量",name="pageSize",dataType="int",paramType="query"),
		@ApiImplicitParam(
				value="当前页",name="page",dataType="int",paramType="query"),
		@ApiImplicitParam(
				value="时间类型的字符串(yyyy-mm-dd)",name="begaindata",dataType="string",paramType="query"),
		@ApiImplicitParam(
				value="时间类型的字符串(yyyy-mm-dd",name="enddate",dataType="string",paramType="query"),
	})
	public DataResponse<Object> searchauthPilotMoreCount(@RequestParam final String authorityId,@RequestParam final String begaindata,@RequestParam final String enddate)
	{
		long start = System.currentTimeMillis();
		Long authorityIdi=null;
		if(authorityId!=null){
			authorityIdi=Long.parseLong(authorityId);	
		}
		
		DataResponse<Object> response = new DataResponse<Object>();
		
		try {
			Integer count=null;
			List<GaCompany> gaCompanyList = gaCompanyService.findByAuthorityIdList(authorityIdi);
			if(gaCompanyList!=null&&gaCompanyList.size()>0) {
				List<Integer> companyIdList = new ArrayList<Integer>();
				for (GaCompany gaCompany : gaCompanyList) {	
					companyIdList.add(gaCompany.getCompanyId());
				}
			
				count = pilotService.queryPilot4CompanyWithLicensecount(companyIdList,begaindata,enddate);	
		
				} 
			else {
				count=null;
			}
			
			
			response.setData(count);
			
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
	
	@RequestMapping(value="/search/company/supmore",method = RequestMethod.GET)
	@ApiOperation(value = "分页监管企业飞行员时间段内飞行记录")
	@ApiImplicitParams({
		@ApiImplicitParam(
				value="监管企业的系统id",name="supervisionId",dataType="string",paramType="query"),
		@ApiImplicitParam(
				value="分页的每页数量",name="pageSize",dataType="int",paramType="query"),
		@ApiImplicitParam(
				value="当前页",name="page",dataType="int",paramType="query"),
		@ApiImplicitParam(
				value="时间类型的字符串(yyyy-mm-dd)",name="begaindata",dataType="string",paramType="query"),
		@ApiImplicitParam(
				value="时间类型的字符串(yyyy-mm-dd",name="enddate",dataType="string",paramType="query"),
	})
	public DataResponse<List<PilotListVO>> searchsupmorePilotMore(@RequestParam final String supervisionId,@RequestParam final Integer page, @RequestParam("pageSize") final Integer pageSize,@RequestParam final String begaindata,@RequestParam final String enddate)
	{
		long start = System.currentTimeMillis();
		Long supervisionIdi=null;
		if(supervisionId!=null){
			 supervisionIdi=Long.parseLong(supervisionId);	
		}
		
		DataResponse<List<PilotListVO>> response = new DataResponse<List<PilotListVO>>();
		
		try 
		{
			List<PilotListVO> list = new ArrayList<PilotListVO>();

			Integer offset = (page - 1) * pageSize;
			List<GaCompany> gaCompanyList = gaCompanyService.findBySupervisionIdList(supervisionIdi);
			if(gaCompanyList!=null&&gaCompanyList.size()>0) {
				List<Integer> companyIdList = new ArrayList<Integer>();
				for (GaCompany gaCompany : gaCompanyList) {	
					companyIdList.add(gaCompany.getCompanyId());
				}
			
				list = pilotService.queryPilot4CompanyWithLicense(companyIdList, offset, pageSize,begaindata,enddate);	
		
				} 
			else {
				list=null;
			}
			
			
			response.setData(list);
			
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
	
	@RequestMapping(value="/search/company/supmorecount",method = RequestMethod.GET)
	@ApiOperation(value = "监管企业飞行员时间段内飞行记录的数量")
	@ApiImplicitParams({
		@ApiImplicitParam(
				value="监管企业的系统id",name="supervisionId",dataType="string",paramType="query"),
		@ApiImplicitParam(
				value="分页的每页数量",name="pageSize",dataType="int",paramType="query"),
		@ApiImplicitParam(
				value="当前页",name="page",dataType="int",paramType="query"),
		@ApiImplicitParam(
				value="时间类型的字符串(yyyy-mm-dd)",name="begaindata",dataType="string",paramType="query"),
		@ApiImplicitParam(
				value="时间类型的字符串(yyyy-mm-dd",name="enddate",dataType="string",paramType="query"),
	})
	public DataResponse<Object> searchsupmorePilotMoreCount(@RequestParam final String supervisionId,@RequestParam final String begaindata,@RequestParam final String enddate)
	{
		long start = System.currentTimeMillis();
		Long supervisionIdi=null;
		if(supervisionId!=null){
			 supervisionIdi=Long.parseLong(supervisionId);	
		}
		
		DataResponse<Object> response = new DataResponse<Object>();
		
		try 
		{
			Integer count=null;
			List<GaCompany> gaCompanyList = gaCompanyService.findBySupervisionIdList(supervisionIdi);
			if(gaCompanyList!=null&&gaCompanyList.size()>0) {
				List<Integer> companyIdList = new ArrayList<Integer>();
				for (GaCompany gaCompany : gaCompanyList) {	
					companyIdList.add(gaCompany.getCompanyId());
				}
			
				count = pilotService.queryPilot4CompanyWithLicensecount(companyIdList,begaindata,enddate);	
		
				} 
			else {
				count=null;
			}
			
			
			response.setData(count);
			
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
	
}
