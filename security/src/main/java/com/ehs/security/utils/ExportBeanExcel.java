package com.ehs.security.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: ExportBeanExcel.java
* @Description: 导出Excel
*
* @version: v1.0.0
* @author: zhaol
* @date: 2019年10月10日 下午4:05:40 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年10月10日     zhaol           v1.0.0               修改原因
*/
public class ExportBeanExcel {

	/**
	 * 这是一个通用的方法，利用了JAVA的反射机制，可以将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出
	 * <p>
	 * sheetName 表格sheet名称 headersName 表格属性列名数组 headersId
	 * 表格属性列名对应的字段---你需要导出的字段名（为了更灵活控制你想要导出的字段） dtoList
	 * 需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象 out 与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
	 * <p>
	 * dtoRow dtoList集合中每个对象记录所在行序号（从1开始，第0行是列标题栏） zdCell 列标题字段所在单元格的列序号
	 * 
	 * @throws IOException
	 */
	public static <T> void exportExcel(String sheetName, List<String> headersName, List<String> headersId,
			List<T> dtoList, String fname, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		OutputStream out = response.getOutputStream();
		response.reset();// 清空输出流
		response.setCharacterEncoding("UTF-8");// 设置相应内容的编码格式
		fname = java.net.URLEncoder.encode(fname, "UTF-8");
		response.setHeader("Content-Disposition","attachment;filename=" + new String(fname.getBytes("UTF-8"), "GBK") + ".xlsx");
		response.setContentType("application/ms-excel");// 定义输出类型

		/*
		 * （一）表头--标题栏
		 */
		Map<Integer, String> headersNameMap = new HashMap<>();
		int key = 0;
		for (int i = 0; i < headersName.size(); i++) {
			if (!headersName.get(i).equals(null)) {
				headersNameMap.put(key, headersName.get(i));
				key++;
			}
		}
		/*
		 * （二）字段---标题对应的字段
		 */
		Map<Integer, String> headersFieldMap = new HashMap<>();
		int value = 0;
		for (int i = 0; i < headersId.size(); i++) {
			if (!headersId.get(i).equals(null)) {
				headersFieldMap.put(value, headersId.get(i));
				value++;
			}
		}

		/*
		 * (三）声明一个工作薄：包括构建工作簿、表格、样式
		 */
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet(sheetName);
//		sheet.setDefaultColumnWidth(15);
		// 生成一个样式
		XSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);

		/*
		 * 合并第一行单元格标题
		 */
		XSSFRow row = sheet.createRow(0);
		XSSFCell cell;
		cell = row.createCell(0);
		cell.setCellValue(sheetName);
		cell.setCellStyle(style);
		wb.getSheet(sheetName).addMergedRegion(new CellRangeAddress(0, 0, 0, headersNameMap.size() - 1));
		// 创建列标题栏所在行
		row = sheet.createRow(1);
		Collection<String> c = headersNameMap.values();// 拿到表格所有列标题的value的集合
		Iterator<String> it = c.iterator();// 表格标题的迭代器
		/*
		 * （四）导出数据：包括导出标题栏以及内容栏
		 */
		// 根据选择的字段生成表头
		int size = 0;
		while (it.hasNext()) {
			cell = row.createCell(size);
			cell.setCellValue(it.next().toString());
			cell.setCellStyle(style);
			size++;
		}
		// 表格列标题一行对应的字段的集合
		Collection<String> headersFieldCo = headersFieldMap.values();
		Iterator<T> labIt = dtoList.iterator();// 总记录的迭代器
		int dtoRow = 2;// 内容栏 导出数据dtoList的行序号
		while (labIt.hasNext()) {// ****** 记录的迭代器，遍历总记录
			row = sheet.createRow(dtoRow);
			dtoRow++;
			T l = (T) labIt.next();
			//利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
			Field[] fields = l.getClass().getDeclaredFields();// 获得JavaBean全部属性
			 ArrayList<Field> fieldsList = new ArrayList<Field>(Arrays.asList(fields)) ;
			 if(l.getClass().getSuperclass() != null){//增加父类属性
                 Field[] superFields = l.getClass().getSuperclass().getDeclaredFields();
                 ArrayList<Field> surpList = new ArrayList<Field>(Arrays.asList(superFields)) ;
                 fieldsList.addAll(surpList);
             }

			for (short i = 0; i < fieldsList.size(); i++) {// ****** 遍历属性，比对
				Field field = fieldsList.get(i);
				String fieldName = field.getName();// 属性名
				Iterator<String> zdIt = headersFieldCo.iterator();// 一行列标题字段的集合的迭代器
				int zdCell = 0;
				while (zdIt.hasNext()) {// 遍历要导出的字段集合
					String dataClo=zdIt.next();
					if (dataClo.equals(fieldName)) {// 比对JavaBean的属性名，一致就写入，不一致就丢弃
						String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);// 拿到属性的get方法
						Class<?> tCls = l.getClass();// 拿到JavaBean对象
						Class<?> surpCls=l.getClass().getSuperclass();//拿到JavaBean 的父类对象
						Method[] surMethod=	surpCls.getMethods();
						for (Method method : surMethod) {
							if(method.getName().equals(getMethodName)) {
								getMethodName=method.getName();
							}
						}
						
						try {
							Method getMethod = tCls.getMethod(getMethodName, new Class[] {});// 通过JavaBean对象拿到该属性的get方法，从而进行操控
							Object val = getMethod.invoke(l, new Object[] {});// 操控该对象属性的get方法，从而拿到属性值
							String textVal = null;
							if (val != null) {
								if (val instanceof Timestamp) {// 格式化 日期
									val = new SimpleDateFormat("yyyy/MM/dd").format(val);
								}
								textVal = String.valueOf(val);// 转化成String
							} else {
								// 字段内容值为null时，进行处理
								textVal = "";
							}
							cell = row.createCell(zdCell);
							cell.setCellValue(textVal);// 写进excel对象
						} catch (Exception e) {
							e.printStackTrace();
						}
					} // if
					zdCell++;
				} // while
			} // for
		}
		// 设置自适应
		for (int j = 0; j < headersNameMap.size(); j++) {
			sheet.autoSizeColumn(j);
			sheet.setColumnWidth(j, sheet.getColumnWidth(j) * 16 / 10);
		}
		try {
			// 写入数据
			wb.write(out);
			// 关闭文件
			wb.close();
			out.close();
		} catch (Exception e) {
			// System.out.println("---出现异常---");
			e.printStackTrace();
		}
	}
}