package com.example.demo.service.teacher;


import com.alibaba.fastjson.JSONObject;
import com.example.demo.dao.click.ExamDetailDao;
import com.example.demo.dao.master.ExamDao;
import com.example.demo.dao.master.StudentDao;
import com.example.demo.entity.exam.Exam;
import com.example.demo.entity.exam.Select;
import com.example.demo.entity.roles.Teacher;
import com.example.demo.service.teacher.impl.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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

        //Get  Num
        Integer selectNum = examDetailDao.getSelectNum(teacher);
        Integer blankNum = examDetailDao.getBlankNum(teacher);
        Integer judgeNum = examDetailDao.getJudgeNum(teacher);
        Integer essayNum = examDetailDao.getEssayNum(teacher);

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
        res.put("selectNum",selectNum);
        res.put("blankNum",blankNum);
        res.put("judgeNum",judgeNum);
        res.put("essayNum",essayNum);
        res.put("totalNum",totalNum);

//        res.put("studentNum",studentNum);
        res.put("examRes",examRes);
        res.put("examDep",examDep);

        return res;
    }

    @Override
    public Object getSelect(Select select) {

        List<Select> list = examDetailDao.getSelect(select);

        return list;
    }
}
