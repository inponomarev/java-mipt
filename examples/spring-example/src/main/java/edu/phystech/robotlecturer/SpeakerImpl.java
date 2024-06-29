package edu.phystech.robotlecturer;

import org.springframework.stereotype.Service;

@Service
public class SpeakerImpl implements Speaker {
    @Override
    public void speak(String text) {
        System.out.printf("Speaking: %s%n", text);
    }
}
