package com.atosenet.ga.company.mgt.controller.ga.employe;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atosenet.ga.company.db.model.ga.employe.TrainingCertificate;
import com.atosenet.ga.company.mgt.service.ga.employe.TrainingCertificateService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@CrossOrigin
@RequestMapping("/eim/api/trainingcertificate")
@Api(tags="员工培训证书信息记录")
@ApiIgnore
public class TrainingCertificateController {
	
	@Autowired
	TrainingCertificateService trainingRecordService;
	@RequestMapping(value="/savaTrainingCertificate",method = RequestMethod.POST)
	@ApiOperation(value="保存培训证书信息")
	@Transactional(readOnly=false)
	public void savaTrainingCertificate(@RequestBody TrainingCertificate trainingCertificate,HttpServletRequest request)
	{
		trainingRecordService.sava(trainingCertificate,request);
	}
	@RequestMapping(value = "/deleteTrainingCertificateById/{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "根据id删除当前员工证书信息")
	@ApiImplicitParams({
		@ApiImplicitParam(value="培训证书信息的id",name="id",dataType="String")	
	})
	public void deleteTrainingCertificateById(@PathVariable("id") String id) {
		trainingRecordService.delete(Long.parseLong(id));
	}
	
	@RequestMapping(value="/serach/getbyemployeeId",method = RequestMethod.GET)
	@ApiOperation(value="根据员工id（employee）获取员工下的所有证书详细信息")
	@ApiImplicitParams({
		@ApiImplicitParam(value="员工employee表下的id",name="employeeId",dataType="String")	
	})
	public List<TrainingCertificate> getById(@RequestParam("employeeId") String employeeId)
	{	
		if(employeeId.equals("") || employeeId == null){
			return null;
		}
		return trainingRecordService.findByEmployeeId(Integer.parseInt(employeeId));
	}
	
	@RequestMapping(value="/serach/getTrainingRecordById",method = RequestMethod.GET)
	@ApiOperation(value="根据培训id获取某员工证书详细信息")
	@ApiImplicitParams({
		@ApiImplicitParam(value="培训证书下的id",name="id",dataType="String")	
	})
	public TrainingCertificate getTrainingCertificateById(@RequestParam("id") String id)
	{	
		return trainingRecordService.getTrainingCertificateRecordById(Long.parseLong(id));
	}
}
