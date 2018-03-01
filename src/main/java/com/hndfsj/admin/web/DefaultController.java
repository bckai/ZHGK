package com.hndfsj.admin.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.alibaba.fastjson.JSON;
import com.hndfsj.admin.domain.JsonMod;
import com.hndfsj.admin.domain.Module;
import com.hndfsj.admin.domain.Role;
import com.hndfsj.admin.domain.User;
import com.hndfsj.admin.service.IDeptService;
import com.hndfsj.admin.service.IDictionaryService;
import com.hndfsj.admin.service.IModuleService;
import com.hndfsj.admin.service.IRoleService;
import com.hndfsj.admin.service.IUserService;
import com.hndfsj.framework.common.MReturnObject;
import com.hndfsj.framework.config.EnumType.UserType;
import com.hndfsj.framework.config.RWAdminConstant;
import com.hndfsj.framework.pager.PageModel;
import com.hndfsj.framework.pager.PageRequest;
import com.hndfsj.framework.security.bean.RWUserDetails;
import com.hndfsj.framework.security.context.RWAdminContext;
import com.hndfsj.framework.security.context.RWAdminContextHolder;

/**
 * 默认的控制器类
 * 
 * @author ibm
 * @date May 18, 2010
 */
@Controller
@RequestMapping(value = "/default")
@SessionAttributes(RWAdminConstant.RW_ADMIN_CONTEXT_IN_SESSION)
public class DefaultController {

	static Logger log=LoggerFactory.getLogger(DefaultController.class);
//	private static ExecutorService es=Executors.newFixedThreadPool(10);
	@Resource
	IUserService userService;
	@Resource
	private IModuleService moduleService;
	@Resource
	private IRoleService roleService;
	@Resource
	IDictionaryService dictionaryService;
	@Resource
	IDeptService deptService;  
	/**
	 * 主框架页面,包括top、center和bottom
	 * 
	 * @return
	 */
	@RequestMapping(value = "/welcome")
	public String welcome(
			@ModelAttribute(RWAdminConstant.RW_ADMIN_CONTEXT_IN_SESSION) RWAdminContext context,
			Model model) {
		// bryxxService.insertDept();
		return "common/welcome";
	}

	@RequestMapping(value = "/home")
	public String home(Model model) {
		return "common/home";
	}

	/**
	 * 登录验证
	 * 
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody MReturnObject login(
			@ModelAttribute(RWAdminConstant.RW_ADMIN_CONTEXT_IN_SESSION) RWAdminContext context,
			HttpServletResponse response,HttpServletRequest request) {
		 User user = userService.getRoleByUserId(context.getCurrentUser().getUserId());
		Set<Url> list = new HashSet<Url>();
		if(user!=null){
			userService.updateUserLoginTime(user.getId(), new Date());
			MReturnObject object = new MReturnObject(MReturnObject.SUCCESS,user);
			if (UserType.ADMIN.equals(UserType.getUserType(user.getUserType()))) {
				list.add(new Url("超级管理员", "/rest/default/welcome"));
				object.setRespList(list);
			} else{
				loadUser(user);
				object.setRespList(loadAuth(user));
			}
			return object;
		}else{
			return new MReturnObject(MReturnObject.ERROR, "没有权限登录");
		}
	}
	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public @ResponseBody MReturnObject error(HttpServletResponse response,HttpServletRequest request) {
		return new MReturnObject(MReturnObject.TIMEOUT, "请重新登录！");
	}
	
	@RequestMapping(value = "/login/check", method = RequestMethod.GET)
	public @ResponseBody MReturnObject check(HttpServletResponse response,HttpServletRequest request) {
		// 获取当前用户信息
		RWAdminContext context = (RWAdminContext) request.getSession().getAttribute(
				RWAdminConstant.RW_ADMIN_CONTEXT_IN_SESSION);
		if (context == null) {
			return new MReturnObject(MReturnObject.TIMEOUT, "请重新登录！");
		}
		User user=userService.getRoleByUserId(context.getCurrentUser().getUserId());
		MReturnObject object = new MReturnObject(MReturnObject.SUCCESS,user);
		loadUser(user);
		object.setRespList(loadAuth(user));
		return new MReturnObject(MReturnObject.SUCCESS, userService.getRoleByUserId(context.getCurrentUser().getUserId()));
	}
	
	public static List<JsonMod> loadAuth(User user) {
		List<JsonMod> mods=null;
		try {
			PathMatchingResourcePatternResolver patternResolver=new PathMatchingResourcePatternResolver();
			org.springframework.core.io.Resource resource =patternResolver.getResource("classpath:auth.json");
			mods = JSON.parseArray(IOUtils.toString(resource.getInputStream(),"utf-8"), JsonMod.class);
			handle(mods,user.getRoles());
		} catch (IOException e) {
		}
		return mods;
	}
	

	private static void handle(List<JsonMod> mods, List<Role> roles) {
		for (JsonMod mod : mods) {
			if(isExist((List<String>)mod.getShow(),roles)){
				mod.setShow(true);
			}else{
				mod.setShow(false);
			}
			if(isExist((List<String>)mod.getOprea(),roles)){
				mod.setOprea(true);
			}else{
				mod.setOprea(false);
			}
			if(mod.getRole()!=null){
				handle(mod.getRole(), roles);
			}
		}
		
	}

	private static boolean isExist(List<String> show, List<Role> roles) {
		if(show!=null){
			
			for (Role role : roles) {
				for (String roleId : show) {
					if(roleId.equals(role.getId())){
						return true;
					}
				}
			}
		}
		return false;
	}

	public static void loadUser(User member) {
		/*if(member!=null){
			IValuesService  valuesService=SpringContextHolder.getBean(IValuesService.class);
			ILineNodeService  lineNodeService=SpringContextHolder.getBean(ILineNodeService.class);
			Values values = valuesService.getById(member.getId());
			member.setPassword(null);
			member.setIsValid(null);
			member.setLoginTime(null);
			member.setUserType(null);
			LineNode lineNode=lineNodeService.getById(member.getDeptId());
			if(lineNode!=null){
				member.setDeptName(lineNode.getName());
			}
			if(values!=null){
				member.setSignPath(values.getValue());
			}
		}else{
			throw new ValidateParamException("用户不存在！");
		}*/
	}
	

	

	/**
	 * 登出
	 * 
	 * @return
	 */
	@RequestMapping(value = "/logout")
	public String logout(HttpServletResponse response) {
		return "common/login";
	}
	
	@RequestMapping(value = "/quit")
	public @ResponseBody MReturnObject logout(HttpServletRequest request) {
		RWAdminContextHolder.cleanContext();
		request.getSession().removeAttribute(RWAdminConstant.RW_ADMIN_CONTEXT_IN_SESSION);
		System.out.println(request.getSession().getAttribute(RWAdminConstant.RW_ADMIN_CONTEXT_IN_SESSION));
		request.getSession().invalidate();
		request.getSession();
		return new MReturnObject(MReturnObject.SUCCESS);
	}

	/**
	 * 新登录
	 * 
	 * @return
	 */
	@RequestMapping(value = "/newLogin")
	public String newLogin() {
		return "common/login";
	}

	/**
	 * 设置某些常用数据
	 * 
	 * @param model
	 * @author Mr.Hao
	 * @version 2013-1-4 下午02:59:20
	 */
	@ModelAttribute()
	public void initModelValue(Model model) {
		Date date = new Date();
		model.addAttribute("year", date.getYear() + 1900);
	}

	/**
	 * 框架页（顶部）
	 * 
	 * @param context
	 * @param model
	 * @return
	 * @author Mr.Hao
	 * @version 2013-1-4 下午02:54:42
	 */
	@RequestMapping(params = "m=top")
	public String getTop(
			@ModelAttribute(RWAdminConstant.RW_ADMIN_CONTEXT_IN_SESSION) RWAdminContext context,
			Model model) {

		RWUserDetails account = context.getCurrentUser();
		model.addAttribute("account", account);
		// List<Module> list =
		// moduleService.getModuleByUser(context.getCurrentUser());
		List<Module> list = moduleService.getRootModuleByUser(account);
		Collections.sort(list, new Comparator<Module>() {
			@Override
			public int compare(Module o1, Module o2) {
				return o1.getSortNo() - o2.getSortNo();
			}
		});
		model.addAttribute("moduleList", list);
		// 添加当前系统时间、工作日
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		Date date = new Date();
		// 工作日
		int n = date.getDay();
		model.addAttribute("date", sdf.format(date));
		String week = null;
		switch (n) {
		case 0: {
			week = "星期日";
			break;
		}
		case 1: {
			week = "星期一";
			break;
		}
		case 2: {
			week = "星期二";
			break;
		}
		case 3: {
			week = "星期三";
			break;
		}
		case 4: {
			week = "星期四";
			break;
		}
		case 5: {
			week = "星期五";
			break;
		}
		case 6: {
			week = "星期六";
			break;
		}
		}
		model.addAttribute("week", week);
		return "common/frame/top";
	}

	/**
	 * 框架页（左侧）
	 * 
	 * @param model
	 * @param request
	 * @param context
	 * @return
	 * @author Mr.Hao
	 * @version 2013-1-4 下午02:54:03
	 */
	@RequestMapping(params = "m=left")
	public String getLeft(Model model, HttpServletRequest request,
			@ModelAttribute(RWAdminConstant.RW_ADMIN_CONTEXT_IN_SESSION) RWAdminContext context) {
		RWUserDetails user = context.getCurrentUser();
		String superMid = request.getParameter("superMid");
		if (StringUtils.isBlank(superMid)) {
			List<Module> mdule = moduleService.getRootModuleByUser(user);
			if (mdule.size() > 0) {
				superMid = mdule.get(0).getId();
			}
		}
		Module m1 = moduleService.getById(superMid);
		model.addAttribute("m1", m1);

		List<Module> list = moduleService.getModuleByUserAndParent(user,
				request.getParameter("superMid"));
		Collections.sort(list, new Comparator<Module>() {
			@Override
			public int compare(Module o1, Module o2) {
				return o1.getSortNo() - o2.getSortNo();
			}
		});

		model.addAttribute("moduleList", list);

		return "common/frame/left";
	}

	/**
	 * 框架页（左侧栏缩进条）
	 * 
	 * @param model
	 * @return
	 * @author Mr.Hao
	 * @version 2013-1-4 下午02:54:52
	 */
	@RequestMapping(params = "m=side")
	public String getSide(Model model) {
		return "common/frame/side";
	}

	/**
	 * 框架页（右侧）
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @author Mr.Hao
	 * @version 2013-1-4 下午02:54:29
	 */
	@RequestMapping(params = "m=center")
	public String getCenter(HttpServletRequest request, HttpServletResponse response, Model model) {
		PageRequest pageRequest = new PageRequest();
		PageModel pageModel = roleService.findPageAll(pageRequest);
		model.addAttribute(pageModel);
		model.addAttribute("page", pageModel);
		return "admin/role/roleList";
	}

	/**
	 * 框架页（底部）
	 * 
	 * @param session
	 * @param model
	 * @return
	 * @author Mr.Hao
	 * @version 2013-1-4 下午02:54:55
	 */
	@RequestMapping(params = "m=bottom")
	public String getBottom(HttpSession session, Model model) {
		return "common/frame/bottom";
	}

	/**
	 * 菜单
	 * 
	 * @param session
	 * @param model
	 * @return
	 * @author Mr.Hao
	 * @version 2013-1-4 下午02:54:55
	 */
	@RequestMapping(params = "m=mainMenu")
	public String getMainMenu(HttpSession session, Model model) {
		RWUserDetails account = ((RWAdminContext) session
				.getAttribute(RWAdminConstant.RW_ADMIN_CONTEXT_IN_SESSION)).getCurrentUser();
		model.addAttribute("account", account);
		List<Module> list = moduleService.getRootModuleByUser(account);
		Collections.sort(list, new Comparator<Module>() {
			@Override
			public int compare(Module o1, Module o2) {
				return o1.getSortNo() - o2.getSortNo();
			}
		});
		for (Module module : list) {
			List<Module> listchild = moduleService
					.getModuleByUserAndParent(account, module.getId());
			Collections.sort(listchild, new Comparator<Module>() {
				@Override
				public int compare(Module o1, Module o2) {
					return o1.getSortNo() - o2.getSortNo();
				}
			});
			List children = listchild;
			module.setChildren(children);
		}
		model.addAttribute("moduleList", list);
		return "common/frame/mainMenu";
	}

}

class Url {
	String name;
	String url;

	public Url(String name, String url) {
		setName(name);
		setUrl(url);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Url) {
			Url url = (Url) obj;
			if (url.name == this.name && url.url == this.url) {
				return true;
			}
		}
		return super.equals(obj);
	}
}
