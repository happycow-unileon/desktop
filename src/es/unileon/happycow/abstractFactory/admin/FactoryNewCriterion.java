package es.unileon.happycow.abstractFactory.admin;

import es.unileon.happycow.abstractFactory.FactoryWindows;
import es.unileon.happycow.controller.InterfaceController;
import es.unileon.happycow.controller.admin.NewCriterionController;
import es.unileon.happycow.gui.admin.NewCriterion;
import javax.swing.JPanel;

/**
 *
 * @author dorian
 */
public class FactoryNewCriterion implements FactoryWindows{

    private NewCriterion panel;
    private NewCriterionController controller;

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

    @Override
    public void createElements() {
        createPanel();
        createController();
    }
    
}
