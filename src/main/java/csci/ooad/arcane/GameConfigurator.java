package csci.ooad.arcane;

import csci.ooad.layout.IMazeObserver;
import csci.ooad.layout.MazeObserver;

import java.util.List;

public class GameConfigurator {
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
        Arcane arcane = new Arcane(MazeFactory.createMaze(roomNum,adventurerNum,creatureNum,foodNum));

        int SECONDS_TO_PAUSE_BETWEEN_TURNS = 3;

        IMazeObserver mazeObserver = MazeObserver.getNewBuilder("Arcane Game")
                .useRadialLayoutStrategy()
                .setDelayInSecondsAfterUpdate(SECONDS_TO_PAUSE_BETWEEN_TURNS)
                .build();

        AudibleArcaneObserver audibleObserver = new AudibleArcaneObserver(arcane, List.of(EventType.All), 5);
        arcane.attach(audibleObserver);

        new MazeAdapter(arcane).attach(mazeObserver);

        arcane.runGame();
    }
}
