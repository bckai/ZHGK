
package com.hndfsj.admin.domain;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.hndfsj.framework.security.bean.RWUserDetails;
import com.hndfsj.framework.utils.DateUtils;

/**
 * TODO  用户登录日志
 * @copyright {@link www.hndfsj.com}
 * @author ZhengXingJian<Auto generate>
 * @version  2016-11-08 09:27:24
 * @see com.hndfsj.admin.domain.UserLoginLog
 */
public class UserLoginLog  implements java.io.Serializable {
	
	private static final long serialVersionUID = 5454155825314635342L;

	//date formats
	public static final String FORMAT_LOGIN_TIME = DateUtils.DATETIME_SECOND_FORMAT;
	
	//columns START
	/**
	 * id
	 */
	private java.lang.Long id;
	/**
	 * 用户ID
	 */
	private java.lang.String userId;
	/**
	 * 最后登录时间
	 */
	private java.util.Date loginTime;
	/**
	 * 登录客户端
	 */
	private java.lang.String platform;
	/**
	 * 登录ip
	 */
	private java.lang.String ip;
	/**
	 * 当时所属部门
	 */
	private java.lang.String deptId;
	/**
	 * realName
	 */
	private java.lang.String realName;
	//columns END
	
	//concstructor

	public UserLoginLog(){
	}

	public UserLoginLog(
		java.lang.Long id
	){
		this.id = id;
	}

	public UserLoginLog(String userId, Date date) {
		setUserId(userId);
		setLoginTime(date);
	}

	public UserLoginLog(RWUserDetails userDetails) {
		setUserId(userDetails.getUserId());
		setLoginTime(new Date());
		setRealName(userDetails.getRealName());
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
	public String getLoginTimeString() {
		return DateUtils.convertDate2String(FORMAT_LOGIN_TIME, getLoginTime());
	}
	public void setLoginTimeString(String value) throws ParseException{
		setLoginTime(DateUtils.convertString2Date(FORMAT_LOGIN_TIME,value));
	}
	
	public void setLoginTime(java.util.Date value) {
		this.loginTime = value;
	}
	
	public java.util.Date getLoginTime() {
		return this.loginTime;
	}
	public void setPlatform(java.lang.String value) {
		this.platform = value;
	}
	
	public java.lang.String getPlatform() {
		return this.platform;
	}
	public void setIp(java.lang.String value) {
		this.ip = value;
	}
	
	public java.lang.String getIp() {
		return this.ip;
	}
	public void setDeptId(java.lang.String value) {
		this.deptId = value;
	}
	
	public java.lang.String getDeptId() {
		return this.deptId;
	}
	
	public java.lang.String getRealName() {
		return realName;
	}

	public void setRealName(java.lang.String realName) {
		this.realName = realName;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("UserId",getUserId())
			.append("LoginTime",getLoginTime())
			.append("Platform",getPlatform())
			.append("Ip",getIp())
			.append("DeptId",getDeptId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UserLoginLog == false) return false;
		if(this == obj) return true;
		UserLoginLog other = (UserLoginLog)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
