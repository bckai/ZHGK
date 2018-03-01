/* Copyright 2004, 2005, 2006 Acegi Technology Pty Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hndfsj.framework.security.bean;
import java.io.Serializable;
import java.util.Date;

import com.hndfsj.framework.security.spring.UserDetails;



/**
 * 扩展的UserDetails(Spring Security)的接口
 *
 * @author 王富强
 * @date 2009-11-19 上午11:27:33
 */
public interface RWUserDetails extends UserDetails,Serializable {
/**
 * 改变了父接口的方法的返回类型
 */
    RWGrantedAuthority[] getAuthorities();
    
    RWGrantedGroup[] getGroups();
    
	String getRealName();
	
	String getUserId();

	void setRealName(String realName);
	
	Date getLastLoginTime();

	void setLastLoginTime(Date lastLoginTime);
	
	String getUserType();
	String getDeptId();
}
