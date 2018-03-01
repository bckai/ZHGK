package com.hndfsj.app.device.web;

import java.util.*;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.hndfsj.framework.exceptions.ValidateParamException;
import com.hndfsj.framework.pager.PageRequest;
import com.hndfsj.framework.pager.SearchCondition;

import org.apache.commons.lang.StringUtils;

import com.hndfsj.framework.utils.DateUtils;
import com.hndfsj.framework.utils.properties.MessageUtils;
import com.hndfsj.framework.base.controller.BaseRestJSONController;
import com.hndfsj.framework.common.MReturnObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hndfsj.app.common.enums.ZHGKEnum.DVC_FAC_NAME;
import com.hndfsj.app.device.domain.CmsStruct;
import com.hndfsj.app.device.domain.Device;
import com.hndfsj.app.device.domain.DeviceConfig;
import com.hndfsj.app.device.domain.DsStruct;
import com.hndfsj.app.device.service.ICmsStructService;
import com.hndfsj.app.device.service.IDeviceConfigService;
import com.hndfsj.app.device.service.IDeviceService;
import com.hndfsj.app.device.service.IDsStructService;
import com.hndfsj.app.device.service.IVdStructService;
import com.hndfsj.app.device.service.IWsStructService;
import com.hndfsj.driver.constant.DvcTypeConstant;

/**
 * TODO
 * 
 * @copyright {@link www.hndfsj.com}
 * @author BuChunKai<Auto generate>
 * @version 2017-09-05 11:46:13
 * @see com.hndfsj.app.device.web.Device
 */
@Controller
@RequestMapping(value = "/device")
public class DeviceController extends BaseRestJSONController<Device, java.lang.String> {

	static Logger log = LoggerFactory.getLogger(DeviceController.class);

	@Resource
	private IDeviceService deviceService;
	@Resource
	private IDeviceConfigService deviceConfigService;

	@Resource
	private IDsStructService dsStructService;

	@Resource
	private IWsStructService wsStructService;

	@Resource
	private IVdStructService vdStructService;

	@Resource
	private ICmsStructService cmsStructService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class,
				new CustomDateEditor(new SimpleDateFormat(DateUtils.DATETIME_SECOND_FORMAT), true));
	}

	/**
	 * 进入device 列表
	 *
	 * @param request
	 * @param model
	 * @return
	 * @author zxj
	 * @version 2017-09-05 11:46:13
	 */
	@RequestMapping(value = "/{dvcType}/list")
	public @ResponseBody MReturnObject list(HttpServletRequest request, @PathVariable String dvcType) throws Exception {
		PageRequest pageRequest = newPageRequest(request);
		pageRequest.addAndCondition(
				"substring(" + Device.ID + "," + 4 + "," + 2 + ")='" + DvcTypeConstant.getDvcType(dvcType) + "'",
				SearchCondition.SUB_STRING, null);
		pageRequest.addAndCondition(Device.IS_DELETED, SearchCondition.EQUAL, 0);
		List<Device> devices = new ArrayList<>();
		List<Device> devicesAll = deviceService.findAll(pageRequest);
		for (Device device : devicesAll) {
			format(device);
			if (dvcType.equals("cms")) {
				PageRequest pageRequestCms = newPageRequest(request);
				pageRequestCms.addAndCondition(CmsStruct.DVC_ID, SearchCondition.EQUAL, device.getId());
				pageRequestCms.setLeftJoinSql(" left join hd_cms_showlist on hd_cms_showlist.id=hd_cms_struct.id ");
				pageRequestCms.setColumns(" hd_cms_struct.*, hd_cms_showlist.high");
				device.setCmsStructs(cmsStructService.findColumnsAll(pageRequestCms));
			}
			if (dvcType.equals("vd")) {
				device.setVdStruct(vdStructService.getById(device.getId()));
			}
			if (dvcType.equals("wd")) {
				device.setWsStruct(wsStructService.getById(device.getId()));
			}
			devices.add(device);
		}
		return new MReturnObject(MReturnObject.SUCCESS, devices);
	}

	/**
	 * 格式化
	 * 
	 * @param device
	 */
	private void format(Device device) {
		if (device.getSizes() != null && device.getSizes().toString().split(",").length == 2) {
			device.setSize(new int[] { Integer.parseInt(device.getSizes().toString().split(",")[0]),
					Integer.parseInt(device.getSizes().toString().split(",")[1]) });
		}
		if (device.getPositions() != null && device.getPositions().toString().split(",").length == 2) {
			device.setPosition(new double[] { Double.valueOf(device.getPositions().toString().split(",")[0]),
					Double.valueOf(device.getPositions().toString().split(",")[1]) });
		}
	}

	/**
	 * 进入device 列表
	 *
	 * @param request
	 * @param model
	 * @return
	 * @author zxj
	 * @version 2017-09-05 11:46:13
	 */
	@RequestMapping(value = "/state")
	public @ResponseBody MReturnObject state(HttpServletRequest request) throws Exception {
		PageRequest pageRequest = newPageRequest(request);
		pageRequest.addAndCondition(Device.IS_DELETED, SearchCondition.EQUAL, 0);
		List<Device> devices = new ArrayList<>();

		String tableName = "hd_ds_" + DateUtils.formatDate(new Date(), DateUtils.DATETIME_YM_FORMAT);
		List<String> mTblsSqlForMMData = new ArrayList<String>();
		DsStruct dsStruct = new DsStruct();
		dsStruct.setTable(tableName);
		if (dsStructService.isTableExists(dsStruct) > 0) {
			mTblsSqlForMMData.add(tableName);
		}

		tableName = "hd_ds_" + DateUtils.formatDate(DateUtils.getLastMaxMonthDate(), DateUtils.DATETIME_YM_FORMAT);
		dsStruct.setTable(tableName);
		if (dsStructService.isTableExists(dsStruct) > 0) {
			mTblsSqlForMMData.add(tableName);
		}
		List<Device> devicesFindAll = deviceService.findAll(pageRequest);
		for (Device device : devicesFindAll) {
			format(device);
			device.setDsStruct(dsStructService.getById(device.getId()));
			devices.add(device);
		}
		return new MReturnObject(MReturnObject.SUCCESS, devices);
	}

	/**
	 * 进入device 列表
	 *
	 * @param request
	 * @param model
	 * @return
	 * @author zxj
	 * @version 2017-09-05 11:46:13
	 */
	@RequestMapping(value = "/{dvcType}/info")
	public @ResponseBody MReturnObject listInfo(HttpServletRequest request, @PathVariable String dvcType)
			throws Exception {
		PageRequest pageRequest = newPageRequest(request);
		pageRequest.addAndCondition("substring(" + Device.ID + ",4,2 )='" + DvcTypeConstant.getDvcType(dvcType) + "'",
				SearchCondition.SUB_STRING, null);
		pageRequest.putMap("brand", DvcTypeConstant.getDvcType(dvcType));
		pageRequest.addAndCondition(Device.IS_DELETED, SearchCondition.EQUAL, 0);
		List<Device> devices = new ArrayList<>();

		pageRequest.putMap("brand", DVC_FAC_NAME.getDvcFacNameMap(dvcType));
		List<Device> devicesFindAllInfo = deviceService.findAllInfo(pageRequest);
		for (Device device : devicesFindAllInfo) {
			format(device);
			device.getDeviceConfig()
					.setDvcBrandName(DVC_FAC_NAME.getDvcFacName(dvcType, device.getDeviceConfig().getDvcBrand()));
			devices.add(device);
		}
		return new MReturnObject(MReturnObject.SUCCESS, devices);
	}

	/**
	 * 新增/修改save操作
	 *
	 * @param model
	 * @param device
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author zxj
	 * @version 2017-09-05 11:46:13
	 */
	@RequestMapping(value = "/{dvcType}/save")
	public @ResponseBody MReturnObject create(Model model, Device device, HttpServletRequest request,
			HttpServletResponse response, @PathVariable String dvcType) throws Exception {
		try {
			deviceService.validateEntity(device);
			device.setId(DvcTypeConstant.DVC_ORGNIZATION + DvcTypeConstant.getDvcTypeName(device.getType()).toString()
					+ device.getId());
			device.setCreateTime(new Date());
			device.setIsDeleted(0);
			if (device.getId().length() > 10) {
				return new MReturnObject(MReturnObject.WARNING, "编号不能超过4位！");
			}
			if (deviceService.getById(device.getId()) != null) {
				return new MReturnObject(MReturnObject.WARNING, "编号已存在！");
			}
			if (dvcType.equals("cam")) {
				device.setVideo(request.getParameter("comPort"));
			}
			PageRequest pageRequest = newPageRequest(request);
			pageRequest.addAndCondition(DeviceConfig.COM_PORT, SearchCondition.EQUAL, request.getParameter("comPort"));
			if (dvcType.equals("cms")) {
				if (!isNumeric(device).getRespCode().equals(MReturnObject.SUCCESS)) {
					return isNumeric(device);
				}
			}
			DeviceConfig deviceConfig = new DeviceConfig();
			deviceConfig.setComPort(request.getParameter("comPort"));
			deviceConfig.setDvcId(device.getId());
			deviceConfig.setDvcBrand(request.getParameter("dvcBrand"));
			deviceConfig.setIsDeleted(0);
			deviceService.save(device, deviceConfig);
			return new MReturnObject(MReturnObject.SUCCESS, MessageUtils.ADD_SUCCESS, device);
		} catch (ValidateParamException e) {
			return new MReturnObject(MReturnObject.ERROR, e.getMessage());
		} catch (Exception e) {
			log.error("", e);
		}
		return new MReturnObject(MReturnObject.WARNING, MessageUtils.ADD_FAIL);
	}

	@RequestMapping(value = "/{dvcType}/modify")
	public @ResponseBody MReturnObject modify(Model model, Device device, HttpServletRequest request,
			HttpServletResponse response, @PathVariable String dvcType) throws Exception {
		try {
			Device orig_device = deviceService.getById(device.getId());
			if (orig_device != null) {
				device.setModifyTime(new Date());
				device.setIsDeleted(orig_device.getIsDeleted());
				device.setType(orig_device.getType());
				device.setCreateTime(orig_device.getCreateTime());
				device.setModifyTime(new Date());
				deviceService.validateEntity(device);
				if (dvcType.equals("cms")) {
					if (!isNumeric(device).getRespCode().equals(MReturnObject.SUCCESS)) {
						return isNumeric(device);
					}
				}

				DeviceConfig deviceConfig = new DeviceConfig();
				deviceConfig.setComPort(request.getParameter("comPort"));
				deviceConfig.setDvcId(device.getId());
				deviceConfig.setDvcBrand(request.getParameter("dvcBrand"));
				deviceConfig.setIsDeleted(0);
				deviceService.update(device, deviceConfig);
				return new MReturnObject(MReturnObject.SUCCESS, MessageUtils.EDIT_SUCCESS);
			}
		} catch (Exception e) {
			log.error("", e);
		}
		return new MReturnObject(MReturnObject.WARNING, MessageUtils.EDIT_WARING);
	}

	private MReturnObject isNumeric(Device device) {
		if (!device.getSizes().isEmpty()) {
			for (String s : device.getSizes().split(",")) {
				if (!StringUtils.isNumeric(s) || !(Integer.parseInt(s) > 0 && Integer.parseInt(s) < 1000)) {
					return new MReturnObject(MReturnObject.ERROR, "情报板宽度和高度参数请输入1-999的数字！");
				}
			}
		} else {
			return new MReturnObject(MReturnObject.ERROR, "情报板宽度和高度参数请输入1-999的数字！");
		}
		return new MReturnObject(MReturnObject.SUCCESS);
	}

	@RequestMapping(value = "/brand/{dvcType}")
	public @ResponseBody MReturnObject brand(HttpServletRequest request, @PathVariable String dvcType)
			throws Exception {
		return new MReturnObject(MReturnObject.SUCCESS, DVC_FAC_NAME.getDvcFacNameMap(dvcType));
	}

	public @ResponseBody MReturnObject destroy(@PathVariable java.lang.String id) {
		// 执行删除
		try {
			Device device = deviceService.getById(id);
			if (device != null) {
				device.setIsDeleted(1);
				device.setModifyTime(new Date());
				deviceService.update(device);
				DeviceConfig deviceConfig = deviceConfigService.getById(id);
				if (deviceConfig != null) {
					deviceConfig.setIsDeleted(1);
					deviceConfigService.update(deviceConfig);
				}
			}
			return new MReturnObject(MReturnObject.SUCCESS, MessageUtils.DELETE_SUCCESS);
		} catch (Exception e) {
			// TODO 检查关联状态
		}
		return new MReturnObject(MReturnObject.WARNING, MessageUtils.DELETE_WARNING);
	}

	@RequestMapping(value = "/type/{dvcType}")
	public @ResponseBody MReturnObject deviceType(HttpServletRequest request, @PathVariable String dvcType)
			throws Exception {
		return new MReturnObject(MReturnObject.SUCCESS, DvcTypeConstant.getFactories(dvcType));
	}

	@RequestMapping(value = "/number")
	public @ResponseBody MReturnObject number(HttpServletRequest request) {
		return new MReturnObject(MReturnObject.SUCCESS, DvcTypeConstant.getDvcTypeCons());
	}

}
