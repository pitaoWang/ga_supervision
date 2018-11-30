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

import com.atosenet.ga.company.db.model.ga.mmis.WorkReserve;
import com.atosenet.ga.company.mgt.service.ga.mmis.WorkReserveService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author Administrator
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/eim/api/mmis")
@Api(tags="航空器监控监管")
public class WorkReserveController {
	
	@Autowired
	private WorkReserveService workReserveService;
	
	/**
	 * 
	 * @param regNumber
	 * @param no
	 * @param aircraftModel
	 * @param wocNo
	 * @param status
	 * @param delayDate
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/getWorkListByPage",method = RequestMethod.GET)
	@ApiOperation(value="根据条件查询工作保留单")
	@ApiImplicitParams({@ApiImplicitParam(value="航空器注册号",name="regNumber", required=true, dataType="String",paramType="query"),
		@ApiImplicitParam(value="编号",name="no",dataType="String",paramType="query"),
		@ApiImplicitParam(value="航空器型号",name="aircraftModel",dataType="String",paramType="query"),
		@ApiImplicitParam(value="工作单卡编号",name="wocNo",dataType="String",paramType="query"),
		@ApiImplicitParam(value="推迟期限",name="delayDate",dataType="String",paramType="query"),
		@ApiImplicitParam(value="处理状态",name="status",dataType="String",paramType="query"),
		@ApiImplicitParam(value = "页面大小", name = "pageSize", required=true, dataType = "int", paramType = "query"),
		@ApiImplicitParam(value = "当前页, 从1开始", name = "page", dataType = "int", required=true, paramType = "query")})
	public Page<WorkReserve> getWorkListByPage(@RequestParam(value="regNumber", required=true) String regNumber,
			@RequestParam(value="no", required=false) String no, 
			@RequestParam(value="aircraftModel", required=false) String aircraftModel, 
			@RequestParam(value="wocNo", required=false) String wocNo, 
			@RequestParam(value="delayDate", required=false) String delayDate,
			@RequestParam(value="status", required=false) String status, 
			@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {	
		Pageable pageable = new PageRequest(page-1, pageSize);
		return workReserveService.findListByPage(no, aircraftModel, wocNo, delayDate, status, regNumber, pageable);
	}
	
	/**
	 * 根据ID获取工作保留单详细信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getWorkReserveById",method = RequestMethod.GET)
	@ApiOperation(value="根据ID获取工作保留单详细信息")
	@ApiImplicitParams({@ApiImplicitParam(value="航空器注册号",name="id", required=true, dataType="String",paramType="query")})
	public WorkReserve getWorkReserveById(@RequestParam(value="id", required=true) String id) {	
		return workReserveService.findWorkReserveById(id);
	} 
}
