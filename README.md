# Homework2 ARCANE-SetUp

Team Members:

1. Gavin Morrison
2. Jonathan Wu

Java Version: 17.0.10-tem

Description:
When the game is started, a 4 room maze is created, with both an Adventurer and a Monster being placed within random rooms. Each turn, the adventurer will either fight the monster in the room with him, or look for said monster if they aren't in the same room. When the fight happens, both combatants will roll a d6 each turn. We added a twist where whoever rolled higher doesn't just deal one damage, but deals damage equal to their dice roll. This continues until either the adventurer or monster have been slain.

Screenshots:
![coverageScreenshot.png](coverageScreenshot.png)
![testCallStackScreenshot.png](testCallStackScreenshot.png)

UML Diagram:
```mermaid
classDiagram
    class Arcane {
        - Arcane
        - maze
        - mazeWidth
        - mazeHeight
        - adventurers
        - turnCounter
        - gameNotOver
        - endMessage
        - randomNumberGenerator
        + main(args)
        + Arcane()
        + getRoom(x, y)
        + getMaze()
        - setRoomNames()
        - runGame()
        - turn()
        + endGame(endMessage)
        - createRooms()
        - instantiateRooms()
        - connectRoom(x, y)
        - autofillRoomConnections()
        + toString()
    }
    class Room {
        - ArrayList<Room> connectedRooms
        - ArrayList<Entity> occupants
        - String name
        + Room()
        + getEntityOfClass(classname)
        + getOccupants()
        + getConnectedRooms()
        + getName()
        + setName(name)
        + addRoomConnection(room)
        + addOccupant(entity)
        + removeOccupant(entity)
        - Combat(combatantA, combatantB)
        + turn()
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
        + setName(newName)
        + setHealth(newHealth)
        + setCurrentRoom(newRoom)
        + rollDice()
        + takeDamage(damage)
        + isDead()
    }
    class Adventurer {
        + Adventurer(name, health, currentRoom)
        + moveRooms()
        + toString()
    }
    class Creature {
        + Creature(name, health, currentRoom)
        + toString()
    }
    class Food {
        + Food(name, healthRestored)
        - name
        - healthRestored
    }
    Entity <|-- Creature
    Entity <|-- Adventurer
```

Output (note: our testing flushes the console between runs, so you only get this output from the main method):

ARCANE MAZE: turn 1
Northwest
Northeast
Southwest
Adventurer Tim(health: 5) is here
Creature Cobblebeast(health: 5) is here
Southeast

ARCANE MAZE: turn 2
Northwest
Northeast
Southwest
Adventurer Tim(health: 5) is here
Creature Cobblebeast(health: 5) is here
Southeast

ARCANE MAZE: turn 3
Northwest
Northeast
Southwest
Adventurer Tim(health: 5) is here
Creature Cobblebeast(health: 2) is here
Southeast

ARCANE MAZE: turn 4
Northwest
Northeast
Southwest
Adventurer Tim(health: 2) is here
Creature Cobblebeast(health: 2) is here
Southeast

ARCANE MAZE: turn 5
Northwest
Northeast
Southwest
Adventurer Tim(health: 2) is here
Creature Cobblebeast(health: 2) is here
Southeast

ARCANE MAZE: turn 6
Northwest
Northeast
Southwest
Adventurer Tim(health: 1) is here
Creature Cobblebeast(health: 2) is here
Southeast

ARCANE MAZE: turn 7
Northwest
Northeast
Southwest
Adventurer Tim(health: 0) is here
Creature Cobblebeast(health: 2) is here
Southeast

Tim was defeated!

Process finished with exit code 0