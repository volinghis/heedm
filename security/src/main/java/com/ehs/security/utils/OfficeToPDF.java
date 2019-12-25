/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.security.utils 
 * @author: qjj   
 * @date: 2019年9月5日 上午9:25:29 
 */
package com.ehs.security.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import com.aspose.cells.PdfSaveOptions;
import com.aspose.cells.Workbook;
import com.aspose.words.Document;
import com.aspose.words.License;
import com.aspose.words.SaveFormat;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: WordToPDF.java
* @Description: MS office文档转换为PDF
*
* @version: v1.0.0
* @author: qjj
* @date: 2019年9月5日 上午9:25:29 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年9月5日     qjj           v1.0.0               修改原因
*/
public class OfficeToPDF {
 
	public boolean getLicense() throws Exception {
		boolean result = false;
		try {
			InputStream is =this.getClass().getResourceAsStream("/templates/license.xml");
			License aposeLic = new License();
			aposeLic.setLicense(is);
			result = true;
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}
 
	// ==========暂时无用=============
	public static void excelTopdf(InputStream source, OutputStream os) throws Exception {

		OfficeToPDF d=new OfficeToPDF();
		boolean flag = false;
		File file = null;
		try
		{
			// 验证License
			if (!d.getLicense())
			{
				return;
			}

			long old = System.currentTimeMillis();
			// InPath是将要被转化的文档
			Workbook wb = new Workbook(source);
			PdfSaveOptions option = new PdfSaveOptions();
			option.setOnePagePerSheet(true);
			wb.save(os, option);
			flag = true;
			long now = System.currentTimeMillis();
			System.out.println("共耗时：" + ((now - old) / 1000.0) + "秒"); // 转化用时
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (os != null)
				{
					os.close();
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			if (!flag)
			{
				file.deleteOnExit();
			}
		}
	}
	// ==========暂时无用=============
	/**
	 * 
	* @Function: OfficeToPDF.java
	* @Description: office转pdf
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年10月10日 下午4:07:51 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年10月10日     zhaol           v1.0.0               修改原因
	 */
	public static void docTopdf(InputStream source, OutputStream os) throws Exception {
		OfficeToPDF d=new OfficeToPDF();
		System.out.println("=======================进入docToPdf方法===================");
		if (!d.getLicense()) { // 验证License 若不验证则转化出的pdf文档有水印
			throw new Exception("com.aspose.words lic ERROR!");
		}
		try {
			long old = System.currentTimeMillis();
			Document doc = new Document(source); // word文档
			// 支持RTF HTML,OpenDocument, PDF,EPUB, XPS转换
			doc.save(os, SaveFormat.PDF);
			long now = System.currentTimeMillis();
			System.out.println("convert OK! " + ((now - old) / 1000.0) + "秒");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    public static void main(String[] args) {
        //String filepath = "D:\\jiudian.doc";  
        String filepath = "D:\\ccc.xls";  
        String outpath = "D:\\ps.pdf";   
           
        InputStream source;
		OutputStream target;
		try {
			source = new FileInputStream(filepath);  
			target = new FileOutputStream(outpath);
			excelTopdf(source, target);
		    //docTopdf(source,target);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
 
	}

}
