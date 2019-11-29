package edu.phystech.jdbcdemo;

import edu.phystech.jdbcdemo.dao.ConferenceDao;
import edu.phystech.jdbcdemo.dao.SpeakerDao;
import edu.phystech.jdbcdemo.dao.TalkDao;
import edu.phystech.jdbcdemo.domain.Conference;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("conference")
public class ConferenceController {
    private final ConferenceDao conferenceDao;

    @PutMapping
    public void saveConference(Conference conference) throws SQLException {
        conferenceDao.saveConferences(Collections.singleton(conference));
    }

    @GetMapping
    public Set<Conference> printHello() throws SQLException {
        return conferenceDao.getConferences();
    }
}
