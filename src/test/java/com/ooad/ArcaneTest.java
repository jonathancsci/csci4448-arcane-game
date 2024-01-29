package com.ooad;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class ArcaneTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStream() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
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
        String[] expected = {"ARCANE MAZE: turn 1","  Northwest","  Northeast","  Southwest","  Southeast"};
        Arcane.main(new String[0]);
        String[] printedLines = outContent.toString().split("\n");
        System.out.println(printedLines);
        for (int i = 0; i < expected.length; i++) {
            assertTrue(Arrays.asList(printedLines).contains(expected[i]));
        }
    }

    @Test
    public void printTest() {
        String[] expected = {"ARCANE MAZE: turn 1","  Northwest","  Northeast","  Southwest","  Southeast"};
        Arcane arcane = new Arcane();
        arcane.runGame(1);
        String[] printedLines = outContent.toString().split("\n");
        System.out.println(printedLines);
        for (int i = 0; i < expected.length; i++) {
            assertTrue(Arrays.asList(printedLines).contains(expected[i]));
        }
    }
}
