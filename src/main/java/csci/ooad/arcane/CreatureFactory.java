package csci.ooad.arcane;

import java.util.Random;
public class CreatureFactory {
    private static final String[] names = {"Cobblebeast", "Dimcreeper", "Ooze", "Unruly Armor","Cavern Crab", "Snapdragon", "Wyvern"};
    private static final String[] demonNames = {"Satan","Asmodeus","Beelzebub","Giratina","Rubilax"};

    private static final Random random = new Random();

    public static Creature createCreature() {
        String name = names[random.nextInt(names.length)];
        return new Creature(name, 3.0);
    }

    public static Creature createCreature(String name, double health) {
        return new Creature(name, health);
    }

    public static Creature createCreature(String[] names, double health) {
        String name = names[random.nextInt(names.length)];
        return new Creature(name, health);
    }

    public static Creature createRandomCreature() {
        switch(random.nextInt(4)) {
            case 3: return createDemon();
            default: return createCreature();
        }
    }

    public static Demon createDemon() {
        String name = demonNames[random.nextInt(demonNames.length)];
        return new Demon(name, 15.0);
    }

    public static Demon createDemon(String name, double health) {
        return new Demon(name, health);
    }

}
