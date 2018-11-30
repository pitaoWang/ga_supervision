package com.atosenet.ga.company.mgt.controller.ga.employe;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.atosenet.ga.company.db.model.ga.employe.ControlEmpl;
import com.atosenet.ga.company.db.model.ga.employe.Employee;
import com.atosenet.ga.company.db.model.ga.employe.PersonExamRecord;
import com.atosenet.ga.company.db.model.ga.employe.PersonQulificationInfo;
import com.atosenet.ga.company.db.model.ga.employe.PhysicalRecord;
import com.atosenet.ga.company.db.model.ga.employe.Pilot;
import com.atosenet.ga.company.db.model.ga.employe.WorkRecord;
import com.atosenet.ga.company.db.model.ga.employe.vo.EmployeeVo;
import com.atosenet.ga.company.mgt.config.Constants;
import com.atosenet.ga.company.mgt.service.ga.company.GaCompanyService;
import com.atosenet.ga.company.mgt.service.ga.employe.ControlEmplService;
import com.atosenet.ga.company.mgt.service.ga.employe.EmployeeService;
import com.atosenet.ga.company.mgt.service.ga.employe.PersonExamRecordService;
import com.atosenet.ga.company.mgt.service.ga.employe.PersonQulificationInfoService;
import com.atosenet.ga.company.mgt.service.ga.employe.PhysicalRecordService;
import com.atosenet.ga.company.mgt.service.ga.employe.PilotService;
import com.atosenet.ga.company.mgt.service.ga.employe.WorkRecordService;
import com.atosenet.ga.company.mgt.service.ga.employe.response.DataResponse;
import com.atosenet.ga.company.mgt.util.ExcelExport;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@CrossOrigin
@RequestMapping(value="/eim/api/employee")
@Api(tags="所有企业人员的维护")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	@Autowired
	WorkRecordService workRecordService;
	@Autowired
	PhysicalRecordService physicalRecordService;

	@Autowired
	PersonQulificationInfoService personQulificationInfoService;

	@Autowired
	PersonExamRecordService personExamRecordService;

	@Autowired
	GaCompanyService gaCompanyService;
	
	@Autowired
	private PilotService pilotService;
	
	@Autowired
	private ControlEmplService ceService;

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ApiOperation(value = "获取单个人员详情")
	public DataResponse<Employee> getPilotById(@PathVariable("id") Long employeeId) {
		long start = System.currentTimeMillis();

		DataResponse<Employee> response = new DataResponse<Employee>();

		try {
			Employee employ = employeeService.get(employeeId);
			response.setData(employ);
			response.setResultCode(Constants.OK);
		} catch (Exception e) {
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		} finally {
			System.out.print("time = " + (System.currentTimeMillis() - start));
		}

		return response;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ApiOperation(value = "创建员工基本信息")
	@ApiImplicitParam(value = "员工的实体对象", name = "employee", paramType = "body")
	public DataResponse<Employee> saveEmployee(@RequestBody Employee  employee, HttpServletRequest request) {
		long start = System.currentTimeMillis();

		DataResponse<Employee> response = new DataResponse<Employee>();
		try {
			Employee retObj = employeeService.sava(employee, request);
			response.setData(retObj);
			response.setResultCode(Constants.OK);

		} catch (Exception e) {
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		} finally {
			System.out.print("time = " + (System.currentTimeMillis() - start));
		}

		return response;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ApiIgnore
	@ApiOperation(value = "修改员工基本信息")
	@ApiImplicitParam(value = "员工的实体对象", name = "employee", paramType = "body")
	public DataResponse<Employee> updateEmployee(@RequestBody EmployeeVo  employeevo, HttpServletRequest request) {

		DataResponse<Employee> response = new DataResponse<Employee>();
		
			Employee e = new Employee();
			e.setEmployeeId(employeevo.getEmployeeId());
			e.setCompanyId(employeevo.getCompanyId());
			e.setUserId(employeevo.getUserId());
			e.setUserName(employeevo.getUserName());
			e.setGender(employeevo.getGender());
			e.setJobTitle(employeevo.getJobTitle());
			e.setBirthday(employeevo.getBirthday());
			e.setNationality(employeevo.getNationality());
			e.setEthnicity(employeevo.getEthnicity());
			e.setEducation(employeevo.getEducation());
			e.setSchool(employeevo.getSchool());
			e.setCardId(employeevo.getCardId());
			e.setAuthorityId(employeevo.getAuthorityId());
			e.setSupervisionId(employeevo.getSupervisionId());
			e.setTechpos(employeevo.getTechpos());
			e.setTurnOut(employeevo.getTurnOut());
			e.setBaseId(employeevo.getBaseId());
			e.setContract(employeevo.getContract());
			e.setHomePostcode(employeevo.getHomePostcode());
			e.setHomeMobile(employeevo.getHomeMobile());
			e.setHomeAddress(employeevo.getHomeAddress());
			e.setEmail(employeevo.getEmail());
			e.setUrgencyLinkman(employeevo.getUrgencyLinkman());
			e.setIsagent(employeevo.getIsagent());
			e.setPhoto(employeevo.getPhoto());
			e.setScanCard(employeevo.getScanCard());
			e.setBackScancard(employeevo.getOthersideCard());
			try {
				Employee employee = this.employeeService.save(e);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			}
			
			if(e.getJobTitle().equals("飞行人员")){
				Pilot p = new Pilot();
				p.setPilotId(employeevo.getPilotId());
				p.setEmployeeId(employeevo.getEmployeeId());
				p.setTechClass(employeevo.getTechClass());
				p.setWqc(employeevo.getWqc());
				p.setEsCertificate(employeevo.getEsCertificate());
				p.setChCertificate(employeevo.getChCertificate());
				p.setScanning(employeevo.getScanning());
				p.setFlightTeam(employeevo.getFlightTeam());
				p.setFlySumTime(employeevo.getFlySumTime());
				p.setFlySumIllegal(employeevo.getFlySumIllegal());
				if(p.getTechClass().contains("教员")){
					p.setScannedTeacher(employeevo.getScannedTeacher());
					p.setTeacherQualification(employeevo.getTeacherQualification());
					p.setValidityTime(employeevo.getValidityTime());
					if(p.getTechClass().equals("理论教员")){
						p.setTeachCategories(employeevo.getTeachCategories());
					}
				}
				try {
					Pilot pilot = this.pilotService.sava(p, request);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
				}
			}
			
			if(e.getJobTitle().equals("管制人员")){
				ControlEmpl ce = new ControlEmpl();
				ce.setControlId(employeevo.getControlId());
				ce.setEmployeeId(employeevo.getEmployeeId());
				ce.setSkill(employeevo.getSkill());
				ce.setEnglishLevel(employeevo.getEnglishLevel());
				ce.setCommuStatus(employeevo.getCommuStatus());
				ce.setSkillLevel(employeevo.getSkillLevel());
				try {
					ControlEmpl controlEmpl = this.ceService.save(ce);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
				}
			}
			response.setResultCode(Constants.OK);
		return response;
	}
	

	@RequestMapping(value="/insert", method = RequestMethod.POST)
	@ApiIgnore
	@ApiOperation(value="创建员工基本信息，如果是飞行员或者管制人员，分别往子表里面添加")
	public DataResponse<Employee> insert(@RequestBody Map<String, String> data, HttpServletRequest request){
		
		DataResponse<Employee> response = new DataResponse<Employee>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Employee e = new Employee();
		
		if(data.get("userId")!=null && !data.get("userId").trim().equals("")){
			e.setUserId(Long.parseLong(data.get("userId")));
		}
		
		if(data.get("companyId")!=null && !data.get("companyId").trim().equals("") ){
			e.setCompanyId(Long.parseLong(data.get("companyId")));
		}
		
		if(data.get("workId")!= null&& !data.get("workId").trim().equals("")){
			e.setWorkId(data.get("workId"));	
		}
		
		if(data.get("school")!= null&& !data.get("school").trim().equals("")){
			e.setSchool(data.get("school"));
		}
		if(data.get("cardId")!= null&& !data.get("cardId").trim().equals("")){
			e.setCardId(data.get("cardId"));
		}
		
		if(data.get("homeMobile")!= null&& !data.get("homeMobile").trim().equals("")){
			e.setHomeMobile(data.get("homeMobile"));	
		}
		
		if(data.get("userName")!= null&& !data.get("userName").trim().equals("")){
			e.setUserName(data.get("userName"));	
		}
		
		if(data.get("age")!= null&& !data.get("age").trim().equals("")){
			e.setAge(data.get("age"));	
		}
		
		if(data.get("email")!= null&& !data.get("email").trim().equals("")){
			e.setEmail(data.get("email"));	
		}
		
		if(data.get("jobTitle")!= null&& !data.get("jobTitle").trim().equals("")){
			e.setJobTitle(data.get("jobTitle"));	
		}
		
		if(data.get("gender")!= null&& !data.get("gender").trim().equals("")){
			e.setGender(data.get("gender"));
		}
		
		if(data.get("baseId")!= null&& !data.get("baseId").trim().equals("")){
			e.setBaseId(Long.parseLong(data.get("baseId")));
		}
		
		if(data.get("birthday")!= null&& !data.get("birthday").trim().equals("")){
			//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				e.setBirthday(sdf.parse(data.get("birthday")));
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
		}
		
		if(data.get("urgencyLinkman")!= null&& !data.get("urgencyLinkman").trim().equals("")){
			e.setUrgencyLinkman(data.get("urgencyLinkman"));	
		}
		
		if(data.get("homeAddress")!= null&& !data.get("homeAddress").trim().equals("")){
			e.setHomeAddress(data.get("homeAddress"));	
		}
		
		if(data.get("homePostcode")!= null&& !data.get("homePostcode").trim().equals("")){
			e.setHomePostcode(data.get("homePostcode"));	
		}
		
		if(data.get("nationality")!= null&& !data.get("nationality").trim().equals("")){
			e.setNationality(data.get("nationality"));
		}
		
		if(data.get("ethnicity")!= null&& !data.get("ethnicity").trim().equals("")){
			e.setEthnicity(data.get("ethnicity"));
		}
		
		if(data.get("education")!= null&& !data.get("education").trim().equals("")){
			e.setEducation(data.get("education"));
		}
		
		if(data.get("isagent")!= null&& !data.get("isagent").trim().equals("")){
			e.setIsagent(data.get("isagent"));	
		}
		
		if(data.get("authorityId")!= null&& !data.get("authorityId").trim().equals("")){
			e.setAuthorityId(Long.parseLong(data.get("authorityId")));
		}
		
		if(data.get("supervisionId")!= null&& !data.get("supervisionId").trim().equals("")){
			e.setSupervisionId(Long.parseLong(data.get("supervisionId")));	
		}
		if(data.get("techpos")!= null&& !data.get("techpos").trim().equals("")){
			e.setTechpos(data.get("techpos"));
		}
		
		if(data.get("turnOut")!= null&& !data.get("turnOut").trim().equals("")){
			e.setTurnOut(data.get("turnOut"));	
		}
		
		if(data.get("contract")!= null&& !data.get("contract").trim().equals("")){
			e.setContract(data.get("contract"));
		}
		
		if(data.get("photo")!= null&& !data.get("photo").trim().equals("")){
			e.setPhoto(data.get("photo"));	
		}
		
		if(data.get("scanCard")!= null&& !data.get("scanCard").trim().equals("")){
			e.setScanCard(data.get("scanCard"));	
		}
		if(data.get("othersideCard")!= null &&!data.get("othersideCard").trim().equals("")){
			if(e.getScanCard() != null){
				String scanCard = e.getScanCard();
				StringBuffer sb = new StringBuffer(scanCard);
				StringBuffer buffer = sb.append(",").append(data.get("othersideCard"));
				e.setScanCard(buffer.toString());
			}else{
				StringBuffer sb = new StringBuffer(",");
				StringBuffer buffer = sb.append(data.get("othersideCard"));
				e.setScanCard(buffer.toString());
			}
		}
		
		
		Employee employee = null;
		try {
			employee = this.employeeService.sava(e, request);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e1.getMessage());
			return response;
		}
		Long id = employee.getEmployeeId();
		
		if(e.getJobTitle().equals("飞行人员")){
			Pilot pilot = new Pilot();
			pilot.setEmployeeId(id);
			pilot.setTechClass(data.get("techClass"));
			pilot.setFlightTeam(data.get("flightTeam"));
			
			if(data.get("flySumTime")!=null && !data.get("flySumTime").trim().equals("")){
				pilot.setFlySumTime(Long.parseLong(data.get("flySumTime")));
			}
			if(data.get("flySumIllegal")!=null && !data.get("flySumIllegal").trim().equals("")){
				pilot.setFlySumIllegal(Integer.parseInt(data.get("flySumIllegal")));
			}
			
			pilot.setWqc(data.get("wqc"));
			pilot.setEsCertificate(data.get("esCertificate"));
			pilot.setChCertificate(data.get("chCertificate"));
			pilot.setScanning(data.get("scanning"));
			if(pilot.getTechClass().contains("教员")){
				pilot.setScannedTeacher(data.get("scannedTeacher"));
				pilot.setTeacherQualification(data.get("teacherQualification"));
				try {
					pilot.setValidityTime(sdf.parse(data.get("validityTime")));
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(pilot.getTechClass().equals("理论教员")){
					pilot.setTeachCategories(data.get("teachCategories"));
				}
			}
			try {
				Pilot p = this.pilotService.sava(pilot, request);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
				response.setMessage(e1.getMessage());
				this.employeeService.delete(id);
				return response;
			}
			
		}else if(e.getJobTitle().equals("管制人员")){
			ControlEmpl ce = new ControlEmpl();
			ce.setEmployeeId(id);
			ce.setSkill(data.get("skill"));
			ce.setEnglishLevel(data.get("englishLevel"));
			ce.setCommuStatus(data.get("commuStatus"));
			ce.setSkillLevel(data.get("skillLevel"));
			try {
				ControlEmpl c = this.ceService.save(ce);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
				response.setMessage(e1.getMessage());
				this.employeeService.delete(id);
				return response;
			}
		}
		response.setData(employee);
		response.setResultCode(Constants.OK);
		return response;
	}
	
	
	@RequestMapping(value = "/findEmployeeByCartId", method = RequestMethod.GET)
	@ApiIgnore
	@ApiOperation(value = "根据企业员工身份证号查询员工信息")
	@ApiImplicitParam(value = "身份证账号", name = "cartId", dataType = "string", paramType = "query")
	public DataResponse<Employee> findEmployeeByCartId(@RequestParam("cartId") String employeeCardId) {
		long start = System.currentTimeMillis();

		DataResponse<Employee> response = new DataResponse<Employee>();

		try {
			// Long long_employeeCardId=Long.parseLong(employeeCardId);
			System.out.println(employeeCardId);
			Employee employ = employeeService.findEmployeeByCardId(employeeCardId);
			System.out.println(employ.toString());
			response.setData(employ);
			response.setResultCode(Constants.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		} finally {
			System.out.print("time = " + (System.currentTimeMillis() - start));
		}

		return response;
	}

	@RequestMapping(value = "search/findEmployeeByjobtitleAndcompanyId", method = RequestMethod.GET)
	
	@ApiOperation(value = "通过企业id和关键字和人员类型查询该企业下各个岗位的员工信息(带分页)")
	@ApiImplicitParams({
			@ApiImplicitParam(value = "企业系统id", name = "companyId", dataType = "Long", paramType = "query"),
			@ApiImplicitParam(value = "分页的每页数量", name = "pageSize", dataType = "Integer", paramType = "query"),
			@ApiImplicitParam(value = "当前页", name = "page", dataType = "Integer", paramType = "query"),
			@ApiImplicitParam(value = "人员类型（飞行人员，机务人员等）", name = "jobTitle", dataType = "string", paramType = "query"),
			@ApiImplicitParam(value = "输入关键字", name = "keyword", dataType = "string", paramType = "query"), })

	public DataResponse<List<Employee>> findEmployeeByjobtitleAndcompanyId(@RequestParam final Long companyId,
			@RequestParam final Integer page, @RequestParam("pageSize") final Integer pageSize,
			@RequestParam final String jobTitle, @RequestParam(name="keyword",required=false) final String keyword) {
		long start = System.currentTimeMillis();

		DataResponse<List<Employee>> response = new DataResponse<List<Employee>>();

		try {
			Integer offset = (page - 1) * pageSize;
			if (keyword == null || keyword.length() <= 0) {
				List<Employee> list = employeeService.findEmployeeByjobtitleAndcompanyId(companyId, offset, pageSize,
						jobTitle);
				response.setData(list);
			} else {
				List<Employee> list = employeeService.findEmployeeByjobtitleAndcompanyId1(companyId, offset, pageSize,
						jobTitle, keyword);
				response.setData(list);
			}

			response.setResultCode(Constants.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		} finally {
			System.out.print("time = " + (System.currentTimeMillis() - start));
		}

		return response;
	}

	@RequestMapping(value = "search/findcountEmployeeByjobtitleAndcompanyId", method = RequestMethod.GET)
	@ApiIgnore
	@ApiOperation(value = "通过企业id和关键字和人员类型查询该企业下各个岗位的员工的数量")
	@ApiImplicitParams({
			@ApiImplicitParam(value = "企业系统id", name = "companyId", dataType = "Long", paramType = "query"),
			@ApiImplicitParam(value = "分页的每页数量", name = "pageSize", dataType = "int", paramType = "query"),
			@ApiImplicitParam(value = "当前页", name = "page", dataType = "int", paramType = "query"),
			@ApiImplicitParam(value = "人员类型（飞行人员，机务人员等）", name = "jobTitle", dataType = "string", paramType = "query"),
			@ApiImplicitParam(value = "关键字", name = "keyword", dataType = "string", paramType = "query"), })
	public DataResponse<Object> findcountEmployeeByjobtitleAndcompanyId(@RequestParam final Long companyId,
			@RequestParam final Integer page, @RequestParam("pageSize") final Integer pageSize,
			@RequestParam final String jobTitle, @RequestParam final String keyword) {
		long start = System.currentTimeMillis();

		DataResponse<Object> response = new DataResponse<Object>();

		try {
			Integer offset = (page - 1) * pageSize;
			Integer acount = null;
			if (keyword == null || keyword.length() <= 0) {
				acount = employeeService.getcount(companyId, offset, pageSize, jobTitle);

			} else {
				acount = employeeService.getcount1(companyId, offset, pageSize, jobTitle, keyword);

			}

			response.setData(acount);

			response.setResultCode(Constants.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		} finally {
			System.out.print("time = " + (System.currentTimeMillis() - start));
		}

		return response;
	}

	@RequestMapping(value = "search/getdetailbyid", method = RequestMethod.GET)
	@ApiOperation(value = "获取单个人员资质等详情信息")
	@ApiIgnore
	@ApiImplicitParam(value = "企业员工系统id", name = "employeeId", dataType = "string", paramType = "query")

	public DataResponse<Map<String, Object>> getempById(@RequestParam("employeeId") String employeeId, HttpServletRequest request) {
		
		String page = request.getParameter("page");
		String size = request.getParameter("size");
		long start = System.currentTimeMillis();
		
		// ,@RequestParam final Integer page, @RequestParam("pageSize") final
		// Integer pageSize
		DataResponse<Map<String, Object>> response = new DataResponse<Map<String, Object>>();
		Map<String, Object> pilotMap = new HashMap<String, Object>();
		try {
			// 人员详情
			Employee employ = employeeService.get(Long.parseLong(employeeId));
			pilotMap.put("employee", employ);

			// 执照信息列表
			List<PersonQulificationInfo> PersonQulificationInfo = personQulificationInfoService
					.findByEmployeeId(Long.parseLong(employeeId));
			pilotMap.put("PersonQulificationInfolist", PersonQulificationInfo);

			// 改为分页查询
			Pageable pageable = new PageRequest(Integer.parseInt(page), Integer.parseInt(size));
			Page<PhysicalRecord> prList = physicalRecordService.findByEmployeeId(Long.parseLong(employeeId), pageable);
			
			pilotMap.put("prList", prList);

			
			//工作信息
			List<WorkRecord> workRecord = workRecordService.findByEmployeeId(Long.parseLong(employeeId));
			pilotMap.put("workRecordInfo", workRecord);
			
			//考试信息

			Page<PersonExamRecord> peList = personExamRecordService.findByEmployeeId(Long.parseLong(employeeId), pageable);
			pilotMap.put("peList", peList);

			response.setData(pilotMap);
			response.setResultCode(Constants.OK);

		} catch (Exception e) {
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		} finally {
			System.out.print("time = " + (System.currentTimeMillis() - start));
		}

		return response;
	}

	@RequestMapping(value = { "/search/companyEmployee", "" }, method = RequestMethod.GET)
	@ApiIgnore
	@ApiOperation(value = "分页查询监管局监管企业所有各个岗位的人员")
	@ApiImplicitParams({
			@ApiImplicitParam(value = "监管企业的系统id", name = "supervisionId", dataType = "string", paramType = "query"),
			@ApiImplicitParam(value = "分页的每页数量", name = "pageSize", dataType = "int", paramType = "query"),
			@ApiImplicitParam(value = "当前页", name = "page", dataType = "int", paramType = "query"),
			@ApiImplicitParam(value = "人员类型（飞行人员，机务人员等）", name = "jobTitle", dataType = "string", paramType = "query"),
			@ApiImplicitParam(value = "关键字", name = "keyword", dataType = "string", paramType = "query"), })
	public DataResponse<List<Employee>> searchCompanyEmployee(@RequestParam final String supervisionId,
			@RequestParam final Integer page, @RequestParam("pageSize") final Integer pageSize,
			@RequestParam final String keyword, @RequestParam final String jobTitle) {
		long start = System.currentTimeMillis();
		// DataResponse<List<PilotListVO>> response = new
		// DataResponse<List<PilotListVO>>();
		DataResponse<List<Employee>> response = new DataResponse<List<Employee>>();
		Long supervisionIdi = null;
		if (supervisionId != null) {
			supervisionIdi = Long.parseLong(supervisionId);
		}
		try {

			Integer offset = (page - 1) * pageSize;
			List<Employee> list = new ArrayList<Employee>();
			// 获得监管局底下的企业列表
			List<GaCompany> gaCompanyList = gaCompanyService.findBySupervisionIdList(supervisionIdi);
			if (gaCompanyList != null && gaCompanyList.size() > 0) {
				for (GaCompany gaCompany : gaCompanyList) {
					List<Employee> list1 = null;
					if (keyword == null || keyword.length() <= 0) {
						list1 = employeeService.findEmployeeByjobtitleAndcompanyId(gaCompany.getCompanyId(), offset,
								pageSize, jobTitle);
					} else {
						list1 = employeeService.findEmployeeByjobtitleAndcompanyId1(gaCompany.getCompanyId(), offset,
								pageSize, jobTitle, keyword);
					}
					list.addAll(list1);
				}
				response.setData(list);

			}

			response.setResultCode(Constants.OK);

		} catch (Exception e) {
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		} finally {
			System.out.print("time = " + (System.currentTimeMillis() - start));
		}

		return response;
	}

	@RequestMapping(value = "/search/searchEmployees", method = RequestMethod.GET)
	@ApiOperation(value = "查找所有人员集合")
	@ApiIgnore
	public DataResponse<List<Employee>> searchEmployeeByUsername() {

		DataResponse<List<Employee>> response = new DataResponse<List<Employee>>();
		try {
			List<Employee> list = null;

			// list=employeeService.searchEmployeeByUsername(username);
			list = employeeService.searchEmployees();
			System.out.println("list======" + list.toString());

			response.setData(list);

			response.setResultCode(Constants.OK);

		} catch (Exception e) {
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		}
		return response;
	}

	@RequestMapping(value = "search/findAllEmployeeBycompanyId", method = RequestMethod.GET)
	@ApiIgnore
	@ApiOperation(value = "通过企业id查询该企业下各个类型员工的数量")
	@ApiImplicitParam(value = "企业的系统id", name = "companyId", dataType = "Long", paramType = "query")
	public DataResponse<List<Object>> findEmployeeAllBycompanyId(@RequestParam final Long companyId) {
		long start = System.currentTimeMillis();

		DataResponse<List<Object>> response = new DataResponse<List<Object>>();

		try {

			List<Object> list = employeeService.getcountbytype(companyId);
			response.setData(list);

			response.setResultCode(Constants.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		} finally {
			System.out.print("time = " + (System.currentTimeMillis() - start));
		}

		return response;
	}

	@RequestMapping(value = "search/findEmployeeeduBycompanyId", method = RequestMethod.GET)
	@ApiOperation(value = "通过企业id查询该企业员工的教育分布")
	@ApiIgnore
	@ApiImplicitParam(value = "企业的系统id", name = "companyId", dataType = "Long", paramType = "query")
	public DataResponse<List<Object>> findEmployeeeduBycompanyId(@RequestParam final Long companyId) {
		long start = System.currentTimeMillis();

		DataResponse<List<Object>> response = new DataResponse<List<Object>>();
		try {
			List<Object> list = employeeService.getcountbyedu(companyId);
			response.setData(list);

			response.setResultCode(Constants.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		} finally {
			System.out.print("time = " + (System.currentTimeMillis() - start));
		}

		return response;
	}
	
	@RequestMapping(value = "search/getPilotLicenseTypeCount", method = RequestMethod.GET)
	@ApiOperation(value = "通过企业id查询该企业员工飞行执照类型分布")
	@ApiIgnore
	public DataResponse<List<Object>> getPilotLicenseTypeCount(@RequestParam final Long companyId){
		
		DataResponse<List<Object>> response = new DataResponse<List<Object>>();
		
		try {
			List<Object> list=employeeService.getPilotLicenseTypeCount(companyId);
			response.setData(list);
			response.setResultCode(Constants.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setMessage(e.getMessage());
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	@RequestMapping(value = "search/getPilotLicenseTypeCountByauthorityId", method = RequestMethod.GET)
	@ApiOperation(value = "通过管理局id查询员工飞行执照类型分布")
	@ApiIgnore
	public DataResponse<List<Object>> getPilotLicenseTypeCountByauthorityId(@RequestParam final Long authorityId){
		
		DataResponse<List<Object>> response = new DataResponse<List<Object>>();
		
		try {
			List<Object> list=employeeService.getPilotLicenseTypeCountByauthorityId(authorityId);
			response.setData(list);
			response.setResultCode(Constants.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setMessage(e.getMessage());
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	@RequestMapping(value = "search/getPilotLicenseTypeCountBysupervisionId", method = RequestMethod.GET)
	@ApiOperation(value = "通过监管局id查询员工飞行执照类型分布")
	@ApiIgnore
	public DataResponse<List<Object>> getPilotLicenseTypeCountBysupervisionId(@RequestParam final Long supervisionId){
		
		DataResponse<List<Object>> response = new DataResponse<List<Object>>();
		
		try {
			List<Object> list=employeeService.getPilotLicenseTypeCountBysupervisionId(supervisionId);
			response.setData(list);
			response.setResultCode(Constants.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setMessage(e.getMessage());
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	
	
	

	@RequestMapping(value = "search/supervisionId", method = RequestMethod.GET)
	@ApiIgnore
	@ApiOperation(value = "查询监管局下企业员工的教育分布")
	@ApiImplicitParam(value = "监管企业的系统id", name = "supervisionId", dataType = "Long", paramType = "query")
	public DataResponse<List<Object>> findEmployeeeduBySupId(@RequestParam final Long supervisionId) {
		long start = System.currentTimeMillis();
		System.out.println("supervisionId==========" + supervisionId);
		DataResponse<List<Object>> response = new DataResponse<List<Object>>();

		try {
			List<Object> list = employeeService.getGroupEmpEduBySupId(supervisionId);
			response.setData(list);
			response.setResultCode(Constants.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		} finally {
			System.out.print("time = " + (System.currentTimeMillis() - start));
		}

		return response;
	}

	@RequestMapping(value = "search/authorityId", method = RequestMethod.GET)
	@ApiIgnore
	@ApiOperation(value = "通过管理局id查询该企业员工和教育分布")
	@ApiImplicitParam(value = "管理局企业的系统id", name = "authorityId", dataType = "Long", paramType = "query")
	public DataResponse<List<Object>> findEmployeeeduByautid(@RequestParam final Long authorityId) {

		DataResponse<List<Object>> response = new DataResponse<List<Object>>();

		try {
			List<Object> list = employeeService.getGroupEmployeeEductionByauthorityId(authorityId);
			response.setData(list);
			response.setResultCode(Constants.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		}

		return response;
	}

	@RequestMapping(value = "search/findEmployeeAndcountBysupId", method = RequestMethod.GET)
	@ApiIgnore
	@ApiOperation(value = "通过监管id查询该企业各职务员工分布")
	@ApiImplicitParam(value = "监管企业的系统id", name = "supervisionId", dataType = "string", paramType = "query")
	public DataResponse<List<Object>> findEmployeeAndcountBysupId(@RequestParam final String supervisionId) {
		long start = System.currentTimeMillis();

		DataResponse<List<Object>> response = new DataResponse<List<Object>>();

		try {

			List<Object> list = employeeService.findEmployeeAndcountBysupId(Long.parseLong(supervisionId));
			response.setData(list);

			response.setResultCode(Constants.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		} finally {
			System.out.print("time = " + (System.currentTimeMillis() - start));
		}

		return response;
	}

	@RequestMapping(value = "search/findEmployeeAndcountByautid", method = RequestMethod.GET)
	@ApiIgnore
	@ApiOperation(value = "通过管理局id查询该企业各职务员工分布")
	@ApiImplicitParam(value = "管理企业的系统id", name = "authorityId", dataType = "string", paramType = "query")
	public DataResponse<List<Object>> findEmployeeAndcountByautid(@RequestParam final String authorityId) {
		long start = System.currentTimeMillis();

		DataResponse<List<Object>> response = new DataResponse<List<Object>>();

		try {

			List<Object> list = employeeService.findEmployeeAndcountByautid(Long.parseLong(authorityId));
			response.setData(list);

			response.setResultCode(Constants.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		} finally {
			System.out.print("time = " + (System.currentTimeMillis() - start));
		}

		return response;
	}

	@RequestMapping(value = "/search/findEmployeetoPilotListBycompanyId", method = RequestMethod.GET)
	@ApiIgnore
	@ApiOperation(value = "根据公司Id来查询飞行人员的集合")
	@ApiImplicitParam(value = "企业的系统id", name = "companyId", dataType = "string", paramType = "query")
	public DataResponse<List<Employee>> findEmployeetoPilotListBycompanyId(@RequestParam String companyId) {

		DataResponse<List<Employee>> response = new DataResponse<List<Employee>>();
		try {
			List<Employee> list = null;

			// list=employeeService.searchEmployeeByUsername(username);
			list = employeeService.findEmployeeListBycompanyId(companyId);
			System.out.println("list======" + list.toString());

			response.setData(list);

			response.setResultCode(Constants.OK);

		} catch (Exception e) {
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
	@ApiIgnore
	@RequestMapping(value = "search/byuserid", method = RequestMethod.GET)
	@ApiOperation(value = "通过用户id获取单个人员详情")
	@ApiImplicitParam(value = "用户的系统id", name = "userid", dataType = "string", paramType = "query")
	public DataResponse<Employee> getPilotByuseidId(@RequestParam String userid) {
		long start = System.currentTimeMillis();

		DataResponse<Employee> response = new DataResponse<Employee>();

		try {
			Employee employ = employeeService.findEmployeeByuserid(Long.parseLong(userid));
			response.setData(employ);
			response.setResultCode(Constants.OK);
		} catch (Exception e) {
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		} finally {
			System.out.print("time = " + (System.currentTimeMillis() - start));
		}

		return response;
	}
	
	/**
	 * 根据用户id查询人员集合
	 * 
	 */
	@RequestMapping(value = "/search/byuserids", method = RequestMethod.GET)
	@ApiOperation(value = "通过用户id人员集合")
	@ApiImplicitParam(value = "用户的系统id", name = "userid", dataType = "string", paramType = "query")
	public DataResponse<List<Employee>> getPilotByuseidIds(@RequestParam String userid) {
		long start = System.currentTimeMillis();

		DataResponse<List<Employee>> response = new DataResponse<List<Employee>>();

		try {
			List<Employee> employ = employeeService.findEmployeeByuserids(Long.parseLong(userid));
			response.setData(employ);
			response.setResultCode(Constants.OK);
		} catch (Exception e) {
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		} finally {
			System.out.print("time = " + (System.currentTimeMillis() - start));
		}

		return response;
	}
	
	/*
	 * 根据用户id 查询企业人员   （一对一）
	 */
	@RequestMapping(value = "/search/byUserId", method = RequestMethod.GET)
	@ApiOperation(value = "通过用户id查询人员")
	@ApiIgnore
	@ApiImplicitParam(value = "用户的系统id", name = "userid", dataType = "String", paramType = "query")
	public DataResponse<Employee> getEmployeeByUserId(@RequestParam String userId){
		DataResponse<Employee> response = new DataResponse<>();
		try {
			Employee employee = this.employeeService.findEmployeeByuserid(Long.parseLong(userId));
			response.setData(employee);
			response.setResultCode(Constants.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			response.setMessage(e.getMessage());
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
		}
		return response;	
	}
	
	
	
	@ApiIgnore
	@RequestMapping(value = "search/findjobtitlebyuserid", method = RequestMethod.GET)
	@ApiOperation(value = "通过用户id获取单个人员的岗位类型")
	@ApiImplicitParam(value = "用户的系统id", name = "userid", dataType = "string", paramType = "query")
	public DataResponse<Object> findjobtitlebyuserid(@RequestParam String userid) {
		long start = System.currentTimeMillis();

		DataResponse<Object> response = new DataResponse<Object>();

		try {
			String jobtitle = employeeService.findjobtitlebyuserid(Long.parseLong(userid));
			response.setData(jobtitle);
			response.setResultCode(Constants.OK);
		} catch (Exception e) {
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		} finally {
			System.out.print("time = " + (System.currentTimeMillis() - start));
		}
		return response;
	}
	
	@RequestMapping(value = "search/bybaseid", method = RequestMethod.GET)
	@ApiOperation(value = "根据运行基地ID获取基地人员列表")
	@ApiImplicitParam(value = "运行基地id", name = "baseid", dataType = "string", paramType = "query")
	public DataResponse<List<Employee>> getBybaseId(@RequestParam String baseid) {
		DataResponse<List<Employee>> response = new DataResponse<List<Employee>>();
		try {
			List<Employee> list = null;

			list = employeeService.findEmployeeListBybaseid(baseid);

			response.setData(list);

			response.setResultCode(Constants.OK);

		} catch (Exception e) {
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		}
		return response;
	}

	@RequestMapping(value = "search/searchAll", method = RequestMethod.GET)
	@ApiIgnore
	@ApiImplicitParam(value = "id", name = "companyId", dataType = "string", paramType = "query")
	public DataResponse<List<Employee>> getBycompanyIda(@RequestParam String companyId) {
		DataResponse<List<Employee>> response = new DataResponse<List<Employee>>();
		try {
			List<Employee> list = null;

			list = employeeService.findEmployeeLists(companyId);

			response.setData(list);

			response.setResultCode(Constants.OK);

		} catch (Exception e) {
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
	@ApiIgnore
	@RequestMapping(value="/search/findEmployeeByInfo",method = RequestMethod.GET)
	@ApiOperation(value = "支持按照姓名、岗位、类型、职位、手机号码等进行查询的员工信息(带分页)")
	@ApiImplicitParams({
		@ApiImplicitParam(
				value="企业系统id",name="companyId",dataType="String",paramType="query"),
		@ApiImplicitParam(
				value="姓名",name="userName",dataType="string",paramType="query"),
		@ApiImplicitParam(
				value="人员类型（飞行人员，机务人员等）",name="jobTitle",dataType="string",paramType="query"),
		@ApiImplicitParam(
				value="职位",name="techpos",dataType="string",paramType="query"),
		@ApiImplicitParam(
				value="手机号码",name="homeMobile",dataType="string",paramType="query"),
		@ApiImplicitParam(
				value="运行基地",name="baseId",dataType="string",paramType="query"),		
		@ApiImplicitParam(
				value="分页的每页数量",name="pageSize",dataType="String",paramType="query"),
		@ApiImplicitParam(
				value="当前页",name="currentPage",dataType="String",paramType="query"),
		
	})
 public DataResponse<List<Employee>> findEmployeeByInfo(
		 @RequestParam final String companyId,
		 @RequestParam final String userName,
		 @RequestParam  String jobTitle,
		 @RequestParam final String techpos,
		 @RequestParam final String homeMobile,
		 @RequestParam(required=false) final String cardId,
		 /*@RequestParam final String baseId,*/
		 @RequestParam final Integer currentPage, 
		 @RequestParam("pageSize") final Integer pageSize){
		long start = System.currentTimeMillis();
		
		DataResponse<List<Employee>> response = new DataResponse<List<Employee>>();
		
		try 
		{
			
			Pageable pageable = new PageRequest(currentPage-1, pageSize);
			if("行政人员".equals(jobTitle)){
				jobTitle="市场人员,财务人员,保障人员,飞行管理人员";
			}
			
				List<Employee> list = employeeService.findEmployeeByInfo(companyId,userName,jobTitle,techpos,
						                                    homeMobile,cardId,pageable);
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
 @ApiIgnore
 @RequestMapping(value="search/downEmployeeExcel",method = RequestMethod.POST)
 public void downEmployeeExcel(
		 @RequestParam final String companyId,
		 @RequestParam final String userName,
		 @RequestParam  String jobTitle,
		 @RequestParam final String techpos,
		 @RequestParam final String homeMobile,
		 @RequestParam(required=false) final String cardId,
		 /*String baseId,*/
		 HttpServletRequest res,HttpServletResponse rep
		 ){
 	try 
		{
 		if("行政人员".equals(jobTitle)){
			jobTitle="市场人员,财务人员,保障人员,飞行管理人员";
		}
			List<Employee> list = employeeService.findEmployeeByInfos(companyId,userName,jobTitle,techpos,
						                                    homeMobile,cardId);
		  String name ="人员列表";
	      ExcelExport<Employee> exports = new ExcelExport<Employee>() {};
	      exports.export(name, list, res, rep);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
 }
 @RequestMapping(value="{id}",method = RequestMethod.DELETE)
	@ApiOperation(value = "删除人员")
	@ApiIgnore
	public BaseResponse deleteEmployeeById(@PathVariable("id") String id)
	{
		long start = System.currentTimeMillis();
		
		BaseResponse response = new BaseResponse();
		
		try 
		{
			employeeService.delete(Long.parseLong(id));
			
		} catch (Exception e) {
			System.out.println(e);
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
