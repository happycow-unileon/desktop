package es.unileon.happycow.controller.evaluation;

import es.unileon.happycow.handler.Category;
import es.unileon.happycow.handler.IdHandler;

/**
 * Interface for controller of criterion's evaluation
 * @author dorian
 */
public interface IEvaluationCriterionController extends IEvaluationController{
    /**
     * Add a copy of the given valoration
     * @param valoration original valoration 
     */
    public void copyValoration(IdHandler valoration);
    /**
     * Show the help of window
     * @param id 
     */
    public void help(IdHandler id);
    /**
     * Check/uncheck the criterion as evaluated
     * @param id 
     */
    public void evaluated(IdHandler id);
    /**
     * Set a criterion is selected
     */
    public void criterionSelected();
    /**
     * Remove the given criterion
     * @param idCriterion 
     */
    public void removeCriterion(IdHandler idCriterion);
    /**
     * Add new criterions to evaluate
     */
    public void addCriterions();
    /**
     * Set a category was selected
     * @param category 
     */
    public void categorySelected(Category category);
    
}
