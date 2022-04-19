package com.example.demo.dao.click;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface ExamMsg {

    List<Map<String, Object>> get();


}
