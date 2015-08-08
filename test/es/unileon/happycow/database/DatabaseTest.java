/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.unileon.happycow.database;

import es.unileon.happycow.database.concreteDatabase.Oracle;
import es.unileon.happycow.database.concreteDatabase.SQLite;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dorian
 */
public class DatabaseTest {
    
    public DatabaseTest() {
    }
    
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
        Database.deleteInstance();
    }

    /**
     * Test of setType method, of class Database.
     */
    @Test
    public void testSetType() {
        TypeDatabase typeData = TypeDatabase.SQLITE;
        Database.setType(typeData);
        assertTrue(Database.getInstance() instanceof SQLite);
                
        Database.deleteInstance();
        typeData = TypeDatabase.ORACLE;
        Database.setType(typeData);
        assertTrue(Database.getInstance() instanceof Oracle);
        
    }

    /**
     * Test of getInstance method, of class Database.
     */
    @Test
    public void testGetInstance() {
        DataBaseOperations result = Database.getInstance();
        assertNotNull(result);
    }
    
}
