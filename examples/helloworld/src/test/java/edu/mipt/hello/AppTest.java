package edu.mipt.hello;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {

    @Test
    void testFoo() {
        assertEquals(10, 5 << 1);
        assertEquals(2, 5 >> 1);
        assertEquals(-10, -5 << 1);
        assertEquals(-3, -5 >> 1);

    }


}
