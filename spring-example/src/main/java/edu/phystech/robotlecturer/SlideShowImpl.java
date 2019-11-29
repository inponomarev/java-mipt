package edu.phystech.robotlecturer;

import edu.phystech.robotlecturer.aop.Benchmark;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Qualifier
@Service
public class SlideShowImpl implements SlideShow {

    @Benchmark
    @Override
    public void show(String text) {
        System.out.printf("Slide: %s%n", text);
    }
}
