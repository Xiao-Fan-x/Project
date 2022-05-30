package com.example.demo.service.teacher.impl;


import com.example.demo.entity.exam.Exam;
import org.springframework.stereotype.Service;

@Service
public interface ExamTeacherService {


    Object getTeacherExamAll(String userId);

    Boolean createExam(Exam exam);

    Object sendExam(Integer examId);

}
