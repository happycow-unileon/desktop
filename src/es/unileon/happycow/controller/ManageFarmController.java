package es.unileon.happycow.controller;

import es.unileon.happycow.application.Parameters;
import es.unileon.happycow.database.Database;
import es.unileon.happycow.windows.Window;
import es.unileon.happycow.gui.PanelManageFarm;
import es.unileon.happycow.handler.IdFarm;
import es.unileon.happycow.handler.IdHandler;
import es.unileon.happycow.model.Farm;
import es.unileon.happycow.model.InformationEvaluation;

/**
 *
 * @author dorian
 */
public class ManageFarmController extends ButtonFarmDetailsIController {

    /**
     * Panel concreto
     */
    private final PanelManageFarm panel;
    /**
     * Identificador de la granja que tiene el panel que controla
     */
    private IdHandler idFarm;

    /**
     * Constructor
     *
     * @param panel panel que controla
     */
    public ManageFarmController(PanelManageFarm panel) {
        this.panel = panel;
        this.idFarm = null;
    }

    public void removeEvaluation(IdHandler id) {
        Database.getInstance().removeEvaluation(id);
        panel.removeEvaluation(id);
    }

    public void report(IdHandler id) {
        controller.clearParameters();
        controller.addParameter("idFarm", idFarm.toString());
        controller.addParameter("idEvaluation", id.toString());
        controller.setState(Window.REPORT);
    }

    /**
     * Selecciona una evaluación y la modifica
     *
     * @param id
     */
    public void evaluationSelected(InformationEvaluation info) {
        //llama al padre y le indica la evaluación seleccionada
        controller.clearParameters();
        controller.addParameter("idFarm", idFarm.toString());
        controller.addParameter("isNew", false);
        controller.addParameter("idEvaluation", info.getIdEvaluation().toString());
        controller.addParameter("user", info.getIdUser().toString());
        controller.setState(Window.EVALUATION);
    }

    /**
     * Deshabilita la granja
     */
    public void disableFarm() {
        if (Database.getInstance().disableFarm(idFarm)) {
            returnWindow();
        }
    }

    /**
     * Vuelve a la página anterior
     */
    public void returnWindow() {
        controller.setState(Window.LIST_FARMS);
    }

    /**
     * Hace una nueva evaluación de esta granja
     */
    public void newEvaluation() {
        controller.clearParameters();
        controller.addParameter("isNew", true);
        controller.addParameter("idFarm", idFarm.toString());
        controller.addParameter("user", Database.getInstance().getUser().getId().toString());
        controller.setState(Window.EVALUATION);
    }

    /**
     * Pasa a la ventana de excel con esta granja y sus evaluaciones marcadas
     */
    public void excel() {
//        LinkedList<Farm> list=new LinkedList<>();
//        list.add(Database.getInstance().getFarm(idFarm));
//        fatherController.excel(list);
    }

    @Override
    public void execute(IdHandler id) {
        //abre la ventana de nueva granja con los datos actuales
        controller.clearParameters();
        controller.addParameter("id", idFarm.toString());
        controller.setState(Window.EDIT_FARM);
    }

    @Override
    public void onResume(Parameters parameters) {
        String id = parameters.getString("id");
        if (id != null) {
            idFarm = new IdFarm(id);
        }

        if (idFarm != null) {
            Farm farm = Database.getInstance().getFarm(idFarm);
            panel.setFarm(farm);
            panel.setList(farm.getListEvaluation());
        }
    }

}
