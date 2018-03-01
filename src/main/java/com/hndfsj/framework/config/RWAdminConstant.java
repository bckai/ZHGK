/**
 * hndfsjSoft webMail project
 */

package com.hndfsj.framework.config;

/**
 * 常量
 *
 * @author 王富强
 * @date 2009-9-3 下午03:09:10
 */
public class RWAdminConstant {

	
	/**
	 * 本地资源文件名
	 */
	public static final String CONFIG_APPPROPS_NAME = "application";

	//资源文件key用的常量
	/**
	 * app资源文件中存的contextholder的策略实现类名字
	 */
	public static final String RWADMIN_CONTEXT_HOLDER_STRATEGY = "rwadmin_context_holder_strategy";


	/**
	 * session里面存放的验证码常量值
	 */
	public static final String SESSION_VALIDATECODE = "validateCode";
	
	
	//session中存的key
	/**
	 * 在组件的session中存的关联的Admin组件的session_id
	 */
	public static final String ADMIN_SESSION_ID_IN_COMPONENT = "admin_session_id_in_component";
	
	/**
	 * 在Admin的session中存的关联的多个组件的session_ids
	 */
	public static final String COMPONENT_SESSION_IDS_IN_ADMIN = "component_session_ids_in_admin";
	/**
	 * 
	 */
	public static final String SESSION_PERMISSION_URLS = "session_permission_urls";
	
	/**
	 * 在seeion中存的用户信息
	 */
	public static final String USERDETAIL_IN_SESSION = "userdetail_in_session";
	
	/**
	 * session中存的securityContext
	 */
	public static final String RW_ADMIN_CONTEXT_IN_SESSION = "rw_admin_context_in_session";

	//
	public static final String RW_ADMIN_ERRORMSG_IN_REQUST = "rw_admin_errormsg_in_requst";
	
	
	/**
	 * 超级管理员名字
	 */
	public static final String SUPER_ADMIN_NAME = "super_admin_name";
	
	/**
	 * 2012年12月29日
	 * Mr.Hao
	 * 当配置文件中的super_admin_name值为空时，从这里获取
	 */
	public static final String RW_SUPUER_ADMIN_NAME_NULL_VALUE = "admin";

	/**
	 * 新增用户的默认密码
	 */
	public static final String DEFAULT_PASSWORD = "default_password";
	
	/**
	 * 2012年12月29日
	 * Mr.Hao
	 * 当配置文件中的default_password值为空时，从这里获取
	 */
	public static final String RW_DEFAULT_PWD_NULL_VALUE = "111111";

	public static final String IS_CACHE_USERINFO = "is_cache_userinfo";
	public static final String SMS_VALIDATE_CODE = "sms_validate_code";
	public static final String SMS_MOBILE_VALIDATE_CODE = "sms_mobile_validate_code";
	public static final String SMS_MASSAGE_VALIDATE_CODE = "sms_massage_validate_code";
	
	
}
