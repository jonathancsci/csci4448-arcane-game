package com.ooad.arcane;

import com.ooad.arcane.Entity;
import com.ooad.arcane.Room;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EntityTest {
    @Test
    public void gettersTest() {
        Room room = new Room();
        Entity testEntity = new Entity("Bob", 5, room);
        assertEquals("Bob", testEntity.getName(), "Error in Entity.getName()");
        assertEquals(5, testEntity.getHealth(), "Error in Entity.getHealth()");
        assertNotNull(testEntity.getCurrentRoom(), "Error in Entity.getCurrentRoom()");
    }

    @Test
    public void settersTest() {
        Room room1 = new Room();
        Room room2 = new Room();
        Entity testEntity = new Entity("Bob", 5, room1);
        testEntity.setName("Bill");
        testEntity.setHealth(3);
        testEntity.setCurrentRoom(room2);

        assertEquals("Bill", testEntity.getName(), "Error in Entity.setName()");
        assertEquals(3, testEntity.getHealth(), "Error in Entity.setHealth()");
        assertEquals(room2, testEntity.getCurrentRoom(), "Error in Entity.setCurrentRoom");
    }

    @Test
    public void rollDiceTest() {
        Room room = new Room();
        Entity testEntity = new Entity("Bob", 5, room);
        Integer diceRollResult = testEntity.rollDice();
        assertTrue(diceRollResult >= 1 && diceRollResult <= 6, "Error in Entity.rollDice");
    }

    @Test
    public void takeDamageTest() {
        Room room = new Room();
        Entity testEntity = new Entity("Bob", 5, room);
        testEntity.takeDamage(1);
        assertEquals(4, testEntity.getHealth());
    }

    @Test
    public void isDead() {
        Room room = new Room();
        Entity testEntity = new Entity("Bob", 5, room);
        assertFalse(testEntity.isDead());
        testEntity.setHealth(0);
        assertTrue(testEntity.isDead());
        testEntity.setHealth(-1);
        assertTrue(testEntity.isDead());
    }
}
