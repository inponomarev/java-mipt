package edu.phystech.robotlecturer;

import java.util.stream.Stream;

public class FirstLecure implements Lecture {
    @Override
    public Stream<Slide> getSlides() {
        return Stream.of(new Slide("slide1", "blah-blah"),
                new Slide("slide2", "blah-blah-blah"));
    }
}
