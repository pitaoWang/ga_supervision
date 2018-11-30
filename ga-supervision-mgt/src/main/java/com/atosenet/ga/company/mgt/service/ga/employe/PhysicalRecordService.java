package com.atosenet.ga.company.mgt.service.ga.employe;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.atosenet.ga.company.db.model.ga.employe.Employee;
import com.atosenet.ga.company.db.model.ga.employe.PhysicalRecord;
import com.atosenet.ga.company.db.repos.ga.employe.EmployeeRepository;
import com.atosenet.ga.company.db.repos.ga.employe.PhysicalRecordRepository;
/**
 * @Description:TODO
 * @time:2017年9月1日 上午8:00:48
 */
@Service
@Transactional(propagation=Propagation.SUPPORTS,readOnly = true)
public class PhysicalRecordService {

	@Autowired
	PhysicalRecordRepository physicalRecordRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	/**
	 * 增加
	 */
	@Transactional(readOnly=false)
    public void save(PhysicalRecord record,HttpServletRequest request){
		PhysicalRecord employee= physicalRecordRepository.save(record);
		Employee em = employeeRepository.findOne(employee.getEmployeeId());
		em.setFlag(4);
		employeeRepository.save(em);
    }
	
	/**
	 *删除
	 */
	@Transactional(readOnly=false)
    public void delete(Long id){
		physicalRecordRepository.delete(id);
    }
	
	/**
	 *删除
	 */
	@Transactional(readOnly=false)
    public void batchDelete(List<PhysicalRecord> entities){
		physicalRecordRepository.delete(entities);
    }
	
	/**
	 * 查询
	 */
	@Transactional(readOnly=true)
    public Page<PhysicalRecord> findByEmployeeId(long employeeId, Pageable pageable){	
		return  physicalRecordRepository.findByEmployeeId(employeeId, pageable);
    }
	
	@Transactional(readOnly=true)
    public List<PhysicalRecord> findByEmployeeId(long employeeId){	
		return  physicalRecordRepository.findAllByEmployeeId(employeeId);
    }
	
	
	
	/**
	 * 查询
	 */
	@Transactional(readOnly=true)
    public PhysicalRecord findById(long id){	
		return  physicalRecordRepository.findOne(id);
    }

	
}
