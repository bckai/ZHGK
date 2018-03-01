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
import com.hndfsj.admin.dao.IDictionaryDao;
import com.hndfsj.app.common.enums.ZHGKEnum.DICTIONARY_COUNT;
import com.hndfsj.app.device.dao.IVdStructDao;
import com.hndfsj.app.device.domain.VdStruct;
import com.hndfsj.app.device.service.IVdStructService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO
 * 
 * @copyright {@link www.hndfsj.com}
 * @author BuChunKai<Auto generate>
 * @version 2017-09-05 14:37:21
 * @see com.hndfsj.app.device.service.impl.VdStruct
 */
@Service("vdStructService")
public class VdStructServiceImpl extends BaseServiceImpl<VdStruct, String> implements IVdStructService {

	static Logger log = LoggerFactory.getLogger(VdStructServiceImpl.class);

	@Resource
	private IVdStructDao vdStructDao;

	@Resource
	private IDictionaryDao dictionaryDao;

	@Override
	protected IBaseDao<VdStruct, String> getBaseDao() {
		return this.vdStructDao;
	}

	public void validateEntity(VdStruct vdStruct) throws ValidateParamException {
		// TODO write validate code throw new ValidateParamException
	}

	@Override
	public void save(VdStruct vdStruct) {
		vdStruct.setCreateTime(new Date());
		vdStruct.setTable("hd_vd_" + DateUtils.formatDate(vdStruct.getCreateTime(), DateUtils.DATETIME_YM_FORMAT));
		if (vdStructDao.getTableCount(vdStruct) == 0) {
			vdStructDao.insertTable(vdStruct);
		}
		super.save(vdStruct);
		vdStruct.setTable("hd_vd_struct");
		super.save(vdStruct);
		dictionaryDao.addCountOne(DICTIONARY_COUNT.VD.name());
	}

	@Override
	public int isTableExists(VdStruct vdStruct) {
		return vdStructDao.isTableExists(vdStruct);
	}

	@Override
	public List<VdStruct> getAllPage(PageRequest pageRequest) {
		return vdStructDao.getAllPage(pageRequest);
	}

	@Override
	public void saveWsYears(VdStruct vdStructinfo) {
		vdStructinfo.setCreateTime(new Date());
		vdStructinfo.setTable("hd_vd_struct");
		vdStructinfo.setTransFlag(0);
		vdStructinfo.setPeriod(0);// period采集周期（单位：秒）,

		vdStructinfo.setCarType(0);

		vdStructinfo.setLane1Flux(0);
		vdStructinfo.setLane2Flux(0);
		vdStructinfo.setLane3Flux(0);
		vdStructinfo.setLane4Flux(0);
		vdStructinfo.setLane5Flux(0);
		vdStructinfo.setLane6Flux(0);
		vdStructinfo.setLane7Flux(0);
		vdStructinfo.setLane8Flux(0);

		vdStructinfo.setLane1Speed(0);
		vdStructinfo.setLane2Speed(0);
		vdStructinfo.setLane3Speed(0);
		vdStructinfo.setLane4Speed(0);
		vdStructinfo.setLane5Speed(0);
		vdStructinfo.setLane6Speed(0);
		vdStructinfo.setLane7Speed(0);
		vdStructinfo.setLane8Speed(0);

		vdStructinfo.setLane1Occ(Double.valueOf(0));
		vdStructinfo.setLane2Occ(Double.valueOf(0));
		vdStructinfo.setLane3Occ(Double.valueOf(0));
		vdStructinfo.setLane4Occ(Double.valueOf(0));
		vdStructinfo.setLane5Occ(Double.valueOf(0));
		vdStructinfo.setLane6Occ(Double.valueOf(0));
		vdStructinfo.setLane7Occ(Double.valueOf(0));
		vdStructinfo.setLane8Occ(Double.valueOf(0));

		super.save(vdStructinfo);
	}

	@Override
	public VdStruct status(String dvcId) {
		return vdStructDao.status(dvcId);
	}
}
