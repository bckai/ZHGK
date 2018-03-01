package com.hndfsj.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hndfsj.framework.common.MReturnObject;
import com.hndfsj.framework.utils.RegexUtils;

@Component
public class HandlerExceptionResolverImpl implements HandlerExceptionResolver {
	private Logger log = LoggerFactory.getLogger(HandlerExceptionResolverImpl.class);

	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		ModelAndView modelAndView = null;
		this.log.error("[应用捕获]:" ,ex);
		if (RegexUtils.match(request.getHeader("Accept"), "application/json")) {
			MReturnObject json = new MReturnObject(MReturnObject.ERROR, "系统异常，请联系管理员！", ex);
			response.setContentType("application/json");
			response.setStatus(500);
			try {
				JSON.writeJSONString(response.getOutputStream(), json, SerializerFeature.QuoteFieldNames);
			} catch (IOException e) {
			}
		} else {
			modelAndView = new ModelAndView();
			modelAndView.addObject("errorMessage", ex);
			modelAndView.setViewName("error");
		}
		return modelAndView;
	}

}
