package com.atosenet.ga.company.mgt.controller.ga.company;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.atosenet.ga.company.db.model.ga.company.AirportMap;
import com.atosenet.ga.company.db.model.ga.company.FlyBase;
import com.atosenet.ga.company.mgt.service.ga.company.AirportMapService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/app/api/airpmap")
@Api(tags="机场航图接口")
public class AirportMapController {
	
	public static final Logger logger = LoggerFactory.getLogger(AirportMapController.class);
	@Autowired
	private AirportMapService airportMapService;
	
	@RequestMapping(value = { "list", "" }, method = RequestMethod.GET)
	@ApiIgnore
	@ApiOperation(value = "获取所有飞行基地信息")
	public List<AirportMap> getAll() {
		System.out.println("获取所有机场图");
		return airportMapService.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ApiIgnore
	@ApiOperation(value = "新增机场图信息")
	@Transactional(readOnly=false)
	@ApiImplicitParams(
			{
				@ApiImplicitParam(value="机场航图属性",name="airportMap",dataTypeClass=AirportMap.class)
			})
	public AirportMap save(@RequestBody AirportMap airportMap) {
		return airportMapService.sava(airportMap);
	}
	@RequestMapping(value="{id}",method = RequestMethod.GET)
	@ApiIgnore
	@ApiOperation(value = "获取机场图详情")
	@Transactional(readOnly=false)
	@ApiImplicitParams(
			{
				@ApiImplicitParam(value="机场航图ID",name="id",dataType="Long")
			})
	public AirportMap getId(@PathVariable("id") Long id) {
		System.out.println("airportMapId    "+id);
		AirportMap airmap=null;
		try {
			airmap =airportMapService.getId(id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		System.out.println(airmap);
		return airmap;
	}
	@RequestMapping(value="byairportid/{id}",method = RequestMethod.GET)
	@ApiIgnore
	@ApiOperation(value = "根据机场ID获取机场图详情")
	@Transactional(readOnly=false)
	@ApiImplicitParams(
			{
				@ApiImplicitParam(value="机场ID",name="id",dataType="Long")
			})
	public AirportMap getByAirpId(@PathVariable("id") Long id) {
		System.out.println("airportId    "+id);
		AirportMap airmap=null;
		try {
			airmap =airportMapService.getAirpMapByAirpId(id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		System.out.println(airmap);
		return airmap;
	}
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ApiIgnore
	@ApiOperation(value = "删除机场图信息")
	@ApiImplicitParams(
			{
				@ApiImplicitParam(value="机场航图ID",name="id",dataType="Long")
			})
	public void deleteById(@PathVariable("id") Long id) {
		airportMapService.delete(id);
	}

}
