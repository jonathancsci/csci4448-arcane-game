# ARCANE

Team Members:

1. Gavin Morrison
2. Jonathan Wu

Java Version: 17.0.10-tem

Description:
When the game is started, a 4 room maze is created, with both an Adventurer and a Monster being placed within random rooms. Each turn, the adventurer will either fight the monster in the room with him, or look for said monster if they aren't in the same room. When the fight happens, both combatants will roll a d6 each turn. We added a twist where whoever rolled higher doesn't just deal one damage, but deals damage equal to their dice roll. This continues until either the adventurer or monster have been slain.

UML Diagram:
```mermaid
classDiagram
    class Adventurer {
        - private static final Logger logger
        + public Adventurer(String name, double health)
        + public void turn(Room currentRoom)
        + public void moveRooms()
        + public void eatFood(Food food)
        + public String toString()
    }
    class Arcane {
        - public static final Logger logger
        - private Maze maze
        - private int turnCounter
        - private String endMessage
        - private Random randomNumberGenerator
        + public Arcane(Maze maze)
        + public void runGame()
        + public boolean isGameOver()
        + public String toString()
    }
    class Coward {
        + public Coward(String name, double health)
        + public void turn(Room currentRoom)
    }
    class Creature {
        + public Creature(String name, double health)
        + public String toString()
    }
    class Demon {
        +  public Demon(String name, double health)
    }
    class Entity {
        - private static final Logger logger
        - private String name
        - private double health
        - private Room currentRoom
        - private Random randomNumberGenerator
        - private static String[] possibleNames
        + public Entity(String name, double health)
        + public Entity(String[] nameOptions, double health)
        + public int compareTo(Entity other)
        + public String getName()
        + public double getHealth()
        + public Room getCurrentRoom()
        + public Random getRandomNumberGenerator()
        + public void setName(String newName)
        + public void setHealth(double newHealth)
        + public void setCurrentRoom(Room newRoom)
        + public void turn(Room currentRoom)
        + public void combat(Entity foe)
        + public Integer rollDice()
        + public void takeDamage(double damage)
        + public boolean isDead()
    }
    class Food {
        - static String[] possibleNames
        - private String name
        - private Integer healthRestored
        + public Food(String name, Integer healthRestored)
        + public Food()
        + public String getName()
        + public Integer getHealthRestored()
        + public String toString()
    }
    class GameConfigurator {
        - private static final MazeFactory mazeFactory
        + public static void main(String [] args)
    }
    class Glutton {
        + public Glutton(String name, double health)
        + public void turn(Room currentRoom)
    }
    class Knight {
        + public Knight(String name, double health)
        + public void turn(Room currentRoom)
    }
    class Maze {
        - private Random randomNumberGenerator
        - private Room[] rooms
        - private ArrayList<Adventurer> adventurers
        - private ArrayList<Creature> creatures
        + public Maze()
        + public void setRooms(Room[] rooms)
        + public void setAdventurers(ArrayList<Adventurer> adventurers)
        + public void setCreatures(ArrayList<Creature> creatures)
        + public Room getRoom(int num)
        + public int getSize()
        + public String toString()
        + public void turn()
        + public Room getEntityRoom(Entity entity)
        + public boolean checkAllCreaturesDead()
        + public boolean checkAllAdventurersDead()
    }
    class Room {
        - private ArrayList<Room> connectedRooms
        - private ArrayList<Entity> occupants
        - private ArrayList<Food> loot
        - private String name
        + public Room()
        + public Creature getHealthiestCreature()
        + public Demon getHealthiestDemon()
        + public Adventurer getHealthiestAdventurer()
        + public boolean isThereFood()
        + public void addFood(Food food)
        + public Food takeFood()
        + public ArrayList<Entity> getOccupants()
        + public void addOccupant(Entity entity)
        + public void removeOccupant(Entity entity)
        + public void addRoomConnection(Room room)
        + public ArrayList<Room> getConnectedRooms()
        + public String getName()
        + public void setName(String name)
        + public String toString()
    }
    Entity <|-- Creature
    Entity <|-- Adventurer
    Adventurer <|-- Knight
    Adventurer <|-- Coward
    Adventurer <|-- Glutton
    Creature <|-- Demon
    
    Arcane *-- Maze
    Maze *-- Room
    Room *-- Food
    
    Maze o-- Adventurer
    Maze o-- Creature
    Room o-- Entity
```

Sequence Diagram:
```mermaid
sequenceDiagram
    participant GameConfigurator
    participant MazeFactory
    participant OtherFactories
    participant AudibleArcaneObserver
    participant GameLayoutObserver
    participant Arcane
    participant EventBus
    participant Maze
    participant Room
    participant Adventurer
    participant Creature
    
    GameConfigurator->>MazeFactory: createMaze
    MazeFactory->>OtherFactories: createEntitiesAndFood
    OtherFactories-->>MazeFactory: return
    MazeFactory-->>Maze: create
    MazeFactory-->>GameConfigurator: return
    GameConfigurator-->>Arcane: create
    Arcane->>EventBus: create
    Arcane->>EventBus: attach
    GameConfigurator-->>GameLayoutObserver: create
    GameLayoutObserver->>Arcane: attach
    GameConfigurator-->>AudibleArcaneObserver: create
    AudibleArcaneObserver->>Arcane: attach
    
    Arcane->>EventBus: notifyObservers(GameStart)
    EventBus->>Arcane: update
    Arcane->>AudibleArcaneObserver: update
    Arcane->>GameLayoutObserver: update
    loop While game isn't over
        Arcane->>Maze: turn
        loop Every Adventurer
            Maze->>Adventurer: turn
            Adventurer->>Room: getHealthiestCreature
            Room-->>Adventurer: return
            alt Creature exists
            Adventurer->>Creature: rollDice
            Creature-->>Adventurer: return
                alt Adventurer rolls higher
                    Adventurer->>Creature: takeDamage
                else
                    Adventurer->>Adventurer: takeDamage
                end
                alt Loser died
                    Adventurer->>EventBus: notifyObservers(Death)
                    EventBus->>Arcane: update
                    Arcane->>AudibleArcaneObserver: update
                    Arcane->>GameLayoutObserver: update
                end
                Adventurer->>EventBus: notifyObservers(FightOutcome)
                EventBus->>Arcane: update
                Arcane->>AudibleArcaneObserver: update
                Arcane->>GameLayoutObserver: update
            else
            Adventurer->>Room: isThereFood
            Room-->>Adventurer: return
            alt Food exists
                Adventurer->>Room: takeFood
                Room-->>Adventurer: return
                Adventurer->>EventBus: notifyObservers(AteFood)
                EventBus->>Arcane: update
                Arcane->>AudibleArcaneObserver: update
                Arcane->>GameLayoutObserver: update
            else
                Adventurer->>Room: getConnectedRooms
                Room-->>Adventurer: 
                Adventurer->>Room: removeOccupant
                Adventurer->>Room: addOccupant
            end
        end
        Arcane->>EventBus: notifyObservers(TurnEnded)
        EventBus->>Arcane: update
        Arcane->>AudibleArcaneObserver: update
        Arcane->>GameLayoutObserver: update
    end
    Arcane->>EventBus: notifyObservers(GameOver)
    EventBus->>Arcane: update
    Arcane->>AudibleArcaneObserver: update
    Arcane->>GameLayoutObserver: update
end
```
