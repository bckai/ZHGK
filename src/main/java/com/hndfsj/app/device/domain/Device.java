
package com.hndfsj.app.device.domain;

import com.hndfsj.framework.utils.DateUtils;
import java.text.ParseException;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * TODO 
 * @copyright {@link www.hndfsj.com}
 * @author BuChunKai<Auto generate>
 * @version  2017-09-05 11:55:32
 * @see com.hndfsj.app.device.domain.Device
 */
public class Device  implements java.io.Serializable {
	
	private static final long serialVersionUID = 5454155825314635342L;

	//date formats
	public static final String ID = "hd_device.id";
	public static final String NAME = "hd_device.name";
	public static final String DIRECTION = "hd_device.direction";
	public static final String FORMAT_ENTER_TIME = DateUtils.DATETIME_SECOND_FORMAT;
	public static final String CREATE_TIME = "hd_device.create_time";
	public static final String VIDEO = "hd_device.video";
	public static final String IS_DELETED = "hd_device.isDeleted";
	public static final String POSITION = "hd_device.position";
	public static final String DVC_TYPE = "hd_device.dvcType";
 
	 // id设备编号（依据联网中心技术标准，含分中心编码）,
	 // dvcName设备名称,
	 // location安装位置,
	 // enterTime录入时间,
	 // isDeleted是否删除 0 未删除 1 已删除,
	 // positions坐标-经纬度,
	 
	//columns START
	/**
	 * 设备编号（依据联网中心技术标准，含分中心编码）
	 */
	private java.lang.String id;
	/**
	 * 设备名称
	 */
	private java.lang.String name;
	/**
	 * 安装位置
	 */
	private java.lang.String direction;
	/**
	 * 录入时间
	 */
	private java.util.Date createTime;
	private java.util.Date modifyTime;

	/**
	 * 是否删除 0 未删除 1 已删除
	 */
	private java.lang.Integer isDeleted;
	/**
	 * 坐标-经度
	 */
	private double[] position;
	private String positions;
	
	private int[] size;
	private String sizes;
	private java.lang.String type;
	
	private java.lang.String count;
	
	private java.lang.String video;
	
	private DeviceConfig deviceConfig;
	private DsStruct dsStruct;
	private WsStruct wsStruct;
	private VdStruct vdStruct;
	private List<VdStruct> vdStructs;
	private List<CmsStruct> cmsStructs;
	
	//columns END
	
	//concstructor

	public Device(){
	}

	public Device(
		java.lang.String id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.String value) {
		this.id = value;
	}

	public List<VdStruct> getVdStructs() {
		return vdStructs;
	}

	public void setVdStructs(List<VdStruct> vdStructs) {
		this.vdStructs = vdStructs;
	}

	public java.lang.String getCount() {
		return count;
	}

	public void setCount(java.lang.String count) {
		this.count = count;
	}

	public java.util.Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(java.util.Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public VdStruct getVdStruct() {
		return vdStruct;
	}

	public void setVdStruct(VdStruct vdStruct) {
		this.vdStruct = vdStruct;
	}

	public WsStruct getWsStruct() {
		return wsStruct;
	}

	public void setWsStruct(WsStruct wsStruct) {
		this.wsStruct = wsStruct;
	}

	public List<CmsStruct> getCmsStructs() {
		return cmsStructs;
	}

	public void setCmsStructs(List<CmsStruct> cmsStructs) {
		this.cmsStructs = cmsStructs;
	}

	public DsStruct getDsStruct() {
		return dsStruct;
	}

	public void setDsStruct(DsStruct dsStruct) {
		this.dsStruct = dsStruct;
	}

	public DeviceConfig getDeviceConfig() {
		return deviceConfig;
	}

	public void setDeviceConfig(DeviceConfig deviceConfig) {
		this.deviceConfig = deviceConfig;
	}

	public java.lang.String getVideo() {
		return video;
	}

	public void setVideo(java.lang.String video) {
		this.video = video;
	}

	public String getPositions() {
		return positions;
	}

	public void setPositions(String positions) {
		this.positions = positions;
	}

	public String getSizes() {
		return sizes;
	}

	public void setSizes(String sizes) {
		this.sizes = sizes;
	}

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.String getDirection() {
		return direction;
	}

	public void setDirection(java.lang.String direction) {
		this.direction = direction;
	}

	public double[] getPosition() {
		return position;
	}

	public void setPosition(double[] position) {
		this.position = position;
	}

	public int[] getSize() {
		return size;
	}

	public void setSize(int[] strings) {
		this.size = strings;
	}

	public java.lang.String getType() {
		return type;
	}

	public void setType(java.lang.String type) {
		this.type = type;
	}

	public java.lang.String getId() {
		return this.id;
	}
	public String getEnterTimeString() {
		return DateUtils.convertDate2String(FORMAT_ENTER_TIME, getCreateTime());
	}
	public void setCreateTimeString(String value) throws ParseException{
		setCreateTime(DateUtils.convertString2Date(FORMAT_ENTER_TIME,value));
	}
	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	public void setIsDeleted(java.lang.Integer value) {
		this.isDeleted = value;
	}
	
	public java.lang.Integer getIsDeleted() {
		return this.isDeleted;
	}
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("IsDeleted",getIsDeleted())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Device == false) return false;
		if(this == obj) return true;
		Device other = (Device)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
