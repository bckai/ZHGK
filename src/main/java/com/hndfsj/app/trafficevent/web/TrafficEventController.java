package com.hndfsj.app.trafficevent.web;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hndfsj.admin.service.IDictionaryService;
import com.hndfsj.app.trafficevent.domain.TrafficEvent;
import com.hndfsj.app.trafficevent.service.ITrafficEventCommentService;
import com.hndfsj.app.trafficevent.service.ITrafficEventService;
import com.hndfsj.framework.base.controller.BaseRestJSONController;
import com.hndfsj.framework.common.MReturnObject;
import com.hndfsj.framework.exceptions.ValidateParamException;
import com.hndfsj.framework.pager.PageRequest;
import com.hndfsj.framework.pager.SearchCondition;
import com.hndfsj.framework.utils.DateUtils;
import com.hndfsj.framework.utils.UUIDGenerator;
import com.hndfsj.framework.utils.properties.MessageUtils;

/**
 * 交通事件
 * 
 * @copyright {@link www.hndfsj.com}
 * @author BuChunKai<Auto generate>
 * @version 2017-10-17 16:53:03 modify by jiangtao
 * @see com.hndfsj.app.trafficevent.web.TrafficEvent
 */
@Controller
@RequestMapping(value = "/trafficEvent")
public class TrafficEventController extends BaseRestJSONController<TrafficEvent, java.lang.String> {

	static Logger log = LoggerFactory.getLogger(TrafficEventController.class);

	@Resource
	private ITrafficEventService trafficEventService;
	@Resource
	private IDictionaryService dictionaryService;
	@Autowired
	private ITrafficEventCommentService trafficEventCommentService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class,
				new CustomDateEditor(new SimpleDateFormat(DateUtils.DATETIME_SECOND_FORMAT), true));
	}

	/**
	 * 进入交通事件 列表
	 *
	 * @param request
	 * @param model
	 * @return
	 * @author zxj
	 * @version 2017-10-17 16:53:03
	 */
	public @ResponseBody MReturnObject list(HttpServletRequest request) throws Exception {
		PageRequest pageRequest = newPageRequest(request);
		pageRequest.addAndCondition(TrafficEvent.STATUS, SearchCondition.EQUAL, 1);
		return trafficEventService.findPageAll(pageRequest).toMReturnObject();

	}

	public @ResponseBody MReturnObject show(@PathVariable("id") java.lang.String id) {
		PageRequest pageRequest = new PageRequest();

		trafficEventService.findColumnsAll(pageRequest);
		return new MReturnObject(MReturnObject.SUCCESS, trafficEventService.getById(id));
	}

	/**
	 * 新增/修改save操作
	 *
	 * @param model
	 * @param trafficEvent
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author zxj
	 * @version 2017-10-17 16:53:03
	 */
	public @ResponseBody MReturnObject create(Model model, TrafficEvent trafficEvent, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (StringUtils.isBlank(trafficEvent.getId())) {// 新增
			trafficEvent.setId(UUIDGenerator.UUIDValue());
			try {
				trafficEventService.validateEntity(trafficEvent);
				trafficEvent.setStatus(true);
				trafficEvent.setCreateTime(new Date());
				trafficEvent.setModifyTime(new Date());
				trafficEventService.save(trafficEvent);
				return new MReturnObject(MReturnObject.SUCCESS, trafficEvent);
			} catch (ValidateParamException e) {
				return new MReturnObject(MReturnObject.ERROR, e.getMessage());
			} catch (Exception e) {
				log.error("", e);
			}
			return new MReturnObject(MReturnObject.ERROR, MessageUtils.ADD_FAIL);
		} else {// 修改
			try {
				TrafficEvent orig_trafficEvent = trafficEventService.getById(trafficEvent.getId());
				if (orig_trafficEvent != null) {
					trafficEventService.validateEntity(trafficEvent);
					trafficEvent.setTitle(orig_trafficEvent.getTitle());
					trafficEvent.setContent(orig_trafficEvent.getContent());
					trafficEvent.setStatus(false);
					trafficEvent.setCoordinate(orig_trafficEvent.getCoordinate());
					trafficEvent.setFounder(orig_trafficEvent.getFounder());
					trafficEvent.setFounderId(orig_trafficEvent.getFounderId());
					trafficEvent.setCreateTime(orig_trafficEvent.getCreateTime());
					trafficEvent.setModifyTime(new Date());
					trafficEventService.update(trafficEvent);
					return new MReturnObject(MReturnObject.SUCCESS, MessageUtils.EDIT_SUCCESS);
				}
			} catch (Exception e) {
				log.error("", e);
			}
			return new MReturnObject(MReturnObject.WARNING, MessageUtils.EDIT_WARING);
		}

	}

	// 删除交通事件，同时删除相关评论
	public @ResponseBody MReturnObject destroy(@PathVariable java.lang.String id) {
		// 执行删除
		try {
			trafficEventCommentService.deleteByEventid(id);
			trafficEventService.deleteById(id);
			return new MReturnObject(MReturnObject.SUCCESS, MessageUtils.DELETE_SUCCESS);
		} catch (Exception e) {

		}
		return new MReturnObject(MReturnObject.WARNING, MessageUtils.DELETE_WARNING);
	}

	/**
	 * 删除多条记录
	 *
	 * @param request
	 * @param model
	 * @return
	 * @author zxj
	 * @version 2017-10-17 16:53:03
	 */
	public @ResponseBody MReturnObject delMultiRecords(HttpServletRequest request, Model model) {
		String records = request.getParameter("records");
		System.out.println(records);
		String[] rs = records.split(",");
		int i = 0;
		for (String str : rs) {
			try {
				trafficEventService.deleteById(str);
			} catch (Exception e) {
				if (i > 0) {
					// return new MReturnObject(MReturnObject.ERROR,
					// MessageUtils.DELETE_ALL_WARNING);
				}
				// return new MReturnObject(MReturnObject.ERROR,
				// MessageUtils.DELETE_ALL_FAIL);
			}
			i++;
		}
		return new MReturnObject(MReturnObject.SUCCESS, MessageUtils.DELETE_ALL_SUCCESS);
	}
}
