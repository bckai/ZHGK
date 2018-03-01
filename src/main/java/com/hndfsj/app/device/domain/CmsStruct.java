
package com.hndfsj.app.device.domain;

import com.hndfsj.framework.utils.DateUtils;
import com.hndfsj.framework.utils.properties.JdbcUtils;

import java.text.ParseException;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * TODO 
 * @copyright {@link www.hndfsj.com}
 * @author ZhengXingJian<Auto generate>
 * @version  2017-09-12 14:44:18
 * @see com.hndfsj.app.device.domain.CmsStruct
 */
public class CmsStruct  implements java.io.Serializable {
	
	private static final long serialVersionUID = 5454155825314635342L;

	//date formats
	public static final String ID = "hd_cms_struct.id";
	public static final String DVC_ID = "hd_cms_struct.dvcId";
	public static final String CDNT_LEFT = "hd_cms_struct.cdntLeft";
	public static final String CDNT_UP = "hd_cms_struct.cdntUp";
	public static final String MESSAGE = "hd_cms_struct.message";
	public static final String PLAY_MODE = "hd_cms_struct.playMode";
	public static final String FONT_COLOR = "hd_cms_struct.fontColor";
	public static final String FONT_STYLE = "hd_cms_struct.fontStyle";
	public static final String FONT_SIZE = "hd_cms_struct.fontSize";
	public static final String KEEP_TIME = "hd_cms_struct.keepTime";
	public static final String FORMAT_PUB_TIME = DateUtils.DATETIME_SECOND_FORMAT;
	public static final String CREATE_TIME = "hd_cms_struct.create_time";
	public static final String PUB_MAN = "hd_cms_struct.pubMan";
	public static final String PUB_FLAG = "hd_cms_struct.pubFlag";
	public static final String TRANS_FLAG = "hd_cms_struct.transFlag";
 
	 // idid,
	 // dvcId情报板编号,
	 // cdntLeftcdntLeft,
	 // cdntUpcdntUp,
	 // message节目内容,
	 // playMode播放模式,
	 // fontColor字体颜色,
	 // fontStyle字体样式,
	 // fontSize字体大小,
	 // keepTime停留时间 单位：秒,
	 // pubTime发布时间,
	 // pubMan发布人 (发布人的工号),
	 // pubFlag是否已经发布,
	 // transFlag是否已经上传,
	 
	//columns START
	/**
	 * id
	 */
	private java.lang.String id;
	/**
	 * 情报板编号
	 */
	private java.lang.String dvcId;
	/**
	 * cdntLeft
	 */
	private java.lang.Integer cdntLeft;
	/**
	 * cdntUp
	 */
	private java.lang.Integer cdntUp;
	/**
	 * 节目内容
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
	 * 停留时间 单位：秒
	 */
	private java.lang.Integer keepTime;
	/**
	 * 发布时间
	 */
	private java.util.Date createTime;
	/**
	 * 发布人 (发布人的工号)
	 */
	private java.lang.String pubMan;
	/**
	 * 是否已经发布
	 */
	private java.lang.Integer pubFlag;
	/**
	 * 是否已经上传
	 */
	private java.lang.Integer transFlag;
	/**
	 * 表名
	 */
	private String table;
	private String db=JdbcUtils.getString("jdbc.dbName");
	
	
	//columns END
	/**
	 * gao
	 */
	private java.lang.String high;
	//concstructor


	public CmsStruct(){
	}

	public CmsStruct(
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

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public java.lang.String getId() {
		return this.id;
	}
	public void setDvcId(java.lang.String value) {
		this.dvcId = value;
	}
	
	public java.lang.String getDvcId() {
		return this.dvcId;
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
	public String getPubTimeString() {
		return DateUtils.convertDate2String(FORMAT_PUB_TIME, getCreateTime());
	}
	public void setPubTimeString(String value) throws ParseException{
		setCreateTime(DateUtils.convertString2Date(FORMAT_PUB_TIME,value));
	}
	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	public void setPubMan(java.lang.String value) {
		this.pubMan = value;
	}
	
	public java.lang.String getPubMan() {
		return this.pubMan;
	}
	public void setPubFlag(java.lang.Integer value) {
		this.pubFlag = value;
	}
	
	public java.lang.Integer getPubFlag() {
		return this.pubFlag;
	}
	public void setTransFlag(java.lang.Integer value) {
		this.transFlag = value;
	}
	
	public java.lang.Integer getTransFlag() {
		return this.transFlag;
	}
	
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("DvcId",getDvcId())
			.append("CdntLeft",getCdntLeft())
			.append("CdntUp",getCdntUp())
			.append("Message",getMessage())
			.append("PlayMode",getPlayMode())
			.append("FontColor",getFontColor())
			.append("FontStyle",getFontStyle())
			.append("FontSize",getFontSize())
			.append("KeepTime",getKeepTime())
			.append("PubMan",getPubMan())
			.append("PubFlag",getPubFlag())
			.append("TransFlag",getTransFlag())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof CmsStruct == false) return false;
		if(this == obj) return true;
		CmsStruct other = (CmsStruct)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
