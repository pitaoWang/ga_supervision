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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atosenet.ga.company.core.common.model.response.BaseResponse;
import com.atosenet.ga.company.db.model.ga.employe.ControlEmpl;
import com.atosenet.ga.company.mgt.config.Constants;
import com.atosenet.ga.company.mgt.service.ga.employe.ControlEmplService;
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
@RequestMapping("/eim/api/controlEmpl")
@Api(tags="管制人员的维护")
@ApiIgnore
public class ControlEmplController {
	
	@Autowired
	private ControlEmplService controlEmplService;
	
	/**
	 * 
	 * TODO(保存管制人员) 
	 *
	 * @Title: save 
	 * @param controlEmpl
	 * @return  
	 * @throws
	 *
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
    @ApiOperation(value = "新增管制人员")
	public DataResponse<ControlEmpl> save(@RequestBody ControlEmpl controlEmpl){
		
		DataResponse<ControlEmpl> response = new DataResponse<ControlEmpl>();
		
		try {
			this.controlEmplService.save(controlEmpl);
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
	 * TODO(根据id查询管制人员) 
	 *
	 * @Title: selectById 
	 * @param id
	 * @return  
	 * @throws
	 *
	 */
	@RequestMapping(value="/search", method = RequestMethod.GET)
	@ApiOperation(value="根据id查询管制人员")
	public DataResponse<ControlEmpl> selectById(@RequestParam String id){
		DataResponse<ControlEmpl> response = new DataResponse<ControlEmpl>();
	
		try {
			ControlEmpl	ce = this.controlEmplService.selectById(Long.parseLong(id));
			response.setData(ce);
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
	 * TODO(分页查询管制人员) 
	 *
	 * @Title: selectByPage 
	 * @param request
	 * @param response
	 * @return  
	 * @throws
	 *
	 */
	@RequestMapping(value="/pageList", method=RequestMethod.GET)
	@ApiOperation(value="分页查询管制人员")
	public DataResponse<Page<ControlEmpl>> selectByPage(HttpServletRequest request, HttpServletResponse response){
		DataResponse<Page<ControlEmpl>> res = new DataResponse<Page<ControlEmpl>>();
		String page = request.getParameter("page");
		String size = request.getParameter("size");
		Pageable pageable = new PageRequest(Integer.parseInt(page), Integer.parseInt(size));
		try {
			Page<ControlEmpl> selectPage = this.controlEmplService.selectPage(pageable);
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
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="{id}", method = RequestMethod.DELETE)
	@ApiOperation(value="通过id删除管制人员")
	public BaseResponse deleteById(@PathVariable("id") Long id){
		BaseResponse response = new BaseResponse();
		try {
			this.controlEmplService.deleteById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
	@RequestMapping(value="/search/byEmployeeId", method=RequestMethod.GET)
	@ApiOperation(value = "根据人员id获取管制人员信息")
	@ApiImplicitParam(value = "人员id", name = "id", dataType = "string", paramType = "query")
	public DataResponse<ControlEmpl> getByEmployeeId(@RequestParam("id") String employeeId){
		DataResponse<ControlEmpl> response = new DataResponse<>();
		
		try {
			ControlEmpl ce = this.controlEmplService.getByEmployeeId(Long.parseLong(employeeId));
			response.setData(ce);
			response.setResultCode(Constants.OK);
			return response;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			return response;
		}
		
	}
	

}
