package com.example.demo;


import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.*;
import com.github.dockerjava.api.model.*;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import com.github.dockerjava.transport.DockerHttpClient;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.github.dockerjava.api.model.HostConfig.newHostConfig;

@Slf4j
@SpringBootTest
public class DockerTest {

    String pyCode = "#!/usr/bin/python\n" +
            "# Write Python 3 code in this online editor and run it.\n" +
            "print(\"Hello, World!\");";

    String javaCode = "public class HelloWorld {\n" +
            "    public static void main(String []args) {\n" +
            "       System.out.println(\"Hello World!\");\n" +
            "    }\n" +
            "}";

    public static CreateContainerResponse createContainer(DockerClient client) throws URISyntaxException {

        CreateContainerCmd containerCmd = client.createContainerCmd("nginx:latest")
                //名字
                .withName("tomcat")
                //端口映射 内部80端口与外部81端口映射
                .withHostConfig(new HostConfig().withPortBindings(new Ports(new ExposedPort(8080), Ports.Binding.bindPort(3355))))
                //环境变量
                .withEnv("key=value")
                //挂载
                .withVolumes(new Volume("/var/log"));

        //创建
        CreateContainerResponse response = containerCmd.exec();
        System.out.println(response.getId());
        return response;
    }

    @Test
    public void dockerClient() throws IOException {

        Properties properties = new Properties();
        properties.setProperty("DOCKER_HOST", "tcp://127.0.0.1:4243");

        DefaultDockerClientConfig config = DefaultDockerClientConfig
                .createDefaultConfigBuilder()
                .withProperties(properties)
                .build();


        DockerHttpClient httpClient = new ApacheDockerHttpClient.Builder()
                .dockerHost(config.getDockerHost())
                .maxConnections(100)
                .connectionTimeout(Duration.ofSeconds(30))
                .responseTimeout(Duration.ofSeconds(45))
                .build();

        DockerClient client = DockerClientImpl.getInstance(config, httpClient);


        HostConfig hostConfig = HostConfig.newHostConfig();

        List<Mount> list = new ArrayList<>();
        Mount mount = new Mount();
        mount.withSource("/temp/workspace");
        mount.withTarget("/temp/workspace");

        list.add(mount);
        hostConfig.withMounts(list);

        CreateContainerCmd containerCmd = client.createContainerCmd("tomcat:9.0")
                //端口映射 内部80端口与外部81端口映射
//                .withPortSpecs("3355")
                //环境变量
                //挂载
                .withWorkingDir("/temp/workspace")
                .withAttachStdout(true)
                .withAttachStdout(true)
//                .withBinds(Bind.parse("/var/local:var/local"))
                .withHostConfig(HostConfig.newHostConfig())
                .withTty(true);
//                .withCmd("python", "demo.py");

        CreateContainerResponse container = containerCmd.exec();
//        client.startContainerCmd(container.getId()).exec();
//        LogContainerCmd cmd = client.logContainerCmd(container.getId()).withStdOut(true);
//            client.crea
        try {
            client.execCreateCmd(container.getId()).withTty(true)
                    .withAttachStdin(true).withAttachStdout(true).exec();
        } catch (Exception e) {
            e.printStackTrace();
        }
        InputStream stdin = client.execStartCmd(container.getId()).getStdin();

        System.out.println(stdin);
        stdin.close();
//        System.out.println(cmd);
//        adapter.onNext();
    }


    @Test
    public void buildClient() throws IOException {

        Properties properties = new Properties();
        properties.setProperty("DOCKER_HOST", "tcp://127.0.0.1:4243");

        DefaultDockerClientConfig config = DefaultDockerClientConfig
                .createDefaultConfigBuilder()
                .withProperties(properties)
                .build();


        DockerHttpClient httpClient = new ApacheDockerHttpClient.Builder()
                .dockerHost(config.getDockerHost())
                .maxConnections(100)
                .connectionTimeout(Duration.ofSeconds(30))
                .responseTimeout(Duration.ofSeconds(45))
                .build();

        DockerClient client = DockerClientImpl.getInstance(config, httpClient);


        HostConfig hostConfig = HostConfig.newHostConfig();

        File file = new File("/home/xiaofan/Documents/Project/Docker/Dockerfiles/Dockerfile");
        String imageCmd = client.buildImageCmd()
                .withDockerfile(file)
                .withPull(true)
                .withTarget("python:demo")
                .exec(new BuildImageResultCallback())
                .awaitImageId();


        CreateContainerCmd containerCmd = client.createContainerCmd(imageCmd)
                .withAttachStdout(true)
                .withTty(true);
//

//        CreateContainerCmd containerCmd = client.createContainerCmd("tomcat:9.0")
//                //名字
//                .withName("tomcat")
//                //端口映射 内部80端口与外部81端口映射
//                .withHostConfig(new HostConfig().withPortBindings(new Ports(new ExposedPort(80), Ports.Binding.bindPort(81))))
//                //环境变量
//                //挂载
//                .withVolumes(new Volume("/var/log"));


        CreateContainerResponse container = containerCmd.exec();

        client.startContainerCmd(container.getId()).exec();

        LogContainerCmd cmd = client.logContainerCmd(container.getId()).withStdOut(true);

        System.out.println(cmd);
//        adapter.onNext();
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

