package com.atosenet.ga.company.mgt.controller.ga.company;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atosenet.ga.company.db.model.ga.company.GaCompany;
import com.atosenet.ga.company.db.model.ga.company.LandingPoint;
import com.atosenet.ga.company.db.projection.company.LandingPointSimple;
import com.atosenet.ga.company.db.repos.ga.company.LandingPointRepository;
import com.atosenet.ga.company.mgt.service.ga.company.GaCompanyService;
import com.atosenet.ga.company.mgt.service.ga.company.LandingPointService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/app/api/landingpoint")
@ApiIgnore
@Api(tags="起降点接口")
public class LandingPointController {

	@Autowired
	LandingPointService landingPointService;
	
	@Autowired
	GaCompanyService gaCompanyService;
	
	@Autowired
	LandingPointRepository landingPointRepository;
	
	@RequestMapping(value={"list",""},method =RequestMethod.GET)
	@ApiOperation(value="获取所有起降点信息")
	public List<LandingPoint> getAllLandingPoint()
	{
		System.out.println("获取所有起降点信息");
		return landingPointService.findAll();
	}
	
	@RequestMapping(value="{id}",method = RequestMethod.GET)
	@ApiOperation(value="获取起降点详细信息")
	@ApiImplicitParams(
			{
				@ApiImplicitParam(value="起降点ID",name="id",dataType="Long")
			}
			)
	public LandingPoint getById(@PathVariable("id") Long id)
	{
		return landingPointService.get(id);
	}
	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value="保存起降点信息")
	@Transactional(readOnly=false)
	@ApiImplicitParams(
			{
				@ApiImplicitParam(value="起降点信息",name="landingPoint",dataTypeClass=LandingPoint.class)
			}
			)
	public void save(@RequestBody LandingPoint landingPoint)
	{
		
		System.out.println("保存起降点信息");
		System.out.println(landingPoint.getName());
		GaCompany gaCompany= gaCompanyService.get(Integer.parseInt(landingPoint.getGaCompanyId()+""));
		landingPoint.setCompanyId(gaCompany);
		LandingPoint lp = landingPointService.save(landingPoint);
	//	landingPointService.savecompanylandingpoint(landingPoint.getGaCompanyId(), lp.getId());	
	}
	@RequestMapping(value="/getlistbyname",method = RequestMethod.POST)
	@ApiOperation(value="根据起降点名称获取起降点列表")
	@Transactional(readOnly=false)
	@ApiIgnore
	public List<LandingPoint> getListbyName(@RequestBody LandingPoint name)
	{
		System.out.println(name);
		return landingPointService.findListByName(name.getName());
	}
	
	@RequestMapping(value="{id}",method = RequestMethod.DELETE)
	@ApiOperation(value="删除当前起降点")
	@ApiImplicitParams(
			@ApiImplicitParam(value="起降点ID",name="id",dataType="Long")
			)
	public void deleteById(@PathVariable("id") Long id)
	{
		landingPointService.delete(id);
	}
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public String emptyResultDataAccessException(Throwable t)
	{
		return "error";
	}
	@RequestMapping(value="/search/getlistbycompanyid",method = RequestMethod.GET)
	@ApiOperation(value="根据企业id获得起降点列表")
	@ApiImplicitParams(
			@ApiImplicitParam(value="公司ID",name="companyid",dataType="String")
			)
	public List<LandingPoint> getlistbycompanyid(@RequestParam ("companyid") String companyid)
	{
		return landingPointService.getlistbycompanyid(Long.parseLong(companyid));
	}
	/**
	 * 根据主权公司id获取起降点
	 * @param companyid
	 * @return
	 */
	@RequestMapping(value="/search/getlistbymaincompanyid",method = RequestMethod.GET)
	@ApiOperation(value="根据主权企业id获得起降点列表")
	@ApiImplicitParams(
			@ApiImplicitParam(value="公司ID",name="companyid",dataType="String")
			)
	public List<LandingPoint> getlistbymaincompanyid(@RequestParam ("companyid") String companyid)
	{
		System.out.println(companyid);
		return landingPointService.getlistbymaincompanyid(Long.parseLong(companyid));
	}
	
	@RequestMapping(value="/search/landingbycompanyid",method = RequestMethod.GET)
	@ApiOperation(value="根据企业id获得起降点的列表 返回部分字段")
	@ApiImplicitParams(
			@ApiImplicitParam(value="公司ID",name="companyid",dataType="Long")
			)
	public List<Map<String, Object>> getlistbycom(@RequestParam ("companyid") Long companyid)
	{
		//GaCompany com = new GaCompany(Integer.parseInt(companyid));
		return landingPointService.getlandingPointByCompanyId(companyid);
	}
	
	@RequestMapping(value="/search/landingall",method = RequestMethod.GET)
	@ApiOperation(value="获取所有的起降点 返回部分字段")
	public List<LandingPointSimple> getlistbm()
	{
		return landingPointRepository.findByIdNotNull();
	}
	
}
