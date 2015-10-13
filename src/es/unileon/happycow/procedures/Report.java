package es.unileon.happycow.procedures;

import es.unileon.happycow.model.evaluation.IEvaluationModel;
import es.unileon.happycow.strategy.EvaluationAlgorithm;

/**
 * Class which process the report
 * @author amdiaz8
 */
public class Report {
    /**
     * Algorithm used for the report
     */
    private EvaluationAlgorithm calculus;

    /**
     * Constructor
     * @param calculus
     * @param model 
     */
    public Report(EvaluationAlgorithm calculus, IEvaluationModel model) {
        this.calculus = calculus;
        this.calculus.setModel(model);
    }
    
    @Override
    public String toString(){
        return "<html><body>" + calculus.toString() + "</body></html>";
    }
}
