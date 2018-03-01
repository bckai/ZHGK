
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
 * @version  2017-09-05 14:29:09
 * @see com.hndfsj.app.device.domain.WsStruct
 */
public class WsStruct  implements java.io.Serializable {
	
	private static final long serialVersionUID = 5454155825314635342L;

	//date formats
	public static final String ID = "hd_ws_struct.id";
	public static final String DVC_ID = "hd_ws_struct.dvcId";
	public static final String FORMAT_TTIME = DateUtils.DATETIME_SECOND_FORMAT;
	public static final String CREATE_TTIME = "hd_ws_struct.create_Time";
	public static final String VIS_AVG = "hd_ws_struct.visAvg";
	public static final String WSPEED_AVG = "hd_ws_struct.wSpeedAvg";
	public static final String WDIR = "hd_ws_struct.wDir";
	public static final String ATEMP_AVG = "hd_ws_struct.atempAvg";
	public static final String RTEMP_AVG = "hd_ws_struct.rtempAvg";
	public static final String HUMI_AVG = "hd_ws_struct.humiAvg";
	public static final String RAIN_VOL = "hd_ws_struct.rainVol";
	public static final String RSUR_FACE = "hd_ws_struct.rsurFace";
	public static final String PERIOD = "hd_ws_struct.period";
	public static final String TRANS_FLAG = "hd_ws_struct.transFlag";
	public static final String TABLE = "table";
	
	 // idid,
	 // dvcId气象设备编号,
	 // createTime数据时间,
	 // visAvg能见度平均值,
	 // wspeedAvg风速平均值,
	 // wdir风向,
	 // atempAvg气温平均值,
	 // rtempAvg路面温度平均值,
	 // humiAvg湿度平均值,
	 // rainVol降雨量,
	 // rsurFace路面状况 00干燥 01潮湿 02多雨 03冰冻 04霜/雪 05残盐 06冰雨,
	 // period采集周期,
	 // transFlag上传标志,
	 
	//columns START
	/**
	 * id
	 */
	private java.lang.String id;
	/**
	 * 气象设备编号
	 */
	private java.lang.String dvcId;
	/**
	 * 数据时间
	 */
	private java.util.Date createTime;
	/**
	 * 能见度平均值
	 */
	private java.lang.Double visAvg;
	/**
	 * 风速平均值
	 */
	private java.lang.Double wspeedAvg;
	/**
	 * 风向
	 */
	private java.lang.Integer wdir;
	/**
	 * 气温平均值
	 */
	private java.lang.Double atempAvg;
	/**
	 * 路面温度平均值
	 */
	private java.lang.Double rtempAvg;
	/**
	 * 湿度平均值
	 */
	private java.lang.Double humiAvg;
	/**
	 * 降雨量
	 */
	private java.lang.Double rainVol;
	/**
	 * 路面状况 00干燥 01潮湿 02多雨 03冰冻 04霜/雪 05残盐 06冰雨
	 */
	private java.lang.String rsurFace;
	/**
	 * 采集周期
	 */
	private java.lang.Integer period;
	/**
	 * 上传标志
	 */
	private java.lang.Integer transFlag;
	
	private String name;
	private String db=JdbcUtils.getString("jdbc.dbName");
	//columns END
	/**
	 * 表名
	 */
	private String table;
	//concstructor

	public WsStruct(){
	}

	public WsStruct(
		java.lang.String id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.String value) {
		this.id = value;
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
	public void setVisAvg(java.lang.Double value) {
		this.visAvg = value;
	}
	
	public java.lang.Double getVisAvg() {
		return this.visAvg;
	}
	public void setWspeedAvg(java.lang.Double value) {
		this.wspeedAvg = value;
	}
	
	public java.lang.Double getWspeedAvg() {
		return this.wspeedAvg;
	}
	public void setWdir(java.lang.Integer value) {
		this.wdir = value;
	}
	
	public java.lang.Integer getWdir() {
		return this.wdir;
	}
	public void setAtempAvg(java.lang.Double value) {
		this.atempAvg = value;
	}
	
	public java.lang.Double getAtempAvg() {
		return this.atempAvg;
	}
	public void setRtempAvg(java.lang.Double value) {
		this.rtempAvg = value;
	}
	
	public java.lang.Double getRtempAvg() {
		return this.rtempAvg;
	}
	public void setHumiAvg(java.lang.Double value) {
		this.humiAvg = value;
	}
	
	public java.lang.Double getHumiAvg() {
		return this.humiAvg;
	}
	public void setRainVol(java.lang.Double value) {
		this.rainVol = value;
	}
	
	public java.lang.Double getRainVol() {
		return this.rainVol;
	}
	public void setRsurFace(java.lang.String value) {
		this.rsurFace = value;
	}
	
	public java.lang.String getRsurFace() {
		return this.rsurFace;
	}
	public void setPeriod(java.lang.Integer value) {
		this.period = value;
	}
	
	public java.lang.Integer getPeriod() {
		return this.period;
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
			.append("VisAvg",getVisAvg())
			.append("WspeedAvg",getWspeedAvg())
			.append("Wdir",getWdir())
			.append("AtempAvg",getAtempAvg())
			.append("RtempAvg",getRtempAvg())
			.append("HumiAvg",getHumiAvg())
			.append("RainVol",getRainVol())
			.append("RsurFace",getRsurFace())
			.append("Period",getPeriod())
			.append("TransFlag",getTransFlag())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof WsStruct == false) return false;
		if(this == obj) return true;
		WsStruct other = (WsStruct)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
