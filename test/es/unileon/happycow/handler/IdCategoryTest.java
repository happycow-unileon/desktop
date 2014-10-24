package es.unileon.happycow.handler;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dorian
 */
public class IdCategoryTest {
    private IdCategory food;
    private IdCategory health;
    private IdCategory house;
    private IdCategory behaviour;
    
    @Before
    public void setUp() {
        food=new IdCategory(Category.FOOD);
        health=new IdCategory(Category.HEALTH);
        house=new IdCategory(Category.HOUSE);
        behaviour=new IdCategory(Category.BEHAVIOUR);
    }
    
    @Test
    public void createString(){
        IdCategory one=new IdCategory("Alimentación");
        assertEquals(0, one.compareTo(food));
    }
    
    @Test
    public void createCategory(){
        IdCategory one=new IdCategory(Category.FOOD);
        assertEquals(0, one.compareTo(food));
    }
    
    @Test
    public void createIdHandler(){
        IdCategory one=new IdCategory(food);
        assertEquals(0, one.compareTo(food));
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testCreateWrongHandler(){
        IdGeneric another=new IdGeneric("another");
        new IdCategory(another);
    }
    

    /**
     * Test of getCategory method, of class IdCategory.
     */
    @Test
    public void testGetCategory() {
        assertEquals(Category.FOOD, food.getCategory());
        assertEquals(Category.HOUSE, house.getCategory());
        assertEquals(Category.HEALTH, health.getCategory());
        assertEquals(Category.BEHAVIOUR, behaviour.getCategory());
        
    }

    /**
     * Test of toString method, of class IdCategory.
     */
    @Test
    public void testToString() {
        assertEquals("Alimentación", food.toString());
        assertEquals("Salud", health.toString());
        assertEquals("Refugio", house.toString());
        assertEquals("Comportamiento", behaviour.toString());
    }

    /**
     * Test of compareTo method, of class IdCategory.
     */
    @Test
    public void testCompareTo() {
        assertEquals(0, food.compareTo(food));
        assertFalse(food.compareTo(house)==0);
    }

    /**
     * Test of valor method, of class IdCategory.
     */
    @Test
    public void testValor() {
//        System.out.println("valor");
//        IdCategory instance = null;
//        int expResult = 0;
//        int result = instance.valor();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
    
}
