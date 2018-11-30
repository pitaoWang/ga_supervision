package com.atosenet.ga.company.mgt.controller.ga.company;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.atosenet.ga.company.db.model.ga.company.CompanyLegalperson;
import com.atosenet.ga.company.mgt.service.ga.company.CompanyLegalpersonService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/app/api/legalperson")
@ApiIgnore
@Api(tags="企业法人信息")
public class CompanyLegalpersonController {
	
	@Autowired
	private CompanyLegalpersonService companyLegalpersonService;
	
//	@RequestMapping(value="/findCompanyLegalpersonByCartId")
//	@ApiOperation(value = "根据身份证查询企业法人")
//	public DataResponse<CompanyLegalperson> findCompanyLegalpersonByCartId(@RequestParam("cartid") String cardid)
//	{
//		long start = System.currentTimeMillis();
//		
//		DataResponse<CompanyLegalperson> response = new DataResponse<CompanyLegalperson>();
//		
//		try 
//		{
//			CompanyLegalperson companyLegalperson = companyLegalpersonService.findCompanyLegalpersonByCardid(cardid);
//			response.setData(companyLegalperson);
//			response.setResultCode(Constants.OK);	
//		} catch (Exception e) {
//			e.printStackTrace();
//			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
//			response.setMessage(e.getMessage());
//		}
//		finally 
//		{
//			System.out.print("time = "+(System.currentTimeMillis() - start));
//		}
//		
//		return response;
//	}
	
	@RequestMapping(value="/save",method = RequestMethod.POST)
	@ApiOperation(value="保存员工体检信息")
	@Transactional(readOnly=false)
	public void saveCompanyLegalperson(@RequestBody CompanyLegalperson companyLegalperson,HttpServletRequest request)
	{
		companyLegalpersonService.sava(companyLegalperson,request);
	}
	
	@RequestMapping(value="/findByUserId",method = RequestMethod.GET)
	@ApiOperation(value="根据id来查询法人信息")
	@ApiImplicitParams({
		@ApiImplicitParam(value="用户userid",name="userid",dataType="String")	
	})
	@Transactional(readOnly=false)
	public CompanyLegalperson findByUserId(@RequestParam("userid") String id)
	{

		return companyLegalpersonService.findByUserId(id);
	}
	
	@RequestMapping(value="/findByCompanyId",method = RequestMethod.GET)
	@ApiOperation(value="根据公司Id来查询法人信息")
	@ApiImplicitParams({
		@ApiImplicitParam(value="公司id",name="companyId",dataType="String")	
	})
	public CompanyLegalperson findByCompanyId(@RequestParam("companyId") String companyId)
	{

		return companyLegalpersonService.findByCompanyId(companyId);
	}
}
