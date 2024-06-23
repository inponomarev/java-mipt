package edu.phystech.robotlecturer;


import edu.phystech.robotlecturer.annotation.InjectRandomInt;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class RobotLecturer {
    private final Lecture lecture;
    private final Speaker speaker;
    private final SlideShow slideShow;
    @InjectRandomInt(min = 1, max = 3)
    private int repeat;


    private final Map<String, Speaker> m;

    public void lecture() {
        System.out.println(m.toString());

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
