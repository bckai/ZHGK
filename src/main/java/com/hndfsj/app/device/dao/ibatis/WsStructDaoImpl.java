package com.hndfsj.app.device.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hndfsj.framework.base.dao.BaseDaoImpl;
import com.hndfsj.framework.pager.PageRequest;
import com.hndfsj.app.device.dao.IWsStructDao;
import com.hndfsj.app.device.domain.VdStruct;
import com.hndfsj.app.device.domain.WsStruct;

/**
 * TODO 
 * @copyright {@link www.hndfsj.com}
 * @author BuChunKai<Auto generate>
 * @version  2017-09-05 14:27:53
 * @see com.hndfsj.app.device.dao.WsStruct
 */
@Repository("wsStructDao")
public class WsStructDaoImpl extends BaseDaoImpl<WsStruct, String>implements IWsStructDao {

	//concstructor

	public WsStructDaoImpl(){
		super(WsStruct.class);
	}

	@Override
	public void insertTable(WsStruct wsStruct) {
		insert("insertTable", wsStruct);
	}

	@Override
	public List<WsStruct> getAllPage(PageRequest pageRequest){
		return queryList("getAllPage", pageRequest);
	}
	
	@Override
	public int isTableExists(WsStruct wsStruct){
		return getCountTable("isTableExists", wsStruct);
	}

	@Override
	public WsStruct getByOneId(PageRequest pageRequest) {
		return queryObject("getByOneId",pageRequest);
	}

	@Override
	public WsStruct status(String dvcId) {
		WsStruct wsStruct=new WsStruct();
		wsStruct.setDvcId(dvcId);
		return queryObject("status",wsStruct);
	}
}
