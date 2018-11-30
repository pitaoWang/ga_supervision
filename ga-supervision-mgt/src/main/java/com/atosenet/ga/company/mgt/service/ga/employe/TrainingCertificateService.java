package com.atosenet.ga.company.mgt.service.ga.employe;


import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.atosenet.ga.company.db.model.ga.employe.Employee;
import com.atosenet.ga.company.db.model.ga.employe.TrainingCertificate;
import com.atosenet.ga.company.db.repos.ga.employe.EmployeeRepository;
import com.atosenet.ga.company.db.repos.ga.employe.TrainingCertificateRepository;

@Service
@Transactional(propagation=Propagation.SUPPORTS,readOnly = true)
public class TrainingCertificateService {
	@Autowired
	TrainingCertificateRepository trainingCertificateRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	/**
	 * 增加
	 */
	@Transactional(readOnly=false)
    public void sava(TrainingCertificate trainingCertificate,HttpServletRequest request){
		if( trainingCertificate.getCreatetime()==null) {
			trainingCertificate.setCreatetime(new Date());
		}
		else {
			trainingCertificate.setUpdatetime(new Date());
		}
		
		TrainingCertificate employee= trainingCertificateRepository.save(trainingCertificate);
		Employee em = employeeRepository.findOne(employee.getEmployeeId());
		em.setFlag(4);
		employeeRepository.save(em);
    }
	
	/**
	 *删除
	 */
	@Transactional(readOnly=false)
    public void delete(Long id){
		trainingCertificateRepository.delete(id);
    }
	
	@Transactional(readOnly=false)
    public List<TrainingCertificate> findByEmployeeId(long employeeId){
		
		return trainingCertificateRepository.findByEmployeeId(employeeId);
    }
	
	@Transactional(readOnly=false)
    public TrainingCertificate getTrainingCertificateRecordById(long id){
		
		return trainingCertificateRepository.findOne(id);
    }
	
}
