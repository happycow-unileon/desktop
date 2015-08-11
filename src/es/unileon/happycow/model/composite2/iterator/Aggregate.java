package es.unileon.happycow.model.composite2.iterator;

import es.unileon.happycow.model.composite2.iterator.*;
import es.unileon.happycow.model.composite2.Component;

public interface Aggregate {
	public java.util.Iterator<Component> iterator(String... args) throws IteratorException;
}
