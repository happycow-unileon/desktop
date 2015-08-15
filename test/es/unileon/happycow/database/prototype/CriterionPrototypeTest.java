/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.unileon.happycow.database.prototype;

import es.unileon.happycow.handler.Category;
import es.unileon.happycow.handler.IdCategory;
import es.unileon.happycow.model.composite.Criterion;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dorian
 */
public class CriterionPrototypeTest {
    private CriterionPrototype prototype;
    private Criterion one,two,three;
    
    @Before
    public void setUp() {
        prototype=new CriterionPrototype();
        
        one=new Criterion("nameone", new IdCategory(Category.FOOD), "description", "help");
        two=new Criterion("nametwo", new IdCategory(Category.BEHAVIOUR), "description", "help");
        three=new Criterion("namethree", new IdCategory(Category.HEALTH), "description", "help");
        
        prototype.add(one,two,three);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of add method, of class CriterionPrototype.
     */
    @Test
    public void testAdd() {
        assertEquals(3, prototype.getList().size());
        prototype.add(new Criterion("name4", new IdCategory(Category.HEALTH), "description", "help"));
        assertEquals(4, prototype.getList().size());
    }

    /**
     * Test of remove method, of class CriterionPrototype.
     */
    @Test
    public void testRemove() {
        assertEquals(3, prototype.getList().size());
        prototype.remove(two);
        assertEquals(2, prototype.getList().size());
        prototype.remove(two);
        assertEquals(2, prototype.getList().size());
    }

    /**
     * Test of getList method, of class CriterionPrototype.
     */
    @Test
    public void testGetList() {
        List<Criterion> list=prototype.getList();
        assertEquals(3, list.size());
        assertTrue(list.contains(one));
        assertTrue(list.contains(two));
        assertTrue(list.contains(three));
    }

    /**
     * Test of clone method, of class CriterionPrototype.
     */
    @Test
    public void testClone() {
        assertEquals(three.getName(), prototype.clone("namethree").getName());
        assertEquals(two.getName(), prototype.clone("nametwo").getName());
        assertEquals(one.getName(), prototype.clone("nameone").getName());
        
    }
    
    @Test
    public void testCloneBad() {
        assertNull(prototype.clone("name"));
        
    }
    
}
