package com.hndfsj.framework.objects;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.hndfsj.framework.utils.UrlUtils;

/**
 * RWFramework的请求类，可以获取请求对应的URL和METHOD等信息
 *
 * @author ibm
 * @date   Apr 8, 2010
 */
public class RWRequest {
	
	private String url;//请求的URL
	private String method;//请求的方法
	
	/**
	 * 构造函数，处理Request对象，生成URL和METHOD等信息
	 * 
	 * @param request
	 */
	public RWRequest(HttpServletRequest request) {
		
		String sUrl = UrlUtils.getRequestUrl(request);
		String sMethod = request.getMethod();
		//==去掉请求参数
		sUrl = StringUtils.substringBefore(sUrl,"?");
		//==pre请求预处理
		if (StringUtils.endsWithIgnoreCase(sUrl, "/pre")) {
			sUrl = StringUtils.removeEnd(sUrl, "/pre");
			sMethod = StringUtils.substringAfterLast(sUrl,"/").toUpperCase();
			sUrl = StringUtils.substringBeforeLast(sUrl,"/");
		}
			
		this.url = sUrl;
		this.method = sMethod;
	}
	public RWRequest() {
	}
	

	public RWRequest(String url, String method) {
		this.url = url;
		this.method = method;
	}


	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}


	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public static void  main(String args[]){
		String sUrl = "rest/module/new/put/pre";
		sUrl = StringUtils.removeEnd(sUrl, "/pre");
		String sMethod = StringUtils.substringAfterLast(sUrl,"/").toUpperCase();
		sUrl = StringUtils.substringBeforeLast(sUrl,"/");
		System.out.println(sUrl+"  "+sMethod);
	}
}
