package es.unileon.happycow.database.prototype;

import es.unileon.happycow.model.composite.Criterion;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Class with pattern prototype
 * @author dorian
 */
public class CriterionPrototype {
    /**
     * List of criterions
     */
    private HashMap<String, Criterion> list;

    /**
     * Constructor
     */
    public CriterionPrototype() {
        list = new HashMap<>();
    }

   /**
    * Add a list of criterions
    * @param criterions 
    */
    public void add(Criterion... criterions) {
        for (Criterion criterion : criterions) {
            list.put(criterion.getName(), criterion);
        }
    }

    /**
     * Remove a list of criterions
     * @param criterions 
     */
    public void remove(Criterion... criterions) {
            for (Criterion criterion : criterions) {
                list.remove(criterion.getName());
            }
    }

    /**
     * Get the list of criterions
     * @return 
     */
    public LinkedList<Criterion> getList() {
        return new LinkedList<>(list.values());
    }

    /**
     * Get a clone of a criterion
     * @param name
     * @return 
     */
    public Criterion clone(String name) {
        Criterion criterion = null;
        try {
            criterion = (Criterion) list.get(name).clone();
        } catch (CloneNotSupportedException e) {
            System.out.println(e.toString());
        }catch(Exception e){
            System.out.println(e.toString());
        }
        return criterion;
    }

}
