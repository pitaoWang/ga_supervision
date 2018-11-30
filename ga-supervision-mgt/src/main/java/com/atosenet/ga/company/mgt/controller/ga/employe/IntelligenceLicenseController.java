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
import com.atosenet.ga.company.db.model.ga.employe.IntelligenceLicense;
import com.atosenet.ga.company.db.model.ga.employe.IntelligenceLicenseDTO;
import com.atosenet.ga.company.mgt.config.Constants;
import com.atosenet.ga.company.mgt.service.ga.employe.IntelligenceLicenseService;
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
@RequestMapping("/eim/api/intelligencelicense")

@Api(tags="情报人员执照维护")
public class IntelligenceLicenseController {
	
	@Autowired
	private IntelligenceLicenseService intelligenceLicenseService;
	
	/**
	 * 
	 * TODO(新增情报人员执照) 
	 *
	 * @Title: save 
	 * @param fdLicense
	 * @return  
	 * @throws
	 *
	 */
	@RequestMapping(value="/save", method=RequestMethod.POST)
	@ApiOperation(value="新增情报人员执照")
	public BaseResponse save(@RequestBody IntelligenceLicense intelligenceLicense){
		BaseResponse response = new BaseResponse();
		
		try {
			this.intelligenceLicenseService.save(intelligenceLicense);
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
	@ApiOperation(value="根据id删除情报员执照")
	public BaseResponse deleteById(@PathVariable("id") Long id){
		BaseResponse response = new BaseResponse();
		try {
			this.intelligenceLicenseService.delete(id);
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
	@ApiOperation(value = "根据id查询情报员执照")
	public DataResponse<IntelligenceLicense> selectById(@PathVariable("id") Long id){
		DataResponse<IntelligenceLicense> response = new DataResponse<>();
		
		try {
			IntelligenceLicense intelligenceLicense = this.intelligenceLicenseService.findById(id);
			response.setData(intelligenceLicense);
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
	@ApiOperation(value = "根据执照id查询情报员执照")
	public DataResponse<IntelligenceLicenseDTO> selectAllById(@PathVariable("id") Long id){
		DataResponse<IntelligenceLicenseDTO> response = new DataResponse<>();
		
		try {
			IntelligenceLicenseDTO intelligenceLicense = this.intelligenceLicenseService.findAllById(id);
			response.setData(intelligenceLicense);
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
	 * TODO(分页查询情报员执照) 
	 *
	 * @Title: selectpage 
	 * @param request
	 * @param response
	 * @return  
	 * @throws
	 *
	 */
	@RequestMapping(value="/pageList", method=RequestMethod.GET)
	@ApiOperation(value="分页查询情报员执照")
	@ApiIgnore
	public DataResponse<Page<IntelligenceLicense>> selectpage(HttpServletRequest request, HttpServletResponse response){
		DataResponse<Page<IntelligenceLicense>> res = new DataResponse<Page<IntelligenceLicense>>();
		String page = request.getParameter("page");
		String size = request.getParameter("size");
		Pageable pageable = new PageRequest(Integer.parseInt(page), Integer.parseInt(size));
		try {
			Page<IntelligenceLicense> selectPage = this.intelligenceLicenseService.selectPage(pageable);
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
