package com.hndfsj.framework.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则验证工具类
 * 
 * @copyright {@link www.hndfsjsoft.com}
 * @author john
 * @version Apr 3, 2014 4:22:29 PM
 * @see com.yingqi.app.utils.hndfsj.app.club.utils.RegularUtils
 */
public class RegularUtils {
	/**
	 * 11位手机号码验证
	 */
	public static boolean matchPhone(String phone) {
		String regex = "^[1][3-8]{1}\\d{9}";
		return validate(regex, phone);
	}

	/**
	 * 区号、固话、分机号验证
	 */
	public static boolean matchMobile(String mobile) {
		// String regex =
		// "^(0[0-9]{2,3}\\-)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?$";
		String regex = "^(((\\+86)|(86)|(086))\\-?)?((0[1-9]\\d{1,2}[-]?)|(\\(0[1-9]\\d{1,2}\\)?))?([1-9]\\d{6,7})(\\-\\d{1,4})?$";
		return validate(regex, mobile);
	}

	/**
	 * 验证大于零的数字
	 * 
	 * @param number
	 * @return
	 * @author john
	 * @version Apr 6, 2014 11:25:27 AM
	 */
	public static boolean matchPlusNumber(String number) {
		String regex = "\\d+";
		return validate(regex, number);
	}

	/**
	 * 验证数字(包含负数)
	 * 
	 * @param number
	 * @return
	 * @author john
	 * @version Apr 6, 2014 11:25:27 AM
	 */
	public static boolean matchNumber(String number) {
		// String regex="[-]{0,1}[1-9]{1}\\d+";严格模式
		String regex = "[-]{0,1}\\d+";
		return validate(regex, number);
	}

	/**
	 * 匹配身份证号
	 * 
	 * @param certNO
	 * @return
	 * @author Mr.Zheng
	 * @version 2014年11月2日 下午3:13:38
	 */
	public static boolean matchCert(String certNO) {
		String regex = "(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)";
		return validate(regex, certNO);
	}

	public static boolean validate(String regex, String content) {
		if (content == null) {// 不能为空
			return false;
		}
		Pattern pat = Pattern.compile(regex);
		Matcher match = pat.matcher(content);
		return match.matches();
	}

	public static List<String> captureStrs(String regex, String content, int  index) {
		Pattern pat = Pattern.compile(regex);
		Matcher match = pat.matcher(content);
		List<String> result = new ArrayList<String>();
		while(match.find()) {
			result.add(match.group(index));
		}
		return result;
	}

	/**
	 * 正则匹配IP
	 * 
	 * @param ip
	 * @return
	 * @author john
	 * @version Apr 8, 2014 2:33:34 PM
	 */
	public static boolean matchIP(String ip) {
		// String regex=MessageUtils.REGULAR_MATCH_IP;
		// 10.0.111.125
		String regex = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
		return validate(regex, ip);
	}

	/**
	 * 正则匹配机场IP
	 * 
	 * @param ip
	 * @return
	 * @author john
	 * @version Apr 8, 2014 2:33:34 PM
	 */
	public static boolean matchJCCLKIP(String ip) {
		// String regex=MessageUtils.REGULAR_MATCH_IP;
		// 10.0.111.125
		String regex = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
		return validate(regex, ip);
	}

	public static boolean matchEmail(String email) {
		String regex = "^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)$";
		return validate(regex, email);
	}

	public static boolean isEmail(String email) {
		String regex = "^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+";
		return validate(regex, email);
	}

	/**
	 * 验证是否是中文
	 * 
	 * @param realName
	 * @return
	 * @author Mr.Zheng
	 * @version 2014年11月6日 上午10:12:25
	 */
	public static boolean isChinese(String realName) {
		String regex = "^[·.\u4E00-\u9FA5]{2,}$";
		return validate(regex, realName);
	}

	/**
	 * 验证呢称是否是大于6位字符的呢称
	 * 
	 * @param nickName
	 * @return
	 * @author Mr.Zheng
	 * @version 2014年11月6日 上午10:12:25
	 */
	public static boolean isNickName(String nickName) {
		String regex = "^[a-zA-Z0-9\u4E00-\u9FA5]{4,10}$";
		return validate(regex, nickName);
	}

	/**
	 * 校验密码
	 * 
	 * @param pwd
	 * @return
	 * @author Mr.Hao
	 * @version 2014-11-7 下午11:16:05
	 */
	public static boolean isPwd(String pwd) {
		String regex = "^[\\w\\-~!@#$%^&()+{}\\[\\]:]{6,16}$";
		return validate(regex, pwd);
	}

	/**
	 * 验证用户帐号
	 * 
	 * @param nickName
	 * @return
	 * @author Mr.Zheng
	 * @version 2014年11月6日 上午10:12:25
	 */
	public static boolean checkUserName(String userName) {
		String regex = "^[a-zA-Z0-9]{6,12}$";
		return validate(regex, userName);
	}

	public static boolean isNumber(String regex, String number) {
		return validate(regex, number);
	}

	// /**
	// * 验证是否是中文
	// * @param realName
	// * @return
	// * @author Mr.Zheng
	// * @version 2014年11月6日 上午10:12:25
	// */
	// public static boolean isSecIDCard(String ID) {
	// String regex="^\\d{17}(\\d|x)$";
	// return validate(regex,ID);
	// if (!validate(regex,ID)) {
	// return false;
	// }
	//
	// var f = 0;
	// var e = {
	// 11 : "北京",
	// 12 : "天津",
	// 13 : "河北",
	// 14 : "山西",
	// 15 : "内蒙",
	// 21 : "辽宁",
	// 22 : "吉林",
	// 23 : "黑龙",
	// 31 : "上海",
	// 32 : "江苏",
	// 33 : "浙江",
	// 34 : "安徽",
	// 35 : "福建",
	// 36 : "江西",
	// 37 : "山东",
	// 41 : "河南",
	// 42 : "湖北",
	// 43 : "湖南",
	// 44 : "广东",
	// 45 : "广西",
	// 46 : "海南",
	// 50 : "重庆",
	// 51 : "四川",
	// 52 : "贵州",
	// 53 : "云南",
	// 54 : "西藏",
	// 61 : "陕西",
	// 62 : "甘肃",
	// 63 : "青海",
	// 64 : "宁夏",
	// 65 : "新疆",
	// 71 : "台湾",
	// 81 : "香港",
	// 82 : "澳门",
	// 91 : "国外"
	// };
	//
	// ID = ID.replace(/x$/i, "a");
	// if (e[parseInt(a.substr(0, 2))] == null) {
	// return false
	// }
	// var c = a.substr(6, 4) + "-" + Number(a.substr(10, 2)) + "-"
	// + Number(a.substr(12, 2));
	// var h = new Date(c.replace(/-/g, "/"));
	// if (c != (h.getFullYear() + "-" + (h.getMonth() + 1) + "-" +
	// h.getDate())) {
	// return false
	// }
	// for ( var b = 17; b >= 0; b--) {
	// f += (Math.pow(2, b) % 11) * parseInt(a.charAt(17 - b), 11)
	// }
	// if (f % 11 != 1) {
	// return false
	// }
	// return true
	//
	//
	//
	//
	// return validate(regex,realName);
	// }

}
