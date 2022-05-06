package com.example.demo.service.upload.impl;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface TeacherUpload {


  /**
   * 文件上传测试
   * @param file
   * @return
   */
  void uploadTest(MultipartFile file) throws IOException;

}
