/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.unileon.happycow.abstractFactory.admin;

import es.unileon.happycow.abstractFactory.FactoryWindows;
import es.unileon.happycow.controller.InterfaceController;
import es.unileon.happycow.controller.admin.BackupController;
import es.unileon.happycow.gui.admin.Backup;
import javax.swing.JPanel;

/**
 *
 * @author dorian
 */
public class FactoryBackup implements FactoryWindows{
    private Backup panel;
    private BackupController controller;

    @Override
    public InterfaceController getController() {
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

    @Override
    public void createElements() {
        createPanel();
        createController();
    }
    
}
