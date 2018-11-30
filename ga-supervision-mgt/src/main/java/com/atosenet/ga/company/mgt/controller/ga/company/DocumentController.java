/**
 * 
 */
package com.atosenet.ga.company.mgt.controller.ga.company;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atosenet.ga.company.db.model.ga.company.Document;
import com.atosenet.ga.company.mgt.config.Constants;
import com.atosenet.ga.company.mgt.service.ga.company.DocService;
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
@RequestMapping("/eim/api/doc")
@Api(tags="企业运行相关文件")
public class DocumentController {
	@Autowired
	DocService docService;
	
	@RequestMapping(value = "/search/listbycompanyid", method = RequestMethod.GET)
	
	@ApiOperation(value = "获取所有运行相关文件通过企业id")
	@ApiImplicitParams({
		@ApiImplicitParam(value="公司ID",name="companyid",paramType="query",dataType="String")
	})
	public List<Document> getDataFileBycompanyId(@RequestParam("companyid") String companyid){
		return this.docService.findByCompanyId(Long.parseLong(companyid));
	}
	
	@RequestMapping(value = "/savedatafile",method = RequestMethod.POST)
	@ApiOperation(value = "新增/修改运行文件")
	@ApiImplicitParams({
		@ApiImplicitParam(value="文件对象",name="datafile",paramType="body",dataType="Document")
	})
	public DataResponse<Document> saveDataFile(@RequestBody Document datafile){
		DataResponse<Document> res = new DataResponse<Document>();
		Document docu = new Document();
		try{
			docu = docService.save(datafile);
			res.setData(docu);
			res.setResultCode(Constants.OK);
		}catch(Exception e){
			e.printStackTrace();
			res.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
		}
		return res;
	}
	
	@RequestMapping(value = "/findbycompanyidanddatafilenameandversionnumber/{companyId},{datafileName},{versionNumber}",method = RequestMethod.GET)
	@ApiOperation(value = "根据企业id文件名字版本号运行文件")
	@ApiIgnore
	@ApiImplicitParams({
		@ApiImplicitParam(value="企业id",name="companyId",dataType="String"),
		@ApiImplicitParam(value="文件名字",name="datafileName",dataType="String"),
		@ApiImplicitParam(value="版本号",name="versionNumber",dataType="String")
	})
	public DataResponse<Document> findByCompanyIdAndDatafileNameAndVersionNumber(@PathVariable("companyId")String companyId,@PathVariable("datafileName")String datafileName,@PathVariable("versionNumber")String versionNumber){
		DataResponse<Document> res = new DataResponse<Document>();
		Document doc = new Document();
		try{
			doc = docService.findByCompanyIdAndDatafileNameAndVersionNumber(Long.parseLong(companyId), datafileName, versionNumber);
			res.setData(doc);
			res.setResultCode(Constants.OK);
		}catch(Exception e){
			e.printStackTrace();
			res.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
		}
		return res;
	}
	
	@RequestMapping(value = "deletebyid/{id}", method=RequestMethod.DELETE)
	@ApiOperation(value = "根据运行相关文件id删除运行文件")
	@ApiImplicitParams({
		@ApiImplicitParam(value="运行相关文件id",name="id",paramType="path",dataType="string")
	})
	public DataResponse deleteById(@PathVariable("id")String id){
		DataResponse res = new DataResponse();
		try{
			docService.delete(Long.parseLong(id));
			res.setResultCode(Constants.OK);
		}catch(Exception e){
			e.printStackTrace();
			res.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
		}
		return res;
	}

}
