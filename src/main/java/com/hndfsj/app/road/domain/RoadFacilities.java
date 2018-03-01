
package com.hndfsj.app.road.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * TODO 
 * @copyright {@link www.hndfsj.com}
 * @author ZhengXingJian<Auto generate>
 * @version  2018-02-26 16:31:04
 * @see com.hndfsj.app.road.domain.RoadFacilities
 */
public class RoadFacilities  implements java.io.Serializable {
	
	private static final long serialVersionUID = 5454155825314635342L;

	//date formats
	public static final String ID = "bas_road_facilities.id";
	public static final String ROAD_ID = "bas_road_facilities.roadId";
	public static final String NAME = "bas_road_facilities.name";
	public static final String SIGN = "bas_road_facilities.sign";
	public static final String TABLENAME= "bas_road_facilities";
 
	 // id服务区、收费为站编号,
	 // roadId所属路段编号,
	 // name服务区、收费站名称,
	 // sign桩号,
	 
	//columns START
	/**
	 * 服务区、收费为站编号
	 */
	private java.lang.String id;
	/**
	 * 所属路段编号
	 */
	private java.lang.String roadId;
	/**
	 * 服务区、收费站名称
	 */
	private java.lang.String name;
	/**
	 * 桩号
	 */
	private java.lang.String sign;
	
	private java.lang.String  lngLat;
	//columns END
	
	//concstructor

	public RoadFacilities(){
	}

	public RoadFacilities(
		java.lang.String id
	){
		this.id = id;
	}

	//get and set
	public RoadFacilities  setId(java.lang.String value) {
		this.id = value;
		return this;
	}
	
	public java.lang.String getLngLat() {
		return lngLat;
	}

	public void setLngLat(java.lang.String lngLat) {
		this.lngLat = lngLat;
	}

	public java.lang.String getId() {
		return this.id;
	}
	public RoadFacilities  setRoadId(java.lang.String value) {
		this.roadId = value;
		return this;
	}
	
	public java.lang.String getRoadId() {
		return this.roadId;
	}
	public RoadFacilities  setName(java.lang.String value) {
		this.name = value;
		return this;
	}
	
	public java.lang.String getName() {
		return this.name;
	}
	public RoadFacilities  setSign(java.lang.String value) {
		this.sign = value;
		return this;
	}
	
	public java.lang.String getSign() {
		return this.sign;
	}
	
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("RoadId",getRoadId())
			.append("Name",getName())
			.append("Sign",getSign())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof RoadFacilities == false) return false;
		if(this == obj) return true;
		RoadFacilities other = (RoadFacilities)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
