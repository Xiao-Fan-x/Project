package com.example.demo.service.upload;

import com.alibaba.excel.EasyExcel;
import com.example.demo.dao.master.TeacherDao;
import com.example.demo.entity.exam.Select;
import com.example.demo.entity.roles.Teacher;
import com.example.demo.service.upload.impl.TeacherUpload;
import com.example.demo.util.UploadTeacherUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Slf4j
@Service
public class TeacherUploadImpl implements TeacherUpload {

    @Autowired
    private TeacherDao teacherDao;

    @Override
    public void uploadTeacher(MultipartFile file) throws IOException {

        EasyExcel.read(file.getInputStream(), Teacher.class, new UploadTeacherUtils(teacherDao)).sheet().doRead();

    }
}
