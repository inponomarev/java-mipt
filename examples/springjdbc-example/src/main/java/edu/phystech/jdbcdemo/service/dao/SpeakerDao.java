package edu.phystech.jdbcdemo.service.dao;

import edu.phystech.jdbcdemo.domain.Conference;
import edu.phystech.jdbcdemo.domain.Speaker;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Repository
@AllArgsConstructor
public class SpeakerDao {
    private final JdbcTemplate jdbcTemplate;

    private Speaker createSpeaker(ResultSet resultSet) throws SQLException {
        return new Speaker(resultSet.getInt("id"),
                resultSet.getString("name"));
    }

    public void saveSpeakers(Collection<Speaker> speakers) throws SQLException {
        jdbcTemplate.execute("insert into speaker(id, name) values (?, ?)",
                (PreparedStatementCallback<?>) ps -> {
                    for (Speaker speaker : speakers) {
                        ps.setInt(1, speaker.getId());
                        ps.setString(2, speaker.getName());
                        ps.execute();
                    }
                    return null;
                });
    }

    public Set<Speaker> getSpeakers() throws SQLException {
        return jdbcTemplate.query("select * from speaker", resultSet -> {
            Set<Speaker> result = new HashSet<>();
            while (resultSet.next()) {
                result.add(createSpeaker(resultSet));
            }
            return result;
        });

    }

    public Set<Speaker> getSpeakersByConference(Conference conference) throws SQLException {
        return jdbcTemplate.query("select speaker.id, speaker.name from speaker inner join " +
                        "talkspeakers on talkspeakers.speakerid = speaker.id inner join " +
                        "talk on talk.id = talkspeakers.talkid where talk.conferenceid = ?",
                ps -> ps.setInt(1, conference.getId()),
                resultSet -> {
                    Set<Speaker> result = new HashSet<>();
                    while (resultSet.next()) {
                        result.add(createSpeaker(resultSet));
                    }
                    return result;
                });
    }
}
