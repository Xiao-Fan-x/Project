package com.example.demo;

import com.example.demo.dao.click.ExamDao;
import com.spotify.docker.client.DefaultDockerClient;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.LogStream;
import com.spotify.docker.client.exceptions.DockerCertificateException;
import com.spotify.docker.client.exceptions.DockerException;
import com.spotify.docker.client.messages.ContainerConfig;
import com.spotify.docker.client.messages.ContainerCreation;
import com.spotify.docker.client.messages.ContainerExit;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.OutputStream;

/**
 * web读写案例
 *
 * @author Jiaju Zhuang
 **/
@Slf4j
@MapperScan("com.example.demo.mapper")
@SpringBootTest
public class WebTest {
    private final String restEndpointUrl = "tcp://127.0.0.1:2375";
    String Path = "/home/xiaofan/Desktop/";
    @Autowired
    private ExamDao examDao;

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


        ContainerCreation creation = client.createContainer(
                ContainerConfig.builder()
                        .image("python:3.8")
                        .attachStdin(true).tty(true).cmd("/bin/bash")
                        .attachStdout(true).openStdin(true).build());
        client.resizeTty(creation.id(),10,10);
//        final ContainerExit exit = docker.waitContainer("containerID");
//        try (LogStream stream = client.logs(creation.id(), DockerClient.LogsParam.stdout(), DockerClient.LogsParam.stderr())) {
//            logs = stream.readFully();
//        }
//        System.out.println(logs+"----");
        OutputStream infor = null;
        OutputStream inforErr =null;
        final String logs;
        try (LogStream stream = client.attachContainer(creation.id(),
                DockerClient.AttachParameter.LOGS, DockerClient.AttachParameter.STDOUT,
                DockerClient.AttachParameter.STDERR, DockerClient.AttachParameter.STREAM)) {
            logs = stream.readFully();
        }

        String str = new String(infor.toString());
//        System.out.println(infor.);
        System.out.println(logs + "----"+ str);

    }
}