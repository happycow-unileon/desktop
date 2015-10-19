package es.unileon.happycow.controller.evaluation;

import es.unileon.happycow.application.Parameters;
import es.unileon.happycow.controller.Controller;
import es.unileon.happycow.database.Database;
import es.unileon.happycow.gui.evaluation.cow.PanelEvaluationCow;
import es.unileon.happycow.handler.Category;
import es.unileon.happycow.handler.IdCategory;
import es.unileon.happycow.handler.IdCriterion;
import es.unileon.happycow.handler.IdEvaluation;
import es.unileon.happycow.handler.IdFarm;
import es.unileon.happycow.handler.IdHandler;
import es.unileon.happycow.handler.IdUser;
import es.unileon.happycow.model.Cow;
import es.unileon.happycow.model.composite.Criterion;
import es.unileon.happycow.model.composite.Valoration;
import es.unileon.happycow.model.evaluation.EvaluationCowModel;
import es.unileon.happycow.strategy.EvaluationAlgorithm;
import es.unileon.happycow.windows.Window;
import java.awt.Color;
import java.io.File;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.JFileChooser;

/**
 * Controller of cow's evaluation
 *
 * @author dorian
 */
public class EvaluationCowController extends Controller implements IEvaluationCowController {

    /**
     * Panel
     */
    private PanelEvaluationCow panel;
    /**
     * Model
     */
    private EvaluationCowModel model;
    /**
     * Is new evaluation?
     */
    private boolean newEvaluation;

    /**
     * The combo of criterion (model-all criterions of a category) Position of
     * array, ordinal of enumeration category
     */
    private LinkedList<String>[] modelComboCriterion;

    public EvaluationCowController(PanelEvaluationCow panel) {
        this.panel = panel;
        this.newEvaluation = false;
        initComboCriterion();
    }

    /**
     * Inicializo el contenido del combo de criterios que se añade
     */
    private void initComboCriterion() {
        //prepare array
        int categories = Category.getArrayString().length;
        modelComboCriterion = new LinkedList[categories];
        for (int i = 0; i < categories; i++) {
            modelComboCriterion[i] = new LinkedList<>();
        }

        //fill data
        LinkedList<Criterion> lista = Database.getInstance().getListCriterion();
        for (Criterion criterion : lista) {
            IdCategory id = (IdCategory) criterion.getCategory();
            modelComboCriterion[Category.getEnum(id.getValue()).ordinal()]
                    .add(criterion.getName());
        }
    }

    /**
     * Write in panel the ponderations
     */
    private void setPonderationsInfo() {
        selectedCategoryPonderation();
    }

    @Override
    public void selectedCategoryPonderation() {
        IdHandler category = panel.getCategoryPonderationSelected();
        float ponderation = model.getWeighing(category);
        //set on panel the ponderation
        panel.setPonderationCategory(ponderation);

        //set the combo of criterions
        panel.setComboCriterionPonderation(
                modelComboCriterion[Category.getEnum(category.getValue()).ordinal()]);

        //set the ponderation of criterions
        selectedCriterionPonderation();
    }

    @Override
    public void selectedCriterionPonderation() {
        float ponderation = 1.0f;
        IdHandler selected = panel.getCriterionPonderationSelected();
        if (selected != null) {
            ponderation = model.getWeighing(selected);
        }
        //set the ponderation on panel
        panel.setPonderationCriterion(ponderation);
    }

    @Override
    public void onResume(Parameters parameters) {
        super.onResume(parameters);
        //set the information with a change of category
        changeCategory();

        //get the farm associated
        IdHandler farm = new IdFarm(parameters.getString("idFarm"));
        //is new evaluation?
        boolean isNew = parameters.getBoolean("isNew");
        
        newEvaluation = isNew;
        if (!isNew) {
            //rellenar los datos de evaluación
            IdHandler idEvaluation = new IdEvaluation(parameters.getString("idEvaluation"));
            model = new EvaluationCowModel(Database.getInstance().getEvaluation(idEvaluation));
            Database.getInstance().prepareFiles(idEvaluation);
        } else {
            IdHandler user = new IdUser(parameters.getString("user"));
            model = new EvaluationCowModel(farm, user);
        }

        if (model != null) {
            setNumberCows();
            addAll();
        }

        //pongo la ponderacion de la categoria y su color correcto
        setPonderationsInfo();

        //si hay vaca establecido pongo los datos
        selectedCow();
    }

    /**
     * Add all information from model
     */
    private void addAll() {
        for (int i = 0; i < model.getNumberCows(); i++) {
            panel.addCow();
        }
        setListFiles();
    }
    
    private void setListFiles(){
        List<String> files=Database.getInstance().getFileNames(model.getIdHandler());
        panel.setFileList(files);
    }

    /**
     * Set the minimum cows of evaluation
     */
    private void setNumberCows() {
        int cows = EvaluationAlgorithm.necesaryNumberOfCows(
                model.getInformation().getNumberCows());
        panel.setNumberCows(cows);
    }

    @Override
    public void setPonderationCriterion(IdHandler id, String ponderation) {
        if (isFloatUnit(ponderation)) {
            //seteo en el modelo la ponderación
            float pon = Float.valueOf(ponderation);
            model.setWeighing(id, pon);
            panel.setColorPonderationCriterion(Color.BLACK);
        } else {
            panel.setColorPonderationCriterion(Color.RED);
        }
    }

    @Override
    public void setCategoryPonderation(IdHandler id, String ponderation) {
        if (isFloatUnit(ponderation)) {
            //seteo en el modelo la ponderación
            float pon = Float.valueOf(ponderation);
            model.setWeighing(id, pon);
            panel.setColorPonderationCategory(Color.BLACK);
        } else {
            panel.setColorPonderationCategory(Color.RED);
        }
    }

    /**
     * Comprueba si es un número flotante
     * @param ponderation
     * @return true si es, false al contrario
     */
    private boolean isFloatUnit(String ponderation) {
        final String Digits = "(\\p{Digit}+)";
        final String HexDigits = "(\\p{XDigit}+)";
        // an exponent is 'e' or 'E' followed by an optionally 
        // signed decimal integer.
        final String Exp = "[eE][+-]?" + Digits;
        final String fpRegex
                = ("[\\x00-\\x20]*" + // Optional leading "whitespace"
                "[+-]?(" + // Optional sign character
                "NaN|" + // "NaN" string
                "Infinity|"
                + // "Infinity" string
                // A decimal floating-point string representing a finite positive
                // number without a leading sign has at most five basic pieces:
                // Digits . Digits ExponentPart FloatTypeSuffix
                // 
                // Since this method allows integer-only strings as input
                // in addition to strings of floating-point literals, the
                // two sub-patterns below are simplifications of the grammar
                // productions from the Java Language Specification, 2nd 
                // edition, section 3.10.2.
                // Digits ._opt Digits_opt ExponentPart_opt FloatTypeSuffix_opt
                "(((" + Digits + "(\\.)?(" + Digits + "?)(" + Exp + ")?)|"
                + // . Digits ExponentPart_opt FloatTypeSuffix_opt
                "(\\.(" + Digits + ")(" + Exp + ")?)|"
                + // Hexadecimal strings
                "(("
                + // 0[xX] HexDigits ._opt BinaryExponent FloatTypeSuffix_opt
                "(0[xX]" + HexDigits + "(\\.)?)|"
                + // 0[xX] HexDigits_opt . HexDigits BinaryExponent FloatTypeSuffix_opt
                "(0[xX]" + HexDigits + "?(\\.)" + HexDigits + ")"
                + ")[pP][+-]?" + Digits + "))"
                + "[fFdD]?))"
                + "[\\x00-\\x20]*");// Optional trailing "whitespace"

        return Pattern.matches(fpRegex, ponderation);
        //Double.valueOf(ponderation); // Will not throw NumberFormatException

    }

    @Override
    public void changeCategory() {
        panel.setComboCriterion(modelComboCriterion[
                panel.getSelectedCategory().ordinal()]);

    }

    /**
     * Recibo la orden de descargar el fichero, hago lo necesario
     *
     * @param id
     */
    @Override
    public void downloadFile(IdHandler id) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int seleccion = fileChooser.showSaveDialog(null);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File fichero = fileChooser.getSelectedFile();
            File downloaded = new File(fichero.getPath() + File.separator + id.getValue());
            byte[] data = Database.getInstance().getFile(model.getIdHandler(), id.getValue());
            Database.getInstance().saveFileToTheSystem(data, downloaded);
        }
    }

    /**
     * Recibo la orden de borrar un fichero y lo borro en el modelo tengo que
     * notificar a la vista que quite ese fichero de la lista
     *
     * @param id
     */
    @Override
    public void removeFile(IdHandler id) {
        //borrar fichero del modelo
        //TODO
        //borro dicho fichero en la vista
        panel.removeFile(id);
    }

    @Override
    public void addFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int seleccion = fileChooser.showOpenDialog(null);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File fichero = fileChooser.getSelectedFile();
            Database.getInstance().saveFile(model.getIdHandler(), fichero);
            //añado el fichero al gui
            panel.addFile(fichero.getName());
        }
    }

    @Override
    public void removeValoration(IdHandler val) {
        int index = panel.getSelectedCow();
        boolean result = false;
        String valoration = "";
        if (index >= 0) {
            Cow cow = model.getCow(index);
            valoration = panel.getSelectedValoration();

            String parts[] = valoration.split("/");
            String part = parts[1].split(":")[0];

            IdHandler criterion = new IdCriterion(part);
            System.out.println(part);
            Valoration target = cow.getValoration(criterion);

            //elimino la valoracion del modelo
            result = model.remove(target.getId());
        }

        //y lo quito de la gui también
        if (result) {
            panel.removeValoration(valoration);
        }
    }

    @Override
    public void addNewValoration() {
        if (panel.getSelectedCow() >= 0) {

            //compruebo que dicho criterio no esté evaluado
            IdHandler criterion = panel.getCriterion();
            IdHandler category = panel.getCategory();
            //añado el criterio al modelo por si acaso
            model.add(category, Database.getInstance().getCriterion(criterion));

            float valoration = panel.getValoration();
            Valoration val = new Valoration(model.nextIdValoration(), valoration);

            if (model.add(panel.getSelectedCow(), category, criterion, val)) {
                addValorationPanel(category, criterion, valoration);
            }
        }
    }

    /**
     * Add a valoration to the panel
     * @param category
     * @param criterion 
     * @param valoration 
     */
    private void addValorationPanel(IdHandler category, IdHandler criterion, float valoration) {
        panel.addValoration(category.getValue().concat("/")
                .concat(criterion.getValue()
                        .concat(": ").concat(Float.toString(valoration))));
    }

    /**
     * Add a valoration to model and to panel
     * @param val 
     */
    private void addValoration(Valoration val) {
        IdHandler criterion = val.getIdCriterion();
        IdHandler category = val.getIdCategory();
        float valoration = val.getNota();
        Valoration valor = new Valoration(criterion, valoration);

        model.add(panel.getSelectedCow(), category, criterion, valor);
        addValorationPanel(category, criterion, valoration);
    }

    @Override
    public void help(IdHandler id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void finishEvaluation() {
        //TODO
        if (newEvaluation) {
            if (Database.getInstance().saveEvaluation(model)) {
                controller.clearParameters();
                controller.addParameter("idFarm", model.getInformation().getIdFarm().toString());
                controller.addParameter("idEvaluation", model.getIdHandler().toString());
                controller.setState(Window.REPORT);
            }
        }  else {
            if(Database.getInstance().updateEvaluation(model)){
                controller.clearParameters();
                controller.addParameter("idFarm", model.getInformation().getIdFarm().toString());
                controller.addParameter("idEvaluation", model.getIdHandler().toString());
                controller.setState(Window.REPORT);
            }
        }
    }

    @Override
    public void addCow() {
        model.addCow();
        panel.addCow();
        //seleccionar la vaca
        panel.setSelectedCow(panel.getNumberCows() - 1);
        //mostrar valoraciones de la vaca
        panel.clearValorations();
    }

    @Override
    public void duplicateCow() {
        int index = panel.getSelectedCow();
        if (index >= 0) {
            Cow cow = model.getCow(index);
            //añado nueva vaca
            addCow();
            //añado las valoraciones
            Collection<Valoration> col = cow.getValorations();
            for (Valoration val : col) {
                addValoration(val);
            }
        }

    }

    @Override
    public void selectedCow() {
        int index = panel.getSelectedCow();
        if (index >= 0) {
            panel.clearValorations();
            Collection<Valoration> col = model.getCowValorations(index);
            for (Valoration valoration : col) {
                IdHandler category = valoration.getIdCategory();
                IdHandler criterion = valoration.getIdCriterion();
                float note = valoration.getNota();
                addValorationPanel(category, criterion, note);
            }
        }
    }
}
