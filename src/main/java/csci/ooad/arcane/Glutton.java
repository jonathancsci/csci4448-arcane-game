package csci.ooad.arcane;

public class Glutton extends Adventurer{
    public Glutton() {
        super(possibleNames, 3);
    }

    @Override
    public void turn(Room currentRoom) {
        Creature creature = currentRoom.getHealthiestCreature();
        if (currentRoom.isThereFood()) {
            Food food = currentRoom.takeFood();
            Arcane.logger.info(this + " just ate a " + food.getName() + "\n");
            eatFood(food);
        } else if((creature != null) &&
                (currentRoom.getHealthiestAdventurer() == this)) {
            combat(creature);
        } else {
            moveRooms();
        }
    }
}
