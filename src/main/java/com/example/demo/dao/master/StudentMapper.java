package com.example.demo.dao.master;

import com.example.demo.entity.roles.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface StudentMapper {


    String getByUserId(@Param("userId") String userId);


    Boolean register(Student student);

}
