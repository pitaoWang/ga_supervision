/**  
 * 
 * @Title: TrainingBaseController.java 
 * @Package com.atosenet.ga.company.mgt.controller.ga.company  
 * @author dgw   
 * @date 2018年8月8日 下午3:30:10 
 * @version V1.0
 *
 */
package com.atosenet.ga.company.mgt.controller.ga.company;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.atosenet.ga.company.db.model.ga.company.TrainingBase;
import com.atosenet.ga.company.mgt.service.ga.company.TrainingBaseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/** 
 * TODO(训练基地控制层)
 * 
 * @ClassName: TrainingBaseController 
 * @author dgw 
 * @date 2018年8月8日 下午3:30:10 
 *  
 */
@RestController
@RequestMapping("/eim/api/trainbase")
@Api(tags = "训练基地信息")
@ApiIgnore
public class TrainingBaseController {
	
	@Autowired
	TrainingBaseService trainingBaseService;
	
	@RequestMapping(value = "pagelist",method = RequestMethod.GET)
	@ApiOperation(value = "获取训练基地分页列表")
	public Page<TrainingBase> findPageAll(HttpServletRequest request,HttpServletResponse response){
		String page = request.getParameter("page");
		String size = request.getParameter("size");
		Pageable pageable = new PageRequest(Integer.parseInt(page),Integer.parseInt(size));
		return trainingBaseService.findPageAll(pageable);
	}
	
	@RequestMapping(value = "save",method = RequestMethod.GET)
	@ApiOperation(value = "保存训练基地的信息")
	public TrainingBase save(TrainingBase trainBase){
		return trainingBaseService.save(trainBase);
	}
	
	@RequestMapping(value = "update",method = RequestMethod.GET)
	@ApiOperation(value = "修改训练基地的信息")
	public TrainingBase update(TrainingBase trainBase){
		trainBase.setTrainbaseId(1);
		trainBase.setTrainbaseName("test");
		return trainingBaseService.save(trainBase);
	}
	
	@RequestMapping(value = "delete",method = RequestMethod.GET)
	@ApiOperation(value = "根据id删除训练基地的信息")
	public void delete(long trainbaseId){
		trainingBaseService.delete(trainbaseId);
	}
}
