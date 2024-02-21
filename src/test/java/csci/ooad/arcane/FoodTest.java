package csci.ooad.arcane;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FoodTest {
    @Test
    public void testFood() {
        Food food = new Food();
        assertEquals(1,food.getHealthRestored());
        assertEquals(food.getName(),food.toString());
    }
    @Test
    public void testFoodNaming() {
        Food food = new Food("Carrot",2);
        assertEquals(2,food.getHealthRestored());
        assertEquals("Carrot",food.getName());
    }

}
