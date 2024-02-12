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
    private boolean gameNotOver = true;
    private String endMessage = "";
    private Random randomNumberGenerator = new Random();

    public static void main(String [] args) {
        Arcane arcane = new Arcane();
        arcane.runGame();
    }

    public Arcane() {
        mazeWidth = 3;
        mazeHeight = 3;
        instantiateRooms(2,2,10);
    }

    public Arcane(int mazeWidth, int mazeHeight) {
        this.mazeWidth = mazeWidth;
        this.mazeHeight = mazeHeight;
        instantiateRooms(2,2,10);
    }

    private void runGame() {
        logger.info(this.toString());
        turnCounter++;
        while(gameNotOver) {
            turn();
            logger.info(this.toString());
            turnCounter++;
            checkGameOver();
        }
        logger.info(endMessage);
    }

    private void turn() {
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

    public void checkGameOver() {
        if(checkAllCreaturesDead()) {
            gameNotOver = false;
            endMessage = "The Adventurers have triumphed!";
        }
        if(checkAllAdventurersDead()) {
            gameNotOver = false;
            endMessage = "The Adventurers have died horribly!";
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

    private void instantiateRooms(int adventurerNum, int creatureNum, int foodNum) {
        int mazeSize = mazeHeight*mazeWidth;
        maze = new Room[mazeSize];
        createRooms();
        setRoomNames();
        autofillRoomConnections();
        generateAdventurers(adventurerNum);
        generateCreatures(creatureNum);
        generateFood(foodNum);
    }

    private void createRooms() {
        for(int i = 0; i<maze.length; i++) {
            maze[i] = new Room();
        }
    }

    private void setRoomNames() {
        for(int i=0; i<maze.length; i++) {
            String name = "";
            if (i < mazeWidth) {
                name = "North";
            } else if (i >= mazeHeight*(mazeWidth-1)) {
                name = "South";
            }
            if (i%mazeWidth == 0) {
                name += "East";
            } else if (i%mazeWidth == mazeWidth-1) {
                name += "West";
            }
            if(name.equals("")) {
                name = "Center";
            }
            maze[i].setName(name);
        }
    }

    private void autofillRoomConnections() {
        for(int x = 0; x < mazeWidth; x++) {
            for(int y = 0; y < mazeHeight; y++) {
                connectAdjacentRooms(x,y);
            }
        }
    }

    private void connectAdjacentRooms(int x, int y) {
        Room currentRoom = getRoom(x,y);
        Room[] adjacentRooms = {getRoom(x-1,y),getRoom(x+1,y),getRoom(x,y-1),getRoom(x,y+1)};
        for(int i=0; i < adjacentRooms.length; i++) {
            if(adjacentRooms[i] != null) {
                currentRoom.addRoomConnection(adjacentRooms[i]);
            }
        }
    }

    private void generateAdventurers(int number) {
        for (int i = 0; i < number; i++) {
            addAdventurer(new Adventurer());
        }
    }

    private void addAdventurer(Adventurer adventurer) {
        adventurers.add(adventurer);
        maze[randomNumberGenerator.nextInt(mazeHeight*mazeWidth)].addOccupant(adventurer);
    }

    private void addAdventurer(Adventurer[] adventurers) {
        for(Adventurer a : adventurers) {
            addAdventurer(a);
        }
    }

    private void generateCreatures(int number) {
        for (int i = 0; i < number; i++) {
            addCreature(new Creature());
        }
    }

    private void addCreature(Creature creature) {
        creatures.add(creature);
        maze[randomNumberGenerator.nextInt(mazeHeight*mazeWidth)].addOccupant(creature);
    }

    private void addCreature(Creature[] creatures) {
        for(Creature c : creatures) {
            addCreature(c);
        }
    }

    private void generateFood(int number) {
        for (int i = 0; i < number; i++) {
            addFood(new Food(1));
        }
    }

    private void addFood(Food food) {
        maze[randomNumberGenerator.nextInt(mazeHeight*mazeWidth)].addFood(food);
    }

    private void addFood(Food[] food) {
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

    public Room[] getMaze () {
        return maze;
    }

    public String toString() {
        String status = "ARCANE MAZE: turn "+(turnCounter+1)+"\n";
        for(int i=0; i<maze.length; i++) {
            status += maze[i];
        }
        return status;
    }
}
