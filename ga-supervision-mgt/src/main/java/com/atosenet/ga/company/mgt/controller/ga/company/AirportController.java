package com.atosenet.ga.company.mgt.controller.ga.company;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atosenet.ga.company.db.model.ga.company.Airport;
import com.atosenet.ga.company.db.projection.company.AirportSimple;
import com.atosenet.ga.company.db.repos.ga.company.AirportRepository;
import com.atosenet.ga.company.mgt.service.ga.company.AirportService;
import com.atosenet.ga.company.mgt.service.ga.company.GaCompanyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@CrossOrigin
@RequestMapping("/eim/api/airport")
@Api(tags="机场接口")
public class AirportController {
	
	public static final Logger logger = LoggerFactory.getLogger(AirportController.class);
	@Autowired
	AirportRepository airportRepository;
	@Autowired
	AirportService airportService;
	@Autowired
	GaCompanyService gaCompanyService;
	@RequestMapping(value = {"list", ""}, method = RequestMethod.GET)
	@ApiIgnore
	@ApiOperation(value = "获取机场列表",httpMethod="GET",response=List.class)
	public List<Airport> getAllTest() {
		return airportRepository.findList();
	}
	@RequestMapping(value = "listbyprovinceandcity/pro", method = RequestMethod.POST)
	@ApiIgnore
	@ApiOperation(value = "根据城市获取机场列表",httpMethod="POST",response=List.class)
	public List<Airport> listbyprovinceandcity(@RequestBody Airport airport) {
		return airportService.findListbypc(airport.getProvince(),airport.getCity());
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ApiIgnore
	@ApiOperation(value = "获取机场详细信息",httpMethod="GET",response=Airport.class)
	@ApiImplicitParams(
			@ApiImplicitParam(value="机场ID",name="id",dataType="Long")
			)
	public Airport getById(@PathVariable("id") Long id) {
		return airportRepository.findOne(id);
	}
	
	@RequestMapping(value = "/search/{id}", method = RequestMethod.GET)
	@ApiIgnore
	@ApiOperation(value = "获取机场封装后的详细信息",httpMethod="GET",response=Map.class)
	@ApiImplicitParams(
			@ApiImplicitParam(value="机场ID",name="id",dataType="Long")
			)
	public Map<String,Object> getMapById(@PathVariable("id") Long id) {
		return airportService.findMapOne(id);
	}
	
	@RequestMapping(value = "/airportname", method = RequestMethod.POST)
	@ApiIgnore
	@ApiOperation(value = "根据机场的名称获取机场列表",httpMethod="POST",response=Map.class)
	public List<Airport> getById(@RequestBody Airport airport) {
		System.out.println(airport.getAirportName());
		return airportService.getByairportName(airport.getAirportName());
	}
	@RequestMapping(value = "/search/getaiportbysup", method = RequestMethod.GET)
	@ApiIgnore
	@ApiOperation(value = "根据监管局ID获取机场分页列表",httpMethod="GET",response=Page.class)
	@ApiImplicitParams({
			@ApiImplicitParam(value="当前页码",name="pageno",dataType="String"),
			@ApiImplicitParam(value="页面显示长度",name="pagesize",dataType="String"),
			@ApiImplicitParam(value="监管局ID",name="companyid",dataType="String"),
			@ApiImplicitParam(value="机场名称",name="airportName",dataType="String")
	}
			)
	public Page<Map<String,Object>> getBycompanysupervise(@RequestParam(value="pageno",required=false) String pageno,@RequestParam(value="pagesize",required=false)  String pagesize,@RequestParam(value="companyid",required=false) String companyid,
			@RequestParam(value="airportName",required=false) String airportName) throws UnsupportedEncodingException {
		Pageable pageable = null;
		if(pageno!=null&&pagesize!=null)
		{
			pageable=new PageRequest(Integer.parseInt(pageno), Integer.parseInt(pagesize));
		}else
		{
			pageable=new PageRequest(1, 10);
		}
		Page<Map<String,Object>> st =null;
		if(companyid!=null&&companyid!=""){
			st=airportService.findAircraftBysupervise(airportName, Long.parseLong(companyid), pageable);
		}
		return st;
	}
	
	@RequestMapping(value = "/search/getairportbyauth", method = RequestMethod.GET)
	@ApiIgnore
	@ApiOperation(value = "根据管理局ID获取机场分页列表",httpMethod="GET",response=Page.class)
	@ApiImplicitParams({
		@ApiImplicitParam(value="当前页码",name="pageno",dataType="String"),
		@ApiImplicitParam(value="页面显示长度",name="pagesize",dataType="String"),
		@ApiImplicitParam(value="监管局ID",name="companyid",dataType="String"),
		@ApiImplicitParam(value="机场名称",name="airportName",dataType="String")
}
		)
	public Page<Map<String,Object>> getBycompanyauthority(@RequestParam(value="pageno",required=false) String pageno,@RequestParam(value="pagesize",required=false)  String pagesize,@RequestParam(value="companyid",required=false) String companyid,
			@RequestParam(value="airportName",required=false) String airportName) throws UnsupportedEncodingException {
		Pageable pageable = null;
		if(pageno!=null&&pagesize!=null)
		{
			pageable=new PageRequest(Integer.parseInt(pageno), Integer.parseInt(pagesize));
		}else
		{
			pageable=new PageRequest(1, 10);
		}
		Page<Map<String,Object>> st =null;
		if(companyid!=null&&companyid!=""){
			st=airportService.findAircraftByauthority(airportName, Long.parseLong(companyid), pageable);
		}
		return st;
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ApiIgnore
	@ApiOperation(value = "删除机场信息",httpMethod="DELETE",response=void.class)
	@ApiImplicitParams({
		@ApiImplicitParam(value="机场ID",name="id",dataType="Long")
}
		)
	public void deleteById(@PathVariable("id") Long id) {
		airportService.delete(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ApiIgnore
	@ApiOperation(value = "新增机场信息",httpMethod="POST",response=Map.class)
	@Transactional(readOnly=false)
	@ApiImplicitParams({
		@ApiImplicitParam(value="机场属性",name="airport",dataTypeClass=Airport.class)
}
		)
	public Map<String,Object> save(@RequestBody Airport airport) {
		System.out.println(airport.getCompanyId());
	//	GaCompany gaCompany= gaCompanyService.get(Integer.parseInt(airport.getGaCompanyId()+""));
	//	airport.setCompanyId(gaCompany);
		airport=airportService.sava(airport);
		System.out.println(airportService.sava(airport));
		Map<String,Object> map = new HashMap<>();
		map.put("id", airport.getAirportId());
//		airportService.savecompanylandingpoint(airport.getCompanyId(), airport.getAirportId());//保存到中间表
		return map;
	}
	
	@RequestMapping(value="/search/getlistbycompanyid",method = RequestMethod.GET)
	@ApiIgnore
	@ApiOperation(value = "根据企业id获取机场列表",httpMethod="GET",response=List.class)
	@ApiImplicitParams({
		@ApiImplicitParam(value="根据公司ID",name="companyid",dataType="String")
}
		)
	public List<Airport> getlistbycompanyid(@RequestParam ("companyid") String companyid)
	{
		try{
			Long.parseLong(companyid);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		return airportService.getlistbycompanyid(Long.parseLong(companyid));
	}
	
	
	@RequestMapping(value="/search/airportbycompanyid",method = RequestMethod.GET)
	@ApiIgnore
	@ApiOperation(value="根据企业id获得机场的列表 返回部分字段",httpMethod="GET",response=List.class)
	@ApiImplicitParams({
		@ApiImplicitParam(value="根据公司ID",name="companyid",dataType="Long")
}
		)
	public List<Map<String, Object>> getlistbycom( @RequestParam ("companyid") Long companyid)
	{
		//GaCompany com = new GaCompany(Integer.parseInt(companyid));
		//return airportRepository.findByCompanyId(com);
		return airportService.getairportByCompanyId(companyid);
	}
	
	@RequestMapping(value="/search/airportbymaincompanyid",method = RequestMethod.GET)
	@ApiIgnore
	@ApiOperation(value="根据主权企业id获得机场的列表",httpMethod="GET",response=List.class)
	@ApiImplicitParams({
		@ApiImplicitParam(value="根据主权公司ID",name="companyid",dataType="Long")
}
		)
	public List<Airport> getlistbymaincom(@ApiParam(value = "根据主权公司ID",required=true) @RequestParam ("companyid") Long companyid)
	{
		//GaCompany com = new GaCompany(Integer.parseInt(companyid));
		//return airportRepository.findByCompanyId(com);
		return airportService.getairportByMainCompanyId(companyid);
	}
	
	@RequestMapping(value="/search/airportall",method = RequestMethod.GET)
	@ApiIgnore
	@ApiOperation(value="获取所有的机场 返回部分字段")
	public List<AirportSimple> getlistbm()
	{
		return airportService.getairportAll();
	}
	
	@RequestMapping(value="/listbyprovinceandcity/pro",method = RequestMethod.GET)
	@ApiIgnore
	@ApiOperation(value="省市级联查询机场列表")
	@ApiImplicitParams({
		@ApiImplicitParam(value="省份",name="province",dataType="String"),
		@ApiImplicitParam(value="市",name="city",dataType="String")
}
		)
	public List<Airport> getlistbyprovinceandcity(@RequestParam ("province") String province,@RequestParam ("city") String city)
	{
		return airportService.getlistbyprovinceandcity(province,city);
	}
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	@ApiIgnore
	public String emptyResultDataAccessException(Throwable t){
		return "error";
	}
}
