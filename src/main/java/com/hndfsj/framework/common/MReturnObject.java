package com.hndfsj.framework.common;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.hndfsj.framework.pager.PageModel;



/**
 * 封装手机返回对象类型（该类为通用型，后期语音、图片etc对象返回时单独定义return object）
 * 
 * @copyright {@link www.hndfsjsoft.com}
 * @author haoyingshuai@hndfsj.com,haoluziqi@126|gmail.com
 * @version 2013-1-17 上午10:46:40
 * @see com.hndfsj.app.common.objects.MReturnObject
 */
@SuppressWarnings("serial")
public class MReturnObject extends PageModel  implements Serializable{
	public static final String SUCCESS = "success";
	public static final String ERROR = "error";
	public static final String TIMEOUT = "hndfsj_app_session_timeout";//session超时
	public static final String FATAL_ERROR = "fatalError";
	public static final String WARNING = "warning";
	private String respCode;// 返回该次请求状态code
	private String respMessage;// 消息内容
	private Object respBody;
	private Object respList;// 封装好的返回对象数据模型（可能是json对象或者json数组，亦或者是一个value）
	private String sessionId;// 加入sessionid参数，保存会话
	private Integer   itemCount;
	private Date serverDate;

	public MReturnObject(String respCode) {
		this.respCode = respCode;
		this.serverDate=new Date();
	}

	public MReturnObject(String respCode,String respMessage, Object respBody, String sessionId) {
		this.respCode = respCode;
		this.respBody = respBody;
		this.respMessage = respMessage;
		this.sessionId = sessionId;
		this.serverDate=new Date();
	}

	public MReturnObject(String respCode, String respMessage) {
		this.respCode = respCode;
		this.respMessage = respMessage;
		this.serverDate=new Date();
	}
	
	
	

	public MReturnObject(String respCode, Object object) {
		this.respCode = respCode;
		this.respBody=object;
	}
	public MReturnObject(String respCode, List  list) {
		this.respCode = respCode;
		this.respList=list;
	}

	public MReturnObject(String status, String message, Object obj) {
		this.respCode = status;
		this.respMessage = message;
		if(obj instanceof List ){
			respList=obj;
		}else{
			respBody=obj;
		}
	}

	
	public MReturnObject(String respCode, Object respBody, Object respList) {
		super();
		this.respCode = respCode;
		this.respBody = respBody;
		this.respList = respList;
	}


	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getRespMessage() {
		return respMessage;
	}

	public void setRespMessage(String respMessage) {
		this.respMessage = respMessage;
	}

	public Object getRespBody() {
		return respBody;
	}

	public void setRespBody(Object respBody) {
		this.respBody = respBody;
	}



	public Object getRespList() {
		return respList;
	}

	public void setRespList(Object respList) {
		this.respList = respList;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Integer getItemCount() {
		return itemCount;
	}

	public void setItemCount(Integer itemCount) {
		this.itemCount = itemCount;
	}

	public Date getServerDate() {
		return serverDate;
	}

	public void setServerDate(Date serverDate) {
		this.serverDate = serverDate;
	}



}
