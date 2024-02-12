package csci.ooad.arcane;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RoomTest {

    @Test
    public void turnAdventurerMoveTest() {
        Room testRoom = new Room();
        Room testRoom2 = new Room();
        testRoom.addRoomConnection(testRoom2);
        Adventurer testAdventurer = new Adventurer("Bob",5);
        assertEquals(0,testRoom.getOccupants().size());
    }

    @Test
    public void turnCombatTest() {
        Room testRoom = new Room();
        Adventurer testAdventurer = new Adventurer("Bob",5);
        Creature testCreature = new Creature("Ogre",5);
        int hpA = testRoom.getOccupants().get(0).getHealth();
        int hpB = testRoom.getOccupants().get(1).getHealth();
        assertTrue(hpA+hpB <= 10);
    }

    @Test
    public void manageOccupantsTest() {
        Room testRoom = new Room();
        assertEquals(0,testRoom.getOccupants().size());
        Adventurer testAdventurer = new Adventurer("Bob",5);
        Creature testCreature = new Creature("Ogre",5);
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
    public void toStringTest() {
        Room room = new Room();
        room.setName("Southwest");
        assertEquals("  Southwest\n",room.toString());
    }
}
