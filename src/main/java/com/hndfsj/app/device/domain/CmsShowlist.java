
package com.hndfsj.app.device.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * TODO 
 * @copyright {@link www.hndfsj.com}
 * @author BuChunKai<Auto generate>
 * @version  2017-09-11 15:15:20
 * @see com.hndfsj.app.device.domain.CmsShowlist
 */
public class CmsShowlist  implements java.io.Serializable {
	
	private static final long serialVersionUID = 5454155825314635342L;

	//date formats
	public static final String ID = "hd_cms_showlist.id";
	public static final String DVC_TYPE = "hd_cms_showlist.dvcType";
	public static final String CDNT_LEFT = "hd_cms_showlist.cdntLeft";
	public static final String CDNT_UP = "hd_cms_showlist.cdntUp";
	public static final String MESSAGE = "hd_cms_showlist.message";
	public static final String PLAY_MODE = "hd_cms_showlist.playMode";
	public static final String FONT_COLOR = "hd_cms_showlist.fontColor";
	public static final String FONT_STYLE = "hd_cms_showlist.fontStyle";
	public static final String FONT_SIZE = "hd_cms_showlist.fontSize";
	public static final String KEEP_TIME = "hd_cms_showlist.keepTime";
	public static final String STAR_FLAG = "hd_cms_showlist.starFlag";
 
	 // idid,
	 // dvcType情报板设备类型,
	 // cdntLeft节目距左边坐标,
	 // cdntUp节目距上边坐标,
	 // message消息节目内容,
	 // playMode播放模式,
	 // fontColor字体颜色,
	 // fontStyle字体样式,
	 // fontSize字体大小,
	 // keepTime停留时间 单位: 秒,
	 // starFlag是否添加星标 1 添加星标（常用） 0 未添加星标,
	 
	//columns START
	/**
	 * id
	 */
	private java.lang.String id;
	/**
	 * 情报板设备编号
	 */
	private java.lang.String dvcType;
	/**
	 * 节目距左边坐标
	 */
	private java.lang.Integer cdntLeft;
	/**
	 * 节目距上边坐标
	 */
	private java.lang.Integer cdntUp;
	/**
	 * 消息节目内容
	 */
	private java.lang.String message;
	/**
	 * 播放模式
	 */
	private java.lang.Integer playMode;
	/**
	 * 字体颜色
	 */
	private java.lang.String fontColor;
	/**
	 * 字体样式
	 */
	private java.lang.String fontStyle;
	/**
	 * 字体大小
	 */
	private java.lang.Integer fontSize;
	/**
	 * 停留时间 单位: 秒
	 */
	private java.lang.Integer keepTime;
	/**
	 * 是否添加星标 1 添加星标（常用） 0 未添加星标
	 */
	private java.lang.Integer starFlag;
	/**
	 * gao
	 */
	private java.lang.String high;
	
	
	/**
	 * 录入时间
	 */
	private java.util.Date createTime;
	private java.util.Date modifyTime;
	//columns END
	
	//concstructor

	public CmsShowlist(){
	}

	public CmsShowlist(
		java.lang.String id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getHigh() {
		return high;
	}

	public void setHigh(java.lang.String high) {
		this.high = high;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public java.util.Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(java.util.Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public java.lang.String getId() {
		return this.id;
	}
	public void setDvcType(java.lang.String value) {
		this.dvcType = value;
	}
	
	public java.lang.String getDvcType() {
		return this.dvcType;
	}
	public void setCdntLeft(java.lang.Integer value) {
		this.cdntLeft = value;
	}
	
	public java.lang.Integer getCdntLeft() {
		return this.cdntLeft;
	}
	public void setCdntUp(java.lang.Integer value) {
		this.cdntUp = value;
	}
	
	public java.lang.Integer getCdntUp() {
		return this.cdntUp;
	}
	public void setMessage(java.lang.String value) {
		this.message = value;
	}
	
	public java.lang.String getMessage() {
		return this.message;
	}
	public void setPlayMode(java.lang.Integer value) {
		this.playMode = value;
	}
	
	public java.lang.Integer getPlayMode() {
		return this.playMode;
	}
	public void setFontColor(java.lang.String value) {
		this.fontColor = value;
	}
	
	public java.lang.String getFontColor() {
		return this.fontColor;
	}
	public void setFontStyle(java.lang.String value) {
		this.fontStyle = value;
	}
	
	public java.lang.String getFontStyle() {
		return this.fontStyle;
	}
	public void setFontSize(java.lang.Integer value) {
		this.fontSize = value;
	}
	
	public java.lang.Integer getFontSize() {
		return this.fontSize;
	}
	public void setKeepTime(java.lang.Integer value) {
		this.keepTime = value;
	}
	
	public java.lang.Integer getKeepTime() {
		return this.keepTime;
	}
	public void setStarFlag(java.lang.Integer value) {
		this.starFlag = value;
	}
	
	public java.lang.Integer getStarFlag() {
		return this.starFlag;
	}
	
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("DvcType",getDvcType())
			.append("CdntLeft",getCdntLeft())
			.append("CdntUp",getCdntUp())
			.append("Message",getMessage())
			.append("PlayMode",getPlayMode())
			.append("FontColor",getFontColor())
			.append("FontStyle",getFontStyle())
			.append("FontSize",getFontSize())
			.append("KeepTime",getKeepTime())
			.append("StarFlag",getStarFlag())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof CmsShowlist == false) return false;
		if(this == obj) return true;
		CmsShowlist other = (CmsShowlist)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
