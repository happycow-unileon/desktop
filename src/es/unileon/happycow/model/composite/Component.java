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
import java.util.LinkedList;

/**
 *
 * @author amdiaz
 */
public abstract class Component implements Aggregate, Serializable {

    protected float _weighing;
    protected LinkedList<Component> _list;
    protected IdHandler id;
    protected Entity TYPE;
    protected Component parent;
    protected Component root;

    public abstract boolean isLeaf();

    public abstract String show(int depth);

    public IdHandler getId() {
        return id;
    }

    public float getWeighing() {
        return _weighing;
    }

    public void setWeighing(float weighing) {
        this._weighing = weighing;
    }

    public Component getParent() {
        return parent;
    }

    public Component getRoot() {
        return root;
    }

    public void setParent(Component parent) {
        this.parent = parent;
    }

    public void setRoot(Component root) {
        this.root = root;
    }

    public void add(Component component) throws CompositeException {
        throw new CompositeException("No se puede añadir nada a una valoracion.");
    }

    public InformationEvaluation getInformation(){
        return this.root.getInformation();
    }

    public LinkedList<Component> getList() {
        return _list;
    }

    /**
     *
     * @param delete el componente a eliminar
     *
     * @return <code>true</code> si eliminó con éxito, <code>false</code> si al
     * contrario
     */
    public abstract boolean remove(Component delete);
    
    public abstract boolean delete(Component component);

    public Entity getType() {
        return this.TYPE;
    }

    /**
     * Devuelve el número de ciudadanos que hay bajo este componente
     *
     * @return número de ciudadanos
     */
    public abstract int size();

    /**
     * Busca el componente dado
     *
     * @param id el id del componente
     * @return el componente encontrado o null si no lo encontró
     */
    public abstract Component search(String id);

    /**
     * Busca el componente dado
     *
     * @param id el id del componente
     * @return el componente encontrado o null si no lo encontró
     */
    public abstract Component search(IdHandler id);

}
