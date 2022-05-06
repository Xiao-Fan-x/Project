package com.example.demo;

import com.alibaba.excel.EasyExcel;
import com.example.demo.dao.click.ExamDao;
import com.example.demo.entity.exam.Select;
import com.example.demo.util.UploadSelectUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * web读写案例
 *
 * @author Jiaju Zhuang
 **/
@Slf4j
@MapperScan("com.example.demo.mapper")
@SpringBootTest
public class WebTest {
    @Autowired
    private ExamDao examDao;
    String Path = "/home/xiaofan/Desktop/";

    @Test
    void tese() throws FileNotFoundException {
        File file = new File(Path + File.separator + "hello.xlsx");
        InputStream inputStream = new FileInputStream(file);

        EasyExcel.read(inputStream, Select.class, new UploadSelectUtils(examDao)).sheet().doRead();
    }
}