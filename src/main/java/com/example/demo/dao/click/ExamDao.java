package com.example.demo.dao.click;


import com.example.demo.entity.exam.*;
import com.example.demo.entity.roles.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Mapper
@Component
public interface ExamDao {


    Boolean uploadSelect(List<Select> list);

    Boolean uploadBlank(List<Blank> list);

    Boolean uploadJudge(List<Judge> list);

    Boolean uploadEssay(List<Essay> list);



    Integer getSelectNum(Teacher teacher);

    Integer getBlankNum(Teacher teacher);

    Integer getJudgeNum(Teacher teacher);

    Integer getEssayNum(Teacher teacher);


    List<Exam> getExam(Exam exam);

    List<Exam> getExamDepartment(Exam exam);
}
