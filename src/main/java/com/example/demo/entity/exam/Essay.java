package com.example.demo.entity.exam;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Essay extends ExamTest implements Serializable {

    private String id;
    private String detail;
    private String pointOne;
    private String pointTwo;
    private String pointThree;
    private String pointFour;

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
        return "Essay{" +
                "id='" + id + '\'' +
                ", detail='" + detail + '\'' +
                ", pointOne='" + pointOne + '\'' +
                ", pointTwo='" + pointTwo + '\'' +
                ", pointThree='" + pointThree + '\'' +
                ", pointFour='" + pointFour + '\'' +
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
