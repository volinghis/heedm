/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.security.entity 
 * @author: chentm   
 * @date: 2019年6月20日 上午10:45:52 
 */
package com.ehs.security.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: UserMenu.java
* @Description: 用户菜单视图
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年6月20日 上午10:45:52 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年6月20日      chentm          v1.0.0               修改原因
*/
@Entity
@Table(name="VIEW_USER_MENU")
public class UserMenu implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String ACCOUNT="account";
	public static final String MENU_CODE="menuCode";
	public static final String MENU_NAME="menuName";
	public static final String MENU_SORT="menuSort";
	public static final String MENU_URL="menuUrl";
	public static final String MENU_PARENT_CODE="menuParentCode";
	
	@Id
	@Column(name = "ACCOUNT")
	private String account;
	
	@Id
	@Column(name = "MENU_CODE")
	private String menuCode;
	
	@Column(name = "MENU_NAME")
	private String menuName;
	
	@Column(name = "MENU_SORT")
	private Integer menuSort;
	
	@Column(name = "MENU_URL")
	private String menuUrl;
	
	@Column(name = "MENU_PARENT_CODE")
	private String menuParentCode;

	private String menuIcon;
	
	
	
	
	/**
	 * @return the menuIcon
	 */
	public String getMenuIcon() {
		return menuIcon;
	}

	/**
	 * @param menuIcon the menuIcon to set
	 */
	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	/**
	 * @return the account
	 */
	public String getAccount() {
		return account;
	}

	/**
	 * @param account the account to set
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * @return the menuCode
	 */
	public String getMenuCode() {
		return menuCode;
	}

	/**
	 * @param menuCode the menuCode to set
	 */
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	/**
	 * @return the menuName
	 */
	public String getMenuName() {
		return menuName;
	}

	/**
	 * @param menuName the menuName to set
	 */
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	/**
	 * @return the menuSort
	 */
	public Integer getMenuSort() {
		return menuSort;
	}

	/**
	 * @param menuSort the menuSort to set
	 */
	public void setMenuSort(Integer menuSort) {
		this.menuSort = menuSort;
	}

	/**
	 * @return the menuUrl
	 */
	public String getMenuUrl() {
		return menuUrl;
	}

	/**
	 * @param menuUrl the menuUrl to set
	 */
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	/**
	 * @return the menuParentCode
	 */
	public String getMenuParentCode() {
		return menuParentCode;
	}

	/**
	 * @param menuParentCode the menuParentCode to set
	 */
	public void setMenuParentCode(String menuParentCode) {
		this.menuParentCode = menuParentCode;
	}


	
	
	
	
	
	
	
}
