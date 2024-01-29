package com.ooad;

public class Creature extends Entity {
    public Creature(String name, Integer health, Room currentRoom) {
        super(name, health, currentRoom);
        currentRoom.addOccupant(this);
    }

    public String toString() {
        return "Creature "+this.getName()+"(health: "+this.getHealth()+")";
    }
}
