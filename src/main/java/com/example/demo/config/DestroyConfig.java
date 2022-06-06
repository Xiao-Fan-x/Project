package com.example.demo.config;


import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.messages.ContainerCreation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class DestroyConfig implements DisposableBean {

    @Autowired
    private DockerClient client;

    @Qualifier("python")
    @Autowired
    private ContainerCreation creationPython;

    @Qualifier("java")
    @Autowired
    private ContainerCreation creationJava;

    @Override
    public void destroy() throws Exception {
        log.info("==========================================");
        client.stopContainer(creationPython.id(), 5);
        client.removeContainer(creationPython.id());
        client.stopContainer(creationJava.id(), 5);
        client.removeContainer(creationJava.id());

        client.close();
    }

}
