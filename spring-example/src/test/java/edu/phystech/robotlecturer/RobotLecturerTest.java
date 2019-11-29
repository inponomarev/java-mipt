package edu.phystech.robotlecturer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = RobotLecturerTest.TestConfig.class)
class RobotLecturerTest {

    static final List<String> output = new ArrayList<>();

    /*Берём настоящих RobotLecturer и SlideShowImpl, но
    * "подделываем" Lecture и Speaker*/
    @Configuration
    @Import({RobotLecturer.class, SlideShowImpl.class})
    public static class TestConfig {

        @Bean
        public Lecture getLecture(){
           return new Lecture(){
               @Override
               public Stream<Slide> getSlides() {
                   return Stream.of(new Slide("foo", "bar"),
                           new Slide("foofoo", "barbar"));
               }
           };
        }

        @Bean
        public Speaker getSpeaker() {
            Speaker speaker = Mockito.mock(Speaker.class);

            return speaker;
        }
    }

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