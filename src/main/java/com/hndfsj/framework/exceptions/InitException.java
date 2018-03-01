package com.hndfsj.framework.exceptions;

/**
 * 初始化异常
 * 
 * @copyright {@link www.hndfsjsoft.com}
 * @author
 * @version 2015年3月27日 下午2:56:57
 * @see com.hndfsj.exception.InitException
 */
public class InitException extends RuntimeException {
	public InitException() {
		super("初始化异常");
	}

	public InitException(String message) {
		super(message);
	}

}
