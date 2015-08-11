package es.unileon.happycow.model.composite2;

import es.unileon.happycow.handler.IdHandler;
import es.unileon.happycow.model.composite2.iterator.Aggregate;
import es.unileon.happycow.model.composite2.iterator.IteratorException;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author dorian
 */
public abstract class Component implements Aggregate {

    /**
     * id de la clase
     */
    protected IdHandler id;
    protected Entity entity;
    protected float weighing;
    protected Component parent;

    /**
     *
     * @param another el componente a añadir
     *
     * @throws CompositeException si hubo errores y no pudo añadirse
     */
    public void add(Component another) throws CompositeException {
        throw new CompositeException("No se puede añadir");
    }

    /**
     *
     * @param delete el componente a eliminar
     *
     * @return <code>true</code> si eliminó con éxito, <code>false</code> si al
     * contrario
     */
    public abstract boolean remove(Component delete);

    public IdHandler getId() {
        return id;
    }

    public float getWeighing() {
        return weighing;
    }

    public void setWeighing(float weighing) {
        this.weighing = weighing;
    }

    public Component getParent() {
        return parent;
    }

    public void setParent(Component parent) {
        this.parent = parent;
    }
    
    public Component getRoot() {
        return (parent==null)? this:parent.getRoot();
    }

    public Entity getEntity() {
        return this.entity;
    }
    
    public LinkedList<Component> getList() {
        return null;
    }
    

    /**
     * Devuelve el número de hijos
     *
     * @return número de hijos
     */
    public abstract int size();

    /**
     * Busca el componente dado
     *
     * @param id el id del componente
     * @return el componente encontrado o null si no lo encontró
     */
//	public abstract Component search(String id);
    /**
     * Busca el componente dado
     *
     * @param id el id del componente
     * @return el componente encontrado o null si no lo encontró
     */
    public abstract Component search(IdHandler id);

    /**
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    public abstract String toString();

    @Override
    public Iterator<Component> iterator(String... args) throws IteratorException {
        if (args != null) {
            if (args.length >= 0) {
                switch (args.length) {
//                    case 0:
//                        return new IteratorStack(this);
//                    case 1:
//                        return new IteratorCriterion(this, new IdGeneric(args[0]));
//                    case 2:
//                        return new IteratorClassify(this, new IdGeneric(args[0]), new IdGeneric(args[1]));
                    default:
                        return null;
//                        return new ConcreteIterator(this);
                }
            } else {
                throw new IteratorException();
            }
        } else {
            throw new IteratorException();
        }
    }
    
    
}
