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
public interface IEvaluationCriterionController {

    public void downloadFile(IdHandler id);
    public void removeFile(IdHandler id);
    public void addFile();

    public void removeValoration(IdHandler valoration);
    public void copyValoration(IdHandler valoration);
    public void addNewValoration();
    
    public void help(IdHandler id);
    public void evaluated(IdHandler id);
    public void setPonderationCriterion(IdHandler id, String ponderation);
    public void criterionSelected();
    public void removeCriterion(IdHandler idCriterion);
    public void addCriterions();
    
    public void setCategoryPonderation(String ponderation);
    public void categorySelected(Category category);
    
    public void finishEvaluation();
    
}
