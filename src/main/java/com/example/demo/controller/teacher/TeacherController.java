package com.example.demo.controller.teacher;


import com.example.demo.entity.exam.Blank;
import com.example.demo.entity.exam.Essay;
import com.example.demo.entity.exam.Judge;
import com.example.demo.entity.exam.Select;
import com.example.demo.entity.roles.Teacher;
import com.example.demo.service.teacher.impl.TeacherService;
import com.example.demo.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @RequestMapping("/init")
    public Object dashboard(@RequestBody Teacher teacher) {
        try {
            return Result.ResultSuccess(teacherService.initDash(teacher));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ResultErr(null, "程序出错");
        }
    }

    @PostMapping("/select")
    public Object getSelect(@RequestBody Select select) {
        try {
            return Result.ResultSuccess(teacherService.getSelect(select));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ResultErr(e, "教师获取选择题失败！");
        }
    }

    @PostMapping("/blank")
    public Object getBlank(@RequestBody Blank blank) {
        try {
            return Result.ResultSuccess(teacherService.getBlank(blank));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ResultErr(e, "教师获取填空题失败！");
        }
    }

    @DeleteMapping("/blank/{id}")
    public Object getBlank(@PathVariable String id) {
        try {
            return Result.ResultSuccess(teacherService.deleteBlank(id));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ResultErr(e, "教师删除填空题失败！");
        }
    }

    @PutMapping("/blank/update")
    public Object putBlank(@RequestBody Blank blank) {

        log.info("=====================" + blank.toString());

        return null;
    }

    @PostMapping("/judge")
    public Object getJudge(@RequestBody Judge judge) {
        try {
            return Result.ResultSuccess(teacherService.getJudge(judge));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ResultErr(e, "教师获取判断题失败！");
        }
    }

    @PostMapping("/essay")
    public Object getEssay(@RequestBody Essay essay) {
        try {
            return Result.ResultSuccess(teacherService.getEssay(essay));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ResultErr(e, "教师获取简答题失败！");
        }
    }

}
