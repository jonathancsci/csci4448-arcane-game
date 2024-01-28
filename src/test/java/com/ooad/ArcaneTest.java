package com.ooad;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class ArcaneTest {

    @Test
    public void helloTest() {
        Arcane arcane = new Arcane();
        assertEquals("Hello World!", arcane.helloWorld());
    }
}
