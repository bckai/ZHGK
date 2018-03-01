/**
 * hndfsjSoft webMail project
 */

package com.hndfsj.framework.security.context;

import com.hndfsj.framework.security.bean.RWUserDetails;

/**
 * RW的上下文类
 *
 * @author 王富强
 * @date 2009-11-19 下午04:08:47
 */
public class RWAdminContextImpl implements RWAdminContext{

	private RWUserDetails currentUser;

	public RWUserDetails getCurrentUser() {
		return currentUser;
	}
	public void setCurrentUser(RWUserDetails currentUser) {
		this.currentUser = currentUser;
	}
	
	
	//TODO  实现下面的方法
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}



}
