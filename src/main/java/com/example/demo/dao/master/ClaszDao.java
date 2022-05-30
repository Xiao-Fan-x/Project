package com.example.demo.dao.master;

import com.example.demo.entity.Clasz;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClaszDao {

    List<Clasz> getAll();

    List<String>  getElectiveStudent();
}
