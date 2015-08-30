/*
 * 
 */
package es.unileon.happycow.database.mapper;

import es.unileon.happycow.database.EntityDB;
import es.unileon.happycow.handler.IdHandler;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author dorian
 */
public class ListEvaluationsMapper implements EntityDB{
    private IdHandler idFarm;

    public ListEvaluationsMapper(IdHandler idFarm) {
        this.idFarm = idFarm;
    }

    public void setIdFarm(IdHandler idFarm) {
        this.idFarm = idFarm;
    }
    
    

    @Override
    public List<PreparedStatement> insertObject(Connection connection) throws SQLException {
        return new LinkedList<>();
    }

    @Override
    public List<PreparedStatement> updateObject(Connection connection) throws SQLException {
        return new LinkedList<>();
    }

    @Override
    public List<PreparedStatement> deleteObject(Connection connection) throws SQLException {
        return new LinkedList<>();
    }
    
    public static PreparedStatement getObject(Connection connection, IdHandler id) throws SQLException{
        PreparedStatement sql = connection.prepareStatement("SELECT * FROM EVALUATION WHERE IDGRANJA=?");
        sql.setString(1, id.getValue());
        return sql;
    }
    
}
