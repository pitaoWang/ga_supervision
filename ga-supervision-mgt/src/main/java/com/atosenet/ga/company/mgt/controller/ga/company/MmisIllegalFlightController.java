package com.atosenet.ga.company.mgt.controller.ga.company;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atosenet.ga.company.db.model.ga.company.MmisIllegalFlight;
import com.atosenet.ga.company.mgt.service.ga.company.MmisIllegalFlightService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@CrossOrigin
@RequestMapping("/eim/api/mmisillegal")
@Api(tags = "违规飞行记录")
@ApiIgnore
public class MmisIllegalFlightController {
	
	@Autowired
	MmisIllegalFlightService mmisIllegalFlightService;
	
	@RequestMapping(value = "/findbycompanyId" , method = RequestMethod.GET)
	@ApiOperation(value = "根据企业ids查询违规记录信息")
	public List<MmisIllegalFlight> findByCompanyId(@RequestParam("companyIds")String companyIds,
			@RequestParam("companyId")String companyId,@RequestParam("aircraftRegNo")String aircraftRegNo,
			@RequestParam("starttime")String starttime,@RequestParam("endtime")String endtime
			){
		String[] companyids = companyIds.split(",");
		List<MmisIllegalFlight> illegalList = null;
		if((companyId==""||companyId.equals(""))&&(aircraftRegNo==""||aircraftRegNo.equals(""))&&(starttime==""||starttime.equals(""))&&(endtime==""||endtime.equals("")))
		   illegalList = mmisIllegalFlightService.findByCompanyId(companyids);
		
		if((companyId!=""||!companyId.equals(""))&&(aircraftRegNo==""||aircraftRegNo.equals(""))&&(starttime==""||starttime.equals(""))&&(endtime==""||endtime.equals("")))
			illegalList = mmisIllegalFlightService.findByCompanyIdsAndId(companyids, Long.parseLong(companyId));
		
		if((companyId==""||companyId.equals(""))&&(aircraftRegNo!=""||!aircraftRegNo.equals(""))&&(starttime==""||starttime.equals(""))&&(endtime==""||endtime.equals("")))
			illegalList = mmisIllegalFlightService.findByCompanyIdsAndAirRegNo(companyids, aircraftRegNo);
		
		if((companyId==""||companyId.equals(""))&&(aircraftRegNo==""||aircraftRegNo.equals(""))&&(starttime!=""||!starttime.equals(""))&&(endtime!=""||!endtime.equals("")))
			illegalList = mmisIllegalFlightService.findByCompanyIdsAndDate(companyids, starttime, endtime);
		
		if((companyId!=""||!companyId.equals(""))&&(aircraftRegNo!=""||!aircraftRegNo.equals(""))&&(starttime==""||starttime.equals(""))&&(endtime==""||endtime.equals("")))
			illegalList = mmisIllegalFlightService.findByCompanyIdsAndIdAndAirRegNo(companyids, Long.parseLong(companyId), aircraftRegNo);
		
		if((companyId!=""||!companyId.equals(""))&&(aircraftRegNo==""||aircraftRegNo.equals(""))&&(starttime!=""||!starttime.equals(""))&&(endtime!=""||!endtime.equals("")))
			illegalList = mmisIllegalFlightService.findByCompanyIdsAndIdAndDate(companyids, Long.parseLong(companyId), starttime, endtime);
		
		if((companyId==""||companyId.equals(""))&&(aircraftRegNo!=""||!aircraftRegNo.equals(""))&&(starttime!=""||!starttime.equals(""))&&(endtime!=""||!endtime.equals("")))
			illegalList = mmisIllegalFlightService.findByCompanyIdsAndAirRegNoAndDate(companyids, aircraftRegNo, starttime, endtime);
		
		if((companyId!=""||!companyId.equals(""))&&(aircraftRegNo!=""||!aircraftRegNo.equals(""))&&(starttime!=""||!starttime.equals(""))&&(endtime!=""||!endtime.equals("")))
			illegalList = mmisIllegalFlightService.findByAllCase(companyids, Long.parseLong(companyId), aircraftRegNo, starttime, endtime);
		
		return illegalList;
	}
	
	@RequestMapping(value = "/findbycompanyId1", method = RequestMethod.GET)
	@ApiOperation(value = "违规飞行企业名称下拉列表")
	public List<MmisIllegalFlight> findByCompanyId1(@RequestParam("companyIds")String companyIds){
		String[] companyids = companyIds.split(",");
		return mmisIllegalFlightService.findByCompanyId(companyids);
	}
	
}
