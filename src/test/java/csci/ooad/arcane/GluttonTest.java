package csci.ooad.arcane;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GluttonTest {
    @Test
    public void testConstructor() {
        String[] possibleNames = {
                "Bill", "Sheri", "Tim", "Dave", "Ashley", "Zoe", "Carl", "Jack"
        };
        // Default Constructor
        Glutton testGlutton = new Glutton();
        String gluttonName = testGlutton.getName();
        Boolean isNameValid = Arrays.asList(possibleNames).contains(gluttonName);
        assertTrue(isNameValid, "Glutton should have one of the predefined names");

        // Factory Constructor
        Glutton testGlutton2 = new Glutton("Tim", 5);
        String GluttonName2 = testGlutton2.getName();
        Boolean isNameValid2 = Arrays.asList(possibleNames).contains(GluttonName2);
        assertTrue(isNameValid2, "Glutton should have one of the predefined names");
    }

    @Test
    public void testTurn() {
        Creature creature = new Creature();
        Demon demon = new Demon();
        Glutton glutton = new Glutton("Tim", 50);
        Room room = new Room();
        Room connectedRoom = new Room();
        Food food = new Food();

        room.addOccupant(creature);
        room.addOccupant(demon);
        room.addOccupant(glutton);
        room.addFood(food);
        room.addRoomConnection(connectedRoom);
        connectedRoom.addRoomConnection(room);

        // Glutton fights demon
        room.getHealthiestAdventurer().turn(room);
        Boolean didGluttonAndDemonFight = room.getHealthiestDemon().getHealth() <= 15 ||
                room.getHealthiestAdventurer().getHealth() <= 50;
        assertTrue(didGluttonAndDemonFight, "Glutton and Demon should fight when in the same room");

        room.removeOccupant(demon);

        // Glutton eats food event with Creature in the same room
        room.getHealthiestAdventurer().turn(room);
        Boolean isThereNoFood = !room.isThereFood();
        assertTrue(isThereNoFood, "Glutton should eat food even if there is a Creature in the same room");

        // Glutton fights creature when there is no food
        double prevHealth = glutton.getHealth();
        room.getHealthiestAdventurer().turn(room);
        double afterHealth = glutton.getHealth();
        Boolean didGluttonFight = prevHealth >= afterHealth;
        assertTrue(didGluttonFight, "Glutton should fight if there is no food and a creature");

        if (room.getHealthiestCreature() != null) {
            room.removeOccupant(creature);
        }

        // Glutton moves rooms
        Room prevRoom = glutton.getCurrentRoom();
        glutton.turn(room);
        Room afterRoom = glutton.getCurrentRoom();
        Boolean didGluttonMove = prevRoom != afterRoom;
        assertTrue(didGluttonMove, "Glutton should have moved rooms.");
    }
}
