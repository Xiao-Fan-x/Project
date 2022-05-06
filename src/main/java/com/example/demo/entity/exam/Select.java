package com.example.demo.entity.exam;


import lombok.Data;

@Data
public class Select {

    private String id;
    private String detail;
    private String pointA;
    private String pointB;
    private String pointC;
    private String pointD;
    private String answer;
    private String reply;

    private String grade;       //年级
    private String department;  //系
    private String major;       //专业
    private String subject;     //学科

    private String analysis;


}