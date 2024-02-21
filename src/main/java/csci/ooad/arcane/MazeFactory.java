package csci.ooad.arcane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MazeFactory {

    public void setGridRoomNames(Maze maze, int mazeWidth, int mazeHeight) {
        for(int i=0; i<maze.getSize(); i++) {
            String name = "";
            if (i < mazeWidth) {
                name = "North";
            } else if (i >= mazeHeight*(mazeWidth-1)) {
                name = "South";
            }
            if (i%mazeWidth == 0) {
                name += "West";
            } else if (i%mazeWidth == mazeWidth-1) {
                name += "East";
            }
            if(name.equals("")) {
                name = "Center";
            }
            maze.getRoom(i).setName(name);
        }
    }

    public void autofillGridConnections(Maze maze, int mazeWidth, int mazeHeight) {
        for(int x = 0; x < mazeWidth; x++) {
            for(int y = 0; y < mazeHeight; y++) {
                connectAdjacentRooms(maze,x,y,mazeWidth);
            }
        }
    }

    public void connectAdjacentRooms(Maze maze, int x, int y, int mazeWidth) {
        Room currentRoom = roomAtCoords(maze, x, y, mazeWidth);
        Room[] adjacentRooms = {roomAtCoords(maze, x - 1, y, mazeWidth), roomAtCoords(maze, x + 1, y, mazeWidth), roomAtCoords(maze, x, y - 1, mazeWidth), roomAtCoords(maze, x, y + 1, mazeWidth)};
        for (int i = 0; i < adjacentRooms.length; i++) {
            if (adjacentRooms[i] != null) {
                currentRoom.addRoomConnection(adjacentRooms[i]);
            }
        }
    }

    public Room roomAtCoords(Maze maze, int x, int y, int mazeWidth) {
        return maze.getRoom(mazeWidth*y+x);
    }

    public Maze gridRoomPrep(int mazeWidth, int mazeHeight) {
        Maze maze = new Maze();
        Room[] rooms = new Room[mazeWidth*mazeHeight];
        for (int i = 0; i < rooms.length; i++) {
            rooms[i] = new Room();
        }
        maze.setRooms(rooms);
        setGridRoomNames(maze,mazeWidth,mazeHeight);
        autofillGridConnections(maze,mazeWidth,mazeHeight);
        return maze;
    }

    public Maze gridMazePrep(int mazeWidth, int mazeHeight, int advNum, int creaNum, int foodNum) {
        Maze maze = gridRoomPrep(mazeWidth,mazeHeight);
        maze.addAdventurer(generateAdventurers(advNum));
        maze.addCreature(generateCreatures(creaNum));
        maze.addFood(generateFood(foodNum));
        return maze;
    }

    public Maze fourRoomGrid() {
        return gridMazePrep(2, 2, 1, 1, 5);
    }

    public Maze nineRoomGrid() {
        return gridMazePrep(3, 3, 2,2,10);
    }

    public Adventurer[] generateAdventurers(int num) {
        Adventurer[] adventurers = new Adventurer[num];
        for (int i = 0; i < num; i++) {
            adventurers[i] = new Adventurer();
        }
        return adventurers;
    }

    public Creature[] generateCreatures(int num) {
        Creature[] creatures = new Creature[num];
        for (int i = 0; i < num; i++) {
            creatures[i] = new Creature();
        }
        return creatures;
    }

    public Food[] generateFood(int num) {
        Food[] foods = new Food[num];
        for (int i = 0; i < num; i++) {
            foods[i] = new Food();
        }
        return foods;
    }


    //For our example of polymorphism, the addAdventurer method is overloaded to allow for a multitude of adventurers to be added, or for the room to be specified in testing
    //Kind of, I, Gavin, think of polymorphism as a bit of an umbrella term, a better example is in the toStrings


}
