package es.unileon.happycow.model.composite.iterator;

import es.unileon.happycow.model.composite.Component;
import java.util.LinkedList;
import java.util.Queue;

import java.util.Iterator;

public class ConcreteIterator implements Iterator<Component> {

    private Queue<Component> queue;
    private Component current;

    public ConcreteIterator(Component component) {
        queue = new LinkedList<Component>();
        queue.add(component);
    }

    //Recorre en anchura
    @Override
    public Component next() {
        current = queue.poll();

        if (current.size()>0) {
            Component children = current;
            for (Component c : children.getList()) {
                queue.add(c);
            }
        }

        return current;
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
