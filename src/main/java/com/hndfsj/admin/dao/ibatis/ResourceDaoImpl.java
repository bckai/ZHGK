package com.hndfsj.admin.dao.ibatis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hndfsj.admin.dao.IResourceDao;
import com.hndfsj.admin.domain.Resource;
import com.hndfsj.framework.base.dao.BaseDaoImpl;

/**
 * 资源Dao实现类
 * 
 * @author ibm
 * @date May 18, 2010
 */
@Repository("resourceDao")
public class ResourceDaoImpl extends BaseDaoImpl<Resource, String> implements IResourceDao {

	public ResourceDaoImpl() {
		super(Resource.class);
	}

	@Override
	public List<Resource> getResourcesForSecurity() {
		return queryList("getResourcesForSecurity");
	}

	@Override
	public List<Resource> getResourceByUserId(String userId) {
		return queryList("getResourceByUserId", userId);
	}

	@Override
	public List<Resource> getResourceByRoleId(String roleId) {
		return queryList("getResourceByRoleId", roleId);
	}
	@Override
	public List<String> findAllResourceUrl() {
		List<Resource> resource = queryList("findAllResourceUrl");
		List<String> list = new ArrayList<String>(resource.size());
		for (Resource resource2 : resource) {
			list.add(resource2.getUrl());
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.hndfsj.admin.dao.IResourceDao#getResourceIdAndRoleId(java.lang.String, java.lang.String)
	 */
	@Override
	public Boolean getResourceIdAndRoleId(String resouceId, String roleId) {
		Map<String,String> map=new HashMap<String,String>();
		map.put("resourceId", resouceId);
		map.put("roleId", roleId);
		//2013-7-15下午03:39:45
		Integer count=getCountForM("Resource.getResourceIdAndRoleId",map);
		if(count>0){
			return true;
		}
		return false;
	}
}
