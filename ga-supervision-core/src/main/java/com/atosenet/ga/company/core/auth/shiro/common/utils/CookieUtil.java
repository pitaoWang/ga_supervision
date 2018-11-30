package com.atosenet.ga.company.core.auth.shiro.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;

public class CookieUtil {

	public static Cookie createCookie(String key, String value) {
		return createCookie(key, value, true);
	}

	public static Cookie createCookie(String key, String value, boolean httpOnly) {
		try {
			Cookie cookie = new Cookie(key, URLEncoder.encode(value, "UTF-8"));
			cookie.setHttpOnly(httpOnly);
			cookie.setMaxAge(-1);
			cookie.setPath("/");
			return cookie;
		} catch (UnsupportedEncodingException e) {
		}
		return null;

	}

	public static String findCookieValue(Cookie[] cookies, String key) {
		try {
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals(key)) {
						return URLDecoder.decode(cookie.getValue(), "UTF-8");
					}
				}
			}
		} catch (UnsupportedEncodingException e) {
		}
		return null;
	}

	public static Cookie expireCookieWithPath(String key, String path) {
		Cookie cookie = new Cookie(key, null);
		cookie.setMaxAge(0);
		cookie.setPath(path);
		return cookie;
	}
}