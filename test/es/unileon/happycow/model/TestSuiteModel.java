package es.unileon.happycow.model;

import es.unileon.happycow.model.composite.table.PermissionCompositeTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author dorian
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({es.unileon.happycow.model.InformationEvaluationTest.class, 
    es.unileon.happycow.model.UserTest.class, 
    es.unileon.happycow.model.FarmTest.class,
    PermissionCompositeTest.class})
public class TestSuiteModel {
    
}
