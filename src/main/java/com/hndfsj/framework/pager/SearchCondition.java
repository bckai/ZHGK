package com.hndfsj.framework.pager;

public class SearchCondition {
	public final static String GREATER = ">";
	public final static String LESS = "<";
	public final static String EQUAL = "=";
	public final static String NOTEQUAL = "<>";
	public final static String GREATER_EQUAL = ">=";
	public final static String LESS_EQUAL = "<=";
	public final static String LIKE = "like";
	public final static String NOT_EQUAL = "!=";
	public final static String SUB_STRING="substring";
	
	/**
	 * 不转义标志，用于SQL的特别判断语句，如is ,in
	 */
	public final static String ESCAPE = "escape"; 
	private String key;
	private Object value;
	private String operator = EQUAL;

	public SearchCondition() {

	}

	public SearchCondition(String key, Object value) {
		this.key = key;
		this.value = value;
	}

	public SearchCondition(String key, String operator, Object value) {
		this.key = key;
		this.operator = operator;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
}
