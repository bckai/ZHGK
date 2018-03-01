package com.hndfsj.app.device.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.hndfsj.framework.utils.DateUtils;
import com.hndfsj.framework.utils.UUIDGenerator;
import com.hndfsj.framework.base.dao.IBaseDao;
import com.hndfsj.framework.base.service.BaseServiceImpl;
import com.hndfsj.framework.exceptions.ValidateParamException;
import com.hndfsj.framework.pager.PageRequest;
import com.hndfsj.app.device.dao.IDsStructDao;
import com.hndfsj.app.device.domain.DsStruct;
import com.hndfsj.app.device.service.IDsStructService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO
 * 
 * @copyright {@link www.hndfsj.com}
 * @author BuChunKai<Auto generate>
 * @version 2017-09-05 14:27:31
 * @see com.hndfsj.app.device.service.impl.DsStruct
 */
@Service("dsStructService")
public class DsStructServiceImpl extends BaseServiceImpl<DsStruct, String> implements IDsStructService {

	static Logger log = LoggerFactory.getLogger(DsStructServiceImpl.class);

	@Resource
	private IDsStructDao dsStructDao;

	@Override
	protected IBaseDao<DsStruct, String> getBaseDao() {
		return this.dsStructDao;
	}

	public void validateEntity(DsStruct dsStruct) throws ValidateParamException {
		// TODO write validate code throw new ValidateParamException
	}

	@Override
	public void save(DsStruct dsStruct) {
		dsStruct.setCreateTime(new Date());
		dsStruct.setId(UUIDGenerator.UUIDValue());
		dsStruct.setTransFlag(0);
		dsStruct.setTable("hd_ds_" + DateUtils.formatDate(dsStruct.getCreateTime(), DateUtils.DATETIME_YM_FORMAT));
		if (dsStructDao.getTableCount(dsStruct) == 0) {
			dsStructDao.insertTable(dsStruct);
		}
		super.save(dsStruct);
		dsStruct.setTable("hd_ds_struct");
		dsStructDao.deleteById(dsStruct.getDvcId());
		super.save(dsStruct);
	}

	@Override
	public int isTableExists(DsStruct dsStruct) {
		return dsStructDao.isTableExists(dsStruct);
	}

	@Override
	public List<DsStruct> getAllPage(PageRequest pageRequest) {
		return dsStructDao.getAllPage(pageRequest);
	}

	@Override
	public List<Map<String, String>> deviceRate() {
		return dsStructDao.deviceRate();
	}
}
