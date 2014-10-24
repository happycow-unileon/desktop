/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.unileon.happycow.database.concreteDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dorian
 */
public class InsertCriterion {
    public void execute(PreparedStatement sql, Connection conection){
        try {
            sql = conection.prepareStatement(sentence);
        } catch (SQLException ex) {
            Logger.getLogger(InsertCriterion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private String sentence="INSERT INTO CRITERION (NOMBRECRITERIO,DESCRIPCION,HELP,CATEGORIA)"
            + "VALUES "
            + "('','','',''),"
            + "('','','',''),"
            + "('','','',''),"
            + "('','','',''),"
            + "('','','',''),"
            + "(,,)";
}
