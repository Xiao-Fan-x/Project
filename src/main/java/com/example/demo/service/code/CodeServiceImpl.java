package com.example.demo.service.code;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.service.code.impl.CodeService;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.LogStream;
import com.spotify.docker.client.exceptions.DockerException;
import com.spotify.docker.client.messages.ContainerCreation;
import com.spotify.docker.client.messages.ExecCreation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Slf4j
@Service
public class CodeServiceImpl implements CodeService {

    @Autowired
    private DockerClient client;

    @Qualifier("python")
    @Autowired
    private ContainerCreation creationPython;

    @Qualifier("java")
    @Autowired
    private ContainerCreation creationJava;

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

                String id = creationPython.id();

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
            if ("Linux".equals(sys)) {
                file = new File(File.separator + "var" + File.separator + "codeSpace" + File.separator);

            } else {
                file = new File("C:\\Users\\17430\\Desktop\\demo\\code\\Main.java");
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

                String id = creationJava.id();

                String[] command = {"javac", "Main.java"};

                ExecCreation execCreation = null;


                execCreation = client.execCreate(
                        id, command, DockerClient.ExecCreateParam.attachStdout(),
                        DockerClient.ExecCreateParam.attachStderr());

                LogStream output = client.execStart(execCreation.id());
                String execOutput = output.readFully();
                output.close();
                System.out.println(execOutput);

                command = new String[]{"java", "Main"};

                execCreation = client.execCreate(
                        id, command, DockerClient.ExecCreateParam.attachStdout(),
                        DockerClient.ExecCreateParam.attachStderr());
                output = client.execStart(execCreation.id());


                execOutput = output.readFully();

                System.out.println(execOutput);

                Thread.sleep(5);
                output.readFully();
                System.out.println(execOutput);
                output.close();

                return execOutput;
            }
        } else {

        }

        return null;
    }
}
