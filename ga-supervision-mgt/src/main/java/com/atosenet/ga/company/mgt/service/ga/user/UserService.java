//package com.atosenet.ga.company.mgt.service.ga.user;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.alibaba.fastjson.JSONObject;
//import com.atosenet.ga.company.core.auth.shiro.common.utils.HttpUtils;
//import com.atosenet.ga.company.db.model.ga.employe.Employee;
//import com.atosenet.ga.company.db.model.ga.employe.Pilot;
//import com.atosenet.ga.company.db.model.sys.CreateUser;
//import com.atosenet.ga.company.db.model.sys.Org;
//import com.atosenet.ga.company.db.model.sys.Role;
//import com.atosenet.ga.company.db.model.sys.User;
//import com.atosenet.ga.company.db.repos.ga.company.GaCompanyRepository;
//import com.atosenet.ga.company.db.repos.ga.company.GaOfficialRepository;
//import com.atosenet.ga.company.db.repos.ga.employe.EmployeeRepository;
//import com.atosenet.ga.company.db.repos.ga.employe.PilotRepository;
//import com.atosenet.ga.company.db.repos.sys.OrgRepository;
//import com.atosenet.ga.company.db.repos.sys.RoleRepository;
//import com.atosenet.ga.company.db.repos.sys.UserRepository;
//import com.atosenet.ga.company.mgt.common.DefinedRole;
//import com.atosenet.ga.company.mgt.service.ga.sys.buz.BuzInfoService;
///**
// * @Description:TODO
// * @time:2017年9月15日
// */
//@Service
//@Transactional(propagation=Propagation.SUPPORTS,readOnly = true)
//public class UserService {
//
//	@Autowired
//	UserRepository userRepository;
//	@Autowired
//	EmployeeRepository employeeRepository;
//	@Autowired
//	GaOfficialRepository gaOfficialRepository;
//	@Autowired
//	GaCompanyRepository gaCompanyRepository;
//	@Autowired
//	OrgRepository orgRepository;
//	@Autowired
//	RoleRepository roleRepository;
//	@Autowired
//	PilotRepository pilotRepository;
//	
//	@Autowired
//	BuzInfoService buzInfoService;
//	
//	
//	
//	
//	public List<Object> listbyorgid(String orgid,int offset,int pageSize) {
//		return  userRepository.listbyorgid(orgid, offset, pageSize);
//	}
//
//
//	@Transactional(readOnly=false)
//	public JSONObject deleteemploy(HttpServletRequest request,String url,Map<String, String> map,Long userid){
//		JSONObject apis = HttpUtils.post(url,map,request);
//		 Employee emplyoo = employeeRepository.findByUserId(userid);
//		 userRepository.deleteemployeeByOrgId(userid);
//		if(emplyoo!=null){
//			pilotRepository.deletebyemployeeId(emplyoo.getEmployeeId());	
//		}
//		return apis;
//	}
//
//	/*
//	 * 根据用户名获取权限集合
//	 * */
//	@Transactional(readOnly=false)
//	public List<Role> findUserRoleByAccount(String account)
//	{
//		System.out.println("进到了service=============");
//		//System.out.println(userRepository.findByAccount(account).toString());
//		User user= userRepository.findByAccount(account);
//		Set<Role> roles =user.getRoles();
//		List<Role> list=new ArrayList<Role>();
//		
//		for(Role r:roles){
//			System.out.println(r.getRolename());
//			if(DefinedRole.ROLE_COMPANY_ADMIN.equals(r.getRolename())) {
//				list=roleRepository.selectByRoletype(3);
//				
//			}
//			else if(DefinedRole.ROLE_SYSTEM_ADMIN.equals(r.getRolename())){
//				
//				list=roleRepository.selectAll(0);
//				System.out.println(list.toString()+"---------");
//
//			}
//			
//		}
//		return list;
//	}
//	//	系统设置-》创建用户
//	@Transactional(readOnly=false)
//	public void saveCreateUser(CreateUser createuser){
//		try {
//			
//				Org org1=null;
//				org1=orgRepository.findOne(Long.parseLong(createuser.getCompanyId()));
//				System.out.println("org1==="+org1.toString());
//				
//				//根据管理员的account来查询出对象来设置employee里的userid
//				//User user1=userRepository.findByAccount(createuser.getAccount());
//				
//				//保存到sys_user表
//				User user =new User();
//				user.setAccount(createuser.getUserUserName());
//				//Org org=new Org();
//				//org.setId(Long.parseLong(createuser.getCompanyType()));
//				user.setOrg(org1);
//				user.setCreateTime(new Date());
//				user.setEmail(createuser.getEmail());
//				user.setUsername(createuser.getUserName());
//				Set<Role> roles=new HashSet<Role> ();
//				Role role=roleRepository.findOne((roleRepository.selectByRoleName(createuser.getUserRole()).getId()));
//				roles.add(role);			
//				user.setRoles(roles);
//				userRepository.save(user);
//				User user1=userRepository.findByAccount(createuser.getUserUserName());
//				//System.out.println(user1.toString());
//				
//				Employee employee1=null;
//				String rolename = createuser.getUserRole();
//				Long orgid = Long.parseLong(createuser.getCompanyId());
//				Map<String, Object> buzInfoByRole = buzInfoService.getUserBuzInfoByRole(rolename, orgid);
//				if(rolename.equals(DefinedRole.ROLE_PILOT_GENERAL) ||
//						rolename.equals(DefinedRole.ROLE_MAINTENANCE_GENERAL) ||
//						rolename.equals(DefinedRole.ROLE_COMPANY_ADMIN)) {
//					Employee employee =new Employee();
//					employee.setCompanyId(Long.parseLong(buzInfoByRole.get("companyId").toString()));
//					
//					//System.out.println(user1.getId()+".....");
//					employee.setUserId(user1.getId());
//					employee.setUserName(createuser.getUserName());
//					employee1= employeeRepository.save(employee);
//				}
//				
//				
//				if(createuser.getUserRole().equals(DefinedRole.ROLE_PILOT_GENERAL)) {
//				
//					Pilot pilot=new Pilot();
//					pilot.setEmployeeId(employee1.getEmployeeId());
//					pilotRepository.save(pilot);
//				
//				}
//				
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//}
