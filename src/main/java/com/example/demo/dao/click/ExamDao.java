package com.example.demo.dao.click;


import com.example.demo.entity.exam.Blank;
import com.example.demo.entity.exam.Essay;
import com.example.demo.entity.exam.Judge;
import com.example.demo.entity.exam.Select;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ExamDao {


    Boolean uploadSelect(List<Select> list);

    Boolean uploadBlank(List<Blank> list);

    Boolean uploadJudge(List<Judge> list);

    Boolean uploadEssay(List<Essay> list);

}
