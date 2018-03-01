/**
 * hndfsjSoft webMail project
 */

package com.hndfsj.framework.security.bean;

import com.hndfsj.framework.security.spring.GrantedAuthority;

/**
 * 被赋予的角色和组
 *
 * @author 王富强
 * @date 2009-11-19 上午11:15:19
 */
public interface RWGrantedAuthority extends GrantedAuthority {
	/**
	  * 得到所属角色
	 * @return
	 */
	 String getAuthority();

}
