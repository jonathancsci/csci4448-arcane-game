package csci.ooad.arcane;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GluttonTest {

    @Test
    public void testTurn() {
        Creature creature = CreatureFactory.createCreature("Snapdragon", 10);
        Demon demon = CreatureFactory.createDemon();
        Glutton glutton = new Glutton("Tim", 50);
        Room room = new Room();
        Room connectedRoom = new Room();
        Food food = new Food();

        // Create room with demon, creature, glutton, food and a connectedRoom which is empty
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

        room.removeOccupant(creature);

        // Glutton moves rooms
        Room prevRoom = glutton.getCurrentRoom();
        glutton.turn(room);
        Room afterRoom = glutton.getCurrentRoom();
        Boolean didGluttonMove = prevRoom != afterRoom;
        assertTrue(didGluttonMove, "Glutton should have moved rooms.");
    }
}
