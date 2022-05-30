package com.example.demo.controller.classInfor;


import com.example.demo.service.claszInfor.ClaszInforService;
import com.example.demo.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/clasz")
public class ClaszController {

    @Autowired
    private ClaszInforService claszInforService;

    @GetMapping
    public Object getClaszInforAll() {

        try {
            return Result.ResultSuccess(claszInforService.getClaszInforAll());
        } catch (Exception e) {
            log.error("获取所有班级信息错误");
            throw new RuntimeException(e);
        }
    }
}
