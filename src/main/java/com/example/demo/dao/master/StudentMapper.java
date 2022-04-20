package com.example.demo.dao.master;

import com.example.demo.entity.roles.Student;
import com.example.demo.entity.roles.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface StudentMapper {


    User getByUserId(User user);


    Boolean register(Student student);

}
