package com.example.demo.controller.teacher;


import com.example.demo.dao.click.ExamDao;
import com.example.demo.entity.roles.Teacher;
import com.example.demo.entity.roles.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping("/teacherdash")
public class TeacherDashboard {

    @Autowired
    private ExamDao examDao;


    @RequestMapping("/init")
    public Object dashboard(@RequestBody Teacher teacher){
        Map res = new HashMap();

        //echarts
        return null;
    }
}
