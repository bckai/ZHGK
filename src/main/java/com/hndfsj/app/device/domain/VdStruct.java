
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
 * @author BuChunKai<Auto generate>
 * @version  2017-09-05 14:39:32
 * @see com.hndfsj.app.device.domain.VdStruct
 */
public class VdStruct  implements java.io.Serializable {
	
	private static final long serialVersionUID = 5454155825314635342L;

	//date formats
	public static final String DVC_ID = "hd_vd_struct.dvcId";
	public static final String CAR_TYPE = "hd_vd_struct.carType";
	public static final String FORMAT_TTIME = DateUtils.DATETIME_SECOND_FORMAT;
	public static final String CREATE_TTIME = "hd_vd_struct.create_time";
	public static final String PERIOD = "hd_vd_struct.period";
	public static final String LANE1_FLUX = "hd_vd_struct.lane1Flux";
	public static final String LANE2_FLUX = "hd_vd_struct.lane2Flux";
	public static final String LANE3_FLUX = "hd_vd_struct.lane3Flux";
	public static final String LANE4_FLUX = "hd_vd_struct.lane4Flux";
	public static final String LANE5_FLUX = "hd_vd_struct.lane5Flux";
	public static final String LANE6_FLUX = "hd_vd_struct.lane6Flux";
	public static final String LANE7_FLUX = "hd_vd_struct.lane7Flux";
	public static final String LANE8_FLUX = "hd_vd_struct.lane8Flux";
	public static final String LANE1_SPEED = "hd_vd_struct.lane1Speed";
	public static final String LANE2_SPEED = "hd_vd_struct.lane2Speed";
	public static final String LANE3_SPEED = "hd_vd_struct.lane3Speed";
	public static final String LANE4_SPEED = "hd_vd_struct.lane4Speed";
	public static final String LANE5_SPEED = "hd_vd_struct.lane5Speed";
	public static final String LANE6_SPEED = "hd_vd_struct.lane6Speed";
	public static final String LANE7_SPEED = "hd_vd_struct.lane7Speed";
	public static final String LANE8_SPEED = "hd_vd_struct.lane8Speed";
	public static final String LANE1_OCC = "hd_vd_struct.lane1Occ";
	public static final String LANE2_OCC = "hd_vd_struct.lane2Occ";
	public static final String LANE3_OCC = "hd_vd_struct.lane3Occ";
	public static final String LANE4_OCC = "hd_vd_struct.lane4Occ";
	public static final String LANE5_OCC = "hd_vd_struct.lane5Occ";
	public static final String LANE6_OCC = "hd_vd_struct.lane6Occ";
	public static final String LANE7_OCC = "hd_vd_struct.lane7Occ";
	public static final String LANE8_OCC = "hd_vd_struct.lane8Occ";
	public static final String FLUX_UP = "hd_vd_struct.fluxUp";
	public static final String FLUX_DOWN = "hd_vd_struct.fluxDown";
	public static final String SPEED_UP = "hd_vd_struct.speedUp";
	public static final String SPEED_DOWN = "hd_vd_struct.speedDown";
	public static final String OCC_UP = "hd_vd_struct.occUp";
	public static final String OCC_DOWN = "hd_vd_struct.occDown";
	public static final String LANE_NUM = "hd_vd_struct.laneNum";
	public static final String TRANS_FLAG = "hd_vd_struct.transFlag";
	public static final String ID = "hd_vd_struct.id";
 
	 // dvcId设备编号,
	 // carType车长类型,
	 // createTime数据时间,
	 // period采集周期（单位：秒）,
	 // lane1Flux车道1流量,
	 // lane2Flux车道2流量,
	 // lane3Flux车道3流量,
	 // lane4Flux车道4流量,
	 // lane5Flux车道5流量,
	 // lane6Flux车道6流量,
	 // lane7Flux车道7流量,
	 // lane8Flux车道8流量,
	 // lane1Speed车道1速度,
	 // lane2Speed车道2速度,
	 // lane3Speed车道3速度,
	 // lane4Speed车道4速度,
	 // lane5Speed车道5速度,
	 // lane6Speed车道6速度,
	 // lane7Speed车道7速度,
	 // lane8Speed车道8速度,
	 // lane1Occ车道1占有率,
	 // lane2Occ车道2占有率,
	 // lane3Occ车道3占有率,
	 // lane4Occ车道4占有率,
	 // lane5Occ车道5占有率,
	 // lane6Occ车道6占有率,
	 // lane7Occ车道7占有率,
	 // lane8Occ车道8占有率,
	 // fluxUp上行流量,
	 // fluxDown下行流量,
	 // speedUp上行速度,
	 // speedDown下行速度,
	 // occUp上行占有率,
	 // occDown下行占有率,
	 // laneNum总车道数（双向车道数和）,
	 // ds数据状态,
	 // transFlag上传标识,
	 // idid,
	 
	//columns START
	/**
	 * 设备编号
	 */
	private java.lang.String dvcId;
	/**
	 * 车长类型
	 */
	private java.lang.Integer carType;
	/**
	 * 数据时间
	 */
	private java.util.Date createTime;
	/**
	 * 采集周期（单位：秒）
	 */
	private java.lang.Integer period;
	/**
	 * 车道1流量
	 */
	private java.lang.Integer lane1Flux;
	/**
	 * 车道2流量
	 */
	private java.lang.Integer lane2Flux;
	/**
	 * 车道3流量
	 */
	private java.lang.Integer lane3Flux;
	/**
	 * 车道4流量
	 */
	private java.lang.Integer lane4Flux;
	/**
	 * 车道5流量
	 */
	private java.lang.Integer lane5Flux;
	/**
	 * 车道6流量
	 */
	private java.lang.Integer lane6Flux;
	/**
	 * 车道7流量
	 */
	private java.lang.Integer lane7Flux;
	/**
	 * 车道8流量
	 */
	private java.lang.Integer lane8Flux;
	/**
	 * 车道1速度
	 */
	private java.lang.Integer lane1Speed;
	/**
	 * 车道2速度
	 */
	private java.lang.Integer lane2Speed;
	/**
	 * 车道3速度
	 */
	private java.lang.Integer lane3Speed;
	/**
	 * 车道4速度
	 */
	private java.lang.Integer lane4Speed;
	/**
	 * 车道5速度
	 */
	private java.lang.Integer lane5Speed;
	/**
	 * 车道6速度
	 */
	private java.lang.Integer lane6Speed;
	/**
	 * 车道7速度
	 */
	private java.lang.Integer lane7Speed;
	/**
	 * 车道8速度
	 */
	private java.lang.Integer lane8Speed;
	/**
	 * 车道1占有率
	 */
	private java.lang.Double lane1Occ;
	/**
	 * 车道2占有率
	 */
	private java.lang.Double lane2Occ;
	/**
	 * 车道3占有率
	 */
	private java.lang.Double lane3Occ;
	/**
	 * 车道4占有率
	 */
	private java.lang.Double lane4Occ;
	/**
	 * 车道5占有率
	 */
	private java.lang.Double lane5Occ;
	/**
	 * 车道6占有率
	 */
	private java.lang.Double lane6Occ;
	/**
	 * 车道7占有率
	 */
	private java.lang.Double lane7Occ;
	/**
	 * 车道8占有率
	 */
	private java.lang.Double lane8Occ;
	/**
	 * 上行流量
	 */
	private java.lang.Integer fluxUp;
	/**
	 * 下行流量
	 */
	private java.lang.Integer fluxDown;
	/**
	 * 上行速度
	 */
	private java.lang.Integer speedUp;
	/**
	 * 下行速度
	 */
	private java.lang.Integer speedDown;
	/**
	 * 上行占有率
	 */
	private java.lang.Double occUp;
	/**
	 * 下行占有率
	 */
	private java.lang.Double occDown;
	/**
	 * 总车道数（双向车道数和）
	 */
	private java.lang.Integer laneNum;
	/**
	 * 上传标识
	 */
	private java.lang.Integer transFlag;
	/**
	 * id
	 */
	private java.lang.String id;
	//columns END
	/**
	 * 表名
	 */
	private String table;
	private String db=JdbcUtils.getString("jdbc.dbName");
	private String name;
	//concstructor

	public VdStruct(){
	}

	public VdStruct(
		java.lang.String id
	){
		this.id = id;
	}

	//get and set
	public void setDvcId(java.lang.String value) {
		this.dvcId = value;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public java.lang.String getDvcId() {
		return this.dvcId;
	}
	public void setCarType(java.lang.Integer value) {
		this.carType = value;
	}
	
	public java.lang.Integer getCarType() {
		return this.carType;
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
	public void setPeriod(java.lang.Integer value) {
		this.period = value;
	}
	
	public java.lang.Integer getPeriod() {
		return this.period;
	}
	public void setLane1Flux(java.lang.Integer value) {
		this.lane1Flux = value;
	}
	
	public java.lang.Integer getLane1Flux() {
		return this.lane1Flux;
	}
	public void setLane2Flux(java.lang.Integer value) {
		this.lane2Flux = value;
	}
	
	public java.lang.Integer getLane2Flux() {
		return this.lane2Flux;
	}
	public void setLane3Flux(java.lang.Integer value) {
		this.lane3Flux = value;
	}
	
	public java.lang.Integer getLane3Flux() {
		return this.lane3Flux;
	}
	public void setLane4Flux(java.lang.Integer value) {
		this.lane4Flux = value;
	}
	
	public java.lang.Integer getLane4Flux() {
		return this.lane4Flux;
	}
	public void setLane5Flux(java.lang.Integer value) {
		this.lane5Flux = value;
	}
	
	public java.lang.Integer getLane5Flux() {
		return this.lane5Flux;
	}
	public void setLane6Flux(java.lang.Integer value) {
		this.lane6Flux = value;
	}
	
	public java.lang.Integer getLane6Flux() {
		return this.lane6Flux;
	}
	public void setLane7Flux(java.lang.Integer value) {
		this.lane7Flux = value;
	}
	
	public java.lang.Integer getLane7Flux() {
		return this.lane7Flux;
	}
	public void setLane8Flux(java.lang.Integer value) {
		this.lane8Flux = value;
	}
	
	public java.lang.Integer getLane8Flux() {
		return this.lane8Flux;
	}
	public void setLane1Speed(java.lang.Integer value) {
		this.lane1Speed = value;
	}
	
	public java.lang.Integer getLane1Speed() {
		return this.lane1Speed;
	}
	public void setLane2Speed(java.lang.Integer value) {
		this.lane2Speed = value;
	}
	
	public java.lang.Integer getLane2Speed() {
		return this.lane2Speed;
	}
	public void setLane3Speed(java.lang.Integer value) {
		this.lane3Speed = value;
	}
	
	public java.lang.Integer getLane3Speed() {
		return this.lane3Speed;
	}
	public void setLane4Speed(java.lang.Integer value) {
		this.lane4Speed = value;
	}
	
	public java.lang.Integer getLane4Speed() {
		return this.lane4Speed;
	}
	public void setLane5Speed(java.lang.Integer value) {
		this.lane5Speed = value;
	}
	
	public java.lang.Integer getLane5Speed() {
		return this.lane5Speed;
	}
	public void setLane6Speed(java.lang.Integer value) {
		this.lane6Speed = value;
	}
	
	public java.lang.Integer getLane6Speed() {
		return this.lane6Speed;
	}
	public void setLane7Speed(java.lang.Integer value) {
		this.lane7Speed = value;
	}
	
	public java.lang.Integer getLane7Speed() {
		return this.lane7Speed;
	}
	public void setLane8Speed(java.lang.Integer value) {
		this.lane8Speed = value;
	}
	
	public java.lang.Integer getLane8Speed() {
		return this.lane8Speed;
	}
	public void setLane1Occ(java.lang.Double value) {
		this.lane1Occ = value;
	}
	
	public java.lang.Double getLane1Occ() {
		return this.lane1Occ;
	}
	public void setLane2Occ(java.lang.Double value) {
		this.lane2Occ = value;
	}
	
	public java.lang.Double getLane2Occ() {
		return this.lane2Occ;
	}
	public void setLane3Occ(java.lang.Double value) {
		this.lane3Occ = value;
	}
	
	public java.lang.Double getLane3Occ() {
		return this.lane3Occ;
	}
	public void setLane4Occ(java.lang.Double value) {
		this.lane4Occ = value;
	}
	
	public java.lang.Double getLane4Occ() {
		return this.lane4Occ;
	}
	public void setLane5Occ(java.lang.Double value) {
		this.lane5Occ = value;
	}
	
	public java.lang.Double getLane5Occ() {
		return this.lane5Occ;
	}
	public void setLane6Occ(java.lang.Double value) {
		this.lane6Occ = value;
	}
	
	public java.lang.Double getLane6Occ() {
		return this.lane6Occ;
	}
	public void setLane7Occ(java.lang.Double value) {
		this.lane7Occ = value;
	}
	
	public java.lang.Double getLane7Occ() {
		return this.lane7Occ;
	}
	public void setLane8Occ(java.lang.Double value) {
		this.lane8Occ = value;
	}
	
	public java.lang.Double getLane8Occ() {
		return this.lane8Occ;
	}
	public void setFluxUp(java.lang.Integer value) {
		this.fluxUp = value;
	}
	
	public java.lang.Integer getFluxUp() {
		return this.fluxUp;
	}
	public void setFluxDown(java.lang.Integer value) {
		this.fluxDown = value;
	}
	
	public java.lang.Integer getFluxDown() {
		return this.fluxDown;
	}
	public void setSpeedUp(java.lang.Integer value) {
		this.speedUp = value;
	}
	
	public java.lang.Integer getSpeedUp() {
		return this.speedUp;
	}
	public void setSpeedDown(java.lang.Integer value) {
		this.speedDown = value;
	}
	
	public java.lang.Integer getSpeedDown() {
		return this.speedDown;
	}
	public void setOccUp(java.lang.Double value) {
		this.occUp = value;
	}
	
	public java.lang.Double getOccUp() {
		return this.occUp;
	}
	public void setOccDown(java.lang.Double value) {
		this.occDown = value;
	}
	
	public java.lang.Double getOccDown() {
		return this.occDown;
	}
	public void setLaneNum(java.lang.Integer value) {
		this.laneNum = value;
	}
	
	public java.lang.Integer getLaneNum() {
		return this.laneNum;
	}
	public void setTransFlag(java.lang.Integer value) {
		this.transFlag = value;
	}
	
	public java.lang.Integer getTransFlag() {
		return this.transFlag;
	}
	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}
	
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("DvcId",getDvcId())
			.append("CarType",getCarType())
			.append("Period",getPeriod())
			.append("Lane1Flux",getLane1Flux())
			.append("Lane2Flux",getLane2Flux())
			.append("Lane3Flux",getLane3Flux())
			.append("Lane4Flux",getLane4Flux())
			.append("Lane5Flux",getLane5Flux())
			.append("Lane6Flux",getLane6Flux())
			.append("Lane7Flux",getLane7Flux())
			.append("Lane8Flux",getLane8Flux())
			.append("Lane1Speed",getLane1Speed())
			.append("Lane2Speed",getLane2Speed())
			.append("Lane3Speed",getLane3Speed())
			.append("Lane4Speed",getLane4Speed())
			.append("Lane5Speed",getLane5Speed())
			.append("Lane6Speed",getLane6Speed())
			.append("Lane7Speed",getLane7Speed())
			.append("Lane8Speed",getLane8Speed())
			.append("Lane1Occ",getLane1Occ())
			.append("Lane2Occ",getLane2Occ())
			.append("Lane3Occ",getLane3Occ())
			.append("Lane4Occ",getLane4Occ())
			.append("Lane5Occ",getLane5Occ())
			.append("Lane6Occ",getLane6Occ())
			.append("Lane7Occ",getLane7Occ())
			.append("Lane8Occ",getLane8Occ())
			.append("FluxUp",getFluxUp())
			.append("FluxDown",getFluxDown())
			.append("SpeedUp",getSpeedUp())
			.append("SpeedDown",getSpeedDown())
			.append("OccUp",getOccUp())
			.append("OccDown",getOccDown())
			.append("LaneNum",getLaneNum())
			.append("TransFlag",getTransFlag())
			.append("Id",getId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof VdStruct == false) return false;
		if(this == obj) return true;
		VdStruct other = (VdStruct)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
