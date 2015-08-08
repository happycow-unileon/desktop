/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import es.unileon.happycow.TestSuiteHappycow;
import es.unileon.happycow.database.TestSuiteDatabaseSubPackages;
import es.unileon.happycow.handler.TestSuiteHandler;
import es.unileon.happycow.model.TestSuiteModel;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author dorian
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({TestSuiteHandler.class,
                    TestSuiteDatabaseSubPackages.class,
                    TestSuiteHappycow.class,
    TestSuiteModel.class})

public class AllTestSuite {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
}
