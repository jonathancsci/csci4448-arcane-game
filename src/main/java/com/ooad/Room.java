package com.ooad;

import java.util.ArrayList;

public class Room {
    private ArrayList<Room> connectedRooms;
    private ArrayList<Entity> occupants;
    private ArrayList<Food> loot;
    private String name;

    public Room() {
        connectedRooms = new ArrayList<Room>();
        occupants = new ArrayList<Entity>();
        loot = new ArrayList<Food>();
        name = "None";
    }

    public Creature getHealthiestCreature() {
        //TODO: sort by health before iterating
        for (int i = 0; i < occupants.size(); i++) {
            if(occupants.get(i).getClass().getName().equals("com.ooad.Creature")) {
                return (Creature)occupants.get(i);
            }
        }
        return null;
    }

    public boolean isThereFood() {
        return loot.isEmpty();
    }

    public Food takeFood() {
        Food takenFood = loot.get(0);
        loot.remove(takenFood);
        return takenFood;
    }

    public ArrayList<Entity> getOccupants() {
        return this.occupants;
    }

    public void addOccupant(Entity entity) {
        occupants.add(entity);
    }

    public void removeOccupant(Entity entity) {
        occupants.remove(entity);
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

    public String toString() {
        String status = "  "+name+"\n";
        for(int i=0; i<occupants.size(); i++) {
            status += "    "+occupants.get(i)+" is here\n";
        }
        return status;
    }
}
