
package com.hndfsj.admin.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * TODO 在此加入类描述
 * @copyright {@link www.hndfsj.com}
 * @author ZhengXingJian<Auto generate>
 * @version  2016-10-21 13:55:58
 * @see com.hndfsj.admin.domain.Group
 */
public class Group  implements java.io.Serializable {
	
	private static final long serialVersionUID = 5454155825314635342L;

	//date formats
	
	//columns START
	/**
	 * ID  UUID
	 */
	private java.lang.String id;
	/**
	 * 组名
	 */
	private java.lang.String name;
	/**
	 * 分组描述
	 */
	private java.lang.String description;
	/**
	 * 是否可用0：不可用，1：可用
	 */
	private java.lang.String isValid;
	//columns END
	
	//concstructor

	public Group(){
	}

	public Group(
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
	public void setDescription(java.lang.String value) {
		this.description = value;
	}
	
	public java.lang.String getDescription() {
		return this.description;
	}
	public void setIsValid(java.lang.String value) {
		this.isValid = value;
	}
	
	public java.lang.String getIsValid() {
		return this.isValid;
	}
	
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Name",getName())
			.append("Description",getDescription())
			.append("IsValid",getIsValid())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Group == false) return false;
		if(this == obj) return true;
		Group other = (Group)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
