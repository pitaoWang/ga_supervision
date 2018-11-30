package com.atosenet.ga.company.mgt.controller.ga.company;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atosenet.ga.company.db.model.ga.company.GaCompany;
import com.atosenet.ga.company.db.model.ga.company.GaOfficial;
import com.atosenet.ga.company.db.model.sys.CreateUser;
import com.atosenet.ga.company.db.model.sys.Org;
import com.atosenet.ga.company.db.repos.ga.company.GaCompanyRepository;
import com.atosenet.ga.company.db.repos.ga.company.GaOfficialRepository;
import com.atosenet.ga.company.db.repos.sys.OrgRepository;
import com.atosenet.ga.company.mgt.config.Constants;
import com.atosenet.ga.company.mgt.service.ga.company.OrgService;
import com.atosenet.ga.company.mgt.service.ga.employe.response.DataResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/app/api/org")
@ApiIgnore
@Api(tags="企业（org）信息")
public class OrgController {

	@Autowired
	OrgService orgservice;
	@Autowired
	OrgRepository orgRepository;
	@Autowired
	GaOfficialRepository gaOfficialRepository;
	@Autowired
	GaCompanyRepository gaCompanyRepository;
	
	@RequestMapping(method =RequestMethod.GET)
	public DataResponse<List<Org>> findAllOrg(){
			long start = System.currentTimeMillis();	
			DataResponse<List<Org>> response = new DataResponse<List<Org>>();
			List<Org> list=new ArrayList<Org>();
			try 
			{
				list=  orgservice.findAllOrg();
				response.setData(list);
				response.setResultCode(Constants.OK);			
			} catch (Exception e) {
				e.printStackTrace();
				response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
				response.setMessage(e.getMessage());
			}
			finally 
			{
				System.out.print("time = "+(System.currentTimeMillis() - start));
			}
			
			return response;
		}
	
		@RequestMapping(value="/createOrg",method = RequestMethod.POST)
		@ApiOperation(value = "保存创建用户信息")
		public DataResponse<String> createOrg(@RequestBody CreateUser createuser) {
		
			String s=null;
			Org org=new Org();
			org.setName(createuser.getCompanyName());
			org.setOrgtype(Long.parseLong(createuser.getCompanyType()));
			Org orgjieguo=orgRepository.save(org);
			if((createuser.getCompanyType()).equals("1") || (createuser.getCompanyType()).equals("2")) {
				GaOfficial gaOfficial=new GaOfficial();
				gaOfficial.setOrgId(orgjieguo.getId());
				gaOfficial.setName(createuser.getCompanyName());
				gaOfficialRepository.save(gaOfficial);
			}
			
			else if((createuser.getCompanyType()).equals("3") || 
						(createuser.getCompanyType()).equals("5") || 
						(createuser.getCompanyType()).equals("6")	){
				GaCompany gaCompany =new GaCompany();
				gaCompany.setOrgId(orgjieguo.getId());
				gaCompany.setName(createuser.getCompanyName());
				GaCompany gaCompany1=gaCompanyRepository.save(gaCompany);
				s=gaCompany1.getCompanyId()+"";
			}
			DataResponse<String> response = new DataResponse<String>();
			response.setData(s);
			response.setResultCode(Constants.OK);	
			return response;
			
		}
		//创建完整的企业信息
		@RequestMapping(value="/createCompleteOrg",method = RequestMethod.POST)
		@ApiOperation(value = "保存创建组织信息")
		public void createCompleteOrg(@RequestBody Org org) {
			orgservice.createCompleteOrg(org);
			
		}
		
	
		@RequestMapping(value="/deleteOrgById",method =RequestMethod.GET)
		@ApiOperation(value = "根据Id删除组织信息")
		public void deleteOrgById(@RequestParam("id") String id) {
			orgservice.deleteOrgById(Long.parseLong(id));
			
		}
		
		@RequestMapping(value="/findOrgById",method =RequestMethod.GET)
		@ApiOperation(value = "根据Id查询组织")
		public Org findOrgById(@RequestParam("id") String id) {
			return orgservice.findOrgById(Long.parseLong(id));
		}
		
		@RequestMapping(value="{id}",method =RequestMethod.GET)
		@ApiOperation(value = "根据Id查询组织")
		public Org getid(@PathVariable("id") long id) {
			return orgservice.findOrgById(id);
		}
		
		@RequestMapping(value="/searchByOrgNameAndOrgType",method = RequestMethod.POST)
		@ApiOperation(value = "根据组织名和组织类型查询组织")
		public DataResponse<List<Org>> searchByOrgNameAndOrgType(@RequestBody Org selectorg) {
			DataResponse<List<Org>> response = new DataResponse<List<Org>>();
			List<Org> list=null;
			try {
				list= orgservice.searchByOrgNameAndOrgType(selectorg);
				response.setData(list);
				response.setResultCode(Constants.OK);
				
			} catch (Exception e) {
				e.printStackTrace();
				response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
				response.setMessage(e.getMessage());
			}
			
			return response;
		}
}
