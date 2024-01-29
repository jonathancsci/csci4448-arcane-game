package com.ooad;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RoomTest {

    @Test
    public void addRoomConnectionTest() {
        Room room1 = new Room();
        Room room2 = new Room();
        room1.addRoomConnection(room2);
        assertSame(room1.getConnectedRooms().get(0), room2);
    }

    @Test
    public void roomNameTest() {
        Room room = new Room();
        room.setName("Atrium");
        assertEquals("Atrium",room.getName());
    }

    @Test
    public void toStringTest() {
        Room room = new Room();
        room.setName("Southwest");
        assertEquals("  Southwest\n",room.toString());
    }
}
