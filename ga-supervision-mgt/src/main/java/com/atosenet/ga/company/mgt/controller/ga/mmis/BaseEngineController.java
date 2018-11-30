package com.atosenet.ga.company.mgt.controller.ga.mmis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atosenet.ga.company.db.model.ga.mmis.BaseEngine;
import com.atosenet.ga.company.mgt.service.ga.mmis.BaseEngineService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
@RequestMapping("/eim/api/engine")
@Api(tags="发动机")
public class BaseEngineController {
	
	@Autowired
	private BaseEngineService baseEngineService;
	
	/**
	 * 根据航空器注册号查询发动机信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getEngineListByRegNumber",method = RequestMethod.GET)
	@ApiOperation(value="根据航空器注册号查询发动机信息")
	@ApiImplicitParams({@ApiImplicitParam(value="航空器注册号",name="regNumber", required=true, dataType="String",paramType="query")})
	public List<BaseEngine> getEngineListByRegNumber(@RequestParam(value="regNumber", required=true) String regNumber) {	
		return baseEngineService.findeListByRegNumber(regNumber);
	}
}
