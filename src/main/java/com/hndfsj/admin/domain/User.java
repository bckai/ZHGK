
package com.hndfsj.admin.domain;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.hndfsj.framework.utils.DateUtils;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link www.hndfsj.com}
 * @author ZhengXingJian<Auto generate>
 * @version 2016-10-21 15:14:57
 * @see com.hndfsj.admin.domain.User
 */
public class User implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	// date formats
	public static final String ID = "sys_user.id";
	public static final String USERNAME = "sys_user.username";
	public static final String PASSWORD = "sys_user.password";
	public static final String REALNAME = "sys_user.realname";
	public static final String SEX = "sys_user.sex";
	public static final String POST = "sys_user.post";
	public static final String MOBILE = "sys_user.mobile";
	public static final String PHONE = "sys_user.phone";
	public static final String EMAIL = "sys_user.email";
	public static final String ADDRESS = "sys_user.address";
	public static final String USER_TYPE = "sys_user.userType";
	public static final String IS_VALID = "sys_user.isValid";
	public static final String DEPT_ID = "sys_user.deptId";
	public static final String FORMAT_LOGIN_TIME = DateUtils.DATETIME_SECOND_FORMAT;
	public static final String LOGIN_TIME = "sys_user.loginTime";
	public static final String SIGN_PATH = "sys_user.signPath";
	public static final String SORT = "sys_user.sort";
 
	 // idID  UUID,
	 // username账号  必须唯一,
	 // password密码,
	 // realname姓名,
	 // sex性别  1:男,0:女,
	 // post职务,
	 // mobile手机号码,
	 // phone联系电话,
	 // email电子邮箱,
	 // address住址,
	 // userType用户类型  [0:超级管理员],
	 // isValid用户状态  1:可用,0:不可用,
	 // deptId所属部门  外键关联sys_dept表,
	 // loginTime登录时间,
	 // signPath签名图片路径,
	 // sortsort,

	// columns START
	/**
	 * ID UUID
	 */
	private java.lang.String id;
	/**
	 * 账号 必须唯一
	 */
	private java.lang.String username;
	/**
	 * 密码
	 */
	private java.lang.String password;
	/**
	 * 姓名
	 */
	private java.lang.String realname;
	/**
	 * 性别 1:男,0:女
	 */
	private java.lang.String sex;
	/**
	 * 职务
	 */
	private java.lang.String post;
	/**
	 * 手机号码
	 */
	private java.lang.String mobile;
	/**
	 * 联系电话
	 */
	private java.lang.String phone;
	/**
	 * 电子邮箱
	 */
	private java.lang.String email;
	/**
	 * 住址
	 */
	private java.lang.String address;
	/**
	 * 用户类型 [0:超级管理员],1:普通人员
	 */
	private java.lang.String userType;
	/**
	 * 用户状态 1:可用,0:不可用
	 */
	private java.lang.String isValid;
	/**
	 * 所属部门 外键关联sys_dept表
	 */
	private java.lang.String deptId;
	/**
	 * 登录时间
	 */
	private java.util.Date loginTime;
	/**
	 * 签名图片路径
	 */
	private java.lang.String signPath;
	/**
	 * 排序
	 */
	private java.lang.Integer sort;
	
	private Date createTime;
	// columns END
	/**
	 * 头像路径
	 */
	private java.lang.String headImg;
	/**
	 * deptName
	 */
	private java.lang.String deptName;
	private List<Role> roles;// 角色列表
	private List<UserNode> userNodes;// 角色列表

	private boolean deleted=false;
	// concstructor

	public User() {
	}

	public User(java.lang.String id) {
		this.id = id;
	}

	public User(String id, String username, String default_pwd, String realname, Object object, String string2,
			String string3) {
		setId(id);
		setRealname(realname);
		setPassword(default_pwd);
		setUsername(username);
		setIsValid("1");
		setSex("0");
	}

	// get and set
	public void setId(java.lang.String value) {
		this.id = value;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public java.lang.String getId() {
		return this.id;
	}

	public void setUsername(java.lang.String value) {
		this.username = value;
	}

	public java.lang.String getUsername() {
		return this.username;
	}

	public void setPassword(java.lang.String value) {
		this.password = value;
	}

	public java.lang.String getPassword() {
		return this.password;
	}

	public void setRealname(java.lang.String value) {
		this.realname = value;
	}

	public java.lang.String getRealname() {
		return this.realname;
	}

	public void setSex(java.lang.String value) {
		this.sex = value;
	}

	public java.lang.String getSex() {
		return this.sex;
	}

	public void setPost(java.lang.String value) {
		this.post = value;
	}

	public java.lang.String getPost() {
		return this.post;
	}

	public void setMobile(java.lang.String value) {
		this.mobile = value;
	}

	public java.lang.String getMobile() {
		return this.mobile;
	}

	public java.lang.Integer getSort() {
		return sort;
	}

	public void setSort(java.lang.Integer sort) {
		this.sort = sort;
	}

	public void setPhone(java.lang.String value) {
		this.phone = value;
	}

	public java.lang.String getPhone() {
		return this.phone;
	}

	public void setEmail(java.lang.String value) {
		this.email = value;
	}

	public java.lang.String getEmail() {
		return this.email;
	}

	public void setAddress(java.lang.String value) {
		this.address = value;
	}

	public java.lang.String getAddress() {
		return this.address;
	}

	public void setUserType(java.lang.String value) {
		this.userType = value;
	}

	public java.lang.String getUserType() {
		return this.userType;
	}

	public void setIsValid(java.lang.String value) {
		this.isValid = value;
	}

	public java.lang.String getIsValid() {
		return this.isValid;
	}

	public void setDeptId(java.lang.String value) {
		this.deptId = value;
	}

	public java.lang.String getDeptId() {
		return this.deptId;
	}

	public String getLoginTimeString() {
		return DateUtils.convertDate2String(FORMAT_LOGIN_TIME, getLoginTime());
	}

	public void setLoginTimeString(String value) throws ParseException {
		setLoginTime(DateUtils.convertString2Date(FORMAT_LOGIN_TIME, value));
	}

	public void setLoginTime(java.util.Date value) {
		this.loginTime = value;
	}

	public java.util.Date getLoginTime() {
		return this.loginTime;
	}

	public void setSignPath(java.lang.String value) {
		this.signPath = value;
	}

	public java.lang.String getSignPath() {
		return this.signPath;
	}

	public java.lang.String getDeptName() {
		return deptName;
	}

	public void setDeptName(java.lang.String deptName) {
		this.deptName = deptName;
	}

	public java.lang.String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(java.lang.String headImg) {
		this.headImg = headImg;
	}
	public java.lang.String getName() {
		if(realname==null)
			return this.username;
		return this.realname;
	}

	public List<UserNode> getUserNodes() {
		return userNodes;
	}

	public void setUserNodes(List<UserNode> userNodes) {
		this.userNodes = userNodes;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("Id", getId())
				.append("Username", getUsername()).append("Password", getPassword()).append("Realname", getRealname())
				.append("Sex", getSex()).append("Post", getPost()).append("Mobile", getMobile())
				.append("Phone", getPhone()).append("Email", getEmail()).append("Address", getAddress())
				.append("UserType", getUserType()).append("IsValid", getIsValid()).append("DeptId", getDeptId())
				.append("LoginTime", getLoginTime()).append("SignPath", getSignPath()).toString();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getId()).toHashCode();
	}

	public boolean equals(Object obj) {
		if (obj instanceof User == false)
			return false;
		if (this == obj)
			return true;
		User other = (User) obj;
		return new EqualsBuilder().append(getId(), other.getId()).isEquals();
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public void setDeleted(boolean deleted) {
		this.deleted=deleted;
	}

	public boolean isDeleted() {
		return deleted;
	}

}
