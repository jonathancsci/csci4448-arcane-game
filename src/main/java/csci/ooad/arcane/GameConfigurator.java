package csci.ooad.arcane;

public class GameConfigurator {
    private static final MazeFactory mazeFactory= new MazeFactory();
    public static void main(String [] args) {
        int adventurerNum = 2;
        int creatureNum = 2;
        int foodNum = 9;
        int roomNum = 5;
        for (int i = 0; i < args.length; i++) {
            if(args[i].equals("--numberOfAdventurers")) {
                adventurerNum = Integer.parseInt(args[i+1]);
            }
            if(args[i].equals("--numberOfCreatures")) {
                creatureNum = Integer.parseInt(args[i+1]);
            }
            if(args[i].equals("--numberOfFoodItems")) {
                foodNum = Integer.parseInt(args[i+1]);
            }
            if(args[i].equals("--numberOfRooms")) {
                roomNum = Integer.parseInt(args[i+1]);
            }
        }
        Arcane arcane = new Arcane(mazeFactory.createMaze(roomNum,adventurerNum,creatureNum,foodNum));
        arcane.runGame();
    }
}
