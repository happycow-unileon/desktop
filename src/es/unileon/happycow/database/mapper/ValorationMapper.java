/*
 * 
 */
package es.unileon.happycow.database.mapper;

import es.unileon.happycow.database.EntityDB;
import es.unileon.happycow.model.composite.Valoration;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author dorian
 */
public class ValorationMapper implements EntityDB {

    private Valoration valoration;

    public ValorationMapper(Valoration valoration) {
        this.valoration = valoration;
    }

    public void setValoration(Valoration valoration) {
        this.valoration = valoration;
    }

    @Override
    public List<PreparedStatement> insertObject(Connection connection) throws SQLException {
        PreparedStatement sql = connection.prepareStatement("INSERT INTO VALORATION"
                + "(IDVALORATION, IDEVALUATION,NOTA,PONDERACION)"
                + " VALUES(?,?,?,?)");
        sql.setInt(1, Integer.parseInt(valoration.getId().toString()));
        sql.setInt(2, Integer.parseInt(valoration.getIdEvaluation().toString()));
        sql.setFloat(3, valoration.getNota());
        sql.setFloat(4, valoration.getWeighing());
        
        //y guardo su ponderación
        //en este caso, no se guardan

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
        throw new UnsupportedOperationException("Not supported yet.");
    }


}
