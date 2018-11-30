package com.atosenet.ga.company.mgt.service.ga.company;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.atosenet.ga.company.db.model.ga.company.CompanyLegalperson;
import com.atosenet.ga.company.db.model.ga.company.GaCompany;
import com.atosenet.ga.company.db.repos.ga.company.CompanyLegalpersonRepository;
import com.atosenet.ga.company.db.repos.ga.company.GaCompanyRepository;

@Service
@Transactional(propagation=Propagation.SUPPORTS,readOnly = true)
public class CompanyLegalpersonService {
	@Autowired
	private CompanyLegalpersonRepository companyLegalpersonRepository;
	
	@Autowired
	GaCompanyRepository gaCompanyRepository;
	
	/*
	 * 根据身份证获取人员信息
	 * */
	@Transactional(readOnly=false)
	public CompanyLegalperson findCompanyLegalpersonByCardid(String cardid)
	{
		
		return companyLegalpersonRepository.findByCardid(cardid);
	}
	/*
	 * 保存法人信息
	 * */
	@Transactional(readOnly=false)
	public void sava(CompanyLegalperson companyLegalperson,HttpServletRequest request) {
		GaCompany ga = gaCompanyRepository.findOne(companyLegalperson.getCompanyId());
		ga.setFlag((short)4);
		gaCompanyRepository.save(ga);
		companyLegalpersonRepository.save(companyLegalperson);
	}
	/*
	 * 根据id来查询信息
	 * */
	@Transactional(readOnly=false)
	public CompanyLegalperson findByUserId(String id) {
//		legalperson_id
		return companyLegalpersonRepository.findOne(Long.parseLong(id));
	}
	/*
	 * 根据companyId来查询信息
	 * */
	@Transactional(readOnly=false)
	public CompanyLegalperson findByCompanyId(String companyId) {
		
		return companyLegalpersonRepository.findByCompanyId(Integer.parseInt(companyId));
	}
}
