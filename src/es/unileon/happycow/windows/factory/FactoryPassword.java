package es.unileon.happycow.windows.factory;

import es.unileon.happycow.application.Parameters;
import es.unileon.happycow.controller.IController;
import es.unileon.happycow.controller.LoginController;
import es.unileon.happycow.controller.PasswordController;
import es.unileon.happycow.gui.PanelLogin;
import es.unileon.happycow.gui.PanelPassword;
import java.util.HashMap;
import javax.swing.JPanel;

/**
 * Create the panel and controller of the login window
 * @author dorian
 */
public class FactoryPassword extends IFactory {
    /**
     * Panel concreto
     */
    private PanelPassword panel;
    /**
     * Controlador concreto
     */
    private PasswordController controller;

    public FactoryPassword(Parameters parameters) {
        super(parameters);
    }

    /**
     * 
     * @see es.unileon.happycow.abstractFactory.FactoryWindows#getController() 
     */
    @Override
    public IController getController() {
        if(controller==null){
            createController();
        }
        return controller;
    }
    
    /**
     * 
     * @see es.unileon.happycow.abstractFactory.FactoryWindows#getPanel() 
     */
    @Override
    public JPanel getPanel() {
        if(panel==null){
            createPanel();
        }
        return panel;
    }

    /**
     * @see es.unileon.happycow.abstractFactory.FactoryWindows#createController() 
     */
    @Override
    public void createController() {
        if(panel==null){
            createPanel();
        }
        
        if(controller==null){
            controller=new PasswordController(panel);
            panel.setController(controller);
        }
    }

    /**
     * @see es.unileon.happycow.abstractFactory.FactoryWindows#createPanel() 
     */
    @Override
    public void createPanel() {
        if(panel==null){
            panel=new PanelPassword();
        }
        
        //if the controller exists, set the controller to the panel
        if(controller!=null){
            panel.setController(controller);
        }
    }
   
}
