package edu.phystech.jdbcdemo.dao;

import edu.phystech.jdbcdemo.db.ConnectionSource;
import edu.phystech.jdbcdemo.domain.Conference;
import lombok.AllArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@AllArgsConstructor
public class ConferenceDao {
    private final ConnectionSource source;

    private Conference createConference(ResultSet resultSet) throws SQLException {
        return new Conference(resultSet.getInt("id"),
                resultSet.getString("name"));
    }

    public void saveConferences(Collection<Conference> conferences) throws SQLException {
        source.preparedStatement("insert into conference(id, name) values (?, ?)", insertConference -> {
            for (Conference conference : conferences) {
                insertConference.setInt(1, conference.getId());
                insertConference.setString(2, conference.getName());
                insertConference.execute();
            }
        });
    }

    public Set<Conference> getConferences() throws SQLException {
        return source.statement(stmt -> {
            Set<Conference> result = new HashSet<>();
            ResultSet resultSet = stmt.executeQuery("select * from conference");
            while (resultSet.next()) {
                result.add(createConference(resultSet));
            }
            return result;
        });
    }
}
