package edu.phystech.jdbcdemo.service.dao;

import edu.phystech.jdbcdemo.service.db.DbInit;
import edu.phystech.jdbcdemo.domain.Talk;
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

@SpringBootTest(classes = DaoTestConfiguration.class)
class TalkDaoTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private TalkDao dao;

    @BeforeEach
    void setupDB(@Autowired DbInit dbInit,
                 @Autowired ConferenceDao conferenceDao,
                 @Autowired SpeakerDao speakerDao) throws IOException, SQLException {
        dbInit.create();
        speakerDao.saveSpeakers(Arrays.asList(
                EGOROV, TOLKACHEV, BORISOV, VALEEV));
        conferenceDao.saveConferences(Arrays.asList(
                JOKER, JPOINT));
    }

    @AfterEach
    void tearDownDB() throws SQLException, IOException {
        jdbcTemplate.execute("drop all objects;");
    }


    private int getTalkCount() throws SQLException {
        return jdbcTemplate.query("select count(*) from talk", rs->{
            rs.next();
            return rs.getInt(1);
        });
    }

    private Collection<Talk> getTestTalks() {
        return Arrays.asList(JAVA914, REACTIVEORNOT, SIMPSON, TESTCONTAINERS);
    }


    @Test
    void saveTalks() throws SQLException {
        Collection<Talk> testTalks = getTestTalks();
        assertEquals(0, getTalkCount());
        dao.saveTalks(testTalks);
        assertEquals(testTalks.size(), getTalkCount());
    }

    @Test
    void getTalksByConference() throws SQLException {
        dao.saveTalks(getTestTalks());
        Set<Talk> talks = dao.getTalksByConference(JOKER);

        assertEquals(new HashSet<>(Arrays.asList(JAVA914, TESTCONTAINERS)),
                talks);
    }


}