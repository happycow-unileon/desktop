/*
 * 
 */
package es.unileon.happycow.controller.evaluation;

import es.unileon.happycow.handler.IdHandler;

/**
 *
 * @author dorian
 */
public interface IEvaluationCowController extends IEvaluationController{
    /**
     * Show the help of the criterion
     * @param id 
     */
    public void help(IdHandler id);
    /**
     * Set a category was changed
     */
    public void changeCategory();
    /**
     * The category of ponderation was changed
     */
    public void selectedCategoryPonderation();
    /**
     * The criterion of ponderation was changed
     */
    public void selectedCriterionPonderation();
    /**
     * Add a new cow
     */
    public void addCow();
    /**
     * Duplicate the selected cow
     */
    public void duplicateCow();
    /**
     * Set a cow is selected
     */
    public void selectedCow();
    
}
