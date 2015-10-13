package es.unileon.happycow.database.mapper;

import es.unileon.happycow.database.EntityDB;
import es.unileon.happycow.handler.IdHandler;
import es.unileon.happycow.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *Class map for user
 * @author dorian
 */
public class UserMapper implements EntityDB {
    /**
     * User
     */
    private User user;

    /**
     * Constructor
     * @param user 
     */
    public UserMapper(User user) {
        this.user = user;
    }

    /**
     * Set user
     * @param user 
     */
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public List<PreparedStatement> insertObject(Connection connection) throws SQLException {
        //preparo la sentencia
        PreparedStatement sql = connection.prepareStatement("INSERT INTO USUARIO VALUES(?,?,?)");

        sql.setString(1, user.getName());
        sql.setString(2, user.getPassword());
        sql.setString(3, user.getStringRol());

        LinkedList<PreparedStatement> list = new LinkedList<>();
        list.add(sql);
        return list;
    }

    @Override
    public List<PreparedStatement> updateObject(Connection connection) throws SQLException {
        PreparedStatement sql = connection.prepareStatement(
                "UPDATE USUARIO SET CONTRASENIA=? WHERE NOMBREUSUARIO=?");
        sql.setString(1, user.getPassword());
        sql.setString(2, user.getId().getValue());

        LinkedList<PreparedStatement> list = new LinkedList<>();
        list.add(sql);
        return list;
    }

    @Override
    public List<PreparedStatement> deleteObject(Connection connection) throws SQLException {
        PreparedStatement sql = connection.prepareStatement("DELETE FROM usuario "
                + "WHERE nombreusuario=?;");
        sql.setString(1, user.getName());

        LinkedList<PreparedStatement> list = new LinkedList<>();
        list.add(sql);
        return list;
    }

    /**
     * Get an user given the id
     * @param connection of the database
     * @param id id of user
     * @return the needed statements
     * @throws SQLException 
     */
    public static PreparedStatement getObject(Connection connection, IdHandler id) throws SQLException{
        PreparedStatement sql = connection.prepareStatement("SELECT * FROM USUARIO WHERE NOMBREUSUARIO=?");
        sql.setString(1, id.getValue());
        return sql;
    }
    
    /**
     * Get all users
     * @param connection
     * @return the needed statements
     * @throws SQLException 
     */
    public static PreparedStatement getAllObject(Connection connection)throws SQLException{
        return connection.prepareStatement("SELECT * FROM USUARIO");
    }
    
    /**
     * Restore an user from the resultset
     * @param result
     * @return an user
     * @throws SQLException 
     */
    public static User restoreObject(ResultSet result) throws SQLException {
        User one=new User(result.getString("NOMBREUSUARIO"), result.getString("CONTRASENIA"), result.getString("ROL"));
        one.setPassword(result.getString("CONTRASENIA"));
        return one;
    }

}
