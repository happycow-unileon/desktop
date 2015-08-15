package es.unileon.happycow.windows.factory;

import es.unileon.happycow.application.Parameters;
import es.unileon.happycow.controller.EditFarmController;
import es.unileon.happycow.controller.IController;
import es.unileon.happycow.controller.NewFarmController;
import es.unileon.happycow.database.Database;
import es.unileon.happycow.gui.PanelEditFarm;
import es.unileon.happycow.gui.PanelNewFarm;
import es.unileon.happycow.handler.IdFarm;
import es.unileon.happycow.model.Farm;
import javax.swing.JPanel;

/**
 * Class where panel and controller of the new farm's window are created Used
 * for famr's modification too
 *
 * @author dorian
 */
public class FactoryEditFarm extends IFactory {

    /**
     * Concrete panel
     */
    private PanelEditFarm panel;
    /**
     * Concrete controller
     */
    private EditFarmController controller;

    /**
     * *
     *
     * @param farm the farm to modify or use. If null, a new farm is created
     */
    public FactoryEditFarm(Parameters parameters) {
        super(parameters);
    }

    /**
     *
     * @see es.unileon.happycow.abstractFactory.FactoryWindows#getController()
     */
    @Override
    public IController getController() {
        if (controller == null) {
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
        if (panel == null) {
            createPanel();
        }
        return panel;
    }

    /**
     * @see
     * es.unileon.happycow.abstractFactory.FactoryWindows#createController()
     */
    @Override
    public void createController() {
        if (panel == null) {
            createPanel();
        }

        if (controller == null) {
            //create the controller with the farm
            controller = new EditFarmController(panel, new IdFarm(parameters.getInteger("id")));
            panel.setController(controller);
        }
    }

    /**
     * @see es.unileon.happycow.abstractFactory.FactoryWindows#createPanel()
     */
    @Override
    public void createPanel() {
        if (panel == null) {
            Farm farm = Database.getInstance().getFarm(new IdFarm(parameters.getInteger("id")));
            panel = new PanelEditFarm(farm);
        }

        //if controller exists, set the controller to the panel
        if (controller != null) {
            panel.setController(controller);
        }
    }

}
