package es.unileon.happycow.controller.evaluation;

import es.unileon.happycow.handler.IdHandler;

/**
 * Basic interface for all mode of evaluations
 * @author dorian
 */
public interface IEvaluationController {
    /**
     * Download a file
     * @param id 
     */
    public void downloadFile(IdHandler id);
    /**
     * Remove a file
     * @param id 
     */
    public void removeFile(IdHandler id);
    /**
     * Add a new file
     */
    public void addFile();
    /**
     * Remove a given valoration
     * @param valoration 
     */
    public void removeValoration(IdHandler valoration);
    /**
     * Add a new valoration
     */
    public void addNewValoration();
    /**
     * Set the criterion ponderation
     * @param id
     * @param ponderation 
     */
    public void setPonderationCriterion(IdHandler id, String ponderation);
    /**
     * Set the category ponderation
     * @param id
     * @param ponderation 
     */
    public void setCategoryPonderation(IdHandler id,String ponderation);
    /**
     * Finish the evaluation
     */
    public void finishEvaluation();
}
