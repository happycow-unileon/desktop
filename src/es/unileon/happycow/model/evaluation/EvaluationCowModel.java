package es.unileon.happycow.model.evaluation;

import es.unileon.happycow.handler.IdHandler;
import es.unileon.happycow.model.Cow;
import es.unileon.happycow.model.composite.Criterion;
import es.unileon.happycow.model.composite.Evaluation;
import es.unileon.happycow.model.composite.Valoration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author dorian
 */
public class EvaluationCowModel extends DefaultEvaluationModel {

    private ArrayList<Cow> cows;

    public EvaluationCowModel(IdHandler idFarm, IdHandler user) {
        super(idFarm, user);
        cows = new ArrayList<>();
    }

    public EvaluationCowModel(Evaluation evaluation) {
        super(evaluation);
        cows=new ArrayList<>();
        
        //add all to cows
        LinkedList<Criterion> list = getListCriterion();
        for (Iterator<Criterion> iterator = list.iterator(); iterator.hasNext();) {
            Criterion next = iterator.next();
            LinkedList<Valoration> vals = super.listOfCriterion(next.getId());
            
            for (int i = cows.size(); i < vals.size(); i++) {
                cows.add(new Cow());
            }

            int indexCow = 0;
            for (Valoration val : vals) {
                cows.get(indexCow).addValoration(next.getId(), val);
                indexCow++;
            }
        }
    }
    
    public int getNumberCows(){
        return cows.size();
    }

//    public int getValorationSize(IdHandler criterion) {
//        Component component = evaluation.search(criterion);
//        int result = 0;
//        if (component != null) {
//            component.getList().size();
//        }
//        return result;
//    }

    public boolean add(int cow, IdHandler categoria, IdHandler criterio, Valoration valoration) {
        if (cows.get(cow).addValoration(criterio, valoration)) {
            return super.add(categoria, criterio, valoration);
        }
        return false;
    }

    public void addCow() {
        cows.add(new Cow());
    }

    public Collection<Valoration> getCowValorations(int index) {
        return cows.get(index).getValorations();
    }

    public Cow getCow(int index) {
        return cows.get(index);
    }
}
