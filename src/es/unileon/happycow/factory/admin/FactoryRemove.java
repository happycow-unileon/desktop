package es.unileon.happycow.factory.admin;

import es.unileon.happycow.controller.IController;
import es.unileon.happycow.controller.admin.RemoveController;
import es.unileon.happycow.database.Database;
import es.unileon.happycow.factory.Factory;
import es.unileon.happycow.gui.panels.admin.Remove;
import es.unileon.happycow.gui.panels.admin.RemoveAdmin;
import es.unileon.happycow.model.User;
import es.unileon.happycow.model.composite.Criterion;
import java.util.HashMap;
import java.util.LinkedList;
import javax.swing.JPanel;

/**
 *
 * @author dorian
 */
public class FactoryRemove extends Factory {

    private Remove panel;
    private RemoveController controller;
    private final RemoveAdmin function;

    public FactoryRemove(RemoveAdmin what, HashMap<String, String> parameters) {
        super(parameters);
        function = what;
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
            controller = new RemoveController(function, panel);
            panel.setController(controller);
        }
    }

    @Override
    public void createPanel() {
        if (panel == null) {
            LinkedList<String> list=new LinkedList<String>();
            switch (function) {
                case CRITERION:
                    for (Criterion criterion : Database.getInstance().getListCriterion()) {
                        list.add(criterion.getName());
                    }
                    panel=new Remove(
                            "Escoja criterio a eliminar", 
                            "<html>Seleccione cuidadosamente el criterio.<br>"
                                    + "Si procede a eliminar, se eliminará el resto de información relacionada<br>"
                                    + " como son parte de las evaluaciones en las que se ha evaluado con dicho criterio.</html>"
                            , list);
                    break;
                    
                case USER:
                    list=new LinkedList<String>();
                    for (User user : Database.getInstance().getListUsers()) {
                        list.add(user.getName());
                    }
                    panel=new Remove(
                            "Escoja usuario a eliminar", 
                            "<html>Seleccione cuidadosamente el usuario.<br>"
                                    + "Si procede a eliminar, se eliminará el resto de información relacionada<br>"
                                    + " como son todas sus evaluaciones y granjas evaluadas.</html>"
                            , list);
                    break;
            }
        }

        if (controller != null) {
            panel.setController(controller);
        }
    }

}
