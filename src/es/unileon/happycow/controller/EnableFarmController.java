package es.unileon.happycow.controller;

import es.unileon.happycow.database.*;
import es.unileon.happycow.gui.PanelEnableFarm;
import es.unileon.happycow.handler.IdHandler;

/**
 *
 * @author dorian
 */
public class EnableFarmController implements ButtonFarmInterfaceController{
     /**
     * Panel concreto
     */
    private final PanelEnableFarm panel;
    /**
     * Controlador concreto
     */
    private final JFrameController fatherController;

    public EnableFarmController(PanelEnableFarm panel) {
        this.panel = panel;
        this.fatherController = JFrameController.getInstance();
    }

    public void comeBack(){
        fatherController.seeListFarm();
    }
    
    @Override
    public void execute(IdHandler id) {
        //habilitar en la base de datos el id seleccionado
        Database.getInstance().enableFarm(id);
        panel.changeList(Database.getInstance().getDisabledFarms());
    }
    
}
