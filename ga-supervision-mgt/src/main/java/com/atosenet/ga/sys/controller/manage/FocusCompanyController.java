package com.atosenet.ga.sys.controller.manage;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atosenet.ga.company.db.model.ga.company.FocusCompany;
import com.atosenet.ga.company.db.model.ga.company.GaCompany;
import com.atosenet.ga.sys.serviceimpl.FocusCompanyService;

import io.swagger.annotations.Api;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@Api(tags = "关注企业")
@ApiIgnore
@RequestMapping("/eim/fousCompany")
public class FocusCompanyController {
	
	@Autowired
	private FocusCompanyService focusCompanyService;
	
	/**
	 * 
	 * @param manageId
	 * @param type
	 * @return
	 */
	@RequestMapping("/getCompanyListByManageId")
	public List<GaCompany> getCompanyListByManageId(Long manageId, String type) {
		return focusCompanyService.getCompanyListByManageId(manageId, type);
	}
	
	/**
	 * 
	 * @param manageId
	 * @param companyIds
	 */
	@RequestMapping("/saveFocusCompany")
	public FocusCompany saveFocusCompany(@RequestBody FocusCompany focusCompany,HttpServletRequest request) {
		focusCompanyService.deleteByManageId(Long.valueOf(focusCompany.getSuperId()));
		FocusCompany company = focusCompanyService.saveFocusCompany(focusCompany);
		return company;
	}
	
	/**
	 * 
	 * @param superId
	 * @return
	 */
	@RequestMapping("/getCompanyIdsBySuperId")
	public String getCompanyIdsBySuperId(Long superId) {
		return focusCompanyService.getCompanyIdsBySuperId(superId);
	}
	
	@RequestMapping("/getCompanyListByManageIds")
	public String getCompanyIdsBySuperIds(Long manageId) {
		return focusCompanyService.getCompanyIdsBySuperId(manageId);
	}
}
