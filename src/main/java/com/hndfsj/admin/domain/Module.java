package com.hndfsj.admin.domain;

import java.io.Serializable;
import java.util.List;

import com.hndfsj.framework.objects.TreeObject;

/**
 * @author ibm
 * 
 */
public class Module extends TreeObject implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id; // 模块ID
	private String name; // 模块名称
	private String superMod;// 上级模块
	private int sortNo; // 排序
	private String isLeaf; // 是否叶子节点
	private String isValid; // 是否可用
	private String defaultURL;// 默认url
	private List<Resource> resources;// 模块对应的资源信息

	public Module() {

	}

	public Module(String id, String name, String superMod, int sortNo, String isLeaf,
			String defaultURL) {
		this.id = id;
		this.name = name;
		this.superMod = superMod;
		this.sortNo = sortNo;
		this.isLeaf = isLeaf;
		this.defaultURL = defaultURL;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSuperMod() {
		return superMod;
	}

	public void setSuperMod(String superMod) {
		this.superMod = superMod;
	}

	public int getSortNo() {
		return sortNo;
	}

	public void setSortNo(int sortNo) {
		this.sortNo = sortNo;
	}

	public String getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}

	public String getDefaultURL() {
		return defaultURL;
	}

	public void setDefaultURL(String defaultURL) {
		this.defaultURL = defaultURL;
	}

	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	@Override
	public String toString() {
		return "Module [id=" + id + ", name=" + name + ", superMod=" + superMod + ", sortNo="
				+ sortNo + ", isLeaf=" + isLeaf + ", isValid=" + isValid + ", defaultURL="
				+ defaultURL + ", resources=" + resources + "]";
	}
	
	
}
