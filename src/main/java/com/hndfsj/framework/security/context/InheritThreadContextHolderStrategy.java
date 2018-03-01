package com.hndfsj.framework.security.context;

import com.hndfsj.framework.exceptions.RWAdminContextException;



/**
 * 请在此加入你的功能说明
 *
 * @author 王富强
 * @date 2009-9-9 上午10:04:36
 */
public class InheritThreadContextHolderStrategy implements
		RWAdminContextHolderStrategy {

	private ThreadLocal<RWAdminContext> contextHolder = new InheritableThreadLocal<RWAdminContext>() ;
	public void clearContext() {
		 contextHolder.set(null);

	}

	public  RWAdminContext getContext() {
		if(contextHolder.get()==null){
			this.contextHolder.set(new RWAdminContextImpl());
		}
		return contextHolder.get();
	}

	public void setContext(RWAdminContext context) throws RWAdminContextException {
		if(context!=null){
			this.contextHolder.set(context);
		}else {
			throw new RWAdminContextException("adminContext must be not null");
		}
	}

}
