
package com.hndfsj.admin.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * TODO
 * 
 * @copyright {@link www.hndfsj.com}
 * @author ZhengXingJian<Auto generate>
 * @version 2017-05-02 19:28:38
 * @see com.hndfsj.admin.domain.UserNode
 */
public class UserNode implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	// date formats
	public static final String USER_ID = "bas_user_node.user_id";
	public static final String NODE_ID = "bas_user_node.node_id";

	// userId用户ID 关联表sys_user,
	// nodeId节点ID 关联表bas_line_node,

	// columns START
	/**
	 * 用户ID 关联表sys_user
	 */
	private java.lang.String userId;
	/**
	 * 节点ID 关联表bas_line_node
	 */
	private java.lang.String nodeId;
	// columns END
	private java.lang.String nodeName;
	// concstructor

	public UserNode() {
	}

	public UserNode(java.lang.String userId) {
		this.userId = userId;
	}

	public UserNode(String userId, String nodeId) {
		setUserId(userId);
		setNodeId(nodeId);
	}

	// get and set
	public UserNode setUserId(java.lang.String value) {
		this.userId = value;
		return this;
	}

	public java.lang.String getUserId() {
		return this.userId;
	}

	public UserNode setNodeId(java.lang.String value) {
		this.nodeId = value;
		return this;
	}

	public java.lang.String getNodeId() {
		return this.nodeId;
	}

	public java.lang.String getNodeName() {
		return nodeName;
	}

	public void setNodeName(java.lang.String nodeName) {
		this.nodeName = nodeName;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("UserId", getUserId())
				.append("NodeId", getNodeId()).toString();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getUserId()).toHashCode();
	}

	public boolean equals(Object obj) {
		if (obj instanceof UserNode == false)
			return false;
		if (this == obj)
			return true;
		UserNode other = (UserNode) obj;
		return new EqualsBuilder().append(getUserId(), other.getUserId()).isEquals();
	}
}
