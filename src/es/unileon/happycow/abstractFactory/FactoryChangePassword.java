
package es.unileon.happycow.abstractFactory;

import es.unileon.happycow.controller.InterfaceController;
import es.unileon.happycow.controller.PasswordController;
import es.unileon.happycow.gui.PanelPassword;
import javax.swing.JPanel;

/**
 * Create the panel and controller of the password change
 * @author dorian
 */
public class FactoryChangePassword implements FactoryWindows{
    /**
     * Panel concreto
     */
    private PanelPassword passwordPanel;
    /**
     * Controlador concreto
     */
    private PasswordController controller;

    
    /**
     * 
     * @see es.unileon.happycow.abstractFactory.FactoryWindows#getController() 
     */
    @Override
    public InterfaceController getController() {
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
        if(passwordPanel==null){
            createPanel();
        }
        return passwordPanel;
    }

    /**
     * @see es.unileon.happycow.abstractFactory.FactoryWindows#createController() 
     */
    @Override
    public void createController() {
        if(passwordPanel==null){
            createPanel();
        }
        if(controller==null){
            controller=new PasswordController(passwordPanel);
            //set the new controller if the panel exists
            passwordPanel.setController(controller);
        }
    }

    /**
     * 
     * @see es.unileon.happycow.abstractFactory.FactoryWindows#createPanel()
     */
    @Override
    public void createPanel() {
        if(passwordPanel==null){
                passwordPanel=new PanelPassword();
        }
        
        //if the controller exists, set the controller to the panel
        if(controller!=null){
            passwordPanel.setController(controller);
        }
    }

    /**
     * @see es.unileon.happycow.abstractFactory.FactoryWindows#createElements() 
     */
    @Override
    public void createElements() {
        createPanel();
        createController();
    }
    
}
