/*
 * 
 */
package es.unileon.happycow.database2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author dorian
 */
public interface EntityDB {
    public List<PreparedStatement> insertObject(Connection connection)throws SQLException;
    public List<PreparedStatement> updateObject(Connection connection)throws SQLException;
    public List<PreparedStatement> deleteObject(Connection connection) throws SQLException;
    
//    public PreparedStatement getObject(IdHandler id, Connection connection)throws SQLException;
//    public void restoreObject(ResultSet result)throws SQLException;
}
