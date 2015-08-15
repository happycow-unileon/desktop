package es.unileon.happycow.model.composite.iterator;

import es.unileon.happycow.model.composite.Component;
import es.unileon.happycow.model.composite.Entity;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 *
 * @author dorian
 */
public class IteratorCriterion implements Iterator<Component> {

    private Component root;

    public IteratorCriterion(Component root) {
        this.root = root;
    }


    @Override
    public boolean hasNext() {
        return false;

    }

    @Override
    public Component next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        
        return null;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
