package edu.phystech.jdbcdemo.service.dao;

import edu.phystech.jdbcdemo.service.db.DbInit;
import edu.phystech.jdbcdemo.domain.Speaker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static edu.phystech.jdbcdemo.service.dao.TestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

@SpringBootTest(classes = DaoTestConfiguration.class)
class SpeakerDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SpeakerDao dao;

    @BeforeEach
    void setupDB(@Autowired DbInit dbInit) throws IOException, SQLException {
        dbInit.create();
    }

    @AfterEach
    void tearDownDB() throws SQLException, IOException {
        jdbcTemplate.execute("drop all objects;");
    }

    private int getSpeakerCount() throws SQLException {
        return jdbcTemplate.query("select count(*) from speaker", rs->{
            rs.next();
            return rs.getInt(1);
        });
    }

    private Collection<Speaker> getTestSpeakers() {
        return Arrays.asList(EGOROV, TOLKACHEV, BORISOV, VALEEV);
    }

    @Test
    void saveSpeakers() throws SQLException {
        Collection<Speaker> testSpeakers = getTestSpeakers();
        assertEquals(0, getSpeakerCount());
        dao.saveSpeakers(testSpeakers);
        assertEquals(testSpeakers.size(), getSpeakerCount());
    }

    @Test
    void getSpeakers() throws SQLException {
        Collection<Speaker> testSpeakers = getTestSpeakers();
        dao.saveSpeakers(testSpeakers);
        Set<Speaker> speakers = dao.getSpeakers();
        assertNotSame(testSpeakers, speakers);
        assertEquals(new HashSet<>(testSpeakers), speakers);
    }

    @Test
    void getSpeakersByConference(@Autowired ConferenceDao conferenceDao,
                                 @Autowired TalkDao talkDao) throws SQLException {
        dao.saveSpeakers(Arrays.asList(
                EGOROV, TOLKACHEV, BORISOV, VALEEV));

        conferenceDao.saveConferences(Arrays.asList(
                JOKER, JPOINT));

        talkDao.saveTalks(Arrays.asList(JAVA914, REACTIVEORNOT, SIMPSON, TESTCONTAINERS));

        Set<Speaker> speakers = dao.getSpeakersByConference(JPOINT);
        assertEquals(new HashSet<>(Arrays.asList(EGOROV, BORISOV, TOLKACHEV)), speakers);

    }
}