package edu.phystech.jdbcdemo.service;

import org.springframework.boot.SpringBootConfiguration;

import javax.annotation.PostConstruct;

@SpringBootConfiguration
public class StopConfiguration {
    @PostConstruct
    void init(){
        System.out.println(">>>>>>>>>>stop config 1>>>>STOP!");
    }
}
