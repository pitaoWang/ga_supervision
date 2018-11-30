package com.atosenet.ga.company.mgt.controller.ga.company;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
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

import com.atosenet.ga.company.db.model.ga.company.AircraftRepairLicense;
import com.atosenet.ga.company.db.model.ga.company.AirportRepairDto;
import com.atosenet.ga.company.db.model.ga.employe.PersonQulificationInfo;
import com.atosenet.ga.company.mgt.service.ga.company.AircraftRepairLicenseModelCheckService;
import com.atosenet.ga.company.mgt.service.ga.company.AircraftRepairLicenseService;
import com.atosenet.ga.company.mgt.service.ga.employe.PersonQulificationInfoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@CrossOrigin
@RequestMapping("/eim/api/aircraftrl")
@Api(tags="航空器维修执照接口")
public class AircraftRepairLicenseController {
	public static final Logger logger = LoggerFactory.getLogger(AirportController.class);
	@Autowired
	AircraftRepairLicenseService apr;
	@Autowired
	PersonQulificationInfoService personQulificationInfoService;

	@Autowired
	AircraftRepairLicenseModelCheckService aircraftRepairLicenseProjectCheckService;
	
	@RequestMapping(value = { "list", "" }, method = RequestMethod.GET)
	@ApiOperation(value = "获取所有航空器部件维修执照")
	public List<AircraftRepairLicense> getAllTest() {
		return apr.findAll();
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ApiOperation(value = "根据执照id获取所有航空器部件维修执照息")
	@ApiImplicitParams(
			{
				@ApiImplicitParam(value="航空器部件维修执照ID",name="id",dataType="Long")
			}
			)
	public AircraftRepairLicense getById(@PathVariable("id") Long id) {
		return apr.getId(id);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "删除当前航空器部件维修信息")
	@ApiImplicitParams(
			{
				@ApiImplicitParam(value="航空器部件维修执照ID",name="id",dataType="Long")
			}
			)
	public void deleteById(@PathVariable("id") Long id) {
		apr.delete(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "新增当前航空器部件维修信息")
	@Transactional(readOnly = false)
	@ApiImplicitParams(
			{
				@ApiImplicitParam(value="航空器部件维修执照信息",name="map",dataType="map")
			}
			)
	public AircraftRepairLicense save(@RequestBody AirportRepairDto map) {
		
		AircraftRepairLicense apart=map.getAircraftrepair();
		PersonQulificationInfo info =map.getPersonQulificationInfo();
//		System.out.println(map); 
//		AircraftRepairLicense apart = new AircraftRepairLicense();
//		try {
//			BeanUtils.copyProperties(apart, map.get("aircraftRepairLicense"));
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InvocationTargetException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		PersonQulificationInfo info =new PersonQulificationInfo();
//		try {
//			BeanUtils.copyProperties(info, map.get("personQulificationInfo"));
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InvocationTargetException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return apr.save(apart, info);
	}

	@ExceptionHandler(EmptyResultDataAccessException.class)
	@ApiIgnore
	public String emptyResultDataAccessException(Throwable t) {
		return "error";
	}
}
