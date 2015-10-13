package es.unileon.happycow.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface for all the objects to store in a database
 * @author dorian
 */
public interface EntityDB {
    public List<PreparedStatement> insertObject(Connection connection)throws SQLException;
    public List<PreparedStatement> updateObject(Connection connection)throws SQLException;
    public List<PreparedStatement> deleteObject(Connection connection) throws SQLException;
}
