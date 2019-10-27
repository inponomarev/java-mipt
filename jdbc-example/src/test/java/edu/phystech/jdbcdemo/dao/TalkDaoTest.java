package edu.phystech.jdbcdemo.dao;

import edu.phystech.jdbcdemo.db.ConnectionSource;
import edu.phystech.jdbcdemo.db.DbInit;
import edu.phystech.jdbcdemo.domain.Conference;
import edu.phystech.jdbcdemo.domain.Speaker;
import edu.phystech.jdbcdemo.domain.Talk;
import org.h2.jdbcx.JdbcConnectionPool;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static edu.phystech.jdbcdemo.dao.TestData.*;
import static org.junit.jupiter.api.Assertions.*;

class TalkDaoTest {
    private ConnectionSource source = new ConnectionSource(
            JdbcConnectionPool.create("jdbc:h2:mem:database;DB_CLOSE_DELAY=-1", "", ""));
    private TalkDao dao =
            new TalkDao(source);


    @BeforeEach
    void setupDB() throws IOException, SQLException {
        new DbInit(source).create();

        new SpeakerDao(source).saveSpeakers(Arrays.asList(
                EGOROV, TOLKACHEV, BORISOV, VALEEV));

        new ConferenceDao(source).saveConferences(Arrays.asList(
                JOKER, JPOINT));
    }

    @AfterEach
    void tearDownDB() throws SQLException, IOException {
        source.statement(stmt -> {
            stmt.execute("drop all objects;");
        });
    }


    private int getTalkCount() throws SQLException {
        return source.statement(stmt -> {
            ResultSet resultSet = stmt.executeQuery("select count(*) from talk");
            resultSet.next();
            return resultSet.getInt(1);
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