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

import com.atosenet.ga.company.db.model.ga.company.GaCompany;
import com.atosenet.ga.company.db.model.ga.company.MaintainBase;
import com.atosenet.ga.company.db.projection.company.MaintainBaseSimple;
import com.atosenet.ga.company.db.repos.ga.company.MaintainBaseRepository;
import com.atosenet.ga.company.mgt.service.ga.company.GaCompanyService;
import com.atosenet.ga.company.mgt.service.ga.company.MaintainBaseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@Api(tags="维修基地的增删改查")
@RequestMapping("/app/api/mb")
@ApiIgnore
public class MaintainBaseController {
	@Autowired
	MaintainBaseRepository MaintainBaseRepository;
	@Autowired
	MaintainBaseService MaintainBaseService;
	@Autowired
	GaCompanyService gaCompanyService;

	@RequestMapping(value = {"list", ""}, method = RequestMethod.GET)
	@ApiOperation(value = "获取所有维修基地信息")
	public List<MaintainBase> getAllMaintainBase() {
		return  MaintainBaseRepository.findList();
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ApiOperation(value = "获取当前维修基地信息")
	@ApiImplicitParams(
			{
				@ApiImplicitParam(value="维修基地Id",name="id",dataType="Long")
			}
			)
	public MaintainBase getById(@PathVariable("id") Long id) {
		return   MaintainBaseRepository.findOne(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "保存当前维修基地信息")
	@ApiImplicitParams(
			{
				@ApiImplicitParam(value="维修基地信息",name="MaintainBase",dataTypeClass=MaintainBase.class)
			}
			)
	public void save(@RequestBody MaintainBase MaintainBase) {
		GaCompany gaCompany = gaCompanyService.get(Integer.parseInt(MaintainBase.getGaCompanyId() + ""));
		if (gaCompany != null) {
			MaintainBase.setCompanyId(gaCompany);
		}
		MaintainBaseService.save(MaintainBase);
	}
	@RequestMapping(value="/byname",method = RequestMethod.POST)
	@ApiOperation(value = "保存当前维修基地信息")
	@ApiIgnore
	public List<MaintainBase> getListByName(@RequestBody MaintainBase maintainBase) {
		System.out.println(maintainBase.getMaintainbasename());
		return MaintainBaseRepository.findListByName(maintainBase.getMaintainbasename());
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "删除维修基地信息")
	@ApiImplicitParams(
			{
				@ApiImplicitParam(value="维修基地ID",name="id",dataType="Long")
			}
			)
	public void deleteById(@PathVariable("id") Long id) {
		MaintainBaseService.deleteMaintainBase(id);
	}
	
	@RequestMapping(value="/search/getlistbycompanyid",method = RequestMethod.GET)
	@ApiOperation(value="根据企业id获得起降点列表")
	@ApiImplicitParams(
			{
				@ApiImplicitParam(value="公司ID",name="companyid",dataType="String")
			}
			)
	public List<MaintainBase> getlistbycompanyid(@RequestParam ("companyid") String companyid)
	{
		return MaintainBaseRepository.getlistbycompanyid(Long.parseLong(companyid));
	}
	@RequestMapping(value="/search/getlistbymainrightcompanyid",method = RequestMethod.GET)
	@ApiOperation(value="根据产权企业id获得起降点列表")
	@ApiImplicitParams(
			{
				@ApiImplicitParam(value="公司ID",name="companyid",dataType="String")
			}
			)
	public List<MaintainBase> getlistbymainrightcompanyid(@RequestParam ("companyid") String companyid)
	{
		System.out.println(companyid);
		return MaintainBaseService.getlistbymainrightcompanyid(Long.parseLong(companyid));
	}
	
	@RequestMapping(value="/search/mainbycompanyid",method = RequestMethod.GET)
	@ApiOperation(value="根据企业id获得维修基地的列表 返回部分字段")
	@ApiImplicitParams(
			{
				@ApiImplicitParam(value="公司ID",name="companyid",paramType="query",dataType="String")
			}
			)
	public List<?> getlistbycom(@RequestParam ("companyid") String companyid)
	{
		return MaintainBaseService.getMaintainBaseByCompanyId(Long.parseLong(companyid));
	}
	
	@RequestMapping(value="/search/maintainall",method = RequestMethod.GET)
	@ApiOperation(value="获取所有的维修基地 返回部分字段")
	public List<MaintainBaseSimple> getlistbm()
	{
		return MaintainBaseRepository.findByMaintainbaseidNotNull();
	}
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public String emptyResultDataAccessException(Throwable t) {
		return "error";
	}

}
