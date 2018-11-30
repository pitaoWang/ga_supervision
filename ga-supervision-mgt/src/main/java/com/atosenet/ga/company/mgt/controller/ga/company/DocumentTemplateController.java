/**  
 * 
 * @Title: DocumentTemplateController.java 
 * @Package com.atosenet.ga.company.mgt.controller.ga.company  
 * @author dgw   
 * @date 2018年8月8日 下午5:12:07 
 * @version V1.0
 *
 */
package com.atosenet.ga.company.mgt.controller.ga.company;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.atosenet.ga.company.db.model.ga.company.DocumentTemplate;
import com.atosenet.ga.company.mgt.config.Constants;
import com.atosenet.ga.company.mgt.service.ga.company.DocumentTemplateService;
import com.atosenet.ga.company.mgt.service.ga.employe.response.DataResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/** 
 * TODO(文档模板)
 * 
 * @ClassName: DocumentTemplateController 
 * @author dgw 
 * @date 2018年8月8日 下午5:12:07 
 *  
 */
@RestController
@RequestMapping("/eim/api/doctemplate")
@CrossOrigin
@ApiIgnore
public class DocumentTemplateController {
	
	@Autowired
	DocumentTemplateService docTempService;
		
    
    /**
     * 
     * TODO(获取文档模板分页列表) 
     *
     * @Title: findPageAll 
     * @param request
     * @param response
     * @return  
     * @throws
     *
     */
    @RequestMapping("/pagelist")
    @ResponseBody
    @ApiIgnore
    public Page<DocumentTemplate> findPageAll(HttpServletRequest request,HttpServletResponse response){
    	String page = request.getParameter("page");
    	String size = request.getParameter("size");
    	Pageable pageable = new PageRequest(Integer.parseInt(page),Integer.parseInt(size));
    	return docTempService.findPageAll(pageable);
    }
    
    
    @RequestMapping(value="/findbycompanyid",method=RequestMethod.GET)
    @ApiOperation(value="根据id查询")
    @ApiIgnore
    public DataResponse<List<DocumentTemplate>> findByCompanyId(@RequestParam("companyId")String companyId){
    	DataResponse<List<DocumentTemplate>> res = new DataResponse<List<DocumentTemplate>>();
    	List<DocumentTemplate> listDoc = new ArrayList<DocumentTemplate>();
    	try{
    		listDoc = docTempService.findByCompanyId(Long.parseLong(companyId));
    		res.setData(listDoc);
    		res.setResultCode(Constants.OK);    		
    	}catch(Exception e){
    		e.printStackTrace();
    		res.setMessage(e.getMessage());
    		res.setResultCode(Constants.INTERNAL_SERVER_ERROR);
    	}
    	return res;
    }
    
    @RequestMapping(value ="/findbycompanyidandtemplatenameandversionnumber",method=RequestMethod.GET)
    @ApiOperation(value="根据企业id和模板名字和版本号查询")
    @ApiIgnore
    public DataResponse<DocumentTemplate> findByCompanyIdAndTemplateNameAndVersionNumber(@RequestParam("companyId")String companyId,@RequestParam("templateName")String tempName,@RequestParam("versionNumber")String version){
    	DataResponse<DocumentTemplate> res = new DataResponse<DocumentTemplate>();
    	DocumentTemplate doc = new DocumentTemplate();
    	try{
    		doc = docTempService.findByCompanyIdAndTemplateNameAndVersionNumber(Long.parseLong(companyId), tempName, version);
    		res.setData(doc);
    		res.setResultCode(Constants.OK);
    	}catch(Exception e){
    		e.printStackTrace();
    		res.setMessage(e.getMessage());
    		res.setResultCode(Constants.INTERNAL_SERVER_ERROR);
    	}
    	return res;
    }
    
    @RequestMapping(value = "/savedoctemplate",method = RequestMethod.POST)
    @ApiOperation(value = "保存文档模板")
    @ApiIgnore
    public DataResponse<DocumentTemplate> save(@RequestBody DocumentTemplate doc){
    	DataResponse<DocumentTemplate> res = new DataResponse<DocumentTemplate>();
    	DocumentTemplate documentTemp = new DocumentTemplate();
    	try{
    		documentTemp = docTempService.save(doc);
    		res.setData(documentTemp);
    		res.setResultCode(Constants.OK);
    	}catch(Exception e){
    		e.printStackTrace();
    		res.setMessage(e.getMessage());
    		res.setResultCode(Constants.INTERNAL_SERVER_ERROR);
    	}
    	return res;
    }
    
    
    @RequestMapping(value = "/delete/{templateId}",method = RequestMethod.DELETE)
    @ApiOperation(value = "根据id删除模板")
    @ApiIgnore
    public DataResponse delete(@PathVariable("templateId")String templateId){
    	DataResponse res = new DataResponse();
    	try{
    		docTempService.delete(Long.parseLong(templateId));
    		res.setResultCode(Constants.OK);
    	}catch(Exception e){
    		e.printStackTrace();
    		res.setMessage(e.getMessage());
    		res.setResultCode(Constants.INTERNAL_SERVER_ERROR);
    	}
    	return res;
    }
    
    
    
    
   
  
}
