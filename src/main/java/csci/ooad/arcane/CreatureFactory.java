package csci.ooad.arcane;

import java.util.Random;
public class CreatureFactory {

    private static final Random random = new Random();

    public static Creature createCreature() {
        String name = Creature.possibleNames[random.nextInt(Creature.possibleNames.length)];
        return new Creature(name, 3.0);
    }

    public static Creature createCreature(String name, double health) {
        return new Creature(name, health);
    }

    public static Creature createCreature(String[] names, double health) {
        String name = names[random.nextInt(names.length)];
        return new Creature(name, health);
    }

    public static Demon createDemon(String name, double health) {
        return new Demon(name, health);
    }

}
