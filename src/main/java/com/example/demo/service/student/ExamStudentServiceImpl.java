package com.example.demo.service.student;


import com.example.demo.dao.master.StudentDao;
import com.example.demo.entity.ExamStu;
import com.example.demo.entity.roles.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class ExamStudentServiceImpl implements ExamStudentService {

    @Autowired
    private StudentDao studentDao;

    @Override
    public Object getExam(String userId) {

        Student user = studentDao.getById(userId);

        ExamStu stu = ExamStu.builder()
                .userId(userId)
                .startTime(LocalDateTime.now().plusHours(-1))
                .build();

//        log.info(stu.toString());

        List<ExamStu> exam = studentDao.getExam(stu);

        return exam;
    }
}
