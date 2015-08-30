/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.unileon.happycow.model.composite;

import es.unileon.happycow.handler.IdHandler;

/**
 *
 * @author dorian
 */
public class EvaluationCategory extends Composite {

    public EvaluationCategory(IdHandler idHandler) {
        this(idHandler, 1);
    }

    public EvaluationCategory(IdHandler idHandler, float weighing) {
        super();
        this.entity = Entity.CATEGORY;
        id = idHandler;
        this.weighing = weighing;
    }

    public String toString() {
        return "\tCategoria: " + this.id.getValue()+ "\n" + super.toString();
    }
}
