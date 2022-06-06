package com.example.demo;

import com.example.demo.dao.click.ExamDetailDao;
import com.spotify.docker.client.DefaultDockerClient;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.LogStream;
import com.spotify.docker.client.exceptions.DockerCertificateException;
import com.spotify.docker.client.exceptions.DockerException;
import com.spotify.docker.client.messages.ContainerConfig;
import com.spotify.docker.client.messages.ContainerCreation;
import com.spotify.docker.client.messages.ExecCreation;
import com.spotify.docker.client.messages.HostConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * web读写案例
 *
 * @author Jiaju Zhuang
 **/
@Slf4j
@MapperScan("com.example.demo.mapper")
//@SpringBootTest
public class WebTest {
    private final String restEndpointUrl = "tcp://127.0.0.1:2375";
    String Path = "/home/xiaofan/Desktop/";
    @Autowired
    private ExamDetailDao examDetailDao;

    //    https://github.com/spotify/docker-client/blob/master/docs/user_manual.md#troubleshooting
    @Test
    void test() throws IOException, DockerException, InterruptedException, DockerCertificateException {
        DockerClient client = DefaultDockerClient.fromEnv().build();
//        DockerClient client = DefaultDockerClient.builder()
//                .uri(URI.create("http://127.0.0.1:2375"))
////                .dockerCertificates(new DockerCertificates(Paths.get("/Users/rohan/.docker/boot2docker-vm/")))
//                .build();

//        client.pull("python:3.8");
//        List<Container> containers = client.listContainers();

//        containers.forEach(e->{
//            System.out.println(e.names());
//        });
//        String logs;
        HostConfig hostConfig = HostConfig.builder()
                .appendBinds(HostConfig.Bind.from("C:\\Users\\17430\\Desktop\\demo\\code")
                        .to("/temp/").build())
                .build();

        List<String> env = new ArrayList<String>();
//        env.add("export JAVA_HOME=/usr/java/openjdk-8");
//        env.add("export CLASSPATH=%JAVA_HOME%/lib:%JAVA_HOME%/jre/lib");
//        env.add("export PATH=$PATH:$JAVA_HOME/bin:$JAVA_HOME/jre/bin");

        ContainerConfig containerConfig = ContainerConfig.builder()
                .image("bitnami/java:1.8.333-debian-10-r0")
//                .image("python:3.8-alpine3.16")
                .attachStdin(true).tty(true)
                .attachStdout(true).openStdin(true)
                .hostConfig(hostConfig).workingDir("/temp/").cmd("sh", "-c", "while :; do sleep 1; done").build();
//                .cmd("sh", "-c", "while :; do sleep 1; done")

        ContainerCreation creation = client.createContainer(containerConfig);
        String id = creation.id();
        client.startContainer(id);
//        String[] command = new String[2];
//        command[0]="g++ \\-o hello hello.cpp";
//        command[1] = ".\\/hello";
//        command[0] = "javac" + " Main.java";
//        command[1] = "java" + "Main";
//        String[] command = {"g++","-o","hello","hello.cpp"};
//        String[] command = {"python3","demo.py"};
//        String[] command = {"sh","-c","ls"};
        String[] command = {"javac","Main.java"};

        ExecCreation execCreation = null;
        try {
            execCreation = client.execCreate(
                    id, command, DockerClient.ExecCreateParam.attachStdout(),
                    DockerClient.ExecCreateParam.attachStderr());
        } catch (DockerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            LogStream output = client.execStart(execCreation.id());
            String execOutput = output.readFully();
            System.out.println(execOutput);
            output.close();
        }

        command = new String[]{"java", "Main"};

        execCreation = client.execCreate(
                id, command, DockerClient.ExecCreateParam.attachStdout(),
                DockerClient.ExecCreateParam.attachStderr());
        LogStream output = client.execStart(execCreation.id());
        String execOutput = output.readFully();
        System.out.println(execOutput);
//        client.stopContainer();
    }
}