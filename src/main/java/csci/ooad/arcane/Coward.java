package csci.ooad.arcane;

public class Coward extends Adventurer {
    public Coward() {
        super(possibleNames, 5);
    }

    @Override
    public void turn(Room currentRoom) {
        Creature creature = currentRoom.getHealthiestCreature();
        Demon demon = currentRoom.getHealthiestDemon();
        if (demon != null) {
            combat(demon);
        } else if(creature != null) {
            this.takeDamage(0.5);
            moveRooms();
        } else if ((creature == null) &&
                (currentRoom.isThereFood())) {
            Food food = currentRoom.takeFood();
            Arcane.logger.info(this+" just ate a "+food.getName() + "\n");
            eatFood(food);
        } else {
            moveRooms();
        }
    }
}
