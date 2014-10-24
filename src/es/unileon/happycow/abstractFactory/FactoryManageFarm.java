package es.unileon.happycow.abstractFactory;

import es.unileon.happycow.controller.InterfaceController;
import es.unileon.happycow.controller.ManagementFarmController;
import es.unileon.happycow.gui.PanelManagementFarm;
import es.unileon.happycow.model.Farm;
import javax.swing.JPanel;

/**
 * Class that create the window and controller which show the farm's data and
 * their evaluations
 * @author dorian
 */
public class FactoryManageFarm implements FactoryWindows{
    /**
     * Panel concreto
     */
    private PanelManagementFarm panel;
    /**
     * Controlador concreto
     */
    private ManagementFarmController controller;
    /**
     * Farm to show
     */
    private final Farm farm;

    /**
     * 
     * @param farm 
     */
    public FactoryManageFarm(Farm farm) {
        //a farm is needed
        if(farm==null){
            throw new IllegalArgumentException("Ha de recibir una granja a gestionar.");
        }
        this.farm=farm;
    }

    /**
     * 
     * @see es.unileon.happycow.abstractFactory.FactoryWindows#getController() 
     */
    @Override
    public InterfaceController getController() {
        //create the controller if not exists
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
        //create the panel if not exists
        if(panel==null){
            createPanel();
        }
        return panel;
    }

    /**
     * 
     * @see es.unileon.happycow.abstractFactory.FactoryWindows#createController() 
     */
    @Override
    public void createController() {
        //create the panel if not exists because controller need it
        if(panel==null){
            createPanel();
        }
        
        //create the controller if not exists
        if(controller==null){
            //give to the controller the farm and the panel
            controller=new ManagementFarmController(panel, farm.getIdFarm());
            //set the new controller to the panel
            panel.setController(controller);
        }
    }

    /**
     * @see es.unileon.happycow.abstractFactory.FactoryWindows#createPanel() 
     */
    @Override
    public void createPanel() {
        //create the panel if not exists
        if(panel==null){
            panel=new PanelManagementFarm(farm);
        }
        
        //if a controller exists, set the controller to the panel
        if(controller!=null){
            panel.setController(controller);
        }
    }

    /**
     * @see es.unileon.happycow.abstractFactory.FactoryWindows#createElements() 
     */
    @Override
    public void createElements() {
        createPanel();
        createController();
    }
    
}
