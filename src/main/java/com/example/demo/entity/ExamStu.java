package com.example.demo.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExamStu {

    private Integer id;

    private String userId;

    private Integer examId;
    private String examName;
    private LocalDateTime createTime;

    private String createPeople;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private String grade;
    private String department;
    private String major;
    private String subject;
    private String className;
    private String elective;

    @Override
    public String toString() {
        return "ExamStu{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", examId=" + examId +
                ", examName='" + examName + '\'' +
                ", createTime=" + createTime +
                ", createPeople='" + createPeople + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", grade='" + grade + '\'' +
                ", department='" + department + '\'' +
                ", major='" + major + '\'' +
                ", subject='" + subject + '\'' +
                ", className='" + className + '\'' +
                ", elective='" + elective + '\'' +
                '}';
    }

}
