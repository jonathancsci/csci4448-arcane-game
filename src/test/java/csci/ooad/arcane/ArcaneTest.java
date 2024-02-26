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
        return new Arcane(new MazeFactory().createGrid(width,height,advNum,creNum,foodNum));
    }
/*
    public Arcane setUpNineRoomGame() {
        return setUp(3,3,2,2,10);
    }

    public Arcane setUpFourRoomGame() {
        return setUp(2,2,1,1,4);
    }

    @Test
    public void runGameTest() {
        Arcane arcane = setUpNineRoomGame();
        arcane.runGame();
        assertTrue(arcane.isGameOver(),"The game should end");
    }

    @Test
    public void runSmallerGameTest() {
        Arcane arcane = setUpFourRoomGame();
        arcane.runGame();
        assertTrue(arcane.isGameOver(),"The game should end");
    }

    @Test
    public void turnTest() {
        //set-up
        Adventurer adventurer = new Adventurer("Bill",200);
        Creature creature = new Creature("Ogre",1);
        Food food = new Food();
        Room[] rooms = {new Room(), new Room()};
        Arcane arcane = new Arcane();
        arcane.mazeRoomPrep(rooms,1,2);
        arcane.addAdventurer(adventurer, rooms[0]);
        arcane.addCreature(creature, rooms[1]);
        arcane.addFood(food, rooms[1]);
        //run and assert
        assertEquals(adventurer.getCurrentRoom(),rooms[0],"Initial state");
        arcane.turn();
        assertEquals(adventurer.getCurrentRoom(),rooms[1],"Adventurer should move when in empty room.");
        arcane.turn();
        assertEquals(adventurer.getCurrentRoom(),rooms[1],"Adventurer should fight when in a room with a monster.");
        assertTrue(rooms[1].isThereFood(),"Adventurer should leave the food alone when entering the room.");
        while(!creature.isDead() && !adventurer.isDead()) {
            arcane.turn();
        }
        assertTrue(creature.isDead(),"This test failed randomly, the 1 hp monster beat the 200 hp adventurer");
        assertTrue(rooms[1].isThereFood(),"The adventurer should wait until the monster is dead to eat");
        arcane.turn();
        assertFalse(rooms[1].isThereFood(),"The adventurer should eat the food once the monster is dead");
    }

    @Test
    public void combatTest() {
        //set-up
        Adventurer adventurer = new Adventurer("Bill",100);
        Creature creature = new Creature("Ogre",100);
        Room[] rooms = {new Room(), new Room()};
        Arcane arcane = new Arcane();
        arcane.mazeRoomPrep(rooms,1,2);
        //run and assert
        arcane.combat(adventurer,creature);
        arcane.combat(adventurer,creature);
        arcane.combat(adventurer,creature);
        arcane.combat(adventurer,creature);
        arcane.combat(adventurer,creature);
        arcane.combat(adventurer,creature);
        assertTrue(adventurer.getHealth()<100 || creature.getHealth()<100,"After 6 combats, someone should take damage");
        assertFalse(adventurer.isDead() || creature.isDead(),"After 6 combats, neither 100 hp combatant should die");
    }

    @Test
    public void isGameOverTest() {
        Arcane arcane = new Arcane();
        assertTrue(arcane.isGameOver(),"Game should be over with no adventurers or creatures");
        Room[] rooms = {new Room()};
        arcane.mazeRoomPrep(rooms,1,1);
        Adventurer adventurer = new Adventurer();
        Creature creature1 = new Creature();
        Creature creature2 = new Creature();
        arcane.addAdventurer(adventurer);
        arcane.addCreature(creature1);
        arcane.addCreature(creature2);
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
        Arcane arcane = setUpNineRoomGame();
        String[] printedLines = arcane.toString().split("\n");
        for (int i = 0; i < expected.length; i++) {
            assertTrue(Arrays.asList(printedLines).contains(expected[i]), "The first turn of the game did not print what was expected.");
        }
    }

    @Test
    public void roomNamingTest() {
        Arcane arcane = setUpNineRoomGame();
        assertEquals(arcane.getRoom(0,0).getName(),"NorthWest");
        assertEquals(arcane.getRoom(1,0).getName(),"North");
        assertEquals(arcane.getRoom(2,0).getName(),"NorthEast");
        assertEquals(arcane.getRoom(0,1).getName(),"West");
        assertEquals(arcane.getRoom(1,1).getName(),"Center");
        assertEquals(arcane.getRoom(2,1).getName(),"East");
        assertEquals(arcane.getRoom(0,2).getName(),"SouthWest");
        assertEquals(arcane.getRoom(1,2).getName(),"South");
        assertEquals(arcane.getRoom(2,2).getName(),"SouthEast");
        assertNull(arcane.getRoom(-1,1));
        arcane = setUpFourRoomGame();
        assertEquals(arcane.getRoom(1,1).getName(),"SouthEast");
        assertNull(arcane.getRoom(2,1));
        arcane = new Arcane();
        arcane.mazeRoomPrep(new Room[]{new Room()},1,1);
        assertEquals(arcane.getRandomRoom().getName(),"NorthWest");
    }

    @Test
    public void roomConnectionsTest() {
        Arcane arcane = new Arcane();
        Room NW = new Room();
        Room NE = new Room();
        Room W = new Room();
        Room E = new Room();
        Room SW = new Room();
        Room SE = new Room();
        arcane.mazeRoomPrep(new Room[]{NW,NE,W,E,SW,SE},2,3);
        assertTrue(SW.getConnectedRooms().contains(W) && SW.getConnectedRooms().contains(SE));
        assertTrue(E.getConnectedRooms().contains(W) && E.getConnectedRooms().contains(SE) && E.getConnectedRooms().contains(NE));
        assertFalse(NW.getConnectedRooms().contains(E));
    }
 */
}
