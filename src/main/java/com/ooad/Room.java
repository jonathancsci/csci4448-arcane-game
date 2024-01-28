package com.ooad;

import java.util.ArrayList;

public class Room {
    private ArrayList<Room> connectedRooms;
    private ArrayList<Entity> occupants;
    private String name;

    public Room() {
        connectedRooms = new ArrayList<Room>();
        occupants = new ArrayList<Entity>();
        name = "None";
    }

    public void addRoomConnection(Room room) {
        connectedRooms.add(room);
    }

    public ArrayList<Room> getConnectedRooms() {
        return (ArrayList)connectedRooms.clone();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
