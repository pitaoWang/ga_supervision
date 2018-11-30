package com.atosenet.ga.company.mgt.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.atosenet.ga.company.monitor.counter.WebCounter;
import com.atosenet.ga.company.monitor.counter.annotation.Counter;

public class WebCounterInterceptor implements HandlerInterceptor {

	@Autowired
	private WebCounter webCounter;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		if(handler instanceof HandlerMethod) {
			HandlerMethod had = (HandlerMethod) handler;
			// 获取自定义注解
			Counter counter = had.getMethod().getAnnotation(Counter.class);
			if (counter != null) {
				String name = counter.value();
				webCounter.visitCount(name);
			}
		}
	}

}
