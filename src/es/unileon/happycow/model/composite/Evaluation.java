package es.unileon.happycow.model.composite;

import es.unileon.happycow.model.InformationEvaluation;

/**
 *
 * @author dorian
 */
public class Evaluation extends Composite {

    private InformationEvaluation info;

    public Evaluation(InformationEvaluation info) {
        this(1, info);
    }

    public Evaluation(int weighing, InformationEvaluation info) {
        super();
        this.info = info;

        this.entity = Entity.EVALUATION;
        id = info.getIdEvaluation();
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
