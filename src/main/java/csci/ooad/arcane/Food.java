package csci.ooad.arcane;

import java.util.Random;

public class Food {
    static String[] possibleNames = {"Carrot","Apple","Burger","Soup","Cheese Wheel","Steak","Cake","Ice Cream","Spaghetti"};
    private String name;
    private Integer healthRestored;
    public Food(String name, Integer healthRestored) {
        Random randomNumberGenerator = new Random();
        this.name = name;
        this.healthRestored = healthRestored;
    }

    public Food() {
        Random randomNumberGenerator = new Random();
        this.name = possibleNames[randomNumberGenerator.nextInt(possibleNames.length)];
        this.healthRestored = 1;
    }

    public String getName() {
        return this.name;
    }

    public Integer getHealthRestored() {
        return this.healthRestored;
    }

    public String toString() {
        return this.name;
    }
}
