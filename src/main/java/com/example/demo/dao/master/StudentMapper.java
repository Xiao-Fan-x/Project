package com.example.demo.dao.master;

import com.example.demo.entity.roles.Student;
import com.example.demo.entity.roles.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentMapper {


    Student getByUserId(User user);

    List<User> getById(@Param("id") String id);

    Boolean register(Student student);

}
