package com.ooad;

public class Creature extends Entity {
    public Creature(String name, Integer health, Room currentRoom) {
        super(name, health, currentRoom);
        currentRoom.addOccupant(this);
    }

    public void step() {
        ;
    }
}
