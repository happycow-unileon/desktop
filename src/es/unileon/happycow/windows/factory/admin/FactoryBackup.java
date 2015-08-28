package es.unileon.happycow.windows.factory.admin;

import es.unileon.happycow.application.Parameters;
import es.unileon.happycow.controller.Controller;
import es.unileon.happycow.controller.admin.BackupController;
import es.unileon.happycow.windows.factory.IFactory;
import es.unileon.happycow.gui.admin.Backup;
import java.util.HashMap;
import javax.swing.JPanel;

/**
 *
 * @author dorian
 */
public class FactoryBackup extends IFactory{
    private Backup panel;
    private BackupController controller;

    public FactoryBackup(Parameters parameters) {
        super(parameters);
    }

    @Override
    public Controller getController() {
        if(controller==null){
            createController();
        }
        return controller;
    }

    @Override
    public JPanel getPanel() {
        if(panel==null){
            createPanel();
        }
        return panel;
    }

    @Override
    public void createController() {
        if(panel==null){
            createPanel();
        }
        
        if(controller==null){
            controller=new BackupController(panel);
            panel.setController(controller);
        }
    }

    @Override
    public void createPanel() {
        if(panel==null){
            panel=new Backup();
        }
        
        if(controller!=null){
            panel.setController(controller);
        }
    }

}
