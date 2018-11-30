package com.atosenet.ga.company.mgt.service.ga.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.atosenet.ga.company.db.model.ga.company.FlyBase;
import com.atosenet.ga.company.db.model.ga.company.GaCompany;
import com.atosenet.ga.company.db.repos.ga.company.FlyBaseRepository;

@Service
@Transactional(propagation=Propagation.SUPPORTS,readOnly = true)
public class FlyBaseService {

	@Autowired
	FlyBaseRepository flyBaseRepository;
	
	/*
	 * 添加飞行基地信息
	 * */
	@Transactional(readOnly=false)
	public FlyBase save(FlyBase flyBase)
	{
		return flyBaseRepository.save(flyBase);
	}
	/*
	 * 获取飞行基地详细信息
	 * */
	@Transactional(readOnly=false)
	public FlyBase get(long id)
	{
		return flyBaseRepository.findOne(id);
	}
	/*
	 * 获取飞行基地列表
	 * */
	@Transactional(readOnly=false)
	public List<FlyBase> findAll()
	{
		return flyBaseRepository.findList();
	}
	
	/*
	 * 获取飞行基地列表,by对应的监管局ID
	 * */
	@Transactional(readOnly=false)
	public List<FlyBase> findBySupervisionIdList(long id)
	{
		return flyBaseRepository.findBySupervisionIdList(id);
	}
	/*
	 * 获取飞行基地列表
	 * */
	@Transactional(readOnly=false)
	public List<FlyBase> findByName(String name)
	{
		return flyBaseRepository.findByName(name);
	}
	/*
	 * 获取飞行基地列表,by对应的管理局ID
	 * */
	@Transactional(readOnly=false)
	public List<FlyBase> findByAuthorityIdList(long id)
	{
		return flyBaseRepository.findByAuthorityIdList(id);
	}
	
	/*
	 * 获取飞行基地列表,by对应的企业id
	 * */
	@Transactional(readOnly=false)
	public List<FlyBase> findflybasebycompanyid(Long id)
	{
		return flyBaseRepository.findflybasebycompanyid(id);
	}
	/*
	 * 获取飞行基地列表,by对应的主权企业id
	 * */
	@Transactional(readOnly=false)
	public List<FlyBase> findflybasebymaincompanyid(Long id)
	{
		return flyBaseRepository.findflybasebymaincompanyid(id);
	}
	
	/*
	 * 删除飞行基地详细
	 * */
	@Transactional(readOnly=false)
	public void delete(long id)
	{
		flyBaseRepository.delete(id);
		flyBaseRepository.deleteflybasebycompanyid(id);
	}
	/*
	 * 将飞行基地和公司建立联系
	 * 将起降点插入到company_flybase表
	 * */
	@Transactional(readOnly=false)
	public void savecompanylandingpoint(long companyid,long flybaseid)
	{
		flyBaseRepository.savecompanyflybase(companyid, flybaseid);
	}
	
	/**
	 * 获取公司自有的以及租用的飞行基地
	 * @param companyId
	 * @return
	 */
	public List<Map<String, Object>> getflybaseByCompanyId(long companyId){
		List<Map<String, Object>> list = new ArrayList<>();
		 List<Object[]> st = flyBaseRepository.selectflybasebycompanyid(companyId);
		  for(Object[] obj:st){
			 Map<String, Object> map = new HashMap<>();
			 map.put("flybaseid", obj[0]);
			 map.put("flybasename", obj[1]);
			 map.put("companyId", obj[2]);
			 list.add(map);
		 }
		return list;	 
    } 
}
