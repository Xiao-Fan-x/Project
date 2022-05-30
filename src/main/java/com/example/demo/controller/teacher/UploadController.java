package com.example.demo.controller.teacher;


import com.alibaba.excel.EasyExcel;
import com.example.demo.dao.click.ExamDetailDao;
import com.example.demo.entity.exam.Blank;
import com.example.demo.entity.exam.Essay;
import com.example.demo.entity.exam.Judge;
import com.example.demo.entity.exam.Select;
import com.example.demo.service.upload.impl.StudentUpload;
import com.example.demo.service.upload.impl.TeacherUpload;
import com.example.demo.util.UploadBlankUtils;
import com.example.demo.util.UploadEssayUtils;
import com.example.demo.util.UploadJudgeUtils;
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

    @Autowired
    private ExamDetailDao examDetailDao;

    @Autowired
    private TeacherUpload teacherUpload;

    @Autowired
    private StudentUpload studentUpload;


    /**
     * 上传学生信息
     *
     * @param file
     * @return
     */
    @RequestMapping(value = "/student")
    public Boolean uploadStudent(@RequestParam("file") MultipartFile file) {
        try {
            studentUpload.uploadStudent(file);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 上传教师信息
     *
     * @param file
     * @return
     */
    @RequestMapping(value = "/teacher")
    public Boolean uploadTeacher(@RequestParam("file") MultipartFile file) {
        try {
            teacherUpload.uploadTeacher(file);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 上传选择题
     *
     * @param file
     * @return
     */
    @RequestMapping(value = "/selectExam")
    public Boolean uploadSelect(@RequestParam("file") MultipartFile file) {

        try {
            EasyExcel.read(file.getInputStream(), Select.class, new UploadSelectUtils(examDetailDao)).sheet().doRead();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 上传填空题
     *
     * @param file
     * @return
     */
    @RequestMapping(value = "/blankExam")
    public Boolean uploadBlank(@RequestParam("file") MultipartFile file) {

        try {
            EasyExcel.read(file.getInputStream(), Blank.class, new UploadBlankUtils(examDetailDao)).sheet().doRead();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 上传判断题
     *
     * @param file
     * @return
     */
    @RequestMapping(value = "/judgeExam")
    public Boolean uploadJudge(@RequestParam("file") MultipartFile file) {

        try {
            EasyExcel.read(file.getInputStream(), Judge.class, new UploadJudgeUtils(examDetailDao)).sheet().doRead();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 上传简答题
     *
     * @param file
     * @return
     */
    @RequestMapping(value = "/essayExam")
    public Boolean uploadEssay(@RequestParam("file") MultipartFile file) {

        try {
            EasyExcel.read(file.getInputStream(), Essay.class, new UploadEssayUtils(examDetailDao)).sheet().doRead();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 选择题模板
     *
     * @param file
     * @return
     */
    @GetMapping("selectTemp")
    public void downloadSelectTemp(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("选择题模板", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), Select.class).sheet("模板").doWrite(new ArrayList<>());
    }

    /**
     * 填空题模板
     *
     * @param file
     * @return
     */
    @GetMapping("blankTemp")
    public void downloadBlankTemp(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("填空题模板", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), Blank.class).sheet("模板").doWrite(new ArrayList<>());
    }


    /**
     * 判断题模板
     *
     * @param file
     * @return
     */
    @GetMapping("judgeTemp")
    public void downloadJudgeTemp(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("判断题模板", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), Judge.class).sheet("模板").doWrite(new ArrayList<>());
    }

    /**
     * 简答题模板
     *
     * @param file
     * @return
     */
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
