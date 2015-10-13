package es.unileon.happycow.controller;

import es.unileon.happycow.application.Parameters;
import es.unileon.happycow.database.Database;
import es.unileon.happycow.gui.PanelExcel;
import es.unileon.happycow.handler.IdHandler;
import es.unileon.happycow.handler.IdUser;
import es.unileon.happycow.model.Farm;
import es.unileon.happycow.model.InformationEvaluation;
import es.unileon.happycow.model.Rol;
import es.unileon.happycow.model.User;
import es.unileon.happycow.procedures.DataExcel;
import es.unileon.happycow.procedures.Excel;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.japura.gui.model.DefaultListCheckModel;

/**
 * Controller of the excel panel (both admin and normal)
 * @author dorian
 */
public class ExcelController extends Controller {

    /**
     * Panel concreto
     */
    private final PanelExcel panel;
    /**
     * Data model
     */
    private DataExcel model;
    /**
     * the selected farm
     */
    private IdHandler farmSelected;
    /**
     * the selected user
     */
    private IdHandler userSelected;
    /**
     * Data loaded on panel
     */
    private boolean loaded;

    /**
     * Constructor
     * @param login 
     */
    public ExcelController(PanelExcel login) {
        this.panel = login;
        loaded = false;
    }

    /**
     * Export the selected data
     */
    public void export() {
        //ask for a file
        File file = askFile();
        if (file != null) {
            //get the list of users and farms selected
            LinkedHashMap<User, LinkedList<Farm>> exportacion = model.getModel();
            for (User user : model.getUsers()) {
                DefaultListCheckModel listFarm = model.getFarms(user.getId());
                if (listFarm.getChecksCount() > 0) {
                    LinkedList<Farm> listado = exportacion.get(user);
                    //store the farm in excel
                    processFarms(listado, listFarm);
                } else {
                    //user without farms, deleting from model...
                    exportacion.remove(user);
                }
            }

            //create the excel
            Excel exc = new Excel(file);
            //export the data
            if (exc.exportDataUsers(exportacion)) {
                //check the rol to know what to go now
                if (Database.getInstance().getUser().getRol() == Rol.VETERINARIO) {
                    controller.comeBack();
                }
            } else {
                JOptionPane.showMessageDialog(null,
                        "Error en la exportación", "Error", JOptionPane.OK_OPTION);
            }
        }
    }

    /**
     * New user selected on panel, change the list of farms
     */
    public void changeUser() {
        userSelected = new IdUser(panel.getUser());
        changeListFarms();
    }

    /**
     * Select all evaluations of the farm selected
     */
    public void selectedAllEvaluations() {
        model.getEvaluations(farmSelected).checkAll();
    }

    /**
     * Select all farms of the selected user
     */
    public void selectedAllFarm() {
        model.getFarms(userSelected).checkAll();
    }

    /**
     * Deselect all evaluations of the selected farm
     */
    public void selectedNoneEvaluations() {
        model.getEvaluations(farmSelected).removeChecks();
    }

    /**
     * Deselect all farms of the selected user
     */
    public void selectedNoneFarm() {
        model.getFarms(userSelected).removeChecks();
    }

    /**
     * Cancel the export and come back
     */
    public void cancel() {
        controller.comeBack();
    }

    /**
     * Get the farm selected and change the list of evaluations
     */
    public void changeListEvaluation() {
        farmSelected = model.getFarm(panel.getFarm());
        panel.changeEvaluations(model.getEvaluations(farmSelected));
    }

    /**
     * Change the list of farms to the farms of the selected user,
     * also change the list of evaluations
     */
    private void changeListFarms() {
        panel.changeFarms(model.getFarms(userSelected));
        panel.changeEvaluations(new DefaultListCheckModel());
    }

    /**
     * Process the farms, getting which one is selected and processing the selected
     * evaluation
     * @param listado list of farms
     * @param listFarm model of farms checked/unchecked
     */
    private void processFarms(LinkedList<Farm> listado, DefaultListCheckModel listFarm) {
        //clone the list
        LinkedList<Farm> list = (LinkedList<Farm>) listado.clone();
        for (Farm farm : list) {
            if (!listFarm.isChecked(farm.getFarmName())) {
                //farm not selected, remove from list
                listado.remove(farm);
            } else {
                //get the evaluations of the farm
                DefaultListCheckModel listEvaluation = model.getEvaluations(farm.getIdFarm());
                if (listEvaluation.getChecksCount() > 0) {
                    //if any evaluation selected, process them
                    processEvaluations(farm.getListEvaluation(), listEvaluation);
                } else {
                    //if no evaluation selected, remove the farm
                    listado.remove(farm);
                }
            }
        }
    }

    /**
     * Process the evaluations
     * @param listado list of evaluations
     * @param listEvaluation model of evaluations checked/uncheked
     */
    private void processEvaluations(LinkedList<InformationEvaluation> listado,
            DefaultListCheckModel listEvaluation) {
        
        LinkedList<InformationEvaluation> list = (LinkedList<InformationEvaluation>) listado.clone();
        for (InformationEvaluation eval : list) {
            if (!listEvaluation.isChecked(eval.getFecha().toString())) {
                listado.remove(eval);
            }
        }
    }

    /**
     * Ask the user for a file to store the excel
     * @return 
     */
    private File askFile() {
        File file = null;
        FileNameExtensionFilter filter;
        JFileChooser fileChooser;

        //create filter
        filter = new FileNameExtensionFilter("Ficheros excel", "xls");
        fileChooser = new JFileChooser();
        fileChooser.setFileFilter(filter);

        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        //open the dialog
        int seleccion = fileChooser.showOpenDialog(null);
        
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
            if (!file.getName().endsWith(".xls")) {
                file = new File(file.getAbsolutePath().concat(".xls"));
            }
            //if the file exists, ask the user
            if (file.exists()) {
                int confirm = JOptionPane.showConfirmDialog(
                        null,
                        "El fichero ya existe, ¿sobreescribir?",
                        "Confirmar sobreescritura",
                        JOptionPane.YES_NO_CANCEL_OPTION);
                if (JOptionPane.OK_OPTION != confirm) {
                    file = null;
                }
            }
        }
        return file;
    }

    @Override
    public void onResume(Parameters parameters) {
        LinkedHashMap<User, LinkedList<Farm>> list = (LinkedHashMap<User, LinkedList<Farm>>) parameters.getObject("list");
        setExcel(list, parameters.getBoolean("admin"));
    }

    /**
     * Update the panel
     */
    public void update() {
        if (!loaded) {
            loaded=true;
            //create the list of users and theirs lists of farms
            LinkedHashMap<User, LinkedList<Farm>> list = new LinkedHashMap<>();
            //for every user in the database...
            for (User user : Database.getInstance().getListUsers()) {
                //store his list of farms
                list.put(user, Database.getInstance().getListFarms(user.getId()));
            }

            setExcel(list, true);
        }
    }

    /**
     * Set the panel with data
     * @param list list of users and their farms
     * @param admin set if the panel is from admin or not (in admin mode, the combo of user
     * is showed)
     */
    private void setExcel(LinkedHashMap<User, LinkedList<Farm>> list, boolean admin) {
        this.model = new DataExcel(list);

        LinkedList<User> users = new LinkedList<>(list.keySet());
        panel.setComboUsers(users);
        userSelected = users.get(0).getId();
        panel.hideComboUser(!admin);

        changeListFarms();
    }

}
