package com.atosenet.ga.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.atosenet.ga.utils.DataResponse;

/**
 * 图片/文件上传
 * 
 * @author haosi
 *
 */
@Controller
@RequestMapping("/app/api/upload")
public class UploadController {
	
	@Value("${image.upload.avatar.path}")
	private String uploadAvatarPath = null;
	
//	@RequestMapping(value = "/body", method = RequestMethod.POST)
//	public void getBody(HttpServletRequest request, HttpServletResponse response)
//			throws IOException, FileUploadException {
//		Map<String, Object> json = new HashMap<String, Object>();
//		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//
//		/** 页面控件的文件流* */
//		MultipartFile multipartFile = null;
//		Map map = multipartRequest.getFileMap();
//		for (Iterator i = map.keySet().iterator(); i.hasNext();) {
//			Object obj = i.next();
//			multipartFile = (MultipartFile) map.get(obj);
//
//		}
//		/** 获取文件的后缀* */
//		String filename = multipartFile.getOriginalFilename();
//
//		try {
//
//			InputStream inputStream = multipartFile.getInputStream();
//			File tmpFile = File.createTempFile(filename, filename.substring(filename.lastIndexOf(".")));
//			FileUtils.copyInputStreamToFile(inputStream, tmpFile);
//			
//			System.out.println(tmpFile.getAbsolutePath());
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
    @RequestMapping(value = "/avatar", method = RequestMethod.POST)
	public void avatarUpload(HttpServletRequest request, HttpServletResponse response)
    {
    	MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
    	
    	List<String> images = new ArrayList<String>();
		Map<String, MultipartFile> map = multipartRequest.getFileMap();
		
		DataResponse<List<String>> resp = new DataResponse<List<String>>();
		
		for (Iterator<String> i = map.keySet().iterator(); i.hasNext();) {
			Object obj = i.next();
			MultipartFile multipartFile = (MultipartFile) map.get(obj);
			
			try 
			{
				String filename = multipartFile.getOriginalFilename();
				InputStream inputStream = multipartFile.getInputStream();
				String path = request.getServletContext().getRealPath("/")+"WEB-INF";
				File dir = new File(path+uploadAvatarPath);
				if(!dir.exists())
				{
					dir.mkdirs();
				}
				File dest = new File(dir, filename);
//				File tmpFile = File.createTempFile(filename, );
				FileUtils.copyInputStreamToFile(inputStream, dest);
				
				images.add(uploadAvatarPath+"/"+filename);
				
				resp.setResultCode(200);
				resp.setData(images);

			} 
			catch (Exception e) 
			{
				resp.setResultCode(500);
				resp.setMessage(e.getMessage());
			}
		}
		
		try {
//			System.out.println(JSON.toJSONString(resp));
			response.setHeader("Content-type", "text/html;charset=UTF-8");  
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(JSON.toJSONString(resp));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
