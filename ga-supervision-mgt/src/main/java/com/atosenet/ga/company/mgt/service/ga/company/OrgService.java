package com.atosenet.ga.company.mgt.service.ga.company;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.atosenet.ga.company.db.model.ga.company.GaCompany;
import com.atosenet.ga.company.db.model.ga.company.GaOfficial;
import com.atosenet.ga.company.db.model.sys.Org;
import com.atosenet.ga.company.db.repos.ga.company.GaCompanyRepository;
import com.atosenet.ga.company.db.repos.ga.company.GaOfficialRepository;
import com.atosenet.ga.company.db.repos.sys.OrgRepository;


@Service
@Transactional(propagation=Propagation.SUPPORTS,readOnly = true)
public class OrgService {

	@Autowired
	GaOfficialRepository gaOfficialRepository;
	@Autowired
	GaCompanyRepository gaCompanyRepository;
	@Autowired
	OrgRepository orgRepository;
	
	@Transactional(readOnly=false)
	public List<Org> findAllOrg()
	{
		
			return orgRepository.findList();
		
	}
	
	
	@Transactional(readOnly=false)
	public void createCompleteOrg(Org org)
	{
		try {
			Org orgjieguo=orgRepository.save(org);
			
			if(org.getOrgtype()==1 || org.getOrgtype()==2) {
				GaOfficial gaOfficial=new GaOfficial();
				gaOfficial.setOrgId(orgjieguo.getId());
				gaOfficial.setName(org.getName());
				gaOfficialRepository.save(gaOfficial);
			}
			
			else if(org.getOrgtype()==3 || 
							org.getOrgtype()==5 || 
									org.getOrgtype()==6	){
				GaCompany gaCompany =new GaCompany();
				gaCompany.setOrgId(orgjieguo.getId());
				gaCompany.setName(org.getName());
				gaCompanyRepository.save(gaCompany);
//				s=gaCompany1.getCompanyId()+"";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	@Transactional(readOnly=false)
	public void deleteOrgById(long id) {
		try {
			orgRepository.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Transactional(readOnly=false)
	public Org findOrgById(long id) {
		Org org=null;
		try {
			org= orgRepository.findOne(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return org;
	}
	
	@Transactional(readOnly=false)
	public List<Org> searchByOrgNameAndOrgType(Org org) {
		List<Org> list=null;
		try {
			if(("".equals(org.getName())) 
					&&(  org.getOrgtype()==0 )) {
				System.out.println("进到了findList。。。。");
				list= orgRepository.findList();
				
			}
			else if((!"".equals(org.getName()))
					&&(  org.getOrgtype()!=0 )){
				System.out.println("进到了selectByNameAndOrgType。。。。");
				list= orgRepository.selectByNameAndOrgType(org.getName(), org.getOrgtype());
			}
			else if((!"".equals(org.getName())
					&&(  org.getOrgtype()==0 ))) {
				System.out.println("进到了selectByName。。。。");
				list= orgRepository.selectByName(org.getName());
			}
			else {
				System.out.println("进到了selectByOrgtype。。。。");
				list= orgRepository.findByOrgType((org.getOrgtype()));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
