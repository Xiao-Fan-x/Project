package com.example.demo.entity;


import com.alibaba.fastjson.JSON;
import lombok.Data;

@Data
public class ExamMsg {

    private Integer examId;

    private JSON selectMsg;

    private JSON blankMsg;

    private JSON judgeMsg;

    private JSON essayMsg;

    @Override
    public String toString() {
        return "ExamMsg{" +
                "examId=" + examId +
                ", selectMsg=" + selectMsg +
                ", blankMsg=" + blankMsg +
                ", judgeMsg=" + judgeMsg +
                ", essayMsg=" + essayMsg +
                '}';
    }
}
