package com.hndfsj.admin.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hndfsj.admin.dao.IDictionaryDao;
import com.hndfsj.admin.domain.Dictionary;
import com.hndfsj.app.common.enums.ZHGKEnum.DICTIONARY_COUNT;
import com.hndfsj.app.common.enums.ZHGKEnum.DICTIONARY_TYPE;
import com.hndfsj.framework.base.dao.BaseDaoImpl;
import com.hndfsj.framework.pager.PageRequest;
import com.hndfsj.framework.pager.SearchCondition;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link www.hndfsj.com}
 * @author ZhengXingJian<Auto generate>
 * @version 2016-10-21 13:55:35
 * @see com.hndfsj.admin.dao.Dictionary
 */
@Repository("dictionaryDao")
public class DictionaryDaoImpl extends BaseDaoImpl<Dictionary, java.lang.String> implements IDictionaryDao {

	// concstructor

	public DictionaryDaoImpl() {
		super(Dictionary.class);
	}

	@Override
	public void addCountOne(String code) {
		PageRequest pageRequest = new PageRequest();
		pageRequest.addAndCondition(Dictionary.CODE, SearchCondition.EQUAL, code);
		pageRequest.addAndCondition(Dictionary.DICTYPE, SearchCondition.EQUAL, DICTIONARY_COUNT.VD.getId().toString());
		List<Dictionary> dictionaries = findAll(pageRequest);
		if (!dictionaries.isEmpty()) {
			update("updateCount", dictionaries.get(0));
			return;
		}
		save(new Dictionary(code, "1", DICTIONARY_COUNT.VD.getId().toString()));
	}

	@Override
	public void addEventCountOne(Dictionary dictionary) {
		PageRequest pageRequest = new PageRequest();
		pageRequest.addAndCondition(Dictionary.CODE, SearchCondition.EQUAL, dictionary.getCode());
		pageRequest.addAndCondition(Dictionary.DICTYPE, SearchCondition.EQUAL, DICTIONARY_TYPE.INCIDENT_COUNT.name());
		List<Dictionary> dictionaries = findAll(pageRequest);
		if (!dictionaries.isEmpty()) {
			update("updateCount", dictionaries.get(0));
			return;
		}
		save(new Dictionary(dictionary.getCode(), "1", DICTIONARY_COUNT.VD.getId().toString()));
	}

	@Override
	public Dictionary getByValue(Dictionary dictionary) {
		return queryObject("getByValue", dictionary);
	}
}
