package es.unileon.happycow.controller;

import es.unileon.happycow.application.JFrameController;
import es.unileon.happycow.gui.Window;
import es.unileon.happycow.gui.panels.PanelListFarms;
import es.unileon.happycow.handler.IdHandler;

/**
 *
 * @author dorian
 */
public class ListFarmsController extends ButtonFarmInterfaceController{
    /**
     * Panel concreto
     */
    private final PanelListFarms panel;
    /**
     * Controlador concreto
     */

    private JFrameController fatherController;

    /**
     * Constructor que recibe el panel que controla
     * @param panel 
     */
    public ListFarmsController(PanelListFarms panel) {
        this.panel = panel;
    }
    
    /**
     * Salida
     */
    public void exit(){
        fatherController.setState(Window.LOGIN);
    }
    
    /**
     * Nueva granja
     */
    public void newFarm(){
        fatherController.setState(Window.NEW_FARM);
    }
    
    /**
     * Ir a la lista de granjas inahibilitadas para habilitar
     */
    public void enableFarm(){
//        fatherController.enableFarm();
    }
    
    /**
     * Exporta los datos a excel de todas las granjas
     */
    public void exportExcel(){
        //llama al padre y le da toda la lista de granjas
//        fatherController.excel(Database.getInstance().getListFarms());
    }

    @Override
    public void execute(IdHandler id) {
//        fatherController.manageFarm(id);
    }

    @Override
    public void setFrameController(JFrameController controller) {
        fatherController=controller;
    }
    
}
