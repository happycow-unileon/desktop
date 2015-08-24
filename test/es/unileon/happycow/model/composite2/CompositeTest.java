package es.unileon.happycow.model.composite2;

import es.unileon.happycow.model.composite.EvaluationCategory;
import es.unileon.happycow.model.composite.Component;
import es.unileon.happycow.model.composite.Criterion;
import es.unileon.happycow.model.composite.CompositeException;
import es.unileon.happycow.model.composite.Evaluation;
import es.unileon.happycow.model.composite.Valoration;
import es.unileon.happycow.handler.Category;
import es.unileon.happycow.handler.IdCategory;
import es.unileon.happycow.handler.IdGeneric;
import es.unileon.happycow.handler.IdHandler;
import es.unileon.happycow.handler.IdValoration;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dorian
 */
public class CompositeTest {

    private Component evaluation, categoryHealth, categoryFood, criterion1, criterion2;
    private Component valoration1, valoration2, valoration3, valoration4, valoration5, valoration6;

    @Before
    public void setUp() throws CompositeException {
        IdHandler id ;
        evaluation = new Evaluation(1234, null);
        id = new IdCategory(Category.HEALTH);
        categoryHealth = new EvaluationCategory(id);

        id = new IdCategory(Category.FOOD);
        categoryFood = new EvaluationCategory(id);

        criterion1 = new Criterion("namecriterion", id, "description", "help");
        criterion2 = new Criterion("namecriterion2", id, "description", "help");

        // initial leaves
        id = new IdValoration(0);
        valoration1 = new Valoration(id, 4.0f);
        id = new IdValoration(1);
        valoration2 = new Valoration(id, 4.2f);
        id = new IdValoration(2);
        valoration3 = new Valoration(id, 4.3f);
        id = new IdValoration(3);
        valoration4 = new Valoration(id, 4.4f);
        id = new IdValoration(4);
        valoration5 = new Valoration(id, 4.5f);
        id = new IdValoration(5);
        valoration6 = new Valoration(id, 4.6f);

        // building the tree
        evaluation.add(categoryHealth);
        evaluation.add(categoryFood);

        categoryFood.add(criterion1);
        categoryHealth.add(criterion2);

        // adding the leaves
        criterion1.add(valoration1);
        criterion1.add(valoration2);
        criterion1.add(valoration3);
        criterion1.add(valoration4);
        criterion1.add(valoration5);
        criterion1.add(valoration6);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetIdOK() {
        assertNotSame(this.evaluation.getId().toString(), new IdGeneric("2").toString());
    }

    /**
     * Test of isLeaf method, of class Composite.
     */
//    @Test
//    public void testIsLeaf() {
//        assertTrue(valoration1.isLeaf());
//        assertFalse(criterion1.isLeaf());
//        assertFalse(evaluation.isLeaf());
//        assertFalse(categoryFood.isLeaf());
//    }
    @Test
    public void testAddOK() throws CompositeException {
        Component category = new EvaluationCategory(new IdCategory(Category.HOUSE));
        assertEquals(this.evaluation.size(), 2);
        this.evaluation.add(category);
        assertEquals(this.evaluation.size(), 3);
        assertEquals(this.evaluation.search(category.getId()), category);
    }

    @Test(expected = CompositeException.class)
    public void testAddNotOK() throws CompositeException {
        this.valoration1.add(criterion1);
    }

    @Test
    public void testRemoveOK() {
        assertEquals(2, this.evaluation.size());
        assertTrue(this.evaluation.remove(this.categoryHealth));
        assertEquals(this.evaluation.size(), 1);
        assertNull(this.evaluation.search(this.categoryHealth.getId()));
    }

    @Test
    public void testRemoveNotOK() {
        Component category = new EvaluationCategory(new IdCategory(Category.HOUSE));
        assertFalse(this.evaluation.remove(category));
    }

    /**
     * Test of delete method, of class Composite.
     */
    @Test
    public void testDelete() {
        assertEquals(2, this.evaluation.size());
        assertTrue(this.evaluation.remove(this.categoryHealth));
        assertEquals(this.evaluation.size(), 1);
        assertNull(this.evaluation.search(this.categoryHealth.getId()));

        assertEquals(6, this.criterion1.size());
        assertTrue(this.evaluation.remove(this.valoration2));
        assertEquals(5, this.criterion1.size());

        Component category = new EvaluationCategory(new IdCategory(Category.HOUSE));
        assertFalse(this.evaluation.remove(category));
    }

    /**
     * Test of show method, of class Composite.
     */
    @Test
    public void testToString() {
        assertEquals("Evaluación: 1234\n"
                + "	Categoria: Salud\n"
                + "		Criterio: namecriterion2\n"
                + "	Categoria: Alimentación\n"
                + "		Criterio: namecriterion\n"
                + "			Valoration: 0\n"
                + "			Valoration: 1\n"
                + "			Valoration: 2\n"
                + "			Valoration: 3\n"
                + "			Valoration: 4\n"
                + "			Valoration: 5\n", evaluation.toString());
    }

    @Test
    public void testSize() throws CompositeException {
        // initial situation
        assertEquals(this.evaluation.size(), 2);
        assertEquals(this.categoryFood.size(), 1);
        assertEquals(this.criterion1.size(), 6);
        assertEquals(this.criterion2.size(), 0);
        // adding a citizen to the root of the tree
        this.evaluation.add(new EvaluationCategory(new IdCategory(Category.HOUSE)));
        assertEquals(this.evaluation.size(), 3);
        assertEquals(this.categoryFood.size(), 1);
        assertEquals(this.criterion1.size(), 6);
        assertEquals(this.criterion2.size(), 0);
        // adding a citizen to a town (smallest composite)
        this.criterion2.add(new Valoration(new IdValoration(4), 4.0f));
        assertEquals(this.evaluation.size(), 3);
        assertEquals(this.categoryFood.size(), 1);
        assertEquals(this.criterion1.size(), 6);
        assertEquals(this.criterion2.size(), 1);
    }

    @Test
    public void testSearchOk() {
        // it works for composites from several places
        assertEquals(this.evaluation.search(this.evaluation.getId()), this.evaluation);
        assertEquals(this.criterion1.search(this.valoration1.getId()), this.valoration1);
        assertEquals(this.categoryFood.search(this.valoration1.getId()), this.valoration1);
        // it works for leaves wherever they are
        assertEquals(this.evaluation.search(this.valoration1.getId()), this.valoration1);
        assertEquals(this.evaluation.search(this.valoration2.getId()), this.valoration2);
    }

    @Test
    public void testSearchNotOk() {
        assertNull(this.evaluation.search(new IdGeneric("255")));
    }

    /**
     * Test of iterator method, of class Composite.
     */
//    @Test
//    public void testIterator() throws Exception {
//        assertNotNull(evaluation.iterator(""));
//    }
//    
//    @Test(expected = IteratorException.class)
//    public void testIteratorEmpty() throws Exception {
//        evaluation.iterator(null);
//    }
//    
//    @Test(expected = IteratorException.class)
//    public void testIteratorLeaf() throws Exception {
//        valoration1.iterator();
//    }
    /**
     * Test of getChildren method, of class Composite.
     */
    @Test
    public void testGetChildren() {
        List<Component> list = evaluation.getList();
        assertTrue(list.contains(categoryFood));
        assertTrue(list.contains(categoryHealth));
        assertEquals(2, list.size());

        list = valoration1.getList();
        assertNull(list);

        list = criterion1.getList();
        assertEquals(6, list.size());
        assertTrue(list.contains(valoration1));
        assertTrue(list.contains(valoration6));
    }

}
