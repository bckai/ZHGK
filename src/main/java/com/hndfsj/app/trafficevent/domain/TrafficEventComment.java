
package com.hndfsj.app.trafficevent.domain;

import com.hndfsj.framework.utils.DateUtils;
import java.text.ParseException;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 交通事件评论 
 * @copyright {@link www.hndfsj.com}
 * @author BuChunKai<Auto generate>
 * @version  2017-10-17 16:53:19
 * @see com.hndfsj.app.trafficevent.domain.TrafficEventComment
 */
public class TrafficEventComment  implements java.io.Serializable {
	
	private static final long serialVersionUID = 5454155825314635342L;

	//date formats
	public static final String ID = "bas_traffic_event_comment.id";
	public static final String EVENT_ID = "bas_traffic_event_comment.event_id";
	public static final String COMMENT = "bas_traffic_event_comment.comment";
	public static final String FOUNDER = "bas_traffic_event_comment.founder";
	public static final String FOUNDER_ID = "bas_traffic_event_comment.founder_id";
	public static final String FORMAT_CREATE_TIME = DateUtils.DATETIME_SECOND_FORMAT;
	public static final String CREATE_TIME = "bas_traffic_event_comment.create_time";
	public static final String FORMAT_MODIFY_TIME = DateUtils.DATETIME_SECOND_FORMAT;
	public static final String MODIFY_TIME = "bas_traffic_event_comment.modify_time";
 
	 // id主键,
	 // eventIdeventId,
	 // comment评论,
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
	 * eventId
	 */
	private java.lang.String eventId;
	/**
	 * 评论
	 */
	private java.lang.String comment;
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
	
	//concstructor

	public TrafficEventComment(){
	}

	public TrafficEventComment(
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
	public void setEventId(java.lang.String value) {
		this.eventId = value;
	}
	
	public java.lang.String getEventId() {
		return this.eventId;
	}
	public void setComment(java.lang.String value) {
		this.comment = value;
	}
	
	public java.lang.String getComment() {
		return this.comment;
	}
	public void setFounder(java.lang.String value) {
		this.founder = value;
	}
	
	public java.lang.String getFounder() {
		return this.founder;
	}
	public void setFounderId(String value) {
		this.founderId = value;
	}
	
	public String getFounderId() {
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
			.append("EventId",getEventId())
			.append("Comment",getComment())
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
		if(obj instanceof TrafficEventComment == false) return false;
		if(this == obj) return true;
		TrafficEventComment other = (TrafficEventComment)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
