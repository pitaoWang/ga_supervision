package com.atosenet.ga.company.mgt.controller.ga.mmis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atosenet.ga.company.db.model.ga.mmis.BaseEngineHistory;
import com.atosenet.ga.company.mgt.service.ga.mmis.BaseEngineHistoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
@RequestMapping("/eim/api/engine")
@Api(tags="发动机")
public class BaseEngineHistoryController {
	
	@Autowired
	private BaseEngineHistoryService baseEngineHistoryService;
	
	/**
	 * 根据航空器注册号查询发动机信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getHistoryListByEngineId",method = RequestMethod.GET)
	@ApiOperation(value="根据发动机ID查询拆装记录")
	@ApiImplicitParams({@ApiImplicitParam(value="发动机ID", name="engineId", dataType="String", required=true, paramType="query")})
	public List<BaseEngineHistory> getHistoryListByEngineId(@RequestParam(value="engineId", required=true) String engineId) {	
		return baseEngineHistoryService.findHistoryListByEngineId(engineId);
	}
}
