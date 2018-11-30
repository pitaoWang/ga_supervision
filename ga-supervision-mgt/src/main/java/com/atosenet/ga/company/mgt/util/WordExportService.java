package com.atosenet.ga.company.mgt.util;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Service
public class WordExportService {
	@Autowired
	private Configuration configuration;

	public void exportMillCertificateWord(HttpServletRequest request, HttpServletResponse response, Map<String, ?> map)
			throws IOException {
		Object model = map;

		SimpleDateFormat sdf = null;

		try {
			Template template = configuration.getTemplate("doc/flight_record.ftl");
			String dataToExport = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

			response.setCharacterEncoding("utf-8");
			response.setContentType("application/msword");
			// 设置浏览器以下载的方式处理该文件名
			sdf = new SimpleDateFormat("yyyyMMddhhmmss");
			String time = sdf.format(new Date());

			String fileName = "flightrecord" + time + ".doc";
			response.setHeader("Content-Disposition",
					"attachment;filename=".concat(String.valueOf(URLEncoder.encode(fileName, "UTF-8"))));

			IOUtils.write(dataToExport, response.getOutputStream(), "UTF-8");
		} catch (TemplateException e) {
			e.printStackTrace();
		} finally {
			
		}
	}
}
