package com.example.demo.service.student;


import org.springframework.stereotype.Service;

@Service
public interface ExamStudentService {

    Object getExam(String userId);

    Object getExamAll(String userId);
}
