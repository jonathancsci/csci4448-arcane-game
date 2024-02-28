package csci.ooad.arcane;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class KnightTest {

    @Test
    public void testTurn() {
        Creature creature = CreatureFactory.createCreature("Snapdragon", 10);
        Demon demon = CreatureFactory.createDemon();
        Knight knight = new Knight("Sir Tim", 50);
        Room room = new Room();
        Room connectedRoom = new Room();
        Food food = new Food();

        // Create room with demon, creature, knight, food and a connectedRoom which is empty
        room.addOccupant(creature);
        room.addOccupant(demon);
        room.addOccupant(knight);
        room.addFood(food);
        room.addRoomConnection(connectedRoom);
        connectedRoom.addRoomConnection(room);

        // Knight and Demon fight
        room.getHealthiestAdventurer().turn(room);
        Boolean didKnightAndDemonFight = room.getHealthiestDemon().getHealth() <= 15 ||
                room.getHealthiestAdventurer().getHealth() <= 50;
        assertTrue(didKnightAndDemonFight, "Knight and Demon should fight when in the same room");

        room.removeOccupant(demon);
        room.getHealthiestAdventurer().setHealth(50);

        // Knight fights creature and does not eat food
        room.getHealthiestAdventurer().turn(room);
        Boolean didKnightAndCreatureFight = room.getHealthiestCreature().getHealth() <= 3 ||
                room.getHealthiestAdventurer().getHealth() <= 50;
        assertTrue(didKnightAndCreatureFight, "Knight and Creature should fight when in the same room");

        room.removeOccupant(creature);

        // Knight eats food
        room.getHealthiestAdventurer().turn(room);
        Boolean isThereNoFood = !room.isThereFood();
        assertTrue(isThereNoFood, "Knight should eat food if there are no enemies.");

        // Knight moves rooms
        Room prevRoom = knight.getCurrentRoom();
        room.getHealthiestAdventurer().turn(room);
        Room afterRoom = knight.getCurrentRoom();
        Boolean didKnightMove = prevRoom != afterRoom;
        assertTrue(didKnightMove, "Knight should have moved rooms.");
    }
}
