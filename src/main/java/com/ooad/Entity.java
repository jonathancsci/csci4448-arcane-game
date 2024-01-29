package com.ooad;

import java.util.Random;

public class Entity {
    // Attributes
    private String name;
    private Integer health;
    private Room currentRoom;

    private Random randomNumberGenerator;

    // Constructor
    public Entity(String name, Integer health, Room currentRoom) {
        this.name = name;
        this.health = health;
        this.currentRoom = currentRoom;
        this.randomNumberGenerator = new Random();
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
}
