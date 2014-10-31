package es.unileon.happycow.abstractFactory.admin;

import es.unileon.happycow.abstractFactory.FactoryWindows;
import es.unileon.happycow.controller.InterfaceController;
import es.unileon.happycow.controller.admin.NewUserController;
import es.unileon.happycow.gui.admin.NewUser;
import javax.swing.JPanel;

/**
 *
 * @author dorian
 */
public class FactoryNewUser implements FactoryWindows{
    private NewUser panel;
    private NewUserController controller;

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
            controller=new NewUserController(panel);
            panel.setController(controller);
        }
    }

    @Override
    public void createPanel() {
        if(panel==null){
            panel=new NewUser();
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
