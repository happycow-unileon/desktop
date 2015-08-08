package es.unileon.happycow.model.composite;

import es.unileon.happycow.handler.*;
import es.unileon.happycow.model.composite.table.Entity;
import java.util.LinkedList;

public class EvaluationCategory extends Composite {

    public EvaluationCategory(IdHandler idHandler) {
        this(idHandler, 1);
    }

    public EvaluationCategory(IdHandler idHandler, int weighing) {
        this.TYPE=Entity.CATEGORY;
        id = idHandler;
        _list = new LinkedList<>();
        _weighing = weighing;
    }

}
