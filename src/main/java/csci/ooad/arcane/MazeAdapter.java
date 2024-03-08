package csci.ooad.arcane;

import java.util.ArrayList;
import java.util.List;

public class MazeAdapter implements IMaze {
    private Arcane arcane;
    public List<String> getRooms() {
        List<String> names = new ArrayList<String>();
        Room[] rooms = arcane.getRooms();
        for (Room r : rooms) {
            names.add(r.getName());
        }
        return names;
    }
    public List<String> getNeighborsOf(String room) {
        Room roomObject = getRoomByName(room);
        List<Room> connectedRooms = roomObject.getConnectedRooms();
        List<String> names = new ArrayList<String>();
        for (Room r : connectedRooms) {
            names.add(r.getName());
        }
        return names;

    }
    public List<String> getContents(String room) {
        Room roomObject = getRoomByName(room);
        List<Entity> occupants = roomObject.getOccupants();
        List<Food> food = roomObject.getFood();
        List<String> names = new ArrayList<String>();
        for (Entity e : occupants) {
            names.add(e.getName());
        }
        for (Food f : food) {
            names.add(f.getName());
        }
        return names;
    }

    public Room getRoomByName(String name) {
        Room[] rooms = arcane.getRooms();
        Room room = null;
        for (Room r : rooms) {
            if(r.getName().equals(name)) {
                room = r;
            }
        }
        return room;
    }
}
