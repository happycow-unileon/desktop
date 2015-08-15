package es.unileon.happycow.database2.mapper;

import es.unileon.happycow.database2.EntityDB;
import es.unileon.happycow.handler.IdHandler;
import es.unileon.happycow.model.composite2.Component;
import es.unileon.happycow.model.composite2.EvaluationCategory;
import es.unileon.happycow.model.evaluation.IEvaluationModel;
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
public class EvaluationMapper implements EntityDB {

    private IEvaluationModel evaluation;

    public EvaluationMapper(IEvaluationModel evaluation) {
        this.evaluation = evaluation;
    }

    public void setEvaluation(IEvaluationModel evaluation) {
        this.evaluation = evaluation;
    }

    @Override
    public List<PreparedStatement> insertObject(Connection connection) throws SQLException {
        LinkedList<PreparedStatement> list = new LinkedList<>();
        //guardamos la información
        InformationEvaluationMapper map = new InformationEvaluationMapper(evaluation.getInformation());
        list.addAll(map.insertObject(connection));

        //iteramos las categorías y las guardamos
        //a su vez, van guardando ponderaciones, criterios y valoraciones
        for (Component cat : evaluation.getComposite().getList()) {
            CategoryMapper mapCat=new CategoryMapper((EvaluationCategory)cat);
            list.addAll(mapCat.insertObject(connection));
        }

        return list;
    }
    
    public static PreparedStatement getNumberCows(Connection connection, IdHandler id) throws  SQLException{
        PreparedStatement sql = connection.prepareStatement("SELECT NUMEROVACAS FROM EVALUATION WHERE IDGRANJA=?");
        sql.setString(1, id.toString());
        return sql;
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
