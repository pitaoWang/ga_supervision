package com.atosenet.ga.company.mgt.controller.ga.company;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atosenet.ga.company.db.model.ga.company.AircraftCheckinfo;
import com.atosenet.ga.company.db.repos.ga.company.AircraftCheckinfoRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@CrossOrigin
@RequestMapping("/eim/api/ak")
@Api(tags="航空器检查记录接口")
public class AircraftCheckinfoController {
	@Autowired
	AircraftCheckinfoRepository AircraftCheckinfoRepository;

	@RequestMapping(value = {"list"}, method = RequestMethod.GET)
	@ApiIgnore
	@ApiOperation(value = "获取所有检查记录信息")
	public List<AircraftCheckinfo> getAllAircraftCheckinfo() {
		return  AircraftCheckinfoRepository.findList();
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ApiIgnore
	@ApiOperation(value = "获取当前检查记录信息")
	public AircraftCheckinfo getById(@PathVariable("id") Long id) {
		return   AircraftCheckinfoRepository.findOne(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ApiIgnore
	@ApiOperation(value = "保存当前检查记录信息")
	public void save(@RequestBody AircraftCheckinfo AircraftCheckinfo) {
		AircraftCheckinfoRepository.save(AircraftCheckinfo);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ApiIgnore
	@ApiOperation(value = "删除检查记录信息")
	public void deleteById(@PathVariable("id") Long id) {
		AircraftCheckinfoRepository.delete(id);
	}
	
	@RequestMapping(value="/serach/getbyaircraftid",method = RequestMethod.GET)
	@ApiIgnore
	@ApiOperation(value="获取某航空器检查详细信息")
	@ApiImplicitParam(value="航空器ID",name="aircraftId",dataType="String")
	public List<AircraftCheckinfo> getByAircraftId(@RequestParam("aircraftId") String aircraftId)
	{	
		return AircraftCheckinfoRepository.findByAircraftId(Long.parseLong(aircraftId));
	}
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public String emptyResultDataAccessException(Throwable t) {
		return "error";
	}

}
