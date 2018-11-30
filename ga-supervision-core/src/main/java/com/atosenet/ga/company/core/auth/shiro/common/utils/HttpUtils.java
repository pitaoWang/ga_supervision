package com.atosenet.ga.company.core.auth.shiro.common.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.atosenet.ga.company.core.common.model.response.ApiResponse;

public class HttpUtils {
	private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);
	public static final String STATUS_CODE_KEY = "_status_code";
	public static final String TOKEN_NAME = "_token";
	/**
	 * 发送 post请求访问本地应用并根据传递参数不同返回不同结果
	 */
	public static JSONObject post(String url, Map<String, String> params) {
		logger.debug("Post: url={}, params={}", url, params);
		// 获取可关闭的 httpCilent
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// 配置超时时间
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(1000).setConnectionRequestTimeout(1000)
				.setSocketTimeout(1000).setRedirectsEnabled(true).build();

		HttpPost httpPost = new HttpPost(url);
		// 设置超时时间
		httpPost.setConfig(requestConfig);
		// 装配post请求参数
		List<BasicNameValuePair> paramslist = new ArrayList<BasicNameValuePair>();
		if (params != null) {
			for (String key : params.keySet()) {
				paramslist.add(new BasicNameValuePair(key, params.get(key))); // 请求参数
			}
		}

		try {
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramslist, "UTF-8");
			// 设置post求情参数
			httpPost.setEntity(entity);
			HttpResponse httpResponse = httpClient.execute(httpPost);
			String strResult = "";
			if (httpResponse != null) {
				int statusCode = httpResponse.getStatusLine().getStatusCode();
				strResult = EntityUtils.toString(httpResponse.getEntity());
				JSONObject resdata = JSONObject.parseObject(strResult);
				logger.debug("Post: response data={}", resdata.toJSONString());
				resdata.put(STATUS_CODE_KEY, statusCode);
				return resdata;
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		} finally {
			try {
				if (httpClient != null) {
					httpClient.close(); // 释放资源
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * 发送 post请求访问本地应用并根据传递参数不同返回不同结果
	 */
	public static JSONObject post(String url, Map<String, String> params, HttpServletRequest request) {
		logger.debug("Post: url={}, params={}", url, params);
		// 获取可关闭的 httpCilent
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// 配置超时时间
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000).setConnectionRequestTimeout(5000)
				.setSocketTimeout(100000).setRedirectsEnabled(true).build();

		HttpPost httpPost = new HttpPost(url);
		// 设置超时时间
		httpPost.setConfig(requestConfig);
		if(request != null) {
			String authHeader = request.getHeader("Authorization");
			if(StringUtils.isNotEmpty(authHeader)) {
				httpPost.setHeader("Authorization", authHeader);
			} else {
				String token = request.getParameter(TOKEN_NAME);
				if(StringUtils.isNotEmpty(token)) {
					httpPost.setHeader("Authorization", "Bearer " + token);
				}
			}
		}
		// 装配post请求参数
		List<BasicNameValuePair> paramslist = new ArrayList<BasicNameValuePair>();
		if (params != null) {
			for (String key : params.keySet()) {
				paramslist.add(new BasicNameValuePair(key, params.get(key))); // 请求参数
			}
		}

		try {
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramslist, "UTF-8");
			// 设置post求情参数
			httpPost.setEntity(entity);
			HttpResponse httpResponse = httpClient.execute(httpPost);
			String strResult = "";
			if (httpResponse != null) {
				int statusCode = httpResponse.getStatusLine().getStatusCode();
				strResult = EntityUtils.toString(httpResponse.getEntity());
				JSONObject resdata = JSONObject.parseObject(strResult);
				logger.debug("Post: response data={}", resdata.toJSONString());
				resdata.put(STATUS_CODE_KEY, statusCode);
				return resdata;
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		} finally {
			try {
				if (httpClient != null) {
					httpClient.close(); // 释放资源
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 发送 get请求
	 * @return 
	 */
	public static ApiResponse<?>  get(String url, Map<String, String> params) {
		logger.debug("Get: url={}, params={}", url, params);
		return get(url, params, null);
	}
	
	/**
	 * 发送 get请求
	 * @return 
	 */
	public static ApiResponse<?>  get(String url, Map<String, String> params, HttpServletRequest request) {
		logger.debug("Get: url={}, params={}", url, params);
		CloseableHttpClient httpCilent = HttpClients.createDefault();
		StringBuilder sb = new StringBuilder(url);
		ApiResponse<Object> api = new ApiResponse<>();
		if(params != null && params.keySet().size() > 0) {
			sb.append("?");
			for(String key : params.keySet()) {
				if(TOKEN_NAME.equals(key)) continue;
				sb.append(key + "=" + params.get(key) + "&");
			}
		}
		HttpGet httpGet = new HttpGet(sb.toString());
		
		if(request != null) {
			String authHeader = request.getHeader("Authorization");
			if(StringUtils.isNotEmpty(authHeader)) {
				httpGet.setHeader("Authorization", authHeader);
			} else {
				String token = request.getParameter(TOKEN_NAME);
				if(StringUtils.isNotEmpty(token)) {
					httpGet.setHeader("Authorization", "Bearer " + token);
				}
			}
		}
		
		try {
			HttpResponse httpResponse = httpCilent.execute(httpGet);
			String strResult = "";
			if (httpResponse != null) {
				int statusCode = httpResponse.getStatusLine().getStatusCode();
				strResult = EntityUtils.toString(httpResponse.getEntity());
				JSONObject resdata = JSONObject.parseObject(strResult);
				Object data = (Object) resdata.get("data");
				logger.debug("Get: response data={}", resdata.toJSONString());
				resdata.put(STATUS_CODE_KEY, statusCode);
				api.setResultCode(statusCode);
				api.setData(data);
				return api;
			} else {
				return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				httpCilent.close();// 释放资源
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 发送 get请求
	 * @return 
	 */
	public static JSONObject  gets(String url, Map<String, String> params) {
		logger.debug("Get: url={}, params={}", url, params);
		return gets(url, params, null);
	}

	/**
	 * 发送 get请求
	 * @return 
	 */
	public static JSONObject  gets(String url, Map<String, String> params, HttpServletRequest request) {
		logger.debug("Get: url={}, params={}", url, params);
		CloseableHttpClient httpCilent = HttpClients.createDefault();
		StringBuilder sb = new StringBuilder(url);
		if(params != null && params.keySet().size() > 0) {
			sb.append("?");
			for(String key : params.keySet()) {
				if(TOKEN_NAME.equals(key)) continue;
				sb.append(key + "=" + params.get(key) + "&");
			}
		}
		HttpGet httpGet = new HttpGet(sb.toString());
		
		if(request != null) {
			String authHeader = request.getHeader("Authorization");
			if(StringUtils.isNotEmpty(authHeader)) {
				httpGet.setHeader("Authorization", authHeader);
			}
		}
		
		try {
			HttpResponse httpResponse = httpCilent.execute(httpGet);
			String strResult = "";
			if (httpResponse != null) {
				int statusCode = httpResponse.getStatusLine().getStatusCode();
				strResult = EntityUtils.toString(httpResponse.getEntity());
				JSONObject resdata = JSONObject.parseObject(strResult);
				logger.debug("Get: response data={}", resdata.toJSONString());
				resdata.put(STATUS_CODE_KEY, statusCode);
				return resdata;
			} else {
				return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				httpCilent.close();// 释放资源
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 发送 get请求
	 * @return 
	 */
	public static ApiResponse<String>  getDoc(String url, Map<String, String> params, HttpServletRequest request, HttpServletResponse response) {
		logger.debug("Get: url={}, params={}", url, params);
		CloseableHttpClient httpCilent = HttpClients.createDefault();
		StringBuilder sb = new StringBuilder(url);
		ApiResponse<String> api = new ApiResponse<>();
		if(params != null && params.keySet().size() > 0) {
			sb.append("?");
			for(String key : params.keySet()) {
				if(TOKEN_NAME.equals(key)) continue;
				sb.append(key + "=" + params.get(key) + "&");
			}
		}
		HttpGet httpGet = new HttpGet(sb.toString());
		
		if(request != null) {
			String authHeader = request.getHeader("Authorization");
			if(StringUtils.isNotEmpty(authHeader)) {
				//获取token
				 String toke = authHeader.substring(7,authHeader.length());	
				 Cookie cookie = new Cookie("token",toke);
				 response.addCookie(cookie);
				 httpGet.setHeader("Authorization", authHeader);
			} else {
				 String token = request.getParameter(TOKEN_NAME);
				 
				if(StringUtils.isNotEmpty(token)) {
					httpGet.setHeader("Authorization", "Bearer " + token);
					Cookie cookie = new Cookie("token",token);
				    response.addCookie(cookie);
				}
			}
		}
		
		try {
			HttpResponse httpResponse = httpCilent.execute(httpGet);
			String strResult = "";
			if (httpResponse != null) {
				int statusCode = httpResponse.getStatusLine().getStatusCode();
				strResult = EntityUtils.toString(httpResponse.getEntity());
				api.setResultCode(statusCode);
				api.setData(strResult);
				return api;
			} else {
				return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				httpCilent.close();// 释放资源
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 发送 post请求doc
	 * @return 
	 */
	public static ApiResponse<String>  postDoc(String url, Map<String, String> params, HttpServletRequest request, HttpServletResponse response) {
		logger.debug("Post: url={}, params={}", url, params);
		// 获取可关闭的 httpCilent
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// 配置超时时间
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectTimeout(1000)
				.setConnectionRequestTimeout(1000)
				.setSocketTimeout(0)
				.setRedirectsEnabled(true).build();
		HttpPost httpPost = new HttpPost(url);
		ApiResponse<String> api = new ApiResponse<>();
		// 设置超时时间
		httpPost.setConfig(requestConfig);
		if(request != null) {
			String authHeader = request.getHeader("Authorization");
			//获取token
			String toke=null;
			if(authHeader!=null) {
				   toke = authHeader.substring(7,authHeader.length());	
				   Cookie cookie = new Cookie("token",toke);
				   response.addCookie(cookie);
			}
			if(StringUtils.isNotEmpty(authHeader)) {
				httpPost.setHeader("Authorization", authHeader);
			} else {
				String token = request.getParameter(TOKEN_NAME);
				if(StringUtils.isNotEmpty(token)) {
					Cookie cookie = new Cookie("token",token);
					response.addCookie(cookie);
					httpPost.setHeader("Authorization", "Bearer " + token);
				}
			}
		}
		
		// 装配post请求参数
		List<BasicNameValuePair> paramslist = new ArrayList<BasicNameValuePair>();
		if (params != null) {
			for (String key : params.keySet()) {
				paramslist.add(new BasicNameValuePair(key, params.get(key))); // 请求参数
			}
		}

		try {
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramslist, "UTF-8");
			// 设置post求情参数
			httpPost.setEntity(entity);
			HttpResponse httpResponse = httpClient.execute(httpPost);
			String strResult = "";
			if (httpResponse != null) {
				int statusCode = httpResponse.getStatusLine().getStatusCode();
				strResult = EntityUtils.toString(httpResponse.getEntity());
				api.setResultCode(statusCode);
				api.setData(strResult);
				return api;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (httpClient != null) {
					httpClient.close(); // 释放资源
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
