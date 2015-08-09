
package es.unileon.happycow.factory;

import es.unileon.happycow.controller.IController;
import es.unileon.happycow.controller.BarOptionsController;
import es.unileon.happycow.gui.PanelOptions;
import java.util.HashMap;
import javax.swing.JPanel;

/**
 *
 * @author dorian
 */
public class FactoryOptionsBar extends Factory {
    /**
     * Panel concreto
     */
    private PanelOptions panel;
    /**
     * Controlador concreto
     */
    private BarOptionsController controller;

    public FactoryOptionsBar(HashMap<String, String> parameters) {
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
            controller=new BarOptionsController(panel);
            panel.setController(controller);
        }
    }

    /**
     * @see es.unileon.happycow.abstractFactory.FactoryWindows#createPanel() 
     */
    @Override
    public void createPanel() {
        if(panel==null){
            panel=new PanelOptions();
        }
        
        //if the controller exists, set the controller to the panel
        if(controller!=null){
            panel.setController(controller);
        }
    }
    
}
