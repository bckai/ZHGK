/**
 * hndfsjSoft webMail project
 */

package com.hndfsj.framework.security.context;

import java.lang.reflect.Constructor;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.ReflectionUtils;

import com.hndfsj.framework.common.WebApplicationContextListener;
import com.hndfsj.framework.exceptions.RWAdminContextHolderStrategyException;

/**
 * 请在此加入你的功能说明
 *
 * @author 王富强
 * @date 2009-9-23 下午04:54:30
 */
public class BaseHolder {

	private static final String SPRING_PRE = "spring:";
	private static String strategyName ;
	protected static Object strategyObj;

	public static void setStrategyName(String strategyName) throws RWAdminContextHolderStrategyException {
		BaseHolder.strategyName = strategyName;
		init();
	}

	@SuppressWarnings("unchecked")
	protected static void init() throws RWAdminContextHolderStrategyException {
		if(StringUtils.isNotBlank(strategyName)){
			if(strategyName.startsWith(SPRING_PRE)){
				strategyObj =  WebApplicationContextListener.getWebApplicationContext().getBean(StringUtils.substringAfter(strategyName, SPRING_PRE));
			}else {
			try {
	            Class clazz = Class.forName(strategyName);
	            Constructor customStrategy = clazz.getConstructor(new Class[] {});
	             strategyObj = customStrategy.newInstance(new Object[] {});
	        } catch (Exception ex) {
	            ReflectionUtils.handleReflectionException(ex);
	        }
			}
	        
		}else {
			//error
			throw new RWAdminContextHolderStrategyException("mail.holder.loadclass.error");
		}
		
	}


	public BaseHolder() {
		super();
	}

}