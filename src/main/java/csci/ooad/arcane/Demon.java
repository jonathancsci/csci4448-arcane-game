package csci.ooad.arcane;

public class Demon extends Creature {
    public Demon() {
        super(possibleNames, 15);
    }

    public Demon(String name, double health) {
        super(name, health);
    }
}
