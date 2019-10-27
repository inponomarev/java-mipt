package edu.phystech.jdbcdemo.dao;

import edu.phystech.jdbcdemo.db.ConnectionSource;
import edu.phystech.jdbcdemo.db.DbInit;
import edu.phystech.jdbcdemo.domain.Conference;
import org.h2.jdbcx.JdbcConnectionPool;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static edu.phystech.jdbcdemo.dao.TestData.JOKER;
import static edu.phystech.jdbcdemo.dao.TestData.JPOINT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

class ConferenceDaoTest {
    private ConnectionSource source = new ConnectionSource(
            JdbcConnectionPool.create("jdbc:h2:mem:database;DB_CLOSE_DELAY=-1", "", ""));
    private ConferenceDao dao =
            new ConferenceDao(source);

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

    private int getConferenceCount() throws SQLException {
        return source.statement(stmt -> {
            ResultSet resultSet = stmt.executeQuery("select count(*) from conference");
            resultSet.next();
            return resultSet.getInt(1);
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