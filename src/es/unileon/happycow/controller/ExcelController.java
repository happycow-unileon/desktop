package es.unileon.happycow.controller;

import es.unileon.happycow.database.Database;
import es.unileon.happycow.gui.InterfaceExcelPanel;
import es.unileon.happycow.gui.JFrameApplication;
import es.unileon.happycow.gui.PanelExcel;
import es.unileon.happycow.handler.IdHandler;
import es.unileon.happycow.handler.IdUser;
import es.unileon.happycow.model.Farm;
import es.unileon.happycow.model.Rol;
import es.unileon.happycow.model.User;
import es.unileon.happycow.model.excel.DataExcel;
import es.unileon.happycow.model.facade.InterfaceEvaluationModel;
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
public class ExcelController implements InterfaceController {

    private final InterfaceExcelPanel panel;
    private final JFrameController father;
    private final DataExcel model;
    private IdHandler farmSelected;

    public ExcelController(PanelExcel panel, LinkedHashMap<User, LinkedList<Farm>> list) {
        this.panel = panel;
        this.father = JFrameController.getInstance();
        this.model = new DataExcel(list);

        changeListFarms();
    }

    public void changeListEvaluation() {
        System.out.println(panel.getFarm());
        farmSelected = model.getFarm(panel.getFarm());
        System.out.println(farmSelected.toString());
        System.out.println(model.getEvaluations(farmSelected));
        panel.changeEvaluations(model.getEvaluations(farmSelected));
    }

    public void changeListFarms() {
        panel.changeFarms(model.getFarms(new IdUser(panel.getUser())));
        panel.changeEvaluations(new DefaultListCheckModel());
    }

    public void changeUser() {
        changeListFarms();
    }
    
    private File askFile(){
        File file=null;
        FileNameExtensionFilter filter;
        JFileChooser fileChooser;
        
        filter = new FileNameExtensionFilter("Ficheros excel", "xls");
        fileChooser = new JFileChooser();
        fileChooser.setFileFilter(filter);
        
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int seleccion = fileChooser.showOpenDialog(JFrameApplication.getInstance());
        
        if(seleccion==JFileChooser.APPROVE_OPTION){
            file=fileChooser.getSelectedFile();
            if(!file.getName().endsWith(".xls")){
                    file=new File(file.getAbsolutePath().concat(".xls"));
            }
            if(file.exists()){
                int confirm=JOptionPane.showConfirmDialog(
                        JFrameApplication.getInstance(), 
                        "El fichero ya existe, ¿sobreescribir?", 
                        "Confirmar sobreescritura", 
                        JOptionPane.YES_NO_CANCEL_OPTION);
                if(JOptionPane.OK_OPTION != confirm){
                    file=null;
                }
            }
        }
        return file;
    }

    public void export() {
        File file=askFile();
            if(file!=null){
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
                if(exc.exportDataUsers(exportacion)){
                    if(Database.getInstance().getUser().getRol()==Rol.VETERINARIO){
                        JFrameController.getInstance().comeBack();
                    }
                }else{
                    JOptionPane.showMessageDialog(JFrameApplication.getInstance(),
                            "Error en la exportación", "Error", JOptionPane.OK_OPTION);
                }
        }
    }

    private void processFarms(LinkedList<Farm> listado, DefaultListCheckModel listFarm) {
        LinkedList<Farm> list=(LinkedList<Farm>)listado.clone();
        for (Farm farm : list) {
            if (!listFarm.isChecked(farm.getFarmName())) {
                listado.remove(farm);
            }else{
                DefaultListCheckModel listEvaluation=model.getEvaluations(farm.getIdFarm());
                if(listEvaluation.getChecksCount()>0){
                    processEvaluations(farm.getListEvaluation(), listEvaluation);
                }else{
                    listado.remove(farm);
                }
            }
        }
    }
    
    private void processEvaluations(LinkedList<InterfaceEvaluationModel> listado,
            DefaultListCheckModel listEvaluation){
        LinkedList<InterfaceEvaluationModel> list=(LinkedList<InterfaceEvaluationModel>)listado.clone();
        for (InterfaceEvaluationModel eval : list) {
            if(!listEvaluation.isChecked(eval.getInformation().getFecha().toString())){
                listado.remove(eval);
            }
        }
    }

    public void selectedAllFarm() {
        model.getFarms(new IdUser(panel.getUser())).checkAll();
    }

    public void selectedAllEvaluations() {
        model.getEvaluations(farmSelected).checkAll();
    }

    public void selectedNoneFarm() {
        model.getFarms(new IdUser(panel.getUser())).removeChecks();
    }

    public void selectedNoneEvaluations() {
        model.getEvaluations(farmSelected).removeChecks();
    }

    public void cancel() {
        father.comeBack();
    }

}
