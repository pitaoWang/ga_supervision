package com.atosenet.ga.company.mgt.controller.ga.company;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.atosenet.ga.company.db.model.ga.company.AircraftRepairLicenseModelCheck;
import com.atosenet.ga.company.mgt.service.ga.company.AircraftRepairLicenseModelCheckService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@CrossOrigin
@RequestMapping("/eim/api/aircraftrlmc")
@Api(tags="航空器维修执照机型签发接口")
public class AircraftRepairLicenseModelCheckController {
	public static final Logger logger = LoggerFactory.getLogger(AirportController.class);
	@Autowired
	AircraftRepairLicenseModelCheckService apr;
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ApiIgnore
	@ApiOperation(value = "删除航空器维修执照机型签")
	public void deleteById(@PathVariable("id") Long id) {
		apr.delete(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ApiIgnore
	@ApiOperation(value = "新增航空器维修执照机型签")
	@Transactional(readOnly=false)
	public AircraftRepairLicenseModelCheck save(@RequestBody AircraftRepairLicenseModelCheck a) {
		return apr.save(a);
	}
	
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	@ApiIgnore
	public String emptyResultDataAccessException(Throwable t){
		return "error";
	}
}
