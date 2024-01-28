package com.ooad;

public class Arcane {
    private Room[] maze;
    private int mazeWidth;
    private int mazeHeight;
    private int turnCounter = 0;
    private boolean notDone = true;

    public static void main(String [] args) {
        Arcane arcane = new Arcane();
        arcane.runGame();
    }

    public Arcane() {
        mazeWidth = 2;
        mazeHeight = 2;
        instantiateRooms();
    }

    public void runGame() {
        while(notDone && turnCounter < 5) {
            System.out.println(this);
            turnCounter++;
        }
    }

    public void runGame(int turnLimit) {
        while(notDone && turnCounter < turnLimit) {
            System.out.println(this);
            turnCounter++;
        }
    }

    private void instantiateRooms() {
        maze = new Room[mazeHeight*mazeWidth];
        createRooms();
        setRoomNames();
        autofillRoomConnections();
    }

    private void createRooms() {
        for(int i = 0; i<maze.length; i++) {
            maze[i] = new Room();
        }
    }

    private void setRoomNames() {
        //gross hard-coded names because these are definitely gonna get changed later when we have more than 4 rooms
        String[] roomNames = {"Northwest", "Northeast", "Southwest", "Southeast"};
        for(int i=0; i<maze.length; i++) {
            maze[i].setName(roomNames[i%roomNames.length]);
        }
    }

    private void autofillRoomConnections() {
        for(int x = 0; x < mazeWidth; x++) {
            for(int y = 0; y < mazeHeight; y++) {
                connectRoom(x,y);
            }
        }
    }

    private void connectRoom(int x, int y) {
        Room currentRoom = getRoom(x,y);
        Room[] adjacentRooms = {getRoom(x-1,y),getRoom(x+1,y),getRoom(x,y-1),getRoom(x,y+1)};
        for(int i=0; i < adjacentRooms.length; i++) {
            if(adjacentRooms[i] != null) {
                currentRoom.addRoomConnection(adjacentRooms[i]);
            }
        }
    }

    public Room getRoom(int x, int y) {
        if (x < 0 || y < 0 || x >= mazeWidth || y>= mazeHeight) {
            return null;
        } else {
            return maze[x+(y*mazeWidth)];
        }
    }

    public Room[] getMaze () {
        return maze;
    }

    public String toString() {
        String status = "ARCANE MAZE: turn "+(turnCounter+1)+"\n";
        for(int i=0; i<maze.length; i++) {
            status += maze[i];
        }
        return status;
    }
}
