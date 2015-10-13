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
    
    public void help(IdHandler id);
    public void changeCategory();
    public void selectedCategoryPonderation();
    public void selectedCriterionPonderation();
    public void addCow();
    public void duplicateCow();
    public void selectedCow();
    
}
