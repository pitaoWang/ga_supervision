package com.atosenet.ga.company.mgt.service.ga.company;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.atosenet.ga.company.db.model.ga.company.CompanyBusinessType;
import com.atosenet.ga.company.db.repos.ga.company.CompanyBusinessTypeRepository;

@Service
@Transactional(propagation=Propagation.SUPPORTS,readOnly = true)
public class CompanyBusinessTypeService {

	@Autowired
	private CompanyBusinessTypeRepository companyBusinessTypeRepository;
	
	/*
	 * 添加增加经营范围
	 * */
	@Transactional(readOnly=false)
	public void save(CompanyBusinessType com)
	{
		companyBusinessTypeRepository.save(com);
	}
	
	@Transactional(readOnly=false)
	public void deleteByCompanyId(int id)
	{
		companyBusinessTypeRepository.deleteByCompanyId(id);
	}
	
	@Transactional(readOnly=false)
	public List<CompanyBusinessType> findListByBusinessType(short type)
	{
		return companyBusinessTypeRepository.findListbybusinessType(type);
	}
	@Transactional(readOnly=false)
	public CompanyBusinessType get(long id)
	{
		return companyBusinessTypeRepository.findOne(id);
	}
}
