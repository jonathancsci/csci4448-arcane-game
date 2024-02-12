package csci.ooad.arcane;

public class Creature extends Entity {
    static String[] possibleNames = {"Cobblebeast","Dimcreeper","Ooze","Unruly Armor","Cavern Crab","Snapdragon","Wyvern"};
    public Creature(String name, Integer health, Room currentRoom) {
        super(name, health, currentRoom);
        currentRoom.addOccupant(this);
    }

    public Creature(Room currentRoom) {
        super(possibleNames, 3, currentRoom);
        currentRoom.addOccupant(this);
    }

    public String toString() {
        return "Creature "+this.getName()+"(health: "+this.getHealth()+")";
    }
}
