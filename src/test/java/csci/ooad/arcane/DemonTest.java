package csci.ooad.arcane;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DemonTest {
    static String[] possibleNames = {
            "Cobblebeast", "Dimcreeper", "Ooze", "Unruly Armor",
            "Cavern Crab", "Snapdragon", "Wyvern"
    };
    @Test
    public void testConstructor() {
        // Default Constructor
        Demon testDemon = CreatureFactory.createDemon();
        assertNotNull(testDemon, "Demon should be defined after creation");
    }
}
