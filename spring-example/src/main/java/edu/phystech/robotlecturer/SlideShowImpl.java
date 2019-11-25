package edu.phystech.robotlecturer;

import edu.phystech.robotlecturer.aop.Benchmark;
import org.springframework.stereotype.Service;

@Service
public class SlideShowImpl implements SlideShow {

    @Benchmark
    @Override
    public void show(String text) {
        System.out.printf("Slide: %s%n", text);
    }
}
