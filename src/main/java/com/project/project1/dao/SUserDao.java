package com.project.project1.dao;


import com.project.project1.entity.User_s;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SUserDao {

    //返回密码
    String login(@Param("studentId") String studentId);

    String getRealName(String studentId,String password);

    String getRealameById(String studetnId);

    void register(User_s user_s);

    void inputByUserS(User_s user_s);





}
