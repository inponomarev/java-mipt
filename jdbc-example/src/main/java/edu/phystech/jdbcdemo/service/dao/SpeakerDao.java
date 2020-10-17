package edu.phystech.jdbcdemo.service.dao;

import edu.phystech.jdbcdemo.service.db.SimpleJdbcTemplate;
import edu.phystech.jdbcdemo.domain.Conference;
import edu.phystech.jdbcdemo.domain.Speaker;
import lombok.AllArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
public class SpeakerDao {
    private final SimpleJdbcTemplate source;

    private Speaker createSpeaker(ResultSet resultSet) throws SQLException {
        return new Speaker(resultSet.getInt("id"),
                resultSet.getString("name"));
    }

    public void saveSpeakers(Collection<Speaker> speakers) throws SQLException {
        source.preparedStatement("insert into speaker(id, name) values (?, ?)", insertSpeaker -> {
            for (Speaker speaker : speakers) {
                insertSpeaker.setInt(1, speaker.getId());
                insertSpeaker.setString(2, speaker.getName());
                insertSpeaker.execute();
            }
        });
    }

    public Set<Speaker> getSpeakers() throws SQLException {
        return source.statement(stmt -> {
            ResultSet resultSet = stmt.executeQuery("select * from speaker");
            Set<Speaker> result = new HashSet<>();
            while (resultSet.next()) {
                result.add(createSpeaker(resultSet));
            }
            return result;
        });

    }

    public Set<Speaker> getSpeakersByConference(Conference conference) throws SQLException {
        return source.preparedStatement("select speaker.id, speaker.name from speaker inner join " +
                "talkspeakers on talkspeakers.speakerid = speaker.id inner join " +
                "talk on talk.id = talkspeakers.talkid where talk.conferenceid = ?", selectSpeaker -> {
            Set<Speaker> result = new HashSet<>();
            selectSpeaker.setInt(1, conference.getId());
            ResultSet resultSet = selectSpeaker.executeQuery();
            while (resultSet.next()) {
                result.add(createSpeaker(resultSet));
            }
            return result;
        });
    }
}
