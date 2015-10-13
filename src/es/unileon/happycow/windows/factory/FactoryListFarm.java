package es.unileon.happycow.windows.factory;

import es.unileon.happycow.application.Parameters;
import es.unileon.happycow.controller.Controller;
import es.unileon.happycow.controller.ListFarmsController;
import es.unileon.happycow.gui.PanelListFarms;
import javax.swing.JPanel;

/**
 * Create the panel and controller that control the list window where a list of
 * farms is showed (enabled and disabled farms)
 * @author dorian
 */
public class FactoryListFarm extends IFactory{
    /**
     * Concrete panel
     */
    private PanelListFarms listPanel;
    /**
     * Concrete controller
     */
    private ListFarmsController controller;

    
    /**
     * 
     * @param parameters
     */
    public FactoryListFarm(Parameters parameters) {
        super(parameters);
    }
    
    
    
    /**
     * 
     * @return 
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
     * @return 
     * @see es.unileon.happycow.abstractFactory.FactoryWindows#getPanel() 
     */
    @Override
    public JPanel getPanel() {
        if(listPanel==null){
            createPanel();
        }
        return listPanel;
    }

    /**
     * @see es.unileon.happycow.abstractFactory.FactoryWindows#createController() 
     */
    @Override
    public void createController() {
        if(listPanel==null){
            createPanel();
        }
        
        if(controller==null){
            controller=new ListFarmsController(listPanel);
            //set the new controller to the panel
            listPanel.setController(controller);
        }
    }

    /**
     * 
     * @see es.unileon.happycow.abstractFactory.FactoryWindows#createPanel()
     */
    @Override
    public void createPanel() {
        if(listPanel==null){
                listPanel=new PanelListFarms();
        }
        
        //if controller exists, set the controller to the panel
        if(controller!=null){
            listPanel.setController(controller);
        }
    }
    
}
