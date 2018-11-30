package com.atosenet.ga.company.mgt.service.ga.employe;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.atosenet.ga.company.db.model.ga.employe.Employee;
import com.atosenet.ga.company.db.model.ga.employe.PersonExamRecord;
import com.atosenet.ga.company.db.repos.ga.employe.EmployeeRepository;
import com.atosenet.ga.company.db.repos.ga.employe.PersonExamRecordRepository;
/**
 * @Description:TODO
 * @time:2017年9月1日 上午8:00:48
 */
@Service
@Transactional(propagation=Propagation.SUPPORTS,readOnly = true)
public class PersonExamRecordService {

	@Autowired
	PersonExamRecordRepository personExamRecordRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	/**
	 * 增加
	 */
	@Transactional(readOnly=false)
    public void save(PersonExamRecord record,HttpServletRequest request){
		PersonExamRecord employee= personExamRecordRepository.save(record);
		Employee em = employeeRepository.findOne(employee.getEmployeeId());
		em.setFlag(4);
		employeeRepository.save(em);

    }
	
	/**
	 *删除
	 */
	@Transactional(readOnly=false)
    public void delete(Long id){
		personExamRecordRepository.delete(id);
    }
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(readOnly=false)
    public void batchDelete(List<Long> ids) {
		List<PersonExamRecord> recordList = new ArrayList<PersonExamRecord>();
		for (Long id : ids) {
			PersonExamRecord record = new PersonExamRecord();
			record.setId(id);
			recordList.add(record);
		}
		personExamRecordRepository.delete(recordList);
    }
	
	/**
	 * 查询
	 */
	@Transactional(readOnly=true)
    public Page<PersonExamRecord> findByEmployeeId(long employeeId,Pageable pageable){	
		return  personExamRecordRepository.findByEmployeeId(employeeId, pageable);
    }
	
	/**
	 * 查询
	 */
	@Transactional(readOnly=true)
    public List<PersonExamRecord> findByEmployeeId(long employeeId){	
		return  personExamRecordRepository.findByEmployeeId(employeeId);
    }
	
	
	
	/**
	 * 查询
	 */
	@Transactional(readOnly=true)
    public PersonExamRecord findById(long id){	
		return  personExamRecordRepository.findOne(id);
    }

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findByEmployeeId 
	 * @param employeeId
	 * @return  
	 * @throws
	 *
	 */
	public List<PersonExamRecord> findByEmployeeId(Long employeeId) {
		// TODO Auto-generated method stub
		return personExamRecordRepository.findByEmployeeId(employeeId);
	}
}
