package es.unileon.happycow.abstractFactory;

import es.unileon.happycow.controller.InterfaceController;
import es.unileon.happycow.controller.EnableFarmController;
import es.unileon.happycow.gui.PanelEnableFarm;
import es.unileon.happycow.model.Farm;
import java.util.LinkedList;
import javax.swing.JPanel;

/**
 * Panel and controller of the window that controls disabled farms
 * @author dorian
 */
public class FactoryEnableFarm implements FactoryWindows{
    /**
     * Panel concreto
     */
    private PanelEnableFarm panel;
    /**
     * Controlador concreto
     */
    private EnableFarmController controller;
    /**
     * List of disabled farms
     */
    private final LinkedList<Farm> list;

    /**
     * Receives a list of disabled farms
     * @param list list of disabled farms
     */
    public FactoryEnableFarm(LinkedList<Farm> list) {
        this.list=list;
    }

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
        
        if( controller==null){
            controller=new EnableFarmController(panel);
            //set the new controller to the panel
            panel.setController(controller);
        }
    }

    /**
     * @see es.unileon.happycow.abstractFactory.FactoryWindows#createPanel() 
     */
    @Override
    public void createPanel() {
        if(panel==null){
            panel=new PanelEnableFarm(list);
        }
        
        //if controller exists, set the controller to the panel
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
