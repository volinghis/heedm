/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.security.utils 
 * @author: chentm   
 * @date: 2019年6月24日 下午5:53:29 
 */
package com.ehs.security.utils;

import java.util.List;



/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: TreeNode.java
* @Description: 树结构数据封装类
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年6月24日 下午5:53:29 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年6月24日      chentm          v1.0.0               修改原因
*/
public class TextNode {

	
	private String id;
	
	private String pid;
	
	/**
	 * 文本，同name，非lazy设置此项
	 */
	private String text;
	
	private String attribute1;
	
	private String attribute2;
	
	private String attribute3;
	
	private String attribute4;
	
	private String attribute5;
	
	


	/**
	 * @return the pid
	 */
	public String getPid() {
		return pid;
	}


	/**
	 * @param pid the pid to set
	 */
	public void setPid(String pid) {
		this.pid = pid;
	}


	private List<TextNode> children;


	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}


	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}


	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}


	/**
	 * @return the attribute1
	 */
	public String getAttribute1() {
		return attribute1;
	}


	/**
	 * @param attribute1 the attribute1 to set
	 */
	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}


	/**
	 * @return the attribute2
	 */
	public String getAttribute2() {
		return attribute2;
	}

	/**
	 * @param attribute2 the attribute2 to set
	 */
	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}


	/**
	 * @return the attribute3
	 */
	public String getAttribute3() {
		return attribute3;
	}


	/**
	 * @param attribute3 the attribute3 to set
	 */
	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}


	/**
	 * @return the attribute4
	 */
	public String getAttribute4() {
		return attribute4;
	}


	/**
	 * @param attribute4 the attribute4 to set
	 */
	public void setAttribute4(String attribute4) {
		this.attribute4 = attribute4;
	}


	/**
	 * @return the attribute5
	 */
	public String getAttribute5() {
		return attribute5;
	}


	/**
	 * @param attribute5 the attribute5 to set
	 */
	public void setAttribute5(String attribute5) {
		this.attribute5 = attribute5;
	}


	/**
	 * @return the children
	 */
	public List<TextNode> getChildren() {
		return children;
	}


	/**
	 * @param children the children to set
	 */
	public void setChildren(List<TextNode> children) {
		this.children = children;
	}
	
	
	
	
}
