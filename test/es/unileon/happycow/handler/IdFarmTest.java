/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.unileon.happycow.handler;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dorian
 */
public class IdFarmTest {
    private IdFarm firstId;
    private IdFarm secondId;
    private IdUser userId;
    
    @Before
    public void setUp() {
        userId=new IdUser("user");
        firstId=new IdFarm(5);
        secondId=new IdFarm(3);
    }
    
    @Test
    public void testCreate(){
        IdFarm other=new IdFarm(2);
        assertEquals(2, other.getIdFarm());
        
        other=new IdFarm(5);
        assertEquals(5, other.getIdFarm());
    }
    
    @Test
    public void testCreateWithIdHandler(){
        IdFarm other=new IdFarm(firstId);
        assertEquals(0, firstId.compareTo(other));
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testFailCreateWithWrongIdHandler(){
        IdGeneric other=new IdGeneric("other");
        IdFarm failed=new IdFarm(other);
    }
    

    /**
     * Test of getIdFarm method, of class IdFarm.
     */
    @Test
    public void testGetIdFarm() {
        assertEquals(5, firstId.getIdFarm());
        assertEquals(3, secondId.getIdFarm());
    }


    /**
     * Test of toString method, of class IdFarm.
     */
    @Test
    public void testToString() {
        assertEquals("Farm-5", firstId.toString());
        assertEquals("Farm-3", secondId.toString());
    }
    
    @Test
    public void testGetValue(){
        assertEquals("5", firstId.getValue());
        assertEquals("3", secondId.getValue());
    }

    /**
     * Test of compareTo method, of class IdFarm.
     */
    @Test
    public void testCompareTo() {
        assertEquals(0, firstId.compareTo(firstId));
        assertFalse(firstId.compareTo(secondId)==0);
        
        firstId=new IdFarm(secondId.getIdFarm());
        assertTrue(firstId.compareTo(secondId)==0);
    }

    /**
     * Test of valor method, of class IdFarm.
     */
    @Test
    public void testValor() {
//        System.out.println("valor");
//        IdFarm instance = null;
//        int expResult = 0;
//        int result = instance.valor();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
    
}
