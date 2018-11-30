/**  
 * 
 * @Title: ControllerLicenseController.java 
 * @Package com.atosenet.ga.company.mgt.controller.ga.employe  
 * @author dgw   
 * @date 2018年8月10日 上午10:12:14 
 * @version V1.0
 *
 */
package com.atosenet.ga.company.mgt.controller.ga.employe;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.atosenet.ga.company.db.model.ga.employe.ControllerLicense;
import com.atosenet.ga.company.mgt.service.ga.employe.ControllerLicenseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/** 
 * TODO(管制人员执照信息)
 * 
 * @ClassName: ControllerLicenseController 
 * @author dgw 
 * @date 2018年8月10日 上午10:12:14 
 *  
 */
@RestController
@CrossOrigin
@RequestMapping("/eim/api/controllerlicense")
@Api(tags="管制人员执照")
@ApiIgnore
public class ControllerLicenseController {
	
	@Autowired
	ControllerLicenseService controllerLicenseService;
	
	@RequestMapping(value ="/pagelist",method = RequestMethod.GET)
	@ApiOperation(value = "获取管制执照分页信息")
	public Page<ControllerLicense> findPageAll(HttpServletRequest request,HttpServletResponse response){
		String page = request.getParameter("page");
		String size = request.getParameter("size");
		Pageable pageable = new PageRequest(Integer.parseInt(page),Integer.parseInt(size));
		return controllerLicenseService.findPageAll(pageable);
	}
	
	@RequestMapping(value="/save",method = RequestMethod.POST)
	@ApiOperation(value = "保存管理人员执照信息")
	public ControllerLicense save(ControllerLicense controllerLicense){
		return controllerLicenseService.save(controllerLicense);
	}
	
	@RequestMapping(value="{id}",method = RequestMethod.DELETE)
	@ApiOperation(value = "根据id删除管制人员执照信息")
	public void delete(long licenseId){
		controllerLicenseService.delete(licenseId);
	}
}
