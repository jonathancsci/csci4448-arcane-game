package csci.ooad.arcane;

public class Glutton extends Adventurer{

    public Glutton(String name, double health) {
        super(name, health);
    }

    @Override
    public void turn(Room currentRoom) {
        Creature creature = currentRoom.getHealthiestCreature();
        Demon demon = currentRoom.getHealthiestDemon();
        if (demon != null) {
            combat(demon);
        } else if (currentRoom.isThereFood()) {
            Food food = currentRoom.takeFood();
            Arcane.logger.info(this + " just ate a " + food.getName() + "\n");
            EventBus.getInstance().notifyObservers(EventType.AteSomething, this +" just ate a "+ food.getName() + "\n");
            eatFood(food);
        } else if((creature != null) &&
                (currentRoom.getHealthiestAdventurer() == this)) {
            combat(creature);
        } else {
            moveRooms();
        }
    }
}
