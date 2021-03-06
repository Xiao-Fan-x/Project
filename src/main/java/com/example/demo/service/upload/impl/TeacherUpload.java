package com.example.demo.service.upload.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Service
public interface TeacherUpload {


  /**
   * 文件上传测试
   * @param file
   * @return
   */
  void uploadTeacher(MultipartFile file) throws IOException;

}
