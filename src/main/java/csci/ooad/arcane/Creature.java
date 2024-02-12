package csci.ooad.arcane;

public class Creature extends Entity {
    static String[] possibleNames = {"Cobblebeast","Dimcreeper","Ooze","Unruly Armor","Cavern Crab","Snapdragon","Wyvern"};
    public Creature(String name, Integer health) {
        super(name, health);
    }

    public Creature() {
        super(possibleNames, 3);
    }

    public String toString() {
        if (isDead()) {
            return "Creature "+this.getName()+"(health: "+this.getHealth()+"); DEAD";
        }
        return "Creature "+this.getName()+"(health: "+this.getHealth()+")";
    }
}
