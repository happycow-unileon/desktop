package es.unileon.happycow.controller.admin;

import es.unileon.happycow.controller.Controller;
import es.unileon.happycow.gui.admin.Backup;
import java.io.File;
import javax.swing.JOptionPane;

/**
 * Controller of the backup panel
 * @author dorian
 */
public class BackupController extends Controller{
    /**
     * Panel
     */
    private final Backup panel;
    /**
     * Procedure of backup
     */
    private final es.unileon.happycow.procedures.Backup procedure;

    /**
     * Constructor
     * @param panel 
     */
    public BackupController(Backup panel) {
        this.panel = panel;
        procedure=new es.unileon.happycow.procedures.Backup();
    }
    
    /**
     * Export the data in the file
     * @param export folder to store the backup
     */
    public void export(File export){
        if(export!=null){
            if(export.exists() && export.isDirectory()){
                if(!procedure.backup(export)){
                     JOptionPane.showMessageDialog(null,
                            procedure.getEstado(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }else{
                    panel.setInfo("Operación realizada correctamente");
                }
            }else{
                panel.setInfo("Debe ser una carpeta y ha de existir");
            }
        }else{
            panel.setInfo("Seleccione primero una ruta");
        }
    }
    
    /**
     * Import the backup from the file
     * @param importBackup file backup
     */
    public void importBackup(File importBackup){
        if(importBackup!=null){
            if(importBackup.exists() && importBackup.isFile()){
                if(!procedure.recuperarBackup(importBackup)){
                    JOptionPane.showMessageDialog(null,
                            procedure.getEstado(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }else{
                    panel.setInfo("Operación realizada correctamente");
                }
            }else{
                panel.setInfo("Debe ser un fichero y ha de existir");
            }
        }else{
            panel.setInfo("Seleccione primero una ruta");
        }
    }

}
