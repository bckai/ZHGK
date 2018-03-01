package com.hndfsj.framework.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println(isNumeric("1"));
		System.out.println(isNumeric("111"));
		System.out.println(isNumeric("1.1"));
		System.out.println(isNumeric("12as2"));
		

	}

}
