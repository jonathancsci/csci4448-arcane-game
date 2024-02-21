package csci.ooad.arcane;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Maze {
    Random randomNumberGenerator = new Random();
    private Room[] rooms;
    private ArrayList<Adventurer> adventurers = new ArrayList<>();
    private ArrayList<Creature> creatures = new ArrayList<>();

    public Maze() {

    }

    public void setRooms(Room[] rooms) {
        this.rooms = rooms;
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

    public Room getRandomRoom() {
        return rooms[randomNumberGenerator.nextInt(rooms.length)];
    }

    public String toString() {
        String status = "";
        for(int i=0; i<rooms.length; i++) {
            status += rooms[i];
        }
        return status;
    }

    public void addAdventurer(Adventurer adventurer, Room room) {
        adventurers.add(adventurer);
        room.addOccupant(adventurer);
    }

    public void addCreature(Creature creature, Room room) {
        creatures.add(creature);
        room.addOccupant(creature);
    }

    public void addFood(Food food, Room room) {
        room.addFood(food);
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
