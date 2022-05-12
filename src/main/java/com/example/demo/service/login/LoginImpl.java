package com.example.demo.service.login;


import com.example.demo.dao.master.StudentDao;
import com.example.demo.dao.master.TeacherDao;
import com.example.demo.entity.roles.Student;
import com.example.demo.entity.roles.User;
import com.example.demo.service.login.impl.Login;
import com.example.demo.util.MD5Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;


@Service
@Slf4j
public class LoginImpl implements Login {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private TeacherDao teacherDao;

    /**
     * 单个学生注册
     *
     * @param student
     * @return
     */
    @Override
    public Object register(Student student) throws IOException {
        log.info(student.toString());
        String userId = student.getUserId();
//        studentMapper.getByUserId(userId);
        System.out
                .println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
//        student.setRegistTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        student.setRegistTime(LocalDateTime.now());
        student.setPassword(MD5Utils.encrypt(student.getPassword()));
        return studentDao.register(student);
    }


  /**
   * 登录
   * @param user
   * @return
   */
    public Object login(User user) {

        String password = user.getPassword();

        try {
            password = MD5Utils.encrypt(password);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        User userResult;
        if ("student".equals(user.getRole())) {
            userResult = studentDao.getByUserId(user);
        } else {
            userResult = teacherDao.getByUserId(user);
        }
        userResult.setRoles(Arrays.asList(userResult.getRole()));

        if (password.equals(userResult.getPassword())) {
            userResult.setPassword("");
            return userResult;
        } else if ("123456".equals(userResult.getPassword()) && "123456".equals(user.getPassword())) {
            userResult.setPassword("");
            return userResult;
        } else {
            return null;
        }
    }
}
