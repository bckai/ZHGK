package com.hndfsj.app.trafficevent.web;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hndfsj.app.trafficevent.domain.TrafficEventComment;
import com.hndfsj.app.trafficevent.service.ITrafficEventCommentService;
import com.hndfsj.framework.base.controller.BaseRestJSONController;
import com.hndfsj.framework.common.MReturnObject;
import com.hndfsj.framework.exceptions.ValidateParamException;
import com.hndfsj.framework.pager.PageRequest;
import com.hndfsj.framework.utils.DateUtils;
import com.hndfsj.framework.utils.UUIDGenerator;
import com.hndfsj.framework.utils.properties.MessageUtils;

/**
 * 交通事件评论
 * 
 * @copyright {@link www.hndfsj.com}
 * @author BuChunKai<Auto generate>
 * @version 2017-10-17 16:53:19
 * @see com.hndfsj.app.trafficevent.web.TrafficEventComment
 */
@Controller
@RequestMapping(value = "/trafficEventComment")
public class TrafficEventCommentController extends BaseRestJSONController<TrafficEventComment, java.lang.String> {

	static Logger log = LoggerFactory.getLogger(TrafficEventCommentController.class);

	@Resource
	private ITrafficEventCommentService trafficEventCommentService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class,
				new CustomDateEditor(new SimpleDateFormat(DateUtils.DATETIME_SECOND_FORMAT), true));
	}

	/**
	 * 进入交通事件评论 列表
	 *
	 * @param request
	 * @param model
	 * @return
	 * @author zxj
	 * @version 2017-10-17 16:53:19
	 */
	public @ResponseBody MReturnObject list(HttpServletRequest request) throws Exception {
		PageRequest pageRequest = newPageRequest(request);
		return trafficEventCommentService.findPageAll(pageRequest).toMReturnObject();

	}

	public @ResponseBody MReturnObject show(@PathVariable("id") java.lang.String id) {
		return new MReturnObject(MReturnObject.SUCCESS, trafficEventCommentService.getById(id));
	}

	/**
	 * 新增/修改save操作
	 *
	 * @param model
	 * @param trafficEventComment
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author zxj
	 * @version 2017-10-17 16:53:19
	 */
	public @ResponseBody MReturnObject create(Model model, TrafficEventComment trafficEventComment,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (StringUtils.isBlank(trafficEventComment.getId())) {// 新增
			trafficEventComment.setId(UUIDGenerator.UUIDValue());
			try {
				trafficEventCommentService.validateEntity(trafficEventComment);

				trafficEventComment.setCreateTime(new Date());
				trafficEventComment.setModifyTime(new Date());
				trafficEventCommentService.save(trafficEventComment);
				return new MReturnObject(MReturnObject.SUCCESS, trafficEventComment);
			} catch (ValidateParamException e) {
				return new MReturnObject(MReturnObject.ERROR, e.getMessage());
			} catch (Exception e) {
				log.error("", e);
			}
			return new MReturnObject(MReturnObject.ERROR, MessageUtils.ADD_FAIL);
		} else {// 修改
			try {
				TrafficEventComment orig_trafficEventComment = trafficEventCommentService
						.getById(trafficEventComment.getId());
				if (orig_trafficEventComment != null) {
					trafficEventCommentService.validateEntity(trafficEventComment);
					trafficEventComment.setId(orig_trafficEventComment.getId());
					trafficEventComment.setEventId(orig_trafficEventComment.getEventId());
					trafficEventComment.setFounder(orig_trafficEventComment.getFounder());
					trafficEventComment.setFounderId(orig_trafficEventComment.getFounderId());
					trafficEventComment.setCreateTime(orig_trafficEventComment.getCreateTime());
					trafficEventComment.setModifyTime(new Date());
					trafficEventCommentService.update(trafficEventComment);
					return new MReturnObject(MReturnObject.SUCCESS, MessageUtils.EDIT_SUCCESS);
				}
			} catch (Exception e) {
				log.error("", e);
			}
			return new MReturnObject(MReturnObject.WARNING, MessageUtils.EDIT_WARING);
		}

	}

	// 删除
	public @ResponseBody MReturnObject destroy(@PathVariable java.lang.String id) {
		// 执行删除
		try {
			trafficEventCommentService.deleteById(id);
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
	 * @version 2017-10-17 16:53:19
	 */
	public @ResponseBody MReturnObject delMultiRecords(HttpServletRequest request, Model model) {
		String records = request.getParameter("records");
		System.out.println(records);
		String[] rs = records.split(",");
		int i = 0;
		for (String str : rs) {
			try {
				trafficEventCommentService.deleteById(str);
			} catch (Exception e) {
				if (i > 0) {
					return new MReturnObject(MReturnObject.ERROR, MessageUtils.DELETE_ALL_WARNING);
				}
				return new MReturnObject(MReturnObject.ERROR, MessageUtils.DELETE_ALL_FAIL);
			}
			i++;
		}
		return new MReturnObject(MReturnObject.SUCCESS, MessageUtils.DELETE_ALL_SUCCESS);
	}

}
