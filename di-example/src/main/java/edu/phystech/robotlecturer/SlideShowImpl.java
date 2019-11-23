package edu.phystech.robotlecturer;

public class SlideShowImpl implements SlideShow {
    @Override
    public void show(String text) {
        System.out.printf("Slide: %s%n", text);
    }
}
