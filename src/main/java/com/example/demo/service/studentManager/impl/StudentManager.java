package com.example.demo.service.studentManager.impl;


import com.example.demo.entity.roles.Student;
import org.springframework.stereotype.Service;

@Service
public interface StudentManager {

    Object getStudentList(Student student);

    Boolean delete(String userId);

    Boolean update(Student student);
}
