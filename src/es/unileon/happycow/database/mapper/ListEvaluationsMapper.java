package es.unileon.happycow.database.mapper;

import es.unileon.happycow.database.EntityDB;
import es.unileon.happycow.handler.IdHandler;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Class map for a list of evaluations
 * @author dorian
 */
public class ListEvaluationsMapper implements EntityDB{
    /**
     * Associated farm
     */
    private IdHandler idFarm;

    /**
     * Constructor
     * @param idFarm 
     */
    public ListEvaluationsMapper(IdHandler idFarm) {
        this.idFarm = idFarm;
    }

    /**
     * Set the id farm
     * @param idFarm 
     */
    public void setIdFarm(IdHandler idFarm) {
        this.idFarm = idFarm;
    }
    
    

    @Override
    public List<PreparedStatement> insertObject(Connection connection) throws SQLException {
        //TODO
        return new LinkedList<>();
    }

    @Override
    public List<PreparedStatement> updateObject(Connection connection) throws SQLException {
        //TODO
        return new LinkedList<>();
    }

    @Override
    public List<PreparedStatement> deleteObject(Connection connection) throws SQLException {
       //TODO
        return new LinkedList<>();
    }
    
    /**
     * Get all evaluations from an id farm
     * @param connection
     * @param id
     * @return
     * @throws SQLException 
     */
    public static PreparedStatement getObject(Connection connection, IdHandler id) throws SQLException{
        PreparedStatement sql = connection.prepareStatement("SELECT * FROM EVALUATION WHERE IDGRANJA=?");
        sql.setString(1, id.getValue());
        return sql;
    }
    
}
