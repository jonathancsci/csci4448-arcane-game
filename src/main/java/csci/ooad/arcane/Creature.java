package csci.ooad.arcane;

public class Creature extends Entity {
    public Creature(String name, double health) {
        super(name, health);
    }

    public String toString() {
        if (isDead()) {
            return "Creature "+this.getName()+"(health: "+this.getHealth()+"); DEAD";
        }
        return "Creature "+this.getName()+"(health: "+this.getHealth()+")";
    }
}
