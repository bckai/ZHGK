/**
 * hndfsjSoft webMail project
 */

package com.hndfsj.framework.security.bean.impl;

import com.hndfsj.framework.security.bean.RWGrantedGroup;

/**
 * 组Bean
 *
 * @author 王富强
 * @date 2009-11-19 下午01:57:56
 */
public class RWGrantedGroupImpl implements RWGrantedGroup{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String group;
	
	
	
	public RWGrantedGroupImpl(String group) {
		this.group = group;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	@Override
	public int compareTo(Object o) {
		if (o != null && o instanceof RWGrantedGroupImpl) {
			String rhsRole = ((RWGrantedGroupImpl) o).getGroup();
			
			if (rhsRole == null) {
				return -1;
			}
			
			return group.compareTo(rhsRole);
		}
		return -1;
	}

	 public boolean equals(Object obj) {
	        if (obj instanceof String) {
	            return this.group.equals(obj);
	        }
	        else if(obj instanceof RWGrantedGroupImpl) {
	        	RWGrantedGroupImpl attr = (RWGrantedGroupImpl) obj;
	            return this.group.equals(attr.getGroup());
	        }else{
	        	return false;
	        }
	    }
	    public int hashCode() {
	        return this.group.hashCode();
	    }

	    public String toString() {
	        return this.group;
	    }
}
