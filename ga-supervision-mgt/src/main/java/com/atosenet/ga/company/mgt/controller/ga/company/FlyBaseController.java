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

import com.atosenet.ga.company.db.model.ga.company.FlyBase;
import com.atosenet.ga.company.db.model.ga.company.GaCompany;
import com.atosenet.ga.company.db.projection.company.FlyBaseSimple;
import com.atosenet.ga.company.db.repos.ga.company.FlyBaseRepository;
import com.atosenet.ga.company.mgt.service.ga.company.FlyBaseService;
import com.atosenet.ga.company.mgt.service.ga.company.GaCompanyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/app/api/flybase")
@Api(tags="飞行基地接口")
@ApiIgnore
public class FlyBaseController {

	@Autowired
	FlyBaseService flyBaseService;
	@Autowired
	GaCompanyService gaCompanyService;
	@Autowired
	FlyBaseRepository flyBaseRepository;

	@RequestMapping(value = { "list", "" }, method = RequestMethod.GET)
	@ApiOperation(value = "获取所有飞行基地信息")
	@ApiIgnore
	public List<FlyBase> getAllflyBase() {
		System.out.println("获取所有飞行基地信息");
		return flyBaseService.findAll();
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ApiOperation(value = "获取飞行基地详细信息")
	@ApiImplicitParams({
		@ApiImplicitParam(value="飞行基地ID",name="id",dataType="Long")
	})
	@ApiIgnore
	public FlyBase getById(@PathVariable("id") Long id) {
		return flyBaseService.get(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "保存飞行基地信息")
	@Transactional(readOnly = false)
	@ApiImplicitParams({
		@ApiImplicitParam(value="飞行基地信息",name="flyBase",dataType="FlyBase")
	})
	@ApiIgnore
	public void save(@RequestBody FlyBase flyBase) {
		System.out.println("保存飞行基地信息");
		System.out.println(flyBase.getFlybasename());
		/*GaCompany gaCompany = gaCompanyService.get(Integer.parseInt(flyBase.getGaCompanyId() + ""));
		if (gaCompany != null) {
			flyBase.setCompanyId(gaCompany);
		}*/
		flyBase = flyBaseService.save(flyBase);
	//	flyBaseService.savecompanylandingpoint(flyBase.getCompanyId().getCompanyId(), flyBase.getFlybaseid());
	}

	@RequestMapping(value = "/byname", method = RequestMethod.POST)
	@ApiOperation(value = "通过名字查询")
	@Transactional(readOnly = false)
	@ApiIgnore
	public void getListByName(@RequestBody FlyBase flyBase) {
		System.out.println("通过名字查询");
		System.out.println(flyBase.getFlybasename());
		flyBaseService.findByName(flyBase.getFlybasename());
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "删除当前飞行基地信息")
	@ApiImplicitParams({
		@ApiImplicitParam(value="飞行基地ID",name="id",dataType="Long")
	})
	@ApiIgnore
	public void deleteById(@PathVariable("id") Long id) {
		flyBaseService.delete(id);
	}

	@RequestMapping(value = "/listByAutId/{authorityId}", method = RequestMethod.GET)
	@ApiOperation(value = "获取所有飞行基地信息通过管理局ID")
	@ApiImplicitParams({
		@ApiImplicitParam(value="管理局ID",name="authorityId",dataType="Long")
	})
	@ApiIgnore
	public List<FlyBase> findgaCompanyByauthorityId(@PathVariable("authorityId") Long authorityId) {
		return flyBaseService.findByAuthorityIdList(authorityId);
	}

	@RequestMapping(value = "/listBySupId/{supervisionId}", method = RequestMethod.GET)
	@ApiOperation(value = "获取所有飞行基地信息通过监管局ID")
	@ApiImplicitParams({
		@ApiImplicitParam(value="监管局ID",name="supervisionId",dataType="Long")
	})
	@ApiIgnore
	public List<FlyBase> findgaCompanyBysupervisionId(@PathVariable("supervisionId") Long supervisionId) {
		return flyBaseService.findBySupervisionIdList(supervisionId);
	}

	@RequestMapping(value = "/search/listbycompanyid", method = RequestMethod.GET)
	@ApiOperation(value = "获取所有飞行基地信息通过企业id")
	@ApiImplicitParams({
		@ApiImplicitParam(value="公司ID",name="companyid",paramType="query",dataType="String")
	})
	public List<FlyBase> findflybasebycompanyid(@RequestParam("companyid") String companyid) {
		return flyBaseService.findflybasebycompanyid(Long.parseLong(companyid));
	}
	
	@RequestMapping(value = "/search/listbymaincompanyid", method = RequestMethod.GET)
	@ApiOperation(value = "获取所有飞行基地信息通过企业id")
	@ApiImplicitParams({
		@ApiImplicitParam(value="公司ID",name="companyid",dataType="Long")
	})
	@ApiIgnore
	public List<FlyBase> findflybasebymaincompanyid(@RequestParam("companyid") String companyid) {
		return flyBaseService.findflybasebymaincompanyid(Long.parseLong(companyid));
	}

	@RequestMapping(value="/search/flybasebycompanyid",method = RequestMethod.GET)
	@ApiOperation(value="根据企业id获得维修基地的列表 返回部分字段")
	@ApiImplicitParams({
		@ApiImplicitParam(value="公司ID",name="companyid",dataType="Long")
	})
	@ApiIgnore
	public List<Map<String, Object>> getlistbycom(@RequestParam ("companyid") Long companyid)
	{
		return flyBaseService.getflybaseByCompanyId(companyid);
	}
	
	@RequestMapping(value="/search/flybaseall",method = RequestMethod.GET)
	@ApiOperation(value="获取所有的维修基地 返回部分字段")
	@ApiIgnore
	public List<FlyBaseSimple> getlistbm()
	{
		return flyBaseRepository.findByFlybaseidNotNull();
	}
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public String emptyResultDataAccessException(Throwable t) {
		return "error";
	}
}
