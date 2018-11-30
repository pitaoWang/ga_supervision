package com.atosenet.ga.company.mgt.controller.ga.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atosenet.ga.company.db.model.ga.company.StructureFss;
import com.atosenet.ga.company.mgt.service.ga.company.StructureFssService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@CrossOrigin
@RequestMapping("/eim/api/structure")
@Api(tags = "飞行服务站信息")
@ApiIgnore
public class StructureFssController {
	
	@Autowired
	StructureFssService structureFssService;
	
	
	@RequestMapping(value="/findbyadministrationId",method = RequestMethod.GET)
	@ApiOperation(value = "根据管理局分页获取飞行服务站信息")
	public Page<StructureFss> findByAdministrationId(@RequestParam("pageno")String pageno,@RequestParam("pagesize")String pagesize,@RequestParam("administrationId")String administrationId,
			@RequestParam("name") String name,@RequestParam("code")String code
			){
		
		Pageable pageable = new PageRequest(Integer.parseInt(pageno),Integer.parseInt(pagesize));
		Page<StructureFss> ps = null;
		if((name==""||name.length()==0)&&(code==""||code.length()==0)){
			ps = structureFssService.findByAdministrationId(Long.parseLong(administrationId), pageable);
		}
		if((name!=""||name.length()>0)&&(code==""||code.length()==0)){
			ps = structureFssService.findByAdministrationIdAndName(Long.parseLong(administrationId), name, pageable);
		}
		if((name==""||name.length()==0)&&(code!=""||code.length()>0)){
			ps = structureFssService.findByAdministrationIdAndCode(Long.parseLong(administrationId), code, pageable);
		}
		if((name!=""||name.length()>0)&&(code!=""||code.length()>0)){
			ps = structureFssService.findByAdministrationIdAndNameAndCode(Long.parseLong(administrationId), name, code, pageable);
		}
		return ps;
	}
	
	@RequestMapping(value="/findbysupervisionbureauId",method = RequestMethod.GET)
	@ApiOperation(value = "根据监管局分页获取飞行服务站信息")
	public Page<StructureFss> findBySupervisionBureauId(@RequestParam("pageno")String pageno,@RequestParam("pagesize")String pagesize,@RequestParam("supervisionBureauId")String supervisionBureauId,
			@RequestParam("name") String name,@RequestParam("code")String code
			){
		
		Pageable pageable = new PageRequest(Integer.parseInt(pageno),Integer.parseInt(pagesize));
		Page<StructureFss> ps = null;
		if((name==""||name.length()==0)&&(code==""||code.length()==0)){
			ps = structureFssService.findBySupervisionBureauId(Long.parseLong(supervisionBureauId), pageable);
		}
		if((name!=""||name.length()>0)&&(code==""||code.length()==0)){
			ps = structureFssService.findBySupervisionBureauIdAndName(Long.parseLong(supervisionBureauId), name, pageable);
		}
		if((name==""||name.length()==0)&&(code!=""||code.length()>0)){
			ps = structureFssService.findBySupervisionBureauIdAndCode(Long.parseLong(supervisionBureauId), code, pageable);
		}
		if((name!=""||name.length()>0)&&(code!=""||code.length()>0)){
			ps = structureFssService.findBySupervisionBureauIdAndNameAndCode(Long.parseLong(supervisionBureauId), name, code, pageable);
		}
		return ps;
	}
}
