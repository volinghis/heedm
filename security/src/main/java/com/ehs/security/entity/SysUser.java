package com.ehs.security.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;


/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: User.java
* @Description: 用户实体类
*
* @version: v1.0.0
* @author: zhaol
* @date: 2019年5月13日 下午3:57:42 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年5月13日     zhaol           v1.0.0               修改原因
*/
@Entity
@Table(name = "SYS_USER")
public class SysUser extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	public static final String ACCOUNT="account";
	public static final String PASSWORD="password";
	public static final String STATE="state";
	
	private String account;
	
	private String password;
	/**
	 * 0正常，1锁定
	 */
	private Integer state;

	
	
	
	

	public String getAccount() {
		return account;
	}


	public void setAccount(String account) {
		this.account = account;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Integer getState() {
		return state;
	}


	public void setState(Integer state) {
		this.state = state;
	}


	private static List<String> foreignClassesList=null;



	/** 
	* @see com.ehs.security.entity.BaseEntity#getForeignClasses()  
	* @Function: SysRole.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年6月4日 下午3:01:53 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年6月4日      chentm           v1.0.0               修改原因
	*/
	@Override
	public List<String> getForeignClasses() {
		return foreignClassesList;
	}


	static {
		foreignClassesList=new ArrayList<String>();
		foreignClassesList.add(CODE+","+SysUserRole.class.getName()+","+SysUserRole.SYS_USER_CODE);
	}
	
	

}
