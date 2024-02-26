package csci.ooad.arcane;

public class GameConfigurator {
    private static final MazeFactory mazeFactory= new MazeFactory();
    public static void main(String [] args) {
        for (int i = 0; i < args.length; i++) {

        }
        Arcane arcane = new Arcane(mazeFactory.buildFourRoomGrid());
        arcane.runGame();
    }
}
