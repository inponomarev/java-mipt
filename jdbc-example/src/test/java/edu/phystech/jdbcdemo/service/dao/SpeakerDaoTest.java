package edu.phystech.jdbcdemo.service.dao;

import edu.phystech.jdbcdemo.service.db.SimpleJdbcTemplate;
import edu.phystech.jdbcdemo.service.db.DbInit;
import edu.phystech.jdbcdemo.domain.Speaker;
import org.h2.jdbcx.JdbcConnectionPool;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static edu.phystech.jdbcdemo.service.dao.TestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

class SpeakerDaoTest {
    private DataSource pool = JdbcConnectionPool.create("jdbc:h2:mem:database;DB_CLOSE_DELAY=-1", "", "");
    private SimpleJdbcTemplate source = new SimpleJdbcTemplate(pool);
    private SpeakerDao dao = new SpeakerDao(source);

    @BeforeEach
    void setupDB() throws IOException, SQLException {
        new DbInit(source).create();
    }

    @AfterEach
    void tearDownDB() throws SQLException, IOException {
        source.statement(stmt -> {
            stmt.execute("drop all objects;");
        });
    }

    private int getSpeakerCount() throws SQLException {
        return source.statement(stmt -> {
            ResultSet resultSet = stmt.executeQuery("select count(*) from speaker");
            resultSet.next();
            return resultSet.getInt(1);
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
    void getSpeakersByConference() throws SQLException {
        dao.saveSpeakers(Arrays.asList(
                EGOROV, TOLKACHEV, BORISOV, VALEEV));

        new ConferenceDao(source).saveConferences(Arrays.asList(
                JOKER, JPOINT));

        new TalkDao(source).saveTalks(Arrays.asList(JAVA914, REACTIVEORNOT, SIMPSON, TESTCONTAINERS));

        Set<Speaker> speakers = dao.getSpeakersByConference(JPOINT);
        assertEquals(new HashSet<>(Arrays.asList(EGOROV, BORISOV, TOLKACHEV)), speakers);

    }
}
