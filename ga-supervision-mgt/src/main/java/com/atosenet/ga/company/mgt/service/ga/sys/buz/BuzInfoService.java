package com.atosenet.ga.company.mgt.service.ga.sys.buz;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atosenet.ga.company.db.model.ga.company.GaCompany;
import com.atosenet.ga.company.db.model.ga.company.GaOfficial;
import com.atosenet.ga.company.db.model.ga.employe.Employee;
import com.atosenet.ga.company.db.repos.ga.company.GaCompanyRepository;
import com.atosenet.ga.company.db.repos.ga.company.GaOfficialRepository;
import com.atosenet.ga.company.db.repos.ga.employe.EmployeeRepository;
import com.atosenet.ga.company.mgt.common.DefinedRole;
import com.atosenet.ga.sys.dao.DepartmentDao;
import com.atosenet.ga.sys.entity.Department;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

@Service
public class BuzInfoService {
	@Autowired
	private GaCompanyRepository gaCompanyRepository;
	 @Autowired
	private DepartmentDao departmentDao;
	@Autowired
	private GaOfficialRepository gaOfficialRepository;

	@Autowired
	private EmployeeRepository employeeRepository;
	
	public String validateDefinedRole(String role) {
		return validateDefinedRole(Sets.newHashSet(role));
	}

	public String validateDefinedRole(Set<String> roles) {
		for (String role : roles) {
			switch (role) {
			case DefinedRole.ROLE_COMPANY_ADMIN:
				return DefinedRole.ROLE_COMPANY_ADMIN;
			case DefinedRole.ROLE_OFFICIAL_ADMIN:
				return DefinedRole.ROLE_OFFICIAL_ADMIN;
			case DefinedRole.ROLE_SUPERVISION_ADMIN:
				return DefinedRole.ROLE_SUPERVISION_ADMIN;
			case DefinedRole.ROLE_PILOT_GENERAL:
				return DefinedRole.ROLE_PILOT_GENERAL;
			default:
				break;
			}
		}
		
		return null;
	}

	public Map<String, Object> getUserBuzInfoByRole(String roleName, Long orgId) {
		Map<String, Object> buzInfo = Maps.newHashMap();

		switch (roleName) {
		case DefinedRole.ROLE_COMPANY_ADMIN:
		case DefinedRole.ROLE_PILOT_GENERAL:
			GaCompany company = gaCompanyRepository.findByOrgId(orgId);
			if (company != null) {
				buzInfo.put("companyId", company.getCompanyId());
			}
			break;
		case DefinedRole.ROLE_OFFICIAL_ADMIN:
			GaOfficial official = gaOfficialRepository.findByOrgId(orgId);
			if (official != null) {
				buzInfo.put("companyId", official.getAuthorityId());
			}
			break;
		case DefinedRole.ROLE_SUPERVISION_ADMIN:

			break;

		default:
			break;
		}
		return buzInfo;
	}
	
	public Map<String, Object> getUserBuzInfoByUserId(Long userId) {
		Map<String, Object> buzInfo = Maps.newHashMap();
		Employee employee = employeeRepository.findByUserId(userId);
		if(employee != null) {
			buzInfo.put("employeeId", employee.getEmployeeId());
			buzInfo.put("jobtitle", employee.getJobTitle());
			buzInfo.put("userlogo", employee.getPhoto());
		}
		return buzInfo;
	}
	
	/**
	 * 根据部门ID获取部门信息
	 * @param id
	 * @return
	 */
	public Map<String, Object> getUserBuzInfoBygacompanyId(Long id) {
		Map<String, Object> buzInfo = Maps.newHashMap();
		Department department = departmentDao.findOne(id);
		//GaCompany company = gaCompanyRepository.findById(id.intValue());
		if (department != null) {
			buzInfo.put("type", department.getType());
			buzInfo.put("title", department.getTitle());
		}
		return buzInfo;
	}
}
