/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.unileon.happycow.model.composite;

import es.unileon.happycow.handler.IdHandler;
import es.unileon.happycow.model.InformationEvaluation;
import es.unileon.happycow.model.composite.table.Entity;
import es.unileon.happycow.model.composite.iterator.Aggregate;
import java.io.Serializable;

/**
 *
 * @author amdiaz
 */
public interface Component extends Aggregate, Serializable {

    public boolean isLeaf();

    public Component search(IdHandler id);

    public void show(int depth);

    public Entity getLevel();

    public IdHandler getId();

    public float getWeighing();

    public void setWeighing(float weighing);

    public Component getParent();

    public Component getRoot();

    public void setParent(Component parent);

    public void setRoot(Component root);

    public InformationEvaluation getInformation();
}
