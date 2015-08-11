package es.unileon.happycow.windows.factory.admin;

import es.unileon.happycow.controller.IController;
import es.unileon.happycow.controller.admin.RemoveUserController;
import es.unileon.happycow.database.Database;
import es.unileon.happycow.windows.factory.IFactory;
import es.unileon.happycow.gui.admin.RemoveUser;
import es.unileon.happycow.model.User;
import java.util.HashMap;
import java.util.LinkedList;
import javax.swing.JPanel;

/**
 *
 * @author dorian
 */
public class FactoryRemoveUser extends IFactory {

    private RemoveUser panel;
    private RemoveUserController controller;

    public FactoryRemoveUser(HashMap<String, String> parameters) {
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
            controller = new RemoveUserController(panel);
            panel.setController(controller);
        }
    }

    @Override
    public void createPanel() {
        if (panel == null) {
            LinkedList<String> list = new LinkedList<String>();

            list = new LinkedList<String>();
            for (User user : Database.getInstance().getListUsers()) {
                list.add(user.getName());
            }
            panel = new RemoveUser(
                    "Escoja usuario a eliminar",
                    "<html>Seleccione cuidadosamente el usuario.<br>"
                    + "Si procede a eliminar, se eliminará el resto de información relacionada<br>"
                    + " como son todas sus evaluaciones y granjas evaluadas.</html>", list);

        }

        if (controller != null) {
            panel.setController(controller);
        }
    }

}
