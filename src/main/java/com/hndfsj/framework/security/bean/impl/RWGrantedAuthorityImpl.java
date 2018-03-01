/**
 * hndfsjSoft webMail project
 */

package com.hndfsj.framework.security.bean.impl;

import com.hndfsj.framework.security.bean.RWGrantedAuthority;

/**
 * 请在此加入你的功能说明
 *
 * @author 王富强
 * @date 2009-11-19 下午02:10:01
 */
public class RWGrantedAuthorityImpl implements RWGrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String role;
	
	
	
	public RWGrantedAuthorityImpl(String role) {
		this.role = role;
	}

	@Override
	public int compareTo(Object o) {
		if (o != null && o instanceof RWGrantedAuthorityImpl) {
			String rhsRole = ((RWGrantedAuthority) o).getAuthority();
			
			if (rhsRole == null) {
				return -1;
			}
			
			return role.compareTo(rhsRole);
		}
		return -1;
	}

	 public boolean equals(Object obj) {
	        if (obj instanceof String) {
	            return obj.equals(this.role);
	        }

	        if (obj instanceof RWGrantedAuthority) {
	        	RWGrantedAuthority attr = (RWGrantedAuthority) obj;

	            return this.role.equals(attr.getAuthority());
	        }

	        return false;
	    }
	    public int hashCode() {
	        return this.role.hashCode();
	    }

	    public String toString() {
	        return this.role;
	    }

		@Override
		public String getAuthority() {
			return role;
		}


}
