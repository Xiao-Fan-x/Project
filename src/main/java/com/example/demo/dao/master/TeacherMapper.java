package com.example.demo.dao.master;

import com.example.demo.entity.roles.Teacher;
import com.example.demo.entity.roles.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TeacherMapper {
    Teacher getByUserId(User user);
}
