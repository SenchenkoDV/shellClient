package com.senchenko.weather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClientRunner {
    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(ClientRunner.class, args);
        Thread.currentThread().join();
    }
}
