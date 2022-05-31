package com.example.demo.service.code.impl;

import com.alibaba.fastjson.JSONObject;
import com.spotify.docker.client.exceptions.DockerException;

public interface CodeService {


    Object running(JSONObject jsonObject) throws DockerException, InterruptedException;
}
