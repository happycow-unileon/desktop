/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.unileon.happycow.database.concreteDatabase;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author dorian
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({es.unileon.happycow.database.concreteDatabase.DefaultDatabaseTest.class,
                    es.unileon.happycow.database.concreteDatabase.InsertCriterionTest.class,
                    es.unileon.happycow.database.concreteDatabase.OracleTest.class,
                    es.unileon.happycow.database.concreteDatabase.SQLiteTest.class})
public class TestSuiteConcreteDatabase {
}
