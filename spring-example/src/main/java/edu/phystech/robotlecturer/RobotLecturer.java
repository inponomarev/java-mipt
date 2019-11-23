package edu.phystech.robotlecturer;


import edu.phystech.robotlecturer.annotation.InjectRandomInt;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class RobotLecturer {
    private final Lecture lecture;
    private final Speaker speaker;
    private final SlideShow slideShow;
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
