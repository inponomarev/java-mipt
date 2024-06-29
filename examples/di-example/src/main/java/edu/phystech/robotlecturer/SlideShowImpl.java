package edu.phystech.robotlecturer;

import edu.phystech.di.annotation.Benchmark;

public class SlideShowImpl implements SlideShow {
    @Benchmark
    @Override
    public void show(String text) {
        System.out.printf("Slide: %s%n", text);
    }
}
