package com.hndfsj.framework.utils;

import java.awt.Font;
import java.awt.FontMetrics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringUtil {
	public static List<Map> FontProperties(String font, int Size, String string) {
		
		Map map1=new HashMap<>();
		map1.put("h", "黑体");
		map1.put("k", "楷体");
		map1.put("s", "宋体");
		map1.put("f", "仿宋");
		
		Font f = new Font(map1.get(font).toString(), Font.BOLD, Size);
		FontMetrics fm = sun.font.FontDesignMetrics.getMetrics(f);
		List<Map> list = new ArrayList<>();
		for (int i = 0; i < string.length(); i++) {
			Map map = new HashMap<>();
			map.put("H", fm.getHeight());// 高度
			map.put("W", fm.charWidth(string.charAt(i)));// 单个字符宽度
			map.put("C", string.charAt(i));// 单个字符宽度
			list.add(map);
		}
		return list;
	}

	public static Map FontPropertiesAll(String font, String string) {
		Font f = new Font("宋体", Font.BOLD, 12);
		FontMetrics fm = sun.font.FontDesignMetrics.getMetrics(f);
		List<Map> list = new ArrayList<>();
		Map map = new HashMap<>();
		map.put("H", fm.getHeight());// 高度
		map.put("EW", fm.charWidth('A'));// 单个字符宽度
		map.put("ZW", fm.stringWidth("宋"));// 单个字符串的宽度
		map.put("ZEW", fm.stringWidth("宋A"));// 整个字符串的宽度
		list.add(map);
		return map;
	}

	public static List<Map> hexconter(String font, int Size, String string, String w) {
		Size=Size-1;
		List<Map> list = FontProperties(font, Size, string);
		List<Map> list2 = new ArrayList<>();
		for (Map map : list) {
			if (list2.size() == 0) {
				Map map2 = new HashMap<>();
				map2.put("C", map.get("C"));
				map2.put("W", map.get("W"));
				list2.add(map2);
			} else {
				if ((Integer.parseInt(map.get("W").toString())+ Integer.parseInt(list2.get(list2.size()-1).get("W").toString()) > Integer.parseInt(w))) {
					Map map2 = new HashMap<>();
					map2.put("C", map.get("C"));
					map2.put("W", Integer.parseInt(map.get("W").toString()));
					list2.add(map2);
				} else {
					list2.get(list2.size()-1).put("C",list2.get(list2.size()-1).get("C").toString()+map.get("C"));
					list2.get(list2.size()-1).put("W",Integer.parseInt(list2.get(list2.size()-1).get("W").toString())+Integer.parseInt(map.get("W").toString()));
				}
			}
		}
		System.out.println(list2); 	
		return list2;
	}

}
