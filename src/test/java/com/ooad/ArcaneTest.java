package com.ooad;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
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
    public void runGameTest() {
        String[] expected = {"    Adventurer Tim(health: 4) is here","    Creature Cobblebeast(health: 4) is here"};
        Arcane.main(new String[0]);
        String[] printedLines = outContent.toString().split("\n");
        boolean timHurt = Arrays.asList(printedLines).contains(expected[0]);
        boolean monsterHurt = Arrays.asList(printedLines).contains(expected[1]);
        assertTrue(timHurt || monsterHurt, "Expected an entity to take damage.");
    }

    @Test
    public void toStringTest() {
        String[] expected = {"ARCANE MAZE: turn 1","  Northwest","    Adventurer Tim(health: 5) is here","    Creature Cobblebeast(health: 5) is here"};
        Arcane arcane = new Arcane();
        String[] printedLines = arcane.toString().split("\n");
        for (int i = 0; i < expected.length; i++) {
            assertTrue(Arrays.asList(printedLines).contains(expected[i]), "The first turn of the game did not print what was expected.");
        }
    }

    @Test
    public void endGameTest() {
        String[] expected = {"Tim was defeated!","Cobblebeast was defeated!"};
        String[] expected2 = {"    Adventurer Tim(health: 0) is here","    Creature Cobblebeast(health: 0) is here"};
        Arcane.main(new String[0]);
        String[] printedLines = outContent.toString().split("\n");
        boolean timDied = Arrays.asList(printedLines).contains(expected[0]);
        boolean monsterDied = Arrays.asList(printedLines).contains(expected[1]);
        boolean timZero = Arrays.asList(printedLines).contains(expected2[0]);
        boolean monsterZero = Arrays.asList(printedLines).contains(expected2[1]);
        assertTrue((timDied && timZero) ^ (monsterDied && monsterZero), "Expected dead entity to have 0 health");
        assertFalse((timDied && monsterZero) || (monsterDied && timZero), "Expected only one entity to die.");
    }

    @Test
    public void setRoomNamesTest() {
        Arcane arcane = new Arcane();
        assertEquals("Northeast",arcane.getRoom(1,0).getName());
    }

    @Test
    public void instantiateAndGetRoomTest() {
        Arcane arcane = new Arcane();
        assertNotNull(arcane.getRoom(1,1));
        assertNull(arcane.getRoom(-1,0));
    }

    @Test
    public void roomConnectionsTest() {
        Arcane arcane = new Arcane();
        ArrayList<Room> connected = arcane.getRoom(0,0).getConnectedRooms();
        System.out.println(connected);
        assertTrue(connected.contains(arcane.getRoom(1,0)));
        assertTrue(connected.contains(arcane.getRoom(0,1)));
        assertFalse(connected.contains(arcane.getRoom(1,1)));
    }

    @Test
    public void getMazeTest() {
        Arcane arcane = new Arcane();
        assertNotNull(arcane.getMaze());
    }

}
