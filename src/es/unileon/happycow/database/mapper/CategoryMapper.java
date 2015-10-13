package es.unileon.happycow.database.mapper;

import es.unileon.happycow.database.EntityDB;
import es.unileon.happycow.model.composite.Component;
import es.unileon.happycow.model.composite.Criterion;
import es.unileon.happycow.model.composite.EvaluationCategory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Map class for category
 * @author dorian
 */
public class CategoryMapper implements EntityDB {
    /**
     * Category
     */
    private EvaluationCategory category;

    /**
     * Constructor
     * @param category 
     */
    public CategoryMapper(EvaluationCategory category) {
        this.category = category;
    }

    /**
     * Set category
     * @param category 
     */
    public void setCategory(EvaluationCategory category) {
        this.category = category;
    }

    @Override
    public List<PreparedStatement> insertObject(Connection connection) throws SQLException {
        LinkedList<PreparedStatement> list = new LinkedList<>();

        //guardo todas mis criterios
        for (Component criterion : category.getList()) {
            CriterionEvaluationMapper mapVal;
            mapVal = new CriterionEvaluationMapper((Criterion) criterion);
            list.addAll(mapVal.insertObject(connection));
        }

        //guardo además mi ponderación en la evaluación
        /**
         * SetFloat del sql tiene errores de forma que si es 0.5, aparece
         * 0.50000000000000342 y similares por ello se setea directamente en el
         * sql
         */
        PreparedStatement sql = connection.prepareStatement("INSERT INTO PONDERACIONCATEGORIA"
                + "(IDEVALUATION,CATEGORIA,PONDERACION) VALUES(?,?,'" + category.getWeighing() + "')");
        sql.setInt(1, Integer.parseInt(category.getRoot().getId().getValue()));
        sql.setString(2, category.getId().getValue());
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
