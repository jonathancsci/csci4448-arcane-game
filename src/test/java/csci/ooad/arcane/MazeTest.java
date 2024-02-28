package csci.ooad.arcane;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class MazeTest {

    @Test
    public void turnTest() {
        //set-up
        Adventurer adventurer = AdventurerFactory.createAdventurer("Bill",200);
        Creature creature = CreatureFactory.createCreature("Ogre",1);
        Maze maze = new MazeFactory.MazeBuilder()
                .createRooms(2)
                .createFullRoomConnections()
                .addAdventurer(adventurer,0)
                .addCreature(creature,1)
                .addFood(FoodFactory.createFood(),1)
                .build();
        //run and assert
        assertEquals(maze.getEntityRoom(adventurer),maze.getRoom(0),"Initial state");
        maze.turn();
        assertEquals(maze.getEntityRoom(adventurer),maze.getRoom(1),"Adventurer should move when in empty room.");
        maze.turn();
        assertEquals(maze.getEntityRoom(adventurer),maze.getRoom(1),"Adventurer should fight when in a room with a monster.");
        assertTrue(maze.getRoom(1).isThereFood(),"Adventurer should leave the food alone when entering the room.");
        while(!creature.isDead() && !adventurer.isDead()) {
            maze.turn();
        }
        assertTrue(creature.isDead(),"This test failed randomly, the 1 hp monster beat the 200 hp adventurer");
        assertTrue(maze.getRoom(1).isThereFood(),"The adventurer should wait until the monster is dead to eat");
        maze.turn();
        assertFalse(maze.getRoom(1).isThereFood(),"The adventurer should eat the food once the monster is dead");
        maze.turn();
        assertEquals(maze.getEntityRoom(adventurer),maze.getRoom(0),"The adventurer should leave when there's nothing left to do");
        adventurer.setHealth(0);
        maze.turn();
        assertEquals(maze.getEntityRoom(adventurer),maze.getRoom(0),"The adventurer should stop moving when dead");
    }

    @Test
    public void getEntityRoomTest() {
        //set-up
        Adventurer adventurer1 = AdventurerFactory.createAdventurer();
        Adventurer adventurer2 = AdventurerFactory.createAdventurer();
        Adventurer adventurer3 = AdventurerFactory.createAdventurer();
        Maze maze = new MazeFactory.MazeBuilder()
                .createRooms(9)
                .createFullRoomConnections()
                .addAdventurer(adventurer1,0)
                .addAdventurer(adventurer2,4)
                .addAdventurer(adventurer3,8)
                .build();
        assertEquals(maze.getEntityRoom(adventurer1),maze.getRoom(0),"Adventurer in room 0 should be in room 0");
        assertEquals(maze.getEntityRoom(adventurer2),maze.getRoom(4),"Adventurer in room 4 should be in room 4");
        assertEquals(maze.getEntityRoom(adventurer3),maze.getRoom(8),"Adventurer in room 8 should be in room 8");
        maze.turn();
        assertNotEquals(maze.getEntityRoom(adventurer1),maze.getRoom(0),"Adventurers should move");
        assertNotEquals(maze.getEntityRoom(adventurer2),maze.getRoom(4),"Adventurers should move");
        assertNotEquals(maze.getEntityRoom(adventurer3),maze.getRoom(8),"Adventurers should move");
    }

    @Test
    public void checkAllAdventurersDead() {
        //set-up
        Adventurer adventurer = AdventurerFactory.createAdventurer();
        Adventurer knight = AdventurerFactory.createKnight("Sir John",8);
        Creature creature = CreatureFactory.createCreature();
        Demon demon = CreatureFactory.createDemon("Satan",15);
        Maze maze = new MazeFactory.MazeBuilder()
                .createRooms(4)
                .addAdventurer(adventurer,0)
                .addAdventurer(knight,1)
                .addCreature(creature,2)
                .addCreature(demon,3)
                .build();
        //check if entities are alive
        assertFalse(maze.checkAllAdventurersDead());
        assertFalse(maze.checkAllCreaturesDead());
        //verify that entities are considered around if some are dead
        adventurer.setHealth(0);
        assertFalse(maze.checkAllAdventurersDead());
        creature.setHealth(0);
        assertFalse(maze.checkAllCreaturesDead());
        //verify that entities are considered gone if all are dead
        knight.setHealth(0);
        assertTrue(maze.checkAllAdventurersDead());
        demon.setHealth(0);
        assertTrue(maze.checkAllCreaturesDead());
        //verify that the lists aren't mixed up
        knight.setHealth(1);
        assertTrue(maze.checkAllCreaturesDead());
        assertFalse(maze.checkAllAdventurersDead());
    }

    @Test
    public void sizeTest() {
        Maze maze1 = MazeFactory.createGrid(2,2,0,0,0);
        Maze maze2 = MazeFactory.createMaze(6,0,0,0);
        assertEquals(4,maze1.getSize());
        assertEquals(6,maze2.getSize());
    }

    @Test
    public void toStringTest() {
        String[] expected = {"  NorthEast","  North","  NorthWest","  Center","  SouthWest"};
        Maze maze = MazeFactory.createNineRoomGrid();
        String[] printedLines = maze.toString().split("\n");
        for (int i = 0; i < expected.length; i++) {
            assertTrue(Arrays.asList(printedLines).contains(expected[i]), "The first turn of the game did not print what was expected.");
        }
    }
}
