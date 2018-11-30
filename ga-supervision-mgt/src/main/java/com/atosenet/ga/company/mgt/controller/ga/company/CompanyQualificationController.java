package com.atosenet.ga.company.mgt.controller.ga.company;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.atosenet.ga.company.db.model.ga.company.CompanyQualification;
import com.atosenet.ga.company.db.repos.ga.company.CompanyQualificationRepository;
import com.atosenet.ga.company.db.repos.ga.company.GaCompanyRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@Api(tags="企业运行合格证信息")
@RequestMapping("/eim/api/cq")
@CrossOrigin("*")
public class CompanyQualificationController {
	@Autowired
	CompanyQualificationRepository CompanyQualificationRepository;

	@Autowired
	GaCompanyRepository  gaCompanyRepository;

	@RequestMapping(value = {"list", ""}, method = RequestMethod.GET)
	@ApiIgnore
	@ApiOperation(value = "获取所有企业资质信息")
	public List<CompanyQualification> getAllCompanyQualification() {
		return  CompanyQualificationRepository.findList();
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ApiIgnore
	@ApiOperation(value = "获取当前企业资质信息详情")	
	public CompanyQualification getById(@PathVariable("id") String id) {
		return   CompanyQualificationRepository.findOne(Long.parseLong(id));
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ApiIgnore
	@ApiOperation(value = "保存当前企业资质信息")
	public CompanyQualification save(@RequestBody CompanyQualification CompanyQualification,HttpServletRequest request) {
		/*CompanyQualificationRepository.save(CompanyQualification);
		GaCompany ga = gaCompanyRepository.findOne(Integer.parseInt(CompanyQualification.getCompanyid().toString()));
	    ga.setFlag((short)4);
	    gaCompanyRepository.save(ga);
		return CompanyQualification.getId();*/
		return CompanyQualificationRepository.save(CompanyQualification);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ApiImplicitParams({ @ApiImplicitParam(value = "运行合格证id", name = "id",paramType="path", dataType = "String") })
	@ApiOperation(value = "根据id删除运行合格证信息")
	public void deleteById(@PathVariable("id") String id) {
		CompanyQualificationRepository.delete(Long.parseLong(id));
	}
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	@ApiIgnore
	public String emptyResultDataAccessException(Throwable t) {
		return "error";
	}
	
	@RequestMapping(value = "/serach/getbycompanyid", method = RequestMethod.GET)
	@ApiOperation(value = "获取当前企业运行合格证信息")
	@ApiImplicitParams({ @ApiImplicitParam(value = "企业id", name = "companyId",paramType="query", dataType = "String") })
	public List<CompanyQualification> getBycompanyid(@RequestParam("companyId") String companyId) {
		return    CompanyQualificationRepository.findListbycompanyid(Long.parseLong(companyId));
	}
	
	@RequestMapping(value = "/savecompanyqualification", method = RequestMethod.POST)
	@ApiOperation(value = "新增/修改企业运行合格证信息")
	@ApiImplicitParams({ @ApiImplicitParam(value = "运行合格证对象", name = "companyQualification",paramType="body", dataType = "CompanyQualification") })
	public CompanyQualification saveCompanyQualification(@RequestBody CompanyQualification companyQualification){
		return CompanyQualificationRepository.save(companyQualification);
	}
	
	/*@RequestMapping(value = "/companyqualificationstatic",method = RequestMethod.GET)
	@ApiOperation(value = "根据监管局统计企业运行合格证信息")
	@ApiIgnore
	@ApiImplicitParams({ @ApiImplicitParam(value = "监管局id", name = "supervisionId",paramType="query", dataType = "String") })
	public List<Object> companyQualificationStatic(@RequestParam("supervisionId")String supervisionId){
		return CompanyQualificationRepository.companyQualificationStatic(Long.parseLong(supervisionId));		
	}*/
	
	@RequestMapping(value = "/companyqualificationstaticbysuperIds",method = RequestMethod.GET)
	@ApiOperation(value = "根据监管局统计企业运行合格证信息")
	@ApiIgnore
	@ApiImplicitParams({ @ApiImplicitParam(value = "监管局ids", name = "supervisionId",paramType="query", dataType = "String") })
	public List<Object> companyQualificationStaticBySuperIds(@RequestParam("supervisionId")String supervisionId){
		String[] superIds = supervisionId.split(",");
		return CompanyQualificationRepository.companyQualificationStaticBySuperIds(superIds);		
	}
	
	@RequestMapping(value = "/companyqualificationstaticbycompanyIds",method = RequestMethod.GET)
	@ApiOperation(value = "根据企业统计企业运行合格证信息")
	@ApiIgnore
	@ApiImplicitParams({ @ApiImplicitParam(value = "监管局ids", name = "companyId",paramType="query", dataType = "String") })
	public Map<String,List<Object>> companyqualificationstaticbycompanyIds(@RequestParam("companyId")String companyId){
		Map<String,List<Object>> all = new HashMap<>();
		all.put("yxhgz", CompanyQualificationRepository.companyqualificationstaticbycompanyIds(companyId));
		all.put("jylb", gaCompanyRepository.staticBusinessTypeByCompanyIds(companyId));
		
		return all;		
	}
}
