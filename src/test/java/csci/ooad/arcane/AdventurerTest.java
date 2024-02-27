package csci.ooad.arcane;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AdventurerTest {
    @Test
    public void testConstructor() {
        String[] possibleNames = {
                "Bill", "Sheri", "Tim", "Dave", "Ashley", "Zoe", "Carl", "Jack"
        };
        // Default Constructor
        Adventurer testAdventurer = new Adventurer();
        String adventurerName = testAdventurer.getName();
        Boolean isNameValid = Arrays.asList(possibleNames).contains(adventurerName);
        assertTrue(isNameValid, "Adventurer should have one of the predefined names");

        // Name Options Constructor
        Adventurer testAdventurer2 = new Adventurer(possibleNames, 5);
        String adventurerName2 = testAdventurer2.getName();
        Boolean isNameValid2 = Arrays.asList(possibleNames).contains(adventurerName2);
        assertTrue(isNameValid2, "Adventurer should have one of the predefined names");
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
    public void testTurn() {
        Creature creature = new Creature();
        Demon demon = new Demon();
        Adventurer adventurer = new Adventurer();
        Room room = new Room();
        Room connectedRoom = new Room();
        Food food = new Food();

        room.addOccupant(creature);
        room.addOccupant(demon);
        room.addOccupant(adventurer);
        room.addFood(food);
        room.addRoomConnection(connectedRoom);

        // Adventurer and Demon fight
        room.getHealthiestAdventurer().turn(room);
        Boolean didAdventurerAndDemonFight = room.getHealthiestDemon().getHealth() <= 15 || (
                room.getHealthiestAdventurer() == null || room.getHealthiestAdventurer().getHealth() <= 5
        );
        assertTrue(didAdventurerAndDemonFight, "Adventurer and Demon should fight when in the same room");

        // Adventurer and Creature fight
        room.removeOccupant(demon);
        if (room.getHealthiestAdventurer() == null) {
            room.addOccupant(new Adventurer());
        }
        room.getHealthiestAdventurer().setHealth(8);

        adventurer.turn(room);
        Boolean didAdventurerAndCreatureFight = (room.getHealthiestCreature() == null || room.getHealthiestCreature().getHealth() <= 3) ||
                (room.getHealthiestAdventurer() == null || room.getHealthiestAdventurer().getHealth() <= 5);
        assertTrue(didAdventurerAndCreatureFight, "Adventurer and Creature should fight when in the same room");

        // There is only food in the room
        if (room.getHealthiestCreature() != null) {
            room.removeOccupant(creature);
        }

        double prevHealth = adventurer.getHealth();
        adventurer.turn(room);
        double afterHealth = adventurer.getHealth();
        Boolean didAdventurerEatFood = afterHealth > prevHealth;
        assertTrue(didAdventurerEatFood, "Adventurer should have eaten food");

        // Nothing in the room
        Room prevRoom = adventurer.getCurrentRoom();
        adventurer.turn(room);
        Room afterRoom = adventurer.getCurrentRoom();
        Boolean didAdventurerMove = prevRoom != afterRoom;
        assertTrue(didAdventurerMove, "Adventurer should have moved rooms.");
    }

    @Test
    public void toStringTest() {
        Adventurer testAdventurer = new Adventurer("Bob", 5);
        assertEquals("Adventurer Bob(health: 5.0)", testAdventurer.toString());
        testAdventurer.setHealth(0);
        assertEquals("Adventurer Bob(health: 0.0); DEAD", testAdventurer.toString());
    }
}
