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

    public void addAdventurer(Adventurer adventurer) {
        adventurers.add(adventurer);
        getRandomRoom().addOccupant(adventurer);
    }

    public void addAdventurer(Adventurer adventurer, Room room) {
        adventurers.add(adventurer);
        room.addOccupant(adventurer);
    }

    public void addAdventurer(Adventurer[] adventurers) {
        for(Adventurer a : adventurers) {
            addAdventurer(a);
        }
    }

    public void addCreature(Creature creature) {
        creatures.add(creature);
        getRandomRoom().addOccupant(creature);
    }

    public void addCreature(Creature creature, Room room) {
        creatures.add(creature);
        room.addOccupant(creature);
    }

    public void addCreature(Creature[] creatures) {
        for(Creature c : creatures) {
            addCreature(c);
        }
    }

    public void addFood(Food food) {
        getRandomRoom().addFood(food);
    }

    public void addFood(Food food, Room room) {
        room.addFood(food);
    }

    public void addFood(Food[] food) {
        for(Food f : food) {
            addFood(f);
        }
    }

    public void turn() {
        Collections.sort(adventurers);
        for(Adventurer adventurer : adventurers) {
            if (adventurer.isDead()) {
                continue;
            }
            Room currentRoom = adventurer.getCurrentRoom();
            Creature creature = currentRoom.getHealthiestCreature();
            if((creature != null) &&
                    (currentRoom.getHealthiestAdventurer() == adventurer)) {
                combat(adventurer, creature);
            } else if ((creature == null) &&
                    (currentRoom.isThereFood())) {
                Food food = currentRoom.takeFood();
                Arcane.logger.info(adventurer+" just ate a "+food.getName() + "\n");
                adventurer.eatFood(food);
                //dependency injection: the food is passed into the adventurer
            } else {
                adventurer.moveRooms();
            }
        }
    }

    public void combat(Adventurer combatantA, Creature combatantB) {
        int rollA = combatantA.rollDice();
        int rollB = combatantB.rollDice();
        Arcane.logger.info(combatantA + " fought " + combatantB+ "\n");
        if(rollA > rollB) {
            Integer damageForCombatantB = rollA - rollB;
            combatantB.takeDamage(damageForCombatantB);
            if (combatantB.isDead()) {
                Arcane.logger.info(combatantB + " was killed\n");
            }
            Arcane.logger.info(combatantB + " lost to " + combatantA+ "\n");
        } else if (rollB > rollA) {
            Integer damageForCombatantA = rollB - rollA;
            combatantA.takeDamage(damageForCombatantA);
            if (combatantB.isDead()) {
                Arcane.logger.info(combatantA + " was killed\n");
            }
            Arcane.logger.info(combatantA + " lost to " + combatantB+ "\n");
        }
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
