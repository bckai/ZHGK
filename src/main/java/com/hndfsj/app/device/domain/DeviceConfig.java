
package com.hndfsj.app.device.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * TODO 
 * @copyright {@link www.hndfsj.com}
 * @author BuChunKai<Auto generate>
 * @version  2017-09-05 11:57:01
 * @see com.hndfsj.app.device.domain.DeviceConfig
 */
public class DeviceConfig  implements java.io.Serializable {
	
	private static final long serialVersionUID = 5454155825314635342L;

	//date formats
	public static final String DVC_ID = "hd_device_config.dvcId";
	public static final String COM_PORT = "hd_device_config.comPort";
	public static final String IS_DELETED = "hd_device_config.isDeleted";

	public static final String DVC_BRAND = "hd_device_config.dvcBrand";
 
	 // dvcId设备编号,
	 // comPort通讯串口,
	 // isDeleted是否删除 0 未删除 1 已删除,
	 // cmsWidth情报板宽度,
	 // cmsHeight情报板高度,
	 // dvcBrand设备品牌,
	 
	//columns START
	/**
	 * 设备编号
	 */
	private java.lang.String dvcId;
	/**
	 * 通讯串口
	 */
	private java.lang.String comPort;
	/**
	 * 是否删除 0 未删除 1 已删除
	 */
	private java.lang.Integer isDeleted;
	/**
	 * 设备品牌
	 */
	private java.lang.String dvcBrand;
	
	/**
	 * 设备品牌
	 */
	private java.lang.String dvcBrandName;
	//columns END
	
	//concstructor

	public DeviceConfig(){
	}

	public DeviceConfig(
		java.lang.String dvcId
	){
		this.dvcId = dvcId;
	}

	//get and set
	public void setDvcId(java.lang.String value) {
		this.dvcId = value;
	}
	
	public java.lang.String getDvcBrandName() {
		return dvcBrandName;
	}

	public void setDvcBrandName(java.lang.String dvcBrandName) {
		this.dvcBrandName = dvcBrandName;
	}

	public java.lang.String getDvcId() {
		return this.dvcId;
	}
	public void setComPort(java.lang.String value) {
		this.comPort = value;
	}
	
	public java.lang.String getComPort() {
		return this.comPort;
	}
	public void setIsDeleted(java.lang.Integer value) {
		this.isDeleted = value;
	}
	
	public java.lang.Integer getIsDeleted() {
		return this.isDeleted;
	}
	public void setDvcBrand(java.lang.String value) {
		this.dvcBrand = value;
	}
	
	public java.lang.String getDvcBrand() {
		return this.dvcBrand;
	}
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("DvcId",getDvcId())
			.append("ComPort",getComPort())
			.append("IsDeleted",getIsDeleted())
			.append("DvcBrand",getDvcBrand())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getDvcId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof DeviceConfig == false) return false;
		if(this == obj) return true;
		DeviceConfig other = (DeviceConfig)obj;
		return new EqualsBuilder()
			.append(getDvcId(),other.getDvcId())
			.isEquals();
	}
}

	
