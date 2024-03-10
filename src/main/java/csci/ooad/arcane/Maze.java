package csci.ooad.arcane;

import java.util.*;

public class Maze {
    private Random randomNumberGenerator = new Random();
    private Room[] rooms;
    private ArrayList<Adventurer> adventurers = new ArrayList<>();
    private ArrayList<Creature> creatures = new ArrayList<>();

    public Maze() {

    }

    public void setRooms(Room[] rooms) {
        this.rooms = rooms;
    }

    public List<Adventurer> getAliveAdventurers() {
        List<Adventurer> aliveAdventurers = new ArrayList<>();
        for (Adventurer adventurer : this.adventurers) {
            if (!adventurer.isDead()) {
                aliveAdventurers.add(adventurer);
            }
        }
        return aliveAdventurers;
    }

    public List<Creature> getAliveCreatures() {
        List<Creature> aliveCreatures = new ArrayList<>();
        for (Creature creature : this.creatures) {
            if (!creature.isDead()) {
                aliveCreatures.add(creature);
            }
        }
        return aliveCreatures;
    }

    public void setAdventurers(ArrayList<Adventurer> adventurers) {
        this.adventurers = adventurers;
    }
    public void setCreatures(ArrayList<Creature> creatures) {
        this.creatures = creatures;
    }

    public Room[] getRooms() {
        return rooms;
    }

    public Room getRoom(int num) {
        Arcane.logger.debug(""+num);
        if (num < 0 || num >= rooms.length) {
            return null;
        } else {
            return rooms[num];
        }
    }

    public int getSize() {
        return rooms.length;
    }

    public String toString() {
        String status = "";
        for(int i=0; i<rooms.length; i++) {
            status += rooms[i];
        }
        return status;
    }

    public void turn() {
        Collections.sort(adventurers);
        for(Adventurer adventurer : adventurers) {
            if (adventurer.isDead()) {
                continue;
            }
            adventurer.turn(getEntityRoom(adventurer));
        }
    }

    public Room getEntityRoom(Entity entity) {
        for (Room room : rooms) {
            if(room.getOccupants().contains(entity)) {
                return room;
            }
        }
        return null;
    }

    public boolean checkAllCreaturesDead() {
        for (int i = 0; i < creatures.size(); i++) {
            if(!creatures.get(i).isDead()) {
                return false;
            }
        }
        return true;
    }

    public boolean checkAllAdventurersDead() {
        for (int i = 0; i < adventurers.size(); i++) {
            if(!adventurers.get(i).isDead()) {
                return false;
            }
        }
        return true;
    }

}
