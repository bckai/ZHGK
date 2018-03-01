/**
 * hndfsjSoft webMail project
 */

package com.hndfsj.framework.security.manager;

import java.util.List;

import com.hndfsj.framework.exceptions.RWAdminServiceImplException;
import com.hndfsj.framework.security.bean.RWResourceDetail;
import com.hndfsj.framework.security.bean.RWUserDetails;

/**
 * 请在此加入你的功能说明
 *
 * @author 王富强
 * @date 2009-11-25 上午11:55:09
 */
/**
 * 
 * 
 * @author ibm
 *
 */
public interface RWAdminSecurityService {
	/**
	 * 获得全部的RWResourcDetail
	 * @return
	 */
	List<RWResourceDetail> getAllRWResourcDetail() throws RWAdminServiceImplException;

	/**
	 * 获得全部的RWUserDetails
	 * @return
	 * @throws RWAdminServiceImplException
	 */
	List<RWUserDetails> getAllRWUserDetail() throws RWAdminServiceImplException;
	
	
	
	/**
	* 获得需要在本地验证用户的用户列表
	*
	* @return
	* @throws RWAdminServiceImplException
	* @author wfq
	*/
	List<RWUserDetails> getUserDetailInLocal() throws RWAdminServiceImplException;
	/**
	* 获得需要在CasServer验证用户的用户列表
	*
	* @return
	* @throws RWAdminServiceImplException
	* @author wfq
	*/
	List<RWUserDetails> getUserDetailInCas() throws RWAdminServiceImplException;
	/**
	 * 得到一个用户信息
	 * @param userName
	 * @return
	 */
	RWUserDetails getUserDetailsByUserName(String userName);
}
