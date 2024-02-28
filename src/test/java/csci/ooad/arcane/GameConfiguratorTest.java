package csci.ooad.arcane;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameConfiguratorTest {
    @Test
    public void runGameTest() {
        String[] args = {
                "--numberOfAdventurers", "2",
                "--numberOfCreatures", "2",
                "--numberOfFoodItems", "6",
                "--numberOfRooms", "5",
        };
        GameConfigurator.main(args);
    }
}
