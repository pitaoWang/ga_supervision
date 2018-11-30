/**
 * 
 */
package com.atosenet.ga.company.mgt.controller.ga.company;

import java.util.List;

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

import com.atosenet.ga.company.db.model.ga.company.CheckRecord;
import com.atosenet.ga.company.db.model.ga.company.Course;
import com.atosenet.ga.company.mgt.config.Constants;
import com.atosenet.ga.company.mgt.service.ga.company.CourseService;
import com.atosenet.ga.company.mgt.service.ga.employe.response.DataResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author lvhao
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/eim/api/course")
@Api(tags="课程类管理")
public class CourseController {

	@Autowired
	private CourseService courseService;

	// 分页查询
	@RequestMapping(value = "/pageList", method = RequestMethod.GET)
	@ApiIgnore
	public Page<Course> getPage(HttpServletRequest request, HttpServletResponse response) {
		String page = request.getParameter("page");
		String size = request.getParameter("size");
		Pageable pageable = new PageRequest(Integer.parseInt(page), Integer.parseInt(size));
		return this.courseService.selectPage(pageable);
	}
	
	//新增
	@RequestMapping(method=RequestMethod.POST)
	@ApiIgnore
	public DataResponse<Course> save(@RequestBody Course course){
     DataResponse<Course> response = new DataResponse<Course>();
		
		try 
		{
			Course result = this.courseService.save(course);
			response.setData(result);
			response.setResultCode(Constants.OK);
			
		} catch (Exception e) {
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		}
		return response;	
		
	}
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "删除适用课程")
	@ApiIgnore
	public DataResponse<Long> deleteById(@PathVariable("id") Long id) {
		DataResponse<Long> response = new DataResponse<Long>();
		Long result = courseService.delete(id);
		
			response.setData(result);
			response.setResultCode(Constants.OK);
			return response;
	}
	@RequestMapping(value = "/findbysimulatorId",method=RequestMethod.GET)
	@ApiOperation(value="根据模拟训练器ID获取模拟器的适用课程")
	@ApiImplicitParams({ @ApiImplicitParam(value = "模拟训练器id", name = "simulatorId",paramType="query", dataType = "String")})
	public List<Course> findBysimulatorId(@RequestParam("simulatorId")String simulatorId){
		return courseService.findBysimulatorId(Long.parseLong(simulatorId));
	}
	

}
