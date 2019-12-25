package com.ehs.edm.util;

import java.io.*;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.RequestContextHolder;

import com.alibaba.fastjson.JSON;
import com.ehs.security.execute.ResultBean;
import com.ehs.security.execute.ResultType;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class ExportBeanWord {

	private Configuration configuration = null;  
	  
	   //构造函数生成实例并设置编码
	   public ExportBeanWord() {
	      configuration = new Configuration();  
	      configuration.setDefaultEncoding("utf-8");  
	   }
	  
	  /**
	   * 导出word文档，导出到本地
	   * @param tempName，要使用的模板
	   * @param docName，导出文档名称
	   * @param dataMap，模板中变量数据
	   * @param outFile，输出文档路径
	   */
//	   public boolean exportDoc(String tempName,Map<?, ?> dataMap,File outFile) {  
//		   boolean status = false;
//	      // 设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以重servlet，classpath，数据库装载，  
//		  configuration.setClassForTemplateLoading(this.getClass(), "/com/ehs/edm/edmLibraryManager/edmAccountPrint/controller");
//	      Template t = null;  
//	  
//	      try{  
//	         // tempName.ftl为要装载的模板  
//	         t = configuration.getTemplate(tempName+".ftl");  
//	         t.setEncoding("utf-8");  
//	      } catch (IOException e) {
//	         e.printStackTrace();  
//	      }
//	  
//	      Writer out = null;  
//	      try{  
//	         out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf-8"));
//	         status = true;
//	      }catch(Exception e1) {  
//	         e1.printStackTrace();  
//	      }  
//	  
//	      try{  
//	         t.process(dataMap, out);  
//	         out.close();  
//	      }catch(TemplateException e){  
//	         e.printStackTrace();  
//	      } catch (IOException e) {  
//	         e.printStackTrace();  
//	      }  
//	  
//	      return status;
//	   }  
	  
	   /**
	    * 导出word文档，响应到请求端
	    * @param tempName，要使用的模板
	    * @param docName，导出文档名称
	    * @param dataMap，模板中变量数据
	    * @param resp,HttpServletResponse
	    * @param templatePath,模板路径
	 * @throws IOException 
	    */
		public String exportDoc(String tempName, String docName, Map<?, ?> dataMap, HttpServletResponse resp,HttpServletRequest request) throws Exception {
			System.out.println("================进入导出方法=======================");
//			boolean status = false;
//			System.out.println("template========"+templatePath);
			ServletOutputStream sos = null;
			InputStream fin = null;
			if (resp != null) {
				resp.reset();
			}
			// 设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以重servlet，classpath，数据库装载。参数2为模板路径
//			configuration.setClassForTemplateLoading(this.getClass(), templatePath);
			configuration.setClassForTemplateLoading(this.getClass(), "/templates/template");
			Template t = null;
			try {
				// tempName.ftl为要装载的模板
				t = configuration.getTemplate(tempName + ".ftl");
				t.setEncoding("utf-8");
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("===========准备导出临时文件================");
			// 输出文档路径及名称 ,以临时文件的形式导出服务器，再进行下载
			String name = "temp" + (int) (Math.random() * 100000) + ".doc";
			File outFile = new File(name);
			Writer out = null;
			try {
				out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf-8"));
//				status = true;
			} catch (Exception e1) {
				e1.printStackTrace();
			}
	 
			try {
				t.process(dataMap, out);
				out.close();
			} catch (TemplateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	 
			try {
				fin = new FileInputStream(outFile);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			// 文档下载
			System.out.println("===================准备下载文件================");
			resp.setCharacterEncoding("utf-8");
			resp.setContentType("application/msword");
			try {
				 //处理中文文件名乱码
		        if (request.getHeader("User-Agent").toUpperCase().contains("MSIE") ||
		                request.getHeader("User-Agent").toUpperCase().contains("TRIDENT")
		                || request.getHeader("User-Agent").toUpperCase().contains("EDGE")) {
		            try {
		            	docName = java.net.URLEncoder.encode(docName, "UTF-8");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
		        } else {
		            //非IE浏览器的处理：
		            try {
		            	docName = new String(docName.getBytes("UTF-8"), "ISO-8859-1");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
		        }
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			resp.setHeader("Content-disposition", "attachment;filename=" + docName + ".doc");
			try {
				sos = resp.getOutputStream();
			} catch (IOException e) {
				e.printStackTrace();
			}
			byte[] buffer = new byte[512]; // 缓冲区
			int bytesToRead = -1;
			// 通过循环将读入的Word文件的内容输出到浏览器中
			try {
				while ((bytesToRead = fin.read(buffer)) != -1) {
					sos.write(buffer, 0, bytesToRead);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (fin != null) fin.close();
				if (sos != null) sos.close();
				if (outFile != null) outFile.delete(); // 删除临时文件
			}
			System.out.println("====================生成word成功======================");
			  return JSON.toJSONString(ResultBean.getBean("导出成功！", ResultType.OK, null));
		}
}
