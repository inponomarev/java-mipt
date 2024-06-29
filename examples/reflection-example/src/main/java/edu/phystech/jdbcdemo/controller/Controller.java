package edu.phystech.jdbcdemo.controller;

import edu.phystech.jdbcdemo.service.Service;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Controller {
    private final Service service;

    @Command("foo")
    void doSomething() {
        service.foo();
    }

    @Command("bar")
    void bar() {
        service.bar();
    }

    @Command()
    void help() {
        service.help();
    }
}
