package edu.phystech.robotlecturer;

import java.util.stream.Stream;

public interface Lecture {
    Stream<Slide> getSlides();
}
