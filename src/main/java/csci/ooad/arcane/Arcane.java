package csci.ooad.arcane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

public class Arcane {
    public static final Logger logger = LoggerFactory.getLogger(Arcane.class);
    private Maze maze;
    private int turnCounter = 0;
    private String endMessage = "";
    private Random randomNumberGenerator = new Random();

    public static void main(String[] args) {
        Maze maze = new MazeFactory().fourRoomGrid();
        Arcane arcane = new Arcane(maze);
        arcane.runGame();
    }

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

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    //the toString is a more conventional form of polymorphism where all of these objects are behaving differently when called, but being treated the same by the callee
    public String toString() {
        String status = "ARCANE MAZE: turn "+(turnCounter+1)+"\n";
        status += maze.toString();
        return status;
    }
}
