package com.example.demo.dao.click;


import com.example.demo.entity.exam.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
@Component
public interface ExamDetailDao {

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    Boolean uploadSelect(List<Select> list);

    Boolean uploadBlank(List<Blank> list);

    Boolean uploadJudge(List<Judge> list);

    Boolean uploadEssay(List<Essay> list);



    Integer getSelectNum(ExamTest examTest);

    Integer getBlankNum(ExamTest examTest);

    Integer getJudgeNum(ExamTest examTest);

    Integer getEssayNum(ExamTest examTest);


    List<Exam> getExam(Exam exam);

    List<Exam> getExamDepartment(Exam exam);

    List<Select> getSelect(Select select);

    List<Blank> getBlank(Blank blank);

    List<Judge> getJudge(Judge judge);

    List<Essay> getEssay(Essay essay);

    Boolean deleteBlank(@Param("id") String id);

    List<Select> getSelectByList(List<String> select);


    List<Blank> getBlankByList(List<String> blank);

    List<Judge> getJudgeByList(List<String> judge);

    List<Essay> getEssayByList(List<String> essay);
}
