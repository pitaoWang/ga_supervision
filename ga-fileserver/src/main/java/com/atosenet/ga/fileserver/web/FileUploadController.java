package com.atosenet.ga.fileserver.web;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.atosenet.ga.fileserver.common.FileInfo;
import com.atosenet.ga.fileserver.storage.StorageProperties;
import com.atosenet.ga.fileserver.storage.StorageService;

@RestController
public class FileUploadController {

    private final StorageService storageService;
    
    private final StorageProperties properties;

    @Autowired
    public FileUploadController(StorageService storageService, StorageProperties properties) {
        this.storageService = storageService;
        this.properties = properties;
    }

    @GetMapping("/file/**")
    public ResponseEntity<Resource> serveFile(HttpServletRequest request) throws UnsupportedEncodingException {
    	String url = request.getRequestURI();
    	if(url.startsWith("/file/")) {
    		url = url.replace("/file/", "");
    		Resource file = storageService.loadAsResource(url);
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=\"" + new String(file.getFilename().getBytes("UTF-8"),"iso-8859-1") + "\"").body(file);
    	} else {
    		throw new IllegalArgumentException("invalid url");
    	}
        
    }
    

    @PostMapping("/upload")
    @ResponseBody
    public FileInfo handleFileUpload(@RequestParam(value = "path", required = false) String path, 
    		@RequestParam(value = "name", required = false) String name, 
    		@RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes) {

    	if(!StringUtils.isEmpty(path)) {
    		
    	}
        String storedFilePath = storageService.store(file, path, name);
        FileInfo fileInfo = new FileInfo();
        fileInfo.setName(file.getOriginalFilename());
//        fileInfo.setUrl(properties.getDomain() + "/" + storedFilePath);
        fileInfo.setUrl("/" + storedFilePath);
        
        return fileInfo;
    }
    
    @PostMapping("/upload/companyfile")
    @ResponseBody
    public FileInfo handleCompanyFileUpload(@RequestParam(value = "path", required = false) String path, 
    		@RequestParam(value = "name", required = false) String name, 
    		@RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes) {

    	if(!StringUtils.isEmpty(path)) {
    		
    	}
        String storedFilePath = storageService.storeDoc(file, path, name);
        FileInfo fileInfo = new FileInfo();
        fileInfo.setName(file.getOriginalFilename());
//        fileInfo.setUrl(properties.getDomain() + "/" + storedFilePath);
        fileInfo.setUrl("/" + storedFilePath);
        
        return fileInfo;
    }

    @ExceptionHandler(Exception.class)
    public FileInfo handleStorageFileNotFound(Exception exc) {
    	FileInfo fileInfo = new FileInfo();
    	fileInfo.setResultCode(500);
    	fileInfo.setMessage(exc.getMessage());
        return fileInfo;
    }

}
