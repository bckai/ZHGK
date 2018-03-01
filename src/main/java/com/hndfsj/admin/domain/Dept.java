
package com.hndfsj.admin.domain;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.hndfsj.framework.objects.TreeObject;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link www.hndfsj.com}
 * @author ZhengXingJian<Auto generate>
 * @version 2016-10-21 13:55:12
 * @see com.hndfsj.admin.domain.Dept
 */
public class Dept extends TreeObject implements Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	// date formats

	// columns START
	/**
	 * ID UUID
	 */
	private java.lang.String id;
	/**
	 * 部门编码
	 */
	private java.lang.String code;
	/**
	 * 部门名称
	 */
	private java.lang.String name;
	/**
	 * 上级部门 关联表sys_dept
	 */
	private java.lang.String superDep;
	/**
	 * 负责人 储存sys_user表中的用户ID 未做关联
	 */
	private java.lang.String manager;
	/**
	 * 地址
	 */
	private java.lang.String address;
	/**
	 * 是否可用 0:不可用;1:可用
	 */
	private java.lang.Boolean isValid;
	/**
	 * 简称
	 */
	private java.lang.String shortName;
	/**
	 * 排序
	 */
	private java.lang.Integer sort;
	// columns END
	private java.lang.Boolean canAddDept = true;// 能加部门
	private java.lang.Boolean canAddMember = true;// 能加人员
	private List<User> members;// 部门成员
	private List<Dept> subDepts;// 下级部门
	private java.lang.Integer count;// 本级人员数
	// concstructor

	public Dept() {
	}

	public Dept(java.lang.String id) {
		this.id = id;
	}

	// get and set
	public void setId(java.lang.String value) {
		this.id = value;
	}

	public java.lang.String getId() {
		return this.id;
	}

	public void setCode(java.lang.String value) {
		this.code = value;
	}

	public java.lang.String getCode() {
		return this.code;
	}

	public void setName(java.lang.String value) {
		this.name = value;
	}

	public java.lang.String getName() {
		return this.name;
	}

	public void setSuperDep(java.lang.String value) {
		this.superDep = value;
	}

	public java.lang.String getSuperDep() {
		return this.superDep;
	}

	public void setManager(java.lang.String value) {
		this.manager = value;
	}

	public java.lang.String getManager() {
		return this.manager;
	}

	public void setAddress(java.lang.String value) {
		this.address = value;
	}

	public java.lang.String getAddress() {
		return this.address;
	}

	public void setIsValid(java.lang.Boolean value) {
		this.isValid = value;
	}

	public java.lang.Boolean getIsValid() {
		return this.isValid;
	}

	public void setShortName(java.lang.String value) {
		this.shortName = value;
	}

	public java.lang.String getShortName() {
		return this.shortName;
	}

	public java.lang.Boolean getCanAddDept() {
		return canAddDept;
	}

	public void setCanAddDept(java.lang.Boolean canAddDept) {
		this.canAddDept = canAddDept;
	}

	public java.lang.Boolean getCanAddMember() {
		return canAddMember;
	}

	public void setCanAddMember(java.lang.Boolean canAddMember) {
		this.canAddMember = canAddMember;
	}

	public List<User> getMembers() {
		return members;
	}

	public void setMembers(List<User> members) {
		this.members = members;
	}

	public List<Dept> getSubDepts() {
		return subDepts;
	}

	public void setSubDepts(List<Dept> subDepts) {
		this.subDepts = subDepts;
	}

	public java.lang.Integer getCount() {
		return count;
	}

	public void setCount(java.lang.Integer count) {
		this.count = count;
	}

	public java.lang.Integer getSort() {
		return sort;
	}

	public void setSort(java.lang.Integer sort) {
		this.sort = sort;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("Id", getId()).append("Code", getCode())
				.append("Name", getName()).append("SuperDep", getSuperDep()).append("Manager", getManager())
				.append("Address", getAddress()).append("IsValid", getIsValid()).append("ShortName", getShortName())
				.toString();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getId()).toHashCode();
	}

	public boolean equals(Object obj) {
		if (obj instanceof Dept == false)
			return false;
		if (this == obj)
			return true;
		Dept other = (Dept) obj;
		return new EqualsBuilder().append(getId(), other.getId()).isEquals();
	}
}
