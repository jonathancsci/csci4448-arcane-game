package csci.ooad.arcane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arcane implements IObservable, IObserver {
    public static final Logger logger = LoggerFactory.getLogger(Arcane.class);
    private Maze maze;
    private int turnCounter = 0;
    private String endMessage = "";
    private Random randomNumberGenerator = new Random();
    private List<IObserver> observerList = new ArrayList<>();

    public Arcane(Maze maze) {
        this.maze = maze;
        EventBus.getInstance().attach(this);
    }

    public void runGame() {
        logger.info(this.toString());
        turnCounter++;
        while(!isGameOver()) {
            maze.turn();
            logger.info(this.toString());
            EventBus.getInstance().notifyObservers(EventType.TurnEnded, "Turn " + this.turnCounter + " just ended");
            turnCounter++;
        }
        logger.info(endMessage);
        EventBus.getInstance().notifyObservers(EventType.GameOver, this.endMessage);
    }

    public boolean isGameOver() {
        if(maze.checkAllCreaturesDead()) {
            endMessage = "The Adventurers have triumphed! ";
            endMessage += "The Adventurers left alive are " + maze.getAliveAdventurers();
            return true;
        }
        if(maze.checkAllAdventurersDead()) {
            endMessage = "The Adventurers have died horribly! ";
            endMessage += "The Creatures left alive are " + maze.getAliveCreatures();
            return true;
        }
        return false;
    }

    public void attach(IObserver newObserver) {
        this.observerList.add(newObserver);
    }

    public void notifyObservers(EventType postedEventType, String postedEventDescription) {
        for (IObserver observer : this.observerList) {
            logger.info("Arcane is observed by "+observer+"\n");
            observer.update(postedEventType, postedEventDescription);
        }
    }

    public void update(EventType postedEventType, String postedEventDescription) {
        this.notifyObservers(postedEventType, postedEventDescription);
    }
  
    public Room[] getRooms() {
        return maze.getRooms();
    }

    //the toString is a more conventional form of polymorphism where all of these objects are behaving differently when called, but being treated the same by the callee
    public String toString() {
        String status = "ARCANE MAZE: turn "+(turnCounter+1)+"\n";
        status += maze.toString();
        return status;
    }
}
