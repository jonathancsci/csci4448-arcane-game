package csci.ooad.arcane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Collections;

public class Arcane implements IObservable {
    public static final Logger logger = LoggerFactory.getLogger(Arcane.class);
    private Maze maze;
    private int turnCounter = 0;
    private String endMessage = "";
    private Random randomNumberGenerator = new Random();
    private List<IObserver> observerList = new ArrayList<>();

    public Arcane(Maze maze) {
        this.maze = maze;
    }

    public void runGame() {
        logger.info(this.toString());
        turnCounter++;
        while(!isGameOver()) {
            maze.turn();
            logger.info(this.toString());
            turnCounter++;
        }
        logger.info(endMessage);
    }

    public boolean isGameOver() {
        if(maze.checkAllCreaturesDead()) {
            endMessage = "The Adventurers have triumphed!";
            return true;
        }
        if(maze.checkAllAdventurersDead()) {
            endMessage = "The Adventurers have died horribly!";
            return true;
        }
        return false;
    }

    public void attach(IObserver newObserver) {
        this.observerList.add(newObserver);
    }

    public void detach(IObserver observerToRemove) {
        this.observerList.remove(observerToRemove);
    }

    public void notifyObservers() {
        // Dummy data for now
        EventType eventType = EventType.GameOver;
        String eventDescription = "The Adventurers have triumphed!";

        for (IObserver observer : this.observerList) {
            observer.update(eventType, eventDescription);
        }
    }

    //the toString is a more conventional form of polymorphism where all of these objects are behaving differently when called, but being treated the same by the callee
    public String toString() {
        String status = "ARCANE MAZE: turn "+(turnCounter+1)+"\n";
        status += maze.toString();
        return status;
    }
}
