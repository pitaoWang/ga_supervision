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

import com.atosenet.ga.company.db.model.ga.mmis.Evaluate;
import com.atosenet.ga.company.mgt.service.ga.mmis.EvaluateService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
@RequestMapping("/eim/api/mmis")
@Api(tags="航空器监控监管")
public class EvaluateController {
	
	@Autowired
	private EvaluateService evaluateService;
	
	/**
	 * 分页获取当前管理局下的适航指令/服务通告评估记录
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getEvaluateListByPage",method = RequestMethod.GET)
	@ApiOperation(value="根据条件适航指令/服务通告评估记录")
	@ApiImplicitParams({@ApiImplicitParam(value="所有企业ID, 以逗号分隔", name="companyIds", required=true, dataType="String",paramType="query"),
		@ApiImplicitParam(value="编号",name="no",dataType="String",paramType="query"),
		@ApiImplicitParam(value="适航资料编号",name="asInformNo",dataType="String",paramType="query"),
		@ApiImplicitParam(value="执行方式",name="executeWay",dataType="String",paramType="query"),
		@ApiImplicitParam(value="处理状态",name="status",dataType="String",paramType="query"),
		@ApiImplicitParam(value = "页面大小", name = "pageSize", dataType = "int", required=true, paramType = "query"),
		@ApiImplicitParam(value = "当前页  从1开始", name = "page", dataType = "int", required=true, paramType = "query")})
	public Page<Evaluate> getEvaluateListByPage(@RequestParam(value="companyIds", required=true) String companyIds,
			@RequestParam(value="no", required=false) String no, 
			@RequestParam(value="asInformNo", required=false) String asInformNo, 
			@RequestParam(value="executeWay", required=false) String executeWay, 
			@RequestParam(value="status", required=false) String status, 
			@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {	
		Pageable pageable = new PageRequest(page-1, pageSize);
		return evaluateService.findEvaluateListByPage(no, asInformNo, executeWay, status, companyIds, pageable);
	}
	
	/**
	 * 根据ID获取记录详情
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getEvaluateById",method = RequestMethod.GET)
	@ApiOperation(value="根据ID获取适航指令/服务通告评估记录详细信息")
	@ApiImplicitParams({@ApiImplicitParam(value="记录ID", name="id", required=true, dataType="String",paramType="query")})
	public Evaluate getEvaluateById(@RequestParam(value="id", required=true) String id) {
		return evaluateService.findById(id);
	}
	
}
