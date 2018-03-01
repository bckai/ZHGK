/**
 * hndfsjSoft webMail project
 */

package com.hndfsj.framework.security.context;

import com.hndfsj.framework.config.RWAdminConfiguration;
import com.hndfsj.framework.exceptions.RWAdminContextException;
import com.hndfsj.framework.exceptions.RWAdminContextHolderStrategyException;
import com.hndfsj.framework.security.bean.RWUserDetails;


/**
 * 请在此加入你的功能说明
 *
 * @author 王富强
 * @date 2009-11-19 下午04:19:22
 */
public class RWAdminContextHolder extends BaseHolder{
	private static RWAdminContextHolderStrategy holderStrategy;
	public static void setStrategy(RWAdminContextHolderStrategy strategy) {
		RWAdminContextHolder.holderStrategy = strategy;
	}
	static{
		try {
			setStrategyName(RWAdminConfiguration.getInstance().getRWAdminContextHolderStrategy());
			//TODO 讲InheritThreadContextHolderStrategy添加到配置文件中
			RWAdminContextHolder.holderStrategy = (RWAdminContextHolderStrategy) BaseHolder.strategyObj;
//			RWAdminContextHolder.holderStrategy = new InheritThreadContextHolderStrategy();
		} catch (RWAdminContextHolderStrategyException e) {
			e.printStackTrace();
		}
	}
	
	public static  RWAdminContext getContext(){
		return holderStrategy.getContext();
	}
	
	public static void setContext(RWAdminContext context) throws RWAdminContextException{
		holderStrategy.setContext(context);
	}
	
	public static void cleanContext(){
		holderStrategy.clearContext();
	}
	
	public static void initCurrentUser(RWUserDetails userDetails){
		getContext().setCurrentUser(userDetails);
	}
	
	public static RWUserDetails getCurrentUser(){
		return getContext().getCurrentUser();
	}
}
