package com.project.project1.verify;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

@Controller
@RequestMapping
public class Image {

    @RequestMapping("/getimg")
   public void getVerifacationCode(HttpServletResponse response,HttpServletRequest request){

       try {
           int width = 200;
           int height = 69;

           //生成对应大小的图片
           BufferedImage verifyImg = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

           String randomText = VerifyCode.drawRandomText(width,height,verifyImg);

           request.getSession().setAttribute("verifyCode", randomText);
           //必须设置响应内容类型为图片，否则前台不识别
           response.setContentType("image/png");
           //获取文件输出流
           OutputStream os = response.getOutputStream();
           //输出图片流
           ImageIO.write(verifyImg,"png",os);
           os.flush();
           os.close();//关闭流
       } catch (IOException e) {
           System.out.println(e.getMessage());
           e.printStackTrace();
       }

   }



}
