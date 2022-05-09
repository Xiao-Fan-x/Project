package com.example.demo;


import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.LoadImageCmd;
import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.Ports;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import static com.github.dockerjava.api.model.HostConfig.newHostConfig;

@Slf4j
@SpringBootTest
public class DockerTest {


    @Test
    public void dockerClient() {

        Properties properties = new Properties();
//        properties.setProperty("registry.email", "info@baeldung.com");
//        properties.setProperty("registry.password", "baeldung");
//        properties.setProperty("registry.username", "baaldung");
//        properties.setProperty("DOCKER_CERT_PATH", "/home/baeldung/.docker/certs");
//        properties.setProperty("DOCKER_CONFIG", "/home/baeldung/.docker/");
//        properties.setProperty("DOCKER_TLS_VERIFY", "1");
        properties.setProperty("DOCKER_HOST", "tcp://127.0.0.1:4243");

        DefaultDockerClientConfig config
                = DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withProperties(properties).build();
        DockerClient dockerClient = DockerClientBuilder.getInstance(config).build();

        dockerClient.startContainerCmd("python:3").exec();
//        CreateContainerResponse container = dockerClient.createContainerCmd("openjdk:8-jdk-oracle")
//                .withCmd("/bin/bash").exec();
//        client.startContainerCmd(container.getId()).exec();
//        try {
//            this.wait(5000);
//            Thread.currentThread().notify();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } finally {
//            Thread.currentThread().notify();
//        }
//        client.stopContainerCmd(container.getId()).exec();
//        client.removeContainerCmd(container.getId()).exec();
//        return client;
//        Info info = client.infoCmd().exec();
//        System.out.println(JSONObject.toJSONString(info));
//        return client;
    }


    public CreateContainerResponse createContainers(DockerClient client, String containerName, String imageName) {
        //映射端口8088—>80
        ExposedPort tcp80 = ExposedPort.tcp(80);
        Ports portBindings = new Ports();
        portBindings.bind(tcp80, Ports.Binding.bindPort(8088));
//        Bind bind = new Bind();
        CreateContainerResponse container = client.createContainerCmd(imageName)
                .withName(containerName)
                .withHostConfig(newHostConfig().withPortBindings(portBindings))
                .withExposedPorts(tcp80)
                .withAttachStdin(true)
                .withCmd("python", "/root/scripts/test.py", "-t", "999")
                .exec();
        return container;
    }

    public LoadImageCmd loadImage(DockerClient client, String filePath) {
        LoadImageCmd loadImageCmd = null;
        try {
            loadImageCmd = client.loadImageCmd(new FileInputStream(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return loadImageCmd;
    }

    public void removeContainer(DockerClient client, String containerId) {
        client.removeContainerCmd(containerId).exec();
    }

    public void startContainer(DockerClient client, String containerId) {
        client.startContainerCmd(containerId)
                .exec();
    }

    @Test
    public void StartAnJdk() {

//        startContainer(dockerClient(), "openjdk:8-jdk-oracle");

    }

    @Test
    public void createImage() {

        DockerClientConfig clientConfig = DefaultDockerClientConfig.createDefaultConfigBuilder().build();

//        createContainers();


//        dockerClient.pingCmd().exec();
    }

}

