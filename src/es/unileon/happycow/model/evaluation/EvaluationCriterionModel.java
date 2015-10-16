/*
 * 
 */
package es.unileon.happycow.model.evaluation;

import es.unileon.happycow.handler.IdHandler;
import es.unileon.happycow.model.composite.Component;
import es.unileon.happycow.model.composite.Criterion;
import es.unileon.happycow.model.composite.Evaluation;

/**
 *
 * @author dorian
 */
public class EvaluationCriterionModel extends DefaultEvaluationModel{

    public EvaluationCriterionModel(IdHandler idFarm, IdHandler user) {
        super(idFarm, user);
    }
    
    public EvaluationCriterionModel(Evaluation evaluation){
        super(evaluation);
    }
    
    public Criterion getCriterion(IdHandler id) {
         Component c=evaluation.search(id);
        if(c!=null){
            return (Criterion)c;
        }else{
            return null;
        }
    }
    
    public void removeValoration(IdHandler category, IdHandler criterion, IdHandler valoration){
        Component cri = evaluation.search(criterion);
        Component val = cri.search(valoration);
        cri.remove(val);
    }
    
    
}
