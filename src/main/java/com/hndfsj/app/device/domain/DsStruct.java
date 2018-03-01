
package com.hndfsj.app.device.domain;

import com.hndfsj.framework.utils.DateUtils;
import com.hndfsj.framework.utils.properties.JdbcUtils;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * TODO 
 * @copyright {@link www.hndfsj.com}
 * @author BuChunKai<Auto generate>
 * @version  2017-09-05 14:29:27
 * @see com.hndfsj.app.device.domain.DsStruct
 */
public class DsStruct  implements java.io.Serializable {
	
	private static final long serialVersionUID = 5454155825314635342L;

	//date formats
	public static final String ID = "hd_ds_struct.id";
	public static final String DVC_ID = "hd_ds_struct.dvcId";
	public static final String FORMAT_TTIME = DateUtils.DATETIME_SECOND_FORMAT;
	public static final String CREATE_TTIME = "hd_ds_struct.create_time";
	public static final String DSTATUS = "hd_ds_struct.dStatus";
	public static final String ERR_CONTENT = "hd_ds_struct.errContent";
	public static final String TRANS_FLAG = "hd_ds_struct.transFlag";
 
	 // idid,
	 // dvcId设备编号,
	 // ttime更新时间,
	 // dstatus设备状态,
	 // errContent故障内容,
	 // transFlag上传标志,
	 
	//columns START
	/**
	 * id
	 */
	private java.lang.String id;
	/**
	 * 设备编号
	 */
	private java.lang.String dvcId;
	/**
	 * 更新时间
	 */
	private java.util.Date createTime;
	/**
	 * 设备状态
	 */
	private java.lang.Integer dstatus;
	/**
	 * 故障内容
	 */
	private java.lang.String errContent;
	/**
	 * 上传标志
	 */
	private java.lang.Integer transFlag;
	
	private String db=JdbcUtils.getString("jdbc.dbName");
	//columns END
	/**
	 * 表名
	 */
	private String table;
	//concstructor

	public DsStruct(){
	}
	public DsStruct(String dvcId,Integer dstatus,String errContent){
		this.dvcId=dvcId;
		this.dstatus=dstatus;
		this.errContent=errContent;
		this.createTime=new Date();
	}
	public DsStruct(
		java.lang.String id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.String value) {
		this.id = value;
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
	public String getTtimeString() {
		return DateUtils.convertDate2String(FORMAT_TTIME, getCreateTime());
	}
	public void setTtimeString(String value) throws ParseException{
		setCreateTime(DateUtils.convertString2Date(FORMAT_TTIME,value));
	}
	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	public void setDstatus(java.lang.Integer value) {
		this.dstatus = value;
	}
	
	public java.lang.Integer getDstatus() {
		return this.dstatus;
	}
	public void setErrContent(java.lang.String value) {
		this.errContent = value;
	}
	
	public java.lang.String getErrContent() {
		return this.errContent;
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
			.append("Ttime",getCreateTime())
			.append("Dstatus",getDstatus())
			.append("ErrContent",getErrContent())
			.append("TransFlag",getTransFlag())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof DsStruct == false) return false;
		if(this == obj) return true;
		DsStruct other = (DsStruct)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
