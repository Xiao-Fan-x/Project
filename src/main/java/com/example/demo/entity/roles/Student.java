package com.example.demo.entity.roles;


import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Student extends User {

    private String userId;//工号
    private String userName;    //姓名
    private String gender;
    private String password;
    private String phone;
    private LocalDateTime registTime;  //登记时间
    private String role;        //身份 student
    private List<String> roles;        //身份 student
    private String department;  //系
    private String major;       //专业
    private String className;   //班级
    private String grade;       //年级

    @Override
    public String toString() {
        return "Student{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", gender='" + gender + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", registTime=" + registTime +
                ", role='" + role + '\'' +
                ", roles=" + roles +
                ", department='" + department + '\'' +
                ", major='" + major + '\'' +
                ", className='" + className + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }
}
