package com.example.demo.entity.roles;


import java.util.List;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Student extends User {

    private String userId;//工号
    //    @NotNull
    private String userName;    //姓名

    private String gender;
    //    @NotNull
    private String password;
    //    @NotNull
    private String phone;

    private LocalDateTime registTime;  //登记时间

    private String role;        //身份 student

    private List<String> roles;        //身份 student
    //    @NotNull
    private String department;  //系
    //    @NotNull
    private String major;       //专业
    //    @NotNull
    private String className;   //班级
    //    @NotNull
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
