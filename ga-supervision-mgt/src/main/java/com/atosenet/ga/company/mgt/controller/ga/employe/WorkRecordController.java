/**  
 * 
 * @Title: WorkRecord.java 
 * @Package com.atosenet.ga.company.mgt.controller.ga.employe  
 * @author xyx   
 * @date 2018年7月31日 下午5:10:57 
 * @version V1.0
 *
 */
package com.atosenet.ga.company.mgt.controller.ga.employe;


import java.util.List;

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
import com.atosenet.ga.company.db.model.ga.employe.WorkRecord;
import com.atosenet.ga.company.mgt.config.Constants;
import com.atosenet.ga.company.mgt.service.ga.employe.WorkRecordService;
import com.atosenet.ga.company.mgt.service.ga.employe.response.DataResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/** 
 * TODO(员工工作记录)
 * 
 * @ClassName: WorkRecord 
 * @author xyx 
 * @date 2018年7月31日 下午5:10:57 
 *  
 */
@RestController
@CrossOrigin
@RequestMapping("/eim/api/workrecord")
@Api(tags="员工工作记录")
public class WorkRecordController {
	@Autowired
	WorkRecordService workRecordService;
	
	@RequestMapping(value="/serach/getbyemployeeId",method = RequestMethod.GET)
	@ApiOperation(value="根据员工id来获取某员工工作记录")
	@ApiImplicitParams({
		@ApiImplicitParam(value="员工employee表下的id",name="employeeId",dataType="String")	
	})
	public List<WorkRecord> getbyemployeeId(@RequestParam("employeeId") String employeeId){	
		if(employeeId.equals("") || employeeId == null){
			return null;
		}
		
		return workRecordService.findByEmployeeId(Integer.parseInt(employeeId));
	}
	
	@RequestMapping(value="/serach/getPagebyemployeeId",method = RequestMethod.GET)
	@ApiOperation(value="根据员工id来获取某员工工作记录")
	@ApiImplicitParams({
		@ApiImplicitParam(value="员工employee表下的id",name="employeeId",dataType="String")	
	})
	public Page<WorkRecord> getPagebyemployeeId(@RequestParam("employeeId") String employeeId,@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize){	
		if(employeeId.equals("") || employeeId == null){
			return null;
		}
		
		Pageable pageable = new PageRequest(page, pageSize);
		return workRecordService.getPage(Long.parseLong(employeeId),pageable);
		
	}
	
	
	
	
	
	
	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value="保存/修改工作记录")
	@ApiImplicitParams({
		@ApiImplicitParam(value="工作记录实体类",name="workRecord",dataType="body")	
	})
	public DataResponse<WorkRecord> save(@RequestBody WorkRecord workRecord,HttpServletRequest request)
	{
		DataResponse<WorkRecord> response = new DataResponse<WorkRecord>();
		
		
		try 
		{
			workRecordService.save(workRecord,request);
			response.setResultCode(Constants.OK);
			
		} catch (Exception e) {
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		}
		return response;	
	}
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "删除工作记录")
	public void deleteById(@PathVariable("id") Long id) {
		workRecordService.delete(id);
	}
	
	@RequestMapping(value = "deleteBatch/{ids}", method = RequestMethod.DELETE)
	@ApiOperation(value = "批量删除工作记录")
	public BaseResponse deleteBatch(@PathVariable("ids") List<Long> ids){
		BaseResponse response = new BaseResponse();
		try {
			this.workRecordService.deleteBatch(ids);
			response.setResultCode(Constants.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setMessage(e.getMessage());
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	
	@RequestMapping(value="/serach/getbyId",method = RequestMethod.GET)
	@ApiOperation(value="根据ID查询工作经历信息")
	@ApiImplicitParam(value="工作记录ID",name="id",dataType="String")
	public WorkRecord getById(@RequestParam("id") String id)
	{	
		return workRecordService.findById(Long.parseLong(id));
	}
}
