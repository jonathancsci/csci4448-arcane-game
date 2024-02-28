package csci.ooad.arcane;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameConfiguratorTest {
    @Test
    public void runGameTest() {
        String[] args = {
                "--numberOfAdventurers", "6",
                "--numberOfCreatures", "8",
                "--numberOfFoodItems", "24",
                "--numberOfRooms", "12",
        };
        GameConfigurator.main(args);
    }
}
