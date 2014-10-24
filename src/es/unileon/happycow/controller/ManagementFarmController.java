package es.unileon.happycow.controller;

import es.unileon.happycow.database.*;
import es.unileon.happycow.gui.PanelManagementFarm;
import es.unileon.happycow.handler.IdHandler;
import es.unileon.happycow.model.Farm;
import es.unileon.happycow.procedures.Report;
import java.util.LinkedList;

/**
 *
 * @author dorian
 */
public class ManagementFarmController implements ButtonFarmInterfaceController {
    /**
     * Panel concreto
     */
    private final PanelManagementFarm panel;
    /**
     * Controlador concreto
     */
    private final JFrameController fatherController;
    /**
     * Identificador de la granja que tiene el panel que controla
     */
    private final IdHandler idFarm;

    /**
     * Constructor
     * @param panel panel que controla
     * @param idFarm granja del modelo que maneja
     */
    public ManagementFarmController(PanelManagementFarm panel, IdHandler idFarm) {
        this.panel = panel;
        this.fatherController = JFrameController.getInstance();
        this.idFarm=idFarm;
    }
    
    public void removeEvaluation(IdHandler id){
        Database.getInstance().removeEvaluation(id);
    }
    
    public void report(IdHandler id){
        fatherController.report(new Report(Database.getInstance().getEvaluation(id)), idFarm);
    }
    
    /**
     * Selecciona una evaluación y la modifica
     * @param id 
     */
    public void evaluationSelected(IdHandler id){
        //llama al padre y le indica la evaluación seleccionada
        fatherController.evaluation(idFarm, id);
    }
    
    /**
     * Deshabilita la granja
     */
    public void disableFarm(){
        if(Database.getInstance().disableFarm(idFarm)){
            returnWindow();
        }
    }
    
    /**
     * Vuelve a la página anterior
     */
    public void returnWindow(){
        fatherController.seeListFarm();
    }
    
    /**
     * Hace una nueva evaluación de esta granja
     */
    public void newEvaluation(){
        fatherController.evaluation(idFarm, null);
    }
    
    /**
     * Pasa a la ventana de excel con esta granja y sus evaluaciones marcadas
     */
    public void excel(){
        LinkedList<Farm> list=new LinkedList<>();
        list.add(Database.getInstance().getFarm(idFarm));
        fatherController.excel(list);
    }

    @Override
    public void execute(IdHandler id) {
        //abre la ventana de nueva granja con los datos actuales
        fatherController.newFarm(idFarm);
    }
    
}
