package csci.ooad.arcane;

import org.junit.jupiter.api.Test;

import javax.swing.tree.RowMapper;

import static org.junit.jupiter.api.Assertions.*;

public class RoomTest {

    @Test
    public void getHealthiestAdventurerTest() {
        Room room = new Room();
        assertNull(room.getHealthiestAdventurer());
        Adventurer adventurer1 = new Adventurer("Tim the Wizard",2);
        Adventurer adventurer2 = new Adventurer("Douglas the Barbarian",10);
        room.addOccupant(adventurer1);
        room.addOccupant(adventurer2);
        assertEquals(adventurer2,room.getHealthiestAdventurer());
        adventurer2.setHealth(1);
        assertEquals(adventurer1,room.getHealthiestAdventurer());
    }

    @Test
    public void getHealthiestCreatureTest() {
        Room room = new Room();
        assertNull(room.getHealthiestCreature());
        Creature creature1 = new Creature("Goblin",3);
        Creature creature2 = new Creature("Hrothgrot, Destroyer of Worlds",1000);
        room.addOccupant(creature1);
        room.addOccupant(creature2);
        assertEquals(creature2,room.getHealthiestCreature());
        creature2.setHealth(1);
        assertEquals(creature1,room.getHealthiestCreature());
    }

    @Test
    public void takeFoodTest() {
        Room room = new Room();
        assertFalse(room.isThereFood());
        Food food = new Food();
        room.addFood(food);
        assertTrue(room.isThereFood());
        assertEquals(food,room.takeFood());
        assertFalse(room.isThereFood());
    }

    @Test
    public void manageOccupantsTest() {
        Room testRoom = new Room();
        assertEquals(0,testRoom.getOccupants().size());
        Adventurer testAdventurer = new Adventurer("Bob",5);
        Creature testCreature = new Creature("Ogre",5);
        testRoom.addOccupant(testAdventurer);
        testRoom.addOccupant(testCreature);
        assertEquals(2,testRoom.getOccupants().size());
        testRoom.removeOccupant(testCreature);
        assertEquals(1,testRoom.getOccupants().size());
    }

    @Test
    public void getEntityOfClassTest() {
        Room testRoom = new Room();
        Adventurer testAdventurer = new Adventurer("Bob",5);
        Creature testCreature = new Creature("Ogre",5);
        testRoom.removeOccupant(testAdventurer);
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
    public void getHealthiestDemonTest() {
        Room roomWithDemon = new Room();
        Demon demon = CreatureFactory.createDemon();
        roomWithDemon.addOccupant(demon);
        Demon healthiestDemon = roomWithDemon.getHealthiestDemon();
        assertEquals(healthiestDemon.getHealth(), 15, "Healthiest demon should have 15 health.");

        Room roomWithoutDemon = new Room();
        Demon shouldBeNull = roomWithoutDemon.getHealthiestDemon();
        assertNull(shouldBeNull, "There should be no demon in a room without a demon");
    }

    @Test
    public void toStringTest() {
        Room room = new Room();
        room.setName("Southwest");
        room.addOccupant(AdventurerFactory.createAdventurer());
        room.addFood(FoodFactory.createFood());
        assertTrue(room.toString().contains("Southwest"));
        assertTrue(room.toString().contains("Adventurer"));
        assertTrue(room.toString().contains("5"));
    }
}
