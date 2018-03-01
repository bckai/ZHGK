/**
 * hndfsjSoft webMail project
 */

package com.hndfsj.framework.security.context;

import com.hndfsj.framework.exceptions.RWAdminContextException;


/**
 * 请在此加入你的功能说明
 *
 * @author 王富强
 * @date 2009-11-19 下午04:19:53
 */
public interface RWAdminContextHolderStrategy {
	/**
 	 * 将context设置为null
 	 */
 	void clearContext();
    
    /**
     * 返回的RWAdminContext不为null 至少是RWAdminContext的一个实例
     * @return
     */
    RWAdminContext getContext();

    /**
     * context 不能为null
     * @param context
     * @throws MailContextException
     */
    void setContext(RWAdminContext context) throws RWAdminContextException;
}
