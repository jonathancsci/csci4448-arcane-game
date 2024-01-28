package com.ooad;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArcaneTest {

    @Test
    public void constructorTest() {
        Arcane arcane = new Arcane();
        assertNotNull(arcane.getMaze());
    }

    @Test
    public void roomNameTest() {
        Arcane arcane = new Arcane();
        assertEquals("Northeast",arcane.getRoom(1,0).getName());
    }

    @Test
    public void getRoomTest() {
        Arcane arcane = new Arcane();
        assertNotNull(arcane.getRoom(1,1));
    }

    @Test
    public void getRoomSadPathTest() {
        Arcane arcane = new Arcane();
        assertNull(arcane.getRoom(-1,0));
    }
}
