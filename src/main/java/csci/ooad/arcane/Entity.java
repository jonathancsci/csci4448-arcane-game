package csci.ooad.arcane;

import java.util.Random;

public class Entity implements Comparable<Entity> {
    // Attributes
    private String name;
    private Integer health;
    private Room currentRoom;

    private Random randomNumberGenerator;

    private static String[] possibleNames;

    // Constructor
    public Entity(String name, Integer health, Room currentRoom) {
        this.name = name;
        this.health = health;
        this.currentRoom = currentRoom;
        this.randomNumberGenerator = new Random();
    }

    public Entity(Integer health, Room currentRoom) {
        this.name = name;
        this.health = health;
        this.currentRoom = currentRoom;
        this.randomNumberGenerator = new Random();
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
