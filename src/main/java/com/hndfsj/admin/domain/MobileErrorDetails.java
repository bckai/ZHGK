
package com.hndfsj.admin.domain;

import java.text.ParseException;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.hndfsj.framework.utils.DateUtils;

/**
 * TODO 在此加入类描述
 * @copyright {@link www.hndfsj.com}
 * @author ZhengXingJian<Auto generate>
 * @version  2016-10-21 13:58:12
 * @see com.hndfsj.admin.domain.MobileErrorDetails
 */
public class MobileErrorDetails  implements java.io.Serializable {
	
	private static final long serialVersionUID = 5454155825314635342L;

	//date formats
	public static final String FORMAT_CREATE_DATE = DateUtils.DATETIME_SECOND_FORMAT;
	
	//columns START
	/**
	 * id
	 */
	private java.lang.String id;
	/**
	 * 手机型号操作系统型号
	 */
	private java.lang.String mobileType;
	/**
	 * 错误详情
	 */
	private java.lang.String errorDetails;
	/**
	 * 登录手机号
	 */
	private java.lang.String mobile;
	/**
	 * 创建日期
	 */
	private java.util.Date createDate;
	/**
	 * appVesion
	 */
	private java.lang.String appVesion;
	/**
	 * 创建日期
	 */
	private java.lang.String remarks;
	//columns END
	
	//concstructor

	public MobileErrorDetails(){
	}

	public MobileErrorDetails(
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
	public void setMobileType(java.lang.String value) {
		this.mobileType = value;
	}
	
	public java.lang.String getMobileType() {
		return this.mobileType;
	}
	public void setErrorDetails(java.lang.String value) {
		this.errorDetails = value;
	}
	
	public java.lang.String getErrorDetails() {
		return this.errorDetails;
	}
	public void setMobile(java.lang.String value) {
		this.mobile = value;
	}
	
	public java.lang.String getMobile() {
		return this.mobile;
	}
	public String getCreateDateString() {
		return DateUtils.convertDate2String(FORMAT_CREATE_DATE, getCreateDate());
	}
	public void setCreateDateString(String value) throws ParseException{
		setCreateDate(DateUtils.convertString2Date(FORMAT_CREATE_DATE,value));
	}
	
	public void setCreateDate(java.util.Date value) {
		this.createDate = value;
	}
	
	public java.util.Date getCreateDate() {
		return this.createDate;
	}
	public void setAppVesion(java.lang.String value) {
		this.appVesion = value;
	}
	
	public java.lang.String getAppVesion() {
		return this.appVesion;
	}
	public void setRemarks(java.lang.String value) {
		this.remarks = value;
	}
	
	public java.lang.String getRemarks() {
		return this.remarks;
	}
	
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("MobileType",getMobileType())
			.append("ErrorDetails",getErrorDetails())
			.append("Mobile",getMobile())
			.append("CreateDate",getCreateDate())
			.append("AppVesion",getAppVesion())
			.append("Remarks",getRemarks())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof MobileErrorDetails == false) return false;
		if(this == obj) return true;
		MobileErrorDetails other = (MobileErrorDetails)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
