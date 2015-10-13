package es.unileon.happycow.windows.factory.admin;

import es.unileon.happycow.application.Parameters;
import es.unileon.happycow.controller.Controller;
import es.unileon.happycow.controller.admin.NewCriterionController;
import es.unileon.happycow.windows.factory.IFactory;
import es.unileon.happycow.gui.admin.NewCriterion;
import javax.swing.JPanel;

/**
 *
 * @author dorian
 */
public class FactoryNewCriterion extends IFactory{

    private NewCriterion panel;
    private NewCriterionController controller;

    public FactoryNewCriterion(Parameters parameters) {
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
