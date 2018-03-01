/**
 * hndfsjSoft webMail project
 */

package com.hndfsj.framework.security.bean;

import java.io.Serializable;

/**
 * 请在此加入你的功能说明
 *
 * @author 王富强
 * @param <T>
 * @date 2009-11-19 下午01:52:32
 */
@SuppressWarnings("unchecked")
public interface RWGrantedGroup extends Comparable ,Serializable {
	 /**
	  * 得到所属组
	 * @return
	 */
	String getGroup();
}
