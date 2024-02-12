package csci.ooad.arcane;

import java.util.ArrayList;
import java.util.Random;

public class Adventurer extends Entity {
    boolean hasTakenTurn = false;
    static String[] possibleNames = {"Bill","Sheri","Tim","Dave","Ashley","Zoe","Carl","Jack"};
    public Adventurer(String name, Integer health, Room currentRoom) {
        super(name, health, currentRoom);
        currentRoom.addOccupant(this);
    }

    public Adventurer(Room currentRoom) {
        super(possibleNames, 5, currentRoom);
        currentRoom.addOccupant(this);
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

        currentRoom.removeOccupant(this);
        this.setCurrentRoom(newRoom);
        newRoom.addOccupant(this);
    }

    public void eatFood(Food food) {
        this.setHealth(this.getHealth() + food.getHealthRestored());
    }

    public String toString() {
        return "Adventurer "+this.getName()+"(health: "+this.getHealth()+")";
    }
}
