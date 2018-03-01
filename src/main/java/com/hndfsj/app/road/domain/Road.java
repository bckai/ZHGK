
package com.hndfsj.app.road.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * TODO
 * 
 * @copyright {@link www.hndfsj.com}
 * @author ZhengXingJian<Auto generate>
 * @version 2018-02-26 16:31:12
 * @see com.hndfsj.app.road.domain.Road
 */
public class Road implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	// date formats
	public static final String ID = "bas_road.id";
	public static final String NAME = "bas_road.name";
	public static final String SHORT = "bas_road.short";
	public static final String START = "bas_road.start";
	public static final String END = "bas_road.end";
	public static final String TABLENAME = "bas_road";

	// id高速公路编号,
	// name公路服务名称,
	// start起始桩号,
	// end结束桩号,

	// columns START
	/**
	 * 高速公路编号
	 */
	private java.lang.String id;
	/**
	 * 公路服务名称
	 */
	private java.lang.String name;
	/**
	 * 起始桩号
	 */
	private java.lang.String start;
	/**
	 * 结束桩号
	 */
	private java.lang.String end;
	// columns END

	// concstructor

	public Road() {
	}

	public Road(java.lang.String id) {
		this.id = id;
	}

	// get and set
	public Road setId(java.lang.String value) {
		this.id = value;
		return this;
	}

	public java.lang.String getId() {
		return this.id;
	}

	public Road setName(java.lang.String value) {
		this.name = value;
		return this;
	}

	public java.lang.String getName() {
		return this.name;
	}

	public Road setStart(java.lang.String value) {
		this.start = value;
		return this;
	}

	public java.lang.String getStart() {
		return this.start;
	}

	public Road setEnd(java.lang.String value) {
		this.end = value;
		return this;
	}

	public java.lang.String getEnd() {
		return this.end;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("Id", getId()).append("Name", getName())
				.append("Start", getStart()).append("End", getEnd()).toString();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getId()).toHashCode();
	}

	public boolean equals(Object obj) {
		if (obj instanceof Road == false)
			return false;
		if (this == obj)
			return true;
		Road other = (Road) obj;
		return new EqualsBuilder().append(getId(), other.getId()).isEquals();
	}
}
