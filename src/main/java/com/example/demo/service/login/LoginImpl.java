package com.example.demo.service.login;


import com.example.demo.dao.master.StudentMapper;
import com.example.demo.entity.roles.Student;
import com.example.demo.entity.roles.User;
import com.example.demo.util.MD5Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Service
@Slf4j
public class LoginImpl implements Login {

    @Autowired
    private StudentMapper studentMapper;

    /**
     * 单个学生注册
     *
     * @param student
     * @return
     */
    @Override
    public Object register(Student student) {
        log.info(student.toString());
        String userId = student.getUserId();
//        studentMapper.getByUserId(userId);
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
//        student.setRegistTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        student.setRegistTime(LocalDateTime.now());
        Boolean regist = studentMapper.register(student);
        return regist;
    }


    public Object login(User user) {

        String password = user.getPassword();

        try {
            password = MD5Utils.encrypt(password);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        User userResult = studentMapper.getByUserId(user);
        log.info(userResult.toString());
        if (password.equals(userResult.getPassword())) {
            userResult.setPassword("");
            return userResult;
        }
        return null;
    }
}
