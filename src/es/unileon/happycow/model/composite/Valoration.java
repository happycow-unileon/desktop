package es.unileon.happycow.model.composite;

import es.unileon.happycow.database.Database;
import es.unileon.happycow.handler.IdGeneric;
import es.unileon.happycow.handler.IdHandler;
import es.unileon.happycow.handler.IdValoration;
import es.unileon.happycow.model.composite.iterator.Iterator;
import es.unileon.happycow.model.composite.iterator.IteratorException;
import es.unileon.happycow.model.composite.table.Entity;

public class Valoration extends Component {

    private float nota;

    public Valoration(IdHandler idHandler, float nota) {
        this.nota = nota;
        //por defecto el peso es 1
        
        this.TYPE=Entity.VALORATION;
        id = idHandler;
        _weighing = 1;
    }

    public Valoration(float nota) {
        this((IdHandler) new IdValoration(Database.getInstance().nextIdValoration()),
                nota);

    }

    public IdHandler getIdCategory() {
        //parent es el criterio, getparent me da la categoria
        return parent.getParent().getId();
    }

    public IdHandler getIdEvaluation() {
        return this.root.getInformation().getIdEvaluation();
    }

    public IdHandler getIdCriterion() {
        return this.parent.getId();
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    @Override
    public boolean isLeaf() {
        return true;
    }


    @Override
    public Component search(IdHandler id) {
        if (this.id.compareTo(id) == 0) {
            return this;
        }

        return null;
    }


    @Override
    public Iterator<Component> iterator(String[] args) throws IteratorException {
        throw new IteratorException("Leaf");
    }

    @Override
    public boolean remove(Component delete) {
        return false;
    }

    @Override
    public int size() {
        return 1;
    }

    @Override
    public Component search(String id) {
        return this.search(new IdGeneric(id));
    }

    @Override
    public String show(int depth) {
        StringBuilder result=new StringBuilder();
        result.append(id.toString()).append(" --> ").append(depth);
        result.append("\n");
        return result.toString();
    }
    
    @Override
    public boolean delete(Component component) {
        boolean isRemoved = false;

        isRemoved = _list.remove(component);

        return isRemoved;
    }

}
