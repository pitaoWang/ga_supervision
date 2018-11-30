package com.atosenet.ga.company.mgt.controller.ga.company;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.atosenet.ga.company.db.model.ga.company.CompanyBusinessType;
import com.atosenet.ga.company.mgt.service.ga.company.CompanyBusinessTypeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/app/api/cbt")
@Api(tags="公司运行类型接口")
@ApiIgnore
public class CompanyBusinessTypeController {

	@Autowired
	CompanyBusinessTypeService companyBusinessTypeService;
	

	
	@RequestMapping(value="bybusinesstype/{type}",method =RequestMethod.GET)
	@ApiOperation(value="获取所有公司信息")
	public List<CompanyBusinessType> getListByBusinessType(@PathVariable("type") short type)
	{
		System.out.println("获取所有公司信息");
		return companyBusinessTypeService.findListByBusinessType(type);
	}
	
	@RequestMapping(value="{id}",method = RequestMethod.GET)
	@ApiOperation(value="详细信息")
	public CompanyBusinessType getById(@PathVariable("id") long id)
	{
		return companyBusinessTypeService.get(id);
	}
	@RequestMapping(value="{id}",method = RequestMethod.DELETE)
	@ApiOperation(value="删除企业经营范围根据企业ID")
	public void deleteById(@PathVariable("id") int id)
	{
		companyBusinessTypeService.deleteByCompanyId(id);;
	}
	
	
	
}
