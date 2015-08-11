package es.unileon.happycow.model.composite2;

import es.unileon.happycow.handler.IdHandler;
import es.unileon.happycow.model.InformationEvaluation;

/**
 *
 * @author dorian
 */
public class Evaluation extends Composite {

    private InformationEvaluation info;

    public Evaluation(IdHandler idHandler, InformationEvaluation info) {
        this(idHandler, 1, info);
    }

    public Evaluation(IdHandler idHandler, int weighing, InformationEvaluation info) {
        super();
        this.info = info;

        this.entity = Entity.EVALUATION;
        id = idHandler;
        this.weighing = weighing;
    }

    public InformationEvaluation getInformation() {
        return info;
    }

    @Override
    public String toString() {
        return "Evaluación: " + id.toString() + "\n"+ super.toString();
    }
}
