package edu.phystech.robotlecturer;

import org.springframework.stereotype.Service;

@Service
public class SlideShowImpl implements SlideShow {
    @Override
    public void show(String text) {
        System.out.printf("Slide: %s%n", text);
    }
}
