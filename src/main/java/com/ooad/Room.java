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

    public ArrayList<Entity> getOccupants() {
        return this.occupants;
    }

    public void addRoomConnection(Room room) {
        connectedRooms.add(room);
    }

    public void addOccupant(Entity entity) {
        occupants.add(entity);
    }

    public void removeOccupant(Entity entity) {
        occupants.remove(entity);
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

    public void step() {
        for(int i=0; i < occupants.size(); i++) {
            //TODO integrate
            //occupants.get(i).step()
        }
    }

    public String toString() {
        String status = "  "+name+"\n";
        for(int i=0; i<occupants.size(); i++) {
            status += "    "+occupants.get(i)+"\n";
        }
        return status;
    }
}
