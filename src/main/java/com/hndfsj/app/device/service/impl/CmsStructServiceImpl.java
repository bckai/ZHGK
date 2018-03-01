package com.hndfsj.app.device.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.hndfsj.framework.utils.DateUtils;
import com.hndfsj.framework.base.dao.IBaseDao;
import com.hndfsj.framework.base.service.BaseServiceImpl;
import com.hndfsj.framework.exceptions.ValidateParamException;
import com.hndfsj.framework.pager.PageRequest;
import com.hndfsj.app.device.dao.ICmsStructDao;
import com.hndfsj.app.device.dao.IDeviceDao;
import com.hndfsj.app.device.domain.CmsStruct;
import com.hndfsj.app.device.service.ICmsStructService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO
 * 
 * @copyright {@link www.hndfsj.com}
 * @author ZhengXingJian<Auto generate>
 * @version 2017-09-12 14:44:18
 * @see com.hndfsj.app.device.service.impl.CmsStruct
 */
@Service("cmsStructService")
public class CmsStructServiceImpl extends BaseServiceImpl<CmsStruct, java.lang.String> implements ICmsStructService {

	static Logger log = LoggerFactory.getLogger(CmsStructServiceImpl.class);

	@Resource
	private ICmsStructDao cmsStructDao;
	@Resource
	private IDeviceDao deviceDao;

	@Override
	protected IBaseDao<CmsStruct, java.lang.String> getBaseDao() {
		return this.cmsStructDao;
	}

	public void validateEntity(CmsStruct cmsStruct) throws ValidateParamException {
	}

	@Override
	public int isTableExists(CmsStruct cmsStruct) {
		return cmsStructDao.isTableExists(cmsStruct);
	}

	@Override
	public List<CmsStruct> getAllPage(PageRequest pageRequest) {
		return cmsStructDao.getAllPage(pageRequest);
	}

	@Override
	public void updateSendState(String dvcId) {
		cmsStructDao.updateSendState(dvcId);
	}

	@Override
	public void save(CmsStruct cmsStruct) {
		cmsStruct.setTable("hd_cms_" + DateUtils.formatDate(cmsStruct.getCreateTime(), DateUtils.DATETIME_YM_FORMAT));
		if (cmsStructDao.getTableCount(cmsStruct) == 0) {
			cmsStructDao.insertTable(cmsStruct);
		}
		super.save(cmsStruct);
		cmsStruct.setTable("hd_cms_struct");
		super.save(cmsStruct);
	}

	@Override
	public void saveLog(List<CmsStruct> msgs) {
		for (CmsStruct cmsStruct : msgs) {
			cmsStruct.setTable("hd_cms_" + DateUtils.formatDate(new Date(), DateUtils.DATETIME_YM_FORMAT));
			if (cmsStructDao.getTableCount(cmsStruct) == 0) {
				cmsStructDao.insertTable(cmsStruct);
			}
			super.save(cmsStruct);
			cmsStruct.setTable("hd_cms_struct");
			cmsStruct.setPubFlag(1);
			cmsStruct.setCreateTime(new Date());
			super.update(cmsStruct);
		}
	}

}
