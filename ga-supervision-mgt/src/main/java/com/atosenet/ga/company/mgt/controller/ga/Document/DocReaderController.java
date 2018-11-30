package com.atosenet.ga.company.mgt.controller.ga.Document;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atosenet.ga.company.db.model.ga.document.DocReader;
import com.atosenet.ga.company.db.repos.ga.document.DocReaderRepository;
import com.atosenet.ga.company.mgt.service.ga.Document.DocListService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/eim/api/docreader")
@Api(tags="公文实体的各种操作")
@ApiIgnore
public class DocReaderController {



	
	@Autowired
	DocReaderRepository DocReaderRepository;
	



	

	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ApiOperation(value = "获取当前公文信息")
	@ApiImplicitParams(
			{
				@ApiImplicitParam(value="公文ID",name="id",dataType="Long")
			}
			)
	public DocReader getById(@PathVariable("id") Long id) {
		return DocReaderRepository.findOne(id);
	}
	
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "删除当前公文信息")
	@ApiImplicitParams(
			{
				@ApiImplicitParam(value="公文ID",name="id",dataType="Long")
			}
			)
	public void deleteById(@PathVariable("id") Long id) {
		DocReaderRepository.delete(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "新增公文信息")
	@Transactional(readOnly=false)
	@ApiImplicitParams(
			{
				@ApiImplicitParam(value="机场跑道信息",name="DocList",dataType="DocList")
			}
			)
	public void save(@RequestBody DocReader DocReader) {

		DocReaderRepository.save(DocReader);
	}
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public String emptyResultDataAccessException(Throwable t){
		return "error";
	}
}
