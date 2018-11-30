package com.atosenet.ga.company.mgt.service.ga.company;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.atosenet.ga.company.db.model.ga.company.Aircraft;
import com.atosenet.ga.company.db.model.ga.company.GaCompany;
import com.atosenet.ga.company.db.model.ga.company.OperationBase;
import com.atosenet.ga.company.db.model.ga.company.PlanType;

import com.atosenet.ga.company.db.model.sys.Org;
import com.atosenet.ga.company.db.repos.ga.company.AircraftRepository;
import com.atosenet.ga.company.db.repos.ga.company.CheckRecordRepository;
import com.atosenet.ga.company.db.repos.ga.company.FlySimulateRepository;
import com.atosenet.ga.company.db.repos.ga.company.GaCompanyRepository;
import com.atosenet.ga.company.db.repos.ga.company.PlanTypeRepository;
import com.atosenet.ga.company.db.repos.ga.company.entity.DaoSpec;
import com.atosenet.ga.company.db.repos.sys.OrgRepository;
import com.atosenet.ga.company.mgt.service.ga.company.dto.AircraftCheckDTO;
import com.atosenet.ga.sys.dao.DepartmentDao;
import com.atosenet.ga.sys.entity.Department;

@Service
@Transactional(propagation=Propagation.SUPPORTS,readOnly = true)
public class AircraftService {
	@Autowired
	CheckRecordRepository checkRecordRepository;
	@Autowired
	AircraftRepository aircraftRepository;
	@Autowired
	PlanTypeRepository planTypeResitory;
	@Autowired
	GaCompanyRepository gaCompanyRepository;
    @Autowired
    private DepartmentDao departmentDao;
    @Autowired
	private FlySimulateRepository FSRepository;

	@Autowired
	OrgRepository orgRepository;
	/**
	 * 增加航空器的操作
	 */
	@Transactional(readOnly=false)
    public void sava(Aircraft aircraft,HttpServletRequest request){
		aircraft.setFlag(4);
		aircraftRepository.save(aircraft);
    }
	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findByCompanyId 
	 * @param companyId
	 * @return  
	 * @throws
	 *
	 */
	public List<Aircraft> findByCompanyId(String companyId) {
		// TODO Auto-generated method stub
		return aircraftRepository.findByCompanyId(companyId);
	}
	/**
	 *删除航空器delete
	 */
	@Transactional(readOnly=false)
    public void delete(Long id){
		aircraftRepository.delete(id);
		checkRecordRepository.deleteByAircaftId(id);
    }
	
	public Page<Aircraft> findAircraftByCompany(String companyIds,Pageable pageable){
		
		Page<Aircraft> list = aircraftRepository.selectBycompanyId(companyIds, pageable);	
		
		
		List<GaCompany> companyList = gaCompanyRepository.findList();
		
		List<PlanType> planTypeList = planTypeResitory.findAll();
		
		List<Department> departmentList = departmentDao.findAll();
		
		for (Aircraft aircraft : list) {
			for(GaCompany gaCompany : companyList){
				if(aircraft.getCompanyId()!=null){
					if(gaCompany.getCompanyId()==aircraft.getCompanyId().intValue()){
						aircraft.setCompanyName(gaCompany.getName());
						aircraft.setSupervisionName(gaCompany.getSupervisionName());
						aircraft.setAuthorityName(gaCompany.getAuthorityName());
					}
				}
				
			}
			if(aircraft.getPlanTypeId()!=null){
			for(PlanType planType : planTypeList){
				if(planType.getId()==aircraft.getPlanTypeId()){
					aircraft.setPlanTypeName(planType.getTypeName());
				}
			}
		}
			if(aircraft.getSuperId()!=null){
				for(Department department : departmentList){
					if(department.getId()==aircraft.getSuperId()){
						aircraft.setSupervisionName(department.getTitle());
						
					}
				}
			}
			if(aircraft.getManagerId()!=null){
				for(Department department : departmentList){
					if(department.getId()==aircraft.getManagerId()){
						aircraft.setAuthorityName(department.getTitle());
					}
				}
			}

  }
		
		
		return list;
	}
	/**
	 * 
	 * @param companyId
	 * @param pageable
	 * @return
	 * 通过企业名称或者页面查询信息获取飞行器信息
	 */
	public Page<Aircraft> findAircraftByCompanyAndinfo(String companyids,String info,Pageable pageable){
		return aircraftRepository.selectByCompanyAndinfo(companyids,info,pageable);	
	}
	
	
//	/**
//	 * 
//	 * @param companyId
//	 * @param pageable
//	 * @return
//	 * 通过企业ids或者页面查询信息获取飞行器信息
//	 */
//	public Page<Aircraft> findAircraftBycompanyIds(String info,String compids,Pageable pageable){
//		Page<Aircraft> page=null;
//		if(info==null||"".equals(info)){
//		    page=aircraftRepository.selectBycompanyIdsAll(compids, pageable);
//		}else{
//			page=aircraftRepository.selectBycompanyIds(info,compids, pageable);
//		}
//		return 	page;
//	}
	
//	/**
//	 * 根据企业ID获取和航空器名称获取航空器列表
//	 * @param companyId
//	 * @return
//	 */
//	public Page<Aircraft> findAircraftByCompanyAndName(Long companyId,Pageable pageable,String name){
//		//return aircraftRepository.selectBycompanyIdAndName(companyId, pageable,name);	
//		return aircraftRepository.selectBycompanyId(companyId, pageable);
//	}
	
	@Transactional(readOnly=false)
	public List<Aircraft> searchAircrafts() {
		return aircraftRepository.findList();
	}
	
	/**
	 * 根据企业ID获取同型号航空器数量
	 * @param companyId
	 * @return
	 */
	public List<Object> findModelByCompanyids(String companyIds){	
		return aircraftRepository.findModelByCompanyids(companyIds);
	}
	
	/**
	 * 根据企业ID获取航空器类别及数量
	 * @param companyId
	 * @return
	 */
	public List<Object> getAircraftClassNumByCompany(String companyIds){
		return aircraftRepository.getAircraftClassNum(companyIds);
	}
	
	@Transactional(readOnly=false)
	public List<Aircraft> searchAircraftsByCompanyName(String companyName) {
		// TODO Auto-generated method stub
		return aircraftRepository.findAircraftList(companyName);
	}
	
	/**
	 * 根据登记号查找航空器信息
	 * @param registernumber
	 * @return
	 */
	public List<Aircraft> isExistAircraft(String registernumber){	
		return aircraftRepository.isExistAircraft(registernumber);
	}
	
	public List<Object> airbelongcompanyname(){	
		return aircraftRepository.airbelongcompanyname();
	}
	
	/**
	 * 
	 * TODO(根据企业ID获取该企业所有的航空器列表) 
	 *
	 * @Title: findByCompanyId 
	 * @param companyId
	 * @return  
	 * @throws
	 *
	 */
	public List<Aircraft> findByCompanyId(Integer companyId){
		return aircraftRepository.findByCompanyId(companyId);
	}
	
	/**
	 * 
	 * TODO(根据航空器注册号获取航空器基本信息) 
	 *
	 * @Title: findByRegNumber 
	 * @param regNumber
	 * @return  
	 * @throws
	 *
	 */
	public Aircraft findByRegNumber(String regNumber){
        Aircraft list = aircraftRepository.findByRegNumber(regNumber);	
		
		
		List<GaCompany> companyList = gaCompanyRepository.findList();
		
		List<PlanType> planTypeList = planTypeResitory.findAll();
		
		
			for(GaCompany gaCompany : companyList){
				if(list.getCompanyId()!=null){
					if(gaCompany.getCompanyId()==list.getCompanyId().intValue()){
						list.setCompanyName(gaCompany.getName());
						list.setSupervisionName(gaCompany.getSupervisionName());
						list.setAuthorityName(gaCompany.getAuthorityName());
					}
				}
				
			}
			if(list.getPlanTypeId()!=null){
			for(PlanType planType : planTypeList){
				if(planType.getId()==list.getPlanTypeId()){
					list.setPlanTypeName(planType.getTypeName());
				}
			}
			}
			
		
		
		
		return list;
		
	}
	
	/**
	 * 
	 * TODO(根据监管局或管理局ID返回航空器列表) 
	 *
	 * @Title: findBySuperIdOrManagerId 
	 * @param superId
	 * @param managerId
	 * @return  
	 * @throws
	 *
	 */
	public List<Aircraft> findBySuperIdOrManagerId(long superId,long managerId){
		return aircraftRepository.findBySuperIdOrManagerId(superId, managerId);
	}

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findAircraftByCompany 
	 * @param companyId
	 * @return  
	 * @throws
	 *
	 */
	public List<Aircraft> findAircraftByCompany(String companyId) {
		
		List<Aircraft> list = aircraftRepository.findAircraftByCompany(companyId);
		
	
		List<GaCompany> companyList = gaCompanyRepository.findList();
		
		List<PlanType> planTypeList = planTypeResitory.findAll();
		
		for (Aircraft aircraft : list) {
			for(GaCompany gaCompany : companyList){
				if(aircraft.getCompanyId()!=null){
					if(gaCompany.getCompanyId()==aircraft.getCompanyId().intValue()){
						aircraft.setCompanyName(gaCompany.getName());
						aircraft.setSupervisionName(gaCompany.getSupervisionName());
						aircraft.setAuthorityName(gaCompany.getAuthorityName());
					}
				}
				
			}
			if(aircraft.getPlanTypeId()!=null){
			for(PlanType planType : planTypeList){
				if(planType.getId()==aircraft.getPlanTypeId()){
					aircraft.setPlanTypeName(planType.getTypeName());
				}
			}
			}
			
			

  }
		return list;
     }

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findAircraftByInfo 
	 * @param companyId
	 * @param regNumber
	 * @param aircraftStatus
	 * @param pageable
	 * @return  
	 * @throws
	 *
	 */
	public List<Aircraft> findAircraftByInfo(String companyId, String regNumber, String aircraftStatus,
			Pageable pageable) {
  Specification<Aircraft> spec = DaoSpec.getSpec(companyId,regNumber,aircraftStatus);
		
		List<Aircraft> list = aircraftRepository.findAll(spec,pageable).getContent();
		long total = aircraftRepository.findAll(spec,pageable).getTotalElements();		
		
        List<GaCompany> companyList = gaCompanyRepository.findList();
		
		List<PlanType> planTypeList = planTypeResitory.findAll();
		
		List<Department> departmentList = departmentDao.findAll();
		
		for (Aircraft aircraft : list) {
			aircraft.setTotal(total);
			
			for(GaCompany gaCompany : companyList){
				if(aircraft.getCompanyId()!=null){
					if(gaCompany.getCompanyId()==aircraft.getCompanyId().intValue()){
						aircraft.setCompanyName(gaCompany.getName());
						aircraft.setSupervisionName(gaCompany.getSupervisionName());
						aircraft.setAuthorityName(gaCompany.getAuthorityName());
					}
				}
				
			}
			if(aircraft.getPlanTypeId()!=null){
			for(PlanType planType : planTypeList){
				if(planType.getId()==aircraft.getPlanTypeId()){
					aircraft.setPlanTypeName(planType.getTypeName());
					aircraft.setTypeNumber(planType.getTypeNumber());
				}
			}
		}
			if(aircraft.getSuperId()!=null){
				for(Department department : departmentList){
					if(department.getId()==aircraft.getSuperId()){
						aircraft.setSupervisionName(department.getTitle());
					}
				}
			}
			if(aircraft.getManagerId()!=null){
				for(Department department : departmentList){
					if(department.getId()==aircraft.getManagerId()){
						aircraft.setAuthorityName(department.getTitle());
					}
				}
			}

        }
		return  list;
	}

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: saveAircraft 
	 * @param info  
	 * @throws
	 *
	 */
	public void saveAircraft(AircraftCheckDTO info) {
		// TODO Auto-generated method stub
		Aircraft aircraft = aircraftRepository.save(info.getAircraft());
		for(int i = 0;i<info.getCheckRecord().size();i++){
			info.getCheckRecord().get(i).setAircaftId(aircraft.getAircraftId());
			checkRecordRepository.save(info.getCheckRecord().get(i));
		}
		
	}

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findByRegNumberAndAircaftId 
	 * @param regnumber
	 * @param aircaftId
	 * @return  
	 * @throws
	 *
	 */
	public Aircraft findByRegNumberAndAircaftId(String regNumber, Long aircaftId,Integer companyId) {
		// TODO Auto-generated method stub
		return aircraftRepository.findByRegNumberAndAircaftId(regNumber,aircaftId,companyId);
	}

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findAircraftByInfos 
	 * @param companyId
	 * @param regNumber
	 * @param aircraftStatus
	 * @return  
	 * @throws
	 *
	 */
	public List<Aircraft> findAircraftByInfos(String companyId, String regNumber, String aircraftStatus) {
Specification<Aircraft> spec = DaoSpec.getSpec(companyId,regNumber,aircraftStatus);
		
		List<Aircraft> list = aircraftRepository.findAll(spec);
			
		
		List<PlanType> planTypeList = planTypeResitory.findAll();
		
		for (Aircraft aircraft : list) {
			if(aircraft.getPlanTypeId()!=null){
			for(PlanType planType : planTypeList){
				if(planType.getId()==aircraft.getPlanTypeId()){
					aircraft.setPlanTypeName(planType.getTypeName());
					aircraft.setTypeNumber(planType.getTypeNumber());
				}
			}
			}


  }
		return  list;
	}
	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findByRegNumberAndCompanyId 
	 * @param regNumber
	 * @param companyId
	 * @return  
	 * @throws
	 *
	 */
	public Aircraft findByRegNumberAndCompanyId(String regNumber, Integer companyId) {
		// TODO Auto-generated method stub
		return aircraftRepository.findByRegNumberAndCompanyId(regNumber,companyId);
	}
	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findBySuperId 
	 * @param parseLong
	 * @return  
	 * @throws
	 *
	 */
	public List<Aircraft> findBySuperId(long superid) {
		List<Aircraft> list = aircraftRepository.findBySuperId(superid);
		
		
		List<GaCompany> companyList = gaCompanyRepository.findList();
		
		List<PlanType> planTypeList = planTypeResitory.findAll();
		
		for (Aircraft aircraft : list) {
			for(GaCompany gaCompany : companyList){
				if(aircraft.getCompanyId()!=null){
					if(gaCompany.getCompanyId()==aircraft.getCompanyId().intValue()){
						aircraft.setCompanyName(gaCompany.getName());
						aircraft.setSupervisionName(gaCompany.getSupervisionName());
						aircraft.setAuthorityName(gaCompany.getAuthorityName());
					}
				}
				
			}
			if(aircraft.getPlanTypeId()!=null){
			for(PlanType planType : planTypeList){
				if(planType.getId()==aircraft.getPlanTypeId()){
					aircraft.setPlanTypeName(planType.getTypeName());
				}
			}
			}


  }
		return list;
		
	}
	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findByManagerId 
	 * @param parseLong
	 * @return  
	 * @throws
	 *
	 */
	public List<Aircraft> findByManagerId(long managerid) {
List<Aircraft> list = aircraftRepository.findByManagerId(managerid);
		
		
		List<GaCompany> companyList = gaCompanyRepository.findList();
		
		List<PlanType> planTypeList = planTypeResitory.findAll();
		
		for (Aircraft aircraft : list) {
			for(GaCompany gaCompany : companyList){
				if(aircraft.getCompanyId()!=null){
					if(gaCompany.getCompanyId()==aircraft.getCompanyId().intValue()){
						aircraft.setCompanyName(gaCompany.getName());
						aircraft.setSupervisionName(gaCompany.getSupervisionName());
						aircraft.setAuthorityName(gaCompany.getAuthorityName());
					}
				}
				
			}
			if(aircraft.getPlanTypeId()!=null){
			for(PlanType planType : planTypeList){
				if(planType.getId()==aircraft.getPlanTypeId()){
					aircraft.setPlanTypeName(planType.getTypeName());
				}
			}
			}
			


  }
		return list;
		
	}
	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: deletes 
	 * @param list  
	 * @throws
	 *
	 */
	public void deletes(List<Aircraft> list) {
		
			aircraftRepository.delete(list);
			
		
		
	}
	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: save 
	 * @param aircraft  
	 * @throws
	 *
	 */
	public void save(Aircraft aircraft) {
		aircraftRepository.save(aircraft);
	}
	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: mut 
	 * @param name
	 * @return  
	 * @throws
	 *
	 */
	public List<Object> mut(String name) {
		// TODO Auto-generated method stub
		return aircraftRepository.mut(name);
	}
	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findallLists 
	 * @param companyId
	 * @return  
	 * @throws
	 *
	 */
	public List<String> findallLists(String companyId) {
		List<String> list = new ArrayList<>();
		String air = aircraftRepository.findallLists(companyId);
		String  sim = FSRepository.findallLists(companyId);
		
		System.out.println(air);
		list.add(air);
		list.add(sim);
		return list;
	}
	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findflyLists 
	 * @param companyId
	 * @return  
	 * @throws
	 *
	 */
	public List<String> findflyLists(String companyId) {
		
		return aircraftRepository.findflyLists(companyId);
	}
}