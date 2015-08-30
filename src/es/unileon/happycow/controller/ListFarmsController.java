package es.unileon.happycow.controller;

import es.unileon.happycow.application.JFrameController;
import es.unileon.happycow.application.Parameters;
import es.unileon.happycow.database.Database;
import es.unileon.happycow.windows.Window;
import es.unileon.happycow.gui.PanelListFarms;
import es.unileon.happycow.handler.IdHandler;
import es.unileon.happycow.model.Farm;
import es.unileon.happycow.model.User;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author dorian
 */
public class ListFarmsController extends ButtonListFarmIController{
    /**
     * Panel concreto
     */
    private final PanelListFarms panel;

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
        controller.setState(Window.LOGIN);
    }
    
    /**
     * Nueva granja
     */
    public void newFarm(){
        controller.setState(Window.NEW_FARM);
    }
    
    /**
     * Ir a la lista de granjas inahibilitadas para habilitar
     */
    public void enableFarm(){
        controller.setState(Window.ENABLE_FARM);
    }
    
    /**
     * Exporta los datos a excel de todas las granjas
     */
    public void exportExcel(){
        //llama al padre y le da toda la lista de granjas
        controller.clearParameters();
        
        LinkedHashMap<User, LinkedList<Farm>> list=new LinkedHashMap<>();
        list.put(Database.getInstance().getUser(),Database.getInstance().getListFarms());
        
        controller.addParameter("list", list);
        controller.setState(Window.EXCEL);
        
    }

    @Override
    public void execute(IdHandler id) {
        controller.clearParameters();
        controller.addParameter("id", id.toString());
        controller.setState(Window.MANAGE_FARM);
    }

    @Override
    public void setFrameController(JFrameController controller) {
        this.controller=controller;
    }

    @Override
    public void onResume(Parameters parameters) {
        List<Farm> list=Database.getInstance().getListFarms();
        panel.changeList(list);
    }

    @Override
    public void edit(IdHandler id) {
        controller.clearParameters();
        controller.addParameter("id", id.toString());
        controller.setState(Window.EDIT_FARM);
    }
    
}
