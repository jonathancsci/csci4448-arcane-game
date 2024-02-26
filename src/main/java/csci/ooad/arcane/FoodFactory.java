package csci.ooad.arcane;

import java.util.Random;
public class FoodFactory {

    private static final Random random = new Random();

    public static Food createFood() {
        String name = Food.possibleNames[random.nextInt(Food.possibleNames.length)];
        return new Food(name, 1);
    }

    public static Food createFood(String name, Integer healthRestored) {
        return new Food(name, healthRestored);
    }

}
