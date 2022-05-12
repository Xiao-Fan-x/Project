package com.example.demo.dao.master;

import com.example.demo.entity.roles.Student;
import com.example.demo.entity.roles.User;
import jnr.ffi.Struct;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentDao {


    Student getByUserId(User user);

    User getById(@Param("id") String id);

    Boolean register(Student student);

    Boolean uploadStudent(List<Student> students);

}
