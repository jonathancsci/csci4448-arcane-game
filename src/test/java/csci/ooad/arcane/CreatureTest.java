package csci.ooad.arcane;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreatureTest {
    @Test
    public void testDefaultConstructor() {
        String[] possibleNames = {"Cobblebeast","Dimcreeper","Ooze","Unruly Armor","Cavern Crab","Snapdragon","Wyvern"};
        Creature testCreature = new Creature();
        String creatureName = testCreature.getName();
        Boolean isNameValid = Arrays.asList(possibleNames).contains(creatureName);
        assertTrue(isNameValid, "Creature should have one of the predefined names");
    }

    @Test
    public void toStringTest() {
        Creature testCreature = new Creature("Ogre", 5);
        assertEquals("Creature Ogre(health: 5)", testCreature.toString());
        testCreature.setHealth(0);
        assertEquals("Creature Ogre(health: 0); DEAD", testCreature.toString());
    }

}
