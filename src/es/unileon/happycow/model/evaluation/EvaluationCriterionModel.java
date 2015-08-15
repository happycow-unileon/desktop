/*
 * 
 */
package es.unileon.happycow.model.evaluation;

import es.unileon.happycow.handler.IdHandler;
import es.unileon.happycow.model.composite.Component;
import es.unileon.happycow.model.composite.Criterion;

/**
 *
 * @author dorian
 */
public class EvaluationCriterionModel extends DefaultEvaluationModel{

    public EvaluationCriterionModel(IdHandler idFarm) {
        super(idFarm);
    }
    
    public Criterion getCriterion(IdHandler id) {
         Component c=evaluation.search(id);
        if(c!=null){
            return (Criterion)c;
        }else{
            return null;
        }
    }
    
}
