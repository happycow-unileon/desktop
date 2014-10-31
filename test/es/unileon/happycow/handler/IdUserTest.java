package es.unileon.happycow.handler;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dorian
 */
public class IdUserTest {
    private IdUser firstId;
    private IdUser secondId;
   
    @Before
    public void setUp() {
        firstId=new IdUser("oneName");
        secondId=new IdUser("another name");
    }
    
    @Test
    public void testCreate(){
        IdUser one=new IdUser("another");
        assertEquals("another", one.getUser());
    }
    
    @Test
    public void testCreateWithIdHandler(){
        IdUser one=new IdUser(firstId);
        assertEquals(firstId.getUser(), one.getUser());
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testFailCreateWithWrongIdHandler(){
        IdGeneric other=new IdGeneric("other");
        IdUser failed=new IdUser(other);
    }
    

    /**
     * Test of getUser method, of class IdUser.
     */
    @Test
    public void testGetUser() {
        assertEquals("oneName", firstId.getUser());
        assertEquals("another name", secondId.getUser());
    }

    /**
     * Test of toString method, of class IdUser.
     */
    @Test
    public void testToString() {
        assertEquals("oneName", firstId.toString());
        assertEquals("another name", secondId.toString());
    }

    /**
     * Test of compareTo method, of class IdUser.
     */
    @Test
    public void testCompareTo() {
        assertEquals(0, firstId.compareTo(firstId));
        assertFalse(firstId.compareTo(secondId)==0);
    }

    /**
     * Test of valor method, of class IdUser.
     */
    @Test
    public void testValor() {
//        System.out.println("valor");
//        IdUser instance = null;
//        int expResult = 0;
//        int result = instance.valor();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
    
}
