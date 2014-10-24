package es.unileon.happycow.handler;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dorian
 */
public class IdValorationTest {
    private IdValoration firstId;
    private IdValoration secondId;
    
    @Before
    public void setUp() {
        firstId=new IdValoration(5);
        secondId=new IdValoration(3);
    }
    
    @Test
    public void testCreate(){
        IdValoration other=new IdValoration(2);
        assertEquals(2, other.getId());
    }
    
    @Test
    public void testCreateWithIdHandler(){
        IdValoration other=new IdValoration(firstId);
        assertEquals(0, firstId.compareTo(other));
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testFailCreateWithWrongIdHandler(){
        IdGeneric other=new IdGeneric("other");
        IdValoration failed=new IdValoration(other);
    }

    /**
     * Test of getId method, of class IdValoration.
     */
    @Test
    public void testGetId() {
        assertEquals(5, firstId.getId());
        assertEquals(3, secondId.getId());
    }

    /**
     * Test of toString method, of class IdValoration.
     */
    @Test
    public void testToString() {
        assertEquals("5", firstId.toString());
        assertEquals("3", secondId.toString());
    }

    /**
     * Test of compareTo method, of class IdValoration.
     */
    @Test
    public void testCompareTo() {
        assertEquals(0, firstId.compareTo(firstId));
        assertFalse(firstId.compareTo(secondId)==0);
    }

    /**
     * Test of valor method, of class IdValoration.
     */
    @Test
    public void testValor() {
//        System.out.println("valor");
//        IdValoration instance = null;
//        int expResult = 0;
//        int result = instance.valor();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
    
}
