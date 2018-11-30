/**  
 * 
 * @Title: OtherBaseController.java 
 * @Package com.atosenet.ga.company.mgt.controller.ga.company  
 * @author dgw   
 * @date 2018年8月21日 下午6:44:27 
 * @version V1.0
 *
 */
package com.atosenet.ga.company.mgt.controller.ga.company;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atosenet.ga.company.db.model.ga.company.OtherBase;
import com.atosenet.ga.company.mgt.config.Constants;
import com.atosenet.ga.company.mgt.service.ga.company.OtherBaseService;
import com.atosenet.ga.company.mgt.service.ga.employe.response.DataResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/** 
 * TODO(这里用一句话描述这个类的作用)
 * 
 * @ClassName: OtherBaseController 
 * @author dgw 
 * @date 2018年8月21日 下午6:44:27 
 *  
 */
@RestController
@CrossOrigin
@RequestMapping("/eim/api/otherbase")
@ApiIgnore
@Api(tags = "其他基地信息")
public class OtherBaseController {
		
	@Autowired
	OtherBaseService otherservice;
	
	@RequestMapping(value = "/findall",method = RequestMethod.GET)
	@ApiOperation(value = "获取所有其他基地信息")
	@ApiIgnore
	public DataResponse<List<OtherBase>> findAll(){
		DataResponse<List<OtherBase>> res = new DataResponse<List<OtherBase>>();
		List<OtherBase> otherBaseList = new ArrayList<OtherBase>();
		try{
			otherBaseList = otherservice.findAll();
			res.setData(otherBaseList);
			res.setResultCode(Constants.OK);
		}catch(Exception e){
			e.printStackTrace();
			res.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
		}
		return res;
	}
	
	@RequestMapping(value = "/findbycompanyidandbasetype",method = RequestMethod.GET)
	@ApiOperation(value = "获取所有其他基地信息")
	@ApiImplicitParams({ @ApiImplicitParam(value = "企业id", name = "companyId",paramType="query", dataType = "String"),
	@ApiImplicitParam(value = "基地类型(1、飞行基地,2、维修基地,3、训练基地)", name = "baseType",paramType="query", dataType = "String")
	})
	public DataResponse<List<OtherBase>> findByCompanyIdAndBaseType(@RequestParam("companyId")String companyId,@RequestParam("baseType")String baseType){
		DataResponse<List<OtherBase>> res = new DataResponse<List<OtherBase>>();
		List<OtherBase> otherBaseList = new ArrayList<OtherBase>();
		try{
			otherBaseList = otherservice.findByCompanyIdAndBaseType(Long.parseLong(companyId), baseType);
			res.setData(otherBaseList);
			res.setResultCode(Constants.OK);
		}catch(Exception e){
			e.printStackTrace();
			res.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
		}
		return res;
	}
	
	
	
	@RequestMapping(value = "/saveotherbase",method=RequestMethod.POST)
	@ApiOperation(value = "保存其他基地信息")
	@ApiIgnore
	public DataResponse<OtherBase> saveOtherBase(@RequestBody OtherBase otherBase){
		DataResponse<OtherBase> res = new DataResponse<OtherBase>();
		OtherBase ob = new OtherBase();
		try{
			ob = otherservice.save(otherBase);
			res.setData(ob);							
			res.setResultCode(Constants.OK);
		}catch(Exception e){
			e.printStackTrace();
			res.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
		}
		return res;
	}
	
	@RequestMapping(value = "/deleteotherbase/{baseId}",method = RequestMethod.DELETE)
	@ApiOperation(value = "删除其他基地信息")
	@ApiIgnore
	public DataResponse deleteOtherBase(@PathVariable("baseId")String baseId){
		DataResponse res = new DataResponse();
		try{
			otherservice.delete(Long.parseLong(baseId));
			res.setResultCode(Constants.OK);
		}catch(Exception e){
			e.printStackTrace();
			res.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
		}
		return res;
	}
	
	@RequestMapping(value = "/findByCompanyId",method = RequestMethod.GET)
	@ApiOperation(value = "根据企业id查看其他基地信息")
	@ApiIgnore
	public DataResponse<List<OtherBase>> findByCompanyId(String companyId){
		DataResponse<List<OtherBase>> res = new DataResponse<List<OtherBase>>();
		List<OtherBase> obList = new ArrayList<OtherBase>();
		try{
			obList = otherservice.findByCompanyId(Long.parseLong(companyId));
			res.setData(obList);
			res.setResultCode(Constants.OK);
		}catch(Exception e){
			e.printStackTrace();
			res.setMessage(e.getMessage());
			res.setResultCode(Constants.INTERNAL_SERVER_ERROR);
		}
		return res;
	}
}
