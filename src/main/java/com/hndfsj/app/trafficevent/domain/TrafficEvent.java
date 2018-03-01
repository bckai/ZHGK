
package com.hndfsj.app.trafficevent.domain;

import java.text.ParseException;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.hndfsj.framework.utils.DateUtils;

/**
 * 交通事件实体 
 * @copyright {@link www.hndfsj.com}
 * @author BuChunKai<Auto generate>
 * @version  2017-10-17 16:53:03
 * @see com.hndfsj.app.trafficevent.domain.TrafficEvent
 */
public class TrafficEvent  implements java.io.Serializable {
	
	private static final long serialVersionUID = 5454155825314635342L;

	//date formats
	public static final String ID = "bas_traffic_event.id";
	public static final String TITLE = "bas_traffic_event.title";
	public static final String TYPE = "bas_traffic_event.type";
	public static final String CONTENT = "bas_traffic_event.content";
	public static final String STATUS = "bas_traffic_event.status";
	public static final String COORDINATE = "bas_traffic_event.coordinate";
	public static final String FOUNDER = "bas_traffic_event.founder";
	public static final String FOUNDER_ID = "bas_traffic_event.founder_id";
	public static final String FORMAT_CREATE_TIME = DateUtils.DATETIME_SECOND_FORMAT;
	public static final String CREATE_TIME = "bas_traffic_event.create_time";
	public static final String FORMAT_MODIFY_TIME = DateUtils.DATETIME_SECOND_FORMAT;
	public static final String MODIFY_TIME = "bas_traffic_event.modify_time";
 
	 // id主键,
	 // title标题,
	 // content内容,
	 // status状态,
	 // coordinate坐标,
	 // founder发布人,
	 // founderId发布人id,
	 // createTime创建时间,
	 // modifyTime修改时间,
	 
	//columns START
	/**
	 * 主键
	 */
	private java.lang.String id;
	/**
	 * 标题
	 */
	private java.lang.String title;
	/*
	 *事故类型 
	 */
	private java.lang.String type;
	/**
	 * 内容
	 */
	private java.lang.String content;
	/**
	 * 状态
	 */
	private java.lang.Boolean status;
	/**
	 * 坐标
	 */
	private java.lang.String coordinate;
	/**
	 * 发布人
	 */
	private java.lang.String founder;
	/**
	 * 发布人id
	 */
	private java.lang.String founderId;
	/**
	 * 创建时间
	 */
	private java.util.Date createTime;
	/**
	 * 修改时间
	 */
	private java.util.Date modifyTime;
	//columns END
	
	private List<TrafficEventComment> trafficEventComments;
	
	
	//concstructor


	public TrafficEvent(){
	}
	
	
	public java.lang.String getType() {
		return type;
	}


	public void setType(java.lang.String type) {
		this.type = type;
	}


	public List<TrafficEventComment> getTrafficEventComments() {
		return trafficEventComments;
	}

	public void setTrafficEventComments(List<TrafficEventComment> trafficEventComments) {
		this.trafficEventComments = trafficEventComments;
	}

	public TrafficEvent(
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
	public void setTitle(java.lang.String value) {
		this.title = value;
	}
	
	public java.lang.String getTitle() {
		return this.title;
	}
	public void setContent(java.lang.String value) {
		this.content = value;
	}
	
	public java.lang.String getContent() {
		return this.content;
	}
	public void setStatus(java.lang.Boolean value) {
		this.status = value;
	}
	
	public java.lang.Boolean getStatus() {
		return this.status;
	}
	public void setCoordinate(java.lang.String value) {
		this.coordinate = value;
	}
	
	public java.lang.String getCoordinate() {
		return this.coordinate;
	}
	public void setFounder(java.lang.String value) {
		this.founder = value;
	}
	
	public java.lang.String getFounder() {
		return this.founder;
	}
	public void setFounderId(java.lang.String value) {
		this.founderId = value;
	}
	
	public java.lang.String getFounderId() {
		return this.founderId;
	}
	public String getCreateTimeString() {
		return DateUtils.convertDate2String(FORMAT_CREATE_TIME, getCreateTime());
	}
	public void setCreateTimeString(String value) throws ParseException{
		setCreateTime(DateUtils.convertString2Date(FORMAT_CREATE_TIME,value));
	}
	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	public String getModifyTimeString() {
		return DateUtils.convertDate2String(FORMAT_MODIFY_TIME, getModifyTime());
	}
	public void setModifyTimeString(String value) throws ParseException{
		setModifyTime(DateUtils.convertString2Date(FORMAT_MODIFY_TIME,value));
	}
	
	public void setModifyTime(java.util.Date value) {
		this.modifyTime = value;
	}
	
	public java.util.Date getModifyTime() {
		return this.modifyTime;
	}
	
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Title",getTitle())
			.append("Type",getType())
			.append("Content",getContent())
			.append("Status",getStatus())
			.append("Coordinate",getCoordinate())
			.append("Founder",getFounder())
			.append("FounderId",getFounderId())
			.append("CreateTime",getCreateTime())
			.append("ModifyTime",getModifyTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TrafficEvent == false) return false;
		if(this == obj) return true;
		TrafficEvent other = (TrafficEvent)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
