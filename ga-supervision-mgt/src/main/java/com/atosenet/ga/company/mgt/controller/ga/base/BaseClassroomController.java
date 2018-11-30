/**
 * 
 */
package com.atosenet.ga.company.mgt.controller.ga.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atosenet.ga.company.db.model.ga.base.BaseClassroom;
import com.atosenet.ga.company.mgt.service.ga.base.BaseClassroomService;

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
@ApiIgnore
@CrossOrigin
@RequestMapping("/eim/api/base")
@Api(tags="基地教室接口")
public class BaseClassroomController {
	@Autowired
	private BaseClassroomService bcService;
	
	@RequestMapping(value = "/search/listbyOpbaseId", method = RequestMethod.GET)
	@ApiOperation(value = "通过运行基地id获取所有教室")
	@ApiImplicitParams({
		@ApiImplicitParam(value="基地id",name="baseid",dataType="Long")
	})
	public List<BaseClassroom> getListByOperationBaseId(@RequestParam("baseid") Long baseid){
		return this.bcService.getListByOperationBaseId(baseid);
	}
	

}
