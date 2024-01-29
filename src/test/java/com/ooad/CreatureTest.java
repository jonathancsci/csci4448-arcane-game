package com.ooad;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreatureTest {
    @Test
    public void gettersTest() {
        Room room = new Room();
        Creature testCreature = new Creature("Ogre", 5, room);
        assertEquals("Ogre", testCreature.getName(), "Error in Creature.getName()");
        assertEquals(5, testCreature.getHealth(), "Error in Creature.getHealth()");
        assertNotNull(testCreature.getCurrentRoom(), "Error in Creature.getCurrentRoom()");
    }

    @Test
    public void settersTest() {
        Room room1 = new Room();
        Room room2 = new Room();
        Creature testCreature = new Creature("Ogre", 5, room1);
        testCreature.setName("Giant");
        testCreature.setHealth(3);
        testCreature.setCurrentRoom(room2);

        assertEquals("Giant", testCreature.getName(), "Error in Creature.setName()");
        assertEquals(3, testCreature.getHealth(), "Error in Creature.setHealth()");
        assertEquals(room2, testCreature.getCurrentRoom(), "Error in Creature.setCurrentRoom");
    }

    @Test
    public void methodsTest() {
        Room room = new Room();
        Creature testCreature = new Creature("Ogre", 5, room);
        Integer diceRollResult = testCreature.rollDice();
        assertTrue(diceRollResult >= 1 && diceRollResult <= 6, "Error in Creature.rollDice");
    }
}
