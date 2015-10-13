/*
 * 
 */
package es.unileon.happycow.model;

import es.unileon.happycow.handler.IdHandler;
import es.unileon.happycow.model.composite.Valoration;
import java.util.Collection;
import java.util.HashMap;

/**
 *
 * @author dorian
 */
public class Cow implements Cloneable {

    HashMap<IdHandler, Valoration> criterionsEvaluated;

    public Cow() {
        criterionsEvaluated = new HashMap<>();
    }

    public boolean addValoration(IdHandler criterion, Valoration val) {
        if (!criterionsEvaluated.containsKey(criterion)) {
            criterionsEvaluated.put(criterion, val);
            return true;
        }

        return false;
    }

    public Valoration getValoration(IdHandler criterion) {
        Valoration result= criterionsEvaluated.get(criterion);
        return result;
    }

    public Collection<Valoration> getValorations() {
        return criterionsEvaluated.values();
    }

    private void setHashMap(HashMap<IdHandler, Valoration> hash) {
        this.criterionsEvaluated = hash;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Cow cloned = (Cow) super.clone();
        cloned.setHashMap((HashMap<IdHandler, Valoration>) criterionsEvaluated.clone());
        return cloned;
    }
}
