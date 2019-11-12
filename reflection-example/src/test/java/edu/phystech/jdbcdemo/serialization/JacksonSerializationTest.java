package edu.phystech.jdbcdemo.serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import edu.phystech.jdbcdemo.domain.Person;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JacksonSerializationTest {

    @Test
    void personIsSerialized() throws JsonProcessingException {
        Person p = new Person("Ivan", "Ivanov", LocalDate.of(1997, 11, 2));
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        String jsonString = mapper.writeValueAsString(p);
        JSONObject json = new JSONObject(jsonString);

        assertEquals("Ivan", json.get("firstName"));
        assertEquals("Ivanov", json.get("lastName"));
        assertEquals(Arrays.asList(1997,11,2), json.getJSONArray("birthDate").toList());
    }


}
