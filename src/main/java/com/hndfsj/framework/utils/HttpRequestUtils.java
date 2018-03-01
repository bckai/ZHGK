package com.hndfsj.framework.utils;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpRequestUtils {

	public static String toRequestParams(ServletRequest request) {
		Enumeration<String> enumeration = request.getParameterNames();
		StringBuffer sb = new StringBuffer();
		for (; enumeration.hasMoreElements();) {
			String key = (String) enumeration.nextElement();
			sb.append(key);
			if (key != null) {
				sb.append(":");
				String[] values = request.getParameterValues(key);
				sb.append(Arrays.toString(values));
			}
			sb.append(";");
		}
		return sb.toString();
	}
	/**
	 * 获取客户端ip
	 * 
	 * @param request
	 * @return
	 */
	public static  String getClientAddress(HttpServletRequest request) {
		String address = request.getHeader("X-Forwarded-For");
		if (address != null && isIpAddress(address)) {
			return address;
		}
		return request.getRemoteAddr();
	}
	/**
	 * ip校验
	 * 
	 * @param s
	 * @return
	 */
	private static Boolean isIpAddress(String s) {
		String regex = "(((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.](((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.](((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.](((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(s);
		return m.matches();
	}

	/**
	 * 发送Redirect请求，供无权限重新登录调用
	 * 
	 * @param request
	 * @param response
	 * @param url
	 * @param useRelativeContext
	 * @throws IOException
	 * @throws ServletException
	 */
	public static final void sendRedirect(HttpServletRequest request, HttpServletResponse response, String url,
			boolean useRelativeContext) throws IOException, ServletException {
		String finalUrl;
		if (!url.startsWith("http://") && !url.startsWith("https://")) {
			if (useRelativeContext) {
				finalUrl = url;
			} else {
				finalUrl = request.getContextPath() + url;
			}
		} else if (useRelativeContext) {
			// Calculate the relative URL from the fully qualifed URL, minus the
			// protocol and base context.
			int len = request.getContextPath().length();
			int index = url.indexOf(request.getContextPath()) + len;
			finalUrl = url.substring(index);

			if (finalUrl.length() > 1 && finalUrl.charAt(0) == '/') {
				finalUrl = finalUrl.substring(1);
			}
		} else {
			finalUrl = url;
		}
		response.sendRedirect(finalUrl);
	}
}
