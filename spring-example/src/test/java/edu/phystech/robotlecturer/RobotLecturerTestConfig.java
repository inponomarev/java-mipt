package edu.phystech.robotlecturer;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.stream.Stream;

@Configuration
@Import({RobotLecturer.class, SlideShowImpl.class})
public class RobotLecturerTestConfig {

    @Bean
    public Lecture getLecture() {
        return new Lecture() {
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
