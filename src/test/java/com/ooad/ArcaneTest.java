package com.ooad;

// There are issues with imports here, these two don't work for me
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ArcaneTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStream() {
        System.setOut(new PrintStream(outContent));
    }

    @Before
    public void resetStream() {
        System.setOut(originalOut);
    }

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

    @Test
    public void runGameTest() {
        Arcane arcane = new Arcane();
        arcane.runGame();
        String[] printedLines = outContent.toString().split("\n");
        System.out.println(printedLines);
        assertTrue(printedLines.length > 12); //6 lines per turn*minimum of 2 turns, later increase this to 8 lines for entities and the minimum turns needed for game over
    }

    @Test
    public void printTest() {
        Arcane arcane = new Arcane();
        arcane.runGame(1);
        String[] printedLines = outContent.toString().split("\n");
        System.out.println(printedLines);
        assertEquals("ARCANE MAZE: turn 1",printedLines[0]);
        assertEquals("  Northwest",printedLines[1]);
    }
}
