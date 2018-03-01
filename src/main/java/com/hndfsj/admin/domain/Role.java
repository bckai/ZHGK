
package com.hndfsj.admin.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * TODO 在此加入类描述
 * @copyright {@link www.hndfsj.com}
 * @author ZhengXingJian<Auto generate>
 * @version  2016-10-21 13:59:17
 * @see com.hndfsj.admin.domain.Role
 */
public class Role  implements java.io.Serializable {
	
	private static final long serialVersionUID = 5454155825314635342L;

	//date formats
	
	//columns START
	/**
	 * ID  UUID
	 */
	private java.lang.String id;
	/**
	 * 角色名
	 */
	private java.lang.String name;
	/**
	 * 角色描述
	 */
	private java.lang.String description;
	/**
	 * 是否可用0：不可用，1：可用
	 */
	private java.lang.Boolean isValid;
	/**
	 * sort
	 */
	private java.lang.Integer sort=0;
	//columns END
	
	//concstructor

	public Role(){
	}

	public Role(
		java.lang.String id
	){
		this.id = id;
	}

	public Role(String id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
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
	public void setDescription(java.lang.String value) {
		this.description = value;
	}
	
	public java.lang.String getDescription() {
		return this.description;
	}
	public void setIsValid(java.lang.Boolean value) {
		this.isValid = value;
	}
	
	public java.lang.Boolean getIsValid() {
		return this.isValid;
	}
	public void setSort(java.lang.Integer value) {
		this.sort = value;
	}
	
	public java.lang.Integer getSort() {
		return this.sort;
	}
	
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Name",getName())
			.append("Description",getDescription())
			.append("IsValid",getIsValid())
			.append("Sort",getSort())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Role == false) return false;
		if(this == obj) return true;
		Role other = (Role)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
