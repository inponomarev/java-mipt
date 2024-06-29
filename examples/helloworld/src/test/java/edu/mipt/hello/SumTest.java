package edu.mipt.hello;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SumTest {

    @Test
    void sum() {
        assertEquals(4, new Sum().sum(2, 2));
    }
}
