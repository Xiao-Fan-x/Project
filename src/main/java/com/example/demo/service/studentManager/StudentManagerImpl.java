package com.example.demo.service.studentManager;


import com.example.demo.dao.master.StudentDao;
import com.example.demo.entity.roles.Student;
import com.example.demo.service.studentManager.impl.StudentManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentManagerImpl implements StudentManager {

    @Autowired
    private StudentDao studentDao;

    @Override
    public Object getStudentList(Student student) {
        Map res = new HashMap(4);
        int page = student.getPage();
        student.setPage((page - 1) * 10);
        List<Student> studentList = studentDao.getStudentList(student);
        Integer studentNum = studentDao.getStudentNum(student);

        res.put("list", studentList);
        res.put("total", studentNum);
        return res;
    }

    @Override
    public Boolean delete(String userId) {

        return studentDao.delete(userId);
    }

    @Override
    public Boolean update(Student student) {

        return studentDao.update(student);
    }
}
