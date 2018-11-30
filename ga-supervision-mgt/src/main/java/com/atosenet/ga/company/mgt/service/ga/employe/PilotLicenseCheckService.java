package com.atosenet.ga.company.mgt.service.ga.employe;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.atosenet.ga.company.db.model.ga.employe.PilotLicenseCheckRecord;
import com.atosenet.ga.company.db.repos.ga.employe.PilotLicenseCheckRepository;
/**
 * @Description:TODO
 * @time:2017年9月1日 上午8:00:48
 */
@Service
@Transactional(propagation=Propagation.SUPPORTS,readOnly = true)
public class PilotLicenseCheckService {

	@Autowired
	PilotLicenseCheckRepository pilotLicenseCheckRepository;
	
	/**
	 * 增加
	 */
	@Transactional(readOnly=false)
    public PilotLicenseCheckRecord sava(PilotLicenseCheckRecord record){
		return pilotLicenseCheckRepository.save(record);
    }
	
	/**
	 * 删除
	 */
	@Transactional(readOnly=false)
    public Long delete(Long id){
		try
		{
			pilotLicenseCheckRepository.delete(id);
			
			return id;
		}
		catch (Exception e) {
			return -1L;
		}
		
    }
	
	/**
	 * 查询
	 */
	@Transactional(readOnly=true)
    public List<PilotLicenseCheckRecord> findByLicenseId(String licenseId){
		return  pilotLicenseCheckRepository.findByLicenseId(Long.parseLong(licenseId));
    }
	
	@Transactional(readOnly=false)
	public void deleteBatch(List<Long> ids) {
		for(Long id: ids){
			pilotLicenseCheckRepository.delete(id);
		}
		
	}
	
	@Transactional(readOnly=true)
	public Page<PilotLicenseCheckRecord> findByPage(Long id, Pageable pageable) {
		return pilotLicenseCheckRepository.findByLicenseId(id,pageable);
	}
	
	@Transactional(readOnly=true)
	public PilotLicenseCheckRecord findById(long id) {
		// TODO Auto-generated method stub
		return this.pilotLicenseCheckRepository.findOne(id);
	}
	
	@Transactional(readOnly=true)
	public List<PilotLicenseCheckRecord> findAll() {
		// TODO Auto-generated method stub
		return this.pilotLicenseCheckRepository.findAll();
	}
}
