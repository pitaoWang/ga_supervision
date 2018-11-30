package com.atosenet.ga.company.mgt.service.ga.company;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.atosenet.ga.company.db.model.ga.company.MmisIllegalFlight;
import com.atosenet.ga.company.db.repos.ga.company.MmisIllegalFlightRepository;

@Service
@Transactional(propagation=Propagation.SUPPORTS,readOnly = true)
public class MmisIllegalFlightService {
	
	@Autowired
	MmisIllegalFlightRepository mmisIllegalFlightRep;
	
	/**
	 * 根据企业ids查询违规飞行记录
	 * @param supervisionId
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<MmisIllegalFlight> findByCompanyId(String[] companyIds){
		return mmisIllegalFlightRep.findByCompanyId(companyIds);
	}
	
	/**
	 * 根据企业ids和企业id查询违规飞行记录
	 * @param companyIds
	 * @param companyId
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<MmisIllegalFlight> findByCompanyIdsAndId(String[] companyIds,Long companyId){
		return mmisIllegalFlightRep.findByCompanyIdsAndId(companyIds, companyId);
	}
	
	/**
	 * 根据企业ids和航空器注册号查询违规飞行记录
	 * @param companyIds
	 * @param aircraftId
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<MmisIllegalFlight> findByCompanyIdsAndAirRegNo(String[] companyIds,String aircraftId){
		return mmisIllegalFlightRep.findByCompanyIdsAndAirRegNo(companyIds, aircraftId);
	}
	
	/**
	 * 根据企业ids和违规时间查询违规飞行记录
	 * @param companyIds
	 * @param starttime
	 * @param endtime
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<MmisIllegalFlight> findByCompanyIdsAndDate(String[] companyIds,String starttime,String endtime){
		return mmisIllegalFlightRep.findByCompanyIdsAndDate(companyIds, starttime, endtime);
	}
	
	/**
	 * 根据企业ids和企业id和航空注册号查询违规飞行记录
	 * @param companyIds
	 * @param companyId
	 * @param aircraftId
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<MmisIllegalFlight> findByCompanyIdsAndIdAndAirRegNo(String[] companyIds,Long companyId,String aircraftId){
		return mmisIllegalFlightRep.findByCompanyIdsAndIdAndAirRegNo(companyIds, companyId, aircraftId);
	}
	
	/**
	 * 根据企业ids和企业id和违规时间查询违规飞行记录
	 * @param companyIds
	 * @param companyId
	 * @param starttime
	 * @param endtime
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<MmisIllegalFlight> findByCompanyIdsAndIdAndDate(String[] companyIds,Long companyId,String starttime,String endtime){
		return mmisIllegalFlightRep.findByCompanyIdsAndIdAndDate(companyIds, companyId, starttime, endtime);
	}
	
	/**
	 * 根据企业ids和航空器注册号和违规时间查询违规飞行记录
	 * @param companyIds
	 * @param aircraftId
	 * @param starttime
	 * @param endtime
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<MmisIllegalFlight> findByCompanyIdsAndAirRegNoAndDate(String[] companyIds,String aircraftId,String starttime,String endtime){
		return mmisIllegalFlightRep.findByCompanyIdsAndAirRegNoAndDate(companyIds, aircraftId, starttime, endtime);
	}
	
	/**
	 * 根据所有条件查询违规飞行记录
	 * @param companyIds
	 * @param companyId
	 * @param aircraftId
	 * @param starttime
	 * @param endtime
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<MmisIllegalFlight> findByAllCase(String[] companyIds,Long companyId,String aircraftId,String starttime,String endtime){
		return mmisIllegalFlightRep.findByAllCase(companyIds, companyId, aircraftId, starttime, endtime);
	}
}
