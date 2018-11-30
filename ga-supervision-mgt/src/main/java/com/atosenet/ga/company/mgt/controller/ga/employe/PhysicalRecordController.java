package com.atosenet.ga.company.mgt.controller.ga.employe;
import java.util.ArrayList;
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

import com.atosenet.ga.company.db.model.ga.employe.PhysicalRecord;
import com.atosenet.ga.company.db.model.ga.employe.PilotLicenseCheckRecord;
import com.atosenet.ga.company.mgt.config.Constants;
import com.atosenet.ga.company.mgt.service.ga.company.CompanyBusinessTypeService;
import com.atosenet.ga.company.mgt.service.ga.employe.PhysicalRecordService;
import com.atosenet.ga.company.mgt.service.ga.employe.response.DataResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@CrossOrigin
@RequestMapping("/eim/api/physical")
@Api(tags="员工体检记录")
public class PhysicalRecordController {

	@Autowired
	PhysicalRecordService physicalRecordService;
	@Autowired
	CompanyBusinessTypeService companyBusinessTypeService;
	
	/**
	 * 新增
	 * @param physicalRecord
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value="保存员工体检信息")
	@Transactional(readOnly=false)
	public DataResponse<PilotLicenseCheckRecord> save(@RequestBody PhysicalRecord physicalRecord,HttpServletRequest request) {
		DataResponse<PilotLicenseCheckRecord> response = new DataResponse<PilotLicenseCheckRecord>();
		
		if (physicalRecord.createTime == null) {
			physicalRecord.createTime = new Date();
		} else {
			physicalRecord.updateTime = new Date();
		}
		try {
			physicalRecordService.save(physicalRecord,request);
			response.setResultCode(Constants.OK);
		} catch (Exception e) {
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
	/**
	 * 根据ID删除
	 * @param id
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "删除当前员工体检信息")
	@ApiIgnore
	public void deleteById(@PathVariable("id") Long id) {
		physicalRecordService.delete(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@RequestMapping(value = "/batchDelete/{ids}", method = RequestMethod.DELETE)
	@ApiOperation(value = "批量删除员工体检信息")
	public void batchDelete(@PathVariable("ids") List<Long> ids) {
		List<PhysicalRecord> recordList = new ArrayList<PhysicalRecord>();
		for (Long id : ids) {
			PhysicalRecord record = new PhysicalRecord();
			record.setId(id);
			recordList.add(record);
		}
		physicalRecordService.batchDelete(recordList);;
	}
	
	/**
	 * 根据人员ID分页查询体检记录
	 * 
	 * @param employeeId
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/serach/getbyemployeeid",method = RequestMethod.GET)
	@ApiOperation(value="根据员工ID分页获取获取某员工体检列表")
	@ApiImplicitParams({@ApiImplicitParam(value="员工ID",name="employeeId",dataType="String",paramType="query"),
		@ApiImplicitParam(value = "页面大小", name = "pageSize", dataType = "int", paramType = "query"),
		@ApiImplicitParam(value = "当前页", name = "page", dataType = "int", paramType = "query")
		})
	public Page<PhysicalRecord> getByEmpId(@RequestParam(value="employeeId",required=false) String employeeId, @RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {	
		if(employeeId.equals("")||employeeId == null) {
			return null;
		}
		Pageable pageable = new PageRequest(page, pageSize);
		return physicalRecordService.findByEmployeeId(Integer.parseInt(employeeId), pageable);
	}
	
	/*
	 * 不分页
	 */
	@RequestMapping(value="/serach/getbyemployeeids",method = RequestMethod.GET)
	@ApiOperation(value="获取某员工体检列表")
	@ApiImplicitParam(value="员工ID",name="employeeId",dataType="String",paramType="query")
	public List<PhysicalRecord> getByEmpId(@RequestParam("employeeId") String employeeId)
	{	
		if(employeeId.equals("")||employeeId == null){
			return null;
		}
		return physicalRecordService.findByEmployeeId(Integer.parseInt(employeeId));
	}
	
	
	
	
	
	/**
	 * 根据ID返回体检记录
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/serach/getbyid",method = RequestMethod.GET)
	@ApiOperation(value="获取某体检记录详细信息")
	@ApiImplicitParam(value="体检信息ID",name="id",dataType="String")
	public PhysicalRecord getById(@RequestParam("id") String id) {	
		return physicalRecordService.findById(Long.parseLong(id));
	}
	
	
	
	
}
