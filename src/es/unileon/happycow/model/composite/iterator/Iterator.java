package es.unileon.happycow.model.composite.iterator;

public interface Iterator<E> extends java.util.Iterator<E> {
        @Override
	public E next();
        
        @Override
	public boolean hasNext();
}
