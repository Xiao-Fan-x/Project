package com.example.demo.controller.student;


import com.example.demo.service.student.ExamStudentService;
import com.example.demo.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/examStudent")
public class ExamStudentController {


    @Autowired
    private ExamStudentService examStudentService;

    @GetMapping("/{userId}")
    public Object getExam(@PathVariable String userId) {

        try {
            return Result.ResultSuccess(examStudentService.getExam(userId));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ResultErr(e,"学生获取试卷失败！");
        }
    }

    @GetMapping("/all/{userId}")
    public Object getExamAll(@PathVariable String userId) {

        try {
            return Result.ResultSuccess(examStudentService.getExamAll(userId));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ResultErr(e,"学生获取试卷失败！");
        }
    }
}
