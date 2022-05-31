package com.example.demo.entity.exam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamTest {

    private String grade;       //年级
    private String department;  //系
    private String major;       //专业
    private String subject;     //学科
}
