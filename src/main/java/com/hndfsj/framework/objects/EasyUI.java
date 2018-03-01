package com.hndfsj.framework.objects;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.hndfsj.admin.domain.Module;
import com.hndfsj.admin.domain.Resource;
import com.hndfsj.framework.utils.ReflectionUtils;

/**
 * EasyUI对应类
 *
 * @author ibm
 * @date   2010-7-13
 */
public class EasyUI {

	public static List<TreeObject> genTableTree(Collection<?> list,String idName,String parentIdName){ 
		//==初始化对象列表
		List<TreeObject> listTree = new ArrayList<TreeObject>(); 
		for (Iterator<?> iterator = list.iterator(); iterator.hasNext();) {
			TreeObject object = (TreeObject) iterator.next();
			listTree.add(object);
		}
		
		//==对象列表放入Map
		Map<String,TreeObject> objectMap = new HashMap<String,TreeObject>();
		for (Iterator<?> iterator = listTree.iterator(); iterator.hasNext();) {
			TreeObject object = (TreeObject)iterator.next();
			String id = (String)ReflectionUtils.getFieldValue(object,idName);
			objectMap.put(id, object);
		}
		
		//==生成目标树对象
		List<TreeObject> returnList = new ArrayList<TreeObject>();
		for (Iterator<?> iterator = listTree.iterator(); iterator.hasNext();) {
			TreeObject object = (TreeObject)iterator.next();
			String parentId=(String)ReflectionUtils.getFieldValue(object,parentIdName);
			TreeObject pObject = objectMap.get(parentId);
			if (pObject != null) {
				pObject.addChild(object);
			} else {
				returnList.add(object);
			}
		}
		
		return returnList;		
	}
	public static List<TreeObject> genDeptTree(String deptId,Collection<?> list,String idName,String parentIdName){ 
		//==初始化对象列表
		List<TreeObject> listTree = new ArrayList<TreeObject>(); 
		for (Iterator<?> iterator = list.iterator(); iterator.hasNext();) {
			TreeObject object = (TreeObject) iterator.next();
			listTree.add(object);
		}
		
		//==对象列表放入Map
		Map<String,TreeObject> objectMap = new HashMap<String,TreeObject>();
		for (Iterator<?> iterator = listTree.iterator(); iterator.hasNext();) {
			TreeObject object = (TreeObject)iterator.next();
			String id = (String)ReflectionUtils.getFieldValue(object,idName);
			objectMap.put(id, object);
		}
		
		Map<String,TreeObject> newObjectMap = new HashMap<String,TreeObject>();
		//==生成目标树对象
		List<TreeObject> returnList = new ArrayList<TreeObject>();
		for (Iterator<?> iterator = listTree.iterator(); iterator.hasNext();) {
			TreeObject object = (TreeObject)iterator.next();
			String parentId=(String)ReflectionUtils.getFieldValue(object,parentIdName);
			TreeObject pObject = objectMap.get(parentId);
			newObjectMap.put((String) ReflectionUtils.getFieldValue(object,"id"), object);
			if (pObject != null) {
				pObject.addChild(object);
			} else {
				returnList.add(object);
			}
		}
		TreeObject dept=newObjectMap.get(deptId);
		if(dept!=null){
			List<TreeObject> newlist = new ArrayList<TreeObject>();
			newlist.add(dept);
			return newlist;
		}
		return returnList;		
	}
	public static Map<String,TreeObject> genDeptMapTree( Collection<?> list,String idName,String parentIdName){ 
		//==初始化对象列表
		List<TreeObject> listTree = new ArrayList<TreeObject>(); 
		for (Iterator<?> iterator = list.iterator(); iterator.hasNext();) {
			TreeObject object = (TreeObject) iterator.next();
			listTree.add(object);
		}
		
		//==对象列表放入Map
		Map<String,TreeObject> objectMap = new HashMap<String,TreeObject>();
		for (Iterator<?> iterator = listTree.iterator(); iterator.hasNext();) {
			TreeObject object = (TreeObject)iterator.next();
			String id = (String)ReflectionUtils.getFieldValue(object,idName);
			objectMap.put(id, object);
		}
		
		Map<String,TreeObject> newObjectMap = new HashMap<String,TreeObject>();
		//==生成目标树对象
		List<TreeObject> returnList = new ArrayList<TreeObject>();
		for (Iterator<?> iterator = listTree.iterator(); iterator.hasNext();) {
			TreeObject object = (TreeObject)iterator.next();
			String parentId=(String)ReflectionUtils.getFieldValue(object,parentIdName);
			TreeObject pObject = objectMap.get(parentId);
			newObjectMap.put((String) ReflectionUtils.getFieldValue(object,"id"), object);
			if (pObject != null) {
				pObject.addChild(object);
			} else {
				returnList.add(object);
			}
		}
		return newObjectMap;		
	}
	
	public static int count(TreeObject dept){
		int count=(Integer)ReflectionUtils.getFieldValue(dept,"count");	
		if(dept.getChildren()!=null){
			List<Object> list=dept.getChildren();
			for(Object obj:list){
				TreeObject to=(TreeObject) obj;
				count+=count(to);
			}
		}
		ReflectionUtils.setFieldValue(dept,"count",count);	
		ReflectionUtils.setFieldValue(dept,"name",ReflectionUtils.getFieldValue(dept,"name")+"("+count+"/"+ReflectionUtils.getFieldValue(dept,"villageCount")+")");	
		return count;
	}
	public static int get(TreeObject dept){
		int count=(Integer)ReflectionUtils.getFieldValue(dept,"count");	
		if(dept.getChildren()!=null){
			List<Object> list=dept.getChildren();
			for(Object obj:list){
				TreeObject to=(TreeObject) obj;
				count+=count(to);
			}
		}
		ReflectionUtils.setFieldValue(dept,"count",count);	
		ReflectionUtils.setFieldValue(dept,"name",ReflectionUtils.getFieldValue(dept,"name")+"("+count+"/"+ReflectionUtils.getFieldValue(dept,"villageCount")+")");	
		return count;
	}

	/**
	 * 将对象列表转换为Tree需要的列表对象
	 * 
	 * @param list
	 * @param fieldId
	 * @param fieldName
	 * @param fieldParent
	 * @return
	 */
	public static List<ComboTreeObject> genComboTree(List<?> list,String fieldId,String fieldName,String fieldParent){ 
		//==初始化对象列表
		List<ComboTreeObject> listTree = new ArrayList<ComboTreeObject>(); 
		for (Iterator<?> iterator = list.iterator(); iterator.hasNext();) {
			Object object = (Object) iterator.next();
			ComboTreeObject treeObj = new ComboTreeObject();
			treeObj.setId((String)ReflectionUtils.getFieldValue(object,fieldId));
			treeObj.setText((String)ReflectionUtils.getFieldValue(object,fieldName));
			treeObj.setParentId((String)ReflectionUtils.getFieldValue(object,fieldParent));
			listTree.add(treeObj);
		}
		
		//==对象列表放入Map
		Map<String,ComboTreeObject> objectMap = new HashMap<String,ComboTreeObject>();
		for (Iterator<ComboTreeObject> iterator = listTree.iterator(); iterator.hasNext();) {
			ComboTreeObject object = iterator.next();
			objectMap.put(object.getId(), object);
		}
		
		//==生成目标树对象
		List<ComboTreeObject> returnList = new ArrayList<ComboTreeObject>();
		for (Iterator<ComboTreeObject> iterator = listTree.iterator(); iterator.hasNext();) {
			ComboTreeObject object = iterator.next();
			ComboTreeObject pObject = objectMap.get(object.getParentId());
			if (pObject != null) {
				pObject.addChild(object);
			} else {
				returnList.add(object);
			}
		}
		
		return returnList;
	}
	

	/**
	 * 生成分配模块资源的JSON树
	 * 
	 * @param listAll
	 * @param listAssign
	 * @return
	 */
	public static List<ComboTreeObject> genModuleResourceTree(List<Module> listAll,List<Resource> listAssign){ 
		//==初始化对象列表
		List<ComboTreeObject> listTree = new ArrayList<ComboTreeObject>(); 
		for (Iterator<Module> iterModule = listAll.iterator(); iterModule.hasNext();) {
			Module module = iterModule.next();
			//==创建Module对象
			ComboTreeObject treeObj = new ComboTreeObject();
			treeObj.setId(module.getId());
			treeObj.setText(module.getName());
			treeObj.setParentId(module.getSuperMod());
			listTree.add(treeObj);
			//==创建Resource对象
			if (module.getIsLeaf().equals("1")) {//叶子节点
				for (Iterator<Resource> iterRes = module.getResources().iterator(); iterRes.hasNext();) {
					Resource res = iterRes.next();
					treeObj = new ComboTreeObject();
					treeObj.setId(res.getId());
					treeObj.setText(res.getName());
					treeObj.setParentId(module.getId());
					if (listAssign.contains(res)) treeObj.setChecked(true);
					else  treeObj.setChecked(false);
					listTree.add(treeObj);
				}
			}
		}
		
		//==对象列表放入Map
		Map<String,ComboTreeObject> objectMap = new HashMap<String,ComboTreeObject>();
		for (Iterator<ComboTreeObject> iterator = listTree.iterator(); iterator.hasNext();) {
			ComboTreeObject object = iterator.next();
			objectMap.put(object.getId(), object);
		}
		
		//==生成目标树对象
		List<ComboTreeObject> returnList = new ArrayList<ComboTreeObject>();
		for (Iterator<ComboTreeObject> iterator = listTree.iterator(); iterator.hasNext();) {
			ComboTreeObject object = iterator.next();
			ComboTreeObject pObject = objectMap.get(object.getParentId());
			if (pObject != null) {
				pObject.addChild(object);
			} else {
				returnList.add(object);
			}
		}
		
		return returnList;
	}	
}
