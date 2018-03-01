
package com.hndfsj.admin.domain;

import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * TODO 在此加入类描述
 * @copyright {@link www.hndfsj.com}
 * @author ZhengXingJian<Auto generate>
 * @version  2016-10-21 13:59:10
 * @see com.hndfsj.admin.domain.Resource
 */
public class Resource  implements java.io.Serializable {
	
	private static final long serialVersionUID = 5454155825314635342L;

	//date formats
	
	//columns START
	/**
	 * ID  UUID
	 */
	private java.lang.String id;
	/**
	 * 资源名称  必须唯一
	 */
	private java.lang.String name;
	/**
	 * 资源路径
	 */
	private java.lang.String url;
	/**
	 * 是否可用
	 */
	private java.lang.String isValid;
	/**
	 * 所属模块  关联表Sys_Module
	 */
	private java.lang.String modId;
	/**
	 * 方法类型  GET|PUT|POST|DELETE
	 */
	private java.lang.String method;
	/**
	 * 序号  用于模块显示时的排序
	 */
	private java.lang.Integer sortNo;
	//columns END
	private Module module;// 所属模块信息
	private List<Role> roles;// 角色列表
	private List<User> users;// 人员列表
	//concstructor

	public Resource(){
	}

	public Resource(
		java.lang.String id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}
	public void setName(java.lang.String value) {
		this.name = value;
	}
	
	public java.lang.String getName() {
		return this.name;
	}
	public void setUrl(java.lang.String value) {
		this.url = value;
	}
	
	public java.lang.String getUrl() {
		return this.url;
	}
	public void setIsValid(java.lang.String value) {
		this.isValid = value;
	}
	
	public java.lang.String getIsValid() {
		return this.isValid;
	}
	public void setModId(java.lang.String value) {
		this.modId = value;
	}
	
	public java.lang.String getModId() {
		return this.modId;
	}
	public void setMethod(java.lang.String value) {
		this.method = value;
	}
	
	public java.lang.String getMethod() {
		return this.method;
	}
	public void setSortNo(java.lang.Integer value) {
		this.sortNo = value;
	}
	
	public java.lang.Integer getSortNo() {
		return this.sortNo;
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Resource == false) return false;
		if(this == obj) return true;
		Resource other = (Resource)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
	@Override
	public String toString() {
		return "Resource [id=" + id + ", name=" + name + ", url=" + url + ", method=" + method
				+ ", modId=" + modId + ", sortNo=" + sortNo + ", isValid=" + isValid + ", module="
				+ module + ", roles=" + roles + ", users=" + users + "]";
	}
	
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}
}

	
