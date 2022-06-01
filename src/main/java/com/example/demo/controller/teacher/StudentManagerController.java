package com.example.demo.controller.teacher;


import com.example.demo.entity.roles.Student;
import com.example.demo.service.studentManager.impl.StudentManager;
import com.example.demo.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/studentManager")
public class StudentManagerController {

    @Autowired
    private StudentManager studentManager;

    @PostMapping("/search")
    public Object getStudentList(@RequestBody Student student) {

        return Result.ResultSuccess(studentManager.getStudentList(student));
    }

    @DeleteMapping("/student/{userId}")
    public Object deleteStudent(@PathVariable String userId) {

        try {
            return Result.ResultSuccess(studentManager.delete(userId));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/student/update")
    public Object putStudent(@RequestBody Student student) {

        log.info("=====================" + student.toString());
        try {
            return Result.ResultSuccess(studentManager.update(student));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

}
