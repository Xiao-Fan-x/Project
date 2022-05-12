package com.example.demo.entity.exam;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


@Data
@Builder
public class Exam {

    private String id;  //'id试卷编号 使用时间编写 yyyyMMddHHmm'

    private String examName;
    private LocalDateTime createTime;
    private String createPeople;
    private String status;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String duration;  //时长min
    private String grade;
    private String department;
    private String major;
    private String subject;
    private String className;

    private List<Select> selectList;
    private List<Blank> blankList;
    private List<Judge> judgeList;
    private List<Essay> essayList;

    @Override
    public String toString() {
        return "Exam{" +
                "id='" + id + '\'' +
                ", examName='" + examName + '\'' +
                ", createTime=" + createTime +
                ", createPeople='" + createPeople + '\'' +
                ", status='" + status + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", duration='" + duration + '\'' +
                ", grade='" + grade + '\'' +
                ", department='" + department + '\'' +
                ", major='" + major + '\'' +
                ", subject='" + subject + '\'' +
                ", className='" + className + '\'' +
                ", selectList=" + selectList +
                ", blankList=" + blankList +
                ", judgeList=" + judgeList +
                ", essayList=" + essayList +
                '}';
    }

}
