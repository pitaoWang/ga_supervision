package com.atosenet.ga.company.mgt.service.ga.employe;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.atosenet.ga.company.db.model.ga.employe.PhysicalRecord;
import com.atosenet.ga.company.db.model.ga.employe.PilotExperience;
import com.atosenet.ga.company.db.repos.ga.employe.PilotExperienceRepository;
/**
 * @Description:TODO
 * @time:2017年9月1日 上午8:00:48
 */
@Service
@Transactional(propagation=Propagation.SUPPORTS,readOnly = true)
public class PilotExperienceService {

	@Autowired
	PilotExperienceRepository pilotExperienceRepository;
	
	/**
	 * 增加
	 */
	@Transactional(readOnly=false)
    public void sava(PilotExperience experience){
		pilotExperienceRepository.save(experience);
    }
	
	/**
	 *删除
	 */
	@Transactional(readOnly=false)
    public void delete(Long id){
		pilotExperienceRepository.delete(id);
    }
	
	/**
	 * 查询
	 */
	@Transactional(readOnly=true)
    public List<PilotExperience> findByEmployeeId(long employeeId){
		
		return pilotExperienceRepository.findByEmployeeId(employeeId);
    }
}
