package com.example.demo.dao.master;

import com.example.demo.entity.roles.Teacher;
import com.example.demo.entity.roles.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TeacherDao {
    Teacher getByUserId(User user);

    Boolean uploadTeacher(List<Teacher> teachers);

}
