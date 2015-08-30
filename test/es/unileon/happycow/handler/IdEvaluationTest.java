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
public class IdEvaluationTest {
    
    private IdEvaluation firstId;
    private IdEvaluation secondId;
    
    @Before
    public void setUp() {
        firstId=new IdEvaluation(5);
        secondId=new IdEvaluation(3);
    }
    
    @Test
    public void testCreate(){
        IdEvaluation other=new IdEvaluation(2);
        assertEquals(2, other.getIdentifier());
    }
    
    @Test
    public void testCreateWithIdHandler(){
        IdEvaluation other=new IdEvaluation(firstId);
        assertEquals(0, firstId.compareTo(other));
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testFailCreateWithWrongIdHandler(){
        IdGeneric other=new IdGeneric("other");
        IdEvaluation failed=new IdEvaluation(other);
    }

    /**
     * Test of getId method, of class IdEvaluation.
     */
    @Test
    public void testGetIdentifier() {
        assertEquals(5, firstId.getIdentifier());
        assertEquals(3, secondId.getIdentifier());
    }

    /**
     * Test of toString method, of class IdEvaluation.
     */
    @Test
    public void testToString() {
        assertEquals("Evaluation-5", firstId.toString());
        assertEquals("Evaluation-3", secondId.toString());
    }
    
    @Test
    public void testGetValue(){
        assertEquals("5", firstId.getValue());
        assertEquals("3", secondId.getValue());
    }

    /**
     * Test of compareTo method, of class IdEvaluation.
     */
    @Test
    public void testCompareTo() {
        assertEquals(0, firstId.compareTo(firstId));
        assertFalse(firstId.compareTo(secondId)==0);
    }

    /**
     * Test of valor method, of class IdEvaluation.
     */
    @Test
    public void testValor() {
//        System.out.println("valor");
//        IdEvaluation instance = null;
//        int expResult = 0;
//        int result = instance.valor();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
    
}
