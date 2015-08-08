package es.unileon.happycow.model.composite;

import es.unileon.happycow.handler.*;
import es.unileon.happycow.model.InformationEvaluation;
import es.unileon.happycow.model.composite.table.Entity;
import java.util.LinkedList;

public class Evaluation extends Composite {

    private InformationEvaluation info;

    public Evaluation(IdHandler idHandler, InformationEvaluation info) {
        this(idHandler, 1, info);
    }

    public Evaluation(IdHandler idHandler, int weighing, InformationEvaluation info) {
        this.info = info;
        
        this.TYPE=Entity.EVALUATION;
        id = idHandler;
        _list = new LinkedList<>();
        _weighing = weighing;
    }

    @Override
    public InformationEvaluation getInformation() {
        return info;
    }

}
