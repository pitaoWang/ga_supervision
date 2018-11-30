package com.atosenet.ga.company.mgt.controller.ga.employe;

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

import com.atosenet.ga.company.core.common.model.response.BaseResponse;
import com.atosenet.ga.company.db.model.ga.employe.TrainningRecord;
import com.atosenet.ga.company.mgt.config.Constants;
import com.atosenet.ga.company.mgt.service.ga.company.CompanyBusinessTypeService;
import com.atosenet.ga.company.mgt.service.ga.employe.TrainningRecordService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@CrossOrigin
@RequestMapping("/eim/api/training")
@Api(tags="员工培训信息记录")
public class TrainingController {
	@Autowired
	TrainningRecordService trainningRecordService;
	@Autowired
	CompanyBusinessTypeService companyBusinessTypeService;
	
	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value="保存员工体检信息")
	@Transactional(readOnly=false)
	public void saveTrainingRecord(@RequestBody TrainningRecord trainingRecord,HttpServletRequest request)
	{
		trainningRecordService.sava(trainingRecord,request);
	}
	
	@RequestMapping(value = "/deleteTrainingRecordById/{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "根据id来删除当前员工培训信息")
	@ApiImplicitParams({
		@ApiImplicitParam(value="培训信息的id",name="id",dataType="String")	
	})
	@ApiIgnore
	public void deleteTrainingRecordById(@PathVariable("id") String id) {
		trainningRecordService.delete(Long.parseLong(id));
	}
	
	@RequestMapping(value = "/deleteBatch/{ids}", method = RequestMethod.DELETE)
	@ApiOperation(value = "批量删除培训记录")
	public BaseResponse deleteBatch(@PathVariable("ids") List<Long> ids){
		BaseResponse response = new BaseResponse();
		try {
			this.trainningRecordService.deleteBatch(ids);
			response.setResultCode(Constants.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setMessage(e.getMessage());
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	
	
	
	@RequestMapping(value="/serach/getbyemployeeId",method = RequestMethod.GET)
	@ApiOperation(value="根据员工id来获取某员工培训详细信息")
	@ApiImplicitParams({
		@ApiImplicitParam(value="员工表employee中的id",name="employeeId",dataType="String",paramType="query")	
	})
	public List<TrainningRecord> getById(@RequestParam("employeeId") String employeeId) {	
		if(employeeId.equals("") || employeeId == null) {
			return null;
		}
		return trainningRecordService.findByEmployeeId(Integer.parseInt(employeeId));
	}
	
	@RequestMapping(value="/serach/getPagebyemployeeId",method = RequestMethod.GET)
	@ApiOperation(value="根据员工id分页查询员工培训详细信息")
	@ApiImplicitParams({
		@ApiImplicitParam(value="员工表employee中的id",name="employeeId",dataType="String",paramType="query"),
		@ApiImplicitParam(value = "页面大小", name = "pageSize", dataType = "int", paramType = "query"),
		@ApiImplicitParam(value = "当前页", name = "page", dataType = "int", paramType = "query")
	})
	public Page<TrainningRecord> getPageById(@RequestParam("employeeId") String employeeId,@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {	
		if(employeeId.equals("") || employeeId == null) {
			return null;
		}
		Pageable pageable = new PageRequest(page, pageSize);
		return trainningRecordService.findPageByEmployeeId(Long.parseLong(employeeId),pageable);
	}
	
	
	
	@RequestMapping(value="/serach/getTrainingRecordById",method = RequestMethod.GET)
	@ApiOperation(value="根据培训id来获取培训详细信息")
	@ApiImplicitParams({
		@ApiImplicitParam(value="培训信息的id",name="id",dataType="String")	
	})
	public TrainningRecord getTrainingRecordById(@RequestParam("id") String id)
	{	
		return trainningRecordService.getTrainingRecordById(Long.parseLong(id));
	}
}
