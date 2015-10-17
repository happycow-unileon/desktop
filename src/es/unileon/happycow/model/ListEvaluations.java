package es.unileon.happycow.model;

import es.unileon.happycow.database.Database;
import es.unileon.happycow.handler.IdHandler;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * List of information of evaluations
 * @author dorian
 */
public class ListEvaluations {
    /**
     * List information evaluation
     */
    private LinkedList<InformationEvaluation> list;
    /**
     * Has the list loaded?
     */
    private boolean listLoaded;
    /**
     * Id of associated farm
     */
    private IdHandler farm;

    public ListEvaluations(IdHandler farm) {
        listLoaded=false;
        this.farm=farm;
    }

    public ListEvaluations(IdHandler farm, LinkedList<InformationEvaluation> list) {
        this.list = list;
        this.farm=farm;
        listLoaded=true;
        Collections.sort(this.list);
    }
    
    /**
     * Add an evaluation's information
     * @param evaluation 
     */
    public void addEvaluation(InformationEvaluation evaluation){
        if (!listLoaded) {
            getListEvaluation();
        }
        if (!getListEvaluation().contains(evaluation)) {
            list.add(evaluation);
        }
    }
    
    /**
     * Remove an evaluation
     * @param evaluation 
     */
    public void removeEvaluation(IdHandler evaluation) {
        InformationEvaluation target = null;
        if (!listLoaded) {
            getListEvaluation();
        }
        for (Iterator<InformationEvaluation> it = list.iterator(); it.hasNext() && target == null;) {
            InformationEvaluation eval = it.next();
            if (eval.getIdEvaluation().compareTo(evaluation) == 0) {
                target = eval;
            }
        }
        list.remove(target);
    }

    /**
     * Get information of given evaluation id
     * @param evaluation
     * @return 
     */
    public InformationEvaluation getEvaluation(IdHandler evaluation) {
        int target = -1;
        if (!listLoaded) {
            getListEvaluation();
        }
        int index = 0;
        for (Iterator<InformationEvaluation> it = list.iterator(); it.hasNext() && target <= -1;) {
            InformationEvaluation eval = it.next();
            if (eval.getIdEvaluation().compareTo(evaluation) == 0) {
                target = index;
            }
            index++;
        }
        if (target >= 0) {
            return list.get(target);
        } else {
            return null;
        }
    }
    
    /**
     * Get the list of evaluations' info
     * @return 
     */
    public LinkedList<InformationEvaluation> getListEvaluation() {
        if (!listLoaded) {
            list = Database.getInstance().getListEvaluations(farm);
            Collections.sort(this.list);
        }
        listLoaded = true;
        return list;
    }
    
    /**
     * Number of evaluations
     * @return 
     */
    public int size(){
        return getListEvaluation().size();
    }
    
}
