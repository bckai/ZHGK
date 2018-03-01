/**
 * hndfsjSoft webMail project
 */

package com.hndfsj.framework.security.bean.impl;

import java.util.Date;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import com.hndfsj.framework.security.bean.RWGrantedAuthority;
import com.hndfsj.framework.security.bean.RWGrantedGroup;
import com.hndfsj.framework.security.bean.RWUserDetails;


/**
 * RWUserDetails 的实现Bean
 *
 * @author 王富强
 * @date 2009-11-19 上午11:34:54
 */
public class RWUserDetailsImpl implements RWUserDetails{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String password;
    private String username;
    private RWGrantedAuthority[] authorities;
    private RWGrantedGroup[] groups;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private String realName;
    private Date lastLoginTime;
    private String userType;
    private String userId;
    private String deptId;
   
    
    /**
     * 
     * 
     * @param userId
     * @param username 用户名
     * @param password 密码
     * @param enabled 是否可用
     * @param realName 用户姓名
     * @param userType 用户类型
     * @param lastLoginTime 上次登录时间
     * @param authorities 权限
     * @throws IllegalArgumentException
     */
    public RWUserDetailsImpl(String userId,String username, String password, boolean enabled,String realName,String userType , Date lastLoginTime,RWGrantedAuthority[] authorities)
    	 throws IllegalArgumentException {
             if (StringUtils.isBlank(username)|| StringUtils.isBlank(password)) {
                 throw new IllegalArgumentException("Cannot pass null or empty values to constructor");
             }
             this.userId = userId;
             this.username = username;
             this.password = password;
             this.enabled = enabled;
             this.realName = realName;
             this.accountNonExpired = true;
             this.credentialsNonExpired = true;
             this.accountNonLocked = true;
             this.lastLoginTime = lastLoginTime;
             this.userType = userType;
             setAuthorities(authorities);
    }    
    
   
    /**
     * @param username
     * @param password
     * @param enabled
     * @param realName
     * @param authorities
     * @param groups
     * @throws IllegalArgumentException
     */
    public RWUserDetailsImpl(String username, String password, boolean enabled,String realName,RWGrantedAuthority[] authorities,RWGrantedGroup[] groups)
    	 throws IllegalArgumentException {
             if (StringUtils.isBlank(username)|| StringUtils.isBlank(password)) {
                 throw new IllegalArgumentException("Cannot pass null or empty values to constructor");
             }
             this.username = username;
             this.password = password;
             this.enabled = enabled;
             this.realName = realName;
             this.accountNonExpired = true;
             this.credentialsNonExpired = true;
             this.accountNonLocked = true;
             setAuthorities(authorities);
             setGroups(groups);
    }
    
    //构造Constructors

    /**
     * @param username 用户名
     * @param password 密码
     * @param enabled 是否可用
     * @param realName 用户姓名
     * @param accountNonExpired 账户是否过期 true:  没有过期 false: 过期
     * @param accountNonLocked 账户是否锁定
     * @param authorities 权限
     * @param groups 用户组
     * @throws IllegalArgumentException
     */
    public RWUserDetailsImpl(String username, String password, boolean enabled, String realName,boolean accountNonExpired,
           boolean accountNonLocked, RWGrantedAuthority[] authorities,RWGrantedGroup[] groups)
            throws IllegalArgumentException {
            if (StringUtils.isBlank(username)|| StringUtils.isBlank(password)) {
                throw new IllegalArgumentException("Cannot pass null or empty values to constructor");
            }
            this.username = username;
            this.password = password;
            this.enabled = enabled;
            this.accountNonExpired = accountNonExpired;
            this.credentialsNonExpired = true;
            this.accountNonLocked = accountNonLocked;
            this.realName = realName;
            setAuthorities(authorities);
            setGroups(groups);
        }
    
    //set and get
    public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public void setPwd(String pass) {
		this.password = pass;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public RWGrantedAuthority[] getAuthorities() {
		return authorities;
	}
	@SuppressWarnings("unchecked")
	public void setAuthorities(RWGrantedAuthority[] authorities) {
//		Assert.notNull(authorities, "Cannot pass a null GrantedAuthority array");
		if(authorities!=null){
			SortedSet sorter = new TreeSet();
			for (int i = 0; i < authorities.length; i++) {
				Assert.notNull(authorities[i],
						"Granted authority element " + i + " is null - GrantedAuthority[] cannot contain any null elements");
				sorter.add(authorities[i]);
			}
			this.authorities = (RWGrantedAuthority[]) sorter.toArray(new RWGrantedAuthority[sorter.size()]);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void setGroups(RWGrantedGroup[] groups) {
		Assert.notNull(groups, "Cannot pass a null GrantedAuthority array");
        SortedSet sorter = new TreeSet();
        for (int i = 0; i < groups.length; i++) {
            Assert.notNull(groups[i],
                "Granted authority element " + i + " is null - GrantedAuthority[] cannot contain any null elements");
            sorter.add(groups[i]);
        }
        this.groups = (RWGrantedGroup[]) sorter.toArray(new RWGrantedGroup[sorter.size()]);
	}
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}
	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}
	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}
	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public RWGrantedGroup[] getGroups() {
		return groups;
	}

	@Override
	public String getUserId() {
		return userId;
	}

	@Override
	public String getUserType() {
		return userType;
	}


	public String getDeptId() {
		return deptId;
	}


	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
}
