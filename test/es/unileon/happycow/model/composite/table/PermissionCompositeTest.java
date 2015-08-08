/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.unileon.happycow.model.composite.table;

import es.unileon.happycow.handler.Category;
import es.unileon.happycow.handler.IdCategory;
import es.unileon.happycow.handler.IdCriterion;
import es.unileon.happycow.handler.IdEvaluation;
import es.unileon.happycow.handler.IdHandler;
import es.unileon.happycow.handler.IdValoration;
import es.unileon.happycow.model.composite.Component;
import es.unileon.happycow.model.composite.CompositeException;
import es.unileon.happycow.model.composite.Criterion;
import es.unileon.happycow.model.composite.Evaluation;
import es.unileon.happycow.model.composite.EvaluationCategory;
import es.unileon.happycow.model.composite.Valoration;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dorian
 */
public class PermissionCompositeTest {

    private PermissionComposite permi;

    private Component evaluation, categoryHealth, categoryFood, criterion1, criterion2;
    private Component valoration1, valoration2, valoration3, valoration4, valoration5, valoration6;

    @Before
    public void setUp() throws Exception {
        permi = PermissionComposite.getInstance();
        IdHandler id=new IdEvaluation(2);
        evaluation = new Evaluation(id, null);
        id=new IdCategory(Category.HEALTH);
        categoryHealth = new EvaluationCategory(id);
        
        id=new IdCategory(Category.FOOD);
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
    }

    @Test
    public void testCanBeMyChildOK() throws CompositeException {
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

    @Test(expected = CompositeException.class)
    public void testCanBeMyChildNotOk() throws CompositeException {
        evaluation.add(valoration1);
    }

    @Test
    public void testGetInstanceOk() {
        PermissionComposite permiss = PermissionComposite.getInstance();
        assertEquals(permi, permiss);
    }

}
