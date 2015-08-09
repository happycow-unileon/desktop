package es.unileon.happycow.controller;

import es.unileon.happycow.application.windows.Window;
import es.unileon.happycow.database.*;
import es.unileon.happycow.gui.PanelEnableFarm;
import es.unileon.happycow.handler.IdHandler;

/**
 *
 * @author dorian
 */
public class EnableFarmController extends ButtonFarmInterfaceController{
     /**
     * Panel concreto
     */
    private final PanelEnableFarm panel;

    public EnableFarmController(PanelEnableFarm panel) {
        this.panel = panel;
    }

    public void comeBack(){
        controller.setState(Window.LIST_FARMS);
    }
    
    @Override
    public void execute(IdHandler id) {
        //habilitar en la base de datos el id seleccionado
        Database.getInstance().enableFarm(id);
        panel.changeList(Database.getInstance().getDisabledFarms());
    }
    
}
