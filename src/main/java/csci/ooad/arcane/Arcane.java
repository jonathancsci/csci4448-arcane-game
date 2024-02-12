package csci.ooad.arcane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

public class Arcane {
    private static final Logger logger = LoggerFactory.getLogger(Arcane.class);
    private static Arcane arcane;
    private Room[] maze;
    private int mazeWidth;
    private int mazeHeight;
    private ArrayList<Adventurer> adventurers = new ArrayList<>();
    private int turnCounter = 0;
    private boolean gameNotOver = true;
    private String endMessage = "";
    private Random randomNumberGenerator = new Random();

    public static void main(String [] args) {
        arcane = new Arcane();
        arcane.runGame();
    }

    public Arcane() {
        mazeWidth = 2;
        mazeHeight = 2;
        instantiateRooms();
        Arcane.arcane = this;
    }

    private void runGame() {
        logger.info(this.toString());
        turnCounter++;
        while(gameNotOver) {
            turn();
            logger.info(this.toString());
            turnCounter++;
        }
        logger.info(endMessage);
    }

    private void turn() {
        Collections.sort(adventurers);
        for(Adventurer adventurer : adventurers) {
            Room currentRoom = adventurer.getCurrentRoom();
            Creature creature = currentRoom.getHealthiestCreature();
            if((creature != null) &&
                    (currentRoom.getHealthiestAdventurer() == adventurer)) {
                combat(adventurer, creature);
            } else if ((creature != null) &&
                    (currentRoom.isThereFood())) {
                adventurer.eatFood(currentRoom.takeFood());
            } else {
                    adventurer.moveRooms();
            }
        }
    }

    public void combat(Entity combatantA, Entity combatantB) {
        int rollA = combatantA.rollDice();
        int rollB = combatantB.rollDice();
        if(rollA > rollB) {
            Integer damageForCombatantB = rollA - rollB;
            combatantB.takeDamage(damageForCombatantB);
        } else if (rollB > rollA) {
            Integer damageForCombatantA = rollB - rollA;
            combatantA.takeDamage(damageForCombatantA);
        }
    }

    public static void endGame(String endMessage) {
        arcane.gameNotOver = false;
        arcane.endMessage = endMessage;
    }

    private void instantiateRooms() {
        int mazeSize = mazeHeight*mazeWidth;
        maze = new Room[mazeSize];
        createRooms();
        setRoomNames();
        autofillRoomConnections();
        adventurers.add(new Adventurer("Tim",5,maze[randomNumberGenerator.nextInt(mazeSize)]));
        new Creature("Cobblebeast",5,maze[randomNumberGenerator.nextInt(mazeSize)]);
    }

    private void createRooms() {
        for(int i = 0; i<maze.length; i++) {
            maze[i] = new Room();
        }
    }

    private void setRoomNames() {
        //gross hard-coded names because these are definitely gonna get changed later when we have more than 4 rooms
        String[] roomNames = {"Northwest", "Northeast", "Southwest", "Southeast"};
        for(int i=0; i<maze.length; i++) {
            maze[i].setName(roomNames[i%roomNames.length]);
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
