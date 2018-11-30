package com.atosenet.ga.company.mgt.service.ga.employe;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.atosenet.ga.company.db.model.ga.company.GaCompany;
import com.atosenet.ga.company.db.model.ga.company.OperationBase;
import com.atosenet.ga.company.db.model.ga.employe.Employee;
import com.atosenet.ga.company.db.model.ga.employe.Pilot;
import com.atosenet.ga.company.db.model.ga.employe.vo.PilotListVO;
import com.atosenet.ga.company.db.model.sys.Org;
import com.atosenet.ga.company.db.repos.ga.company.GaCompanyRepository;
import com.atosenet.ga.company.db.repos.ga.company.OperationRepo;
import com.atosenet.ga.company.db.repos.ga.employe.ControlEmplRepository;
import com.atosenet.ga.company.db.repos.ga.employe.EmployeeRepository;
import com.atosenet.ga.company.db.repos.ga.employe.PilotListRepository;
import com.atosenet.ga.company.db.repos.ga.employe.PilotRepository;
import com.atosenet.ga.company.db.repos.sys.OrgRepository;
import com.atosenet.ga.company.mgt.service.ga.employe.dto.EmployeePilotDTO;
/**
 * @Description:TODO
 * @time:2017年9月1日 上午8:00:48
 */
@Service
@Transactional(propagation=Propagation.SUPPORTS,readOnly = true)
public class PilotService {
	@Autowired
	OrgRepository orgRepository;
	@Autowired
	 ControlEmplRepository controlEmplRepository;
	@Autowired
	OperationRepo operationRepo;
	@Autowired
	GaCompanyRepository gaCompanyRepository;
	@Autowired
	PilotRepository pilotRepository;
	
	@Autowired
	PilotListRepository pilotListRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	/**
	 * 增加
	 */
	@Transactional(readOnly=false)
    public Pilot sava(Pilot pilot,HttpServletRequest request){
		
		Pilot employee=pilotRepository.save(pilot);
		Employee em = employeeRepository.findOne(pilot.getEmployeeId());
		em.setFlag(4);
		employeeRepository.save(em);
		return employee;
    }
	
	@Transactional(readOnly=false)
	public Pilot save(Pilot pilot){
		Pilot p=pilotRepository.save(pilot);
		return p;
	}
	
	
	/*
	 * 获取详细信息
	 * */
	@Transactional(readOnly=false)
	public Pilot get(long id)
	{
		return pilotRepository.findOne(id);
	}
	
	
	/**
	 *删除
	 */
	@Transactional(readOnly=false)
    public void delete(Long id){
		pilotRepository.delete(id);
    }
	
	/**
	 * 查询飞行员列表，根据企业查询企业飞行员列表，分页
	 * 返回员工基本信息和飞行员信息
	 */
	@Transactional(readOnly=true)
    public List<PilotListVO> queryPilot4Company(long companyId,int offset, int pageSize,String jobTitle)
	{
		return pilotListRepository.findPilot4Company(companyId, offset, pageSize,jobTitle);
    }
	
	@Transactional(readOnly=true)
    public int getacountpliot2(long companyId,int offset, int pageSize,String jobTitle)
	{
		return pilotListRepository.getacountpliot2(companyId,jobTitle);
    }
	
	
	/**
	 * 搜索飞行员列表，根据企业查询企业飞行员列表，分页
	 * 返回员工基本信息和飞行员信息
	 */
	@Transactional(readOnly=true)
    public List<PilotListVO> querySerchPilot4Company(long companyId,String keyword,int offset, int pageSize,String jobTitle)
	{
		return pilotListRepository.querySerchPilot4Company(companyId,keyword, offset, pageSize,jobTitle);
    }
	
	@Transactional(readOnly=true)
    public int getacountpliot3(long companyId,String keyword,int offset, int pageSize,String jobTitle)
	{
		return pilotListRepository.getacountpliot3(companyId,keyword,jobTitle);
    }
	
	/**
	 * 搜索飞行员名字查询所属企业
	 */
	@Transactional(readOnly=true)
    public List<PilotListVO> querySerchPilotList(List<Integer> companyIdList,String keyword,int offset, int pageSize,String jobTitle)
	{
		return pilotListRepository.querySerchPilotList(companyIdList,keyword,jobTitle,offset,pageSize);//
    }
	
	@Transactional(readOnly=true)
    public int getacountpliot3(List<Integer> companyIdList,String keyword,int offset, int pageSize,String jobTitle)
	{
		return pilotListRepository.getacountpliot3(companyIdList,keyword,jobTitle);
    }
	
	/**
	 * 查询飞行员列表，根据企业查询企业飞行员列表，分页
	 * 返回员工基本信息和飞行员信息
	 */
	@Transactional(readOnly=true)
    public List<PilotListVO> queryPilot4CompanyWithLicense(long companyId,int offset, int pageSize,String begaindata,String enddate)
	{
		return pilotListRepository.findPilot4CompanyWithLicense(companyId, offset, pageSize,begaindata,enddate);
    }
	
	/**
	 * 查询飞行员列表，根据企业查询企业飞行员数量
	 * 返回员工基本信息和飞行员信息
	 */
	@Transactional(readOnly=true)
	 public int queryPilot4CompanyWithLicensecount(long companyId,String begaindata,String enddate)
	{
		return pilotListRepository.findPilot4CompanyWithLicensecount(companyId,begaindata,enddate);
    }
	
	//查询统计飞行员数据长度  
	@Transactional(readOnly=true)
	public int getCount(long companyId,String begaindata,String enddate, String type){
		return pilotListRepository.getCount(companyId,begaindata,enddate,type);
	}
	
	
	
	
	
	/**
	 * 查询飞行员列表，根据监管id监管企业飞行员列表，分页
	 * 返回员工基本信息和飞行员信息
	 */
	@Transactional(readOnly=true)
    public List<PilotListVO> queryPilot4CompanyWithLicense(List<Integer> companyIdList,int offset, int pageSize,String begaindata,String enddate)
	{
		return pilotListRepository.findPilot4CompanyWithLicenseBysupId(companyIdList, offset, pageSize,begaindata,enddate);
    }
	
	@Transactional(readOnly=true)
    public int queryPilot4CompanyWithLicensecount(List<Integer> companyIdList,String begaindata,String enddate)
	{
		return pilotListRepository.findPilot4CompanyWithLicensecountBysupId(companyIdList,begaindata,enddate);
    }
	
	
	/**
	 * 查询飞行员列表，根据企业查询企业飞行员列表，分页
	 * 返回员工基本信息和飞行员信息
	 */
	@Transactional(readOnly=true)
    public List<PilotListVO> queryPilotList(List<Integer> companyIdList,int offset, int pageSize,String jobTitle)
	{
		return pilotListRepository.queryPilotList(companyIdList, offset, pageSize,jobTitle);
    }
	@Transactional(readOnly=true)
    public int getacountpliot(List<Integer> companyIdList,int offset, int pageSize,String jobTitle)
	{
		return pilotListRepository.getacountpliot(companyIdList,jobTitle);
    }
	
	/**
	 * 查询飞行员详情包含飞行员表
	 */
	@Transactional(readOnly=true)
    public EmployeePilotDTO queryEmployeeByEmployeeId(long employeeId)
	{
		Employee employee = employeeRepository.findByEmployeeId(employeeId);
		GaCompany companyList = gaCompanyRepository.findById(employee.getCompanyId().intValue());
		Org org = orgRepository.findById(employee.getAuthorityId());
		Org orgs = orgRepository.findById(employee.getSupervisionId());
		OperationBase operationBase = operationRepo.findByBaseId(employee.getBaseId());
		if(companyList!=null){
			employee.setCompanyName(companyList.getName());
		}
		if(org!=null){
			employee.setAuthorityName(org.getName());
		}
		if(orgs!=null){
			employee.setSupervisionName(orgs.getName());
		}
		if(operationBase!=null){
			employee.setBaseName(operationBase.getBaseName());
		}
		
		EmployeePilotDTO dto=new EmployeePilotDTO();
		dto.setEmployee(employee);
		dto.setPilot(pilotRepository.findByEmployeeId(employeeId));
		dto.setControl(controlEmplRepository.findByEmployeeId(employeeId));
		return dto;
    }

	/** 
	 * TODO(根据企业id获取飞行员信息) 
	 *
	 * @Title: getByEmployeeId 
	 * @param parseLong
	 * @return  
	 * @throws
	 *
	 */
	public Pilot getByEmployeeId(long employeeId) {
		// TODO Auto-generated method stub
		return this.pilotRepository.getByEmployeeId(employeeId);
	}

	public List<PilotListVO> statistic(Long companyId, Integer offset, Integer pageSize, String begaindata,
			String enddate, String type) {
		// TODO Auto-generated method stub
		return pilotListRepository.statistic(companyId, offset, pageSize,begaindata,enddate,type);
		
	}

	public List<PilotListVO> statisticByauthorityId(Long authorityId, Integer offset, Integer pageSize,
			String begaindata, String enddate, String type) {
		// TODO Auto-generated method stub
		return pilotListRepository.statisticByauthorityId(authorityId, offset, pageSize,begaindata,enddate,type);
	}

	public Integer getCountByauthorityId(Long authorityId, String begaindata, String enddate, String type) {
		// TODO Auto-generated method stub
		return pilotListRepository.getCountByauthorityId(authorityId,begaindata,enddate,type);
	}

	public List<PilotListVO> statisticBysupervisionId(Long supervisionId, Integer offset, Integer pageSize,
			String begaindata, String enddate, String type) {
		// TODO Auto-generated method stub
		return pilotListRepository.statisticBysupervisionId(supervisionId, offset, pageSize,begaindata,enddate,type);
	}

	public Integer getCountBysupervisionId(Long supervisionId, String begaindata, String enddate, String type) {
		// TODO Auto-generated method stub
		return pilotListRepository.getCountBysupervisionId(supervisionId,begaindata,enddate,type);
	}

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: statisticBycompanyId 
	 * @param companyId
	 * @param beginDate
	 * @param endDate
	 * @return  
	 * @throws
	 *
	 */
	public List<PilotListVO> statisticBycompanyId(String companyId, String beginDate, String endDate) {
		// TODO Auto-generated method stub
		return pilotListRepository.statisticBycompanyId(companyId,beginDate,endDate);
	}

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: statisticBycompanyIds 
	 * @param companyId
	 * @param beginDate
	 * @param endDate
	 * @return  
	 * @throws
	 *
	 */
	public List<PilotListVO> statisticBycompanyIds(String companyId, String beginDate, String endDate) {
		// TODO Auto-generated method stub
		return pilotListRepository.statisticBycompanyIds(companyId,beginDate,endDate);
	}
	
}
