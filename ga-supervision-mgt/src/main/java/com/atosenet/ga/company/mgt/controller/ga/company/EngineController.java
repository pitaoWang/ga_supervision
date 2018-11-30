/**
 * 
 */
package com.atosenet.ga.company.mgt.controller.ga.company;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.executor.ReuseExecutor;
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

import com.atosenet.ga.company.db.model.ga.company.Course;
import com.atosenet.ga.company.db.model.ga.company.Engine;
import com.atosenet.ga.company.mgt.config.Constants;
import com.atosenet.ga.company.mgt.service.ga.company.EngineService;
import com.atosenet.ga.company.mgt.service.ga.employe.response.DataResponse;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 发动机管理
 * @author lvhao
 *
 */
@RestController
@RequestMapping("/eim/api/engine")
@CrossOrigin
public class EngineController {
	
	@Autowired
	private EngineService engineService;
	
	//根据模拟训练器id 查询发动机 id
	@RequestMapping(value="/search/bySimulatorId", method=RequestMethod.GET)
	@ApiIgnore
	public List<Long> selectBySimulatorId(@RequestParam String id){
		return this.engineService.selectBySimulatorId(Long.parseLong(id));
		
	}
	
	//分页查询
	@RequestMapping(value="/pageList", method=RequestMethod.GET)
	@ApiIgnore
	public Page<Engine> getPage(HttpServletRequest request, HttpServletResponse response){
		String page = request.getParameter("page");
		String size = request.getParameter("size");
		Pageable pageable = new PageRequest(Integer.parseInt(page), Integer.parseInt(size));
		return this.engineService.selectPage(pageable);	
	}
	
	//保存 
	@RequestMapping(method=RequestMethod.POST)
	@ApiIgnore
	public DataResponse<Engine> save(@RequestBody Engine engine){
		 DataResponse<Engine> response = new DataResponse<Engine>();
			
			try 
			{
				Engine result = this.engineService.save(engine);
				response.setData(result);
				response.setResultCode(Constants.OK);
				
			} catch (Exception e) {
				response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
				response.setMessage(e.getMessage());
			}
			return response;	
		
		
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "删除发动机")
	@ApiIgnore
	public DataResponse<Long> deleteById(@PathVariable("id") Long id) {
		DataResponse<Long> response = new DataResponse<Long>();
		Long result = engineService.delete(id);
		
			response.setData(result);
			response.setResultCode(Constants.OK);
			return response;
	}
	
	
	
	
}
