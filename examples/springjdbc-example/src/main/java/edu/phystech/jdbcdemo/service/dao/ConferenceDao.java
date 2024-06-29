package edu.phystech.jdbcdemo.service.dao;

import edu.phystech.jdbcdemo.domain.Conference;
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
public class ConferenceDao {
    private final JdbcTemplate jdbcTemplate;

    private Conference createConference(ResultSet resultSet) throws SQLException {
        return new Conference(resultSet.getInt("id"),
                resultSet.getString("name"));
    }

    public void saveConferences(Collection<Conference> conferences) throws SQLException {
        jdbcTemplate.execute("insert into conference(id, name) values (?, ?)",
                (PreparedStatementCallback<Void>) ps -> {
                    for (Conference conference : conferences) {
                        ps.setInt(1, conference.getId());
                        ps.setString(2, conference.getName());
                        ps.execute();
                    }
                    return null;
                }
        );
    }

    public Set<Conference> getConferences() throws SQLException {

        return jdbcTemplate.query("select * from conference",
                rs -> {
                    Set<Conference> result = new HashSet<>();
                    while (rs.next()) {
                        result.add(createConference(rs));
                    }
                    return result;
                });

    }
}
