/*
 * 
 */
package es.unileon.happycow.controller.evaluation;

import es.unileon.happycow.handler.Category;
import es.unileon.happycow.handler.IdHandler;

/**
 *
 * @author dorian
 */
public interface IEvaluationCriterionController extends IEvaluationController{
    
    public void copyValoration(IdHandler valoration);
    
    public void help(IdHandler id);
    public void evaluated(IdHandler id);
    public void criterionSelected();
    public void removeCriterion(IdHandler idCriterion);
    public void addCriterions();

    public void categorySelected(Category category);
    
}
