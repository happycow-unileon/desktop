/*
 * 
 */
package es.unileon.happycow.model;

import es.unileon.happycow.database2.Database;
import es.unileon.happycow.handler.IdHandler;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author dorian
 */
public class ListEvaluations {
    private LinkedList<InformationEvaluation> list;
    private boolean listLoaded;
    private IdHandler farm;

    public ListEvaluations(IdHandler farm) {
        listLoaded=false;
        this.farm=farm;
    }

    public ListEvaluations(IdHandler farm, LinkedList<InformationEvaluation> list) {
        this.list = list;
        this.farm=farm;
        listLoaded=true;
    }
    
    public void addEvaluation(InformationEvaluation evaluation){
        if (!listLoaded) {
            getListEvaluation();
        }
        if (!getListEvaluation().contains(evaluation)) {
            list.add(evaluation);
        }
    }
    
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
    
    public LinkedList<InformationEvaluation> getListEvaluation() {
        if (!listLoaded) {
            list = Database.getInstance().getListEvaluations(farm);
        }
        listLoaded = true;
        return list;
    }
    
    public int size(){
        return getListEvaluation().size();
    }
    
}
