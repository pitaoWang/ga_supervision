package com.atosenet.ga.company.mgt.controller.ga.employe;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atosenet.ga.company.db.model.ga.employe.PersonExamRecord;
import com.atosenet.ga.company.db.model.ga.employe.PilotLicenseCheckRecord;
import com.atosenet.ga.company.mgt.config.Constants;
import com.atosenet.ga.company.mgt.service.ga.employe.PersonExamRecordService;
import com.atosenet.ga.company.mgt.service.ga.employe.response.DataResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@CrossOrigin
@RequestMapping("/eim/api/personexam")
@Api(tags="员工考试记录")
public class PersonExamRecordController {
	@Autowired
	PersonExamRecordService personExamRecordService;
	
	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value="保存考试信息")
	@Transactional(readOnly=false)
	public DataResponse<PilotLicenseCheckRecord> save(@RequestBody PersonExamRecord personExamRecord,HttpServletRequest request) {
		DataResponse<PilotLicenseCheckRecord> response = new DataResponse<PilotLicenseCheckRecord>();
		
		if (personExamRecord.createTime == null) {
			personExamRecord.createTime = new Date();
		} else {
			personExamRecord.updateTime = new Date();
		}
		
		try {
			personExamRecordService.save(personExamRecord,request);
			response.setResultCode(Constants.OK);
		} catch (Exception e) {
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		}
		return response;	
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "删除当前考试信息")
	@ApiIgnore
	public void deleteById(@PathVariable("id") Long id) {
		personExamRecordService.delete(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@RequestMapping(value = "/batchDelete/{ids}", method = RequestMethod.DELETE)
	@ApiOperation(value = "根据ID批量删除考试信息")
	public void batchDelete(@PathVariable("ids") List<Long> ids) {
		personExamRecordService.batchDelete(ids);
	}
	
	/**
	 * 根据ID 分页获取考试记录
	 * @param employeeId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/serach/getbyemployeeId",method = RequestMethod.GET)
	@ApiOperation(value="根据员工ID分页获取某员工考试信息")
	@ApiImplicitParams({ @ApiImplicitParam(value="员工ID",name="employeeId",dataType="String"),
		@ApiImplicitParam(value = "页面大小", name = "pageSize", dataType = "int", paramType = "query"),
		@ApiImplicitParam(value = "当前页", name = "page", dataType = "int", paramType = "query")})
	public Page<PersonExamRecord> getByEmpId(@RequestParam("employeeId") String employeeId, @RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {	
		if(employeeId.equals("")||employeeId == null) {
			return null;
		}
		Pageable pageable = new PageRequest(page, pageSize);
		return personExamRecordService.findByEmployeeId(Integer.parseInt(employeeId), pageable);
	}
	
	/*
	 * 不分页
	 */
	@RequestMapping(value="/serach/getbyemployeeIds",method = RequestMethod.GET)
	@ApiOperation(value="获取某员工考试信息")
	@ApiImplicitParam(value="员工ID",name="employeeId",dataType="String")
	public List<PersonExamRecord> getByEmpId(@RequestParam("employeeId") String employeeId)
	{	
		if(employeeId.equals("")||employeeId == null){
			return null;
		}
		return personExamRecordService.findByEmployeeId(Integer.parseInt(employeeId));
	}
	
	
	
	@RequestMapping(value="/serach/getbyId",method = RequestMethod.GET)
	@ApiOperation(value="获取某条考试记录详细信息")
	@ApiImplicitParam(value="考试记录ID",name="id",dataType="String")
	public PersonExamRecord getById(@RequestParam("id") String id) {	
		return personExamRecordService.findById(Long.parseLong(id));
	}
}
