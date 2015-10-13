package es.unileon.happycow.database.mapper;

import es.unileon.happycow.database.EntityDB;
import es.unileon.happycow.handler.Category;
import es.unileon.happycow.handler.IdCategory;
import es.unileon.happycow.handler.IdCriterion;
import es.unileon.happycow.handler.IdEvaluation;
import es.unileon.happycow.handler.IdFarm;
import es.unileon.happycow.handler.IdHandler;
import es.unileon.happycow.handler.IdUser;
import es.unileon.happycow.handler.IdValoration;
import es.unileon.happycow.model.InformationEvaluation;
import es.unileon.happycow.model.composite.Component;
import es.unileon.happycow.model.composite.CompositeException;
import es.unileon.happycow.model.composite.Criterion;
import es.unileon.happycow.model.composite.Evaluation;
import es.unileon.happycow.model.composite.EvaluationCategory;
import es.unileon.happycow.model.composite.Valoration;
import es.unileon.happycow.model.evaluation.IEvaluationModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Map class for an evaluation
 * @author dorian
 */
public class EvaluationMapper implements EntityDB {
    /**
     * Evaluation
     */
    private IEvaluationModel evaluation;

    /**
     * Constructor
     * @param evaluation 
     */
    public EvaluationMapper(IEvaluationModel evaluation) {
        this.evaluation = evaluation;
    }

    /**
     * Set the evaluation
     * @param evaluation 
     */
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
            CategoryMapper mapCat = new CategoryMapper((EvaluationCategory) cat);
            list.addAll(mapCat.insertObject(connection));
        }

        return list;
    }

    /**
     * get the number of cows of the evaluation
     * @param connection
     * @param id
     * @return
     * @throws SQLException 
     */
    public static PreparedStatement getNumberCows(Connection connection, IdHandler id) throws SQLException {
        PreparedStatement sql = connection.prepareStatement("SELECT NUMEROVACAS FROM EVALUATION WHERE IDGRANJA=?");
        sql.setString(1, id.getValue());
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

    /**
     * Get an evaluation given its id
     * @param connection
     * @param id
     * @return
     * @throws SQLException 
     */
    public static PreparedStatement getObject(Connection connection, IdHandler id) throws SQLException {
        PreparedStatement sql = connection.prepareStatement(
                "SELECT "
                + "E.USUARIO AS USER, E.IDEVALUATION, E.FECHA, E.NUMEROVACAS, E.IDGRANJA, E.NOTA AS NOTAEV, E.ALIMENTACION, E.SALUD, E.COMFORT, E.COMPORTAMIENTO,"
                + "C.CATEGORIA, PCA.PONDERACION AS PONCATEGORIA, C.NOMBRECRITERIO, PCR.PONDERACION AS PONCRITERIO, V.NOTA, "
                + "V.IDVALORATION "
                + "FROM "
                + "EVALUATION AS E "
                + "LEFT JOIN CRITERION AS C "
                + "LEFT JOIN PONDERACIONCATEGORIA AS PCA ON PCA.CATEGORIA=C.CATEGORIA "
                + "LEFT JOIN VALORATION AS V  ON C.NOMBRECRITERIO=V.NOMBRECRITERIO "
                + "LEFT JOIN PONDERACIONCRITERIO AS PCR ON PCR.NOMBRECRITERIO=V.NOMBRECRITERIO AND PCR.IDEVALUATION=V.IDEVALUATION "
                + "WHERE V.IDEVALUATION=? AND PCA.IDEVALUATION=? AND E.IDEVALUATION=?");
        sql.setInt(1, Integer.parseInt(id.getValue()));
        sql.setInt(2, Integer.parseInt(id.getValue()));
        sql.setInt(3, Integer.parseInt(id.getValue()));
        return sql;
    }

    /**
     * Restore an evaluation
     * @param result
     * @return
     * @throws SQLException 
     */
    public static Evaluation restoreObject(ResultSet result) throws SQLException {
        InformationEvaluation information = null;
        Evaluation evaluation = null;

        boolean firstTime = true;
        while (result.next()) {
            try {
                if (firstTime) {
                    //get the information
                    information = new InformationEvaluation(
                            (IdHandler) new IdEvaluation(result.getInt("IDEVALUATION")),
                            new IdFarm(result.getInt("IDGRANJA")),
                            new IdUser(result.getString("USER")),
                            result.getFloat("NOTAEV"),
                            result.getFloat("ALIMENTACION"),
                            result.getFloat("SALUD"),
                            result.getFloat("COMFORT"),
                            result.getFloat("COMPORTAMIENTO"),
                            result.getDate("FECHA"),
                            result.getInt("NUMEROVACAS"));

                    evaluation = new Evaluation(information);
                    firstTime = false;

                    //add the categories
                    evaluation.add(new EvaluationCategory(new IdCategory(Category.BEHAVIOUR)));
                    evaluation.add(new EvaluationCategory(new IdCategory(Category.FOOD)));
                    evaluation.add(new EvaluationCategory(new IdCategory(Category.HEALTH)));
                    evaluation.add(new EvaluationCategory(new IdCategory(Category.HOUSE)));
                }

                //get the category
                IdHandler category = new IdCategory(result.getString("CATEGORIA"));
                Component categoryComponent = evaluation.search(category);

                //set weighing of category
                float ponderation = result.getFloat("PONCATEGORIA");
                if (!result.wasNull()) {
                    categoryComponent.setWeighing(ponderation);
                }
                //add category to evaluation
//                evaluation.add(categoryComponent);

                //set the criterion
                if (result.getString("NOMBRECRITERIO") != null) {
                    IdHandler id=new IdCriterion(result.getString("NOMBRECRITERIO"));
                    
                    Component criterion=categoryComponent.search(id);
                    if(criterion==null){
                        criterion= new Criterion(id, result.getFloat("PONCRITERIO"));
                        criterion.setWeighing(result.getFloat("PONCRITERIO"));
                        categoryComponent.add(criterion);
                    }

                    Valoration valoration = new Valoration(new IdValoration(result.getInt("IDVALORATION")), result.getInt("NOTA"));

                    //add the valoration to the criterion
                    criterion.add(valoration);
                }
            } catch (CompositeException ex) {
                Logger.getLogger(EvaluationMapper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return evaluation;
    }

}
