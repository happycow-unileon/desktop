package es.unileon.happycow.model.composite;

import es.unileon.happycow.handler.*;
import es.unileon.happycow.model.InformationEvaluation;
import es.unileon.happycow.model.composite.table.Entity;

public class Evaluation extends Composite {

    private InformationEvaluation info;

    public Evaluation(IdHandler idHandler, InformationEvaluation info) {
        super(Entity.EVALUATION, idHandler);
        this.info = info;
    }

    public Evaluation(IdHandler idHandler, int weighing, InformationEvaluation info) {
        super(Entity.EVALUATION, idHandler, weighing);
        this.info = info;
    }

    @Override
    public InformationEvaluation getInformation() {
        return info;
    }

}
