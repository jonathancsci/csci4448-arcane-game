package csci.ooad.arcane;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class ArcaneTest {
    @Test
    public void runGameTest() {
        Arcane arcane = new Arcane(MazeFactory.createMaze(9,2,2,10));
        arcane.runGame();
        assertTrue(arcane.isGameOver(),"The game should end");
    }

    @Test
    public void runBigGridGameTest() {
        Arcane arcane = new Arcane(MazeFactory.buildNineRoomGrid());
        arcane.runGame();
        assertTrue(arcane.isGameOver(),"The game should end");
    }

    @Test
    public void runSmallGridGameTest() {
        Arcane arcane = new Arcane(MazeFactory.buildFourRoomGrid());
        arcane.runGame();
        assertTrue(arcane.isGameOver(),"The game should end");
    }

    @Test
    public void isGameOverTest() {
        Adventurer adventurer = new Adventurer();
        Creature creature1 = new Creature();
        Creature creature2 = new Creature();
        Maze maze = new MazeFactory.MazeBuilder()
                .createRooms(6)
                .addAdventurer(adventurer)
                .addCreature(creature1)
                .addCreature(creature2).build();
        Arcane arcane = new Arcane(maze);
        assertTrue(arcane.isGameOver(),"Game should be over with no adventurers or creatures");
        assertFalse(arcane.isGameOver(),"Game should not be over when entities are added");
        adventurer.setHealth(0);
        assertTrue(arcane.isGameOver(),"Game should end when all adventurers are dead");
        adventurer.setHealth(1);
        assertFalse(arcane.isGameOver(),"Game should base decision on current state");
        creature1.setHealth(0);
        assertFalse(arcane.isGameOver(),"Game shouldn't end when only one monster is dead");
        creature2.setHealth(0);
        assertTrue(arcane.isGameOver(),"Game should end when all monsters are dead");
    }

    @Test
    public void toStringTest() {
        String[] expected = {"ARCANE MAZE: turn 1"};
        Arcane arcane = new Arcane(MazeFactory.buildFourRoomGrid());
        String[] printedLines = arcane.toString().split("\n");
        for (int i = 0; i < expected.length; i++) {
            assertTrue(Arrays.asList(printedLines).contains(expected[i]), "The first turn of the game did not print what was expected.");
        }
    }
}
