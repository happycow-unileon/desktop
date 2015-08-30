package es.unileon.happycow.procedures;

import es.unileon.happycow.model.evaluation.IEvaluationModel;
import es.unileon.happycow.strategy.EvaluationAlgorithm;

/**
 *
 * @author amdiaz8
 */
public class Report {
    private EvaluationAlgorithm calculus;
    private IEvaluationModel model;

    public Report(EvaluationAlgorithm calculus, IEvaluationModel model) {
        this.calculus = calculus;
        this.model = model;
        this.calculus.setModel(model);
    }
    
    @Override
    public String toString(){
        return "<html><body>" + calculus.toString() + "</body></html>";
    }
}
