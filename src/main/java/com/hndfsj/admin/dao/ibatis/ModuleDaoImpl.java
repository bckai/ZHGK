package com.hndfsj.admin.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hndfsj.admin.dao.IModuleDao;
import com.hndfsj.admin.domain.Module;
import com.hndfsj.framework.base.dao.BaseDaoImpl;

/**
 * 模块Dao实现类
 * 
 * @author ibm
 * @date May 18, 2010
 */
@Repository("moduleDao")
public class ModuleDaoImpl extends BaseDaoImpl<Module, String> implements IModuleDao {

	public ModuleDaoImpl() {
		super(Module.class);
	}

	@Override
	public List<Module> getAllModuleResource() {
		return (List<Module>) queryList("getAllModuleResource");
	}

	@Override
	public List<Module> getModIdByUserId(String userId) {
		return (List<Module>) queryList("getModIdByUserId", userId);
	}

}