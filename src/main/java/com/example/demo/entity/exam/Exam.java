package com.example.demo.entity.exam;

import lombok.Data;

import java.util.List;


@Data
public class Exam {

    private String id;

    private List<Select> selectList;

    private List<Blank> blankList;

    private List<Judge> judgeList;

    private List<Essay> essayList;


}
