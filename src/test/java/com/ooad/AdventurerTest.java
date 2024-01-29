package com.ooad;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AdventurerTest {
    @Test
    public void gettersTest() {
        Room room = new Room();
        Adventurer testAdventurer = new Adventurer("Bob", 5, room);
        assertEquals("Bob", testAdventurer.getName(), "Error in Adventurer.getName()");
        assertEquals(5, testAdventurer.getHealth(), "Error in Adventurer.getHealth()");
        assertNotNull(testAdventurer.getCurrentRoom(), "Error in Adventurer.getCurrentRoom()");
    }

    @Test
    public void settersTest() {
        Room room1 = new Room();
        Room room2 = new Room();
        Adventurer testAdventurer = new Adventurer("Bob", 5, room1);
        testAdventurer.setName("Bill");
        testAdventurer.setHealth(3);
        testAdventurer.setCurrentRoom(room2);

        assertEquals("Bill", testAdventurer.getName(), "Error in Adventurer.setName()");
        assertEquals(3, testAdventurer.getHealth(), "Error in Adventurer.setHealth()");
        assertEquals(room2, testAdventurer.getCurrentRoom(), "Error in Adventurer.setCurrentRoom");
    }

    @Test
    public void diceRollTest() {
        Room room = new Room();
        Adventurer testAdventurer = new Adventurer("Bob", 5, room);
        Integer diceRollResult = testAdventurer.rollDice();
        assertTrue(diceRollResult >= 1 && diceRollResult <= 6, "Error in Adventurer.rollDice");
    }

    @Test
    public void moveRoomsTest() {
        Room roomStart = new Room();
        Room roomEnd = new Room();
        roomStart.addRoomConnection(roomEnd);

        Adventurer testAdventurer = new Adventurer("Bob", 5, roomStart);
        testAdventurer.moveRooms();

        assertEquals(roomEnd, testAdventurer.getCurrentRoom(), "Adventurer.moveRooms() failed, Adventurer should be in roomEnd.");
        assertTrue(roomStart.getOccupants().isEmpty(), "Adventurer.moveRooms() failed, Adventurer should not be an occupant of roomStart.");
        assertTrue(roomEnd.getOccupants().contains(testAdventurer), "Adventurer.moveRooms() failed, Adventurer should be an occupant of roomEnd.");
    }

    @Test
    public void toStringTest() {
        Room room = new Room();
        Adventurer testAdventurer = new Adventurer("Bob", 5, room);
        assertEquals("Adventurer Bob(health: 5)",testAdventurer.toString());
    }
}
