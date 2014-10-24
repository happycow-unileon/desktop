package es.unileon.happycow.handler;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dorian
 */
public class CategoryTest {
    Category food;
    Category health;
    Category house;
    Category behaviour;
    
    @Before
    public void setUp() {
        food=Category.FOOD;
        health=Category.HEALTH;
        house=Category.HOUSE;
        behaviour=Category.BEHAVIOUR;
    }

    /**
     * Test of getName method, of class Category.
     */
    @Test
    public void testGetName() {
        assertEquals("Alimentación", Category.getName(food));
        assertEquals("Salud", Category.getName(health));
        assertEquals("Refugio", Category.getName(house));
        assertEquals("Comportamiento", Category.getName(behaviour));
    }

    /**
     * Test of getEnum method, of class Category.
     */
    @Test
    public void testGetEnum_String() {
        assertEquals(Category.FOOD, Category.getEnum(Category.getName(food)));
        assertEquals(Category.HOUSE, Category.getEnum(Category.getName(house)));
        assertEquals(Category.HEALTH, Category.getEnum(Category.getName(health)));
        assertEquals(Category.BEHAVIOUR, Category.getEnum(Category.getName(behaviour)));
    }

    /**
     * Test of getEnum method, of class Category.
     */
    @Test
    public void testGetEnum_int() {
        assertEquals(Category.FOOD, Category.getEnum(Category.FOOD.ordinal()));
        assertEquals(Category.HOUSE, Category.getEnum(Category.HOUSE.ordinal()));
        assertEquals(Category.HEALTH, Category.getEnum(Category.HEALTH.ordinal()));
        assertEquals(Category.BEHAVIOUR, Category.getEnum(Category.BEHAVIOUR.ordinal()));
    }

    /**
     * Test of getArrayString method, of class Category.
     */
    @Test
    public void testGetArrayString() {
        String result[]=Category.getArrayString();
        for (Category category : Category.values()) {
            assertEquals(Category.getName(category), result[category.ordinal()]);
        }
    }
    
}
