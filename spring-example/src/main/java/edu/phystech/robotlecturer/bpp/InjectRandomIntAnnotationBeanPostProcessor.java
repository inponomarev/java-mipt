package edu.phystech.robotlecturer.bpp;

import edu.phystech.robotlecturer.annotation.InjectRandomInt;
import org.springframework.beans.BeansException;
import org.springframework.beans.NotWritablePropertyException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Component
@Order
public class InjectRandomIntAnnotationBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        Class<?> type = bean.getClass();
        Set<Field> allFields = ReflectionUtils.getAllFields(type);
        for (Field field : allFields) {
            InjectRandomInt annotation = field.getAnnotation(InjectRandomInt.class);
            if (annotation != null) {
                int min = annotation.min();
                int max = annotation.max();
                int value = ThreadLocalRandom.current().nextInt(min, max + 1);
                field.setAccessible(true);
                try {
                    field.set(bean, value);
                } catch (IllegalAccessException e) {
                    throw new NotWritablePropertyException(bean.getClass(),
                            field.getName());
                }
            }
        }
        return bean;
    }
}
