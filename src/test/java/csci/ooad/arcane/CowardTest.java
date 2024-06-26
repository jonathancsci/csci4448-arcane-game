package csci.ooad.arcane;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CowardTest {

    @Test
    public void testTurn() {
        Creature creature = CreatureFactory.createCreature("Snapdragon", 10);
        Demon demon = CreatureFactory.createDemon();
        Coward coward = new Coward("Tim", 50);
        Room room = new Room();
        Room connectedRoom = new Room();
        Food food = new Food();

        // Create room with demon, creature, coward and a connectedRoom which contains food
        room.addOccupant(creature);
        room.addOccupant(demon);
        room.addOccupant(coward);
        connectedRoom.addFood(food);
        room.addRoomConnection(connectedRoom);
        connectedRoom.addRoomConnection(room);

        // Coward fights demon
        room.getHealthiestAdventurer().turn(room);
        Boolean didCowardAndDemonFight = room.getHealthiestDemon().getHealth() <= 15 ||
                room.getHealthiestAdventurer().getHealth() <= 50;
        assertTrue(didCowardAndDemonFight, "Coward and Demon should fight when in the same room");

        room.removeOccupant(demon);

        // Coward runs from creature
        Room prevRoom = coward.getCurrentRoom();
        double prevHealth = coward.getHealth();
        room.getHealthiestAdventurer().turn(room);
        Room afterRoom = coward.getCurrentRoom();
        double afterHealth = coward.getHealth();
        Boolean didCowardRun = prevRoom != afterRoom;
        Boolean didCowardLoseHealth = prevHealth > afterHealth;
        assertTrue(didCowardRun, "Coward should run if there is no demon.");
        assertTrue(didCowardLoseHealth, "Coward should lose health when running away");

        // Coward eats food in connected room
        double prevHealth2 = coward.getHealth();
        coward.turn(connectedRoom);
        double afterHealth2 = coward.getHealth();
        Boolean didCowardEatFood = afterHealth2 > prevHealth2;
        assertTrue(didCowardEatFood, "Coward should have eaten food");

        // Coward moves rooms
        Room prevRoom2 = coward.getCurrentRoom();
        coward.turn(connectedRoom);
        Room afterRoom2 = coward.getCurrentRoom();
        Boolean didCowardMove = prevRoom2 != afterRoom2;
        assertTrue(didCowardMove, "Coward should have moved rooms.");
    }
}
