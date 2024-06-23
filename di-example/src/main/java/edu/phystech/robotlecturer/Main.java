package edu.phystech.robotlecturer;

import edu.phystech.di.ObjectFactory;

public class Main {
    public static void main(String[] args) throws ReflectiveOperationException {
        Lecturer lecturer =
                new ObjectFactory().createObject(RobotLecturer.class);
        lecturer.lecture();
    }
}
