package edu.phystech.jdbcdemo.controller;

import edu.phystech.jdbcdemo.service.Service;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.lang.reflect.InvocationTargetException;

class CommandDispatcherTest {
    Service srv = Mockito.mock(Service.class);
    CommandDispatcher dispatcher = new CommandDispatcher(new Controller(srv));

    @Test
    void executeFooCommand() throws InvocationTargetException, IllegalAccessException {
        dispatcher.executeCommand("foo");
        Mockito.verify(srv, Mockito.times(1)).foo();
    }

    @Test
    void executeBarCommand() throws InvocationTargetException, IllegalAccessException {
        dispatcher.executeCommand("bar");
        Mockito.verify(srv, Mockito.times(1)).bar();
    }

    @Test
    void executeHelpCommand() throws InvocationTargetException, IllegalAccessException {
        dispatcher.executeCommand("asdfasdf");
        Mockito.verify(srv, Mockito.times(1)).help();
    }
}