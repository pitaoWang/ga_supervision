/**  
 * 
 * @Title: RecordFssuserService.java 
 * @Package com.atosenet.ga.company.mgt.service.ga.employe  
 * @author xyx   
 * @date 2018年9月21日 下午4:39:43 
 * @version V1.0
 *
 */
package com.atosenet.ga.company.mgt.service.ga.employe;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.atosenet.ga.company.db.model.ga.company.GaCompany;
import com.atosenet.ga.company.db.model.ga.company.OperationBase;
import com.atosenet.ga.company.db.model.ga.company.StructureFss;
import com.atosenet.ga.company.db.model.ga.employe.Employee;
import com.atosenet.ga.company.db.model.ga.employe.RecordFssuser;
import com.atosenet.ga.company.db.repos.ga.company.GaCompanyRepository;
import com.atosenet.ga.company.db.repos.ga.company.OperationRepo;
import com.atosenet.ga.company.db.repos.ga.company.StructureFssRepository;
import com.atosenet.ga.company.db.repos.ga.employe.EmployeeRepository;
import com.atosenet.ga.company.db.repos.ga.employe.PeriodicalCheckRecordRepository;
import com.atosenet.ga.company.db.repos.ga.employe.PersonExamRecordRepository;
import com.atosenet.ga.company.db.repos.ga.employe.PersonQulificationInfoRepository;
import com.atosenet.ga.company.db.repos.ga.employe.PhysicalRecordRepository;
import com.atosenet.ga.company.db.repos.ga.employe.PilotRepository;
import com.atosenet.ga.company.db.repos.ga.employe.RecordFssuserRepository;
import com.atosenet.ga.company.db.repos.ga.employe.SkilledCheckRecordRepository;
import com.atosenet.ga.company.db.repos.ga.employe.TrainingCertificateRepository;
import com.atosenet.ga.company.db.repos.ga.employe.TrainningRecordRepository;
import com.atosenet.ga.company.db.repos.ga.employe.UpdateCheckRecordRepository;
import com.atosenet.ga.company.db.repos.ga.employe.WorkRecordRepository;
import com.atosenet.ga.company.db.repos.ga.employe.entity.DaoSpec;
import com.atosenet.ga.company.db.repos.ga.employe.entity.DaoSpecs;
import com.atosenet.ga.company.db.repos.sys.OrgRepository;

/** 
 * TODO(这里用一句话描述这个类的作用)
 * 
 * @ClassName: RecordFssuserService 
 * @author xyx 
 * @date 2018年9月21日 下午4:39:43 
 *  
 */
@Service
@Transactional(propagation=Propagation.SUPPORTS,readOnly = true)
public class RecordFssuserService {

	@Autowired
	RecordFssuserRepository recordFssuserRepository;
	@Autowired
	OperationRepo operationRepo;
	@Autowired
	GaCompanyRepository gaCompanyRepository;
	@Autowired
	StructureFssRepository structureFssReps;
	
	
	/**
	 * @param fssId  
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findEmployeeByInfo 
	 * @param companyId
	 * @param userName
	 * @param jobTitle
	 * @param techpos
	 * @param homeMobile
	 * @param turnOut
	 * @param contract
	 * @param deptname
	 * @param offset
	 * @param pageSize
	 * @return  
	 * @throws
	 *
	 */
	@Transactional(readOnly=false)
	public List<RecordFssuser> findRecordFssuserByInfo(Long companyId, String name, String type, 
			String tel, String fssId, Pageable pageable) {
		List<StructureFss> structureFssList = new ArrayList<>();
		if(fssId==null||fssId.equals("")){
			if(type.equals("2")){
				structureFssList = structureFssReps.findBySupervisionBureauId(companyId);
			}else{
				structureFssList = structureFssReps.findByAdministrationId(companyId);
			}
			
			for (StructureFss structureFss : structureFssList) {
				
				fssId+=structureFss.getId()+",";
			}
			fssId = fssId.substring(0,fssId.length());
		}
		
		Specification<RecordFssuser> spec = DaoSpecs.getSpec(fssId,name,tel);
		
		List<RecordFssuser> list = recordFssuserRepository.findAll(spec,pageable).getContent();
		List<StructureFss> structureList = structureFssReps.findList();
		long total = recordFssuserRepository.findAll(spec,pageable).getTotalElements();
		for (RecordFssuser recordFssuser : list) {
			recordFssuser.setTotal(total);
			for(StructureFss structureFss : structureList){
				if(recordFssuser.getFssId()!=null){
					if(structureFss.getId()==recordFssuser.getFssId()){
						recordFssuser.setFssName(structureFss.getName());
					}
				}
				
			}
		}
		return  list;
	}


	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findStructureFssByInfo 
	 * @param companyId
	 * @param type
	 * @return  
	 * @throws
	 *
	 */
	public List<StructureFss> findStructureFssByInfo(Long companyId, String type) {
		List<StructureFss> structureFssList = new ArrayList<>();
			if(type.equals("2")){
				structureFssList = structureFssReps.findBySupervisionBureauId(companyId);
			}else{
				structureFssList = structureFssReps.findByAdministrationId(companyId);
			}
			
		return structureFssList;
	}


	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findRecordFssuserByInfos 
	 * @param companyId
	 * @param name
	 * @param type
	 * @param tel
	 * @param fssId
	 * @return  
	 * @throws
	 *
	 */
	public List<RecordFssuser> findRecordFssuserByInfos(Long companyId, String name, String type, String tel,
			String fssId) {
		List<StructureFss> structureFssList = new ArrayList<>();
		if(fssId==null||fssId.equals("")){
			if(type.equals("2")){
				structureFssList = structureFssReps.findBySupervisionBureauId(companyId);
			}else{
				structureFssList = structureFssReps.findByAdministrationId(companyId);
			}
			
			for (StructureFss structureFss : structureFssList) {
				
				fssId+=structureFss.getId()+",";
			}
			fssId = fssId.substring(0,fssId.length());
		}
		
		Specification<RecordFssuser> spec = DaoSpecs.getSpec(fssId,name,tel);
		
		List<RecordFssuser> list = recordFssuserRepository.findAll(spec);
		List<StructureFss> structureList = structureFssReps.findList();
		
		for (RecordFssuser recordFssuser : list) {
			for(StructureFss structureFss : structureList){
				if(recordFssuser.getFssId()!=null){
					if(structureFss.getId()==recordFssuser.getFssId()){
						recordFssuser.setFssName(structureFss.getName());
					}
				}
				
			}
		}
		return  list;
	}

}
