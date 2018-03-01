package com.hndfsj.app.device.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hndfsj.framework.base.dao.BaseDaoImpl;
import com.hndfsj.framework.pager.PageRequest;
import com.hndfsj.app.device.dao.IVdStructDao;
import com.hndfsj.app.device.domain.VdStruct;

/**
 * TODO 
 * @copyright {@link www.hndfsj.com}
 * @author BuChunKai<Auto generate>
 * @version  2017-09-05 14:37:21
 * @see com.hndfsj.app.device.dao.VdStruct
 */
@Repository("vdStructDao")
public class VdStructDaoImpl extends BaseDaoImpl<VdStruct, String>implements IVdStructDao {

	//concstructor

	public VdStructDaoImpl(){
		super(VdStruct.class);
	}

	@Override
	public void insertTable(VdStruct vdStruct) {
		insert("insertTable", vdStruct);
	}

	@Override
	public List<VdStruct> getAllPage(PageRequest pageRequest){
		return queryList("getAllPage", pageRequest);
	}
	
	@Override
	public int isTableExists(VdStruct vdStruct){
		return getCountTable("isTableExists", vdStruct);
	}

	@Override
	public VdStruct status(String dvcId) {
		VdStruct vdStruct=new VdStruct();
		vdStruct.setDvcId(dvcId);
		return queryObject("status",vdStruct);
	}

}
