
package com.hndfsj.app.road.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * TODO 
 * @copyright {@link www.hndfsj.com}
 * @author ZhengXingJian<Auto generate>
 * @version  2018-02-26 16:30:32
 * @see com.hndfsj.app.road.domain.Center
 */
public class Center  implements java.io.Serializable {
	
	private static final long serialVersionUID = 5454155825314635342L;

	//date formats
	public static final String ID = "bas_center.id";
	public static final String NAME = "bas_center.name";
	public static final String TABLENAME= "bas_center";
 
	 // id分中心编号,
	 // name分中心名字,
	 
	//columns START
	/**
	 * 分中心编号
	 */
	private java.lang.String id;
	/**
	 * 分中心名字
	 */
	private java.lang.String name;
	//columns END
	
	//concstructor

	public Center(){
	}

	public Center(
		java.lang.String id,
		java.lang.String name
	){
		this.id = id;
		this.name = name;
	}

	//get and set
	public Center  setId(java.lang.String value) {
		this.id = value;
		return this;
	}
	
	public java.lang.String getId() {
		return this.id;
	}
	public Center  setName(java.lang.String value) {
		this.name = value;
		return this;
	}
	
	public java.lang.String getName() {
		return this.name;
	}
	
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Name",getName())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.append(getName())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Center == false) return false;
		if(this == obj) return true;
		Center other = (Center)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.append(getName(),other.getName())
			.isEquals();
	}
}

	
