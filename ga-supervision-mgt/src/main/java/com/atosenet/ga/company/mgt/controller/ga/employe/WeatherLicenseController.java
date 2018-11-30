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
import com.atosenet.ga.company.db.model.ga.employe.WeatherLicense;
import com.atosenet.ga.company.db.model.ga.employe.WeatherLicenseDTO;
import com.atosenet.ga.company.db.repos.ga.employe.PersonQulificationInfoRepository;
import com.atosenet.ga.company.mgt.config.Constants;
import com.atosenet.ga.company.mgt.service.ga.employe.FlightDispatcherLicenseService;
import com.atosenet.ga.company.mgt.service.ga.employe.WeatherLicenseService;
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
@RequestMapping("/eim/api/weatherlicense")

@Api(tags="气象人员执照维护")
public class WeatherLicenseController {
	
	@Autowired
	private WeatherLicenseService weatherLicenseService;	
	/**
	 * 
	 * TODO(新增气象人员执照) 
	 *
	 * @Title: save 
	 * @param fdLicense
	 * @return  
	 * @throws
	 *
	 */
	@RequestMapping(value="/save", method=RequestMethod.POST)
	@ApiOperation(value="新增气象人员执照")
	public BaseResponse save(@RequestBody WeatherLicense weatherLicense){
		BaseResponse response = new BaseResponse();
		try {
			this.weatherLicenseService.save(weatherLicense);
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
	 * TODO(根据id删除执照) 
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
			this.weatherLicenseService.deleteById(id);
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
	@ApiOperation(value = "根据id查询气象员执照")
	public DataResponse<WeatherLicense> selectById(@PathVariable("id") Long id){
		DataResponse<WeatherLicense> response = new DataResponse<>();
		
		try {
			WeatherLicense weatherLicense = this.weatherLicenseService.selectById(id);
			response.setData(weatherLicense);
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
	@ApiOperation(value = "根据执照id查询气象员执照")
	public DataResponse<WeatherLicenseDTO> selectAllById(@PathVariable("id") Long id){
		DataResponse<WeatherLicenseDTO> response = new DataResponse<>();
		
		try {
			WeatherLicenseDTO weatherLicenseDTO = this.weatherLicenseService.selectAllById(id);
			response.setData(weatherLicenseDTO);
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
	@ApiOperation(value="分页查询气象员执照")
	public DataResponse<Page<WeatherLicense>> selectpage(HttpServletRequest request, HttpServletResponse response){
		DataResponse<Page<WeatherLicense>> res = new DataResponse<Page<WeatherLicense>>();
		String page = request.getParameter("page");
		String size = request.getParameter("size");
		Pageable pageable = new PageRequest(Integer.parseInt(page), Integer.parseInt(size));
		try {
			Page<WeatherLicense> selectPage = this.weatherLicenseService.selectPage(pageable);
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
