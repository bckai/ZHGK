package com.hndfsj.admin.service.security;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.hndfsj.admin.domain.Resource;
import com.hndfsj.admin.domain.Role;
import com.hndfsj.admin.domain.User;
import com.hndfsj.admin.service.IResourceService;
import com.hndfsj.admin.service.IUserService;
import com.hndfsj.framework.exceptions.RWAdminServiceImplException;
import com.hndfsj.framework.security.bean.RWGrantedAuthority;
import com.hndfsj.framework.security.bean.RWGrantedGroup;
import com.hndfsj.framework.security.bean.RWResourceDetail;
import com.hndfsj.framework.security.bean.RWUserDetails;
import com.hndfsj.framework.security.bean.impl.RWGrantedAuthorityImpl;
import com.hndfsj.framework.security.bean.impl.RWResourceDetailsImpl;
import com.hndfsj.framework.security.bean.impl.RWUserDetailsImpl;
import com.hndfsj.framework.security.manager.EhCacheResourcCache;
import com.hndfsj.framework.security.manager.RWAdminSecurityService;

public class RWAdminSecurityServiceImpl implements RWAdminSecurityService {

	@javax.annotation.Resource
	private IResourceService resourceService;
	@javax.annotation.Resource
	private IUserService userService;

	@Override
	public List<RWResourceDetail> getAllRWResourcDetail() throws RWAdminServiceImplException {
		List<RWResourceDetail> listRWResourceDetail = new ArrayList<RWResourceDetail>();
		// 得到所有的模块信息
		List<Resource> listResource = resourceService.getResourcesForSecurity();
		if(listResource != null && listResource.size() > 0){
			RWResourceDetail rwResourceDetail;
			for (Resource resource : listResource) { 
				String resourceCode = resource.getId();
				String resourceUrl = resource.getUrl();
				String resourceRESTMethod = resource.getMethod();
				String resourceType = EhCacheResourcCache.URL_RESOURCES;
				String resourceComponent = resource.getName();
				
				//==资源对应角色信息创建
				List<Role> listRole = resource.getRoles();
				RWGrantedAuthority[] authorities = new RWGrantedAuthority[0];
				if(listRole != null && listRole.size()>0){
					authorities = new RWGrantedAuthority[listRole.size()];
					for (int i = 0; i < listRole.size(); i++) {
						RWGrantedAuthority rwAuthorities;
						String roleName = listRole.get(i).getName();
						rwAuthorities = new RWGrantedAuthorityImpl(roleName);
						authorities[i]= rwAuthorities;
					}
				}

				//==资源对应用户信息创建(用户组暂不用)
				RWGrantedGroup[] groups = new RWGrantedGroup[0];
				List<User> listUser = resource.getUsers();
				String[] users = new String[0];
				if(listUser != null && listUser.size()>0){
					 users = new String[listUser.size()];
					for (int i = 0; i < listUser.size(); i++) {
						users[i] =listUser.get(i).getUsername();
					}
				}
				
				//==生成资源对象并加入列表
				rwResourceDetail = new  RWResourceDetailsImpl(resourceCode, resourceUrl, resourceRESTMethod, resourceType, resourceComponent,
						 authorities, groups, users);
				listRWResourceDetail.add(rwResourceDetail);
			}
		}

		return listRWResourceDetail;
	}

	@Override
	public RWUserDetails getUserDetailsByUserName(String username) {
		RWUserDetails rwUserDetails = null;
		User user = null;
		if(StringUtils.isNotBlank(username)){
			user = userService.getUserByUserName(username);
			if(user!=null){
				String password = user.getPassword();
				boolean enabled = false;
				if("1".equals(user.getIsValid())){
					enabled = true;
				}
				String realName = user.getRealname();
				Date lastLoginTime = user.getLoginTime();
				//根据用户Id查找对应的角色信息
				List<Role> listRole=user.getRoles();
				RWGrantedAuthority[] authorities = null;
				if(listRole != null && listRole.size()>0){
					authorities = new RWGrantedAuthority[listRole.size()];
					for (int i = 0; i < listRole.size(); i++) {
						RWGrantedAuthority rwAuthorities;
						String roleName = listRole.get(i).getName();
						rwAuthorities = new RWGrantedAuthorityImpl(roleName);
						authorities[i] = rwAuthorities;
					}
				}
				String userId = user.getId();
				rwUserDetails = new RWUserDetailsImpl(userId,username,  password,  enabled, realName,user.getUserType(), lastLoginTime, authorities);
			}
		}
		return rwUserDetails;
	}

	@Override
	public List<RWUserDetails> getAllRWUserDetail()
			throws RWAdminServiceImplException {
		List<RWUserDetails> rwUserDetailsList = new ArrayList<RWUserDetails>();
		//得到所有的用户信息
		List<User> userList = userService.findAll();
		if(userList != null && userList.size() > 0){
			for (User users : userList) {
				RWUserDetails rwUserDetails = null;
				User user = null;
				user = userService.getUserByUserName(users.getUsername());//得到某个用户的信息
				String password = user.getPassword();
				boolean enabled = false;
				if("1".equals(user.getIsValid())){
					enabled = true;
				}
				String realName = user.getRealname();
				String username = user.getUsername();
				Date lastLoginTime = user.getLoginTime();
				//根据用户Id查找对应的角色信息
				List<Role> listRole=user.getRoles();
				RWGrantedAuthority[] authorities = null;
				if(listRole != null && listRole.size()>0){
					authorities = new RWGrantedAuthority[listRole.size()];
					for (int i = 0; i < listRole.size(); i++) {
						RWGrantedAuthority rwAuthorities;
						String roleName = listRole.get(i).getName();
						rwAuthorities = new RWGrantedAuthorityImpl(roleName);
						authorities[i] = rwAuthorities;
					}
				}
				String userId = user.getId();
				rwUserDetails = new RWUserDetailsImpl(userId,username,  password,  enabled, realName, user.getUserType(), lastLoginTime, authorities);
				
				rwUserDetailsList.add(rwUserDetails);
			}
		}
		return rwUserDetailsList;
	}

	@Override
	public List<RWUserDetails> getUserDetailInCas() throws RWAdminServiceImplException {
		List<RWUserDetails> rwUserDetailsList = new ArrayList<RWUserDetails>();
		//得到所有的用户信息
		List<User> userList = userService.findAll();
		if(userList != null && userList.size() > 0){
			for (User users : userList) {
				RWUserDetails rwUserDetails = null;
				User user = null;
				user = userService.getUserByUserName(users.getUsername());//得到某个用户的信息
				String password = user.getPassword();
				boolean enabled = false;
				if("1".equals(user.getIsValid())){
					enabled = true;
				}
				String realName = user.getRealname();
				String username = user.getUsername();
				Date lastLoginTime = user.getLoginTime();
				//根据用户Id查找对应的角色信息
				List<Role> listRole=user.getRoles();
				RWGrantedAuthority[] authorities = null;
				if(listRole != null && listRole.size()>0){
					authorities = new RWGrantedAuthority[listRole.size()];
					for (int i = 0; i < listRole.size(); i++) {
						RWGrantedAuthority rwAuthorities;
						String roleName = listRole.get(i).getName();
						rwAuthorities = new RWGrantedAuthorityImpl(roleName);
						authorities[i] = rwAuthorities;
					}
				}
				String userId = user.getId();
				rwUserDetails = new RWUserDetailsImpl(userId,username,  password,  enabled, realName,user.getUserType(),  lastLoginTime, authorities);
				
				rwUserDetailsList.add(rwUserDetails);
			}
		}
		return rwUserDetailsList;
	}

	@Override
	public List<RWUserDetails> getUserDetailInLocal() throws RWAdminServiceImplException {
		List<RWUserDetails> rwUserDetailsList = new ArrayList<RWUserDetails>();
		//得到所有的用户信息
		List<User> userList = userService.findAll();
		if(userList != null && userList.size() > 0){
			for (User users : userList) {
				RWUserDetails rwUserDetails = null;
				User user = null;
				user = userService.getUserByUserName(users.getUsername());//得到某个用户的信息
				String password = user.getPassword();
				boolean enabled = false;
				if("1".equals(user.getIsValid())){
					enabled = true;
				}
				String realName = user.getRealname();
				String username = user.getUsername();
				Date lastLoginTime = user.getLoginTime();
				//根据用户Id查找对应的角色信息
				List<Role> listRole=user.getRoles();
				RWGrantedAuthority[] authorities = null;
				if(listRole != null && listRole.size()>0){
					authorities = new RWGrantedAuthority[listRole.size()];
					for (int i = 0; i < listRole.size(); i++) {
						RWGrantedAuthority rwAuthorities;
						String roleName = listRole.get(i).getName();
						rwAuthorities = new RWGrantedAuthorityImpl(roleName);
						authorities[i] = rwAuthorities;
					}
				}
				String userId = user.getId();
				rwUserDetails = new RWUserDetailsImpl(userId,username,  password,  enabled, realName,user.getUserType(),  lastLoginTime, authorities);
				rwUserDetailsList.add(rwUserDetails);
			}
		}
		return rwUserDetailsList;
	}
}
