package csci.ooad.arcane;

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

    //Print stream testing set-up: https://stackoverflow.com/questions/1119385/junit-test-for-system-out-println
    @BeforeEach
    public void setUpStream() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void resetStream() {
        System.setOut(originalOut);
    }


    public Arcane setUp(int width, int height, int advNum, int creNum, int foodNum) {
        Arcane arcane = new Arcane();
        Room[] rooms = new Room[width*height];
        for(int i=0; i<rooms.length; i++) {
            rooms[i] = new Room();
        }
        Adventurer[] adventurers = new Adventurer[advNum];
        for(int i=0; i<adventurers.length; i++) {
            adventurers[i] = new Adventurer();
        }
        Creature[] creatures = new Creature[creNum];
        for(int i=0; i<creatures.length; i++) {
            creatures[i] = new Creature();
        }
        Food[] food = new Food[foodNum];
        for(int i=0; i<food.length; i++) {
            food[i] = new Food();
        }
        return arcane;
    }

    public Arcane setUpNineRoomGame() {
        return setUp(3,3,2,2,10);
    }

    public Arcane setUpFourRoomGame() {
        return setUp(2,2,1,1,4);
    }

    @Test
    public void runGameTest() {

    }

    @Test
    public void turnTest() {
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
        String[] expected = {"Tim was defeated!\r","Cobblebeast was defeated!\r"};
        Arcane.main(new String[0]);
        String[] printedLines = outContent.toString().split("\n");
        boolean timDied = Arrays.asList(printedLines).contains(expected[0]);
        boolean monsterDied = Arrays.asList(printedLines).contains(expected[1]);
        assertTrue((timDied) || (monsterDied), "Expected only one entity to die");
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
