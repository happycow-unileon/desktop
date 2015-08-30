/*
 * 
 */
package es.unileon.happycow.database.mapper;

import es.unileon.happycow.database.EntityDB;
import es.unileon.happycow.handler.IdFarm;
import es.unileon.happycow.handler.IdHandler;
import es.unileon.happycow.handler.IdUser;
import es.unileon.happycow.model.Farm;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author dorian
 */
public class FarmMapper implements EntityDB {

    private Farm farm;

    public FarmMapper(Farm farm) {
        this.farm = farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }

    @Override
    public List<PreparedStatement> insertObject(Connection connection) throws SQLException {
        PreparedStatement sql = connection.prepareStatement("INSERT INTO FARM"
                + "(IDGRANJA,NOMBREGRANJA,NOMBREUSUARIO,DIRECCION,"
                + "NOMBREGANADERO,DNIGANADERO,NUMEROVACAS,"
                + "IDENTIFICADORGRANJA,OTROSDATOS,ENABLED)"
                + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        sql.setInt(1, Integer.parseInt(farm.getIdFarm().getValue()));
        sql.setString(2, farm.getFarmName());
        sql.setString(3, farm.getNameUser());
        sql.setString(4, farm.getAddress());
        sql.setString(5, farm.getFarmerName());
        sql.setString(6, farm.getDniFarmer());
        sql.setInt(7, farm.getCowNumber());
        sql.setString(8, farm.getFarmIdentifier());
        sql.setString(9, farm.getOtherData());
        sql.setBoolean(10, farm.isEnabled());

        LinkedList<PreparedStatement> listSql = new LinkedList<>();
        listSql.add(sql);
        return listSql;
    }

    @Override
    public List<PreparedStatement> updateObject(Connection connection) throws SQLException {
        PreparedStatement sql = connection.prepareStatement("UPDATE FARM SET "
                + "NOMBREGRANJA=?,"
                + "DIRECCION=?,"
                + "NOMBREGANADERO=?,"
                + "IDENTIFICADORGRANJA=?,"
                + "OTROSDATOS=?,"
                + "DNIGANADERO=?,"
                + "NUMEROVACAS=?, "
                + "ENABLED=? "
                + "WHERE IDGRANJA=?");

        sql.setString(1, farm.getFarmName());
        sql.setString(2, farm.getAddress());
        sql.setString(3, farm.getFarmerName());
        sql.setString(4, farm.getFarmIdentifier());
        sql.setString(5, farm.getOtherData());
        sql.setString(6, farm.getDniFarmer());
        sql.setInt(7, farm.getCowNumber());
        sql.setBoolean(8, farm.isEnabled());
        sql.setInt(9, Integer.parseInt(farm.getIdFarm().getValue()));

        LinkedList<PreparedStatement> listSql = new LinkedList<>();
        listSql.add(sql);
        return listSql;
    }

    @Override
    public List<PreparedStatement> deleteObject(Connection connection) throws SQLException {
        PreparedStatement sql = connection.prepareStatement("DELETE FROM FARM WHERE IDGRANJA=?");
        sql.setInt(1, Integer.parseInt(farm.getIdFarm().getValue()));

        LinkedList<PreparedStatement> listSql = new LinkedList<>();
        listSql.add(sql);
        return listSql;
    }

    public static PreparedStatement getObject(IdHandler id, Connection connection) throws SQLException {
        PreparedStatement sql = connection.prepareStatement("SELECT * FROM FARM WHERE IDGRANJA=?");
        sql.setInt(1, Integer.parseInt(id.getValue()));
        return sql;
    }

    public static PreparedStatement getAllObject(IdHandler idUser, Connection connection) throws SQLException {
        PreparedStatement sql = connection.prepareStatement("SELECT * FROM FARM WHERE NOMBREUSUARIO=? AND ENABLED=?");
        sql.setString(1, idUser.getValue());
        sql.setBoolean(2, true);
        return sql;
    }
    
    public static PreparedStatement getDisabledFarms(IdHandler idUser, Connection connection) throws SQLException{
        PreparedStatement sql = connection.prepareStatement("SELECT * FROM FARM WHERE ENABLED=? AND NOMBREUSUARIO=?");
        sql.setBoolean(1, false);
        sql.setString(2, idUser.getValue());
        return sql;
    }
    
    public static PreparedStatement getNumberCows(IdHandler id, Connection connection) throws SQLException{
        PreparedStatement sql = connection.prepareStatement("SELECT NUMEROVACAS FROM FARM WHERE IDGRANJA=?");
        sql.setString(1, id.getValue());
        return sql;
    }

    public static Farm restoreObject(ResultSet result) throws SQLException {
        return new Farm(new IdFarm(result.getInt("IDGRANJA")), 
                result.getString("NOMBREGRANJA"),
                result.getString("IDENTIFICADORGRANJA"),
                result.getString("DIRECCION"),
                result.getString("NOMBREGANADERO"),
                result.getString("DNIGANADERO"),
                result.getInt("NUMEROVACAS"),
                new IdUser(result.getString("NOMBREUSUARIO")),
                result.getString("OTROSDATOS"),
                result.getBoolean("ENABLED"));
    }
}
