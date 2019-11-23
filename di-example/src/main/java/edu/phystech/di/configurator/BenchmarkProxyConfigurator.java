package edu.phystech.di.configurator;

import edu.phystech.di.ProxyConfigurator;
import edu.phystech.di.annotation.Benchmark;
import org.reflections.ReflectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class BenchmarkProxyConfigurator implements ProxyConfigurator {
    @Override
    public <T> T wrapWithPoxy(T t, Class<? extends T> type) {
        boolean isProxyNeeded = type.isAnnotationPresent(Benchmark.class)
                || !ReflectionUtils.getAllMethods(type, method -> method.isAnnotationPresent(Benchmark.class)).isEmpty();
        if (isProxyNeeded) {
            return (T) Proxy.newProxyInstance(type.getClassLoader(),
                    type.getInterfaces(), (proxy, method, args) -> {
                        Method classMethod = type.getMethod(method.getName(),
                                method.getParameterTypes());
                        return invoke(t, type, method, args, classMethod);
                    });
        }
        return t;
    }

    private Object invoke(Object t, Class type, Method method, Object[] args,
                          Method classMethod) throws ReflectiveOperationException {
        if (classMethod.isAnnotationPresent(Benchmark.class)
                || type.isAnnotationPresent(Benchmark.class)) {
            System.out.printf("[[[BENCHMARK method %s%n", method.getName());
            long start = System.nanoTime();
            Object retVal = method.invoke(t, args);
            long end = System.nanoTime();
            System.out.printf("Time: %dns]]]%n", end - start);
            return retVal;
        } else {
            return method.invoke(t, args);
        }
    }
}
