package com.example.demo.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExamHistory {

    private Integer id;

    private Integer examId;

    private String userId;

    private Double score;

    private String selectMsg;

    private String blankMsg;

    private String judgeMsg;

    private String essayMsg;

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
