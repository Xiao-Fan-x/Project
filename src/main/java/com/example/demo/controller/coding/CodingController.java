package com.example.demo.controller.coding;


import com.alibaba.fastjson.JSONObject;
import com.example.demo.service.code.impl.CodeService;
import com.example.demo.util.Result;
import com.spotify.docker.client.exceptions.DockerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("coding")
public class CodingController {

    @Autowired
    private CodeService codeService;

    @PostMapping("running")
    public Object running(@RequestBody JSONObject jsonObject) throws DockerException, InterruptedException {

        return Result.ResultSuccess(codeService.running(jsonObject));
    }
}
