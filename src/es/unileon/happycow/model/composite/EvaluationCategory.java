package es.unileon.happycow.model.composite;

import es.unileon.happycow.handler.*;
import es.unileon.happycow.model.composite.table.Entity;

public class EvaluationCategory extends Composite {

    public EvaluationCategory(IdHandler idHandler) {
        super(Entity.CATEGORY, idHandler);
    }

    public EvaluationCategory(IdHandler idHandler, int weighing) {
        super(Entity.CATEGORY, idHandler, weighing);
    }

}
