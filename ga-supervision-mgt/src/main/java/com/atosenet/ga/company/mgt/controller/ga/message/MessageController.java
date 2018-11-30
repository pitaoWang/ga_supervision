package com.atosenet.ga.company.mgt.controller.ga.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atosenet.ga.company.db.model.ga.company.CompanyQualification;
import com.atosenet.ga.company.db.model.ga.message.vo.MessageDto;
import com.atosenet.ga.company.db.model.ga.message.vo.MessageVO;
import com.atosenet.ga.company.mgt.service.ga.message.MessageService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 执照信息到期提醒
 * 
 * @author Administrator
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/eim/api/message")
@Api(tags="执照信息到期提醒，过期前一周")
public class MessageController {

	@Autowired
	private MessageService messageService;
	
	@RequestMapping(value="/getListByCompanyIds",method = RequestMethod.GET)
	@ApiOperation(value="根据条件查询飞行记录")
	@ApiImplicitParams({
		@ApiImplicitParam(value="公司ID, 以逗号隔开",name="companyIds",dataType="String",paramType="query"),
		@ApiImplicitParam(value = "页面大小", name = "pageSize", dataType = "int", required=true, paramType = "query"),
		@ApiImplicitParam(value = "当前页  从1开始", name = "page", dataType = "int", required=true, paramType = "query")})
	public Page<MessageVO> getListByCompanyIds(@RequestParam(value="companyIds", required=false) String companyIds, 
			@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
		Pageable pageable = new PageRequest(page-1, pageSize);
		return messageService.findListByCompanyIds(companyIds, pageable);
	}
	
	/**
	 * 查询个人资质到期时间
	 * @param companyIds
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/getListByCompanyIds1",method = RequestMethod.GET)
	@ApiOperation(value="根据条件查询飞行记录")
	@ApiImplicitParams({
		@ApiImplicitParam(value="公司ID, 以逗号隔开",name="companyIds",dataType="String",paramType="query"),
		@ApiImplicitParam(value = "页面大小", name = "pageSize", dataType = "int", required=true, paramType = "query"),
		@ApiImplicitParam(value = "当前页  从1开始", name = "page", dataType = "int", required=true, paramType = "query")})
	public Page<MessageDto> getListByCompanyIds1(@RequestParam(value="companyIds", required=false) String companyIds, 
			@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
		Pageable pageable = new PageRequest(page-1, pageSize);
		return messageService.findListByCompanyIds1(companyIds, pageable);
	}
	
	@RequestMapping(value="/findRecordByCompanyId",method = RequestMethod.GET)
	@ApiOperation(value="企业资质消息提醒")
	@ApiImplicitParams({
		@ApiImplicitParam(value="公司ID, 以逗号隔开",name="companyIds",dataType="String",paramType="query"),
		@ApiImplicitParam(value = "页面大小", name = "pageSize", dataType = "int", required=true, paramType = "query"),
		@ApiImplicitParam(value = "当前页  从1开始", name = "page", dataType = "int", required=true, paramType = "query")})
	public Page<CompanyQualification> findRecordByCompanyId(@RequestParam(value="companyIds", required=false) String companyIds, 
			@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
		Pageable pageable = new PageRequest(page-1, pageSize);
		return messageService.findRecordByCompanyId(companyIds, pageable);
	}
}
