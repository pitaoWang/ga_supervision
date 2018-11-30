package com.atosenet.ga.company.mgt.controller.ga.company;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atosenet.ga.company.db.model.ga.company.FlightRunway;
import com.atosenet.ga.company.db.repos.ga.company.FlightRunwayRepository;
import com.atosenet.ga.company.mgt.service.ga.company.FlightRunwayService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/*@RestController
@RequestMapping("/app/api/flightrunway")
@Api(tags="机场跑道接口")*/
public class FlightRunwayController {

	public static final Logger logger = LoggerFactory.getLogger(AirportController.class);
	@Autowired
	FlightRunwayRepository flightRunwayRepository;
	
	@Autowired
	FlightRunwayService FlightRunwayService;
	
	@ModelAttribute
	@ApiIgnore
	public FlightRunway get(@RequestParam(required=false) Long airportId) {
		logger.debug("@ModelAttribute:airportId = {}", airportId);
		if (airportId != null){
			return flightRunwayRepository.findOne(airportId);
		} else {
			return new FlightRunway();
		}
	}
	
	@RequestMapping(value = {"list", ""}, method = RequestMethod.GET)
	@ApiOperation(value = "获取所有跑道信息")
	public List<FlightRunway> getAllTest() {
		return flightRunwayRepository.findList();
	}
	
	@RequestMapping(value = "byairportid/{airportid}", method = RequestMethod.GET)
	@ApiOperation(value = "获取所有跑道信息")
	@ApiImplicitParams(
			{
				@ApiImplicitParam(value="机场ID",name="airportid",dataType="Long")
			}
			)
	public List<FlightRunway> getRunListByAirportId(@PathVariable("airportid") Long airportid) {
		return flightRunwayRepository.findListByAirportId(airportid);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ApiOperation(value = "获取当前跑道信息")
	@ApiImplicitParams(
			{
				@ApiImplicitParam(value="机场跑道ID",name="id",dataType="Long")
			}
			)
	public FlightRunway getById(@PathVariable("id") Long id) {
		return flightRunwayRepository.findOne(id);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "删除当前跑道信息")
	@ApiImplicitParams(
			{
				@ApiImplicitParam(value="机场跑道ID",name="id",dataType="Long")
			}
			)
	public void deleteById(@PathVariable("id") Long id) {
		FlightRunwayService.delete(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "新增跑道信息")
	@Transactional(readOnly=false)
	@ApiImplicitParams(
			{
				@ApiImplicitParam(value="机场跑道信息",name="flightRunway",dataType="flightRunway")
			}
			)
	public FlightRunway FlightRunway(@RequestBody FlightRunway flightRunway) {
		return FlightRunwayService.sava(flightRunway);
	}
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public String emptyResultDataAccessException(Throwable t){
		return "error";
	}
}
