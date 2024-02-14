package csci.ooad.arcane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

public class Arcane {
    private static final Logger logger = LoggerFactory.getLogger(Arcane.class);
    private Room[] maze;
    private int mazeWidth;
    private int mazeHeight;
    private ArrayList<Adventurer> adventurers = new ArrayList<>();
    private ArrayList<Creature> creatures = new ArrayList<>();
    private int turnCounter = 0;
    private String endMessage = "";
    private Random randomNumberGenerator = new Random();

    public static void main(String [] args) { //TODO: MARKED FOR DEATH
        Arcane arcane = new Arcane();
        arcane.runGame();
    }

    public Arcane() {

    }

    public Arcane(int mazeWidth, int mazeHeight) {  //TODO: MARKED FOR DEATH
        this.mazeWidth = mazeWidth;
        this.mazeHeight = mazeHeight;
        instantiateRooms(2,2,10);
    }

    public void runGame() {
        logger.info(this.toString());
        turnCounter++;
        while(!isGameOver()) {
            turn();
            logger.info(this.toString());
            turnCounter++;
        }
        logger.info(endMessage);
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
                logger.info(adventurer+" just ate a "+food.getName() + "\n");
                adventurer.eatFood(food);
            } else {
                    adventurer.moveRooms();
            }
        }
    }

    public void combat(Adventurer combatantA, Creature combatantB) {
        int rollA = combatantA.rollDice();
        int rollB = combatantB.rollDice();
        logger.info(combatantA + " fought " + combatantB+ "\n");
        if(rollA > rollB) {
            Integer damageForCombatantB = rollA - rollB;
            combatantB.takeDamage(damageForCombatantB);
            if (combatantB.isDead()) {
                logger.info(combatantB + " was killed\n");
            }
            logger.info(combatantB + " lost to " + combatantA+ "\n");
        } else if (rollB > rollA) {
            Integer damageForCombatantA = rollB - rollA;
            combatantA.takeDamage(damageForCombatantA);
            if (combatantB.isDead()) {
                logger.info(combatantA + " was killed\n");
            }
            logger.info(combatantA + " lost to " + combatantB+ "\n");
        }
    }

    public boolean isGameOver() {
        if(checkAllCreaturesDead()) {
            endMessage = "The Adventurers have triumphed!";
            return true;
        }
        if(checkAllAdventurersDead()) {
            endMessage = "The Adventurers have died horribly!";
            return true;
        }
        return false;
    }

    private boolean checkAllCreaturesDead() {
        for (int i = 0; i < creatures.size(); i++) {
            if(!creatures.get(i).isDead()) {
                return false;
            }
        }
        return true;
    }

    private boolean checkAllAdventurersDead() {
        for (int i = 0; i < adventurers.size(); i++) {
            if(!adventurers.get(i).isDead()) {
                return false;
            }
        }
        return true;
    }

    private void instantiateRooms(int adventurerNum, int creatureNum, int foodNum) { //TODO: MARKED FOR DEATH
        int mazeSize = mazeHeight*mazeWidth;
        maze = new Room[mazeSize];
        createRooms();
        setRoomNames();
        autofillRoomConnections();
        generateAdventurers(adventurerNum);
        generateCreatures(creatureNum);
        generateFood(foodNum);
    }

    private void createRooms() { //TODO: MARKED FOR DEATH
        for(int i = 0; i<maze.length; i++) {
            maze[i] = new Room();
        }
    }

    public void mazeRoomPrep(Room[] rooms, int mazeWidth, int mazeHeight) {
        setMaze(rooms);
        setMazeWidth(mazeWidth);
        setMazeHeight(mazeHeight);
        setRoomNames();
        autofillRoomConnections();
    }

    public void fullMazePrep(Room[] rooms, int mazeWidth, int mazeHeight, Adventurer[] adventurers, Creature[] creatures, Food[] food) {
        mazeRoomPrep(rooms,mazeWidth,mazeHeight);
        addAdventurer(adventurers);
        addCreature(creatures);
        addFood(food);
    }

    public void setMaze(Room[] rooms) {
        maze = rooms;
    }

    public void setMazeWidth(int mazeWidth) {
        this.mazeWidth = mazeWidth;
    }

    public void setMazeHeight(int mazeHeight) {
        this.mazeHeight = mazeHeight;
    }

    public void setRoomNames() {
        for(int i=0; i<maze.length; i++) {
            String name = "";
            if (i < mazeWidth) {
                name = "North";
            } else if (i >= mazeHeight*(mazeWidth-1)) {
                name = "South";
            }
            if (i%mazeWidth == 0) {
                name += "West";
            } else if (i%mazeWidth == mazeWidth-1) {
                name += "East";
            }
            if(name.equals("")) {
                name = "Center";
            }
            maze[i].setName(name);
        }
    }

    public void autofillRoomConnections() {
        for(int x = 0; x < mazeWidth; x++) {
            for(int y = 0; y < mazeHeight; y++) {
                connectAdjacentRooms(x,y);
            }
        }
    }

    public void connectAdjacentRooms(int x, int y) {
        Room currentRoom = getRoom(x,y);
        Room[] adjacentRooms = {getRoom(x-1,y),getRoom(x+1,y),getRoom(x,y-1),getRoom(x,y+1)};
        for(int i=0; i < adjacentRooms.length; i++) {
            if(adjacentRooms[i] != null) {
                currentRoom.addRoomConnection(adjacentRooms[i]);
            }
        }
    }

    private void generateAdventurers(int number) { //TODO: MARKED FOR DEATH
        for (int i = 0; i < number; i++) {
            addAdventurer(new Adventurer());
        }
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

    private void generateCreatures(int number) { //TODO: MARKED FOR DEATH
        for (int i = 0; i < number; i++) {
            addCreature(new Creature());
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

    private void generateFood(int number) { //TODO: MARKED FOR DEATH
        for (int i = 0; i < number; i++) {
            addFood(new Food());
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

    public Room getRoom(int x, int y) {
        if (x < 0 || y < 0 || x >= mazeWidth || y>= mazeHeight) {
            return null;
        } else {
            return maze[x+(y*mazeWidth)];
        }
    }

    public Room getRandomRoom() {
        return maze[randomNumberGenerator.nextInt(mazeHeight*mazeWidth)];
    }

    public String toString() {
        String status = "ARCANE MAZE: turn "+(turnCounter+1)+"\n";
        for(int i=0; i<maze.length; i++) {
            status += maze[i];
        }
        return status;
    }
}
