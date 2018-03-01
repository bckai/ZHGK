/**
 * hndfsj ccls project
 */

package com.hndfsj.framework.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * 类反射工具类<br>
 * 例如：List POJO对象集合转成Map<property,value><br>
 * 将 POJO对象集合转成Map<property,value>
 * @author zhengwenquan
 * @date 2010-10-9 下午04:48:51
 */
public class ClassReflectUtil {
	/**
	 * 
	 * 将List POJO对象集合转成Map <br>
	 * 说明：List集合中的对象类型是不同种的<br>
	 * 
	 * @param listO
	 * @return
	 * @author zhengwenquan
	 */
	public static Map convertDifferentObjectToMap(List<Object> listO) throws Exception {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		for (int i = 0; i < listO.size(); i++) {
			Object obj = listO.get(i);
			Class c = obj.getClass();
			// 得到属性集合
			Field field[] = c.getDeclaredFields();
			// 得到方法集合
			Method m[] = c.getDeclaredMethods();
			for (int j = 0; j < field.length; j++) {
				// 组合成get方法名
				String getName = "get" + StringUtils.capitalize(field[j].getName());
				for (int k = 0; k < m.length; k++) {
					if (getName.equals(m[k].getName())) {
						// put数据
						Object value = m[k].invoke(obj, new Object[0]);
						if (value instanceof Double && value == null) {
							value = 0.00;
						}
						hashMap.put(field[j].getName(), value);
					}
				}
			}
		}
		return hashMap;
	}

	/**
	 * 
	 * 将 POJO对象集合转成Map<br>
	 * 
	 * @param obj
	 * @return
	 * @author zhengwenquan
	 */
	@SuppressWarnings("unchecked")
	public static Map convertSameObjectToMap(Object obj) throws Exception {
		if (obj == null) {
			return null;
		}
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		Class c = obj.getClass();
		// 得到属性集合
		Field field[] = c.getDeclaredFields();
		// 得到方法集合
		Method m[] = c.getDeclaredMethods();
		for (int j = 0; j < field.length; j++) {
			// 组合成get方法名
			String getName = "get" + StringUtils.capitalize(field[j].getName());
			for (int k = 0; k < m.length; k++) {
				if (getName.equals(m[k].getName())) {
					// put数据
					hashMap.put(field[j].getName(), m[k].invoke(obj, new Object[0]));
				}
			}
		}
		return hashMap;
	}
	public static int countDeclaredFieds(Object obj) throws Exception {
		if (obj == null) {
			throw new Exception();
		}
		Class c = obj.getClass();
		return c.getDeclaredFields().length;
	}

	/**
	 * 
	 * 判断一个对象是否为空，为空则返回该对象的一个实例，否则返回本身
	 * 
	 * @param object
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings( { "unchecked", "static-access" })
	public static Object returnObjectInstance(Object object, Class c) throws Exception {
		Object instance = object;
		if (object == null) {
			instance = c.forName(c.getName()).newInstance();
		}
		return instance;
	}

	

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {

		// Map personMap = ClassReflectUtil.convertDifferentObjectToMap(list);
		Map personMap = ClassReflectUtil.convertSameObjectToMap(null);
		Iterator it = personMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry map = (Map.Entry) it.next();
			System.out.println("属性名：" + map.getKey() + "  ,值: " + map.getValue());
		}

	}

}
