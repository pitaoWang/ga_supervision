/**
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.atosenet.ga.company.core.common.model.response.BaseResponse;
import com.atosenet.ga.company.db.model.ga.employe.FlightDispatcherLicense;
import com.atosenet.ga.company.db.model.ga.employe.FlightDispatcherLicenseDTO;
import com.atosenet.ga.company.mgt.config.Constants;
import com.atosenet.ga.company.mgt.service.ga.employe.FlightDispatcherLicenseService;
import com.atosenet.ga.company.mgt.service.ga.employe.response.DataResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author lvhao
 *
 */

@RestController
@CrossOrigin
@RequestMapping("/eim/api/flydispathcer")
@Api(tags="飞行签派员执照维护")

public class FlyDispatcherLicenseController {
	
	@Autowired
	private FlightDispatcherLicenseService flightDispatcherLicenseService;
	
	/**
	 * 
	 * TODO(新增飞行签派员执照) 
	 *
	 * @Title: save 
	 * @param fdLicense
	 * @return  
	 * @throws
	 *
	 */
	@RequestMapping(value="/save", method=RequestMethod.POST)
	@ApiOperation(value="新增飞行签派员执照")
	public BaseResponse save(@RequestBody FlightDispatcherLicense fdLicense){
		BaseResponse response = new BaseResponse();
		
		try {
			this.flightDispatcherLicenseService.save(fdLicense);
			response.setResultCode(Constants.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
	/**
	 * 
	 * TODO(删除飞行签派员执照) 
	 *
	 * @Title: deleteById 
	 * @param id
	 * @return  
	 * @throws
	 *
	 */
	@RequestMapping(value="{id}", method=RequestMethod.DELETE)
	@ApiOperation(value="根据id删除飞行签派员执照")
	public BaseResponse deleteById(@PathVariable("id") Long id){
		BaseResponse response = new BaseResponse();
		try {
			this.flightDispatcherLicenseService.delete(id);
			response.setResultCode(Constants.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
	/**
	 * 
	 * TODO(根据id查询) 
	 *
	 * @Title: selectById 
	 * @param id
	 * @return  
	 * @throws
	 *
	 */
	@RequestMapping(value="{id}", method=RequestMethod.GET)
	@ApiOperation(value = "根据id查询飞行签派员执照")
	public DataResponse<FlightDispatcherLicense> selectById(@PathVariable("id") Long id){
		DataResponse<FlightDispatcherLicense> response = new DataResponse<>();
		
		try {
			FlightDispatcherLicense dispatcherLicense = this.flightDispatcherLicenseService.findById(id);
			response.setData(dispatcherLicense);
			response.setResultCode(Constants.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
	@RequestMapping(value="search/{id}", method=RequestMethod.GET)
	@ApiOperation(value = "根据执照id查询飞行签派员执照")
	public DataResponse<FlightDispatcherLicenseDTO> selectAllById(@PathVariable("id") Long id){
		DataResponse<FlightDispatcherLicenseDTO> response = new DataResponse<>();
		
		try {
			FlightDispatcherLicenseDTO dispatcherLicense = this.flightDispatcherLicenseService.findAllById(id);
			response.setData(dispatcherLicense);
			response.setResultCode(Constants.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
	@RequestMapping(value="/pageList", method=RequestMethod.GET)
	@ApiOperation(value="分页查询飞行签派员执照")
	public DataResponse<Page<FlightDispatcherLicense>> selectpage(HttpServletRequest request, HttpServletResponse response){
		DataResponse<Page<FlightDispatcherLicense>> res = new DataResponse<Page<FlightDispatcherLicense>>();
		String page = request.getParameter("page");
		String size = request.getParameter("size");
		Pageable pageable = new PageRequest(Integer.parseInt(page), Integer.parseInt(size));
		try {
			Page<FlightDispatcherLicense> selectPage = this.flightDispatcherLicenseService.selectPage(pageable);
			res.setData(selectPage);
			res.setResultCode(Constants.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
		}
		return res;
	}
}
