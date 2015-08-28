package es.unileon.happycow.windows.factory;

import es.unileon.happycow.application.Parameters;
import es.unileon.happycow.controller.Controller;
import es.unileon.happycow.controller.NewFarmController;
import es.unileon.happycow.gui.PanelNewFarm;
import es.unileon.happycow.model.Farm;
import javax.swing.JPanel;

/**
 * Class where panel and controller of the new farm's window are created
 * Used for famr's modification too
 * @author dorian
 */
public class FactoryNewFarm extends IFactory{
    /**
     * Concrete panel
     */
    private PanelNewFarm panel;
    /**
     * Concrete controller
     */
    private NewFarmController controller;
    /**
     * the new farm to create/modify
     */
    private final Farm farm;
    
    
    /***
     * 
     * @param farm the farm to modify or use. If null,
     * a new farm is created
     */
    public FactoryNewFarm(Parameters parameters) {
        super(parameters);
        //TODO
        this.farm=null;
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
        
        if( controller==null){
            //create the controller with or without the farm (modify or new)
            if(farm==null){
                controller=new NewFarmController(panel);
            }else{
                controller=new NewFarmController(panel);
            }
            panel.setController(controller);
        }
    }

    /**
     * @see es.unileon.happycow.abstractFactory.FactoryWindows#createPanel() 
     */
    @Override
    public void createPanel() {
        if(panel==null){
            if(farm==null){
                panel=new PanelNewFarm();
                
            //if the farm exists, give the farm so it can get the data
            }else{
                panel=new PanelNewFarm();
            }
        }
        
        //if controller exists, set the controller to the panel
        if(controller!=null){
            panel.setController(controller);
        }
    }

}
