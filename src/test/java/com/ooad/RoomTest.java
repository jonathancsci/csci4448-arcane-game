package com.ooad;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

public class RoomTest {

    @Test
    public void addRoomConnectionTest() {
        Room room1 = new Room();
        Room room2 = new Room();
        room1.addRoomConnection(room2);
        assertSame(room1.getConnectedRooms().get(0), room2);
    }
}
