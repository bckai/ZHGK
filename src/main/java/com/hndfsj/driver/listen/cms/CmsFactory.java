package com.hndfsj.driver.listen.cms;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hndfsj.app.device.domain.CmsStruct;
import com.hndfsj.framework.utils.StringUtil;
import com.hndfsj.framework.utils.driver.CRC16Util;
import com.hndfsj.framework.utils.driver.HexStrUtil;

public class CmsFactory {
	static Logger log = LoggerFactory.getLogger(CmsFactory.class);

	private static String cmd_HanWei_Header = "30313130506C61792E6C73742B000000005B4C6973745D0D0A";
	private static String cmd_SanSi_Header = "30303130706C61792E6C73742B00000000";

	/**
	 * 汉威发送信息拼接
	 * 
	 * @param msgs
	 * @return
	 */
	public static String HanWeiSendMsgs(List<CmsStruct> msgs) {
		String command = "Item_no=" + msgs.size() + "\r\n";
		for (int i = 0; i < msgs.size(); i++) {
			command.offsetByCodePoints(0, 654);
			command += "Item" + i + "=" + msgs.get(i).getKeepTime() * 100 + ",1,0,";
			command += "\\C" + intAdd0(msgs.get(i).getCdntLeft()) + intAdd0(msgs.get(i).getCdntUp());
			command += "\\f" + getFontStyle(msgs.get(i).getFontStyle()) + msgs.get(i).getFontSize()
					+ msgs.get(i).getFontSize();
			command += "\\c" + getColor(msgs.get(i).getFontColor());
			command += msgs.get(i).getMessage().replaceAll("\\\\A", "\\\\n") + "\r\n";
		}
		log.info(command);
		command = cmd_HanWei_Header + HexStrUtil.getHexResult(command);
		command = "02" + command + CRC16Util.caluCRC16(command) + "03";
		return command;
	}

	/**
	 * 三思发送信息拼接
	 * 
	 * @param msgs
	 * @return
	 */
	public static String SanSiSendMsgs(List<CmsStruct> msgs) {
		String command = "item_no=" + msgs.size() + "\r\n";
		for (int i = 0; i < msgs.size(); i++) {
			command += "item" + i + "=" + msgs.get(i).getKeepTime() * 100 + ",1,0,";
			command += "\\C" + intAdd0(msgs.get(i).getCdntLeft()) + intAdd0(msgs.get(i).getCdntUp());
			command += "\\f" + getFontStyle(msgs.get(i).getFontStyle()) + msgs.get(i).getFontSize()
					+ msgs.get(i).getFontSize();
			command += "\\c" + getColor(msgs.get(i).getFontColor());
			command += msgs.get(i).getMessage().replaceAll("\\\\A", "\\\\n") + "\r\n";
		}
		log.info(command);
		command = cmd_SanSi_Header + HexStrUtil.getHexResult(command);
		command = "02" + command + CRC16Util.caluCRC16(command) + "03";
		return command;
	}

	private static String intAdd0(int c) {
		String a = Integer.toString(c);
		if (a.length() == 1) {
			a = "00" + a;
		} else if (a.length() == 2) {
			a = "0" + a;
		}
		return a;
	}

	private static String getColor(String color) {
		if ("red".equals(color)) {
			return "255000000000";
		} else if ("green".equals(color)) {
			return "000255000000";
		} else {
			// 黄色或着其它未识别色，均以黄色显示
			return "255255000000";
		}
	}

	private static String getFontStyle(String fontStyle) {
		switch (fontStyle) {
		case "黑体":
			return "h";
		case "楷体":
			return "k";
		case "宋体":
			return "s";
		case "仿宋":
			return "f";
		case "Tahoma":
			return "Tahoma";
		default:
			break;
		}
		return "h";
	}
}
