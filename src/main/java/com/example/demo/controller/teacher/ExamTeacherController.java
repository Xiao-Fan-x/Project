package com.example.demo.controller.teacher;


import com.example.demo.entity.exam.Exam;
import com.example.demo.service.teacher.impl.ExamTeacherService;
import com.example.demo.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/examTeacher")
public class ExamTeacherController {

    @Autowired
    private ExamTeacherService examTeacherService;

    @GetMapping("/{userId}")
    public Object getExamAll(@PathVariable String userId) {
        try {
            return Result.ResultSuccess(examTeacherService.getTeacherExamAll(userId));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/create")
    public Object create(@RequestBody Exam exam) {

        try {
            if (examTeacherService.createExam(exam)) {
                return Result.ResultSuccess(true);
            }
            return Result.ResultErr(false, "创建考核失败！");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/send/{examId}")
    public Object sendExam(@PathVariable Integer examId) {

        try {
            return Result.ResultSuccess(examTeacherService.sendExam(examId));
        } catch (Exception e) {
            e.printStackTrace();
            return  Result.ResultErr(e,"");
        }
    }


}
