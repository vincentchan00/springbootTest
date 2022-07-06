package com.example.hatchways;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.hatchways.controller.PostsController;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class HatchwaysApplicationTests {
    @LocalServerPort
    private int port;
    @Autowired
    private PostsController controller;
    private RestTemplate restTemplate;
    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }

}
