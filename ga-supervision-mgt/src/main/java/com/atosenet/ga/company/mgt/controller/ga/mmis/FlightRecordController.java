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

import com.atosenet.ga.company.db.model.ga.mmis.ADRecord;
import com.atosenet.ga.company.db.model.ga.mmis.FaultRecord;
import com.atosenet.ga.company.db.model.ga.mmis.FlightRecord;
import com.atosenet.ga.company.db.model.ga.mmis.ReplaceRecord;
import com.atosenet.ga.company.mgt.service.ga.mmis.FlightRecordService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 飞行记录控制类
 * @author Administrator
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/eim/api/mmis")
@Api(tags="航空器监控监管")
public class FlightRecordController {
	
	@Autowired
	private FlightRecordService flightRecordService;
	
	/**
	 * 分页获取当前管理局下的适航指令/服务通告评估记录
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getFlightRecordListByPage",method = RequestMethod.GET)
	@ApiOperation(value="根据条件查询飞行记录")
	@ApiImplicitParams({
		@ApiImplicitParam(value="飞行记录本编号",name="no",dataType="String",paramType="query"),
		@ApiImplicitParam(value="航空器型号",name="asInformNo",dataType="String",paramType="query"),
		@ApiImplicitParam(value="航空器注册号",name="aircraftRegNo",dataType="String",paramType="query"),
		@ApiImplicitParam(value="放行人",name="releaser",dataType="String",paramType="query"),
		@ApiImplicitParam(value="放行时间",name="releaseTime",dataType="String",paramType="query"),
		@ApiImplicitParam(value = "页面大小", name = "pageSize", dataType = "int", required=true, paramType = "query"),
		@ApiImplicitParam(value = "当前页  从1开始", name = "page", dataType = "int", required=true, paramType = "query")})
	
	public Page<FlightRecord> getFlightRecordListByPage(
			@RequestParam(value="no", required=false) String no, 
			@RequestParam(value="aircraftModel", required=false) String aircraftModel, 
			@RequestParam(value="aircraftRegNo", required=false) String aircraftRegNo, 
			@RequestParam(value="releaser", required=false) String releaser, 
			@RequestParam(value="releaseTime", required=false) String releaseTime, 
			@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {	
			Pageable pageable = new PageRequest(page-1, pageSize);
		
		return flightRecordService.findFlightRecordByPage(no, aircraftModel, aircraftRegNo, releaser, releaseTime, pageable);
		
	}
	
	/**
	 * 根据ID获取记录详情
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getFlightRecordById",method = RequestMethod.GET)
	@ApiOperation(value="根据ID飞行记录详细信息")
	@ApiImplicitParams({@ApiImplicitParam(value="记录ID", name="id", required=true, dataType="String",paramType="query")})
	public FlightRecord getFlightRecordById(@RequestParam(value="id", required=true) String id) {
		return flightRecordService.findByFlightRecordId(id);
	}
	
	/**
	 * 根据飞行记录ID获取排故记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getFaultRecordByParentId",method = RequestMethod.GET)
	@ApiOperation(value="根据飞行记录ID获取排故记录")
	@ApiImplicitParams({@ApiImplicitParam(value="飞行记录ID", name="parentId", required=true, dataType="String",paramType="query"),
		@ApiImplicitParam(value = "页面大小", name = "pageSize", dataType = "int", required=true, paramType = "query"),
		@ApiImplicitParam(value = "当前页  从1开始", name = "page", dataType = "int", required=true, paramType = "query")})
	public Page<FaultRecord> getFaultRecordByParentId(@RequestParam(value="parentId", required=true) String parentId, @RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
		Pageable pageable = new PageRequest(page-1, pageSize);
		return flightRecordService.findFaultRecordByPage(parentId, pageable);
	}
	
	/**
	 * 根据飞行记录ID获取换件记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getReplaceRecordByParentId",method = RequestMethod.GET)
	@ApiOperation(value="根据飞行记录ID获取换件记录")
	@ApiImplicitParams({@ApiImplicitParam(value="飞行记录ID", name="parentId", required=true, dataType="String",paramType="query"),
		@ApiImplicitParam(value = "页面大小", name = "pageSize", dataType = "int", required=true, paramType = "query"),
		@ApiImplicitParam(value = "当前页  从1开始", name = "page", dataType = "int", required=true, paramType = "query")})
	public Page<ReplaceRecord> getReplaceRecordByParentId(@RequestParam(value="parentId", required=true) String parentId, @RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
		Pageable pageable = new PageRequest(page-1, pageSize);
		return flightRecordService.findReplaceRecordByPage(parentId, pageable);
	}
	
	/**
	 * 根据飞行记录ID获取AD/SB执行记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getADRecordByParentId",method = RequestMethod.GET)
	@ApiOperation(value="根据飞行记录ID获取AD/SB执行记录")
	@ApiImplicitParams({@ApiImplicitParam(value="飞行记录ID", name="parentId", required=true, dataType="String",paramType="query"),
		@ApiImplicitParam(value = "页面大小", name = "pageSize", dataType = "int", required=true, paramType = "query"),
		@ApiImplicitParam(value = "当前页  从1开始", name = "page", dataType = "int", required=true, paramType = "query")})
	public Page<ADRecord> getADRecordByParentId(@RequestParam(value="parentId", required=true) String parentId, @RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
		Pageable pageable = new PageRequest(page-1, pageSize);
		return flightRecordService.findADRecordByPage(parentId, pageable);
	}
	
	
}
