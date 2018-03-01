package com.hndfsj.admin.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.hndfsj.admin.dao.IDictionaryDao;
import com.hndfsj.admin.domain.Dictionary;
import com.hndfsj.admin.service.IDictionaryService;
import com.hndfsj.framework.base.dao.IBaseDao;
import com.hndfsj.framework.base.service.BaseServiceImpl;
import com.hndfsj.framework.exceptions.ValidateParamException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO 在此加入类描述
 * @copyright {@link www.hndfsj.com}
 * @author ZhengXingJian<Auto generate>
 * @version  2016-10-21 13:55:35
 * @see com.hndfsj.admin.service.impl.Dictionary
 */
@Service("dictionaryService")
public class DictionaryServiceImpl extends BaseServiceImpl<Dictionary,  java.lang.String > implements IDictionaryService {

	static Logger log=LoggerFactory.getLogger(DictionaryServiceImpl.class);

	@Resource
	private IDictionaryDao dictionaryDao;
	
	@Override
	protected IBaseDao<Dictionary , java.lang.String> getBaseDao() {
		return this.dictionaryDao;
	}
	public void validateEntity(Dictionary dictionary)throws ValidateParamException {
		//TODO write validate code throw new ValidateParamException
	}
	
	
}
