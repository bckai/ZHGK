package com.hndfsj.app.trafficevent.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.hndfsj.framework.base.dao.IBaseDao;
import com.hndfsj.framework.base.service.BaseServiceImpl;
import com.hndfsj.framework.exceptions.ValidateParamException;
import com.hndfsj.admin.dao.IDictionaryDao;
import com.hndfsj.admin.domain.Dictionary;
import com.hndfsj.app.common.enums.ZHGKEnum.DICTIONARY_TYPE;
import com.hndfsj.app.trafficevent.dao.ITrafficEventDao;
import com.hndfsj.app.trafficevent.domain.TrafficEvent;
import com.hndfsj.app.trafficevent.service.ITrafficEventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 交通事件
 * 
 * @copyright {@link www.hndfsj.com}
 * @author BuChunKai<Auto generate>
 * @version 2017-10-17 16:53:03
 * @see com.hndfsj.app.trafficevent.service.impl.TrafficEvent
 */
@Service("trafficEventService")
public class TrafficEventServiceImpl extends BaseServiceImpl<TrafficEvent, java.lang.String>
		implements ITrafficEventService {

	static Logger log = LoggerFactory.getLogger(TrafficEventServiceImpl.class);

	@Resource
	private ITrafficEventDao trafficEventDao;

	@Resource
	private IDictionaryDao dictionaryDao;

	@Override
	protected IBaseDao<TrafficEvent, java.lang.String> getBaseDao() {
		return this.trafficEventDao;
	}

	public void validateEntity(TrafficEvent trafficEvent) throws ValidateParamException {
	}

	@Override
	public void save(TrafficEvent entity) {
		super.save(entity);
		Dictionary dictionary = new Dictionary();
		dictionary.setValue(entity.getType());
		dictionary.setDicType(DICTIONARY_TYPE.INCIDENT_COUNT.name());
		dictionary = dictionaryDao.getByValue(dictionary);
		if (dictionary != null) {
			dictionaryDao.addEventCountOne(dictionary);
		}
	}
}
