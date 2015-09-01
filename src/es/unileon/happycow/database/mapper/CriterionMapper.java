/*
 * 
 */
package es.unileon.happycow.database.mapper;

import es.unileon.happycow.database.EntityDB;
import es.unileon.happycow.handler.IdCategory;
import es.unileon.happycow.model.composite.Criterion;
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
public class CriterionMapper implements EntityDB {

    private Criterion criterion;

    public CriterionMapper(Criterion criterion) {
        this.criterion = criterion;
    }

    public void setCriterion(Criterion criterion) {
        this.criterion = criterion;
    }

    @Override
    public List<PreparedStatement> insertObject(Connection connection) throws SQLException {
        PreparedStatement sql = connection.prepareStatement("INSERT INTO "
                + "CRITERION(NOMBRECRITERIO,DESCRIPCION,HELP,CATEGORIA) VALUES(?,?,?,?)");
        sql.setString(1, criterion.getName());
        sql.setString(2, criterion.getDescription());
        sql.setString(3, criterion.getHelp());
        sql.setString(4, (new IdCategory(criterion.getCategory()).getValue()));

        LinkedList<PreparedStatement> list = new LinkedList<>();
        list.add(sql);
        return list;
    }

    @Override
    public List<PreparedStatement> updateObject(Connection connection) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<PreparedStatement> deleteObject(Connection connection) throws SQLException {
        PreparedStatement sql = connection.prepareStatement("DELETE FROM CRITERION WHERE NOMBRECRITERIO=?");
        sql.setString(1, criterion.getId().getValue());
        LinkedList<PreparedStatement> list = new LinkedList<>();
        list.add(sql);
        return list;
    }

    public static PreparedStatement getAllObject(Connection connection)throws SQLException{
        return connection.prepareStatement("SELECT * FROM CRITERION");
    }
    
    public static Criterion restoreObject(ResultSet result) throws SQLException {
        return new Criterion(
                            result.getString("NOMBRECRITERIO"),
                            new IdCategory(result.getString("CATEGORIA")),
                            result.getString("DESCRIPCION"),
                            result.getString("HELP"));
    }
}
