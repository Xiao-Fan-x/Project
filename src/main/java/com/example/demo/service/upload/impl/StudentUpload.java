package com.example.demo.service.upload.impl;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Service
public interface StudentUpload {

    void uploadStudent(MultipartFile file) throws IOException;


}
