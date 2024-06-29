package edu.phystech.jdbcdemo.serialization;

import edu.phystech.jdbcdemo.domain.Person;
import org.json.JSONObject;

import java.time.format.DateTimeFormatter;

public class NaivePersonSerializer {
    String serialize(Person person) {
        JSONObject result = new JSONObject();
        result.put("firstName", person.getFirstName());
        result.put("lastName", person.getLastName());
        result.put("birthDate", person.getBirthDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
        return result.toString();
    }
}
