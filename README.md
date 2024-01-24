# Homework2 ARCANE-SetUp

Team Members:

1. Gavin Morrison
2. Jonathan Wu

Java Version: 17.0.10-tem

Comments / Descriptions:
```mermaid
classDiagram
    class Game {
        +ui()
        +Maze maze
        + step()
        + isOver()
        + playGame()
    }
    class Maze {
        - Room[] rooms
    }
    class Room {
        + ArrayList<Room> connectedRooms
        + getConnectedRooms()
        + getName()
        + getOccupants()
        + toString()
        - String name
        - ArrayList<Entity> occupants
    }
    class Entity {
        + rollDice()
        - # String name
        - # Integer health
        - # currentRoom
    }
    class Adventurer {
        + move()
    }
    class Creature {
    
    }
```