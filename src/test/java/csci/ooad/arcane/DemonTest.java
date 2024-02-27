package csci.ooad.arcane;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DemonTest {
    static String[] possibleNames = {
            "Cobblebeast", "Dimcreeper", "Ooze", "Unruly Armor",
            "Cavern Crab", "Snapdragon", "Wyvern"
    };
    @Test
    public void testConstructor() {
        // Default Constructor
        Demon testDemon = new Demon();
        String demonName = testDemon.getName();
        Boolean isNameValid = Arrays.asList(possibleNames).contains(demonName);
        assertTrue(isNameValid, "Demon should have one of the predefined names");

        // Factory Constructor
        Demon testDemon2 = new Demon("Snapdragon", 15);
        String demonName2 = testDemon2.getName();
        Boolean isNameValid2 = Arrays.asList(possibleNames).contains(demonName2);
        assertTrue(isNameValid2, "Demon should have one of the predefined names");
    }
}
