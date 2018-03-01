

package com.hndfsj.framework.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

import com.hndfsj.framework.config.RWAdminConfiguration;
import com.hndfsj.framework.utils.regex.AntUrlPathMatcher;
import com.hndfsj.framework.utils.regex.RegexUrlPathMatcher;
import com.hndfsj.framework.utils.regex.UrlMatcher;



/**
 * 
 *
 * @author 王富强
 * @date 2009-11-24 下午03:25:03
 */
public final class UrlUtils {

	private static final String DEFAULT_PATH_PATTERN = "ant";
	private static final String DEFAULT_LOWERCASE_COMPARISONS = "false";

	private UrlUtils() {
    }


    private static String buildFullRequestUrl(String scheme, String serverName, int serverPort, String contextPath,
        String requestUrl, String servletPath, String requestURI, String pathInfo, String queryString) {

        boolean includePort = true;

        if ("http".equals(scheme.toLowerCase()) && (serverPort == 80)) {
            includePort = false;
        }

        if ("https".equals(scheme.toLowerCase()) && (serverPort == 443)) {
            includePort = false;
        }

        return scheme + "://" + serverName + ((includePort) ? (":" + serverPort) : "") + contextPath
        + buildRequestUrl(servletPath, requestURI, contextPath, pathInfo, queryString);
    }

    private static String buildRequestUrl(String servletPath, String requestURI, String contextPath, String pathInfo,
        String queryString) {

        String uri = servletPath;

        if (uri == null) {
            uri = requestURI;
            uri = uri.substring(contextPath.length());
        }

        return uri + ((pathInfo == null) ? "" : pathInfo) + ((queryString == null) ? "" : ("?" + queryString));
    }

    public static String getFullRequestUrl(HttpServletRequest r) {

        return buildFullRequestUrl(r.getScheme(), r.getServerName(), r.getServerPort(), r.getContextPath(),
            r.getRequestURL().toString(), r.getServletPath(), r.getRequestURI(), r.getPathInfo(), r.getQueryString());
    }


    public static String getRequestUrl(HttpServletRequest r) {

        return buildRequestUrl(r.getServletPath(), r.getRequestURI(), r.getContextPath(), r.getPathInfo(),
            r.getQueryString());
    }

    
    public static boolean isValidRedirectUrl(String url) {
    	return !StringUtils.hasText(url) || url.startsWith("/") || url.toLowerCase().startsWith("http");
    }
    
   public static UrlMatcher createUrlMatcher() {
       
    	String patternType = RWAdminConfiguration.getInstance().getAppPropsValue("path-type",DEFAULT_PATH_PATTERN);
        boolean useRegex = patternType.equals("regex");
        UrlMatcher matcher = new AntUrlPathMatcher();

        if (useRegex) {
            matcher = new RegexUrlPathMatcher();
        }

        // 处理小写参数
        String lowercaseComparisons = RWAdminConfiguration.getInstance().getAppPropsValue("lowercase-comparisons",DEFAULT_LOWERCASE_COMPARISONS);
        if (!StringUtils.hasText(lowercaseComparisons)) {
            lowercaseComparisons = null;
        }


        if ("true".equals(lowercaseComparisons)) {
            if (useRegex) {
                ((RegexUrlPathMatcher)matcher).setRequiresLowerCaseUrl(true);
            }
        } else if ("false".equals(lowercaseComparisons)) {
            if (!useRegex) {
                ((AntUrlPathMatcher)matcher).setRequiresLowerCaseUrl(false);
            }
        }

        return matcher;
    }
    
    public static void main(String[] args) {
    	UrlMatcher matcher = createUrlMatcher();
    	Object patter = matcher.compile("**/rwadmin/*/**");
    	boolean result = matcher.pathMatchesUrl(patter, "http://10.0.111.138:8008/rwadmin/images/aaa.pic");
    	System.out.println(result);
	}
}
