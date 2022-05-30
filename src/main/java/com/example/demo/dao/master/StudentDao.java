package com.example.demo.dao.master;

import com.example.demo.entity.ExamStu;
import com.example.demo.entity.roles.Student;
import com.example.demo.entity.roles.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentDao {


    Student getByUserId(User user);

    Student getById(@Param("userId") String userId);

    Boolean register(Student student);

    Boolean uploadStudent(List<Student> students);

    Integer getStudentNum(Student student);


    List<ExamStu> getExam(ExamStu examStu);

    List<String> getStudentList(Student student);
}
