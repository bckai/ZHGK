
package com.hndfsj.admin.domain;

import java.text.ParseException;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.hndfsj.framework.utils.DateUtils;

/**
 * TODO 用户操作日志
 * @copyright {@link www.hndfsj.com}
 * @author ZhengXingJian<Auto generate>
 * @version  2016-11-08 12:27:36
 * @see com.hndfsj.admin.domain.UserOperLogs
 */
public class UserOperLogs  implements java.io.Serializable {
	
	private static final long serialVersionUID = 5454155825314635342L;

	//date formats
	public static final String FORMAT_CREATE_TIME = DateUtils.DATETIME_SECOND_FORMAT;
	
	//columns START
	/**
	 * id
	 */
	private java.lang.Long id;
	/**
	 * 操作用户ID
	 */
	private java.lang.String userId;
	/**
	 * 操作内容
	 */
	private java.lang.String content;
	/**
	 * 请求信息
	 */
	private java.lang.String request;
	/**
	 * 响应信息
	 */
	private java.lang.String response;
	/**
	 * 创建时间
	 */
	private java.util.Date createTime;
	//columns END
	
	//concstructor

	public UserOperLogs(){
	}

	public UserOperLogs(
		java.lang.Long id
	){
		this.id = id;
	}

	public UserOperLogs(String userId) {
		setUserId(userId);
	}

	//get and set
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.Long getId() {
		return this.id;
	}
	public void setUserId(java.lang.String value) {
		this.userId = value;
	}
	
	public java.lang.String getUserId() {
		return this.userId;
	}
	public void setContent(java.lang.String value) {
		this.content = value;
	}
	
	public java.lang.String getContent() {
		return this.content;
	}
	public void setRequest(java.lang.String value) {
		this.request = value;
	}
	
	public java.lang.String getRequest() {
		return this.request;
	}
	public void setResponse(java.lang.String value) {
		this.response = value;
	}
	
	public java.lang.String getResponse() {
		return this.response;
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
	
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("UserId",getUserId())
			.append("Content",getContent())
			.append("Request",getRequest())
			.append("Response",getResponse())
			.append("CreateTime",getCreateTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UserOperLogs == false) return false;
		if(this == obj) return true;
		UserOperLogs other = (UserOperLogs)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
