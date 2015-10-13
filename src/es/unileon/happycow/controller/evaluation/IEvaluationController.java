/*
 * 
 */
package es.unileon.happycow.controller.evaluation;

import es.unileon.happycow.handler.IdHandler;

/**
 *
 * @author dorian
 */
public interface IEvaluationController {
    public void downloadFile(IdHandler id);
    public void removeFile(IdHandler id);
    public void addFile();
    
    public void removeValoration(IdHandler valoration);
    public void addNewValoration();
    
    public void setPonderationCriterion(IdHandler id, String ponderation);
    public void setCategoryPonderation(IdHandler id,String ponderation);
    
    public void finishEvaluation();
}
