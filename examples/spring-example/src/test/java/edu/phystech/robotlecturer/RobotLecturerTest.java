package edu.phystech.robotlecturer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = RobotLecturerTestConfig.class)
class RobotLecturerTest {

    static final List<String> output = new ArrayList<>();


    @Autowired
    RobotLecturer lecturer;

    @Test
    void lecturerReadsCommentsToSlides(@Autowired Speaker speaker) {
        lecturer.lecture();
        Mockito.verify(speaker, Mockito.times(1))
                .speak("bar");
        Mockito.verify(speaker, Mockito.times(1))
                .speak("barbar");
    }
}