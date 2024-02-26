package csci.ooad.arcane;

import java.util.Random;

public class AdventurerFactory {

    private static final Random random = new Random();

    public static Adventurer createAdventurer() {
        String name = Adventurer.possibleNames[random.nextInt(Adventurer.possibleNames.length)];
        return new Adventurer(name, 5.0);
    }

    public static Adventurer createAdventurer(String name, double health) {
        return new Adventurer(name, health);
    }

    public static Adventurer createAdventurer(String[] names, double health) {
        String name = names[random.nextInt(names.length)];
        return new Adventurer(name, health);
    }
}
