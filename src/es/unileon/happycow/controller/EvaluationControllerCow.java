package es.unileon.happycow.controller;

import es.unileon.happycow.database.Database;
import es.unileon.happycow.gui.evaluation.PanelEvaluationCow;
import es.unileon.happycow.handler.Category;
import es.unileon.happycow.handler.IdCategory;
import es.unileon.happycow.handler.IdCow;
import es.unileon.happycow.handler.IdCriterion;
import es.unileon.happycow.handler.IdHandler;
import es.unileon.happycow.handler.IdValoration;
import es.unileon.happycow.model.Farm;
import es.unileon.happycow.model.composite.Component;
import es.unileon.happycow.model.composite.Criterion;
import es.unileon.happycow.model.composite.Valoration;
import es.unileon.happycow.model.facade.CowModel;
import es.unileon.happycow.model.facade.EvaluationModel;
import es.unileon.happycow.model.facade.InterfaceEvaluationModel;
import es.unileon.happycow.procedures.Report;
import es.unileon.happycow.strategy.EvaluationAlgorithm;
import java.util.LinkedList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;

/**
 *
 * @author dorian
 */
public class EvaluationControllerCow implements InterfaceController {

    private final CowModel model;
    private final PanelEvaluationCow panel;
    private final boolean newEvaluation;

    DefaultComboBoxModel<String> modelComboFood;
    DefaultComboBoxModel<String> modelComboHealth;
    DefaultComboBoxModel<String> modelComboBehaviour;
    DefaultComboBoxModel<String> modelComboHouse;

    public EvaluationControllerCow(JPanel panel, InterfaceEvaluationModel model) {
        boolean panelInstance = panel instanceof PanelEvaluationCow;
        boolean modelInstance = model instanceof CowModel;
        if (!modelInstance) {
            model = new CowModel(model);
            modelInstance = true;
        }
        if (!(panelInstance && modelInstance)) {
            throw new IllegalArgumentException();
        }

        this.panel = (PanelEvaluationCow) panel;
        this.model = (CowModel) model;
        newEvaluation = false;
        commonConstructor();
    }

    private void commonConstructor() {
        modelComboFood = new DefaultComboBoxModel<>();
        modelComboHealth = new DefaultComboBoxModel<>();
        modelComboBehaviour = new DefaultComboBoxModel<>();
        modelComboHouse = new DefaultComboBoxModel<>();
        createComboCriterion();

        int cows = EvaluationAlgorithm.necesaryNumberOfCows(model.getInformation().getNumberCows());
        panel.setNumberCow(cows);

        if (!newEvaluation) {
            fullCows();
        }
    }

    private void fullCows() {
        int cows = model.getNumberCows();
        DefaultComboBoxModel comboCow = new DefaultComboBoxModel();
        for (int i = 0; i < cows; i++) {
            comboCow.addElement("Vaca " + i);
        }

        panel.setComboCow(comboCow);
    }

    public EvaluationControllerCow(JPanel panel, IdHandler farm) {
        if (!(panel instanceof PanelEvaluationCow)) {
            throw new IllegalArgumentException();
        }
        this.panel = (PanelEvaluationCow) panel;
        this.model = new CowModel(new EvaluationModel(true, farm));
        this.newEvaluation = true;
        commonConstructor();
    }

    private void setNumberCows() {
        Farm farm = Database.getInstance().getFarm(model.getInformation().getIdFarm());
        int cows = EvaluationAlgorithm.necesaryNumberOfCows(model.getInformation().getNumberCows());
        panel.setNumberCow(cows);
    }

    private void createComboCriterion() {
        LinkedList<Criterion> lista = Database.getInstance().getListCriterion();
        for (Criterion criterion : lista) {
            IdCategory id = (IdCategory) criterion.getCategory();
            switch (id.getCategory()) {
                case FOOD:
                    modelComboFood.addElement(criterion.getName());
                    break;
                case HEALTH:
                    modelComboHealth.addElement(criterion.getName());
                    break;
                case HOUSE:
                    modelComboHouse.addElement(criterion.getName());
                    break;
                case BEHAVIOUR:
                    modelComboBehaviour.addElement(criterion.getName());
                    break;
            }
        }
    }

    public void addValoration(float valoration) {
        String nombre = panel.getSelectedCriterion();
        if (nombre != null) {
            IdHandler criterion = new IdCriterion(nombre);
            IdHandler category = new IdCategory(panel.getSelectedCategory());
            Valoration val = new Valoration(valoration);
            model.add(category, criterion, val);

            IdHandler cow = new IdCow(panel.getSelectedCow());
            model.addValoration(cow, category, Database.getInstance().getCriterion(criterion), val);

        }
    }

    public void removeValoration(String valoration) {
//        String cortes[]=valoration.split(":");
//        float nota=Integer.parseInt(cortes[cortes.length-1].trim());
//        LinkedList<Valoration> val=model.listOfCriterion(new IdCriterion(panel.getSelectedCriterion()));
//        IdHandler idValoration=null;
//        for (Iterator<Valoration> it = val.iterator(); it.hasNext() && idValoration==null;) {
//            Valoration valoration1 = it.next();
//            if(valoration1.getNota()==nota){
//                idValoration=valoration1.getId();
//            }
//        }
//        if(idValoration!=null){
//            model.remove(idValoration);
//            IdCriterion idCri = new IdCriterion(panel.getSelectedCriterion());
//            int i = 1;
//            DefaultListModel list = new DefaultListModel();
//            for (Valoration valo : model.listOfCriterion(idCri)) {
//                String element = "Vaca " + String.valueOf(i) + " - Valoración: " + String.valueOf((int) valo.getNota());
//                list.addElement(element);
//                i++;
//            }
//            panel.setModelValoration(list);
//        }
    }

    public void CriterionSelected(String criterion) {
        IdCriterion id = new IdCriterion(criterion);
//        DefaultListModel list = new DefaultListModel();
//        int i = 1;
//        for (Valoration val : model.listOfCriterion(id)) {
//            String element = "Vaca " + String.valueOf(i) + " - Valoración: " + String.valueOf((int) val.getNota());
//            list.addElement(element);
//            i++;
//        }

//        panel.setModelValoration(list);
        panel.habilitarValoraciones();
//        panel.setHelp(model.getCriterion(id).getHelp());
        panel.setPonderationCriterion(model.getWeighing(id));
    }

    public void changeCategory() {
        IdHandler category = new IdCategory(panel.getSelectedCategory());
        panel.setPonderationCategory(model.getWeighing(category));
        panel.deshabilitarValoraciones();
        switch (panel.getSelectedCategory()) {
            case BEHAVIOUR:
                panel.setComboCriterion(modelComboBehaviour);
                break;
            case FOOD:
                panel.setComboCriterion(modelComboFood);
                break;
            case HEALTH:
                panel.setComboCriterion(modelComboHealth);
                break;
            case HOUSE:
                panel.setComboCriterion(modelComboHouse);
                break;
        }
    }

    public void setPonderationCategory(float ponderation) {
        IdHandler category = new IdCategory(panel.getSelectedCategory());
        model.setWeighing(category, ponderation);
        panel.setPonderationCategory(ponderation);
    }

    public void setPonderationCriterion(float ponderation) {
        IdHandler criterion = new IdCriterion(panel.getSelectedCriterion());
        model.setWeighing(criterion, ponderation);
        panel.setPonderationCriterion(ponderation);
    }

    public void saveValoration() {
        if (newEvaluation) {
            Database.getInstance().saveEvaluation(model);
        } else {
            Database.getInstance().saveModifiedEvaluation(model);
        }
        JFrameController.getInstance().report(new Report(model), model.getInformation().getIdFarm());
    }

    public void cancel() {
        JFrameController.getInstance().comeBack();
    }

    public void duplicateCow() {
        IdHandler cow = new IdCow(panel.getSelectedCow());
        IdHandler newCow = new IdCow(model.getNumberCows() + 1);
        List<Valoration> list = model.getValorations(cow);

        for (Valoration valorationToCopy : list) {
            Component criterion = valorationToCopy.getParent();
            IdHandler idCriterion = criterion.getId();
            IdHandler idCategory = criterion.getParent().getId();

            Valoration target = new Valoration(
                    new IdValoration(Database.getInstance().nextIdValoration()),
                    valorationToCopy.getNota());

            model.addValoration(newCow,
                    idCategory,
                    Database.getInstance().getCriterion(idCriterion),
                    target);

        }
    }

    public void saveEvaluation() {
        if (newEvaluation) {
            Database.getInstance().saveEvaluation(model);
        } else {
            Database.getInstance().saveModifiedEvaluation(model);
        }
        JFrameController.getInstance().report(new Report(model), model.getInformation().getIdFarm());
    }

    public void changeViewCow() {
        IdHandler id = new IdCow(panel.getCowViewed());
        DefaultComboBoxModel list = new DefaultComboBoxModel();
        for (Valoration valoration : model.getValorations(id)) {
            StringBuilder string = new StringBuilder();
            string.append(model.getValoration(valoration.getId()).getParent().getId().toString());
            string.append(": ");
            string.append(Float.toString(valoration.getNota()));

            list.addElement(string.toString());
        }
    }
}
