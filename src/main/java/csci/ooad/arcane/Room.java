package csci.ooad.arcane;

import java.util.ArrayList;
import java.util.Collections;

public class Room {
    private ArrayList<Room> connectedRooms;
    private ArrayList<Entity> occupants;
    private ArrayList<Food> loot;
    private String name;

    //note: we moved turn and combat logic into Arcane to increase cohesion, rooms now only store and convey data about what is in a given location
    public Room() {
        connectedRooms = new ArrayList<Room>();
        occupants = new ArrayList<Entity>();
        loot = new ArrayList<Food>();
    }

    //Room does not provide its list of occupants, and instead only provides the healthiest occupant, the bare minimum knowledge required elsewhere in the system
    public Creature getHealthiestCreature() {
        Collections.sort(this.occupants);
        for (int i = 0; i < occupants.size(); i++) {
            if(occupants.get(i).getClass().getName().equals("csci.ooad.arcane.Creature") && !occupants.get(i).isDead()) {
                return (Creature)occupants.get(i);
            }
        }
        return null;
    }

    public Demon getHealthiestDemon() {
        Collections.sort(this.occupants);
        for (int i = 0; i < occupants.size(); i++) {
            if(occupants.get(i).getClass().getName().equals("csci.ooad.arcane.Demon") && !occupants.get(i).isDead()) {
                return (Demon) occupants.get(i);
            }
        }
        return null;
    }

    public Adventurer getHealthiestAdventurer() {
        Collections.sort(this.occupants);
        for (int i = 0; i < occupants.size(); i++) {
            if(occupants.get(i).getClass().getName().equals("csci.ooad.arcane.Adventurer") && !occupants.get(i).isDead()) {
                return (Adventurer)occupants.get(i);
            }
            if(occupants.get(i).getClass().getName().equals("csci.ooad.arcane.Coward") && !occupants.get(i).isDead()) {
                return (Coward)occupants.get(i);
            }
            if(occupants.get(i).getClass().getName().equals("csci.ooad.arcane.Glutton") && !occupants.get(i).isDead()) {
                return (Glutton)occupants.get(i);
            }
            if(occupants.get(i).getClass().getName().equals("csci.ooad.arcane.Knight") && !occupants.get(i).isDead()) {
                return (Knight)occupants.get(i);
            }
        }
        return null;
    }

    public boolean isThereFood() {
        return !loot.isEmpty();
    }

    public void addFood(Food food) {
        loot.add(food);
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
        entity.setCurrentRoom(this);
    }

    public void removeOccupant(Entity entity) {
        occupants.remove(entity);
    }

    public void addRoomConnection(Room room) {
        if(!connectedRooms.contains(room)) {
            connectedRooms.add(room);
        }
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
        status += "    loot:";
        for(int i=0; i<loot.size(); i++) {
            status += " "+loot.get(i);
        }
        status += "\n";
        return status;
    }
}
