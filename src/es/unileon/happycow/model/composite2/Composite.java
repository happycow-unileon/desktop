package es.unileon.happycow.model.composite2;

import es.unileon.happycow.handler.IdHandler;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author dorian
 */
public abstract class Composite extends Component {

    /**
     * Listado de componentes
     */
    protected final LinkedList<Component> list;

    /**
     * Constructor del composite
     */
    public Composite() {
        this.list = new LinkedList<>();
    }

    @Override
    public LinkedList<Component> getList() {
        return list;
    }

    @Override
    public void add(Component another) throws CompositeException {
        if (PermissionComposite.getInstance().canAdd(this.entity, another.getEntity())) {
            another.setParent(this);
            this.list.add(another);
        } else {
            throw new CompositeException("No se puede añadir.");
        }
    }

    @Override
    public boolean remove(Component delete) {
        boolean exite = false;
        if (this.list.contains(delete)) {
            exite = this.list.remove(delete);
        } else {
            for (Iterator<Component> remover = this.list.iterator(); remover.hasNext();) {
                Component comp = (Component) remover.next();
                if (comp.remove(delete)) {
                    exite = true;
                    break;
                }
            }

        }
        return exite;
    }

    @Override
    public int size() {
        return list.size();
    }

//    @Override
//    public Component search(String id) {
//        if (this.id.compareTo(id) == 0) {
//            return this;
//        } else {
//            for (Iterator<Component> searching = list.iterator(); searching.hasNext();) {
//                Component comp = (Component) searching.next();
//                comp = comp.search(id);
//                if (comp != null) {
//                    return comp;
//                }
//            }
//        }
//        return null;
//    }
    @Override
    public Component search(IdHandler id) {
        Component result = null;
        if (this.id.compareTo(id) == 0) {
            result = this;
        } else {
            for (Iterator<Component> searching = list.iterator(); searching.hasNext() && result == null;) {
                Component comp = (Component) searching.next();
                result = comp.search(id);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder information = new StringBuilder();
        for (Component c : this.list) {
            information.append(c.toString());
        }
        return information.toString();
    }

}
