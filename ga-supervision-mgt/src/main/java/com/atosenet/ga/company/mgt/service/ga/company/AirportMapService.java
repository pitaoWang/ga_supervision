package com.atosenet.ga.company.mgt.service.ga.company;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.atosenet.ga.company.db.model.ga.company.Aircraft;
import com.atosenet.ga.company.db.model.ga.company.Airport;
import com.atosenet.ga.company.db.model.ga.company.AirportMap;
import com.atosenet.ga.company.db.repos.ga.company.AirportMapRepository;
import com.atosenet.ga.company.db.repos.ga.company.AirportRepository;
/**
 * 机场
 * @Description:TODO
 * @author:瞿旺伟
 * @time:2017年9月1日 上午8:00:48
 */
@Service
@Transactional(propagation=Propagation.SUPPORTS,readOnly = true)
public class AirportMapService {

	@Autowired
	AirportMapRepository airportMapRepository;
	
	/**
	 * 增加机场的操作
	 */
	@Transactional(readOnly=false)
    public AirportMap sava(AirportMap airportMap){
		 return airportMapRepository.save(airportMap);
    }
	
	/**
	 * 查询机场图详情
	 */
	@Transactional(readOnly=false)
    public AirportMap getId(long id){
		 return airportMapRepository.findOne(id);
    }
	/**
	 * 获取列表
	 */
	@Transactional(readOnly=false)
    public List<AirportMap> findAll(){
		 return airportMapRepository.findList();
    }
	/**
	 * 获取机场航图通过机场ID
	 */
	@Transactional(readOnly=false)
    public AirportMap getAirpMapByAirpId(Long id){
		 return airportMapRepository.findByAirportId(id);
    }
	
	/**
	 * 删除机场图
	 */
	@Transactional(readOnly=false)
    public void delete(Long id){
		airportMapRepository.delete(id);
    }
}
