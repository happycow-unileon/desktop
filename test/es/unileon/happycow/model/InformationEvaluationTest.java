/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.unileon.happycow.model;

import es.unileon.happycow.handler.IdEvaluation;
import es.unileon.happycow.handler.IdFarm;
import es.unileon.happycow.handler.IdHandler;
import es.unileon.happycow.handler.IdUser;
import java.sql.Date;
import java.util.GregorianCalendar;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dorian
 */
public class InformationEvaluationTest {
    
    private InformationEvaluation info;
    private IdHandler evaluation;
    private IdHandler user;
    private IdHandler farm;
    
    @Before
    public void setUp() {
        user=new IdUser("marta");
        farm=new IdFarm(user, 3);
        evaluation=new IdEvaluation(20);
        info=new InformationEvaluation(evaluation, farm, user, 4.3f, 2f, 2.1f, 2.2f, 2.3f, 
                new Date(new GregorianCalendar(2000, 5, 22).getTimeInMillis()), 25);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getNumberCows method, of class InformationEvaluation.
     */
    @Test
    public void testGetNumberCows() {
        assertEquals(25, info.getNumberCows());
    }

    /**
     * Test of getIdEvaluation method, of class InformationEvaluation.
     */
    @Test
    public void testGetIdEvaluation() {
        assertTrue(evaluation.compareTo(info.getIdEvaluation())==0);
    }

    /**
     * Test of getIdFarm method, of class InformationEvaluation.
     */
    @Test
    public void testGetIdFarm() {
        assertTrue(farm.compareTo(info.getIdFarm())==0);
    }

    /**
     * Test of getNota method, of class InformationEvaluation.
     */
    @Test
    public void testGetNota() {
        assertEquals(4.3f, info.getNota(), 0.001);
    }

    /**
     * Test of getIdUser method, of class InformationEvaluation.
     */
    @Test
    public void testGetIdUser() {
        assertTrue(user.compareTo(info.getIdUser())==0);
    }

    /**
     * Test of setNota method, of class InformationEvaluation.
     */
    @Test
    public void testSetNota() {
        assertEquals(4.3, info.getNota(), 0.001);
        
        info.setNota(5);
        
        assertEquals(5, info.getNota(), 0.001);
    }

    /**
     * Test of getAlimentacion method, of class InformationEvaluation.
     */
    @Test
    public void testGetAlimentacion() {
        assertEquals(2.0, info.getAlimentacion(), 0.001);
    }

    /**
     * Test of setAlimentacion method, of class InformationEvaluation.
     */
    @Test
    public void testSetAlimentacion() {
        assertEquals(2.0, info.getAlimentacion(), 0.001);
        
        info.setAlimentacion(4.55f);
        
        assertEquals(4.55, info.getAlimentacion(), 0.001);
    }

    /**
     * Test of getSalud method, of class InformationEvaluation.
     */
    @Test
    public void testGetSalud() {
        assertEquals(2.1f, info.getSalud(), 0.001);
    }

    /**
     * Test of setSalud method, of class InformationEvaluation.
     */
    @Test
    public void testSetSalud() {
        assertEquals(2.1f, info.getSalud(), 0.001);
        
        info.setSalud(3.2f);
        
        assertEquals(3.2f, info.getSalud(), 0.001);
    }

    /**
     * Test of getComfort method, of class InformationEvaluation.
     */
    @Test
    public void testGetComfort() {
        assertEquals(2.2f, info.getComfort(), 0.001);
    }

    /**
     * Test of setComfort method, of class InformationEvaluation.
     */
    @Test
    public void testSetComfort() {
        assertEquals(2.2f, info.getComfort(), 0.001);
        
        info.setComfort(1f);
        
        assertEquals(1f, info.getComfort(), 0.001);
    }

    /**
     * Test of getComportamiento method, of class InformationEvaluation.
     */
    @Test
    public void testGetComportamiento() {
        assertEquals(2.3, info.getComportamiento(), 0.001);
    }

    /**
     * Test of setComportamiento method, of class InformationEvaluation.
     */
    @Test
    public void testSetComportamiento() {
        assertEquals(2.3, info.getComportamiento(), 0.001);
        
        info.setComportamiento(2.5f);
        
        assertEquals(2.5, info.getComportamiento(), 0.001);
    }

    /**
     * Test of getFecha method, of class InformationEvaluation.
     */
    @Test
    public void testGetFecha() {
        Date date=new Date(new GregorianCalendar(2000, 5, 22).getTimeInMillis());
        assertEquals(date, info.getFecha());
    }

    /**
     * Test of setFecha method, of class InformationEvaluation.
     */
    @Test
    public void testSetFecha() {
        Date date=new Date(new GregorianCalendar(2000, 5, 22).getTimeInMillis());
        assertEquals(date, info.getFecha());
        
        
        date=new Date(new GregorianCalendar(2000, 5, 25).getTimeInMillis());
        info.setFecha(date);
        assertEquals(date, info.getFecha());
    }
    
}
