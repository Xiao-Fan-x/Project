package com.example.demo.entity.exam;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Builder
public class Exam {

    private Integer id;  //'id试卷编号 使用时间编写 yyyyMMddHHmm'

    private String examName;
    private LocalDateTime createTime;
    private String createPeople;
    private String status;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer duration;  //时长min
    private String grade;
    private String department;
    private String major;
    private String subject;
    private String className;

    private String elective;

    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", examName='" + examName + '\'' +
                ", createTime=" + createTime +
                ", createPeople='" + createPeople + '\'' +
                ", status='" + status + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", duration=" + duration +
                ", grade='" + grade + '\'' +
                ", department='" + department + '\'' +
                ", major='" + major + '\'' +
                ", subject='" + subject + '\'' +
                ", className='" + className + '\'' +
                ", elective='" + elective + '\'' +
                '}';
    }
//    private List<Select> selectList;
//    private List<Blank> blankList;
//    private List<Judge> judgeList;
//    private List<Essay> essayList;

}
