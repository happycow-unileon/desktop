package es.unileon.happycow.controller.admin;

import es.unileon.happycow.controller.InterfaceController;
import es.unileon.happycow.gui.admin.Backup;
import java.io.File;
import javax.swing.JOptionPane;

/**
 *
 * @author dorian
 */
public class BackupController implements InterfaceController{
    private final Backup panel;
    private final es.unileon.happycow.procedures.Backup procedure;

    public BackupController(Backup panel) {
        this.panel = panel;
        procedure=new es.unileon.happycow.procedures.Backup();
    }
    
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
