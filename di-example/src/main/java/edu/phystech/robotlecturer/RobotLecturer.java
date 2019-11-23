package edu.phystech.robotlecturer;

import edu.phystech.di.annotation.InjectByType;
import edu.phystech.di.annotation.InjectRandomInt;

import javax.annotation.PostConstruct;

public class RobotLecturer {
    @InjectByType
    private Lecture lecture;
    @InjectByType
    private Speaker speaker;
    @InjectByType
    private SlideShow slideShow;

    @InjectRandomInt(min = 1, max = 3)
    private int repeat;

    public void lecture() {
        lecture.getSlides().forEach(
                slide -> {
                    slideShow.show(slide.getText());
                    speaker.speak(slide.getComment());
                }
        );
    }

    @PostConstruct
    public void init() {
        for (int i = 0; i < repeat; i++)
            speaker.speak("Всем привет");
    }
}
