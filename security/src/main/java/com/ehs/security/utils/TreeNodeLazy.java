/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.security.utils 
 * @author: chentm   
 * @date: 2019年6月26日 下午6:14:10 
 */
package com.ehs.security.utils;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: TreeNodeLazy.java
* @Description: 懒加载树数据封装类
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年6月26日 下午6:14:10 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年6月26日      chentm          v1.0.0               修改原因
*/
public class TreeNodeLazy {

private String id;
	
	private String pid;
	
	/**
	 * 文本。同text，lazy设置此项
	 */
	private String name;
	
	private String attribute1;
	
	private String attribute2;
	
	private String attribute3;
	
	private String attribute4;
	
	private String attribute5;
	
	
	private boolean isLeaf;

	private boolean expanded;

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


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return the isLeaf
	 */
	public boolean getIsLeaf() {
		return isLeaf;
	}


	/**
	 * @param isLeaf the isLeaf to set
	 */
	public void setIsLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	/**
	 * @return the expanded 
	 */
	public boolean getExpanded() {
		return expanded;
	}
	
	/**
	 * @param expanded the expanded to set
	 */
	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}
	
	
	
}
