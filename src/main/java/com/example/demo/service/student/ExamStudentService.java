package com.example.demo.service.student;


import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

@Service
public interface ExamStudentService {

    Object getExam(String userId);

    Object getExamAll(String userId);

    Object getExamMsg(Integer examId);

    Boolean endExam(JSONObject jsonObject);
}
