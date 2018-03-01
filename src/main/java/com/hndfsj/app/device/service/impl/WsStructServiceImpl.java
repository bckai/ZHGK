package com.hndfsj.app.device.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.hndfsj.framework.utils.DateUtils;
import com.hndfsj.framework.base.dao.IBaseDao;
import com.hndfsj.framework.base.service.BaseServiceImpl;
import com.hndfsj.framework.exceptions.ValidateParamException;
import com.hndfsj.framework.pager.PageRequest;
import com.hndfsj.admin.dao.IDictionaryDao;
import com.hndfsj.app.common.enums.ZHGKEnum.DICTIONARY_COUNT;
import com.hndfsj.app.device.dao.IWsStructDao;
import com.hndfsj.app.device.domain.WsStruct;
import com.hndfsj.app.device.service.IWsStructService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO
 * 
 * @copyright {@link www.hndfsj.com}
 * @author BuChunKai<Auto generate>
 * @version 2017-09-05 14:27:53
 * @see com.hndfsj.app.device.service.impl.WsStruct
 */
@Service("wsStructService")
public class WsStructServiceImpl extends BaseServiceImpl<WsStruct, String> implements IWsStructService {

	static Logger log = LoggerFactory.getLogger(WsStructServiceImpl.class);

	@Resource
	private IWsStructDao wsStructDao;

	@Resource
	private IDictionaryDao dictionaryDao;

	@Override
	protected IBaseDao<WsStruct, String> getBaseDao() {
		return this.wsStructDao;
	}

	public void validateEntity(WsStruct wsStruct) throws ValidateParamException {
		// TODO write validate code throw new ValidateParamException
	}

	@Override
	public void save(WsStruct wsStruct) {
		wsStruct.setCreateTime(new Date());
		wsStruct.setTable("hd_ws_" + DateUtils.formatDate(wsStruct.getCreateTime(), DateUtils.DATETIME_YM_FORMAT));
		if (wsStructDao.getTableCount(wsStruct) == 0) {
			wsStructDao.insertTable(wsStruct);
		}
		super.save(wsStruct);
		wsStructDao.deleteById(wsStruct.getDvcId());
		wsStruct.setTable("hd_ws_struct");
		super.save(wsStruct);
		dictionaryDao.addCountOne(DICTIONARY_COUNT.WD.name());
	}

	@Override
	public List<WsStruct> getAllPage(PageRequest pageRequest) {
		return wsStructDao.getAllPage(pageRequest);
	}

	@Override
	public int isTableExists(WsStruct wsStruct) {
		return wsStructDao.isTableExists(wsStruct);
	}

	@Override
	public WsStruct getByOneId(PageRequest pageRequest) {
		return wsStructDao.getByOneId(pageRequest);
	}

	@Override
	public WsStruct status(String dvcId) {
		return wsStructDao.status(dvcId);
	}
}
