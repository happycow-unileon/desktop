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
import java.util.regex.Pattern;
import javax.swing.JFileChooser;

/**
 *
 * @author dorian
 */
public class EvaluationCowController extends Controller implements IEvaluationCowController {

    private PanelEvaluationCow panel;
    private EvaluationCowModel model;
    private boolean newEvaluation;

    private LinkedList<String> modelComboFood;
    private LinkedList<String> modelComboHealth;
    private LinkedList<String> modelComboBehaviour;
    private LinkedList<String> modelComboHouse;

    public EvaluationCowController(PanelEvaluationCow panel) {
        this.panel = panel;
        this.newEvaluation = false;
        setCombos();
    }

    private void setCombos() {
        modelComboBehaviour = new LinkedList<>();
        modelComboFood = new LinkedList<>();
        modelComboHealth = new LinkedList<>();
        modelComboHouse = new LinkedList<>();

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
    }

    private void setPonderationsInfo() {
        selectedCategoryPonderation();
    }

    @Override
    public void selectedCategoryPonderation() {
        IdHandler category = panel.getCategoryPonderationSelected();
        float ponderation = model.getWeighing(category);
        panel.setPonderationCategory(ponderation);

        switch (Category.getEnum(category.getValue())) {
            case BEHAVIOUR:
                panel.setComboCriterionPonderation(modelComboBehaviour);
                break;
            case FOOD:
                panel.setComboCriterionPonderation(modelComboFood);
                break;
            case HEALTH:
                panel.setComboCriterionPonderation(modelComboHealth);
                break;
            case HOUSE:
                panel.setComboCriterionPonderation(modelComboHouse);
                break;
        }
        selectedCriterionPonderation();
    }

    @Override
    public void selectedCriterionPonderation() {
        float ponderation = 1.0f;
        IdHandler selected = panel.getCriterionPonderationSelected();
        if (selected != null) {
            ponderation = model.getWeighing(selected);
        }
        panel.setPonderationCriterion(ponderation);
    }

    @Override
    public void onResume(Parameters parameters) {
        super.onResume(parameters);
        changeCategory();

        IdHandler farm = new IdFarm(parameters.getString("idFarm"));
        boolean isNew = parameters.getBoolean("isNew");
        newEvaluation = isNew;
        if (!isNew) {
            //rellenar los datos de evaluación
            IdHandler idEvaluation = new IdEvaluation(parameters.getString("idEvaluation"));
            model = new EvaluationCowModel(Database.getInstance().getEvaluation(idEvaluation));
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

    private void addAll() {
        for (int i = 0; i < model.getNumberCows(); i++) {
            panel.addCow();
        }
    }

    private void setNumberCows() {
        int cows = EvaluationAlgorithm.necesaryNumberOfCows(model.getInformation().getNumberCows());
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

//    public void saveValoration() {
////        if (newEvaluation) {
////            Database.getInstance().saveEvaluation(model);
////        } else {
////            Database.getInstance().saveModifiedEvaluation(model);
////        }
////        JFrameController.getInstance().report(new Report(model), model.getInformation().getIdFarm());
//    }
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
            Valoration val = new Valoration(criterion, valoration);

            if (model.add(panel.getSelectedCow(), category, criterion, val)) {
                addValorationPanel(category, criterion, valoration);
            }
        }
    }

    private void addValorationPanel(IdHandler category, IdHandler criterion, float valoration) {
        panel.addValoration(category.getValue().concat("/")
                .concat(criterion.getValue()
                        .concat(": ").concat(Float.toString(valoration))));
    }

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
        if (newEvaluation) {
            if (Database.getInstance().saveEvaluation(model)) {
                controller.clearParameters();
                controller.addParameter("idFarm", model.getInformation().getIdFarm().toString());
                controller.addParameter("idEvaluation", model.getIdHandler().toString());
                controller.setState(Window.REPORT);
            }
        } else {

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
