package com.hndfsj.framework.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * 正则表达式匹配工具类
 *
 * @author ibm
 * @date   Apr 8, 2010
 */
public class RegexUtils {
	
	/**
	 * 是否存在正则匹配
	 * 
	 * @param str
	 * @param regex
	 * @return
	 */
	public static boolean match(String str, String regex) {
		if(StringUtils.isBlank(str)){
			return false;
		}
		Pattern p = Pattern.compile(regex);  
	    Matcher m = p.matcher(str);
	    return m.find();
	}
	
	
}
