package edu.phystech.jdbcdemo.service.db;

import edu.phystech.jdbcdemo.Hello;
import lombok.AllArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.stream.Collectors;

/**
 * Initializes database
 */
@AllArgsConstructor
public class DbInit {
    final SimpleJdbcTemplate source;

    private String getSQL(String name) throws IOException {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        Hello.class.getResourceAsStream(name),
                        StandardCharsets.UTF_8))) {
            return br.lines().collect(Collectors.joining("\n"));
        }
    }

    public void create() throws SQLException, IOException {
        String sql = getSQL("dbcreate.sql");
        source.statement(stmt -> {
            stmt.execute(sql);
        });
    }
}
