package com.project.project1.controller;

import com.project.project1.entity.User_s;
import com.project.project1.service.UserService;
import com.project.project1.utils.ExcelUtils;
import org.apache.catalina.User;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping
public class FileController {


    @Autowired
    private UserService userService;

    @GetMapping("/download")
    public void download(String fileName, HttpServletResponse response) throws IOException {
        //根据文件名去指定路径查
        String realpath = ResourceUtils.getURL("classpath:").getPath() + "static/download";
        //读取文件
        File file = new File(realpath,fileName);
        //获取文件输入流
        FileInputStream is = new FileInputStream(file);
        //attachment; 附件下载 inline 在线打开
        response.setHeader("content-disposition","attachment;fileName="+fileName);
        //获取响应输出流
        ServletOutputStream os = response.getOutputStream();
        //文件拷贝
        IOUtils.copy(is,os);

        //关流
        is.close();
        os.close();

    }


    @ResponseBody
    @PostMapping("/upload")
    public List<User_s> upload(MultipartFile aaa, HttpServletRequest request) throws IOException {

        System.out.println("文件上传");
        System.out.println("文件名："+aaa.getOriginalFilename());
        System.out.println("文件类型:"+aaa.getContentType());
        System.out.println("文件大小："+aaa.getSize());

        List<User_s> list = ExcelUtils.getListByExcel(aaa.getInputStream(),aaa.getOriginalFilename());

        return list;
//        //处理文件上穿
//        String realpath = ResourceUtils.getURL("classpath:").getPath()+"static/files";
//        System.out.println(realpath);
//        String dateDir = new SimpleDateFormat("YYYY-MM-dd").format(new Date());
//        File dir = new File(realpath,dateDir);
//        if(!dir.exists()) {
//            dir.mkdirs();
//        }
//        String newFileNamePrefix = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())+ UUID.randomUUID().toString();
//        String extension = FilenameUtils.getExtension(aaa.getOriginalFilename());
//        String newFileName= newFileNamePrefix + "." +extension;
//        aaa.transferTo(new File(dir,newFileName));
//
//
//        return "redirect:/upload.html";
    }

    @ResponseBody
    @PostMapping("/uploadstumsg")
    public List<User_s> uploadStuMsg(MultipartFile excel) throws IOException {
        List<User_s> lists = ExcelUtils.getListByExcel(excel.getInputStream(),excel.getOriginalFilename());
        for (User_s list : lists) {
            userService.inputByUserS(list);
        }
        return lists;
    }

}
