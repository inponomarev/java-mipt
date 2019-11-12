package edu.phystech.jdbcdemo.controller;

import org.reflections.ReflectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CommandDispatcher {
    private final Object controller;
    private final Map<String, Method> methodHashMap = new HashMap<>();
    private Method defaultMethod;

    public CommandDispatcher(Object controller) {
        Objects.requireNonNull(controller);
        this.controller = controller;
        for (Method method : ReflectionUtils.getAllMethods(controller.getClass())) {
            Command command = method.getAnnotation(Command.class);
            if (command != null) {
                String commandId = command.value();
                if (commandId.isEmpty()) {
                    defaultMethod = method;
                } else {
                    methodHashMap.put(commandId, method);
                }
            }
        }
    }


    public void executeCommand(String command) throws InvocationTargetException, IllegalAccessException {
        Method method = methodHashMap.getOrDefault(command, defaultMethod);
        if (method != null){
            method.invoke(controller);
        }
    }
}
