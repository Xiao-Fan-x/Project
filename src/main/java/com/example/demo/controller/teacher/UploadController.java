package com.example.demo.controller.teacher;


import com.alibaba.excel.EasyExcel;
import com.example.demo.dao.click.ExamDao;
import com.example.demo.entity.exam.Blank;
import com.example.demo.entity.exam.Essay;
import com.example.demo.entity.exam.Judge;
import com.example.demo.entity.exam.Select;
import com.example.demo.service.upload.TeacherUploadImpl;
import com.example.demo.util.UploadSelectUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

@Slf4j
@RestController
@RequestMapping(value = "/upload")
public class UploadController {
    TeacherUploadImpl teacherUpload;

    @Autowired
    private ExamDao examDao;

    @RequestMapping(value = "/selectExam")
    public Boolean uploadSelect(@RequestParam("file") MultipartFile file) {

        try {
            EasyExcel.read(file.getInputStream(), Select.class, new UploadSelectUtils(examDao)).sheet().doRead();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    @RequestMapping(value = "/blankExam")
    public Boolean uploadBlank(@RequestParam("file") MultipartFile file) {

        try {
            EasyExcel.read(file.getInputStream(), Select.class, new UploadSelectUtils(examDao)).sheet().doRead();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping(value = "/judgeExam")
    public Boolean uploadJudge(@RequestParam("file") MultipartFile file) {

        try {
            EasyExcel.read(file.getInputStream(), Select.class, new UploadSelectUtils(examDao)).sheet().doRead();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping(value = "/essayExam")
    public Boolean uploadEssay(@RequestParam("file") MultipartFile file) {

        try {
            EasyExcel.read(file.getInputStream(), Select.class, new UploadSelectUtils(examDao)).sheet().doRead();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    @GetMapping("selectTemp")
    public void downloadSelectTemp(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("选择题模板", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), Select.class).sheet("模板").doWrite(new ArrayList<>());
    }

    @GetMapping("blankTemp")
    public void downloadBlankTemp(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("填空题模板", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), Blank.class).sheet("模板").doWrite(new ArrayList<>());
    }

    @GetMapping("judgeTemp")
    public void downloadJudgeTemp(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("判断题模板", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), Judge.class).sheet("模板").doWrite(new ArrayList<>());
    }

    @GetMapping("essayTemp")
    public void downloadEssayTemp(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("简答题模板", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), Essay.class).sheet("模板").doWrite(new ArrayList<>());
    }
}
