package es.unileon.happycow.handler;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dorian
 */
public class IdCowTest {
    private IdCow firstId;
    private IdCow secondId;
    
    @Before
    public void setUp() {
        firstId=new IdCow(5);
        secondId=new IdCow(3);
    }
    
    @Test
    public void testCreate(){
        IdCow other=new IdCow(2);
        assertEquals(2, other.getId());
    }
    
    @Test
    public void testCreateWithIdHandler(){
        IdCow other=new IdCow(firstId);
        assertEquals(0, firstId.compareTo(other));
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testFailCreateWithWrongIdHandler(){
        IdGeneric other=new IdGeneric("other");
        IdCow failed=new IdCow(other);
    }

    /**
     * Test of getId method, of class IdCow.
     */
    @Test
    public void testGetId() {
        assertEquals(5, firstId.getId());
        assertEquals(3, secondId.getId());
    }

    /**
     * Test of toString method, of class IdCow.
     */
    @Test
    public void testToString() {
        assertEquals("5", firstId.toString());
        assertEquals("3", secondId.toString());
    }

    /**
     * Test of compareTo method, of class IdCow.
     */
    @Test
    public void testCompareTo() {
        assertEquals(0, firstId.compareTo(firstId));
        assertFalse(firstId.compareTo(secondId)==0);
    }

    /**
     * Test of valor method, of class IdCow.
     */
    @Test
    public void testValor() {
//        System.out.println("valor");
//        IdCow instance = null;
//        int expResult = 0;
//        int result = instance.valor();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
    
}
