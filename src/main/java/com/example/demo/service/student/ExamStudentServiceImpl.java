package com.example.demo.service.student;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.dao.master.ExamDao;
import com.example.demo.dao.master.StudentDao;
import com.example.demo.entity.ExamHistory;
import com.example.demo.entity.ExamMsg;
import com.example.demo.entity.ExamStu;
import com.example.demo.entity.exam.Blank;
import com.example.demo.entity.exam.Essay;
import com.example.demo.entity.exam.Judge;
import com.example.demo.entity.exam.Select;
import com.example.demo.entity.roles.Student;
import com.example.demo.util.SimilarUtils;
import com.hankcs.hanlp.HanLP;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ExamStudentServiceImpl implements ExamStudentService {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private ExamDao examDao;

    @Override
    public Object getExam(String userId) {

        Student user = studentDao.getById(userId);

        ExamStu stu = ExamStu.builder()
                .userId(userId)
                .startTime(LocalDateTime.now().plusHours(-1))
                .build();

//        log.info(stu.toString());

        List<ExamStu> exam = studentDao.getExam(stu);

        return exam;
    }

    @Override
    public Object getExamAll(String userId) {

        Student user = studentDao.getById(userId);

        ExamStu stu = ExamStu.builder()
                .userId(userId)
                .build();

//        log.info(stu.toString());

        List<ExamStu> exam = studentDao.getExam(stu);

        return exam;
    }

    @Override
    public Object getExamMsg(Integer examId) {

        ExamMsg examMsg = studentDao.getExamMsg(examId);

//        String parse = JSON.parse(examMsg.getSelectMsg());

//        log.info(examMsg.toString());

        List<Select> selects = JSON.parseArray(examMsg.getSelectMsg(), Select.class);
        List<Blank> blanks = JSON.parseArray(examMsg.getBlankMsg(), Blank.class);
        List<Judge> judges = JSON.parseArray(examMsg.getJudgeMsg(), Judge.class);
        List<Essay> essays = JSON.parseArray(examMsg.getEssayMsg(), Essay.class);

        Map res = new HashMap(10);

        res.put("select", selects);
        res.put("blank", blanks);
        res.put("judge", judges);
        res.put("essay", essays);

        return res;
    }

    @Override
    public Boolean endExam(JSONObject jsonObject) {
        System.out.println(jsonObject.toString());

        Integer examId = jsonObject.getObject("examId", Integer.class);
        List<String> selects = (List<String>) jsonObject.get("selectAnswer");
        List<String> blanks = (List<String>) jsonObject.get("blankAnswer");
        List<String> judges = (List<String>) jsonObject.get("judgeAnswer");
        List<String> essays = (List<String>) jsonObject.get("essayAnswer");

        ExamMsg examMsg = studentDao.getExamMsg(examId);

        double score = 0.0;
        int selectNum = 0;
        int blankNum = 0;
        int judgeNum = 0;
        int essayNum = 0;

        List<Select> selectList = JSON.parseArray(examMsg.getSelectMsg(), Select.class);
        List<Blank> blankList = JSON.parseArray(examMsg.getBlankMsg(), Blank.class);
        List<Judge> judgeList = JSON.parseArray(examMsg.getJudgeMsg(), Judge.class);
        List<Essay> essayList = JSON.parseArray(examMsg.getEssayMsg(), Essay.class);

        for (int i = 0; i < selectList.size(); i++) {
            String ans1 = selectList.get(i).getAnswer();
            String ans2 = selects.get(i);

            if (ans1.equals(ans2)) {
                score += 3;
            }
        }
        for (int i = 0; i < blankList.size(); i++) {
            if (blankList.get(i).getAnswer().equals(blanks.get(i))) {
                score += 2;
            }
        }
        for (int i = 0; i < judgeList.size(); i++) {
            if (judgeList.get(i).getAnswer().equals(judges.get(i))) {
                score += 1;
            }
        }
        for (int i = 0; i < essayList.size(); i++) {
            double num = 0.0;
            String reply = essays.get(i);
            String answer = essayList.get(i).getAnswer();
            List<String> keywords = HanLP.extractKeyword(reply, 10);
            List<String> answerKey = HanLP.extractKeyword(answer, 10);
            num = answerKey.stream().filter(keywords::contains).count();
            num += SimilarUtils.similarRete(reply, answer);
            score += num * 5;
        }
        String userId = jsonObject.getObject("userId", String.class);
        ExamHistory examHistory = ExamHistory.builder()
                .examId(examId)
                .userId(userId)
                .score(score)
                .selectMsg(JSON.toJSONString(selects))
                .blankMsg(JSON.toJSONString(blanks))
                .judgeMsg(JSON.toJSONString(judges))
                .essayMsg(JSON.toJSONString(essays))
                .build();

        studentDao.endExam(examHistory);

        studentDao.putScore(score, userId);
        return true;
    }
}
