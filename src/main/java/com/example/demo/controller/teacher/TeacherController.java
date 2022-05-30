package com.example.demo.controller.teacher;


import com.example.demo.entity.exam.Select;
import com.example.demo.entity.roles.Teacher;
import com.example.demo.service.teacher.impl.TeacherService;
import com.example.demo.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @RequestMapping("/init")
    public Object dashboard(@RequestBody Teacher teacher) {
        try {
            return Result.ResultSuccess(teacherService.initDash(teacher));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ResultErr(null, "程序出错");
        }
    }

    @GetMapping("/select")
    public Object getSelect(@RequestBody Select select){

        try {
           return Result.ResultSuccess(teacherService.getSelect(select));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ResultErr(e,"教师获取选择题失败！");
        }

    }



}
