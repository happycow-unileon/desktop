package es.unileon.happycow.windows.factory.admin;

import es.unileon.happycow.application.Parameters;
import es.unileon.happycow.controller.IController;
import es.unileon.happycow.controller.admin.RemoveCriterionController;
import es.unileon.happycow.database.Database;
import es.unileon.happycow.windows.factory.IFactory;
import es.unileon.happycow.gui.admin.RemoveCriterion;
import es.unileon.happycow.model.composite.Criterion;
import java.util.LinkedList;
import javax.swing.JPanel;

/**
 *
 * @author dorian
 */
public class FactoryRemoveCriterion extends IFactory {

    private RemoveCriterion panel;
    private RemoveCriterionController controller;

    public FactoryRemoveCriterion(Parameters parameters) {
        super(parameters);
    }

    @Override
    public IController getController() {
        if (controller == null) {
            createController();
        }

        return controller;
    }

    @Override
    public JPanel getPanel() {
        if (panel == null) {
            createPanel();
        }

        return panel;
    }

    @Override
    public void createController() {
        if (panel == null) {
            createPanel();
        }

        if (controller == null) {
            controller = new RemoveCriterionController(panel);
            panel.setController(controller);
        }
    }

    @Override
    public void createPanel() {
        if (panel == null) {
            LinkedList<String> list = new LinkedList<String>();

            for (Criterion criterion : Database.getInstance().getListCriterion()) {
                list.add(criterion.getName());
            }
            panel = new RemoveCriterion(
                    "Escoja criterio a eliminar",
                    "<html>Seleccione cuidadosamente el criterio.<br>"
                    + "Si procede a eliminar, se eliminará el resto de información relacionada<br>"
                    + " como son parte de las evaluaciones en las que se ha evaluado con dicho criterio.</html>", list);

        }

        if (controller != null) {
            panel.setController(controller);
        }
    }

}
