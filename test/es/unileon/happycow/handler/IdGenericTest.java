/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.unileon.happycow.handler;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dorian
 */
public class IdGenericTest {
    private IdGeneric firstId;
    private IdGeneric secondId;
   
    @Before
    public void setUp() {
        firstId=new IdGeneric("oneName");
        secondId=new IdGeneric("another name");
    }
    
    @Test
    public void testCreate(){
        IdGeneric one=new IdGeneric("another");
        assertEquals("another", one.toString());
    }
    
    @Test
    public void testCreateWithIdHandler(){
        IdGeneric one=new IdGeneric(firstId);
        assertEquals(firstId.toString(), one.toString());
    }

    /**
     * Test of toString method, of class IdGeneric.
     */
    @Test
    public void testToString() {
        assertEquals("oneName", firstId.toString());
        assertEquals("another name", secondId.toString());
    }

    /**
     * Test of compareTo method, of class IdGeneric.
     */
    @Test
    public void testCompareTo() {
        assertEquals(0, firstId.compareTo(firstId));
        assertFalse(firstId.compareTo(secondId)==0);
    }

    /**
     * Test of valor method, of class IdGeneric.
     */
    @Test
    public void testValor() {
//        System.out.println("valor");
//        IdGeneric instance = null;
//        int expResult = 0;
//        int result = instance.valor();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
}
