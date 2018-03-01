/**
 * hndfsjSoft webMail project
 */

package com.hndfsj.framework.security.bean.impl;

import java.io.Serializable;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import com.hndfsj.framework.security.bean.RWGrantedAuthority;
import com.hndfsj.framework.security.bean.RWGrantedGroup;
import com.hndfsj.framework.security.bean.RWResourceDetail;

/**
 * 安全资源Bean
 *
 * @author 王富强
 * @date 2009-11-19 下午02:54:29
 */
public class RWResourceDetailsImpl implements RWResourceDetail,Serializable {

	private static final long serialVersionUID = 1L;
	private String resourceName;
	private String resourceUrl;
	private String resourceRESTMethod;
	private String resourceComponent;
	private String resourceType;
	private RWGrantedAuthority[] authorities;
	private RWGrantedGroup[] groups;
	private String[] users;
	
	/**
	 * @param resourceCode 资源code
	 * @param resourceUrl  资源url
	 * @param resourceRESTMethod  资源url的Method
	 * @param resourceType 资源类型 METHOD  URL
	 * @param resourceComponent 资源所属组件名
	 * @param authorities 认证的权限(角色)
	 * @param groups 认证的组
	 * @param users 认证的用户
	 */
	public RWResourceDetailsImpl(String resourceCode,String resourceUrl, String resourceRESTMethod,String resourceType,String resourceComponent,
			RWGrantedAuthority[] authorities,RWGrantedGroup[] groups, String[] users){
		if (StringUtils.isBlank(resourceCode)) {
			throw new IllegalArgumentException("构造的Resource中的resourceCode不能为空");
		}
		if (StringUtils.isBlank(resourceUrl)) {
			throw new IllegalArgumentException("构造的Resource中的resourceUrl不能为空");
		}
		if (StringUtils.isBlank(resourceType)) {
			throw new IllegalArgumentException("构造的Resource中的resourceType不能为空");
		}
		if(StringUtils.isBlank(resourceRESTMethod)){
			throw new IllegalArgumentException("构造的Resource中的resourceRESTMethod不能为空");
		}
		this.resourceComponent = resourceComponent;
		this.resourceName = resourceCode;
		this.resourceUrl = resourceUrl;
		this.resourceRESTMethod = resourceRESTMethod;
		this.resourceType = resourceType;
		//设置认证角色
		setAuthorities(authorities);
		setGroups(groups);
		setUsers(users);
	}
	
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public String getResourceUrl() {
		return resourceUrl;
	}
	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}
	public String getResourceType() {
		return resourceType;
	}
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}
	public RWGrantedAuthority[] getAuthorities() {
		return authorities;
	}
	@SuppressWarnings("unchecked")
	public void setAuthorities(RWGrantedAuthority[] authorities) {
		Assert.notNull(authorities, "被授权的对象数组为空");
        SortedSet sorter = new TreeSet();
        for (int i = 0; i < authorities.length; i++) {
            Assert.notNull(authorities[i],
                "被授权的authoritie元素 " + i + " 为空");
            sorter.add(authorities[i]);
        }
        this.authorities = (RWGrantedAuthority[]) sorter.toArray(new RWGrantedAuthority[sorter.size()]);
	}
	public RWGrantedGroup[] getGroups() {
		return groups;
	}
	@SuppressWarnings("unchecked")
	public void setGroups(RWGrantedGroup[] groups) {
		Assert.notNull(groups, "被授权的对象数组为空");
        SortedSet sorter = new TreeSet();
        for (int i = 0; i < groups.length; i++) {
            Assert.notNull(groups[i],
                "被授权的group元素" + i + " 为空");
            sorter.add(groups[i]);
        }
        this.groups = (RWGrantedGroup[]) sorter.toArray(new RWGrantedGroup[sorter.size()]);
	}
	public String[] getUsers() {
		return users;
	}
	@SuppressWarnings("unchecked")
	public void setUsers(String[] users) {
		Assert.notNull(users, "被授权的对象数组为空");
        SortedSet sorter = new TreeSet();
        for (int i = 0; i < users.length; i++) {
            Assert.notNull(users[i],
                "被授权的user元素" + i + " 为空");
            sorter.add(users[i]);
        }
        this.users = (String[]) sorter.toArray(new String[sorter.size()]);
	}

	public String getResourceComponent() {
		return resourceComponent;
	}

	public void setResourceComponent(String resourceComponent) {
		this.resourceComponent = resourceComponent;
	}

	public String getResourceRESTMethod() {
		return resourceRESTMethod;
	}

	public void setResourceRESTMethod(String resourceRESTMethod) {
		this.resourceRESTMethod = resourceRESTMethod;
	}

	@Override
	public String toString() {
		return "impl [name=" + resourceComponent + ", url=" + resourceUrl
				+ ", method=" + resourceRESTMethod + "]";
	}
	
}
