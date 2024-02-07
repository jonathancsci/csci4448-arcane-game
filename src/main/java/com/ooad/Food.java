package com.ooad;

public class Food {
    private String name;
    private Integer healthRestored;
    public void Food(String name, Integer healthRestored) {
        this.name = name;
        this.healthRestored = healthRestored;
    }

    public String getName() {
        return this.name;
    }

    public Integer getHealthRestored() {
        return this.healthRestored;
    }
}
