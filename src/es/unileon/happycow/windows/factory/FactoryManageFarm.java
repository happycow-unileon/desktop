package es.unileon.happycow.windows.factory;

import es.unileon.happycow.application.Parameters;
import es.unileon.happycow.controller.Controller;
import es.unileon.happycow.controller.ManageFarmController;
import es.unileon.happycow.gui.PanelManageFarm;
import java.util.HashMap;
import javax.swing.JPanel;

/**
 * Create the panel and controller that control the list window where a list of
 * farms is showed (enabled and disabled farms)
 * @author dorian
 */
public class FactoryManageFarm extends IFactory{
    /**
     * Concrete panel
     */
    private PanelManageFarm panel;
    /**
     * Concrete controller
     */
    private ManageFarmController controller;

    
    /**
     * 
     * @param parameters
     * @param list list of farms to show
     */
    public FactoryManageFarm(Parameters parameters) {
        super(parameters);
    }
    
    
    
    /**
     * 
     * @see es.unileon.happycow.abstractFactory.FactoryWindows#getController() 
     */
    @Override
    public Controller getController() {
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
            controller=new ManageFarmController(panel);
            //set the new controller to the panel
            panel.setController(controller);
        }
    }

    /**
     * 
     * @see es.unileon.happycow.abstractFactory.FactoryWindows#createPanel()
     */
    @Override
    public void createPanel() {
        if(panel==null){
                panel=new PanelManageFarm();
        }
        
        //if controller exists, set the controller to the panel
        if(controller!=null){
            panel.setController(controller);
        }
    }
    
}
