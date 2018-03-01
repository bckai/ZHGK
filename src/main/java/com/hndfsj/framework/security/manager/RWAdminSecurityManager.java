/**
 *
 */
package com.hndfsj.framework.security.manager;

import java.util.List;

import com.hndfsj.framework.exceptions.RWAdminResourceException;
import com.hndfsj.framework.exceptions.RWAdminUserException;
import com.hndfsj.framework.objects.RWRequest;
import com.hndfsj.framework.security.bean.RWResourceDetail;
import com.hndfsj.framework.security.bean.RWUserDetails;


/**
 * 安全资源缓存管理器接口
 *
 * @author 王富强
 * @date 2009-11-19 上午11:08:44
 */
public interface RWAdminSecurityManager {

	/**
	 * 初始化安全资源到缓存
	 */
	void initResourceInCache();

	/**
	 * 从缓存获得所有的Url类型的ResourceDetail
	 * @return
	 */
	List<RWResourceDetail> getAllUrlResourceDetailFromCache();
	
	/**
	 * 获得用户
	 * @param userId
	 * @return
	 */
	RWUserDetails loadUserByUsername(String userId);
	
	/**
	 * 获得在CasServer验证的用户
	 * @param userId
	 * @return
	 */
	RWUserDetails loadUserInCasByUsername(String userId);
	
	/**
	 * 获得需要在本地验证的用户
	 * @param userId
	 * @return
	 */
	RWUserDetails loadUserInLocalByUsername(String userId);
    /**
	 * 校验登陆用户信息
	 * @param username 用户名
	 * @param password 密码
	 * @return
	 * @throws RWAdminUserException 
	 */
	RWUserDetails authUser(String username,String password) throws RWAdminUserException ;
	
	/**
	 * @param rwRequest
	 * @return
	 * @throws RWAdminResourceException
	 * @throws RWAdminUserException
	 */
	boolean authRESTRequest(RWRequest rwRequest,RWUserDetails currentUserDetails) throws RWAdminResourceException, RWAdminUserException;
	public RWAdminSecurityService getSecurityService();
}
