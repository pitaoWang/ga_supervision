/**  
 * 
 * @Title: CompanyDataFileController.java 
 * @Package com.atosenet.ga.company.mgt.controller.ga.company  
 * @author dgw   
 * @date 2018年8月9日 下午5:13:28 
 * @version V1.0
 *
 */
package com.atosenet.ga.company.mgt.controller.ga.company;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.atosenet.ga.company.db.model.ga.company.Document;
import com.atosenet.ga.company.db.model.ga.company.DocumentTemplate;
import com.atosenet.ga.company.mgt.service.ga.company.DocService;

import springfox.documentation.annotations.ApiIgnore;

/** 
 * TODO(企业资料文件controller)
 * 
 * @ClassName: CompanyDataFileController 
 * @author dgw 
 * @date 2018年8月9日 下午5:13:28 
 *  
 */
@RestController
@RequestMapping("/companydatafile")
@ApiIgnore
public class CompanyDatafileController {
	
	@Autowired
	DocService docService;
	
	/*
     * 获取file.html页面
     */
    @RequestMapping("file")
    public String file(){
        return "/docmentTemplate";
    }
	
	/**
	 * 
	 * TODO(获取企业质料信息分页列表) 
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
	public Page<Document> findPageAll(HttpServletRequest request,HttpServletResponse response){
		String page = request.getParameter("page");
		String size = request.getParameter("size");
		Pageable pageable = new PageRequest(Integer.parseInt(page),Integer.parseInt(size));
		return docService.findPageAll(pageable);
	}
	
	 /**
     * 
     * TODO(企业资料的上传) 
     *
     * @Title: fileUpload 
     * @param file
     * @return  
     * @throws
     *
     */
    @RequestMapping("fileUpload")
    @ResponseBody 
    public String fileUpload(@RequestParam("file")MultipartFile file){
        if(file.isEmpty()){
            return "false";
        }
        String fileName = file.getOriginalFilename();
        int size = (int) file.getSize();
        System.out.println(fileName + "-->" + size);
        //String dir = "123公司id"+"version";
        String path = "F:/"+"公司123"+"/version"+"/"+fileName ;
       
      
        File dest = new File(path);
        
        if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
            dest.getParentFile().mkdirs();
        }      
        try {
            file.transferTo(dest); //保存文件
            Document doc =  new Document();
            doc.setDataFileName(fileName);
            long companyId = 1L;
            String docName = fileName;
            String version = "0";
            Document docu = docService.findByCompanyIdAndDatafileNameAndVersionNumber(companyId, docName, version);
            if(docu==null){
            	 docService.save(doc);
            }else{
            	/*docService.delete(docu.getDocId());*/
            	docService.save(doc);
            }        
            return "true";
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "false";
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "false";
        }
    	
    }
    
    /**
     * 
     * TODO(企业资料的下载) 
     *
     * @Title: downLoad 
     * @param response
     * @return  
     * @throws
     *
     */
    @RequestMapping("download")
    public String downLoad(HttpServletResponse response){
        String filename="xx.txt";
        String filePath = "F:/test" ;
        File file = new File(filePath + "/" + filename);
        if(file.exists()){ //判断文件父目录是否存在
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment;fileName=" + filename);
            
            byte[] buffer = new byte[1024];
            FileInputStream fis = null; //文件输入流
            BufferedInputStream bis = null;
            
            OutputStream os = null; //输出流
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file); 
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while(i != -1){
                    os.write(buffer);
                    i = bis.read(buffer);
                }
                
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("----------file download" + filename);
            try {
                bis.close();
                fis.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }
    
    /**
     * 
     * TODO(企业资料的删除) 
     *
     * @Title: delete   
     * @throws
     *
     */
    @RequestMapping("/delete")
    @ResponseBody
    public void delete(){
    	//前端获取
    	/*long templateId = 9L;
    	docTempService.delete(templateId);*/
    	String fileName = "xx.xlsx";
    	String dir = "公司id"+"/version";
        String path = "F:/"+dir ;      
        File dest = new File(path + "/" + fileName);
        dest.delete();
    } 
    
    @RequestMapping("/update")
    @ResponseBody
    public void update(){
    	//前台获取的
    	/*long tempId = 10L;*/
    	/*DocumentTemplate dt = docTempService.findByTemplateId(tempId);
    	long companyId = dt.getCompanyId();
    	String tempName = dt.getTemplateName();
    	String version = dt.getVersionNumber();*/
    /*	String filePath = "F:/公司";
    	File fileLast = new File(filePath+"/"+"version"+"/财政项目拨款.xlsx");
        File newFileLast = new File(filePath+"/"+"version"+"/cc.xlsx");       
        fileLast.renameTo(newFileLast);
    	 File file = new File(filePath + "/" +"version");
         File newFile = new File(filePath+"/"+"ver");       
         file.renameTo(newFile);
         File dest = new File(filePath);
         File newDest = new File("F:"+"/"+"公司id");
         dest.renameTo(newDest);*/
         Document doc = new Document();
         //doc.setDocId(3);
         //doc.setVNum("2");
         docService.save(doc);
    }
	
}
