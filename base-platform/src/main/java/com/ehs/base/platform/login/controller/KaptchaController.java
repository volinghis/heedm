/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.base.platform.shiro.controller 
 * @author: chentm   
 * @date: 2019年5月13日 上午10:54:49 
 */
package com.ehs.base.platform.login.controller;

import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.google.code.kaptcha.Producer;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: KaptchaController.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年5月13日 上午10:54:49 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年5月13日      chentm          v1.0.0       修改原因
*/

@Controller
public class KaptchaController  {
	private static final Logger logger = LoggerFactory.getLogger(KaptchaController.class);
	
	@Resource
	private RedisTemplate<String, String> redisTemplate; 
	
    @Autowired
    private Producer captchaProducer;
    
    /**
     * 
    * @Function: KaptchaController.java
    * @Description: 设置浏览器属性，并将验证码输出到页面展示
    *
    * @param request
    * @param response
    * @throws Exception
    * @throws：异常描述
    *
    * @version: v1.0.0
    * @author: zhaol
    * @date: 2019年8月19日 上午10:14:42 
    *
    * Modification History:
    * Date         Author          Version            Description
    *---------------------------------------------------------*
    * 2019年8月19日     zhaol           v1.0.0        修改原因
     */
    @GetMapping("/img/getKaptchaImage")
    public void getKaptchaImage(HttpServletRequest request,HttpServletResponse response) throws Exception {
        String crsfToken=request.getParameter("crsfToken");
    	response.setDateHeader("Expires", 0);
        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        // Set standard HTTP/1.0 no-cache header.
        response.setHeader("Pragma", "no-cache");
        // return a jpeg
        response.setContentType("image/jpeg");
        // create the text for the image
        String capText = captchaProducer.createText();
        //将验证码存到session
        redisTemplate.opsForValue().set(crsfToken, capText, 3, TimeUnit.MINUTES);
        logger.info(capText);
        // create the image with the text
        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        // write the data out
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
    }
}