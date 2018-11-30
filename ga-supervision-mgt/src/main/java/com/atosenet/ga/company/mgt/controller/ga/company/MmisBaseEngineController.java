package com.atosenet.ga.company.mgt.controller.ga.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atosenet.ga.company.db.model.ga.company.MmisBaseEngine;
import com.atosenet.ga.company.mgt.service.ga.company.MmisBaseEngineService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@CrossOrigin
@RequestMapping("/eim/api/mmisengine")
@Api(tags = "发动机监管信息")
@ApiIgnore
public class MmisBaseEngineController {
	
	@Autowired
	MmisBaseEngineService mmisBaseEngineService;
	
	@RequestMapping(value = "/findbycompanyId",method = RequestMethod.GET)
	@ApiOperation(value = "分页查询发动机监管信息")
	public Page<MmisBaseEngine> findByCompanyId(@RequestParam("pageno")String pageno,@RequestParam("pagesize")String pagesize,
			@RequestParam("companyIds")String companyIds,@RequestParam("model")String model,@RequestParam("aircraftRegNo")String aircraftRegNo,@RequestParam("equipmentStatus")String equipmentStatus){
		Pageable pageable = new PageRequest(Integer.parseInt(pageno),Integer.parseInt(pagesize));
		Page<MmisBaseEngine> pm = null;
		String[] companyids = companyIds.split(",");
		if((model==""||model.equals(""))&&(aircraftRegNo==""||aircraftRegNo.equals(""))&&(equipmentStatus==""||equipmentStatus.equals("")))
		    pm = mmisBaseEngineService.findByCompanyId(companyids, pageable);
		
		if((model!=""||!model.equals(""))&&(aircraftRegNo==""||aircraftRegNo.equals(""))&&(equipmentStatus==""||equipmentStatus.equals("")))
			pm = mmisBaseEngineService.findByCompanyIdAndModel(companyids, model, pageable);
		
		if((model==""||model.equals(""))&&(aircraftRegNo!=""||!aircraftRegNo.equals(""))&&(equipmentStatus==""||equipmentStatus.equals("")))
			pm = mmisBaseEngineService.findByCompanyIdAndAircraftRegNo(companyids, aircraftRegNo, pageable);
		
		if((model==""||model.equals(""))&&(aircraftRegNo==""||aircraftRegNo.equals(""))&&(equipmentStatus!=""||!equipmentStatus.equals("")))
		    pm = mmisBaseEngineService.findByCompanyIdAndEquipmentStatus(companyids, equipmentStatus, pageable);
		
		if((model!=""||!model.equals(""))&&(aircraftRegNo!=""||!aircraftRegNo.equals(""))&&(equipmentStatus==""||equipmentStatus.equals("")))
			pm = mmisBaseEngineService.findByModelAndAircraftRegNo(companyids, model, aircraftRegNo, pageable);
		
		if((model!=""||!model.equals(""))&&(aircraftRegNo==""||aircraftRegNo.equals(""))&&(equipmentStatus!=""||!equipmentStatus.equals("")))
			pm = mmisBaseEngineService.findByModelAndEquipmentStatus(companyids, model, equipmentStatus, pageable);
		
		if((model==""||model.equals(""))&&(aircraftRegNo!=""||!aircraftRegNo.equals(""))&&(equipmentStatus!=""||!equipmentStatus.equals("")))
			pm = mmisBaseEngineService.findByRegNoANdEquipStatus(companyids, aircraftRegNo, equipmentStatus, pageable);
		
		if((model!=""||!model.equals(""))&&(aircraftRegNo!=""||!aircraftRegNo.equals(""))&&(equipmentStatus!=""||!equipmentStatus.equals("")))
			pm = mmisBaseEngineService.findAll(companyids, model, aircraftRegNo, equipmentStatus, pageable);
			
		return pm;
	}
}
