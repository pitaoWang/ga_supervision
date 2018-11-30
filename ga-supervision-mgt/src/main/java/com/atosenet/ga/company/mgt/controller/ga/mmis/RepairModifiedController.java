package com.atosenet.ga.company.mgt.controller.ga.mmis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atosenet.ga.company.db.model.ga.mmis.RepairModified;
import com.atosenet.ga.company.mgt.service.ga.mmis.RepairModifiedService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
@RequestMapping("/eim/api/mmis")
@Api(tags="航空器监控监管")
public class RepairModifiedController {
	
	@Autowired
	private RepairModifiedService repairModifiedService;
	
	/**
	 * 
	 * @param regNumber
	 * @param no
	 * @param aircraftModel
	 * @param type
	 * @param project
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/getRepairListByPage",method = RequestMethod.GET)
	@ApiOperation(value="根据条件查询重要修理及改装记录")
	@ApiImplicitParams({@ApiImplicitParam(value="航空器注册号",name="regNumber", required=true, dataType="String",paramType="query"),
		@ApiImplicitParam(value="编号",name="no",dataType="String",paramType="query"),
		@ApiImplicitParam(value="航空器型号",name="aircraftModel",dataType="String",paramType="query"),
		@ApiImplicitParam(value="类型",name="type",dataType="String",paramType="query"),
		@ApiImplicitParam(value="项目",name="project",dataType="String",paramType="query"),
		@ApiImplicitParam(value = "页面大小", name = "pageSize", required=true, dataType = "int", paramType = "query"),
		@ApiImplicitParam(value = "当前页 从1开始", name = "page", dataType = "int", required=true, paramType = "query")})
	public Page<RepairModified> getRepairListByPage(@RequestParam(value="regNumber", required=true) String regNumber,
			@RequestParam(value="no", required=false) String no, 
			@RequestParam(value="aircraftModel", required=false) String aircraftModel, 
			@RequestParam(value="type", required=false) String type, 
			@RequestParam(value="project", required=false) String project, 
			@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {	
		Pageable pageable = new PageRequest(page-1, pageSize);
		return repairModifiedService.findListByPage(no, aircraftModel, type, project, regNumber, pageable);
	}
	
	
	/**
	 * 根据ID获取工作保留单详细信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getRepairModifiedById",method = RequestMethod.GET)
	@ApiOperation(value="根据ID获取重要修理及改装详细信息")
	@ApiImplicitParams({@ApiImplicitParam(value="重要修理及改装记录ID",name="id", dataType="String", required=true, paramType="query")})
	public RepairModified getRepairModifiedById(@RequestParam(value="id", required=true) String id) {	
		return repairModifiedService.findById(id);
	} 
}
