package com.example.demo.service.login.impl;

import com.example.demo.entity.roles.Student;

import java.io.IOException;

public interface LoginService {

    public Object register(Student student) throws IOException;
}
