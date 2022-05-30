package com.example.demo.service.teacher;

import com.example.demo.dao.master.ExamDao;
import com.example.demo.dao.master.TeacherDao;
import com.example.demo.entity.exam.Exam;
import com.example.demo.entity.roles.Teacher;
import com.example.demo.service.teacher.impl.ExamTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExamTeacherServiceImpl implements ExamTeacherService {

    @Autowired
    private ExamDao examDao;

    @Autowired
    private TeacherDao teacherDao;

    @Override
    public Object getTeacherExamAll(String userId) {
        Map res = new HashMap<>(4);

        Teacher user = teacherDao.getById(userId);

        List<Exam> teacherExamAll = examDao.getTeacherExamAll(userId);

        List<Exam> teacherExamRelative = examDao.getTeacherExamRelative(user);

        teacherExamRelative.removeAll(teacherExamAll);

        res.put("self", teacherExamAll);
        res.put("other", teacherExamRelative);

        return res;
    }

    @Override
    public Boolean createExam(Exam exam) {

        String examId = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmm"));
        exam.setId(Integer.valueOf(examId));
        exam.setCreateTime(LocalDateTime.now());

        exam.setDuration((int) Duration.between(exam.getEndTime(), exam.getStartTime()).toMinutes());

        return examDao.createExam(exam);
    }

    @Override
    public Object sendExam(Integer examId) {

        Exam exam = examDao.getExamByExamId(examId);

        List list = new ArrayList(11);

        if (exam.getElective() != null && "".equals(exam.getElective())){


        }


            return null;

    }
}
