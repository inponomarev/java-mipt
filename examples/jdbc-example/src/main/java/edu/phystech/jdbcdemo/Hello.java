package edu.phystech.jdbcdemo;

import edu.phystech.jdbcdemo.domain.Conference;
import edu.phystech.jdbcdemo.service.dao.ConferenceDao;
import edu.phystech.jdbcdemo.service.db.SimpleJdbcTemplate;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

public class Hello {


    public static void main(String[] args) throws IOException, SQLException {
        DataSource ds = null; //From Hikari
        final SimpleJdbcTemplate template = new SimpleJdbcTemplate(ds);
        final ConferenceDao conferenceDao = new ConferenceDao(template);
        for (Conference conference : conferenceDao.getConferences()) {
            System.out.println(conference.getName());
        }
    }

}
