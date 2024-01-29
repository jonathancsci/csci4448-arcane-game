# Homework2 ARCANE-SetUp

Team Members:

1. Gavin Morrison
2. Jonathan Wu

Java Version: 17.0.10-tem

Comments / Descriptions:
```mermaid
classDiagram
    class Arcane {
        - maze
        + main()
        + Arcane()
        + runGame()
        + step()
    }
    class Room {
        - ArrayList<Room> connectedRooms
        - ArrayList<Entity> occupants
        - String name
        + Room()
        + getOccupants()
        + addRoomConnection()
        + addOccupant()
        + removeOccupant()
        + getConnectedRooms()
        + getName()
        + setName()
        + step()
        + toString()
    }
    class Entity {
        - String name
        - Integer health
        - currentRoom
        - randomNumberGenerator
        + Entity(name, health, currentRoom)
        + getName()
        + getHealth()
        + getCurrentRoom()
        + getRandomNumberGenerator()
        + setName()
        + setHealth()
        + set setCurrentRoom()
        + rollDice()
        + step()
    }
    class Adventurer {
        + Adventurer(name, health, currentRoom)
        + moveRooms()
        + step()
    }
    class Creature {
        + Creature(name, health, currentRoom)
        + step()
    }
    Entity <|-- Creature
    Entity <|-- Adventurer
```