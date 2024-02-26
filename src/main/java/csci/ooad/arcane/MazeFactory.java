package csci.ooad.arcane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MazeFactory {

    public Maze buildFourRoomGrid() {
        return createGrid(2, 2, 1, 1, 5);
    }

    public Maze buildNineRoomGrid() {
        return createGrid(3, 3, 2,2,10);
    }


    public Maze createGrid(int mazeWidth, int mazeHeight, int adventurerNum, int creatureNum, int foodNum) {
        return new MazeBuilder()
                .createRooms(mazeWidth*mazeHeight)
                .setGridRoomNames(mazeWidth,mazeHeight)
                .createGridRoomConnections(mazeWidth)
                .generateAdventurers(adventurerNum)
                .generateCreatures(creatureNum)
                .generateFood(foodNum)
                .build();
    }

    public Maze createMaze(int roomNum, int adventurerNum, int creatureNum, int foodNum) {
        return new MazeBuilder()
                .createRooms(roomNum)
                .createFullRoomConnections()
                .generateAdventurers(adventurerNum)
                .generateCreatures(creatureNum)
                .generateFood(foodNum)
                .build();
    }

    public static class MazeBuilder {
        private Room[] rooms;
        private Adventurer[] adventurers;
        private Creature[] creatures;
        private Food[] foods;

        private MazeBuilder createRooms(int num) {
            rooms = new Room[num];
            for (int i = 0; i < num; i++) {
                rooms[i] = new Room();
                rooms[i].setName("Room "+i);
            }
            return this;
        }

        private MazeBuilder createFullRoomConnections() {
            for (int i = 0; i < rooms.length; i++) {
                for (int j = 0; j < rooms.length; j++) {
                    if(i != j) {
                        rooms[i].addRoomConnection(rooms[j]);
                    }
                }
            }
            return this;
        }

        public MazeBuilder setGridRoomNames(int mazeWidth, int mazeHeight) {
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
            return this;
        }

        public MazeBuilder createGridRoomConnections(int mazeWidth) {
            for (int i = 1; i < rooms.length; i++) {
                connectRooms(i, i+1);
                connectRooms(i, i-1);
                connectRooms(i,i+mazeWidth);
                connectRooms(i,i-mazeWidth);
            }
            return this;
        }

        public MazeBuilder connectRooms(int roomA, int roomB) {
            if(!(roomA < 0 || roomA >= rooms.length || roomB < 0 || roomB >= rooms.length)) {
                rooms[roomA].addRoomConnection(rooms[roomB]);
                rooms[roomB].addRoomConnection(rooms[roomA]);
            }
            return this;
        }

        public MazeBuilder generateAdventurers(int num) {
            adventurers = new Adventurer[num];
            for (int i = 0; i < num; i++) {
                adventurers[i] = new Adventurer();
            }
            return this;
        }

        public MazeBuilder generateCreatures(int num) {
            creatures = new Creature[num];
            for (int i = 0; i < num; i++) {
                creatures[i] = new Creature();
            }
            return this;
        }

        public MazeBuilder generateFood(int num) {
            foods = new Food[num];
            for (int i = 0; i < num; i++) {
                foods[i] = new Food();
            }
            return this;
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
}
