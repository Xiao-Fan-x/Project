package com.example.demo.entity;


import com.alibaba.fastjson.JSON;
import lombok.Data;

@Data
public class ExamHistory {

    private Integer id;

    private Integer examId;

    private String userId;

    private Double score;

    private JSON selectMsg;

    private JSON blankMsg;

    private JSON judgeMsg;

    private JSON essayMsg;

    @Override
    public String toString() {
        return "ExamHistory{" +
                "id=" + id +
                ", examId=" + examId +
                ", userId='" + userId + '\'' +
                ", score=" + score +
                ", selectMsg=" + selectMsg +
                ", blankMsg=" + blankMsg +
                ", judgeMsg=" + judgeMsg +
                ", essayMsg=" + essayMsg +
                '}';
    }
}
