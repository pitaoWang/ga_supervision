package com.atosenet.ga.company.mgt.service.ga.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.atosenet.ga.company.db.model.ga.company.LandingPoint;
import com.atosenet.ga.company.db.repos.ga.company.LandingPointRepository;

@Service
@Transactional(propagation=Propagation.SUPPORTS,readOnly = true)
public class LandingPointService {

	@Autowired
	LandingPointRepository landingPointRepository;
	
	/*
	 * 添加起降点
	 * */
	@Transactional(readOnly=false)
	public LandingPoint save(LandingPoint landingPoint)
	{
		return landingPointRepository.save(landingPoint);
	}
	/*
	 * 获取起降点详细信息
	 * */
	@Transactional(readOnly=false)
	public LandingPoint get(long id)
	{
		return landingPointRepository.findOne(id);
	}
	/*
	 * 获取起降点列表
	 * */
	@Transactional(readOnly=false)
	public List<LandingPoint> findAll()
	{
		return landingPointRepository.findList();
	}
	/*
	 * 获取起降点列表
	 * */
	@Transactional(readOnly=false)
	public List<LandingPoint> findListByName(String name)
	{
		return landingPointRepository.findListByName(name);
	}
	/*
	 * 删除起降点详细信息
	 * */
	@Transactional(readOnly=false)
	public void delete(long id)
	{
		landingPointRepository.delete(id);
		landingPointRepository.deletecompanylandingpoint(id);
	}
	/*
	 * 将起降点和公司建立联系
	 * 将起降点插入到company_landing_point表
	 * */
	@Transactional(readOnly=false)
	public void savecompanylandingpoint(long companyid,long landingpointid)
	{
		landingPointRepository.savecompanylandingpoint(companyid, landingpointid);
	}
	/*
	 * 根据企业id获得起降点列表
	 * */
	@Transactional(readOnly=false)
	public List<LandingPoint> getlistbycompanyid(long companyid){
		return landingPointRepository.getlistbycompanyid(companyid);
	}
	/**
	 * 根据主权企业id获得起降点列表
	 * */
	@Transactional(readOnly=false)
	public List<LandingPoint> getlistbymaincompanyid(long companyid){
		
		System.out.println(landingPointRepository.getlistbymaincompanyid(companyid));
		
		return landingPointRepository.getlistbymaincompanyid(companyid);
	}
	//
	/**
	 * 获取公司自有的以及租用的起降点
	 * @param companyId
	 * @return
	 */
	public List<Map<String, Object>> getlandingPointByCompanyId(long companyId){
		List<Map<String, Object>> list = new ArrayList<>();
		 List<Object[]> st = landingPointRepository.selectlistbycompanyid(companyId);
		  for(Object[] obj:st){
			 Map<String, Object> map = new HashMap<>();
			 map.put("id", obj[0]);
			 map.put("name", obj[1]);
			 map.put("companyId", obj[2]);
			 map.put("isavailable", obj[3]);
			 map.put("landingGrade", obj[4]);
			 list.add(map);
		 }
		return list;	 
    } 
}
