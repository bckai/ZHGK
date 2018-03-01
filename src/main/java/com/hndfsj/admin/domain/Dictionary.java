
package com.hndfsj.admin.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link www.hndfsj.com}
 * @author ZhengXingJian<Auto generate>
 * @version 2016-10-21 13:55:35
 * @see com.hndfsj.admin.domain.Dictionary
 */
public class Dictionary implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	public static final String CODE = "sys_dictionary.code";
	public static final String VALUE = "sys_dictionary.value";
	public static final String DICTYPE = "sys_dictionary.dicType";

	// date formats

	// columns START
	/**
	 * 名称
	 */
	private java.lang.String code;
	/**
	 * 值
	 */
	private java.lang.String value;
	/**
	 * 类型 根据该字段来区分不同的字典表,例如1表示报销类型
	 */
	private java.lang.String dicType;
	// columns END
	private Integer count;
	// concstructor

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Dictionary() {
	}

	public Dictionary(java.lang.String code) {
		this.code = code;
	}

	public Dictionary(java.lang.String code, String Value, String type) {
		this.code = code;
		setValue(Value);
		setDicType(type);
	}

	// get and set
	public void setCode(java.lang.String value) {
		this.code = value;
	}

	public java.lang.String getCode() {
		return this.code;
	}

	public void setValue(java.lang.String value) {
		this.value = value;
	}

	public java.lang.String getValue() {
		return this.value;
	}

	public java.lang.String getDicType() {
		return dicType;
	}

	public void setDicType(java.lang.String dicType) {
		this.dicType = dicType;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("Code", getCode())
				.append("Value", getValue()).append("DicType", getDicType()).toString();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getCode()).toHashCode();
	}

	public boolean equals(Object obj) {
		if (obj instanceof Dictionary == false)
			return false;
		if (this == obj)
			return true;
		Dictionary other = (Dictionary) obj;
		return new EqualsBuilder().append(getCode(), other.getCode()).isEquals();
	}
}
