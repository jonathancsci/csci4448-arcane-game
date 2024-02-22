package csci.ooad.arcane;

public class Knight extends Adventurer {
    static String[] possibleNames = {
            "Sir Bill", "Sir Tim", "Sir Dave"
    };

    public Knight() {
        super(possibleNames, 8.0);
    }

    @Override
    public void turn(Room currentRoom) {
        Creature creature = currentRoom.getHealthiestCreature();
        Demon demon = currentRoom.getHealthiestDemon();
        if (demon != null) {
            combat(demon);
        } else if(creature != null) {
            combat(creature);
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
