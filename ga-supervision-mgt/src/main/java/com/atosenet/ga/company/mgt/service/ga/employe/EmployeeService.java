package com.atosenet.ga.company.mgt.service.ga.employe;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.atosenet.ga.company.db.model.ga.company.GaCompany;
import com.atosenet.ga.company.db.model.ga.company.OperationBase;
import com.atosenet.ga.company.db.model.ga.employe.Employee;
import com.atosenet.ga.company.db.model.ga.employe.Pilot;
import com.atosenet.ga.company.db.repos.ga.company.GaCompanyRepository;
import com.atosenet.ga.company.db.repos.ga.company.OperationRepo;
import com.atosenet.ga.company.db.repos.ga.employe.EmployeeRepository;
import com.atosenet.ga.company.db.repos.ga.employe.PeriodicalCheckRecordRepository;
import com.atosenet.ga.company.db.repos.ga.employe.PersonExamRecordRepository;
import com.atosenet.ga.company.db.repos.ga.employe.PersonQulificationInfoRepository;
import com.atosenet.ga.company.db.repos.ga.employe.PhysicalRecordRepository;
import com.atosenet.ga.company.db.repos.ga.employe.PilotRepository;
import com.atosenet.ga.company.db.repos.ga.employe.SkilledCheckRecordRepository;
import com.atosenet.ga.company.db.repos.ga.employe.TrainingCertificateRepository;
import com.atosenet.ga.company.db.repos.ga.employe.TrainningRecordRepository;
import com.atosenet.ga.company.db.repos.ga.employe.UpdateCheckRecordRepository;
import com.atosenet.ga.company.db.repos.ga.employe.WorkRecordRepository;
import com.atosenet.ga.company.db.repos.ga.employe.entity.DaoSpec;
import com.atosenet.ga.company.db.repos.sys.OrgRepository;
/**
 * @Description:TODO
 * @time:2017年9月1日 上午8:00:48
 */
@Service
@Transactional(propagation=Propagation.SUPPORTS,readOnly = true)
public class EmployeeService {
	@Autowired
	PhysicalRecordRepository physicalRecordRepository;
	@Autowired
	PilotRepository pilotRepository;
	
	@Autowired
	PeriodicalCheckRecordRepository periodicalCheckRecordRepository;
	@Autowired
	private UpdateCheckRecordRepository updateCheckRecordRepository;
	@Autowired
	SkilledCheckRecordRepository skilledCheckRecordRepository;
	@Autowired
	PersonQulificationInfoRepository personQulificationInfoRepository;
	@Autowired
	TrainningRecordRepository trainningRecordRepository;
	@Autowired
	TrainingCertificateRepository trainingCertificateRepository;
	@Autowired
	PersonExamRecordRepository personExamRecordRepository;
	@Autowired
	WorkRecordRepository workRecordRepository;
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	OrgRepository orgRepository;
	@Autowired
	OperationRepo operationRepo;
	@Autowired
	GaCompanyRepository gaCompanyRepository;
	/**
	 * 增加
	 * @return 
	 */
	@Transactional(readOnly=false)
    public Employee sava(Employee employee,HttpServletRequest request){
		employee.setFlag(4);
		Employee employees = employeeRepository.save(employee);
		return employees;
    }
	
	/**
	 * 增加
	 * @return 
	 */
	@Transactional(readOnly=false)
	public Employee save(Employee employee){
		return employeeRepository.save(employee);
	}
	
	
	/**
	 *删除
	 */
	@Transactional(readOnly=false)
    public void delete(Long id){
		employeeRepository.delete(id);
		personQulificationInfoRepository.deleteByEmployeeId(id);
		physicalRecordRepository.deleteByEmployeeId(id);
		workRecordRepository.deleteByEmployeeId(id);
		personExamRecordRepository.deleteByEmployeeId(id);
		trainningRecordRepository.deleteByEmployeeId(id);
		trainingCertificateRepository.deleteByEmployeeId(id);
		Pilot pilot = pilotRepository.findByEmployeeId(id);
		if(pilot!=null){
			periodicalCheckRecordRepository.deleteByPilotId(pilot.getPilotId());
			skilledCheckRecordRepository.deleteByPilotId(pilot.getPilotId());
			updateCheckRecordRepository.deleteByPilotId(pilot.getPilotId());
		}
		/*periodicalCheckRecordRepository.deleteByEmployeeId(id);
		skilledCheckRecordRepository.deleteByEmployeeId(id);
		updateCheckRecordRepository.deleteByEmployeeId(id);*/
    }
	
	/*
	 * 获取人员信息
	 * */
	@Transactional(readOnly=false)
	public Employee get(long id)
	{
		return employeeRepository.findOne(id);
	}
	
	/*
	 * 根据身份证获取人员信息
	 * */
	@Transactional(readOnly=false)
	public Employee findEmployeeByCardId(String cardId)
	{
		
		return employeeRepository.selectEmployeeByCardId(cardId);
	}
	
	/*
	 * 根据身份证获取人员信息
	 * */
	@Transactional(readOnly=false)
	public Employee findEmployeeByuserid(Long userId)
	{
	    return employeeRepository.findOneByUserId(userId);
   	}
	
	@Transactional(readOnly=false)
	public List<Employee> findEmployeeByuserids(Long userId)
	{
	    return employeeRepository.findByUserIds(userId);
   	}
	
	/*
	 * 通过企业id查询该企业下各个岗位的员工信息
	 * */
	@Transactional(readOnly=false)
	public List<Employee> findEmployeeByjobtitleAndcompanyId(long companyId,int offset, int pageSize,String jobTitle)
	{
//		if(jobTitle.equals("其他人员")) {
//			return employeeRepository.findByCompanyIdAndJobtitleBysomeoneelse(companyId, offset, pageSize);
//		}
//		else {
			return employeeRepository.findByCompanyIdAndJobtitle(companyId, offset, pageSize,jobTitle);
//		}
	}
	
	@Transactional(readOnly=false)
	public int  getcount(long companyId,int offset, int pageSize,String jobTitle)
	{
//		if(jobTitle.equals("其他人员")) {
//			return employeeRepository.findByCompanyIdAndJobtitleBysomeoneelse(companyId, offset, pageSize);
//		}
//		else {
			return employeeRepository.getacountbyjobTitle(companyId,jobTitle);
//		}
	}
	/*
	 * 通过企业id查询该企业下各个岗位的员工信息
	 * */
	@Transactional(readOnly=false)
	public List<Employee> findEmployeeByjobtitleAndcompanyId1(long companyId,int offset, int pageSize,String jobTitle,String keyword)
	{
//		if(jobTitle.equals("其他人员")) {
//			return employeeRepository.findByCompanyIdAndJobtitleBysomeoneelse1(companyId, offset, pageSize,keyword);	
//		}else {
			return employeeRepository.findByCompanyIdAndJobtitle1(companyId, offset, pageSize,jobTitle,keyword);
//		}
		
	
	}
	
	@Transactional(readOnly=false)
	public int  getcount1(long companyId,int offset, int pageSize,String jobTitle,String keyword)
	{
//		if(jobTitle.equals("其他人员")) {
//			return employeeRepository.findByCompanyIdAndJobtitleBysomeoneelse(companyId, offset, pageSize);
//		}
//		else {
			return employeeRepository.getacountbyjobTitle1(companyId,jobTitle,keyword);
//		}
	}
	//	根据姓名查找人员的信息
	@Transactional(readOnly=false)
	public List<Employee> searchEmployeeByUsername(String username) {
		return employeeRepository.searchEmployeeByUsername(username);
	}

	public List<Employee> searchEmployees() {
		// TODO Auto-generated method stub
		return employeeRepository.findAll();
	}
	public List<Object> getcountbyedu(long companyId){
	
	
		return  employeeRepository.getcountbyedu1(companyId);
	}
	
	public List<Object> getcountbytype(long companyId){



		return employeeRepository.getcountbytype1(companyId);
	}

	public List<Object> findEmployeeAndcountBysupId(Long supervisionId){   
		return employeeRepository.findEmployeeAndcountBysupId(supervisionId);
	}
	
	public List<Object> findEmployeeAndcountByautid(Long authorityId){   
		return employeeRepository.findEmployeeAndcountByautid(authorityId);
	}
	@Transactional(readOnly=false)
	public List<Employee> findEmployeeListBycompanyId(String companyId) {
		
		return employeeRepository.findListByCompanyId(Long.parseLong(companyId));

	}
	
	public String findjobtitlebyuserid(long useid) {
		return employeeRepository.findjobtitlebyuserid(useid);
	}
	
	public List<Object> getGroupEmpEduBySupId(Long supervisionId)
	{
		
		//1.博士,2.研究生,3.本科,4.大专,5,大专以下
		return employeeRepository.getGroupEmployeeEductionBysupervisionId(supervisionId);
		
	}
	
	public List<Object> getGroupEmployeeEductionByauthorityId(Long authorityId)
	{
		
		//1.博士,2.研究生,3.本科,4.大专,5,大专以下
		return employeeRepository.getGroupEmployeeEductionByauthorityId(authorityId);
		
	}


	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: getEmployeeByCompanyId 
	 * @param parseLong
	 * @return  
	 * @throws
	 *
	 */
	public List<Employee> getEmployeeByCompanyId(long companyid) {
		// TODO Auto-generated method stub
		return this.employeeRepository.getEmployeeByCompanyId(companyid);
	}


	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findEmployeeListBybaseid 
	 * @param baseid
	 * @return  
	 * @throws
	 *
	 */
	@Transactional(readOnly=false)
	public List<Employee> findEmployeeListBybaseid(String baseid) {
		// TODO Auto-generated method stub
		return employeeRepository.findEmployeeListBybaseid(Long.parseLong(baseid));
	}

	
	/** 
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
	public List<Employee> findEmployeeByInfo(String companyId, String userName, String jobTitle, 
			String techpos,String homeMobile, String cardId,Pageable pageable) {
		Specification<Employee> spec = DaoSpec.getSpec(companyId,userName,jobTitle,techpos,homeMobile,cardId);
		
		List<Employee> list = employeeRepository.findAll(spec,pageable).getContent();
		List<GaCompany> companyList = gaCompanyRepository.findList();
		
		List<OperationBase> operationBaseList = operationRepo.findList();
		
		long total = employeeRepository.findAll(spec,pageable).getTotalElements();
		for (Employee employee : list) {
			employee.setTotal(total);
			for(GaCompany gaCompany : companyList){
				if(employee.getCompanyId()!=null){
					if(gaCompany.getCompanyId()==employee.getCompanyId().intValue()){
						employee.setCompanyName(gaCompany.getName());
					}
				}
				
			}
			if(employee.getBaseId()!=null){
			for(OperationBase operationBase : operationBaseList){
				if(operationBase.getBaseId()==employee.getBaseId()){
					employee.setBaseName(operationBase.getBaseName());
				}
			}
			}
			
			
			
		}
		return  list;
	}

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findEmployeeByInfos 
	 * @param companyId
	 * @param userName
	 * @param jobTitle
	 * @param techpos
	 * @param homeMobile
	 * @return  
	 * @throws
	 *
	 */
	public List<Employee> findEmployeeByInfos(String companyId, String userName, String jobTitle, String techpos,
			String homeMobile, String cardId) {
Specification<Employee> spec = DaoSpec.getSpec(companyId,userName,jobTitle,techpos,homeMobile,cardId);
		
		List<Employee> list = employeeRepository.findAll(spec);
		List<GaCompany> companyList = gaCompanyRepository.findList();
		
		List<OperationBase> operationBaseList = operationRepo.findList();
		
		for (Employee employee : list) {
			for(GaCompany gaCompany : companyList){
				if(employee.getCompanyId()!=null){
					if(gaCompany.getCompanyId()==employee.getCompanyId().intValue()){
						employee.setCompanyName(gaCompany.getName());
					}
				}
				
			}
			if(employee.getBaseId()!=null){
			for(OperationBase operationBase : operationBaseList){
				if(operationBase.getBaseId()==employee.getBaseId()){
					employee.setBaseName(operationBase.getBaseName());
				}
			}
			}
			
			
			
		}
		return  list;
	}

	public List<Object> getPilotLicenseTypeCount(Long companyId) {
		// TODO Auto-generated method stub
		return  employeeRepository.getPilotLicenseTypeCount(companyId);
	}

	public List<Object> getPilotLicenseTypeCountByauthorityId(Long authorityId) {
		// TODO Auto-generated method stub
		return  employeeRepository.getPilotLicenseTypeCountByauthorityId(authorityId);
	}

	public List<Object> getPilotLicenseTypeCountBysupervisionId(Long supervisionId) {
		// TODO Auto-generated method stub
		return  employeeRepository.getPilotLicenseTypeCountBysupervisionId(supervisionId);
	}

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findEmployeeLists 
	 * @param companyId
	 * @return  
	 * @throws
	 *
	 */
	public List<Employee> findEmployeeLists(String companyId) {
		// TODO Auto-generated method stub
		return employeeRepository.findEmployeeLists(companyId);
	}

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findEmployeeByInfos 
	 * @param companyId
	 * @param userName
	 * @param jobTitle
	 * @param techpos
	 * @param homeMobile
	 * @param turnOut
	 * @param contract
	 * @param deptname
	 * @return  
	 * @throws
	 *
	 */
	/*public List<Employee> findEmployeeByInfos(Long companyId, String userName, String jobTitle, String techpos,
			String homeMobile, String turnOut, String contract, String isagent) {
		// TODO Auto-generated method stub
		Specification<Employee> spec = DaoSpec.getSpec(companyId,userName,jobTitle,techpos,homeMobile,turnOut,contract,isagent);
		return  employeeRepository.findAll(spec);
	}*/
}
