/*
 * 
 */
package es.unileon.happycow.controller.evaluation;

import es.unileon.happycow.application.Parameters;
import es.unileon.happycow.controller.Controller;
import es.unileon.happycow.database.Database;
import es.unileon.happycow.gui.evaluation.criterion.IconList;
import es.unileon.happycow.gui.evaluation.criterion.PanelEvaluationCriterion;
import es.unileon.happycow.handler.Category;
import es.unileon.happycow.handler.IdCategory;
import es.unileon.happycow.handler.IdCriterion;
import es.unileon.happycow.handler.IdEvaluation;
import es.unileon.happycow.handler.IdFarm;
import es.unileon.happycow.handler.IdHandler;
import es.unileon.happycow.handler.IdUser;
import es.unileon.happycow.model.composite.Component;
import es.unileon.happycow.model.composite.Criterion;
import es.unileon.happycow.model.composite.Valoration;
import es.unileon.happycow.model.evaluation.EvaluationCriterionModel;
import es.unileon.happycow.strategy.EvaluationAlgorithm;
import es.unileon.happycow.windows.Window;
import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;

/**
 *
 * @author dorian
 */
public class EvaluationCriterionController extends Controller implements IEvaluationCriterionController {

    private EvaluationCriterionModel model;
    private final PanelEvaluationCriterion gui;

    private Category actualCategory;
    private IdHandler actualCriterion;

    LinkedList<String>[] modelComboCriterion;

    private ArrayList<DefaultListModel<IconList>> modelCriterion;

    private boolean newEvaluation;
    private int numberCows;

    /**
     * Caso 1, evaluación completamente nuevo, me llega o evaluación o
     * información Caso 2, evaluación a ver o modificar, me llega la evaluación
     * entera
     *
     * @param panel
     */
    public EvaluationCriterionController(PanelEvaluationCriterion panel) {
        this.gui = panel;
        actualCategory = Category.FOOD;
        actualCriterion = null;

        initComboCriterion();

        modelCriterion = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            modelCriterion.add(new DefaultListModel<IconList>());
        }
    }

    @Override
    public void onResume(Parameters parameters) {
        super.onResume(parameters);
        
        IdHandler farm = new IdFarm(parameters.getString("idFarm"));
        boolean isNew = parameters.getBoolean("isNew");
        newEvaluation=isNew;
        if (!isNew) {
            //rellenar los datos de evaluación
            IdHandler idEvaluation = new IdEvaluation(parameters.getString("idEvaluation"));
            model = new EvaluationCriterionModel(Database.getInstance().getEvaluation(idEvaluation));
        } else {
            IdHandler user=new IdUser(parameters.getString("user"));
            model = new EvaluationCriterionModel(farm,user);
        }

        if (model != null) {
            numberCows = EvaluationAlgorithm.necesaryNumberOfCows(model.getInformation().getNumberCows());
            setNumberCows();
            addAll();
        }

        //establezco la categoría
        gui.setCategory(actualCategory);
        //cambio la lista del combo de criterios
        gui.setComboCriterion(modelComboCriterion[actualCategory.ordinal()]);
        //cambio la lista de criterios de la categoria
        gui.setModelCriterion(modelCriterion.get(actualCategory.ordinal()));

        //pongo la ponderacion de la categoria y su color correcto
        gui.setPonderationCategory(model.getWeighing(new IdCategory(actualCategory)));
        gui.setColorPonderationCategory(Color.BLACK);

        //si hay criterio establecido, establezco los datos
        if (actualCriterion == null) {
            gui.criterionInformationVisibility(false);
            gui.setValorationList(new LinkedList<Valoration>());

        } else {
            gui.criterionInformationVisibility(true);

            Criterion cri = model.getCriterion(actualCriterion);
            float ponderation = cri.getWeighing();
            boolean evaluated = gui.getCriterionEvaluated(actualCriterion);
            gui.setCriterionInformation(actualCriterion, ponderation, evaluated);

            gui.setValorationList(model.listOfCriterion(actualCriterion));
        }
        
        setTitleValorations();
    }

    private void addAll() {
        //relleno los modelos de criterios
        for (int i = 0; i < 4; i++) {
            LinkedList<Criterion> list=model.getListCriterion(Category.getEnum(i));
            DefaultListModel<IconList> modelList=modelCriterion.get(i);
            for (Iterator<Criterion> iterator = list.iterator(); iterator.hasNext();) {
                Criterion next = iterator.next();
                IconList icon=new IconList(next.getName());
                modelList.addElement(icon);
            }
        }
    }

    private void setNumberCows() {
        gui.setMinimunCows(numberCows);
    }
    
    private void setTitleValorations(){
        int number=0;
        if(actualCriterion!=null){
            number=model.listOfCriterion(actualCriterion).size();
        }
        gui.setTitleValorations(number);
    }

    /**
     * Inicializo el contenido del combo de criterios que se añade
     */
    private void initComboCriterion() {
        int categories = Category.getArrayString().length;
        modelComboCriterion = new LinkedList[categories];
        for (int i = 0; i < categories; i++) {
            modelComboCriterion[i] = new LinkedList<>();
        }

        LinkedList<Criterion> lista = Database.getInstance().getListCriterion();
        for (Criterion criterion : lista) {
            IdCategory id = (IdCategory) criterion.getCategory();
            modelComboCriterion[Category.getEnum(id.getValue()).ordinal()]
                    .add(criterion.getName());
        }
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
        //borro dicho fichero en la vista
        gui.removeFile(id);
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
            gui.addFile(fichero.getName());
        }
    }

    @Override
    public void copyValoration(IdHandler id) {
        //lo duplico en el modelo
        Component valoration = model.getComposite().search(id);
        Valoration toClone = (Valoration) valoration;
        Valoration clone = new Valoration(model.nextIdValoration(), toClone.getNota());
        model.add(toClone.getIdCategory(), toClone.getIdCriterion(), clone);

        //lo añado a la gui
        gui.addValoration(clone);
    }

    @Override
    public void removeValoration(IdHandler id) {
        //elimino la valoracion del modelo
        //pillo en que criterio y categoría está
        IdHandler idCategory = new IdCategory(actualCategory);
        IdHandler idCriterion = new IdCriterion(gui.getSelectedCriterion());
        model.removeValoration(idCategory, idCriterion, id);

        //y lo quito de la gui también
        gui.removeValoration(id);
    }

    /**
     * Me llega petición de añadir una nueva valoracion
     */
    @Override
    public void addNewValoration() {
        //compruebo que haya criterio seleccionado
        if (actualCriterion != null) {
            //lo añado al modelo
            IdHandler idCriterion = new IdCriterion(gui.getSelectedCriterion());
            Float note = gui.getSelectedValoration();
            Valoration val = new Valoration(model.nextIdValoration(), note);
            model.add(new IdCategory(actualCategory), idCriterion, val);
            //notifico a la interfaz de que tiene que añadir una valoración a la lista
            gui.addValoration(val);
        }
    }

    @Override
    public void criterionSelected() {
        if (actualCriterion == null
                || gui.getSelectedCriterion().compareTo(actualCriterion.getValue()) != 0) {
            gui.criterionInformationVisibility(true);
            actualCriterion = new IdCriterion(gui.getSelectedCriterion());
            setTitleValorations();

            //change the criterion information
            Criterion cri = model.getCriterion(actualCriterion);
            float note = cri.getWeighing();
            boolean evaluated = gui.getCriterionEvaluated(actualCriterion);
            gui.setCriterionInformation(cri.getId(), note, evaluated);

            gui.setColorPonderationCriterion(Color.BLACK);

            //change the list valorations
            gui.setValorationList(model.listOfCriterion(actualCriterion));
        }
    }

    @Override
    public void addCriterions() {
        //añado la lista al modelo, y los voy añadiendo al gui
        List<Object> list = gui.getCriterionsAddingSelected();
        for (Object criterion : list) {
            Criterion cri = Database.getInstance().getCriterion(new IdCriterion((String) criterion));
            model.add(new IdCategory(actualCategory), cri);
            gui.addCriterion(cri.getId());
        }
    }

    @Override
    public void removeCriterion(IdHandler idCriterion) {
        //lo borro del modelo
        model.remove(idCriterion);
        //y lo quito del gui
        gui.removeCriterion(idCriterion);
    }

    @Override
    public void help(IdHandler id) {
        //mostrar información
    }

    @Override
    public void evaluated(IdHandler id) {
        //es parte del gui, en el modelo no hace nada
        boolean result = gui.getCriterionEvaluated(id);
        gui.setCriterionEvaluated(actualCriterion, !result);
    }
    
     @Override
    public void categorySelected(Category category) {
        if (actualCategory.compareTo(category) != 0) {
            actualCategory = category;
            //pongo la categoria
            gui.setCategory(category);

            //oculto la ventana que muestra información del criterio seleccionado
            gui.criterionInformationVisibility(false);

            //vacio la selección de criterio
            actualCriterion = null;
            setTitleValorations();

            //cambio la lista del combo de criterios
            gui.setComboCriterion(modelComboCriterion[category.ordinal()]);
            //cambio la lista de criterios de la categoria
            gui.setModelCriterion(modelCriterion.get(category.ordinal()));

            //limpio el panel de valoraciones (con una lista vacía se limpia)
            gui.setValorationList(new LinkedList<Valoration>());

            //pongo la ponderacion de la categoria y su color correcto
            gui.setPonderationCategory(model.getWeighing(new IdCategory(category)));
            gui.setColorPonderationCategory(Color.BLACK);
        }
    }

    @Override
    public void finishEvaluation() {
        if(Database.getInstance().saveEvaluation(model)){
            controller.clearParameters();
            controller.addParameter("idFarm", model.getInformation().getIdFarm().toString());
            controller.addParameter("idEvaluation", model.getIdHandler().toString());
            controller.setState(Window.REPORT);
        }else{
            System.out.println("arggh");
        }
    }

    @Override
    public void setPonderationCriterion(IdHandler id, String ponderation) {
        if (isFloatUnit(ponderation)) {
            //seteo en el modelo la ponderación
            float pon = Float.valueOf(ponderation);
            model.setWeighing(id, pon);
            gui.setColorPonderationCriterion(Color.BLACK);
        } else {
            gui.setColorPonderationCriterion(Color.RED);
        }
    }

    @Override
    public void setCategoryPonderation(String ponderation) {
        if (isFloatUnit(ponderation)) {
            //seteo en el modelo la ponderación
            float pon = Float.valueOf(ponderation);
            model.setWeighing(new IdCategory(actualCategory), pon);
            gui.setColorPonderationCategory(Color.BLACK);
        } else {
            gui.setColorPonderationCategory(Color.RED);
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
}
