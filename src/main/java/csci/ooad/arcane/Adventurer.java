package csci.ooad.arcane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Random;

public class Adventurer extends Entity {
    private static final Logger logger = LoggerFactory.getLogger(Arcane.class);
    boolean hasTakenTurn = false;
    static String[] possibleNames = {
            "Bill", "Sheri", "Tim", "Dave", "Ashley", "Zoe", "Carl", "Jack"
    };
    public Adventurer(String name, double health) {
        super(name, health);
    }

    public Adventurer() {
        super(possibleNames, 5.0);
    }
    public Adventurer(String[] possibleNames, double health) {
        super(possibleNames, health);
    }

    public void turn(Room currentRoom) {
        Creature creature = currentRoom.getHealthiestCreature();
        Demon demon = currentRoom.getHealthiestDemon();
        if(demon != null) {
            combat(demon);
        } else if((creature != null) &&
                (currentRoom.getHealthiestAdventurer() == this)) {
            combat(creature);
        } else if ((creature == null) &&
                (currentRoom.isThereFood())) {
            Food food = currentRoom.takeFood();
            Arcane.logger.info(this+" just ate a "+food.getName() + "\n");
            eatFood(food);
        } else {
            moveRooms();
        }
    }

    public void moveRooms() {
        Room currentRoom = this.getCurrentRoom();
        ArrayList<Room> connectedRooms = currentRoom.getConnectedRooms();

        if (connectedRooms.isEmpty()) {
            throw new IllegalStateException(
                    "This room is not connected with any other rooms."
            );
        }

        Integer numOfConnectedRooms = connectedRooms.size();
        Random randomNumberGenerator = this.getRandomNumberGenerator();
        Integer randomRoomIdx = randomNumberGenerator.nextInt(numOfConnectedRooms);
        Room newRoom = connectedRooms.get(randomRoomIdx);

        logger.info(this.toString() + " moved from " + currentRoom.getName() + " to " + newRoom.getName() + "\n");

        currentRoom.removeOccupant(this);
        newRoom.addOccupant(this);
    }

    public void eatFood(Food food) {
        this.setHealth(this.getHealth() + food.getHealthRestored());
    }

    public String toString() {
        if (isDead()) {
            return "Adventurer "+this.getName()+"(health: "+this.getHealth()+"); DEAD";
        }
        return "Adventurer "+this.getName()+"(health: "+this.getHealth()+")";
    }
}
