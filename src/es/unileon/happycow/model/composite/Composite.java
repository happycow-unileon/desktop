package es.unileon.happycow.model.composite;

import es.unileon.happycow.handler.IdGeneric;
import es.unileon.happycow.handler.IdHandler;
import es.unileon.happycow.model.composite.iterator.ConcreteIterator;
import es.unileon.happycow.model.composite.iterator.IteratorClassify;
import es.unileon.happycow.model.composite.iterator.IteratorCriterion;
import es.unileon.happycow.model.composite.iterator.IteratorException;
import es.unileon.happycow.model.composite.iterator.IteratorStack;
import es.unileon.happycow.model.composite.table.PermissionComposite;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author amdiaz
 */
public abstract class Composite extends Component {
    
    @Override
    public boolean isLeaf() {
        return false;
    }

    @Override
    public void add(Component component) throws CompositeException{
        if (component != null) {
            if (this.search(component.getId()) == null) {
                if (PermissionComposite.getInstance().canAdd(getType(), component.getType())) {
                    component.setParent(this);
                    component.setRoot(root);
                    _list.add(component);
                }else{
                    throw new CompositeException("No se puede añadir.");
                }
            }
        }

    }

    public boolean remove(Component component) {
        return _list.remove(component);
    }

    @Override
    public boolean delete(Component component) {
        boolean isRemoved = false;

        isRemoved = _list.remove(component);

        if (!isRemoved) {
            for (int i = 0; i < _list.size() && !isRemoved; i++) {
                if (!_list.get(i).isLeaf()) {
                    Composite aux = (Composite) _list.get(i);
                    isRemoved = aux.delete(component);
                }
            }

        }

        return isRemoved;
    }

    @Override
    public String show(int depth) {
        StringBuilder result=new StringBuilder();
        result.append(id.toString()).append(" --> ").append(depth);
        result.append("\n");
        for (int i = 0; i < _list.size(); i++) {
            result.append(_list.get(i).show(depth + 1));
        }
        return result.toString();
    }
    
    @Override
	public int size() {
		return _list.size();
	}

    @Override
    public Component search(IdHandler id) {
        Component find = null;
        if (this.id.compareTo(id) == 0) {
            find = this;
        } else {
            for (int i = 0; i < _list.size() && find == null; i++) {
                find = _list.get(i).search(id);
            }
        }

        return find;
    }
    
    public Component search(String id){
        return search(new IdGeneric(id));
    }

    // Es el encargado de instanciar uno u otro iterador concreto
    @Override
    public Iterator<Component> iterator(String... args) throws IteratorException {
        if (args != null) {
            if (args.length >= 0) {
                switch (args.length) {
                    case 0:
                        return new IteratorStack(this);
                    case 1:
                        return new IteratorCriterion(this, new IdGeneric(args[0]));
                    case 2:
                        return new IteratorClassify(this, new IdGeneric(args[0]), new IdGeneric(args[1]));
                    default:
                        return new ConcreteIterator(this);
                }
            } else {
                throw new IteratorException();
            }
        } else {
            throw new IteratorException();
        }

    }

    public LinkedList<Component> getChildren() {
        return _list;
    }
    
}
