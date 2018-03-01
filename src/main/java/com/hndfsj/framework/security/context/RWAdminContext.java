/**
 * hndfsjSoft webMail project
 */

package com.hndfsj.framework.security.context;

import com.hndfsj.framework.security.bean.RWUserDetails;

/**
 * 请在此加入你的功能说明
 *
 * @author 王富强
 * @date 2009-11-19 下午04:00:10
 */
public interface RWAdminContext {

	/**
	 * @return
	 */
	RWUserDetails getCurrentUser();
	/**
	 * @param userDetails
	 */
	void setCurrentUser(RWUserDetails userDetails);
}
