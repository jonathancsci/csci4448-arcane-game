package csci.ooad.arcane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MazeFactory {
    private Room[] rooms;
    private Adventurer[] adventurers;
    private Creature[] creatures;
    private Food[] foods;

    public Maze buildFourRoomGrid() {
        return buildGrid(2, 2, 1, 1, 5);
    }

    public Maze buildNineRoomGrid() {
        return buildGrid(3, 3, 2,2,10);
    }

    public Maze buildGrid(int mazeWidth, int mazeHeight, int advNum, int creaNum, int foodNum) {
        rooms = createRooms(mazeWidth*mazeHeight);
        setGridRoomNames(mazeWidth,mazeHeight);
        gridInterconnect(mazeWidth);
        adventurers = generateAdventurers(advNum);
        creatures = generateCreatures(creaNum);
        foods = generateFood(foodNum);
        return build();
    }

    public Maze buildMaze(int roomNum, int adventurerNum, int creatureNum, int foodNum) {
        rooms = createRooms(roomNum);
        fullyInterconnect();
        adventurers = generateAdventurers(adventurerNum);
        creatures = generateCreatures(creatureNum);
        foods = generateFood(foodNum);
        return build();
    }

    private Room[] createRooms(int num) {
        Room[] rooms = new Room[num];
        for (int i = 0; i < num; i++) {
            rooms[i] = new Room();
            rooms[i].setName("Room "+i);
        }
        return rooms;
    }

    private void fullyInterconnect() {
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms.length; j++) {
                if(i != j) {
                    rooms[i].addRoomConnection(rooms[j]);
                }
            }
        }
    }

    public void setGridRoomNames(int mazeWidth, int mazeHeight) {
        for(int i=0; i<rooms.length; i++) {
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
            if(name.isEmpty()) {
                name = "Center";
            }
            rooms[i].setName(name);
        }
    }

    public void gridInterconnect(int mazeWidth) {
        for (int i = 1; i < rooms.length; i++) {
            connectRooms(i, i+1);
            connectRooms(i, i-1);
            connectRooms(i,i+mazeWidth);
            connectRooms(i,i-mazeWidth);
        }
    }

    public void connectRooms(int roomA, int roomB) {
        if(roomA < 0 || roomA >= rooms.length || roomB < 0 || roomB >= rooms.length) {

        } else {
            rooms[roomA].addRoomConnection(rooms[roomB]);
            rooms[roomB].addRoomConnection(rooms[roomA]);
        }
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

    public Maze build() {
        Maze maze = new Maze();
        maze.setRooms(rooms);
        for(Adventurer adventurer : adventurers) {
            maze.addAdventurer(adventurer, maze.getRandomRoom());
        }
        for(Creature creature : creatures) {
            maze.addCreature(creature, maze.getRandomRoom());
        }
        for(Food food : foods) {
            maze.addFood(food, maze.getRandomRoom());
        }
        return maze;
    }
}
