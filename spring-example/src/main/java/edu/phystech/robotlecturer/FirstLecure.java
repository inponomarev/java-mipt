package edu.phystech.robotlecturer;

import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class FirstLecure implements Lecture {
    @Override
    public Stream<Slide> getSlides() {
        return Stream.of(new Slide("slide1", "blah-blah"),
                new Slide("slide2", "blah-blah-blah"));
    }
}
