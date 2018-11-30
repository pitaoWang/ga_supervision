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

import com.atosenet.ga.company.db.model.ga.mmis.FaultReserve;
import com.atosenet.ga.company.mgt.service.ga.mmis.FaultReserveService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
@RequestMapping("/eim/api/mmis")
@Api(tags="航空器监控监管")
public class FaultReserveController {
	
	@Autowired
	private FaultReserveService faultReserveService;
	
	/**
	 * 
	 * @param regNumber
	 * @param no
	 * @param aircraftModel
	 * @param reserveDate
	 * @param status
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/getFaultListByPage",method = RequestMethod.GET)
	@ApiOperation(value="根据条件查询故障保留单")
	@ApiImplicitParams({@ApiImplicitParam(value="航空器注册号", name="regNumber", required=true, dataType="String",paramType="query"),
		@ApiImplicitParam(value="编号",name="no",dataType="String",paramType="query"),
		@ApiImplicitParam(value="航空器型号",name="aircraftModel",dataType="String",paramType="query"),
		@ApiImplicitParam(value="保留期限",name="reserveDate",dataType="String",paramType="query"),
		@ApiImplicitParam(value="处理状态",name="status",dataType="String",paramType="query"),
		@ApiImplicitParam(value = "页面大小", name = "pageSize", dataType = "int", required=true, paramType = "query"),
		@ApiImplicitParam(value = "当前页  从1开始", name = "page", dataType = "int", required=true, paramType = "query")})
	public Page<FaultReserve> getFaultListByPage(@RequestParam(value="regNumber", required=true) String regNumber,
			@RequestParam(value="no", required=false) String no, 
			@RequestParam(value="aircraftModel", required=false) String aircraftModel, 
			@RequestParam(value="reserveDate", required=false) String reserveDate, 
			@RequestParam(value="status", required=false) String status, 
			@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {	
		Pageable pageable = new PageRequest(page-1, pageSize);
		return faultReserveService.findListByPage(no, aircraftModel, reserveDate, status, regNumber, pageable);
	}
	
	/**
	 * 根据ID获取工作保留单详细信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getFaultReserveById",method = RequestMethod.GET)
	@ApiOperation(value="根据ID获取故障保留单详细信息")
	@ApiImplicitParams({@ApiImplicitParam(value="故障保留单ID",name="id", required=true, dataType="String",paramType="query")})
	public FaultReserve getFaultReserveById(@RequestParam(value="id", required=true) String id) {	
		return faultReserveService.findById(id);
	} 
}
