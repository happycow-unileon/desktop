package es.unileon.happycow.model.composite.iterator;

import es.unileon.happycow.model.composite.Component;

public interface Aggregate {
	public java.util.Iterator<Component> iterator(String... args) throws IteratorException;
}
