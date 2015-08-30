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
 *
 * @author dorian
 */
public class ExcelController extends Controller {

    /**
     * Panel concreto
     */
    private final PanelExcel panel;
    private DataExcel model;
    private IdHandler farmSelected;
    private IdHandler userSelected;

    public ExcelController(PanelExcel login) {
        this.panel = login;
    }

    public void export() {
        File file = askFile();
        if (file != null) {
            LinkedHashMap<User, LinkedList<Farm>> exportacion = model.getModel();
            for (User user : model.getUsers()) {
                DefaultListCheckModel listFarm = model.getFarms(user.getId());
                if (listFarm.getChecksCount() > 0) {
                    LinkedList<Farm> listado = exportacion.get(user);
                    processFarms(listado, listFarm);
                } else {
                    exportacion.remove(user);
                }
            }

            Excel exc = new Excel(file);
            if (exc.exportDataUsers(exportacion)) {
//                if (Database.getInstance().getUser().getRol() == Rol.VETERINARIO) {
//                    JFrameController.getInstance().comeBack();
//                }
                if (Database.getInstance().getUser().getRol() == Rol.VETERINARIO) {
                    controller.comeBack();
                }
            } else {
                JOptionPane.showMessageDialog(null,
                        "Error en la exportación", "Error", JOptionPane.OK_OPTION);
            }
        }
    }

    public void changeUser() {
        userSelected = new IdUser(panel.getUser());
        changeListFarms();
    }

    public void selectedAllEvaluations() {
        model.getEvaluations(farmSelected).checkAll();
    }

    public void selectedAllFarm() {
        model.getFarms(userSelected).checkAll();
    }

    public void selectedNoneEvaluations() {
        model.getEvaluations(farmSelected).removeChecks();
    }

    public void selectedNoneFarm() {
        model.getFarms(userSelected).removeChecks();
    }

    public void cancel() {
        controller.comeBack();
    }

    public void changeListEvaluation() {
        farmSelected = model.getFarm(panel.getFarm());
        panel.changeEvaluations(model.getEvaluations(farmSelected));
    }

    private void changeListFarms() {
        panel.changeFarms(model.getFarms(userSelected));
        panel.changeEvaluations(new DefaultListCheckModel());
    }

    private void processFarms(LinkedList<Farm> listado, DefaultListCheckModel listFarm) {
        LinkedList<Farm> list = (LinkedList<Farm>) listado.clone();
        for (Farm farm : list) {
            if (!listFarm.isChecked(farm.getFarmName())) {
                listado.remove(farm);
            } else {
                DefaultListCheckModel listEvaluation = model.getEvaluations(farm.getIdFarm());
                if (listEvaluation.getChecksCount() > 0) {
                    processEvaluations(farm.getListEvaluation(), listEvaluation);
                } else {
                    listado.remove(farm);
                }
            }
        }
    }

    private void processEvaluations(LinkedList<InformationEvaluation> listado,
            DefaultListCheckModel listEvaluation) {
        LinkedList<InformationEvaluation> list = (LinkedList<InformationEvaluation>) listado.clone();
        for (InformationEvaluation eval : list) {
            if (!listEvaluation.isChecked(eval.getFecha().toString())) {
                listado.remove(eval);
            }
        }
    }

    private File askFile() {
        File file = null;
        FileNameExtensionFilter filter;
        JFileChooser fileChooser;

        filter = new FileNameExtensionFilter("Ficheros excel", "xls");
        fileChooser = new JFileChooser();
        fileChooser.setFileFilter(filter);

        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int seleccion = fileChooser.showOpenDialog(null);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
            if (!file.getName().endsWith(".xls")) {
                file = new File(file.getAbsolutePath().concat(".xls"));
            }
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
        LinkedHashMap<User, LinkedList<Farm>> list = (LinkedHashMap<User, LinkedList<Farm>>)parameters.getObject("list");
        this.model = new DataExcel(list);
        
        LinkedList<User> users = new LinkedList<>(list.keySet());
        if (users.size() == 1) {
            userSelected = users.get(0).getId();
            panel.hideComboUser(true);
        }

        changeListFarms();
    }

}
