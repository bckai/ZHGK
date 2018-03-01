package com.hndfsj.framework.intercepters;

import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hndfsj.admin.domain.UserLoginLog;
import com.hndfsj.admin.domain.UserOperLogs;
import com.hndfsj.admin.service.IUserLoginLogService;
import com.hndfsj.admin.service.IUserOperLogsService;
import com.hndfsj.admin.service.IUserService;
import com.hndfsj.framework.common.MReturnObject;
import com.hndfsj.framework.config.Configuration;
import com.hndfsj.framework.config.EnumType;
import com.hndfsj.framework.config.RWAdminConfiguration;
import com.hndfsj.framework.config.RWAdminConstant;
import com.hndfsj.framework.exceptions.RWAdminContextException;
import com.hndfsj.framework.exceptions.RWAdminResourceException;
import com.hndfsj.framework.exceptions.RWAdminUserException;
import com.hndfsj.framework.objects.RWRequest;
import com.hndfsj.framework.security.bean.RWUserDetails;
import com.hndfsj.framework.security.context.RWAdminContext;
import com.hndfsj.framework.security.context.RWAdminContextHolder;
import com.hndfsj.framework.security.context.RWAdminContextImpl;
import com.hndfsj.framework.security.manager.RWAdminSecurityManager;
import com.hndfsj.framework.utils.HttpRequestUtils;
import com.hndfsj.framework.utils.RegexUtils;
import com.hndfsj.framework.utils.RegularUtils;
import com.hndfsj.framework.utils.RequestUrlFilter;
import com.hndfsj.framework.utils.SpringContextHolder;
import com.hndfsj.framework.utils.UrlUtils;
import com.hndfsj.framework.utils.regex.UrlMatcher;
import com.hndfsj.framework.utils.wx.AppUtils;

public class RequestIntercepter extends HandlerInterceptorAdapter {

	private static Logger log = LoggerFactory.getLogger(RequestIntercepter.class);

	private static final String REST_URL_METHOD_SEPARATOR_CHARS = " ";
	private static RWAdminConfiguration configuration = RWAdminConfiguration.getInstance();

	private static final String LOGOUT_PAGE = RWAdminConfiguration.getInstance().getAppPropsValue("loginout-page");
	private static final String LOGIN_SUBMIT_PAGE = configuration.getAppPropsValue("login-submit-page");// 本地验证时登录提交页面
	private static final String WX_LOGIN_SUBMIT_PAGE = configuration.getAppPropsValue("wx-login-submit-page");// 本地验证时登录提交页面
	private static final String LOGIN_PAGE = configuration.getAppPropsValue("login-index-page");// //本地验证时登录首页面
	private static final String AUTHENTICATION_ERROR_PAGE = RWAdminConfiguration.getInstance()
			.getAppPropsValue("authentication-error-url");// 校验安全资源权限失败
	private static final String COMMON_NON_INTERCEPT_PATH = configuration.getAppPropsValue("common-non-intercept-url");// 不需要拦截的页面;
	private static final String NON_INTERCEPT_PATH = configuration.getAppPropsValue("non-intercept-url");// 通过本地验证时不需要拦截的页面
	private static final String[] INSERT_LOG_URLS = configuration.getAppPropsValue("insert.request.params.url", "")
			.split(",");
	private static UrlMatcher matcher = UrlUtils.createUrlMatcher();
	private static RWAdminSecurityManager securityManager;// 安全管理器
	@Autowired
	private IUserLoginLogService userLoginLogService;// 用户登录日志
	//@Autowired
	//private IUserLoginQrcodeService userLoginQrcodeService;// 用户登录 
	@Autowired
	private IUserService userService;// 用户 

	@Autowired
	public void setSecurityManager(RWAdminSecurityManager securityManager) {
		RequestIntercepter.securityManager = securityManager;
	}

	/* ==========主验证方法========== */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		setReponseConfig(response);
		// 初始化缓存
		securityManager.initResourceInCache();
		String realRequestURI = request.getRequestURI();
		request.getRequestURL();
		// log.debug(" === START === RequestURI : [" + realRequestURI + "]");
		RWRequest rwRequest = new RWRequest(request);
		// ==获取请求URL并进行预处理
		String requestUrl = rwRequest.getUrl();
		HttpRequestUtils.toRequestParams(request);
		if (!isCurrentHandRoutePrefix(requestUrl)) {
			return true;// 跳出此拦截器
		}
		if (isXSSRequest(UrlUtils.getRequestUrl(request), request)) {
			log.debug(" === END ===  RequestURI : [" + realRequestURI + "]" + "有非法参数已被拦截");
			if (request.getHeader("Accept").contains("application/json")) {
				setJsonData2Response(response, MReturnObject.ERROR, "有非法字符", null);
			} else {
				HttpRequestUtils.sendRedirect(request, response, "/common/503.jsp", false);
			}
			return false;
		}

		// 0.初始化RWAdminContext
		HttpSession userSession = safeGetSession(request, true);

		RWAdminContext adminContextBeforeHandle = getSecurityContext(userSession);
		try {
			RWAdminContextHolder.setContext(adminContextBeforeHandle);

		} catch (RWAdminContextException e) {
			e.printStackTrace();
		}

		RWUserDetails currentUserDetails = RWAdminContextHolder.getCurrentUser();
		if (isnotIntercepterRequest(realRequestURI, rwRequest, requestUrl, userSession, currentUserDetails)) {
			return true;
		} else {
			if (currentUserDetails == null || isLogin(requestUrl)) {
				boolean isLoginSuccess = loginToAuth(request, response, realRequestURI, requestUrl, userSession,
						adminContextBeforeHandle, currentUserDetails);
				currentUserDetails = RWAdminContextHolder.getCurrentUser();
				if (isLoginSuccess && currentUserDetails != null) {
					// 增加登录时间
					insertLoginTime(currentUserDetails, request);
				}
				return isLoginSuccess;
			} else {
				// ==不需要验证的资源处理
				if (nonNeedFilter(rwRequest, currentUserDetails.getUserType())) {
					log.debug(" === END ===  RequestURI : [" + realRequestURI + "]" + " 用户不拦截页面  true ");
					return true;
				}
				insertOpreLog(requestUrl, currentUserDetails, request);
				return resourceAuthentication(request, response, rwRequest, currentUserDetails);
			}
		}

	}

	private static boolean isLogin(String requestUrl) {
		return matcher.pathMatchesUrl(matcher.compile(LOGIN_SUBMIT_PAGE), requestUrl);
	}

	private static boolean isWXLogin(String requestUrl) {
		return matcher.pathMatchesUrl(matcher.compile(WX_LOGIN_SUBMIT_PAGE), requestUrl);
	}

	private static void insertOpreLog(String requestURL, RWUserDetails currentUser, ServletRequest request) {
		UrlMatcher matcher = UrlUtils.createUrlMatcher();
		for (String url : INSERT_LOG_URLS) {
			url = url.replaceAll("\\{[\\w]+\\}", "*");
			if ((matcher.pathMatchesUrl(matcher.compile(url), requestURL))) {
				IUserOperLogsService userOperLogsService = SpringContextHolder.getBean("userOperLogsService");
				UserOperLogs userOperLogs = new UserOperLogs(currentUser.getUserId());
				String requestContent = HttpRequestUtils.toRequestParams(request);
				userOperLogs.setRequest(requestContent);
				String content = "操作人：" + currentUser.getRealName() + ",请求路径:" + requestURL;
				userOperLogs.setContent(content);
				;
				userOperLogs.setCreateTime(new Date());
				try {
					userOperLogsService.save(userOperLogs);
				} catch (Exception e) {
					log.error("", userOperLogs.toString());
				}
			}
		}
	}

	public String getRemortIP(HttpServletRequest request) {
		if (request.getHeader("x-forwarded-for") == null) {
			return request.getRemoteAddr();
		}
		return request.getHeader("x-forwarded-for");
	}

	private void insertLoginTime(RWUserDetails currentUserDetails, HttpServletRequest request) {
		try {
			UserLoginLog loginTime = new UserLoginLog(currentUserDetails);
			if (AppUtils.isMobileDevice(request)) {
				loginTime.setPlatform(request.getHeader("User-Agent"));
			} else {
				loginTime.setPlatform("PC");
			}
			loginTime.setIp(getRemortIP(request));
			userLoginLogService.save(loginTime);
		} catch (Exception e) {
			log.error("", e);
		}
	}

	/**
	 * TODO 是否是当前拦截器处理的请求
	 * 
	 * @param requestUrl
	 * @return
	 * @author Mr.Zheng
	 * @version 2015年10月28日 下午2:57:03
	 */
	private boolean isCurrentHandRoutePrefix(String requestUrl) {
		if (".m".equals(requestUrl.substring(requestUrl.length() - 2)) || requestUrl.startsWith("/m")) {
			return false;
		}
		if (requestUrl.startsWith("/resource/imgRes") && RegularUtils.validate("^\\w+[R]{1}[e]{1}[s]{1}/{1}.*",
				requestUrl.substring("/resource/".length()))) {
			return false;
		}
		return true;
	}

	private void setReponseConfig(HttpServletResponse response) {
		// 设置页面不缓存
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	}

	/**
	 * TODO 进行登录信息认证
	 * 
	 * @param request
	 * @param response
	 * @param realRequestURI
	 * @param requestUrl
	 * @param userSession
	 * @param adminContextBeforeHandle
	 * @param currentUserDetails
	 * @return true成功false失败
	 * @throws IOException
	 * @throws ServletException
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @author Mr.Zheng
	 * @version 2015年10月28日 下午2:49:20
	 */
	private boolean loginToAuth(HttpServletRequest request, HttpServletResponse response, String realRequestURI,
			String requestUrl, HttpSession userSession, RWAdminContext adminContextBeforeHandle,
			RWUserDetails currentUserDetails) throws IOException, ServletException {

		try {
			currentUserDetails = validateLoginMessage(request, response, realRequestURI, requestUrl, userSession,
					adminContextBeforeHandle, currentUserDetails);
		} catch (RWAdminUserException e) {
			// 各种验证过期等异常
			// String ss = configuration.getAppPropsValue(e.getMessage());
			setJsonData2Response(response, MReturnObject.ERROR, e.getMessage(), null);
			log.debug(" === END ===  RequestURI : [" + realRequestURI + "]" + " 返回本地用户校验失败  false ");
			return false;
		}

		return initAuth(request, response, realRequestURI, requestUrl, userSession, currentUserDetails);
	}

	/**
	 * TODO 验证登录信息 若也服务认证成功则返回用户信息
	 * 
	 * @param request
	 * @param response
	 * @param realRequestURI
	 * @param requestUrl
	 * @param userSession
	 * @param adminContextBeforeHandle
	 * @param currentUserDetails
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @author Mr.Zheng
	 * @version 2015年10月28日 下午2:40:32
	 */
	private RWUserDetails validateLoginMessage(HttpServletRequest request, HttpServletResponse response,
			String realRequestURI, String requestUrl, HttpSession userSession, RWAdminContext adminContextBeforeHandle,
			RWUserDetails currentUserDetails) throws IOException, ServletException, RWAdminUserException {
		// 1.判断请求的url，如果请求的url属于Cas验证的url，就提取cas的校验信息

		if (isLogin(requestUrl) || isWXLogin(requestUrl)) {// 登录提交页面if(matcher.pathMatchesUrl(matcher.compile(loginSubmitPage),
			// (String)matcher.compile(requestUrl)))
			if (!validCode(request, response, userSession)) {
				throw new RWAdminUserException(configuration.getAppPropsValue("admin.user.authuser.validcodeerror"));
			}
			try {
				currentUserDetails = userValidInLocal(request, adminContextBeforeHandle);
			} catch (RWAdminUserException e) {
				// 各种验证过期等异常
				String ss = configuration.getAppPropsValue(e.getMessage());
				throw new RWAdminUserException(ss == null ? e.getMessage() : ss);
			}
		}
		return currentUserDetails;
	}

	/**
	 * TODO 保存认证信息到session中
	 * 
	 * @param request
	 * @param response
	 * @param realRequestURI
	 * @param requestUrl
	 * @param userSession
	 * @param currentUserDetails
	 * @return
	 * @throws IOException
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws ServletException
	 * @author Mr.Zheng
	 * @version 2015年10月28日 下午2:41:50
	 */
	private boolean initAuth(HttpServletRequest request, HttpServletResponse response, String realRequestURI,
			String requestUrl, HttpSession userSession, RWUserDetails currentUserDetails)
			throws IOException, ServletException {
		// 2.判断经过CasServer验证或者Local验证后是否有当前用户
		if (currentUserDetails != null) {
			// 将校验过的当前用户信息初始化后放入线程和Session中
			RWAdminContextHolder.cleanContext();
			RWAdminContextHolder.initCurrentUser(currentUserDetails);
			userSession.setAttribute(RWAdminConstant.RW_ADMIN_CONTEXT_IN_SESSION, RWAdminContextHolder.getContext());
			log.debug(" === END ===  RequestURI : [" + realRequestURI + "]" + " 用户登录成功  true " + "登录用户: ["
					+ currentUserDetails.getRealName() + "] 返回json到主页");
			return true;
		} else {
			if (isWeixinRequest(requestUrl)) {
				String url = UrlUtils.getRequestUrl(request);
				if (!url.contains("wechat-binding")) {
					//userSession.setAttribute(WeixinCommonController.WX_REQUEST_URL_SESSION, url);
				}
				System.err.println("[" + userSession.getId() + "]" + UrlUtils.getRequestUrl(request));
				/*HttpRequestUtils.sendRedirect(request, response,
						CommonController.createWxLoginUrl(null, "/rest/common/weixin/auto/login", false), false);*/
			} else {
				if (RegexUtils.match(request.getHeader("Accept"), ("application/json"))) {
					setJsonData2Response(response, MReturnObject.TIMEOUT,
							configuration.getAppPropsValue("admin.user.currentuser.null"), null);
				} else {
					HttpRequestUtils.sendRedirect(request, response, LOGIN_PAGE, false);
				}
				log.debug(" === END ===  RequestURI : [" + realRequestURI + "]"
						+ " 用户登录失败  false  currentuser is null  and return json to redirecting to LOGIN_PAGE ");
			}
			return false;
		}
	}

	private boolean isWeixinRequest(String url) {
		String[] Urls = configuration.getAppPropsValue("wx.menu.auto.login.url", "").split(",");
		UrlMatcher matcher = UrlUtils.createUrlMatcher();
		for (String not_url : Urls) {
			not_url = not_url.replaceAll("\\{[\\w]+\\}", "*");
			if ((matcher.pathMatchesUrl(matcher.compile(not_url), url))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 不拦截请求
	 *
	 * @param realRequestURI
	 * @param rwRequest
	 * @param requestUrl
	 * @param userSession
	 * @param currentUserDetails
	 * @return
	 * @author Mr.Zheng
	 * @version 2015年10月28日 下午2:29:44
	 */
	private static boolean isnotIntercepterRequest(String realRequestURI, RWRequest rwRequest, String requestUrl,
			HttpSession userSession, RWUserDetails currentUserDetails) {
		if (matcher.pathMatchesUrl(matcher.compile(LOGOUT_PAGE), (String) matcher.compile(requestUrl))) {// 登出页面,登出页面肯定是本地的登出页面
			if (currentUserDetails != null) {
				cleanSession(userSession);
				log.debug(" === END ===  RequestURI : [" + realRequestURI + "]" + "登出用户: ["
						+ currentUserDetails.getRealName() + "] and return json to redirecting to logout page");
			}
			return true;
		} else if (matcher.pathMatchesUrl(matcher.compile("/rest/default/quit"),
				(String) matcher.compile(requestUrl))) {// 登出页面,登出页面肯定是本地的登出页面
			if (currentUserDetails != null) {
				cleanSession(userSession);
				log.debug(" === END ===  RequestURI : [" + realRequestURI + "]" + "登出用户: ["
						+ currentUserDetails.getRealName() + "] and return json to redirecting to logout page");
			}
			return true;
		} else if ((matcher.pathMatchesUrl(matcher.compile(LOGIN_PAGE), requestUrl))) {// 登录index页面
			cleanSession(userSession);
			log.debug(" === END ===  RequestURI : [" + realRequestURI + "]" + " 登录index页面 true ");
			return true;
		} else if (isLogin(requestUrl)) {// 登录提交页面if(matcher.pathMatchesUrl(matcher.compile(loginSubmitPage),
			cleanSession(userSession);
		} else if (commonNonNeedFilter(rwRequest)) {
			// 公共的不拦截页面
			// ,不需要用户登录信息的。例如:验证码页面|注册页面|忘记密码页面
			// log.debug(" === END ===
			// RequestURI : ["
			// + realRequestURI + "]" +
			// " 公共的不拦截页面 true ");
			return true;
		}
		return false;
	}

	// public static boolean isMobileDevice(HttpServletRequest request) {
	// return
	// hndfsj_Enum_DeviceType.containName(request.getHeader("User-Agent"));
	// }

	// private RWUserDetails autoLogin(HttpServletRequest request, HttpSession
	// userSession,
	// RWUserDetails currentUserDetails) throws RWAdminUserException {
	// if (currentUserDetails == null) {
	// String auth = request.getHeader("auth");
	// if (StringUtils.isNotBlank(auth)) {
	// PageRequest pageRequest = new PageRequest(0, 1);
	// pageRequest.addAndCondition("auth", SearchCondition.EQUAL, auth);
	//
	// }
	// }
	// return currentUserDetails;
	// }

	// private void storeUserDataToSession(HttpSession userSession,
	// RWUserDetails currentUserDetails) {
	// RWAdminContextHolder.cleanContext();
	// RWAdminContextHolder.initCurrentUser(currentUserDetails);
	// userSession.setAttribute(RWAdminConstant.RW_ADMIN_CONTEXT_IN_SESSION,
	// RWAdminContextHolder.getContext());
	// log.debug(" === END === RequestURI : 用户自动登录成功 " + "登录用户: [" +
	// currentUserDetails.getRealName()
	// + "] 返回json到主页");
	// }

	private boolean validCode(HttpServletRequest request, HttpServletResponse response, HttpSession userSession)
			throws IOException {
		// 如果有验证码设置，先校验验证码
		if (StringUtils.isNotBlank(RWAdminConfiguration.getInstance().getAppPropsValue("validcode-in-request"))) {
			if (!StringUtils.equalsIgnoreCase(userSession.getAttribute(RWAdminConstant.SESSION_VALIDATECODE).toString(),
					request.getParameter(
							RWAdminConfiguration.getInstance().getAppPropsValue("validcode-in-request")))) {

				return false;
			}
		}
		return true;
	}

	public static void cleanSession(HttpSession userSession) {

		// auditLogService.saveLogout();
		RWAdminContextHolder.cleanContext();
		userSession.removeAttribute(RWAdminConstant.RW_ADMIN_CONTEXT_IN_SESSION);
	}

	/**
	 * 资源认证
	 *
	 * @param request
	 * @param response
	 * @param rwRequest
	 * @param currentUserDetails
	 * @return
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws RWAdminUserException
	 * @throws IOException
	 * @throws ServletException
	 * @author wfq
	 */
	private static boolean resourceAuthentication(HttpServletRequest request, HttpServletResponse response,
			RWRequest rwRequest, RWUserDetails currentUserDetails)
			throws RWAdminUserException, IOException, ServletException {

		// ==进行安全资源的认证
		try {
			if (securityManager.authRESTRequest(rwRequest, currentUserDetails)) {
				// 继续执行
				log.debug(" === END === RequestURI : [ url-" + rwRequest.getUrl() + " method-" + rwRequest.getMethod()
						+ "]" + " 安全资源认证成功  true ");
				return true;
			} else {
				// 没有权限
				response.setStatus(403);
				if (RegexUtils.match(request.getHeader("Accept"), ("application/json"))) {
					setJsonData2Response(response, MReturnObject.ERROR,
							configuration.getAppPropsValue("admin.resource.nogrant"), null);
				} else {
					request.setAttribute(RWAdminConstant.RW_ADMIN_ERRORMSG_IN_REQUST,
							configuration.getAppPropsValue("admin.resource.nogrant"));
					SendErrorPage(request, response);
				}
				log.debug(" === END === RequestURI : [ url-" + rwRequest.getUrl() + " method-" + rwRequest.getMethod()
						+ "]" + " 安全资源认证没有权限 false ");
				return false;
			}
			// 校验中出现异常
		} catch (RWAdminResourceException e) {
			if (RegexUtils.match(request.getHeader("Accept"), "application/json")) {
				setJsonData2Response(response, MReturnObject.ERROR, configuration.getAppPropsValue(e.getMessage()),
						null);
			} else {
				request.setAttribute(RWAdminConstant.RW_ADMIN_ERRORMSG_IN_REQUST,
						configuration.getAppPropsValue(e.getMessage()));
				SendErrorPage(request, response);
			}
			log.debug(" === END === RequestURI : [ url-" + rwRequest.getUrl() + " method-" + rwRequest.getMethod() + "]"
					+ " 安全资源认证校验中出现异常 false ");
			return false;
		}

	}

	public static boolean resourceAuthentication(RWRequest rwRequest, RWUserDetails currentUserDetails) {

		// ==进行安全资源的认证
		try {
			if (securityManager.authRESTRequest(rwRequest, currentUserDetails)) {
				return true;
			} else {
				return false;
			}
			// 校验中出现异常
		} catch (RWAdminResourceException | RWAdminUserException e) {
			log.debug(" === END === RequestURI : [ url-" + rwRequest.getUrl() + " method-" + rwRequest.getMethod() + "]"
					+ " 安全资源认证校验中出现异常 false ");
			return false;
		}

	}

	/**
	 * 不通过cas校验的url还是走正常的校验流程，登录登出页面为我们自己定义的页面。
	 *
	 * @param adminContextBeforeHandle
	 * @author wfq
	 * @throws RWAdminUserException
	 */
	private RWUserDetails userValidInLocal(HttpServletRequest request, RWAdminContext adminContextBeforeHandle)
			throws RWAdminUserException {

		String username = request
				.getParameter(RWAdminConfiguration.getInstance().getAppPropsValue("username-in-request"));
		String password = request
				.getParameter(RWAdminConfiguration.getInstance().getAppPropsValue("password-in-request"));
		String code = request.getParameter("code");
		if (StringUtils.isNotBlank(code)) {
			/*UserLoginQrcode userLoginQrcode = userLoginQrcodeService.getById(code);
			if (userLoginQrcode != null) {
				if (Login_Qrcode_Status.成功.name().equals(userLoginQrcode.getStatus())) {
					User user = userService.getById(userLoginQrcode.getValue());
					username=user.getUsername();
					password=user.getPassword();
					userLoginQrcode.setStatus(Login_Qrcode_Status.失效.name());
					userLoginQrcodeService.update(userLoginQrcode); 
				}
			}*/
		}
		RWUserDetails user = securityManager.authUser(username, password);
		return user;

	}

	// public RWUserDetails userValidInAuth(HttpServletRequest request) throws
	// RWAdminUserException {
	// String auth = request.getHeader("auth");
	// if (StringUtils.isNotBlank(auth)) {
	// }
	// throw new RWAdminUserException("本地证书过期，请重新绑定");
	// }

	// public RWUserDetails userValidInSms(String username, HttpServletRequest
	// request, String smsCode)
	// throws RWAdminUserException {
	// String session_code = null;
	// if (AppUtils.isMobileDevice(request)) {
	// session_code = (String)
	// request.getSession().getAttribute(RWAdminConstant.SMS_MOBILE_VALIDATE_CODE);
	// } else {
	// session_code = (String)
	// request.getSession().getAttribute(RWAdminConstant.SMS_VALIDATE_CODE);
	// }
	// if (StringUtils.isNotBlank(session_code) && session_code.equals(smsCode))
	// {
	// if (AppUtils.isMobileDevice(request)) {
	// request.getSession().removeAttribute(RWAdminConstant.SMS_MOBILE_VALIDATE_CODE);
	// } else {
	// request.getSession().removeAttribute(RWAdminConstant.SMS_VALIDATE_CODE);
	// }
	// return autoLogin(username);
	// }
	// throw new RWAdminUserException("短信验证码错误，或已经超时！");
	// }

	/**
	 * 返回AJAX请求的JSON对象到页面
	 * 
	 * @param response
	 * @param status
	 * @param message
	 * @param obj
	 * @throws IOException
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 */
	private static void setJsonData2Response(HttpServletResponse response, String status, String message, Object obj)
			throws IOException {
		MReturnObject json = new MReturnObject(status, message, obj);
		response.setContentType("application/json");
		JSON.writeJSONString(response.getOutputStream(), json, SerializerFeature.QuoteFieldNames);
	}

	/**
	 * 是否需要过滤,true不需要过滤,false需要过滤
	 * 
	 * @param rwRequest
	 * @return
	 */
	public static boolean nonNeedFilter(RWRequest rwRequest, String userType) {
		String[] nonInterceptUrls = null;
		boolean isMatch = false;

		if (userType.equals(String.valueOf(EnumType.UserType.ADMIN.getUserType()))) {
			isMatch = true;
		} else {
			nonInterceptUrls = StringUtils.split(NON_INTERCEPT_PATH, ",");

			isMatch = isMatchUrls(rwRequest, nonInterceptUrls);
		}
		// TODO 还有一个(REMOTE_NON_INTERCEPT_PATH) 是使用cas时需要进行判断的

		return isMatch;
	}

	public static boolean commonNonNeedFilter(RWRequest rwRequest) {
		return isMatchUrls(rwRequest, StringUtils.split(COMMON_NON_INTERCEPT_PATH, ","));

	}

	private static boolean isMatchUrls(RWRequest rwRequest, String[] nonInterceptUrls) {
		if (nonInterceptUrls.length > 0) {
			for (String nonInterceptUrl : nonInterceptUrls) {
				// 将资源文件中的不拦截url 的前后部分空格截掉
				nonInterceptUrl = StringUtils.trim(nonInterceptUrl);
				// 如果截掉后仍然有空格 ,就判断为这个不拦截的URL包含REST方式的Method
				if (StringUtils.contains(nonInterceptUrl, REST_URL_METHOD_SEPARATOR_CHARS)) {
					String[] nonInterceptUrlAndMethod = StringUtils.split(nonInterceptUrl,
							REST_URL_METHOD_SEPARATOR_CHARS);
					if (matcher.pathMatchesUrl(matcher.compile(nonInterceptUrlAndMethod[0]),
							(String) matcher.compile(rwRequest.getUrl()))
							&& nonInterceptUrlAndMethod[1].equalsIgnoreCase(rwRequest.getMethod()))
						return true;
				} else {
					if (matcher.pathMatchesUrl(matcher.compile(nonInterceptUrl),
							(String) matcher.compile(rwRequest.getUrl())))
						return true;
				}
			}
		}
		return false;
	}

	/**
	 * 获取安全Session
	 * 
	 * @param request
	 * @param allowCreate
	 * @return
	 */
	public static HttpSession safeGetSession(HttpServletRequest request, boolean allowCreate) {
		try {
			return request.getSession(allowCreate);
		} catch (IllegalStateException ignored) {
			return null;
		}
	}

	/**
	 * 从Session中获取RWAdminContext
	 * 
	 * @param adminSession
	 * @return
	 */
	private RWAdminContext getSecurityContext(HttpSession adminSession) {
		if (adminSession == null) {
			log.debug("because session is null , New RWAdminContextImpl");
			return new RWAdminContextImpl();
		}
		Object adminContext = adminSession.getAttribute(RWAdminConstant.RW_ADMIN_CONTEXT_IN_SESSION);
		if (adminContext == null || !(adminContext instanceof RWAdminContext)) {
			log.debug(
					"because RWAdminContext is null in session or is not RWAdminContext type , New RWAdminContextImpl");
			return new RWAdminContextImpl();
		}
		return (RWAdminContext) adminContext;
	}

	/**
	 * 发送信息到失败页面
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private static void SendErrorPage(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		sendForward(request, response, AUTHENTICATION_ERROR_PAGE, true);
	}

	/**
	 * 发送Forward请求，供SendErrorPage调用
	 * 
	 * @param request
	 * @param response
	 * @param url
	 * @param useRelativeContext
	 * @throws IOException
	 * @throws ServletException
	 */
	private static final void sendForward(HttpServletRequest request, HttpServletResponse response, String url,
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
		request.getRequestDispatcher(finalUrl).forward(request, response);
	}

	/**
	 * 获取Session的当前用户
	 * 
	 * @return
	 * @throws RWAdminUserException
	 */
	// private RWUserDetails getcurrentUser() throws RWAdminUserException {
	// RWUserDetails currentUserDetails = RWAdminContextHolder.getCurrentUser();
	// if (currentUserDetails == null) {
	// throw new RWAdminUserException("admin.user.currentuser.null");
	// }
	// return currentUserDetails;
	// }

	/**
	 * 校验请求是否包含XSS漏洞攻击<br/>
	 * [1] |（竖线符号） [2] & （& 符号） [3];（分号） [4] $（美元符号） [5] %（百分比符号） [6] @（at 符号）
	 * [7] '（单引号） [8] "（引号） [9] \'（反斜杠转义单引号） [10] \"（反斜杠转义引号） [11] <>（尖括号） [12]
	 * ()（括号） [13] +（加号） [14] CR（回车符，ASCII 0x0d） [15] LF（换行，ASCII 0x0a） [16]
	 * ,（逗号） [17] \（反斜杠）
	 * 
	 * @return
	 * 
	 * @version 2013-6-14 下午4:21:16
	 */
	private boolean isXSSRequest(String url, HttpServletRequest request) {
		if (StringUtils.isNotBlank(url)) {
			RWRequest rwRequest = new RWRequest(request);
			if (isNotValidate(rwRequest.getUrl())) {
				return false;
			}
			RequestUrlFilter ruf = new RequestUrlFilter();
			url = url.replace("%3C", "<").replace("%3E", ">").replace("%22", "\"").replace("$", "");
			try {
				String filterUrl = ruf.filter(url);
				if (filterUrl.length() < url.length())
					return true;
				if (filterUrl.trim().toLowerCase().contains("xss:expression")) {
					return true;
				}
			} catch (Exception e) {
				boolean ignoreExt = Boolean.parseBoolean(
						Configuration.getInstance().getAppPropsValue("xss.catch.exception.ignore", "true"));
				log.error("捕获XSS信息时异常");
				if (!ignoreExt)
					return true;
			}
			// 捕捉post表单数据
			String ct = request.getMethod();
			String tUrl = "";
			if (StringUtils.isNotBlank(ct) && !ct.equalsIgnoreCase("get")) {
				Map pms = request.getParameterMap();
				Enumeration ns = request.getParameterNames();
				while (ns.hasMoreElements()) {
					String pn = (String) ns.nextElement();
					String pv = request.getParameter(pn);
					if (!((url.contains("/rest/basNotice") || url.contains("/rest/baslowrule")
							|| url.contains("/rest/basknowledge")) && pn.equals("content"))) {
						tUrl += pn + "=" + pv + "&";
					}
				}
				try {
					tUrl = tUrl.replace("%3C", "<").replace("%3E", ">").replace("$", "");
					String filterUrl = ruf.filter(tUrl);
					if (filterUrl.length() < tUrl.length())
						return true;
					if (filterUrl.trim().toLowerCase().contains("xss:expression")) {
						return true;
					}
				} catch (Exception e) {
					boolean ignoreExt = Boolean.parseBoolean(
							Configuration.getInstance().getAppPropsValue("xss.catch.exception.ignore", "true"));
					log.error("捕获XSS信息时异常");
					if (!ignoreExt)
						return true;
				}
			}
		}
		return false;
	}

	/**
	 * XXS url不验证
	 * 
	 * @param url
	 * @return
	 * @author Mr.Zheng
	 * @version 2014年12月29日 上午11:30:06
	 */
	private boolean isNotValidate(String url) {
		String[] Urls = configuration.getAppPropsValue("xss.no.validate.url", "").split(",");
		UrlMatcher matcher = UrlUtils.createUrlMatcher();
		for (String not_url : Urls) {
			not_url = not_url.replaceAll("\\{[\\w]+\\}", "*");
			if ((matcher.pathMatchesUrl(matcher.compile(not_url), url))) {
				return true;
			}
		}
		return false;
	}
}
