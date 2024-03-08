package csci.ooad.arcane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Random;

public class Entity implements Comparable<Entity> {
    //Entity implements the Comparable interface and is inherited by Adventurer and Creature
    private static final Logger logger = LoggerFactory.getLogger(Arcane.class);
    // Attributes
    private String name;
    private double health;
    private Room currentRoom;

    private Random randomNumberGenerator;

    private static String[] possibleNames;

    // Constructor
    public Entity(String name, double health) {
        this.name = name;
        this.health = health;
        this.randomNumberGenerator = new Random();
    }

    public Entity(String[] nameOptions, double health) {
        this.health = health;
        this.randomNumberGenerator = new Random();
        this.name = nameOptions[randomNumberGenerator.nextInt(nameOptions.length)];
    }

    @Override
    public int compareTo(Entity other) {
        return ((Double)other.getHealth()).compareTo(this.getHealth());
    }

    // Getters
    public String getName() {
        return this.name;
    }

    public double getHealth() {
        return this.health;
    }

    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    public Random getRandomNumberGenerator() {
        return this.randomNumberGenerator;
    }

    // Setters
    public void setName(String newName) {
        this.name = newName;
    }

    public void setHealth(double newHealth) {
        this.health = newHealth;
    }

    public void setCurrentRoom(Room newRoom) {
        this.currentRoom = newRoom;
    }

    // Methods
    public void turn(Room currentRoom) {

    }

    public void combat(Entity foe) {
        int rollA = rollDice();
        int rollB = foe.rollDice();
        Arcane.logger.info(this + " fought " + foe+ "\n");
        if(rollA > rollB) {
            Integer damageForCombatantB = rollA - rollB;
            foe.takeDamage(damageForCombatantB);
            if (foe.isDead()) {
                Arcane.logger.info(foe + " was killed\n");
            }
            Arcane.logger.info(foe + " lost to " + this+ "\n");
        } else if (rollB > rollA) {
            Integer damageForCombatantA = rollB - rollA;
            takeDamage(damageForCombatantA);
            if (isDead()) {
                Arcane.logger.info(this + " was killed\n");
            }
            Arcane.logger.info(this + " lost to " + foe+ "\n");
            EventBus.getInstance().notifyObservers(EventType.FightOutcome,"I lost");
        }
    }

    public Integer rollDice() {
        return randomNumberGenerator.nextInt(6) + 1;
    }

    public void takeDamage(double damage) {
        this.health -= damage;
        this.health = Math.max(0, this.health);
    }

    public boolean isDead() {
        return this.health <= 0;
    }

}
