package es.unileon.happycow.model.evaluation;

import es.unileon.happycow.database.Database;
import es.unileon.happycow.handler.Category;
import es.unileon.happycow.handler.IdCategory;
import es.unileon.happycow.handler.IdEvaluation;
import es.unileon.happycow.handler.IdHandler;
import es.unileon.happycow.handler.IdValoration;
import es.unileon.happycow.model.InformationEvaluation;
import es.unileon.happycow.model.composite.Criterion;
import es.unileon.happycow.model.composite.EvaluationCategory;
import es.unileon.happycow.model.composite.Valoration;
import es.unileon.happycow.model.composite.Component;
import es.unileon.happycow.model.composite.CompositeException;
import es.unileon.happycow.model.composite.Evaluation;
import es.unileon.happycow.model.composite.iterator.IteratorEvaluation;
import es.unileon.happycow.model.composite.iterator.IteratorException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dorian
 */
public class DefaultEvaluationModel implements IEvaluationModel {

    protected int numberValoration;
    protected Evaluation evaluation;

    /**
     * Indica si la evaluación está actualizada con todos sus datos
     * (valoraciones)
     *
     * @param idFarm
     */
    public DefaultEvaluationModel(IdHandler idFarm, IdHandler user) {
        InformationEvaluation info = new InformationEvaluation(
                new IdEvaluation(Database.getInstance().nextIdEvaluation()), idFarm,
                user,
                Database.getInstance().getNumberCow(idFarm));
        this.numberValoration = -1;
        try {
            evaluation = new Evaluation(info);
            evaluation.setParent(null);
            evaluation.add(new EvaluationCategory(new IdCategory(Category.BEHAVIOUR)));
            evaluation.add(new EvaluationCategory(new IdCategory(Category.FOOD)));
            evaluation.add(new EvaluationCategory(new IdCategory(Category.HEALTH)));
            evaluation.add(new EvaluationCategory(new IdCategory(Category.HOUSE)));
        } catch (CompositeException ex) {
            Logger.getLogger(DefaultEvaluationModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public DefaultEvaluationModel(Evaluation evaluation) {
        this.evaluation = evaluation;
        this.numberValoration = -1;

    }

    @Override
    public boolean add(IdHandler categoria, IdHandler criterio, Valoration valoration) {
        Component auxCategoria = evaluation.search(categoria);
        Component auxCriterio = evaluation.search(criterio);

        if (auxCategoria != null && auxCriterio != null) {
            try {
                auxCriterio.add(valoration);
            } catch (CompositeException ex) {
                return false;
            }
            return true;
        }

        return false;
    }

    @Override
    public boolean add(IdHandler categoria, Criterion criterion) {
        Component auxCategoria = evaluation.search(categoria);

        if (auxCategoria != null) {
            try {
                auxCategoria.add(criterion);
                return true;
            } catch (CompositeException ex) {
                return false;
            }
        }

        return false;
    }

    @Override
    public boolean add(IdHandler categoria) {
        Component auxCategory = null;
        auxCategory = evaluation.search(categoria);

        if (auxCategory != null) {
            try {
                evaluation.add(new EvaluationCategory(categoria)); //Creo y anhado la categoria
                return true;
            } catch (CompositeException ex) {
                return false;
            }
        }

        return false;
    }

    @Override
    public boolean add(EvaluationCategory categoria) {
        if (evaluation.search(categoria.getId()) != null) {
            try {
                evaluation.add(categoria);
                return true;
            } catch (CompositeException ex) {
                return false;
            }
        }

        return false;
    }

    /**
     * Metodo auxiliar, ya que se tiene que pasar la instancia exacta del
     * component a buscar.
     *
     * @param component
     * @return
     */
    private boolean delete(Component component) {
        return evaluation.remove(component);
    }

    @Override
    public boolean remove(IdHandler idHandler) {
        Component aux = evaluation.search(idHandler); //Buscamos de forma recursiva la instancia

        if (aux != null) { //Si existe
            return delete(aux); //la borramos
        }

        return false; //si no existe devolvemos false
    }

    @Override
    public boolean setWeighing(IdHandler idHandler, float weighing) {
        Component aux = evaluation.search(idHandler);

        if (aux != null) {
            aux.setWeighing(weighing);
            return true;
        }

        return false;
    }

    @Override
    public float getWeighing(IdHandler idHandler) {
        Component component = evaluation.search(idHandler);
        if (component != null) {
            return component.getWeighing();
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public LinkedList listOfCategory(IdHandler category) {
        LinkedList<Valoration> list = new LinkedList<>();
        Component c = evaluation.search(category);

        if (c != null) {
            for (Iterator<Component> iterator = new IteratorEvaluation(c); iterator.hasNext();) {
                Valoration next = (Valoration) iterator.next();
                list.add(next);
            }

        }

        return list;
    }

    @Override
    public LinkedList<Valoration> listOfCategory(Category category) {
        return listOfCategory(new IdCategory(category));
    }

    @Override
    public LinkedList<Valoration> listOfCriterion(IdHandler criterion) {
        Component component = evaluation.search(criterion);
        LinkedList<Valoration> list = new LinkedList<>();
        if (component != null) {
            for (Component valoration : component.getList()) {
                list.add((Valoration) valoration);
            }
        }

        return list;
    }

    @Override
    public LinkedList<Criterion> getListCriterion(Category category) {
        Component component = evaluation.search(new IdCategory(category));
        LinkedList<Criterion> list = new LinkedList<>();
        if (component != null) {
            for (Component criterion : component.getList()) {
                list.add((Criterion) criterion);
            }
        }

        return list;
    }

    @Override
    public LinkedList<Criterion> getListCriterion() {
        LinkedList<Criterion> list = new LinkedList<Criterion>();

        for (Component categoria : evaluation.getList()) {
            for (Component criterio : categoria.getList()) {
                list.add((Criterion) criterio);
            }
        }

        return list;
    }

    @Override
    public IdHandler getIdHandler() {
        return evaluation.getId();
    }

    @Override
    public InformationEvaluation getInformation() {
        return evaluation.getInformation();
    }

    @Override
    public Evaluation getComposite() {
        return evaluation;
    }

    @Override
    public IdHandler nextIdValoration() {
        numberValoration++;
        return new IdValoration(numberValoration);
    }

}
