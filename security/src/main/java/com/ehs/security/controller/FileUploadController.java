/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.security.controller 
 * @author: chentm   
 * @date: 2019年6月27日 上午8:47:00 
 */
package com.ehs.security.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.ehs.security.entity.FileInfo;
import com.ehs.security.execute.ResultBean;
import com.ehs.security.execute.ResultType;
import com.ehs.security.service.FileInfoService;
import com.ehs.security.utils.OfficeToPDF;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.model.GridFSFile;

/**
 * Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
 * 
 * @ClassName: FileUploadController.java
 * @Description: 该类的功能描述
 *
 * @version: v1.0.0
 * @author: chentm
 * @date: 2019年6月27日 上午8:47:00
 *
 *        Modification History: Date Author Version Description
 *        ---------------------------------------------------------* 2019年6月27日
 *        chentm v1.0.0 修改原因
 */
@Controller
public class FileUploadController {

	private static final Logger logger=LoggerFactory.getLogger(FileUploadController.class);
	
	@Resource
	private FileInfoService fileInfoService;
	
	@Resource
	private GridFsTemplate gridFsTemplate;
	@Resource
 	private MongoDbFactory mongoDbFactory;
	
	/**
	 * 
	* @Function:upload 
	* @Description: 文件上传
	* @param file 文件对象
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年10月10日 下午2:42:46 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2019年10月10日     qjj        v1.0.0            修改原因
	 */
	@RequestMapping("/action/fileUpload")
	@ResponseBody
	public String upload(@RequestParam("Fdata") MultipartFile file) {
		if (file.isEmpty()) {
			return JSON.toJSONString(ResultBean.getBean("上传失败！",ResultType.ERROR,null));

		}
		ObjectId objectId = ObjectId.get();
		try {
			objectId = gridFsTemplate.store(file.getInputStream(), file.getOriginalFilename());
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(objectId.toString());
		return JSON.toJSONString(ResultBean.getBean("上传成功！",ResultType.OK,objectId.toString()));
	}
	
	
	/**
	 * 
	* @Function:fileRemove 
	* @Description: 删除文件
	* @param fileId 文件的唯一标识
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年10月10日 下午2:50:48 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2019年10月10日     qjj        v1.0.0            修改原因
	 */
	@RequestMapping("/action/fileRemove")
	@ResponseBody
	public String fileRemove(@RequestParam("fileId") String fileId) {
		try {
			gridFsTemplate.delete(Query.query(Criteria.where("_id").is(fileId)));
			return JSON.toJSONString(ResultBean.getBean("删除成功！",ResultType.OK,null));
		}catch (Exception e) {
			logger.error("删除失败！",e.getMessage());
			return JSON.toJSONString(ResultBean.getBean("删除失败！",ResultType.ERROR,null));
		}

	}
	
	
	/**
	 * 
	* @Function:fileLists 
	* @Description: 根据实体code查找所有相关文件
	* @param entityDataCode 关联实体的唯一标识
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年10月10日 下午2:51:30 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2019年10月10日     qjj        v1.0.0            修改原因
	 */
	@RequestMapping("/action/fileLists")
	@ResponseBody
	public String fileLists(@RequestParam("dataCode") String entityDataCode) {
		List<FileInfo> fileInfos=fileInfoService.getFiles(entityDataCode);
		return JSON.toJSONString(fileInfos);
	}
	
	
	/**
	 * 
	* @Function:downloadFile 
	* @Description: 文件下载
	* @param fileId 文件的唯一标识
	* @param request
	* @param response
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年10月10日 下午2:52:38 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2019年10月10日     qjj        v1.0.0            修改原因
	 */
	@RequestMapping("/action/downloadFile")
	public void downloadFile(@RequestParam("fileId") String fileId,HttpServletRequest request,
            HttpServletResponse response) {
		GridFSFile gridFSFile = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(fileId)));
		if(gridFSFile==null) {
			return ;
		}
		GridFsResource gridFsResource=new GridFsResource(gridFSFile,GridFSBuckets.create(mongoDbFactory.getDb()).openDownloadStream(gridFSFile.getObjectId()));
        String fileName = gridFSFile.getFilename().replace(",", "");
        //处理中文文件名乱码
        if (request.getHeader("User-Agent").toUpperCase().contains("MSIE") ||
                request.getHeader("User-Agent").toUpperCase().contains("TRIDENT")
                || request.getHeader("User-Agent").toUpperCase().contains("EDGE")) {
            try {
				fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
        } else {
            //非IE浏览器的处理：
            try {
				fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
        }
        response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
        try {
			IOUtils.copy(gridFsResource.getInputStream(),response.getOutputStream());
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * 
	* @Function:createPDF 
	* @Description: PDF预览
	* @param fileId 文件的唯一标识
	* @param response
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年9月5日 下午2:36:54 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2019年9月5日     qjj        v1.0.0            修改原因
	 */
	@RequestMapping("/action/viewFile")
	public void viewByPDF(String fileId,HttpServletResponse response) {
	  	  GridFSFile gridFSFile = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(fileId)));
	  	  if(gridFSFile==null) {
	  		  return ;
	  	  }
	  	  String fileName = gridFSFile.getFilename().replace(",", "");
	  	  String subFileName=fileName.substring(fileName.indexOf(".")+1);
	  	  String outFileName=fileName.substring(0,fileName.indexOf("."))+".pdf";
	  	  try {
	  		  OutputStream out = response.getOutputStream();
				if(gridFSFile!=null) {
					GridFsResource gridFsResource=new GridFsResource(gridFSFile,GridFSBuckets.create(mongoDbFactory.getDb()).openDownloadStream(gridFSFile.getObjectId()));
					 InputStream in=gridFsResource.getInputStream();
				  	  if("docx".equals(subFileName)||"doc".equals(subFileName)) {//判断文件类型
				  		  OfficeToPDF.docTopdf(in, out);
				  	  }else if("xls".equals(subFileName)||"xlsx".equals(subFileName)){
				  		  OfficeToPDF.excelTopdf(in, out);
				  	  }else{
				  		  IOUtils.copy(in,out);
				  	  }
				  	  response.setHeader("Content-Disposition", "inline;filename=\"" + outFileName + "\"");
				}
				out.close();
	  	  	} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}  
	}
	
}
