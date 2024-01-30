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

    public void turn() {
        Adventurer adventurer = (Adventurer)getEntityOfClass("Adventurer");
        if(adventurer != null) {
            Creature creature = (Creature)getEntityOfClass("Creature");
            if(creature != null) {
                Combat(adventurer, creature);
            } else {
                adventurer.moveRooms();
            }
        }
    }

    private void Combat(Entity combatantA, Entity combatantB) {
        int rollA = combatantA.rollDice();
        int rollB = combatantB.rollDice();
        if(rollA > rollB) {
            Integer damageForCombatantB = rollA - rollB;
            combatantB.takeDamage(damageForCombatantB);
        } else if(rollB > rollA) {
            Integer damageForCombatantA = rollB - rollA;
            combatantA.takeDamage(damageForCombatantA);
        }
    }

    public Entity getEntityOfClass(String classname) {
        for (int i = 0; i < occupants.size(); i++) {
            if(occupants.get(i).getClass().getName().equals("com.ooad."+classname)) {
                return occupants.get(i);
            }
        }
        return null;
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
