package csci.ooad.arcane;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AdventurerTest {
    @Test
    public void testDefaultConstructor() {
        String[] possibleNames = {"Bill","Sheri","Tim","Dave","Ashley","Zoe","Carl","Jack"};
        Adventurer testAdventurer = new Adventurer();
        String adventurerName = testAdventurer.getName();
        Boolean isNameValid = Arrays.asList(possibleNames).contains(adventurerName);
        assertTrue(isNameValid, "Adventurer should have one of the predefined names");
    }

    @Test
    public void moveRoomsTest() {
        Room roomStart = new Room();
        roomStart.setName("roomStart");
        Room roomEnd = new Room();
        roomEnd.setName("roomEnd");
        roomStart.addRoomConnection(roomEnd);

        Adventurer testAdventurer = new Adventurer("Bob", 5);
        roomStart.addOccupant(testAdventurer);
        testAdventurer.moveRooms();

        assertEquals(roomEnd, testAdventurer.getCurrentRoom(), "Adventurer.moveRooms() failed, Adventurer should be in roomEnd.");
        assertTrue(roomStart.getOccupants().isEmpty(), "Adventurer.moveRooms() failed, Adventurer should not be an occupant of roomStart.");
        assertTrue(roomEnd.getOccupants().contains(testAdventurer), "Adventurer.moveRooms() failed, Adventurer should be an occupant of roomEnd.");
    }

    @Test
    public void moveRoomsFailureTest() {
        Room room = new Room();
        Adventurer testAdventurer = new Adventurer("Bob", 5);
        room.addOccupant(testAdventurer);
        assertThrows(IllegalStateException.class, testAdventurer::moveRooms);
    }

    @Test
    public void eatFoodTest() {
        Food food = new Food("Cookie", 1);
        Adventurer adventurer = new Adventurer("Bob", 5);
        adventurer.eatFood(food);
        assertEquals(6, adventurer.getHealth(), "Adventurer failed to eat food.");
    }

    @Test
    public void toStringTest() {
        Adventurer testAdventurer = new Adventurer("Bob", 5);
        assertEquals("Adventurer Bob(health: 5)", testAdventurer.toString());
        testAdventurer.setHealth(0);
        assertEquals("Adventurer Bob(health: 0); DEAD", testAdventurer.toString());
    }
}
