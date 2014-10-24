package es.unileon.happycow.abstractFactory;

import es.unileon.happycow.controller.InterfaceController;
import es.unileon.happycow.controller.LoginController;
import es.unileon.happycow.gui.PanelLogin;
import javax.swing.JPanel;

/**
 * Create the panel and controller of the login window
 * @author dorian
 */
public class FactoryLogin implements FactoryWindows{
    /**
     * Panel concreto
     */
    private PanelLogin login;
    /**
     * Controlador concreto
     */
    private LoginController controller;

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
        if(login==null){
            createPanel();
        }
        return login;
    }

    /**
     * @see es.unileon.happycow.abstractFactory.FactoryWindows#createController() 
     */
    @Override
    public void createController() {
        if(login==null){
            createPanel();
        }
        
        if(controller==null){
            controller=new LoginController(login);
            login.setController(controller);
        }
    }

    /**
     * @see es.unileon.happycow.abstractFactory.FactoryWindows#createPanel() 
     */
    @Override
    public void createPanel() {
        if(login==null){
            login=new PanelLogin();
        }
        
        //if the controller exists, set the controller to the panel
        if(controller!=null){
            login.setController(controller);
        }
    }

    @Override
    public void createElements() {
        createPanel();
        createController();
    }
}
