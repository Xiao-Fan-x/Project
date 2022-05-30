package com.example.demo.entity.exam;


import lombok.Data;

@Data
public class Blank {
    private String id;
    private String detail;
    private String answer;
    private String reply;

    private String grade;       //年级
    private String department;  //系
    private String major;       //专业
    private String subject;     //学科

    private String analysis;
    private Integer page;

    @Override
    public String toString() {
        return "Blank{" +
                "id='" + id + '\'' +
                ", detail='" + detail + '\'' +
                ", answer='" + answer + '\'' +
                ", reply='" + reply + '\'' +
                ", grade='" + grade + '\'' +
                ", department='" + department + '\'' +
                ", major='" + major + '\'' +
                ", subject='" + subject + '\'' +
                ", analysis='" + analysis + '\'' +
                ", page=" + page +
                '}';
    }
}
