/*
 * 
 */
package es.unileon.happycow.database.mapper;

import es.unileon.happycow.database.EntityDB;
import es.unileon.happycow.model.composite.Component;
import es.unileon.happycow.model.composite.Criterion;
import es.unileon.happycow.model.composite.Valoration;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Map class for a criterion (with ponderation
 * @author dorian
 */
public class CriterionEvaluationMapper implements EntityDB {
    /**
     * Criterion
     */
    private Criterion criterion;

    /**
     * Constructor
     * @param criterion 
     */
    public CriterionEvaluationMapper(Criterion criterion) {
        this.criterion = criterion;
    }

    /**
     * Set the criterion
     * @param criterion 
     */
    public void setCriterion(Criterion criterion) {
        this.criterion = criterion;
    }

    @Override
    public List<PreparedStatement> insertObject(Connection connection) throws SQLException {

        LinkedList<PreparedStatement> list = new LinkedList<>();
        //guardo todas mis valoraciones
        for (Component valoration : criterion.getList()) {
            ValorationMapper mapVal;
            mapVal = new ValorationMapper((Valoration) valoration);
            list.addAll(mapVal.insertObject(connection));
        }
        
        //guardo además mi ponderación en la evaluación
        /**
         * SetFloat del sql tiene errores de forma que si es 0.5, aparece
         * 0.50000000000000342 y similares por ello se setea directamente en el
         * sql
         */
        PreparedStatement sql = connection.prepareStatement("INSERT INTO PONDERACIONCRITERIO"
                + "(IDEVALUATION, NOMBRECRITERIO, PONDERACION) VALUES(?,?,'" + criterion.getWeighing() + "')");
        sql.setInt(1, Integer.parseInt(criterion.getRoot().getId().getValue()));
        sql.setString(2, criterion.getId().getValue());
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
