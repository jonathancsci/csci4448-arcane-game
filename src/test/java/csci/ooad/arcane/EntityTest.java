package csci.ooad.arcane;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

public class EntityTest {
    @Test
    public void gettersTest() {
        Entity testEntity = new Entity("Bob", 5);

        assertEquals("Bob", testEntity.getName(), "Error in Entity.getName()");
        assertEquals(5, testEntity.getHealth(), "Error in Entity.getHealth()");
        assertNull(testEntity.getCurrentRoom(), "Error in Entity.getCurrentRoom()");
        assertNotNull(testEntity.getRandomNumberGenerator(), "Error in Entity.getRandomNumberGenerator");
    }

    @Test
    public void settersTest() {
        Room room1 = new Room();
        Room room2 = new Room();
        Entity testEntity = new Entity("Bob", 5);
        testEntity.setCurrentRoom(room1);

        testEntity.setName("Bill");
        testEntity.setHealth(3);
        testEntity.setCurrentRoom(room2);

        assertEquals("Bill", testEntity.getName(), "Error in Entity.setName()");
        assertEquals(3, testEntity.getHealth(), "Error in Entity.setHealth()");
        assertEquals(room2, testEntity.getCurrentRoom(), "Error in Entity.setCurrentRoom");
    }

    @Test
    public void testNameOptionsConstructor() {
        String[] possibleNames = {"Cobblebeast","Dimcreeper","Ooze","Unruly Armor","Cavern Crab","Snapdragon","Wyvern"};
        Entity testEntity = new Entity(possibleNames, 3);
        String entityName = testEntity.getName();
        Boolean isNameValid = Arrays.asList(possibleNames).contains(entityName);
        assertTrue(isNameValid, "Creature should have one of the predefined names");
    }

    @Test
    public void testComparable() {
        Entity entityLowHealth = new Entity("Orge", 1);
        Entity entityHighHealth = new Entity("Giant", 10);
        List<Entity> entityList = Arrays.asList(entityLowHealth, entityHighHealth);
        Boolean isLowHealthFirst = entityList.get(0) == entityLowHealth;
        assertTrue(isLowHealthFirst, "Array add failed.");
        entityList.sort(Entity::compareTo);
        Boolean isHighHealthFirst = entityList.get(0) == entityHighHealth;
        assertTrue(isHighHealthFirst, "Sorting by health descending failed.");
    }

    @Test
    public void rollDiceTest() {
        Entity testEntity = new Entity("Bob", 5);
        Integer diceRollResult = testEntity.rollDice();
        assertTrue(diceRollResult >= 1 && diceRollResult <= 6, "Error in Entity.rollDice");
    }

    @Test
    public void takeDamageTest() {
        Entity testEntity = new Entity("Bob", 5);
        testEntity.takeDamage(1);
        assertEquals(4, testEntity.getHealth());
    }

    @Test
    public void isDeadTest() {
        Entity testEntity = new Entity("Bob", 5);
        assertFalse(testEntity.isDead());
        testEntity.setHealth(0);
        assertTrue(testEntity.isDead());
        testEntity.setHealth(-1);
        assertTrue(testEntity.isDead());
    }
}
