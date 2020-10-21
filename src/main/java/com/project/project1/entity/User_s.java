package com.project.project1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class User_s {

    public User_s(String StudentId,String realName,String password){
        this.studentId = StudentId;
        this.realName = realName;
        this.password = password;
    }

    private int id;
    private String studentId;
    private String realName;
    private String password;
    //学院
    private String college;
    private String profession;
    private String classes;
    private String grade;
    private String gender;
    private String birth;

}
