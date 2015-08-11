package es.unileon.happycow.windows.factory.admin;

import es.unileon.happycow.controller.IController;
import es.unileon.happycow.controller.admin.NewUserController;
import es.unileon.happycow.windows.factory.IFactory;
import es.unileon.happycow.gui.admin.NewUser;
import java.util.HashMap;
import javax.swing.JPanel;

/**
 *
 * @author dorian
 */
public class FactoryNewUser extends IFactory {
    private NewUser panel;
    private NewUserController controller;

    public FactoryNewUser(HashMap<String, String> parameters) {
        super(parameters);
    }

    @Override
    public IController getController() {
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
    
}
