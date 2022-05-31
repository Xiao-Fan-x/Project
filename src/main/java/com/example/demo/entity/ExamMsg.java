package com.example.demo.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "examMsg", autoResultMap = true)
public class ExamMsg {

    private Integer examId;

    //    @TableField(typeHandler = FastjsonTypeHandler.class)
//    private List<Select> selectMsg;
//
//    @TableField(typeHandler = FastjsonTypeHandler.class)
//    private List<Blank> blankMsg;
//
//    @TableField(typeHandler = FastjsonTypeHandler.class)
//    private List<Judge> judgeMsg;
//
//    @TableField(typeHandler = FastjsonTypeHandler.class)
//    private List<Essay> essayMsg;
    private String selectMsg;
    private String blankMsg;
    private String judgeMsg;
    private String essayMsg;

    @Override
    public String toString() {
        return "ExamMsg{" +
                "examId=" + examId +
                ", selectMsg='" + selectMsg + '\'' +
                ", blankMsg='" + blankMsg + '\'' +
                ", judgeMsg='" + judgeMsg + '\'' +
                ", essayMsg='" + essayMsg + '\'' +
                '}';
    }

}
