package edu.phystech.jdbcdemo.service.dao;

import edu.phystech.jdbcdemo.domain.Conference;
import edu.phystech.jdbcdemo.domain.Speaker;
import edu.phystech.jdbcdemo.domain.Talk;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Repository
@AllArgsConstructor
public class TalkDao {
    private final JdbcTemplate jdbcTemplate;

    public void saveTalks(Collection<Talk> talks) throws SQLException {
        jdbcTemplate.execute("insert into talk(id, name, conferenceid) values (?, ?, ?)",
                (PreparedStatementCallback<?>) insertTalk -> {
                    jdbcTemplate.execute("insert into talkspeakers(talkid, speakerid) values(?, ?)",
                            (PreparedStatementCallback<?>) insertTalkSpeakers -> {
                                for (Talk talk : talks) {
                                    insertTalk.setInt(1, talk.getId());
                                    insertTalk.setString(2, talk.getName());
                                    insertTalk.setInt(3, talk.getConference().getId());
                                    insertTalk.execute();

                                    for (Speaker speaker : talk.getSpeakers()) {
                                        insertTalkSpeakers.setInt(1, talk.getId());
                                        insertTalkSpeakers.setInt(2, speaker.getId());
                                        insertTalkSpeakers.execute();
                                    }
                                }
                                return null;
                            });
                    return null;
                });
    }

    private Talk createTalk(ResultSet talkSet, ResultSet speakerSet) throws SQLException {
        Set<Speaker> speakers = new HashSet<>();
        while (speakerSet.next()) {
            speakers.add(new Speaker(
                    speakerSet.getInt("id"),
                    speakerSet.getString("name")));
        }
        return new Talk(talkSet.getInt("id"),
                talkSet.getString("name"),
                new Conference(talkSet.getInt("conferenceid"),
                        talkSet.getString("conferencename")),
                Collections.unmodifiableSet(speakers)
        );
    }

    public Set<Talk> getTalksByConference(Conference conference) throws SQLException {
        Set<Talk> result = new HashSet<>();
        jdbcTemplate.execute("select talk.id id, talk.name name, " +
                "conferenceid, conference.name conferencename " +
                "from talk inner join conference on conferenceid = conference.id " +
                "where conferenceid = ?", (PreparedStatementCallback<?>) selectTalks -> {
            jdbcTemplate.execute("select speaker.id id, speaker.name name " +
                    "from talkspeakers inner join speaker on speaker.id = speakerid " +
                    "where talkid = ?", (PreparedStatementCallback<?>) selectSpeakers -> {
                selectTalks.setInt(1, conference.getId());
                try (ResultSet talkSet = selectTalks.executeQuery()) {
                    while (talkSet.next()) {
                        selectSpeakers.setInt(1, talkSet.getInt("id"));
                        ResultSet speakerSet = selectSpeakers.executeQuery();
                        result.add(createTalk(talkSet, speakerSet));
                    }
                }
                return null;
            });
            return null;
        });
        return result;
    }
}
