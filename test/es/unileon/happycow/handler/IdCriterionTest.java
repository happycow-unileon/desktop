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
public class IdCriterionTest {
    
    private IdCriterion firstId;
    private IdCriterion secondId;
   
    @Before
    public void setUp() {
        firstId=new IdCriterion("oneName");
        secondId=new IdCriterion("another name");
    }
    
    @Test
    public void testCreate(){
        IdCriterion one=new IdCriterion("another");
        assertEquals("another", one.getName());
    }
    
    @Test
    public void testCreateWithIdHandler(){
        IdCriterion one=new IdCriterion(firstId);
        assertEquals(firstId.getName(), one.getName());
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testFailCreateWithWrongIdHandler(){
        IdGeneric other=new IdGeneric("other");
        IdCriterion failed=new IdCriterion(other);
    }
    

    /**
     * Test of getUser method, of class IdCriterion.
     */
    @Test
    public void testGetUser() {
        assertEquals("oneName", firstId.getName());
        assertEquals("another name", secondId.getName());
    }

    /**
     * Test of toString method, of class IdCriterion.
     */
    @Test
    public void testToString() {
        assertEquals("Criterion-oneName", firstId.toString());
        assertEquals("Criterion-another name", secondId.toString());
    }
    
    @Test
    public void testGetValue(){
        assertEquals("oneName", firstId.getValue());
        assertEquals("another name", secondId.getValue());
    }

    /**
     * Test of compareTo method, of class IdCriterion.
     */
    @Test
    public void testCompareTo() {
        assertEquals(0, firstId.compareTo(firstId));
        assertFalse(firstId.compareTo(secondId)==0);
    }

    /**
     * Test of valor method, of class IdCriterion.
     */
    @Test
    public void testValor() {
//        System.out.println("valor");
//        IdCriterion instance = null;
//        int expResult = 0;
//        int result = instance.valor();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
    
}
