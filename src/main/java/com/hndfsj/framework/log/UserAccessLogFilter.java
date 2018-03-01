/**
 * 
 */
package com.hndfsj.framework.log;

import java.io.IOException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hndfsj.framework.config.RWAdminConfiguration;
import com.hndfsj.framework.config.RWAdminConstant;
import com.hndfsj.framework.objects.RWRequest;
import com.hndfsj.framework.security.context.RWAdminContext;
import com.hndfsj.framework.utils.DateUtils;

/**
 * 用户访问日志类
 * 
 * @author wfq
 * 
 */
public class UserAccessLogFilter implements Filter {

	private static Log logger = LogFactory.getLog("userAccess");

	private static Logger log=LoggerFactory.getLogger(UserAccessLogFilter.class);
	
	private static final String REQUESTDISPATCHER = RWAdminConfiguration.getInstance().getAppPropsValue("requestDispatcher");
	private static final String JUDGE_REQUESTDISPATCHER = RWAdminConfiguration.getInstance().getAppPropsValue("judge-requestDispatcher");

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String ip = getClientAddress(req);
		String requestURL = req.getRequestURL().toString();
		String query = req.getQueryString();
		StringBuffer sb = new StringBuffer();
		HttpSession session = req.getSession(false);

		String sessionId = null;
		if (session != null)
			sessionId = session.getId();

		String[] noFomat = new String[] { ".js", ".css", ".gif", ".jpg", ".swf", ".png" };

		
		if (!validateUrl(requestURL, noFomat)) {

			chain.doFilter(request, response);
		} else {
			// 记录访问日志
			RWAdminContext context = null;
			String username = "NO Login";
			if (session != null) {
				context = (RWAdminContext) session
						.getAttribute(RWAdminConstant.RW_ADMIN_CONTEXT_IN_SESSION);
			}
			if (context != null) {
				username = String.valueOf(context.getCurrentUser().getUsername());
			}

			sb.append("User Name:[").append(username).append("],sessionId:[" + sessionId + "],IP:[").append(ip).append("],Access Time:["+DateUtils.convertDate2String("yyyy-MM-dd HH:mm",new Date()))
					.append("],Access URL:[").append(requestURL+"]");
			if (query != null) {
				sb.append("?:[").append(query+"]");
			}
			logger.info(sb.toString());
			
			if(((HttpServletRequest)request).getRequestURI().indexOf(JUDGE_REQUESTDISPATCHER)==-1){
				//if(!((HttpServletRequest)request).getRequestURI().endsWith("/rest/default/login/check")){
					if (context == null) {
						request.getRequestDispatcher(REQUESTDISPATCHER).forward(request, response);
						return;
					}
				//}
			}
			chain.doFilter(request, response);
		}

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("init UserAccessLogFilter!!!");
		log.info("init UserAccessLogFilter!!!");
	}

	/**
	 * 获取客户端ip
	 * 
	 * @param request
	 * @return
	 */
	private String getClientAddress(HttpServletRequest request) {
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
	private Boolean isIpAddress(String s) {
		String regex = "(((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.](((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.](((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.](((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(s);
		return m.matches();
	}

	/**
	 * 如果字符串url是否以指定的数组的任一字符串结尾
	 * 
	 * @param url
	 *            指定字符串
	 * @param noFomat
	 *            格式集
	 * @return url不是以指定的数组的任一字符串结尾 返回 true 否则返回false
	 */
	private static boolean validateUrl(String url, String[] noFomat) {
		// 正则非
		String endStr = "";
		// 为空时返回true
		if (noFomat == null || noFomat.length - 0 == 0 || url == null) {
			return true;
		} else {
			for (int i = 0; i < noFomat.length; i++) {
				if (i - 0 == 0) {
					endStr = "((\\" + noFomat[i] + ")";
				} else {
					endStr = endStr + "|(\\" + noFomat[i] + ")";
				}
				if (i - noFomat.length + 1 == 0) {
					endStr = endStr + ")$";
				}
			}
		}
		// 定义正则
		String reg = "^.*";
		// 验证文件后缀
		// 其中参数意义：不区分大小写的,默认只有一个参数时是区分大小写
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(reg + endStr,
				java.util.regex.Pattern.CASE_INSENSITIVE);
		return !p.matcher(url).matches();
	}
	
}
