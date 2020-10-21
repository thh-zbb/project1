package com.project.project1.utils;

import com.project.project1.entity.User_s;
import com.project.project1.service.UserService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


/**
* excel 工具类
* */
public class ExcelUtils {

    private static Logger logger = LoggerFactory.getLogger(ExcelUtils.class);


    public static List<User_s> getListByExcel(InputStream in,String fileName) throws IOException {
        List<User_s> list = new ArrayList<>();
        //创建excel工作簿
        Workbook work = getWorkbook(in,fileName);
        if(null== work){
            //创建excel工作簿为空
        }
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;

        sheet = work.getSheetAt(0);
        System.out.println("行数"+sheet.getLastRowNum());
        for(int i = 1; i <= sheet.getLastRowNum(); i++){
            System.out.println("第"+i + "行："+sheet.getRow(i).getCell(0).getNumericCellValue());
            row = sheet.getRow(i);
            cell = row.getCell(0);
            User_s userS = new User_s();
            int sid = (int) row.getCell(0).getNumericCellValue();
            userS.setStudentId(String.valueOf(sid));
            userS.setPassword(String.valueOf(sid));
            userS.setRealName(row.getCell(1).getStringCellValue());
            userS.setGender(row.getCell(2).getStringCellValue());
            userS.setCollege(row.getCell(3).getStringCellValue());
            userS.setGrade(String.valueOf((int)(row.getCell(4).getNumericCellValue())));
            userS.setProfession(row.getCell(5).getStringCellValue());
            userS.setClasses(row.getCell(6).getStringCellValue());
            userS.setBirth(row.getCell(7).getStringCellValue());
            list.add(userS);
        }
        return list;
    }

    private static Workbook getWorkbook(InputStream in, String fileName) throws IOException {
        Workbook book = null;
        String filetype = fileName.substring(fileName.lastIndexOf("."));
        if(".xls".equals(filetype)){
            book = new HSSFWorkbook(in);
            System.out.println("格式：xls");
        }else if(".xlsx".equals(filetype)){
            book = new XSSFWorkbook(in);
            System.out.println("格式：xlsx");
        }else {
            //上传文件错误
        }
        return book;
    }

}
