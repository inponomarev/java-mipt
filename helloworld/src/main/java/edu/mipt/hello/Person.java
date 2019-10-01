package edu.mipt.hello;


import lombok.AllArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
public class Person implements Comparable<Person>{
    private final int age;
    private final String name;

    @Override
    public int compareTo(Person o) {
        return 0;
    }
}


class TEST{

    public static void main(String[] args) {
        new Person(39, "Ivan");

    }
}