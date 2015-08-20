/*
 * 
 */
package es.unileon.happycow.gui.evaluation2;

import es.unileon.happycow.handler.IdHandler;

/**
 *
 * @author dorian
 */
public interface EvaluationController {
    //panel of files
    public void downloadFile(IdHandler id);
    public void removeFile(IdHandler id);
    
    //panel of valorations
    public void removeValoration(IdHandler id);
    public void copyValoration(IdHandler id);
    
    //criterions
    public void setCriterionPonderation(IdHandler id);
}
