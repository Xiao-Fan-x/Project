package com.example.demo.config;


import com.spotify.docker.client.DefaultDockerClient;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.exceptions.DockerCertificateException;
import com.spotify.docker.client.exceptions.DockerException;
import com.spotify.docker.client.messages.ContainerConfig;
import com.spotify.docker.client.messages.ContainerCreation;
import com.spotify.docker.client.messages.HostConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DockerConfig {


    @Bean
    public DockerClient client() throws DockerCertificateException {
        return DefaultDockerClient.fromEnv().build();
    }

    @Bean(name = "python")
    public ContainerCreation containerPython(DockerClient client) throws DockerException, InterruptedException {

        HostConfig hostConfig = HostConfig.builder().appendBinds(HostConfig.Bind.from("C:\\Users\\17430\\Desktop\\demo\\code").to("/temp/").build()).build();
        ContainerConfig containerConfig = ContainerConfig.builder().image("python:3.8-alpine3.16").attachStdin(true).tty(true).attachStdout(true).openStdin(true).hostConfig(hostConfig).workingDir("/temp/").cmd("sh").build();


        ContainerCreation container = client.createContainer(containerConfig);
        client.startContainer(container.id());

        return container;
    }

    @Bean(name = "java")
    public ContainerCreation containerJava(DockerClient client) throws DockerException, InterruptedException {

        HostConfig hostConfig = HostConfig.builder().appendBinds(HostConfig.Bind.from("C:\\Users\\17430\\Desktop\\demo\\code").to("/temp/").build()).build();
        ContainerConfig containerConfig = ContainerConfig.builder().image("bitnami/java:1.8.333-debian-10-r0").attachStdin(true).tty(true).attachStdout(true).openStdin(true).hostConfig(hostConfig).workingDir("/temp/").cmd("sh", "-c", "while :; do sleep 1; done").build();

        ContainerCreation container = client.createContainer(containerConfig);
        client.startContainer(container.id());

        return container;
    }


//    @Bean(name = "cpp")
    public ContainerCreation containerCpp(DockerClient client) throws DockerException, InterruptedException {

        HostConfig hostConfig = HostConfig.builder().appendBinds(HostConfig.Bind.from("C:\\Users\\17430\\Desktop\\demo\\code").to("/temp/").build()).build();
        ContainerConfig containerConfig = ContainerConfig.builder().image("bitnami/java:1.8.333-debian-10-r0").attachStdin(true).tty(true).attachStdout(true).openStdin(true).hostConfig(hostConfig).workingDir("/temp/").cmd("sh", "-c", "while :; do sleep 1; done").build();

        ContainerCreation container = client.createContainer(containerConfig);
        client.startContainer(container.id());

        return container;
    }


}
