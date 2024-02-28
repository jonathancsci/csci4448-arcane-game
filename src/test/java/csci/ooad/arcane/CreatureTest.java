package csci.ooad.arcane;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreatureTest {
    @Test
    public void toStringTest() {
        Creature testCreature = new Creature("Ogre", 5);
        assertEquals("Creature Ogre(health: 5.0)", testCreature.toString());
        testCreature.setHealth(0);
        assertEquals("Creature Ogre(health: 0.0); DEAD", testCreature.toString());
    }

}
