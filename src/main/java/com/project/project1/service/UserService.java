package com.project.project1.service;

import com.project.project1.entity.User_s;
import org.apache.ibatis.annotations.Param;

public interface UserService {

    String login(@Param("studentId") String studentId);

    String getRealName(String studentId,String password);

    String getRealNameById(String studentId);

    void register(User_s user_s);

    void inputByUserS(User_s user_s);

}
