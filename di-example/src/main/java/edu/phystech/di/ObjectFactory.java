package edu.phystech.di;

import org.reflections.ReflectionUtils;
import org.reflections.Reflections;

import javax.annotation.PostConstruct;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ObjectFactory {

    private final Reflections scanner = new Reflections("edu.phystech");
    private final List<ObjectConfigurator> configurators = new ArrayList<>();
    private final List<ProxyConfigurator> proxyConfigurators = new ArrayList<>();

    public ObjectFactory() throws ReflectiveOperationException {
        Set<Class<? extends ObjectConfigurator>> classes = scanner.getSubTypesOf(ObjectConfigurator.class);
        for (Class<? extends ObjectConfigurator> aClass : classes) {
           try {
               Constructor<? extends ObjectConfigurator> constructor = aClass.getConstructor(ObjectFactory.class);
               configurators.add(constructor.newInstance(this));
           } catch (NoSuchMethodException e){
               configurators.add(aClass.newInstance());
           }
        }
        Set<Class<? extends ProxyConfigurator>> set = scanner.getSubTypesOf(ProxyConfigurator.class);
        for (Class<? extends ProxyConfigurator> aClass : set) {
            proxyConfigurators.add(aClass.newInstance());
        }
    }

    public <T> T createObject(Class<? extends T> type) throws ReflectiveOperationException {
        //Находим реализацию запрошенного типа
        type = resolveImpl(type);
        //Создаём объект (с помощью конструктора по умолчанию, TODO)
        T t = type.newInstance();
        //Конфигурируем
        configure(t);
        //Запускаем методы PostConstruct
        invokeInitMethods(type, t);
        t = wrapWithProxyIfNeeded(type, t);
        return t;
    }

    private <T> Class<? extends T> resolveImpl(Class<? extends T> type) {
        if (type.isInterface()) {
            Set<Class<? extends T>> classes = scanner.getSubTypesOf((Class<T>) type);
            if (classes.size() != 1) {
                throw new RuntimeException("0 or more than one impl found for type " + type + " please update your config");
            }
            type = classes.iterator().next();
        }
        return type;
    }

    private <T> void configure(T t) throws ReflectiveOperationException {
        for (ObjectConfigurator configurator : configurators) {
            configurator.configure(t);
        }
    }

    private <T> T wrapWithProxyIfNeeded(Class<? extends T> type, T t) {
        for (ProxyConfigurator proxyConfigurator : proxyConfigurators) {
            t = proxyConfigurator.wrapWithPoxy(t, type);
        }
        return t;
    }

    private <T> void invokeInitMethods(Class<? extends T> type, T t) throws ReflectiveOperationException {
        Method[] methods = type.getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(PostConstruct.class)) {
                method.invoke(t);
            }
        }
    }


}
