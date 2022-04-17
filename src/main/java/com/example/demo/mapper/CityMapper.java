package com.example.demo.mapper;

import com.example.demo.entity.City;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface CityMapper {

    List<City> get();

}
