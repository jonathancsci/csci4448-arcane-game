package csci.ooad.arcane;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameConfiguratorTest {
    @Test
    public void runGameTest() {
        String[] args = {
                "--numberOfAdventurers", "3",
                "--numberOfCreatures", "3",
                "--numberOfFoodItems", "10",
                "--numberOfRooms", "7",
        };
        GameConfigurator.main(args);
    }
}
