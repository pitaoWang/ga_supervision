package com.atosenet.ga.company.mgt.controller.ga.company;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atosenet.ga.company.db.model.ga.company.MmisBaseEngineHistory;
import com.atosenet.ga.company.mgt.service.ga.company.MmisBaseEngineHistoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@CrossOrigin
@RequestMapping("/eim/api/mmisenginehis")
@Api(tags = "发动机拆装记录")
@ApiIgnore
public class MmisBaseEngineHistoryController {
	
	@Autowired
	MmisBaseEngineHistoryService service;
	
	@RequestMapping(value = "/findbyengineId",method = RequestMethod.GET)
	@ApiOperation(value = "根据发动机id查询拆装记录")
	public List<MmisBaseEngineHistory> findByEngineId(@RequestParam("engineId") String engineId){
		return service.findByEngineId(engineId);
	}
}
