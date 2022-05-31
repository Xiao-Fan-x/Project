package com.example.demo.service.teacher;


import com.alibaba.fastjson.JSONObject;
import com.example.demo.dao.click.ExamDetailDao;
import com.example.demo.dao.master.ExamDao;
import com.example.demo.dao.master.StudentDao;
import com.example.demo.entity.exam.*;
import com.example.demo.entity.roles.Teacher;
import com.example.demo.service.teacher.impl.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private ExamDetailDao examDetailDao;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private ExamDao examDao;


    @Override
    public JSONObject initDash(Teacher teacher) {

        JSONObject res = new JSONObject();

        ExamTest examTest = new ExamTest();

        examTest.setDepartment(teacher.getDepartment());
        examTest.setMajor(teacher.getMajor());


        //Get  Num
        Integer selectNum = examDetailDao.getSelectNum(examTest);
        Integer blankNum = examDetailDao.getBlankNum(examTest);
        Integer judgeNum = examDetailDao.getJudgeNum(examTest);
        Integer essayNum = examDetailDao.getEssayNum(examTest);

//        Student student = Student.builder()
//                .gender(teacher.getGender())
//                .department(teacher.getDepartment())
//                .build();

//        Integer studentNum = studentDao.getStudentNum(teacher);
//        Integer studentNum = studentDao.getStudentNum(student);

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime endTime = now.plusMonths(1);

        Exam exam = Exam.builder()
                .department(teacher.getDepartment())
                .major(teacher.getMajor())
                .createPeople(teacher.getUserId())
                .startTime(now)
                .endTime(endTime)
                .build();
        //该老师自己发布的测试
        List<Exam> examRes = examDetailDao.getExam(exam);
        //同系专业的老师出的试卷
        List<Exam> examDep = examDetailDao.getExamDepartment(exam);
        examDep.removeAll(examRes);

        Integer totalNum = selectNum + blankNum + judgeNum + essayNum;
        res.put("selectNum", selectNum);
        res.put("blankNum", blankNum);
        res.put("judgeNum", judgeNum);
        res.put("essayNum", essayNum);
        res.put("totalNum", totalNum);

//        res.put("studentNum",studentNum);
        res.put("examRes", examRes);
        res.put("examDep", examDep);

        return res;
    }

    @Override
    public Object getSelect(Select select) {
        Map res = new HashMap(4);
        int page = select.getPage();
        select.setPage((page - 1) * 10);
        List<Select> list = examDetailDao.getSelect(select);
        Integer selectNum = examDetailDao.getSelectNum(select);
        res.put("list", list);
        res.put("total", selectNum);
        return res;
    }

    @Override
    public Object getBlank(Blank blank) {
        Map res = new HashMap(4);
        int page = blank.getPage();
        blank.setPage((page - 1) * 10);
        List<Select> list = examDetailDao.getBlank(blank);
        Integer selectNum = examDetailDao.getBlankNum(blank);
        res.put("list", list);
        res.put("total", selectNum);
        return res;

    }

    @Override
    public Object getJudge(Judge judge) {
        Map res = new HashMap(4);
        int page = judge.getPage();
        judge.setPage((page - 1) * 10);
        List<Select> list = examDetailDao.getJudge(judge);
        Integer selectNum = examDetailDao.getSelectNum(judge);
        res.put("list", list);
        res.put("total", selectNum);
        return res;
    }

    @Override
    public Object getEssay(Essay essay) {
        Map res = new HashMap(4);
        int page = essay.getPage();
        essay.setPage((page - 1) * 10);
        List<Select> list = examDetailDao.getEssay(essay);
        Integer selectNum = examDetailDao.getSelectNum(essay);
        res.put("list", list);
        res.put("total", selectNum);
        return res;
    }

    @Override
    public Boolean deleteBlank(String id) {
       return examDetailDao.deleteBlank(id);
    }
}
