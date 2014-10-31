package es.unileon.happycow.controller;

import es.unileon.happycow.database.*;
import es.unileon.happycow.gui.PanelListFarms;
import es.unileon.happycow.handler.IdHandler;

/**
 *
 * @author dorian
 */
public class ListFarmsController implements ButtonFarmInterfaceController{
    /**
     * Panel concreto
     */
    private final PanelListFarms panel;
    /**
     * Controlador concreto
     */
    private final JFrameController fatherController;

    /**
     * Constructor que recibe el panel que controla
     * @param panel 
     */
    public ListFarmsController(PanelListFarms panel) {
        this.panel = panel;
        this.fatherController = JFrameController.getInstance();
    }
    
    /**
     * Salida
     */
    public void exit(){
        fatherController.login();
    }
    
    /**
     * Nueva granja
     */
    public void newFarm(){
        fatherController.newFarm(null);
    }
    
    /**
     * Ir a la lista de granjas inahibilitadas para habilitar
     */
    public void enableFarm(){
        fatherController.enableFarm();
    }
    
    /**
     * Exporta los datos a excel de todas las granjas
     */
    public void exportExcel(){
        //llama al padre y le da toda la lista de granjas
        fatherController.excel(Database.getInstance().getListFarms());
    }

    @Override
    public void execute(IdHandler id) {
        fatherController.manageFarm(id);
    }
    
}
