package com.hndfsj.admin.dao;

import com.hndfsj.admin.domain.Dictionary;
import com.hndfsj.framework.base.dao.IBaseDao;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link www.hndfsj.com}
 * @author ZhengXingJian<Auto generate>
 * @version 2016-10-21 13:55:35
 * @see com.hndfsj.admin.dao.Dictionary
 */
public interface IDictionaryDao extends IBaseDao<Dictionary, java.lang.String> {

	void addCountOne(String code);

	void addEventCountOne(Dictionary dictionary);
	
	Dictionary getByValue(Dictionary dictionary);

}
