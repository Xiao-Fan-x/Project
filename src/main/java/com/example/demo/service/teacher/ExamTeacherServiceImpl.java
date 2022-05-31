package com.example.demo.service.teacher;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.dao.click.ExamDetailDao;
import com.example.demo.dao.master.ExamDao;
import com.example.demo.dao.master.StudentDao;
import com.example.demo.dao.master.TeacherDao;
import com.example.demo.entity.Elective;
import com.example.demo.entity.ExamMsg;
import com.example.demo.entity.ExamStu;
import com.example.demo.entity.exam.*;
import com.example.demo.entity.roles.Student;
import com.example.demo.entity.roles.Teacher;
import com.example.demo.service.teacher.impl.ExamTeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ExamTeacherServiceImpl implements ExamTeacherService {

    @Autowired
    private ExamDao examDao;

    @Autowired
    private TeacherDao teacherDao;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private ExamDetailDao examDetailDao;

    @Override
    public Object getTeacherExamAll(String userId) {
        Map res = new HashMap<>(4);

        Teacher user = teacherDao.getById(userId);

        List<Exam> teacherExamAll = examDao.getTeacherExamAll(userId);

        List<Exam> teacherExamRelative = examDao.getTeacherExamRelative(user);

        teacherExamRelative.removeAll(teacherExamAll);

        res.put("self", teacherExamAll);
        res.put("other", teacherExamRelative);

        return res;
    }

    @Override
    public Boolean createExam(Exam exam) {

        log.info(exam.toString());
        String examId = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHH"));
        exam.setId(Integer.valueOf(examId));
        exam.setCreateTime(LocalDateTime.now());

//        exam.setDuration((int) Duration.between(exam.getEndTime(), exam.getStartTime()).toMinutes());

        return examDao.createExam(exam);
    }

    @Override
    public Object sendExam(Integer examId) {

        Exam exam = examDao.getExamByExamId(examId);
//        List list = new ArrayList(11);

        if (exam.getElective() != null && "".equals(exam.getElective())) {
            Elective elective = Elective.builder()
                    .grade(exam.getGrade())
                    .elective(exam.getElective())
                    .build();

            List<String> stuList = examDao.getElective(elective);
//            ExamStu examStu;
            List<ExamStu> insert = new ArrayList<>();
            stuList.stream().forEach(e -> {
                insert.add(ExamStu.builder()
                        .userId(e)
                        .examId(exam.getId())
                        .examName(exam.getExamName())
                        .createTime(exam.getCreateTime())
                        .createPeople(exam.getCreatePeople())
                        .startTime(exam.getStartTime())
                        .endTime(exam.getEndTime())
                        .grade(exam.getGrade())
                        .department(exam.getDepartment())
                        .major(exam.getMajor())
                        .subject(exam.getSubject())
                        .className(exam.getClassName())
                        .elective(exam.getElective())
                        .build());
            });

            return examDao.sendExam(insert);
        } else {

            Student student = Student.builder()
                    .grade(exam.getGrade())
                    .department(exam.getDepartment())
                    .major(exam.getMajor())
                    .className(exam.getClassName())
                    .build();
            List<String> stuList = studentDao.getStudentList(student);

            List<ExamStu> insert = new ArrayList<>();
            stuList.stream().forEach(e -> {
                insert.add(ExamStu.builder()
                        .userId(e)
                        .examId(exam.getId())
                        .examName(exam.getExamName())
                        .createTime(exam.getCreateTime())
                        .createPeople(exam.getCreatePeople())
                        .startTime(exam.getStartTime())
                        .endTime(exam.getEndTime())
                        .grade(exam.getGrade())
                        .department(exam.getDepartment())
                        .major(exam.getMajor())
                        .subject(exam.getSubject())
                        .className(exam.getClassName())
                        .elective(exam.getElective())
                        .build());
            });
            return examDao.sendExam(insert);
        }

    }

    @Override
    public Object selectQues(Integer id) {

        Exam examByExamId = examDao.getExamByExamId(id);

        Select select = Select.builder()
                .department(examByExamId.getDepartment())
                .major(examByExamId.getMajor())
                .subject(examByExamId.getSubject())
                .build();

        Blank blank = Blank.builder()
                .department(examByExamId.getDepartment())
                .major(examByExamId.getMajor())
                .subject(examByExamId.getSubject())
                .build();

        Judge judge = Judge.builder()
                .department(examByExamId.getDepartment())
                .major(examByExamId.getMajor())
                .subject(examByExamId.getSubject())
                .build();

        Essay essay = Essay.builder()
                .department(examByExamId.getDepartment())
                .major(examByExamId.getMajor())
                .subject(examByExamId.getSubject())
                .build();

        Map res = new HashMap(8);

        res.put("select", examDetailDao.getSelect(select));
        res.put("blank", examDetailDao.getBlank(blank));
        res.put("judge", examDetailDao.getJudge(judge));
        res.put("essay", examDetailDao.getEssay(essay));


        return res;
    }

    @Override
    public Boolean determine(JSONObject jsonObject) {
        Integer id = jsonObject.getObject("id", Integer.class);
        List<String> select = (List<String>) jsonObject.get("select");
        List<String> blank = (List<String>) jsonObject.get("blank");
        List<String> judge = (List<String>) jsonObject.get("judge");
        List<String> essay = (List<String>) jsonObject.get("essay");

        List<Select> selectList = examDetailDao.getSelectByList(select);
        List<Blank> blankList = examDetailDao.getBlankByList(blank);
        List<Judge> judgeList = examDetailDao.getJudgeByList(judge);
        List<Essay> essayList = examDetailDao.getEssayByList(essay);

        ExamMsg examMsg = ExamMsg.builder()
                .examId(id)
                .selectMsg(selectList.toString())
                .blankMsg(blankList.toString())
                .judgeMsg(judgeList.toString())
                .essayMsg(essayList.toString())
                .build();

        examDao.determine(examMsg);
        return true;
    }
}
