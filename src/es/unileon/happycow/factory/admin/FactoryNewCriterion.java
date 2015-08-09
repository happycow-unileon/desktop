package es.unileon.happycow.factory.admin;

import es.unileon.happycow.controller.IController;
import es.unileon.happycow.controller.admin.NewCriterionController;
import es.unileon.happycow.factory.Factory;
import es.unileon.happycow.gui.panels.admin.NewCriterion;
import java.util.HashMap;
import javax.swing.JPanel;

/**
 *
 * @author dorian
 */
public class FactoryNewCriterion extends Factory{

    private NewCriterion panel;
    private NewCriterionController controller;

    public FactoryNewCriterion(HashMap<String, String> parameters) {
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
            controller=new NewCriterionController(panel);
            panel.setController(controller);
        }
    }

    @Override
    public void createPanel() {
        if(panel==null){
            panel=new NewCriterion();
        }
        
        if(controller!=null){
            panel.setController(controller);
        }
    }
    
}
