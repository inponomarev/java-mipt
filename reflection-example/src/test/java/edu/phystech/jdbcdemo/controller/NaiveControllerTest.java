package edu.phystech.jdbcdemo.controller;

import edu.phystech.jdbcdemo.service.Service;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class NaiveControllerTest {

    private final Service srv = Mockito.mock(Service.class);
    private final NaiveController naiveController = new NaiveController(srv);

    @Test
    void executeFooCommand() {
        naiveController.executeCommand("foo");
        Mockito.verify(srv, Mockito.times(1)).foo();
    }

    @Test
    void executeBarCommand() {
        naiveController.executeCommand("bar");
        Mockito.verify(srv, Mockito.times(1)).bar();
    }

    @Test
    void executeHelpCommand() {
        naiveController.executeCommand("asdfasdf");
        Mockito.verify(srv, Mockito.times(1)).help();
    }
}