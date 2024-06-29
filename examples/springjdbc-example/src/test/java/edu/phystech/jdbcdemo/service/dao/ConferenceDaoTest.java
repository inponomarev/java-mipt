package edu.phystech.jdbcdemo.service.dao;

import edu.phystech.jdbcdemo.service.db.DbInit;
import edu.phystech.jdbcdemo.domain.Conference;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import static edu.phystech.jdbcdemo.service.dao.TestData.JOKER;
import static edu.phystech.jdbcdemo.service.dao.TestData.JPOINT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

@SpringBootTest(classes = DaoTestConfiguration.class)
class ConferenceDaoTest {

    @Autowired
    DataSource ds;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ConferenceDao dao;

    @BeforeEach
    void setupDB(@Autowired DbInit dbInit) throws IOException, SQLException {
        dbInit.create();
    }

    @AfterEach
    void tearDownDB() throws SQLException, IOException {
        jdbcTemplate.execute("drop all objects;");
    }

    private int getConferenceCount() throws SQLException {
        return jdbcTemplate.query("select count(*) from conference",
                rs->{
                    rs.next();
                    return rs.getInt(1);
                });
    }

    private Collection<Conference> getTestConferences() {
        return Arrays.asList(JPOINT, JOKER);
    }

    @Test
    void saveConferences() throws SQLException {
        Collection<Conference> testConferences = getTestConferences();
        assertEquals(0, getConferenceCount());
        dao.saveConferences(testConferences);
        assertEquals(testConferences.size(), getConferenceCount());
    }

    @Test
    void getConferences() throws SQLException {
        Collection<Conference> testConferences = getTestConferences();
        dao.saveConferences(testConferences);
        Set<Conference> conferences = dao.getConferences();
        assertNotSame(testConferences, conferences);
        assertEquals(new HashSet<>(testConferences), conferences);
    }
}