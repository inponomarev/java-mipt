package edu.phystech.jdbcdemo.serialization;

import edu.phystech.jdbcdemo.domain.Person;
import edu.phystech.jdbcdemo.serialization.NaivePersonSerializer;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class NaivePersonSerializerTest {

    @Test
    void personIsSerialized(){
        Person p = new Person("Ivan", "Ivanov", LocalDate.of(1997, 11, 2));
        System.out.println(new NaivePersonSerializer().serialize(p));
        /*
        JSONObject json = new NaivePersonSerializer().serialize(p);
        assertEquals("Ivan", json.get("firstName"));
        assertEquals("Ivanov", json.get("lastName"));
        assertEquals("1997-11-02", json.get("birthDate"));*/
    }
}