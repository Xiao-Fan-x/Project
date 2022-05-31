package com.example.demo.service.teacher.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.exam.Blank;
import com.example.demo.entity.exam.Essay;
import com.example.demo.entity.exam.Judge;
import com.example.demo.entity.exam.Select;
import com.example.demo.entity.roles.Teacher;
import org.springframework.stereotype.Service;

@Service
public interface TeacherService {

     JSONObject initDash(Teacher teacher);

    Object getSelect(Select select);

    Object getBlank(Blank blank);

    Object getJudge(Judge judge);

    Object getEssay(Essay essay);

    Boolean deleteBlank(String id);
}
