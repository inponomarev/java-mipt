package edu.phystech.jdbcdemo;

import edu.phystech.jdbcdemo.service.db.DbInit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.sql.SQLException;

@SpringBootApplication(scanBasePackages = "edu.phystech.jdbcdemo")
public class Main {
    @Autowired
    private DbInit dbInit;

    @PostConstruct
    void init() throws IOException, SQLException {
        System.out.println(">>>>>Main config!");
        dbInit.create();
    }

    public static void main(String[] args) throws SQLException, IOException {
        SpringApplication.run(Main.class, args);
    }
}
