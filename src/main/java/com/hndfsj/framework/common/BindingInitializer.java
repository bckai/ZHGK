package com.hndfsj.framework.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

/**
 * 
 * 类描述：日期绑定处理 创建人：wxg 创建时间：Oct 16, 2009 5:25:10 PM
 * 
 */
public class BindingInitializer implements WebBindingInitializer {

	public void initBinder(WebDataBinder binder, WebRequest request) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");// HH:mm:ss
		dateFormat.setLenient(false);// 严格执行format匹配规则
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));

	}

}
