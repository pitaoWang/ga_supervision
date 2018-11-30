package com.atosenet.ga.company.mgt.controller.ga.Document;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atosenet.ga.company.db.model.ga.company.Aircraft;
import com.atosenet.ga.company.db.model.ga.document.DocList;
import com.atosenet.ga.company.db.model.ga.document.DocListDto;
import com.atosenet.ga.company.db.model.ga.document.DocListNewDto;
import com.atosenet.ga.company.db.repos.ga.document.DocReaderRepository;
import com.atosenet.ga.company.mgt.service.ga.Document.DocListService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/eim/api/doclist")
@Api(tags="公文实体的各种操作")

public class DocListController {


	@Autowired
	DocListService DocListService;
	
	@Autowired
	DocReaderRepository DocReaderRepository;
	



	

	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ApiOperation(value = "获取当前公文信息")
	@ApiIgnore
	@ApiImplicitParams(
			{
				@ApiImplicitParam(value="公文ID",name="id",dataType="Long")
			}
			)
	public DocList getById(@PathVariable("id") Long id) {
		return DocListService.findById(id);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "删除当前公文信息")
	@ApiIgnore
	@ApiImplicitParams(
			{
				@ApiImplicitParam(value="公文ID",name="id",dataType="Long")
			}
			)
	public void deleteById(@PathVariable("id") Long id) {
		DocListService.delete(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "新增公文信息")
	@Transactional(readOnly=false)
	@ApiIgnore
	@ApiImplicitParams(
			{
				@ApiImplicitParam(value="机场跑道信息",name="DocList",dataType="DocList")
			}
			)
	public DocList save(@RequestBody DocList DocList) {
		DocList.setCreatetime(new Date());
		return DocListService.save(DocList);
	}
	
	/*@RequestMapping(value = "/search/findlistbycompanyid", method = RequestMethod.GET)
	public Page<DocListDto> finddoclist(@RequestParam(value = "pagesize", required = false) String pagesize,
			@RequestParam(value = "pageno", required = false) String pageno,
			@RequestParam(value = "companyId", required = false) String companyid,
			@RequestParam(value = "jobTitle", required = false) String jobTitle){
		Pageable pageable = new PageRequest(Integer.parseInt(pageno), Integer.parseInt(pagesize));
		Page<DocListDto> pagelist = null;
	
		pagelist=DocListService.finddoclist(jobTitle,companyid, pageable);

		return pagelist;
	}*/
	@RequestMapping(value = "/search/findlistbycompanyid", method = RequestMethod.GET)
	public Page<DocListDto> finddoclist(@RequestParam(value = "pagesize", required = false) String pagesize,
			@RequestParam(value = "pageno", required = false) String pageno,
			@RequestParam(value = "companyId", required = false) String companyid
			){
		Pageable pageable = new PageRequest(Integer.parseInt(pageno), Integer.parseInt(pagesize));
		Page<DocListDto> pagelist = null;
	
		pagelist=DocListService.finddoclist(companyid, pageable);

		return pagelist;
	}
	
	@RequestMapping(value = "/search/findlistbycompanyidcompanymange", method = RequestMethod.GET)
	@ApiIgnore
	public Page<DocList> finddoclist1(@RequestParam(value = "pagesize", required = false) String pagesize,
			@RequestParam(value = "pageno", required = false) String pageno,
			@RequestParam(value = "companyId", required = false) String companyid,
			@RequestParam(value = "jobTitle", required = false) String jobTitle){
		Pageable pageable = new PageRequest(Integer.parseInt(pageno), Integer.parseInt(pagesize));
		Page<DocList> pagelist = null;
			pagelist=DocListService.finddoclistcompany(jobTitle,companyid, pageable);
		
	
		return pagelist;
	}
	
	@RequestMapping(value = "/search/findnamebyemployeeid", method = RequestMethod.GET)
	@ApiIgnore
    public  List<Object> findnamebyemployeeid(@RequestParam(value = "employeeid", required = false) String employeeid){
		return DocListService.findnamebyemployeeid(Long.parseLong(employeeid));
    }
	
	@RequestMapping(value = "/search/geilistbycreator", method = RequestMethod.GET)
	@ApiIgnore
    public  Page<DocListNewDto> geilistbycreator(@RequestParam(value = "employeeid", required = false) String employeeid,
    		@RequestParam(value = "pagesize", required = false) String pagesize,
    		@RequestParam(value = "pageno", required = false) String pageno
    		){
		Pageable pageable = new PageRequest(Integer.parseInt(pageno), Integer.parseInt(pagesize));
		Page<DocListNewDto> pagelist = null;
		pagelist=DocListService.geilistbycreator(Long.parseLong(employeeid), pageable);
		
	
		return pagelist;
	
    }
	//根据superid查询
	@RequestMapping(value = "/search/getListBySupervisionId", method = RequestMethod.GET)
	@ApiIgnore
    public  Page<DocListNewDto> getListBySupervisionId(@RequestParam(value = "superviseId", required = false) String superviseId,
    		@RequestParam(value = "pagesize", required = false) String pagesize,
    		@RequestParam(value = "pageno", required = false) String pageno
    		){
		Pageable pageable = new PageRequest(Integer.parseInt(pageno), Integer.parseInt(pagesize));
		Page<DocListNewDto> pagelist = null;
		pagelist=DocListService.getListBySupervisionId(Long.parseLong(superviseId), pageable);
		
	
		return pagelist;
	
    }
	
	@RequestMapping(value = "/search/geilistbysuperviseId", method = RequestMethod.GET)
	@ApiIgnore
    public  Page<DocList> geilistbysuperviseId(@RequestParam(value = "superviseId", required = false) String superviseId,
    		@RequestParam(value = "pagesize", required = false) String pagesize,
    		@RequestParam(value = "pageno", required = false) String pageno
    		){
		Pageable pageable = new PageRequest(Integer.parseInt(pageno), Integer.parseInt(pagesize));
		Page<DocList> pagelist = null;
		pagelist=DocListService.geilistbysuperviseId(Long.parseLong(superviseId), pageable);
		
	
		return pagelist;
	
    }
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public String emptyResultDataAccessException(Throwable t){
		return "error";
	}
}
