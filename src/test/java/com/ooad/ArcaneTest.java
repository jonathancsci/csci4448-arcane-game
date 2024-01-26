package com.ooad;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArcaneTest {

    @Test
    public void helloTest() {
        Arcane arcane = new Arcane();
        assertEquals("Hello World!", arcane.helloWorld());
    }
}
