package csci.ooad.arcane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class Entity implements Comparable<Entity> {
    //Entity implements the Comparable interface and is inherited by Adventurer and Creature
    private static final Logger logger = LoggerFactory.getLogger(Arcane.class);
    // Attributes
    private String name;
    private Integer health;
    private Room currentRoom;

    private Random randomNumberGenerator;

    private static String[] possibleNames;

    // Constructor
    public Entity(String name, Integer health) {
        this.name = name;
        this.health = health;
        this.randomNumberGenerator = new Random();
    }

    public Entity(String[] nameOptions, Integer health) {
        this.health = health;
        this.randomNumberGenerator = new Random();
        this.name = nameOptions[randomNumberGenerator.nextInt(nameOptions.length)];
    }

    @Override
    public int compareTo(Entity other) {
        return other.getHealth().compareTo(this.getHealth());
    }

    // Getters
    public String getName() {
        return this.name;
    }

    public Integer getHealth() {
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

    public void setHealth(Integer newHealth) {
        this.health = newHealth;
    }

    public void setCurrentRoom(Room newRoom) {
        this.currentRoom = newRoom;
    }

    // Methods
    public Integer rollDice() {
        return randomNumberGenerator.nextInt(6) + 1;
    }

    public void takeDamage(Integer damage) {
        this.health -= damage;
        this.health = Math.max(0, this.health);
    }

    public boolean isDead() {
        return this.health <= 0;
    }

}
