package com.example.demo;

import com.example.demo.entity.City;
import com.example.demo.mapper.CityMapper;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@MapperScan("com.example.demo.mapper")
@SpringBootTest
class DemoApplicationTests {
    @Autowired
    private CityMapper cityMapper;

    @Test
    void contextLoads() {
        System.out.println("hello");
        List<City> a = cityMapper.get();
//        System.out.println(a);
    }

}
