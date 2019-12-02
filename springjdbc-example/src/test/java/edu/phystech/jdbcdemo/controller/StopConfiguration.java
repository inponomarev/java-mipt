package edu.phystech.jdbcdemo.controller;

import org.springframework.boot.SpringBootConfiguration;

import javax.annotation.PostConstruct;

@SpringBootConfiguration
public class StopConfiguration {
    @PostConstruct
    void init(){
        System.out.println(">>>>>>>>>>stop config2>>>>STOP!");
    }
}
