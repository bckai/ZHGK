package com.hndfsj.driver.listen.cms;

import java.util.Date;
import java.util.List;
import java.util.Observable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hndfsj.app.device.domain.CmsStruct;
import com.hndfsj.app.device.domain.DsStruct;
import com.hndfsj.app.device.service.ICmsStructService;
import com.hndfsj.app.device.service.IDsStructService;
import com.hndfsj.driver.HDBaseDriver;
import com.hndfsj.driver.serailport.SerialPortReader;
import com.hndfsj.framework.utils.SpringContextHolder;
import com.hndfsj.framework.utils.driver.CusDateTool;
import com.hndfsj.framework.utils.driver.HexStrUtil;

public class Cms_SanSi extends HDBaseDriver {

	static Logger log = LoggerFactory.getLogger(Cms_SanSi.class);

	private String cmdCheckLink = "023031393727C503";
	private SerialPortReader sr;
	private String dataStr;
	private DsStruct dsStruct;
	private String isPubShow = "";
	private boolean isCheckLink;
	private int pubNum; // 计数器，用于节目发布失败的尝试次数
	private Date showCurrPubTime = new Date(); // 用于记录节目的发布时间，用于分辨无法发送的旧节目
	private int noticeFlag = 0;

	private int flag = 1;

	private IDsStructService dsStructService = SpringContextHolder.getBean("dsStructService");
	private ICmsStructService cmsStructService = SpringContextHolder.getBean("cmsStructService");

	/**
	 * 初始化驱动并打开串口
	 * 
	 * @param dvcId
	 */
	public Cms_SanSi(String dvcId) {
		super.driverInit(dvcId);
		this.spr.delayRead = 200;
	}

	@Override
	public void update(Observable o, Object arg) {
		// 处理从串口收到的返回数据
		dataStr = arg.toString();
		dataStr = HexStrUtil.getHexResult(dataStr);
		sr = (SerialPortReader) o;
		// 情报板返回数据太长，截取一下。
		if (dataStr.length() > 200) {
			dataStr = dataStr.substring(0, 200);
		}
		log.info("接收数据来自：三思光电可变信息标志-" + this.dvcId + "-" + sr.spc.getPortName() + "-"
				+ CusDateTool.getSysTime().substring(0, 19) + "\r\n" + dataStr);
		// 如果是标记为检查通讯状态的情况
		if (isCheckLink) {
			// 通讯正常
			dsStruct = new DsStruct(this.dvcId, 1, "通讯正常");
			dsStructService.save(dsStruct);
			isCheckLink = false;
			if (flag == 0) {
				flag = 1;
			}
		}

		// 或者是处于发布节目等待执行结果的状态
		else if (!"".equals(isPubShow)) {
			// 节目发布成功,发布完成后将节目状态更新为已发布
			cmsStructService.updateSendState(isPubShow);
			isPubShow = "";
			pubNum = 0;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void send(Object obj) {
		// 情报板发送接收到的参数为： List<ShowCurr>
		if (obj instanceof List<?>) {
			List<CmsStruct> msgs = (List<CmsStruct>) obj;
			if (showCurrPubTime.compareTo(msgs.get(0).getCreateTime()) == 0) {
				// 同一次（批）节目连续发布了五次未成功，则不再发送,添加发布失败的警告
				if (noticeFlag == 1) {
					return;
				}
				if (pubNum > 5 && noticeFlag == 0) {
					dsStruct = new DsStruct(this.dvcId, 0, "通讯故障");
					dsStructService.save(dsStruct);
					noticeFlag = 1;
					return;
				}
			} else {
				// 不是上次发送的节目，重新计数（说明又对此情报板发送新的节目任务）
				pubNum = 0;
				noticeFlag = 0;
			}
			super.sendHex(CmsFactory.SanSiSendMsgs(msgs));
			// 标记为发布节目等待结果的状态
			isPubShow = this.dvcId;
			pubNum += 1; // 计数器+1,五次发送失败则不再发送
			showCurrPubTime = msgs.get(0).getCreateTime();
		}
	}

	@Override
	public void getData() {
		// 情报板不需要采集数据，so……
	}

	@Override
	public void checkLink() {
		// 情报板检查通讯状态……根据通讯协议
		super.sendHex(cmdCheckLink);
		isCheckLink = true;
		new Thread(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					Thread.currentThread().sleep(5000);
					if (isCheckLink) {
						// 如果5s钟后还处于检查连接的状态，则认定为通讯故障
						dsStruct = new DsStruct(dvcId, 0, "通讯故障");
						dsStructService.save(dsStruct);
						isCheckLink = false;
						if (flag == 1) {
							flag = 0;
						}
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}
