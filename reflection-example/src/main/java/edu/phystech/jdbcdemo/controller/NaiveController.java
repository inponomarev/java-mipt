package edu.phystech.jdbcdemo.controller;

import edu.phystech.jdbcdemo.service.Service;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NaiveController {
    private final Service service;

    public void executeCommand(String command) {
        switch (command) {
            case "foo":
                service.foo();
                break;
            case "bar":
                service.bar();
                break;
            case "help":
            default:
                service.help();
                break;
        }
    }
}

