/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.unileon.happycow;

import es.unileon.happycow.model.FarmTest;
import es.unileon.happycow.model.InformationEvaluationTest;
import es.unileon.happycow.model.UserTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author dorian
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({FarmTest.class,
                    InformationEvaluationTest.class,
                    UserTest.class})
public class TestSuiteHappycow {
    
}
