package es.unileon.happycow.database;

import es.unileon.happycow.database.concreteDatabase.TestSuiteConcreteDatabase;
import es.unileon.happycow.database.prototype.CriterionPrototypeTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author dorian
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({es.unileon.happycow.database.DatabaseTest.class,
                    TestSuiteConcreteDatabase.class,
                    CriterionPrototypeTest.class})
public class TestSuiteDatabaseSubPackages {
    
}
