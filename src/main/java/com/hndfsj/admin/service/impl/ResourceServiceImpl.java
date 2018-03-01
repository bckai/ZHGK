package com.hndfsj.admin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.hndfsj.admin.dao.IResourceDao;
import com.hndfsj.admin.service.IResourceService;
import com.hndfsj.framework.base.dao.IBaseDao;
import com.hndfsj.framework.base.service.BaseServiceImpl;
import com.hndfsj.framework.exceptions.ValidateParamException;
import com.hndfsj.framework.pager.PageRequest;
import com.hndfsj.framework.pager.SearchCondition;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link www.hndfsj.com}
 * @author ZhengXingJian<Auto generate>
 * @version 2016-10-21 13:59:10
 * @see com.hndfsj.admin.service.impl.Resource
 */
@Service("resourceService")
public class ResourceServiceImpl extends BaseServiceImpl<com.hndfsj.admin.domain.Resource, java.lang.String>
		implements IResourceService {

	static Logger log = LoggerFactory.getLogger(ResourceServiceImpl.class);

	@Resource
	private IResourceDao resourceDao;

	@Override
	protected IBaseDao<com.hndfsj.admin.domain.Resource, java.lang.String> getBaseDao() {
		return this.resourceDao;
	}

	public void validateEntity(com.hndfsj.admin.domain.Resource resource) throws ValidateParamException {
		// TODO write validate code throw new ValidateParamException
	}
	
	@Override
	@CacheEvict(value = "adminResource",allEntries=true)
	public void deleteById(String id) {
		super.deleteById(id);
	}

	@Override
	@CacheEvict(value = "adminResource",allEntries=true)
	public void save(com.hndfsj.admin.domain.Resource entity) {
		super.save(entity);
	}

	@Override
	@CacheEvict(value = "adminResource",allEntries=true)
	public void update(com.hndfsj.admin.domain.Resource entity){
		getBaseDao().update(entity);
	}

	@Override
	public List<com.hndfsj.admin.domain.Resource> getResourcesForSecurity() {
		return resourceDao.getResourcesForSecurity();
	}

	@Override
	public List<com.hndfsj.admin.domain.Resource> getResourceByModuleId(String moduleId) {
		PageRequest pageRequest = new PageRequest();
		pageRequest.addAndCondition("modId", SearchCondition.EQUAL, moduleId);
		pageRequest.addSortConditions("sortNo", "asc");
		
		return resourceDao.findAll(pageRequest);
	}

	@Override
	public List<com.hndfsj.admin.domain.Resource> getResourceByUserId(String userId) {
		return resourceDao.getResourceByUserId(userId);
	}

	@Override
	public List<com.hndfsj.admin.domain.Resource> getResourceByRoleId(String roleId) {
		return resourceDao.getResourceByRoleId(roleId);
	}
	@Override
	public List<String> findAllResourceUrl() {
		return resourceDao.findAllResourceUrl();
	}

	/* (non-Javadoc)
	 * @see com.hndfsj.admin.services.IResourceService#getResourceIdAndRoleId(java.lang.String, java.lang.String)
	 */
	@Override
	public Boolean getResourceIdAndRoleId(String resouceId, String roleId) {
		//2013-7-15下午03:36:18
		return resourceDao.getResourceIdAndRoleId(resouceId, roleId);
	}

}
