package com.hndfsj.exception;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.ThrowsAdvice;

/**
 * 统一处理异常的类，用spring的AOP来实现
 * 
 * @author 王富强
 * @date 2009-11-2 上午09:35:32
 */
public class ExeceptionHandleAdvisor implements ThrowsAdvice {

	private static final Log log = LogFactory
			.getLog(ExeceptionHandleAdvisor.class);

	public void afterThrowing(Method method, Object[] args, Object target,
			Exception ex) throws Throwable {

		// 控制台
		System.out.println("**************Exception**************");
		System.out.println("Error happened in class: "
				+ target.getClass().getName());
		System.out.println("Error happened in method: " + method.getName());
		for (int i = 0; i < args.length; i++) {
			System.out.println("arg[" + i + "]: " + args[i]);
		}
		System.out.println("Exception class: " + ex.getClass().getName());
		System.out.println("ex.getMessage():" + ex.getMessage());
		ex.printStackTrace();
		System.out.println("*************************************");
		// 日志
		log.error("**************Exception**************");
		log.error("Error happened in class: " + target.getClass().getName());
		log.error("Error happened in method: " + method.getName());
		for (int i = 0; i < args.length; i++) {
			log.error("arg[" + i + "]: " + args[i]);
		}
		log.error("Exception class: " + ex.getClass().getName());
		log.error("ex.getMessage():" + ex.getMessage());
		ex.printStackTrace();
		log.error("*************************************");


//		// 处理底层异常
//		if (ex.getClass().equals(HibernateException.class)) {
//			ex.printStackTrace();
//			throw new TopBusinessException(MessageResource
//					.getString("dbacess.core.hibernate.error"));
//		}

		// 其他没有封装的异常
//		if (ex.getClass().equals(DataAccessException.class)) {
//			ex.printStackTrace();
//			throw new TopBusinessException(MessageResource
//					.getString("execpetion.other.dataaccessexception.error"));
//		} else if (ex.getClass().toString().equals(
//				NullPointerException.class.toString())) {
//			ex.printStackTrace();
//			throw new TopBusinessException(MessageResource
//					.getString("execpetion.other.nullpointerexception.error"));
//		} else if (ex.getClass().equals(IOException.class)) {
//			ex.printStackTrace();
//			throw new TopBusinessException(MessageResource
//					.getString("execpetion.other.ioexception.error"));
//		} else if (ex.getClass().equals(ClassNotFoundException.class)) {
//			ex.printStackTrace();
//			throw new TopBusinessException(MessageResource
//					.getString("execpetion.other.classnotfoundexception.error"));
//		} else if (ex.getClass().equals(ArithmeticException.class)) {
//			ex.printStackTrace();
//			throw new TopBusinessException(MessageResource
//					.getString("execpetion.other.arithmeticexception.error"));
//		} else if (ex.getClass().equals(ArrayIndexOutOfBoundsException.class)) {
//			ex.printStackTrace();
//			throw new TopBusinessException(
//					MessageResource
//							.getString("execpetion.other.arrayindexoutofboundsexception.error"));
//		} else if (ex.getClass().equals(IllegalArgumentException.class)) {
//			ex.printStackTrace();
//			throw new TopBusinessException(
//					MessageResource
//							.getString("execpetion.other.illegalargumentexception.error"));
//		} else if (ex.getClass().equals(ClassCastException.class)) {
//			ex.printStackTrace();
//			throw new TopBusinessException(MessageResource
//					.getString("execpetion.other.classcastexception.error"));
//		} else if (ex.getClass().equals(SecurityException.class)) {
//			ex.printStackTrace();
//			throw new TopBusinessException(MessageResource
//					.getString("execpetion.other.securityexception.error"));
//		} else if (ex.getClass().equals(SQLException.class)) {
//			ex.printStackTrace();
//			throw new TopBusinessException(MessageResource
//					.getString("execpetion.other.sqlexception.error"));
//		} else if (ex.getClass().equals(NoSuchMethodError.class)) {
//			ex.printStackTrace();
//			throw new TopBusinessException(MessageResource
//					.getString("execpetion.other.nosuchmethoderror.error"));
//		} else if (ex.getClass().equals(OutOfMemoryError.class)) {
//			ex.printStackTrace();
//			throw new TopBusinessException(MessageResource
//					.getString("execpetion.other.outofmemoryerror.error"));
//		} else if (ex.getClass().equals(InternalError.class)) {
//			ex.printStackTrace();
//			throw new TopBusinessException(MessageResource
//					.getString("execpetion.other.internalerror.error"));
//		} else {
//			ex.printStackTrace();
//			throw new TopBusinessException(MessageResource
//					.getString("execpetion.other.otherexception.error")
//					+ ex.getMessage());
//		}
	}

}
