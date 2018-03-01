package com.hndfsj.framework.objects;


/**
 * 为ComboTree创建对应的对象 
 *
 * @author ibm
 * @date   2010-7-13
 */
public class ComboTreeObject extends TreeObject{
	
	String id;
	String text;
	String parentId;
	boolean checked;
	
	public ComboTreeObject(){
	}
	
	public ComboTreeObject(String id,String text) {
		this.id = id;
		this.text = text;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public boolean getChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
}
