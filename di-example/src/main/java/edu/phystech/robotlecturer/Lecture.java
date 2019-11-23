package edu.phystech.robotlecturer;

import edu.phystech.di.annotation.Benchmark;

import java.util.stream.Stream;

public interface Lecture {
    Stream<Slide> getSlides();
}
