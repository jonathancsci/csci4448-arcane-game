package csci.ooad.arcane;

import java.util.Random;

public class AdventurerFactory {

    private static final String[] names = {"Bill","Ted","Alice","Bob","Kathy","John"};
    private static final String[] knightNames = {"Sir Lancelot","Sir Arthur","Sir Gallarad"};
    private static final String[] cowardNames = {"Townsfolk","Lost Child"};
    private static final String[] gluttonNames = {"Chubster","Sir Cumference","Gorby"};

    private static final Random random = new Random();

    public static Adventurer createAdventurer() {
        String name = names[random.nextInt(names.length)];
        return new Adventurer(name, 5.0);
    }

    public static Adventurer createAdventurer(String name, double health) {
        return new Adventurer(name, health);
    }

    public static Adventurer createAdventurer(String[] names, double health) {
        String name = names[random.nextInt(names.length)];
        return new Adventurer(name, health);
    }

    public static Adventurer createRandomAdventurer() {
        switch(random.nextInt(5)) {
            case 1: return createKnight();
            case 2: return createCoward();
            case 3: return createGlutton();
            default: return createAdventurer();
        }
    }

    public static Coward createCoward() {
        String name = knightNames[random.nextInt(knightNames.length)];
        return new Coward(name, 5);
    }

    public static Glutton createGlutton() {
        String name = gluttonNames[random.nextInt(gluttonNames.length)];
        return new Glutton(name, 3);
    }

    public static Knight createKnight() {
        String name = cowardNames[random.nextInt(cowardNames.length)];
        return new Knight(name, 8);
    }

    public static Coward createCoward(String name, double health) {
        return new Coward(name, health);
    }

    public static Glutton createGlutton(String name, double health) {
        return new Glutton(name, health);
    }

    public static Knight createKnight(String name, double health) {
        return new Knight(name, health);
    }

}
