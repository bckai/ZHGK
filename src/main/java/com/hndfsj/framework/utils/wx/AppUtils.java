package com.hndfsj.framework.utils.wx;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.hndfsj.framework.config.EnumType.DeviceType;

/**
 * 应用相关方法
 *
 */
public class AppUtils {

	public static boolean isMobileDevice(HttpServletRequest request) {
		return DeviceType.containName(request.getHeader("User-Agent"));
	}
	public static boolean isWeiXinDevice(HttpServletRequest request) {
        String signature = request.getParameter("signature");// 微信加密签名
        String timestamp = request.getParameter("timestamp");// 时间戳
        String nonce = request.getParameter("nonce");       // 随机数
        String token = request.getParameter("token");       // 随机数
//		return TokenUtil.checkSignature(token, signature, timestamp, nonce);
		return false;
	}

	public static boolean isUnicom(HttpServletRequest request) {
		String appName = request.getHeader("appname");
		if (StringUtils.isNotBlank(appName) && appName.contains("unicom_")) {
			return true;
		}
		return false;
	}
}
