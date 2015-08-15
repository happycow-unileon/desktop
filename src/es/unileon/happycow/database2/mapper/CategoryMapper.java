/*
 * 
 */
package es.unileon.happycow.database2.mapper;

import es.unileon.happycow.database2.EntityDB;
import es.unileon.happycow.model.composite2.Component;
import es.unileon.happycow.model.composite2.Criterion;
import es.unileon.happycow.model.composite2.EvaluationCategory;
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
public class CategoryMapper implements EntityDB{
    private EvaluationCategory category;

    public CategoryMapper(EvaluationCategory category) {
        this.category = category;
    }

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
        PreparedStatement 
            sql = connection.prepareStatement("INSERT INTO PONDERACIONCATEGORIA"
                    + "(IDEVALUATION,CATEGORIA,PONDERACION) VALUES(?,?,'" + category.getWeighing() + "')");
            sql.setInt(1, Integer.parseInt(category.getRoot().getId().toString()));
            sql.setString(2, category.getId().toString());

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
