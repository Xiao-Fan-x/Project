package com.example.demo.dao.master;

import com.example.demo.entity.exam.Exam;
import com.example.demo.entity.roles.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ExamDao {

    List<Exam> getTeacherExamAll(@Param("userId") String userId);

    List<Exam> getTeacherExamRelative(Teacher teacher);

    Boolean createExam(Exam exam);

    Exam getExamByExamId(@Param("examId") Integer examId);
}
