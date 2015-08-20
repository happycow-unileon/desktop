package es.unileon.happycow.controller.evaluation;

import es.unileon.happycow.controller.IController;
import es.unileon.happycow.database.Database;
import es.unileon.happycow.gui.evaluation.InterfaceEvaluationCriterionPanel;
import es.unileon.happycow.handler.Category;
import es.unileon.happycow.handler.IdCategory;
import es.unileon.happycow.handler.IdCriterion;
import es.unileon.happycow.handler.IdHandler;
import es.unileon.happycow.model.composite.Criterion;
import es.unileon.happycow.model.composite.Valoration;
import es.unileon.happycow.model.evaluation.EvaluationCriterionModel;
import es.unileon.happycow.strategy.EvaluationAlgorithm;
import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;

/**
 *
 * @author dorian
 */
public class EvaluationCriterionController extends IController {

    private InterfaceEvaluationCriterionPanel panel;
    private final EvaluationCriterionModel model;
    private final boolean newEvaluation;
    
    /**
     * Caso 1, evaluación completamente nuevo, me llega o evaluación o información
     * Caso 2, evaluación a ver o modificar, me llega la evaluación entera
     * @param panel
     * @param model 
     * @param newEvaluation 
     */
    
    public EvaluationCriterionController(InterfaceEvaluationCriterionPanel panel, EvaluationCriterionModel model, boolean newEvaluation){
        this.newEvaluation=newEvaluation;
        this.panel=panel;
        this.model=model;
        
        setComboCriterion();
        setNumberCows();
        panel.deshabilitarValoraciones();
        configurePanel();
        if (model != null) {
            addAll();
        }
    }
    

    private void addAll() {
        for (Category category : Category.values()) {
            LinkedList<String> criterios = new LinkedList<>();
            for (Criterion criterion : model.getListCriterion(category)) {
                criterios.add(criterion.getName());
            }
            panel.setCriterion(category, criterios);
        }

        List<String> list = Database.getInstance().getFileNames(model.getIdHandler());
        panel.setListFiles(list);
    }

    private void configurePanel() {
        panel.setPonderationCategory(model.getWeighing(new IdCategory(panel.getSelectedCategory())));
    }

    private void setNumberCows() {
        int cows = EvaluationAlgorithm.necesaryNumberOfCows(model.getInformation().getNumberCows());
        panel.setNumberCow(cows);
    }

    private void setComboCriterion() {
        LinkedList<String> modelComboFood = new LinkedList<>();
        LinkedList<String> modelComboHealth = new LinkedList<>();
        LinkedList<String> modelComboBehaviour = new LinkedList<>();
        LinkedList<String> modelComboHouse = new LinkedList<>();

        LinkedList<Criterion> lista = Database.getInstance().getListCriterion();
        for (Criterion criterion : lista) {
            IdCategory id = (IdCategory) criterion.getCategory();
            switch (id.getCategory()) {
                case FOOD:
                    modelComboFood.add(criterion.getName());
                    break;
                case HEALTH:
                    modelComboHealth.add(criterion.getName());
                    break;
                case HOUSE:
                    modelComboHouse.add(criterion.getName());
                    break;
                case BEHAVIOUR:
                    modelComboBehaviour.add(criterion.getName());
                    break;
            }
        }

        panel.setComboCriterion(Category.FOOD, modelComboFood);
        panel.setComboCriterion(Category.HEALTH, modelComboHealth);
        panel.setComboCriterion(Category.HOUSE, modelComboHouse);
        panel.setComboCriterion(Category.BEHAVIOUR, modelComboBehaviour);
    }

    public void addValoration(float valoration) {
        String nombre = panel.getCriterion();
        if (nombre != null) {
            IdHandler criterion = new IdCriterion(nombre);
            IdHandler category = new IdCategory(panel.getSelectedCategory());
            Valoration val = new Valoration(valoration);
            model.add(category, criterion, val);
        }
    }

    public void addCriterion(String criterion) {
        IdHandler category = new IdCategory(panel.getSelectedCategory());
        Criterion cri = Database.getInstance().getCriterion(new IdCriterion(criterion));
        model.add(category, cri);
        panel.addCriterion(criterion);
    }

    public void removeCriterion(String criterion) {
        model.remove(new IdCriterion(criterion));
        panel.removeValorations();
        panel.deshabilitarValoraciones();
        panel.setHelp("");
    }

    public void removeValoration(String valoration) {
        String cortes[] = valoration.split(":");
        float nota = Integer.parseInt(cortes[cortes.length - 1].trim());
        LinkedList<Valoration> val = model.listOfCriterion(new IdCriterion(panel.getCriterion()));
        IdHandler idValoration = null;
        for (Iterator<Valoration> it = val.iterator(); it.hasNext() && idValoration == null;) {
            Valoration valoration1 = it.next();
            if (valoration1.getNota() == nota) {
                idValoration = valoration1.getId();
            }
        }
        if (idValoration != null) {
            model.remove(idValoration);
            IdCriterion idCri = new IdCriterion(panel.getCriterion());
            int i = 1;
            DefaultListModel list = new DefaultListModel();
            for (Valoration valo : model.listOfCriterion(idCri)) {
                String element = "Vaca " + String.valueOf(i) + " - Valoración: " + String.valueOf((int) valo.getNota());
                list.addElement(element);
                i++;
            }
            panel.setModelValoration(list);
        }
    }

    public void CriterionSelected(String criterion) {
        IdCriterion id = new IdCriterion(criterion);
        DefaultListModel list = new DefaultListModel();
        int i = 1;
        for (Valoration val : model.listOfCriterion(id)) {
            String element = "Vaca " + String.valueOf(i) + " - Valoración: " + String.valueOf((int) val.getNota());
            list.addElement(element);
            i++;
        }
        panel.setModelValoration(list);
        panel.habilitarValoraciones();
        panel.setHelp(model.getCriterion(id).getHelp());
        panel.setPonderationCriterion(model.getWeighing(id));
    }

    public void changeCategory() {
        IdHandler category = new IdCategory(panel.getSelectedCategory());
        panel.setPonderationCategory(model.getWeighing(category));
        if (panel.getCriterion() == null) {
            panel.deshabilitarValoraciones();
        }
    }

    public void setPonderationCategory(float ponderation) {
        IdHandler category = new IdCategory(panel.getSelectedCategory());
        model.setWeighing(category, ponderation);
        panel.setPonderationCategory(ponderation);
    }

    public void setPonderationCriterion(float ponderation) {
        IdHandler criterion = new IdCriterion(panel.getCriterion());
        model.setWeighing(criterion, ponderation);
        panel.setPonderationCriterion(ponderation);
    }

    public void saveValoration() {
        if (newEvaluation) {
            Database.getInstance().saveEvaluation(model);
        } else {
            Database.getInstance().updateEvaluation(model);
        }
        //TODO
//        JFrameController.getInstance().report(new Report(model), model.getInformation().getIdFarm());
    }

    public void criterionSaved() {
//        EvaluationAlgorithm calc = new AverageEvaluation(model);
//        calc.calcular();
//        String total = "Total: " + Float.toString(calc.getTotal()) + " * ";
//        String food = "Alimentación: " + Float.toString(calc.getFood()) + " * ";
//        String health = "Salud: " + Float.toString(calc.getHealth()) + " * ";
//        String house = "Refugio: " + Float.toString(calc.getHouse()) + " * ";
//        String behaviour = "Comportamiento: " + Float.toString(calc.getBehaviour()) + " * ";

//        panel.setInformation(total, food, health, house, behaviour);
    }
    
    

    public void addFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int seleccion = fileChooser.showOpenDialog(null);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File fichero = fileChooser.getSelectedFile();
            Database.getInstance().saveFile(model.getIdHandler(), fichero);
            panel.addFilePanel(fichero.getName());
        }
    }

    public void downloadFile(String name) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int seleccion = fileChooser.showSaveDialog(null);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File fichero = fileChooser.getSelectedFile();
            File downloaded=new File(fichero.getPath()+File.separator+name);
            System.out.println(downloaded.getAbsolutePath());
            byte[] data=Database.getInstance().getFile(model.getIdHandler(), name);
            Database.getInstance().saveFileToTheSystem(data, downloaded);
        }
    }
}
