package com.atosenet.ga.company.mgt.service.ga.employe;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.atosenet.ga.company.db.model.ga.employe.Employee;
import com.atosenet.ga.company.db.model.ga.employe.TrainningRecord;
import com.atosenet.ga.company.db.repos.ga.employe.EmployeeRepository;
import com.atosenet.ga.company.db.repos.ga.employe.TrainningRecordRepository;
/**
 * @Description:TODO
 * @time:2017年9月1日 上午8:00:48
 */
@Service
@Transactional(propagation=Propagation.SUPPORTS,readOnly = true)
public class TrainningRecordService {

	@Autowired
	TrainningRecordRepository trainningRecordRepository;

	@Autowired
	EmployeeRepository employeeRepository;
	
	/**
	 * 增加
	 */
	@Transactional(readOnly=false)
    public void sava(TrainningRecord record,HttpServletRequest request){
		
		if( record.getCreateTime()==null) {
			record.setCreateTime(new Date());
		}
		else {
			record.setUpdateTime(new Date());
		}
		TrainningRecord employee =trainningRecordRepository.save(record);
		Employee em = employeeRepository.findOne(employee.getEmployeeId());
		em.setFlag(4);
		employeeRepository.save(em);
    }
	
	/**
	 *删除
	 */
	@Transactional(readOnly=false)
    public void delete(Long id){
		trainningRecordRepository.delete(id);
    }
	
	@Transactional(readOnly=false)
    public List<TrainningRecord> findByEmployeeId(long employeeId){
		
		return trainningRecordRepository.findByEmployeeId(employeeId);
    }
	
	@Transactional(readOnly=false)
    public TrainningRecord getTrainingRecordById(long id){
		
		return trainningRecordRepository.findOne(id);
    }
	
	@Transactional(readOnly=true)
	public Page<TrainningRecord> findPageByEmployeeId(long employeeId, Pageable pageable) {
		// TODO Auto-generated method stub
		return trainningRecordRepository.findByEmployeeId(employeeId,pageable);
	}
	
	@Transactional(readOnly=false)
	public void deleteBatch(List<Long> ids) {
		for(Long id : ids){
			this.trainningRecordRepository.delete(id);
		}
		
	}
	
}
