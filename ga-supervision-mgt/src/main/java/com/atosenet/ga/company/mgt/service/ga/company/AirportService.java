package com.atosenet.ga.company.mgt.service.ga.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.atosenet.ga.company.db.model.ga.company.Airport;
import com.atosenet.ga.company.db.model.ga.company.GaCompany;
import com.atosenet.ga.company.db.model.sys.Org;
import com.atosenet.ga.company.db.projection.company.AirportSimple;
import com.atosenet.ga.company.db.repos.ga.company.AirportRepository;

/**
 * 机场
 * 
 * @Description:TODO
 * @author:瞿旺伟
 * @time:2017年9月1日 上午8:00:48
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class AirportService {

	@Autowired
	AirportRepository airportRepository;
	@Autowired
	FlightRunwayService flightRunwayService;

	@Autowired
	GaCompanyService gaCompanyService;
	
	@Autowired
	OrgService orgService;
	/**
	 * 增加机场的操作
	 */
	@Transactional(readOnly = false)
	public Airport sava(Airport airport) {
		return airportRepository.save(airport);
	}

	/**
	 * 删除机场delete
	 */
	@Transactional(readOnly = false)
	public void delete(Long id) {
		flightRunwayService.deleteByAirport(id);
		airportRepository.delete(id);
		airportRepository.deleteairportbycompanyid(id);
	}

	/**
	 * 通过省市获得机场
	 */
	public List<Airport> findListbypc(String province, String city) {
		return airportRepository.findListbypc(province, city);

	}

	/**
	 * 通过省市获得机场
	 */
	public List<Airport> getByairportName(String airportName) {
		return airportRepository.getByairportName(airportName);

	}

	/**
	 * 
	 * 
	 * @param pageable
	 * @return 通过机场名称,和监管局id查询机场
	 */
	public Page<Map<String,Object>> findAircraftBysupervise(String airportName, long companyid, Pageable pageable) {
//		return airportRepository.selectByAirportNameAndSupervise(airportName, companyid, pageable);
		Page<Object[]> listobj = airportRepository.selectByAirportNameAndSupervise(airportName, companyid, pageable);
		List<Map<String,Object>> list=new ArrayList<>();
		Map<String,Object> map = null;
		for(Object[] obj:listobj)
		{
			map = new HashMap<String,Object>();
			map.put("airportId", obj[0]);
			map.put("airportName", obj[1]);
			map.put("flag", obj[2]);
			list.add(map);
		}
		Page<Map<String,Object>> pagemap = new PageImpl(list,pageable,listobj.getTotalElements());
		return pagemap;
	}

	/**
	 * 
	 * 
	 * @param pageable
	 * @return 通过机场名称,和管理局id查询机场
	 */
	public Page<Map<String,Object>> findAircraftByauthority(String airportName, long companyid, Pageable pageable) {
		Page<Object[]> listobj = airportRepository.selectByAirportNameAuthority(airportName, companyid, pageable);
		List<Map<String,Object>> list=new ArrayList<>();
		Map<String,Object> map = null;
		for(Object[] obj:listobj)
		{
			map = new HashMap<String,Object>();
			map.put("airportId", obj[0]);
			map.put("airportName", obj[1]);
			map.put("flag", obj[2]);
			list.add(map);
		}
		Page<Map<String,Object>> pagemap = new PageImpl(list,pageable,listobj.getTotalElements());
		return pagemap;
	}

	/**
	 * 通过企业id获得机场列表
	 */
	public List<Airport> getlistbycompanyid(Long id) {
		return airportRepository.getlistbycompanyid(id);

	}

	/*
	 * 将机场和公司建立联系 将机场插入到company_airport表
	 */
	@Transactional(readOnly = false)
	public void savecompanylandingpoint(long companyid, long airportid) {
		airportRepository.deletecompanyairport(companyid, airportid);
		airportRepository.savecompanyairport(companyid, airportid);
	}

	/**
	 * 获取公司自有的以及租用的机场
	 * 
	 * @param companyId
	 * @return
	 */
	public List<Map<String, Object>> getairportByCompanyId(long companyId) {
		List<Map<String, Object>> list = new ArrayList<>();
		List<Object[]> st = airportRepository.selectAirportbycompanyid(companyId);
		for (Object[] obj : st) {
			Map<String, Object> map = new HashMap<>();
			map.put("airportId", obj[0]);
			map.put("airportName", obj[1]);
			map.put("companyId", obj[2]);
			map.put("grade", obj[3]);
			map.put("isavailable", obj[4]);
			list.add(map);
		}
		return list;
	}

	/**
	 * 获取公司自有的机场
	 * 
	 * @param companyId
	 * @return
	 */
	public List<Airport> getairportByMainCompanyId(long companyId) {
		List<Airport> list = airportRepository.selectAirportbymaincompanyid(companyId);
		return list;
	}
	/**
	 * 
	 */
	@Transactional
	public Map<String, Object> findMapOne(Long id) {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			Airport air = airportRepository.findOne(id);
			GaCompany com = gaCompanyService.get(air.getCompanyId());
			Org aut=orgService.findOrgById(air.getAuthorityId());
			Org sup=orgService.findOrgById(air.getSuperviseOrgid());
			if(com!=null)
			{
				map.put("companyName", com.getName());
			}
			if(aut!=null)
			{
				map.put("authorityName",aut.getName());
			}
			if(sup!=null)
			{
				map.put("superviseOrgName", sup.getName());
			}
			map.put("airportId", air.getAirportId());
			map.put("airportName", air.getAirportName());
			map.put("companyId", air.getCompanyId());
			map.put("grade", air.getGrade());
			if(air.getIswater()!=null)
			{
				map.put("iswater", air.getIswater()==1? "是":"否");
			}else
			{
				map.put("iswater", "");
			}
			
			map.put("province", air.getProvince());
			map.put("city", air.getCity());
			map.put("airporttype", air.getAirporttype());
			map.put("isavailable", air.getIsavailable()==0? "是":"否");
			map.put("fireGrade", air.getFireGrade());
			try{
				map.put("licenseBegindate",air.getLicenseBegindate());
				map.put("licenseEnddate",air.getLicenseEnddate());
			}catch(Exception e){
				map.put("licenseBegindate","");
				map.put("licenseEnddate","");
				e.printStackTrace();
			}
			String str = air.getLongitude();
			str=str.replaceFirst("-"," 度 ");
			str=str.replaceFirst("-"," 分 ");
			str=str+" 秒";
			map.put("longitude", str);
			str = air.getLatitude();
			str=str.replaceFirst("-"," 度 ");
			str=str.replaceFirst("-"," 分 ");
			str=str+" 秒";
			map.put("latitude", str);
			map.put("property", air.getProperty());
			map.put("height", air.getHeight());
			map.put("entNumber", air.getEntNumber());
			map.put("orgName", air.getOrgName());
			map.put("icaoNumber", air.getIcaoNumber());
			map.put("iatanumber", air.getIatanumber());
			map.put("address", air.getAddress());
			map.put("serviceTel", air.getServiceTel());
			map.put("tel", air.getTel());
			map.put("superviseOrgid", air.getSuperviseOrgid());
			map.put("authorityId", air.getAuthorityId());
			map.put("warZone", air.getWarZone());
			map.put("towerMainFrequency", air.getTowerMainFrequency());
			map.put("towerReserveFrequency", air.getTowerReserveFrequency());
			map.put("mainFrequency",air.getMainFrequency());
			map.put("reserveFrequency", air.getReserveFrequency());
			if(air.getIslandingDevice()!=null)
			{
				map.put("islandingDevice", air.getIslandingDevice()==1?"是":"否");
			}else
			{
				map.put("islandingDevice", "");
			}
			
			map.put("ndbFrequency", air.getNdbFrequency());
			map.put("vorDemFrequency", air.getVorDemFrequency());
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return map;
	}

	/**
	 * 获取公司自有的以及租用的机场
	 * 
	 * @param companyId
	 * @return
	 */
	public List<AirportSimple> getairportAll() {
		List<AirportSimple> list = new ArrayList<>();
		List<Object[]> st = airportRepository.findAllAiport();
		for (Object[] obj : st) {
			AirportSimple aipo = new AirportSimple();
			aipo.setAirportId(obj[0] + "");
			aipo.setAirportName(obj[1] + "");
			aipo.setCompanyId(obj[2] + "");
			aipo.setName(obj[3] + "");
			list.add(aipo);
		}
		return list;
	}

	public List<Airport> getlistbyprovinceandcity(String province, String city) {
		return airportRepository.findByProvinceAndCity(province, city);
	}
}
