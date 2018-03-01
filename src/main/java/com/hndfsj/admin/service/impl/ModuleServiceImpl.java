package com.hndfsj.admin.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.hndfsj.admin.dao.IModuleDao;
import com.hndfsj.admin.domain.Module;
import com.hndfsj.admin.service.IModuleService;
import com.hndfsj.framework.base.dao.IBaseDao;
import com.hndfsj.framework.base.service.BaseServiceImpl;
import com.hndfsj.framework.config.EnumType;
import com.hndfsj.framework.exceptions.ValidateParamException;
import com.hndfsj.framework.pager.PageRequest;
import com.hndfsj.framework.pager.SearchCondition;
import com.hndfsj.framework.security.bean.RWUserDetails;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO 在此加入类描述
 * @copyright {@link www.hndfsj.com}
 * @author ZhengXingJian<Auto generate>
 * @version  2016-10-21 13:58:21
 * @see com.hndfsj.admin.service.impl.Module
 */
@Service("moduleService")
public class ModuleServiceImpl extends BaseServiceImpl<Module,  java.lang.String > implements IModuleService {

	static Logger log=LoggerFactory.getLogger(ModuleServiceImpl.class);

	@Resource
	private IModuleDao moduleDao;
	
	@Override
	protected IBaseDao<Module , java.lang.String> getBaseDao() {
		return this.moduleDao;
	}
	public void validateEntity(Module module)throws ValidateParamException {
		//TODO write validate code throw new ValidateParamException
	}
	@Override
	public List<Module> getModuleByParentId(String parentId) {
		PageRequest pageRequest = new PageRequest();
		pageRequest.addAndCondition("superMod", SearchCondition.EQUAL, parentId);

		return moduleDao.findAll(pageRequest);
	}

	@Override
	public List<Module> getAllModuleResource() {
		return moduleDao.getAllModuleResource();
	}

	@Override
	public List<Module> getModuleByUser(RWUserDetails userDetail) {
		// ==得到所有的模块
		List<Module> allModule = moduleDao.findAll();
		// ==superAdmin
		final String ADM = String.valueOf(EnumType.UserType.ADMIN.getUserType());
		if (ADM.equals(userDetail.getUserType())) {
			return allModule;
		}

		HashMap<String, Module> hashMod = new HashMap<String, Module>();
		for (Module mod : allModule) {
			hashMod.put(mod.getId(), mod);
		}

		// 得到登陆人员已经具有的模块
		List<Module> userModuleList = moduleDao.getModIdByUserId(userDetail.getUserId());
		List<Module> destModuleList = new ArrayList<Module>(userModuleList);
		for (Module module : userModuleList) {
			// 使用递归获取子模块的所有上级模块
			getParentModule(hashMod, module, destModuleList);
		}
		return destModuleList;
	}

	@Override
	public List<Module> findNotLeaf() {
		PageRequest pageRequest = new PageRequest();
		pageRequest.addAndCondition("isLeaf", SearchCondition.EQUAL, "0");
		return moduleDao.findAll(pageRequest);
	}

	/**
	 * 获取子模块的上级模块
	 * 
	 * @param hashMod
	 * @param module
	 * @param destModuleList
	 */
	private void getParentModule(HashMap<String, Module> hashMod, Module module,
			List<Module> destModuleList) {
		// 父ID为NULL则直接返回
		if (StringUtils.isBlank(module.getSuperMod())) {
			return;
		}
		Module mod = hashMod.get(module.getSuperMod());
		if (!destModuleList.contains(mod)) {
			destModuleList.add(mod);
		}
		getParentModule(hashMod, mod, destModuleList);
	}

	@Override
	public List<Module> getModuleByUserAndParent(RWUserDetails user, String parentId) {
		if (StringUtils.isBlank(parentId)) {
			List<Module> mdule=getRootModuleByUser(user);
			if(mdule.size()>0){
				parentId = mdule.get(0).getId();
			}
		}
		List<Module> uM = getModuleByUser(user);
		List<Module> pM = getModuleByParentId(parentId);
		List<Module> result = new ArrayList<Module>();
		for (Module uModule : pM) {
			for (Module pModule : uM) {
				if (pModule.getId().equals(uModule.getId())) {
					result.add(pModule);
				}
			}
		}
		return result;
	}

	@Override
	public List<Module> getRootModuleByUser(RWUserDetails user) {
		List<Module> uM = getModuleByUser(user);
		List<Module> rM = findNotLeaf();
		List<Module> result = new ArrayList<Module>();
		for (Module uModule : rM) {
			for (Module rModule : uM) {
				if (rModule.getId().equals(uModule.getId())) {
					result.add(rModule);
				}
			}
		}
		return result;
	}
	
}
