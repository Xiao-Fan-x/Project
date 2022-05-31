package com.example.demo.service.code;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.service.code.impl.CodeService;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.LogStream;
import com.spotify.docker.client.exceptions.DockerException;
import com.spotify.docker.client.messages.ContainerCreation;
import com.spotify.docker.client.messages.ExecCreation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


@Service
public class CodeServiceImpl implements CodeService {

    @Autowired
    private DockerClient client;

    @Autowired
    private ContainerCreation creation;

    public Object compileCode(String code, String main) {
        return null;
    }

    @Override
    public Object running(JSONObject jsonObject) throws DockerException, InterruptedException {

        String code = jsonObject.getObject("code", String.class);
        String language = jsonObject.getObject("language", String.class);
        String sys = System.getProperty("os.name");
        File file = null;

        if ("Python".equals(language)) {
            if ("Linux".equals(sys)) {
                file = new File(File.separator + "var" + File.separator + "codeSpace" + File.separator);

            } else {
                file = new File("C:\\Users\\17430\\Desktop\\demo\\code\\demo.py");
//                file = new File("C:" + File.separator + "var" + File.separator + "codeSpace" + File.separator + "demo.py");

//                if (!file.exists()) {
//                    try {
//                        file.createNewFile();
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
                OutputStream outputStream = null;
                try {
                    outputStream = new FileOutputStream(file);
                    outputStream.write(code.getBytes());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } finally {
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

                String id = creation.id();

                String[] command = {"python3", "demo.py"};

                ExecCreation execCreation = client.execCreate(
                        id, command, DockerClient.ExecCreateParam.attachStdout(),
                        DockerClient.ExecCreateParam.attachStderr());

                LogStream output = client.execStart(execCreation.id());
                String execOutput = output.readFully();

                output.close();

                return execOutput;
            }
        } else if ("Java".equals(language)) {

        } else {

        }

        return null;
    }
}
