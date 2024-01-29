package com.ooad;

import java.util.ArrayList;
import java.util.Random;

public class Adventurer extends Entity {
    public Adventurer(String name, Integer health, Room currentRoom) {
        super(name, health, currentRoom);
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

    public void step() {
        this.moveRooms();
    }
}
