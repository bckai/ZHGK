package com.hndfsj.framework.objects;

import java.util.ArrayList;
import java.util.List;

/**
 * 为EasyUI Tree创建对应的对象 
 *
 * @author ibm
 * @date   2010-7-13
 */
public class TreeObject {
	
	List<Object> children;
	

	public List<Object> getChildren() {
		return children;
	}
	public void setChildren(List<Object> children) {
		this.children = children;
	}
	public void addChild(Object child) {
		if(this.children == null) this.children = new ArrayList<Object>();
		this.children.add(child);
	}
	
}
