package csci.ooad.arcane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Random;

public class MazeFactory {

    public static Maze createFourRoomGrid() {
        return createGrid(2, 2, 1, 1, 5);
    }

    public static Maze createNineRoomGrid() {
        return createGrid(3, 3, 2,2,10);
    }


    public static Maze createGrid(int mazeWidth, int mazeHeight, int adventurerNum, int creatureNum, int foodNum) {
        return new MazeBuilder()
                .createRooms(mazeWidth*mazeHeight)
                .setGridRoomNames(mazeWidth,mazeHeight)
                .createGridRoomConnections(mazeWidth)
                .generateAdventurers(adventurerNum)
                .generateCreatures(creatureNum)
                .generateFood(foodNum)
                .build();
    }

    public static Maze createMaze(int roomNum, int adventurerNum, int creatureNum, int foodNum) {
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
        private ArrayList<Adventurer> adventurers = new ArrayList<Adventurer>();
        private ArrayList<Creature> creatures = new ArrayList<Creature>();
        private static final Random random = new Random();

        public MazeBuilder createRooms(int num) {
            rooms = new Room[num];
            for (int i = 0; i < num; i++) {
                rooms[i] = new Room();
                rooms[i].setName("Room "+(i+1));
            }
            return this;
        }

        public MazeBuilder createFullRoomConnections() {
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
            for (int i = 0; i < num; i++) {
                adventurers.add(AdventurerFactory.createAdventurer());
                randomRoom().addOccupant(adventurers.get(i));
            }
            return this;
        }

        public MazeBuilder generateCreatures(int num) {
            for (int i = 0; i < num; i++) {
                creatures.add(CreatureFactory.createCreature());
                randomRoom().addOccupant(creatures.get(i));
            }
            return this;
        }

        public MazeBuilder generateFood(int num) {
            for (int i = 0; i < num; i++) {
                randomRoom().addFood(FoodFactory.createFood());
            }
            return this;
        }

        public Room randomRoom() {
            return rooms[random.nextInt(rooms.length)];
        }

        public MazeBuilder addAdventurer(Adventurer adventurer,int roomNum) {
            adventurers.add(adventurer);
            rooms[roomNum].addOccupant(adventurer);
            return this;
        }

        public MazeBuilder addCreature(Creature creature,int roomNum) {
            creatures.add(creature);
            rooms[roomNum].addOccupant(creature);
            return this;
        }

        public MazeBuilder addFood(Food food,int roomNum) {
            rooms[roomNum].addFood(food);
            return this;
        }

        public MazeBuilder addAdventurer(Adventurer adventurer) {
            adventurers.add(adventurer);
            randomRoom().addOccupant(adventurer);
            return this;
        }

        public MazeBuilder addCreature(Creature creature) {
            creatures.add(creature);
            randomRoom().addOccupant(creature);
            return this;
        }

        public MazeBuilder addFood(Food food) {
            randomRoom().addFood(food);
            return this;
        }

        public Maze build() {
            Maze maze = new Maze();
            maze.setRooms(rooms);
            maze.setAdventurers(adventurers);
            maze.setCreatures(creatures);
            return maze;
        }
    }
}
