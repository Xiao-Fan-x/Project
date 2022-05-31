package com.example.demo.entity.exam;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Exam {

    private Integer id;  //'id试卷编号 使用时间编写 yyyyMMddHHmm'

    private String examName;
    private LocalDateTime createTime;
    private String createPeople;
    private String status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;
    private Integer duration;  //时长min
    private String grade;
    private String department;
    private String major;
    private String subject;
    private String className;

    private String elective;
    private Integer page;

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
                ", page=" + page +
                '}';
    }
//    private List<Select> selectList;
//    private List<Blank> blankList;
//    private List<Judge> judgeList;
//    private List<Essay> essayList;

}
