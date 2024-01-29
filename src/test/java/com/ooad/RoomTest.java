package com.ooad;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RoomTest {

    @Test
    public void turnAdventurerMoveTest() {
        Room testRoom = new Room();
        Room testRoom2 = new Room();
        testRoom.addRoomConnection(testRoom2);
        Adventurer testAdventurer = new Adventurer("Bob",5,testRoom);
        testRoom.turn();
        assertEquals(0,testRoom.getOccupants().size());
    }

    @Test
    public void turnCombatTest() {
        Room testRoom = new Room();
        Adventurer testAdventurer = new Adventurer("Bob",5,testRoom);
        Creature testCreature = new Creature("Ogre",5,testRoom);
        testRoom.turn();
        int hpA = testRoom.getOccupants().get(0).getHealth();
        int hpB = testRoom.getOccupants().get(1).getHealth();
        assertTrue(hpA+hpB >= 9);
    }

    @Test
    public void manageOccupantsTest() {
        Room testRoom = new Room();
        assertEquals(0,testRoom.getOccupants().size());
        Adventurer testAdventurer = new Adventurer("Bob",5,testRoom);
        Creature testCreature = new Creature("Ogre",5,testRoom);
        assertEquals(2,testRoom.getOccupants().size());
        testRoom.removeOccupant(testCreature);
        assertEquals(1,testRoom.getOccupants().size());
    }

    @Test
    public void getEntityOfClassTest() {
        Room testRoom = new Room();
        Adventurer testAdventurer = new Adventurer("Bob",5,testRoom);
        Creature testCreature = new Creature("Ogre",5,testRoom);
        assertEquals(testAdventurer,testRoom.getEntityOfClass("Adventurer"));
        assertEquals(testCreature,testRoom.getEntityOfClass("Creature"));
        testRoom.removeOccupant(testAdventurer);
        assertNull(testRoom.getEntityOfClass("Adventurer"));
    }

    @Test
    public void addRoomConnectionTest() {
        Room room1 = new Room();
        Room room2 = new Room();
        room1.addRoomConnection(room2);
        assertSame(room1.getConnectedRooms().get(0), room2);
    }

    @Test
    public void roomNameTest() {
        Room room = new Room();
        room.setName("Atrium");
        assertEquals("Atrium",room.getName());
    }

    @Test
    public void toStringTest() {
        Room room = new Room();
        room.setName("Southwest");
        assertEquals("  Southwest\n",room.toString());
    }
}
