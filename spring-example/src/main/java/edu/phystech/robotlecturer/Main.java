package edu.phystech.robotlecturer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("edu.phystech.robotlecturer")
public class Main {

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(Main.class);
        RobotLecturer lecturer = ctx.getBean(RobotLecturer.class);
        lecturer.lecture();
    }
}
