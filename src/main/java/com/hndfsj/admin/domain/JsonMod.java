package com.hndfsj.admin.domain;

import java.util.List;

public class JsonMod {
	private String name;
	private Object show;
	private Object oprea;
	private String describe;
	List<JsonMod> role;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getShow() {
		return show;
	}

	public void setShow(Object show) {
		this.show = show;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public List<JsonMod> getRole() {
		return role;
	}

	public void setRole(List<JsonMod> role) {
		this.role = role;
	}

	public Object getOprea() {
		return oprea;
	}

	public void setOprea(Object oprea) {
		this.oprea = oprea;
	}

	@Override
	public String toString() {
		return "JsonMod [name=" + name + ", show=" + show + ", describe=" + describe + ", role=" + role + "]";
	}

}
