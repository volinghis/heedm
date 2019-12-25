/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.security.query 
 * @author: chentm   
 * @date: 2019年6月11日 上午11:03:19 
 */
package com.ehs.security.query;

import java.util.List;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: Pagenate.java
* @Description: 分页实体
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年6月11日 上午11:03:19 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年6月11日      chentm          v1.0.0               修改原因
*/
public class Pagenate {

	/**
	 * 当前页
	 */
	private int startNum;
	
	/**
	 * 每页显示条数
	 */
	private int pageSize;
	
	/**
	 * 总记录数
	 */
	private Long iTotalRecords;
	
	/**
	 * 总显示记录
	 */
	private Long iTotalDisplayRecords;
	
	/**
	 * 数据集合
	 */
	private List data;
	
	/**
	 * 总数
	 */
	private long total;
	
	/**
	 * 
	 */
	private int draw;

	/**
	 * @return the total
	 */
	public long getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(long total) {
		this.total = total;
	}

	/**
	 * @return the startNum
	 */
	public int getStartNum() {
		return startNum;
	}

	/**
	 * @param startNum the startNum to set
	 */
	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}

	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the iTotalRecords
	 */
	public Long getiTotalRecords() {
		return iTotalRecords;
	}

	/**
	 * @param iTotalRecords the iTotalRecords to set
	 */
	public void setiTotalRecords(Long iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}

	/**
	 * @return the iTotalDisplayRecords
	 */
	public Long getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	/**
	 * @param iTotalDisplayRecords the iTotalDisplayRecords to set
	 */
	public void setiTotalDisplayRecords(Long iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}

	/**
	 * @return the data
	 */
	public List getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(List data) {
		this.data = data;
	}

	/**
	 * @return the draw
	 */
	public int getDraw() {
		return draw;
	}

	/**
	 * @param draw the draw to set
	 */
	public void setDraw(int draw) {
		this.draw = draw;
	}
	
}
