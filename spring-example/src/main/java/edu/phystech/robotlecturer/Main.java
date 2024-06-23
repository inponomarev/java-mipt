package edu.phystech.robotlecturer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;


@ComponentScan("edu.phystech.robotlecturer")
//@PropertySource("classpath:config.properties")
@EnableAspectJAutoProxy
public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(Main.class);
        RobotLecturer lecturer = ctx.getBean(RobotLecturer.class);
        lecturer.lecture();
    }
}
