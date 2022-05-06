package com.example.demo.service.upload;

import com.alibaba.excel.EasyExcel;
import com.example.demo.dao.click.ExamDao;
import com.example.demo.entity.exam.Select;
import com.example.demo.service.upload.impl.TeacherUpload;
import com.example.demo.util.UploadSelectUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

public class TeacherUploadImpl implements TeacherUpload {

    @Autowired
    private ExamDao examDao;
    @Override
    public void uploadTest(MultipartFile file) throws IOException {

        EasyExcel.read(file.getInputStream(),Select.class, new UploadSelectUtils(examDao)).sheet().doRead();

    }
}
