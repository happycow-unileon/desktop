package es.unileon.happycow.controller;

import es.unileon.happycow.application.Parameters;
import es.unileon.happycow.database2.Database;
import es.unileon.happycow.windows.Window;
import es.unileon.happycow.gui.PanelManageFarm;
import es.unileon.happycow.handler.IdFarm;
import es.unileon.happycow.handler.IdHandler;
import es.unileon.happycow.model.Farm;

/**
 *
 * @author dorian
 */
public class ManageFarmController extends ButtonFarmInterfaceController {

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
    }

    public void report(IdHandler id) {
//        fatherController.report(new Report(Database.getInstance().getEvaluation(id)), idFarm);
    }

    /**
     * Selecciona una evaluación y la modifica
     *
     * @param id
     */
    public void evaluationSelected(IdHandler id) {
        //llama al padre y le indica la evaluación seleccionada
        controller.clearParameters();
        controller.addParameter("idFarm", idFarm.toString());
        controller.addParameter("idEvaluation", id.toString());
        controller.setState(Window.EVALUATION);
//        fatherController.evaluation(idFarm, id);
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
        controller.setState(Window.EVALUATION);
//        fatherController.evaluation(idFarm, null);
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
//        fatherController.newFarm(idFarm);
    }

    @Override
    public void onResume(Parameters parameters) {
        String id = parameters.getString("id");
        if (id != null) {
            idFarm = new IdFarm(Integer.parseInt(id));
        }
        if (idFarm != null) {
            Farm farm = Database.getInstance().getFarm(idFarm);
            panel.setFarm(farm);
        }
    }

}
