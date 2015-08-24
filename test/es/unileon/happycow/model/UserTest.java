/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.unileon.happycow.model;

import es.unileon.happycow.handler.IdHandler;
import es.unileon.happycow.handler.IdUser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dorian
 */
public class UserTest {
    private User veterinary;
    private User admin;
    
    private IdHandler idVeterinary;
    private IdHandler idAdmin;

    @Before
    public void setUp() {
        idVeterinary=new IdUser("Dorian");
        idAdmin=new IdUser("Marta");
        
        veterinary=new User(idVeterinary, "password", Rol.VETERINARIO);
        admin=new User(idAdmin, "admin", Rol.ADMINISTRADOR);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getName method, of class User.
     */
    @Test
    public void testGetName() {
        assertEquals(idVeterinary.toString(), veterinary.getName());
        assertEquals(idAdmin.toString(), admin.getName());
    }

    /**
     * Test of getPassword method, of class User.
     */
    @Test
    public void testGetPassword() {
        assertEquals("\_\M\�\;\Z\�\e\�\\�\'\޸\�\ϙ", veterinary.getPassword());
        assertEquals("\!\#\/\)\z\W\�\�\C\�\J\\J\�\\�", admin.getPassword());
        assertEquals("password", new User("name", "password", Rol.VETERINARIO).getPassword());
        assertFalse("password".compareTo(
                new User("name", "password", Rol.VETERINARIO, false)
                        .getPassword())==0);
    }

    /**
     * Test of getRol method, of class User.
     */
    @Test
    public void testGetRol() {
        assertEquals(Rol.VETERINARIO, veterinary.getRol());
        assertEquals(Rol.ADMINISTRADOR, admin.getRol());
    }

    /**
     * Test of getStringRol method, of class User.
     */
    @Test
    public void testGetStringRol() {
        assertEquals("VETERINARIO", veterinary.getStringRol());
        assertEquals("ADMINISTRADOR", admin.getStringRol());
    }

    /**
     * Test of getId method, of class User.
     */
    @Test
    public void testGetId() {
        assertTrue(idVeterinary.compareTo(veterinary.getId())==0);
        assertTrue(idAdmin.compareTo(admin.getId())==0);
        assertFalse(idAdmin.compareTo(veterinary.getId())==0);
    }

    /**
     * Test of toString method, of class User.
     */
    @Test
    public void testToString() {
        assertEquals("Dorian \_\M\�\;\Z\�\e\�\\�\'\޸\�\ϙ VETERINARIO", veterinary.toString());
        assertEquals("Marta \!\#\/\)\z\W\�\�\C\�\J\\J\�\\� ADMINISTRADOR", admin.toString());
    }
    
}
