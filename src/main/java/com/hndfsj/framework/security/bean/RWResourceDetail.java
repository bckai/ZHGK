/**
 * hndfsjSoft webMail project
 */

package com.hndfsj.framework.security.bean;

import java.io.Serializable;


/**
 * 请在此加入你的功能说明
 *
 * @author 王富强
 * @date 2009-11-19 上午11:07:28
 */
public interface RWResourceDetail  extends Serializable{

	/**
	 * 得到资源名 (数据库中资源的code)
	 * @return
	 */
	String getResourceName();

	/**
	 * 得到REST请求的Method
	 * @return
	 */
	String getResourceRESTMethod();
	/**
	 * 得到资源所属组件
	 * @return
	 */
	String getResourceComponent();
	/**
	 * 得到资源类型 'URL' 'METHOD'  
	 * @return
	 */
	String getResourceType();
	
	/**
	 * 资源访问路径
	 * @return
	 */
	String getResourceUrl();

	/**
	 * 得到认证的角色(资源可以被那些角色code所访问)
	 * @return
	 */
	RWGrantedAuthority[] getAuthorities();
	
	 /**
	  * 得到认证的组(资源可以被那些组code所访问)
	 * @return
	 */
	RWGrantedGroup[] getGroups();
	
	/**
	 * 得到认证的用户(资源可以被哪些单独分配的用户访问)
	 * @return
	 */
	String[] getUsers();
	
}
