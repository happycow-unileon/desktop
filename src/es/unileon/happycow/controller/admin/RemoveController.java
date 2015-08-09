package es.unileon.happycow.controller.admin;

import es.unileon.happycow.controller.IController;
import es.unileon.happycow.database.Database;
import es.unileon.happycow.gui.panels.admin.Remove;
import es.unileon.happycow.gui.panels.admin.RemoveAdmin;
import es.unileon.happycow.handler.IdCriterion;
import es.unileon.happycow.model.Rol;
import es.unileon.happycow.model.User;
import es.unileon.happycow.model.composite.Criterion;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author dorian
 */
public class RemoveController extends IController {
//    private JFrameController fatherController;

    private final RemoveAdmin remove;
    private final Remove panel;

    public RemoveController(RemoveAdmin remove, Remove panel) {
        this.remove = remove;
        this.panel = panel;
//        switch(remove){
//            case CRITERION:
//                JFrameApplication.getInstance().getHelp().setHelpOnButton(panel.getButtonHelp(), "AyudaAdminEliminarCriterio");
//                JFrameApplication.getInstance().getHelp().setHelp(panel, "AyudaAdminEliminarCriterio");
//                break;
//            case USER:
//                JFrameApplication.getInstance().getHelp().setHelpOnButton(panel.getButtonHelp(), "AyudaAdminEliminarUsuario");
//                JFrameApplication.getInstance().getHelp().setHelp(panel, "AyudaAdminEliminarUsuario");
//                break;
//        }
    }

    public void remove() {
        String target = panel.getElement();
        boolean resultado;
        switch (remove) {
            case CRITERION:
                String targetModified = target.split("-")[0];
                resultado = Database.getInstance().removeCriterion(new IdCriterion(targetModified));
                if (resultado) {
                    panel.removeElement(target);
                } else {
                    JOptionPane.showMessageDialog(panel, "Error eliminando el criterio", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;

            case USER:
                resultado = Database.getInstance().removeUser(new User(target, "something", Rol.VETERINARIO));
                if (resultado) {
                    panel.removeElement(target);
                } else {
                    JOptionPane.showMessageDialog(panel, "Error eliminando el usuario", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
        }
    }

    public void update() {
        LinkedList<String> list = new LinkedList<>();
        switch (remove) {
            case CRITERION:
                LinkedList<Criterion> cri = Database.getInstance().getListCriterion();
                for (Criterion criterion : cri) {
                    list.add(criterion.getName() + "-" + criterion.getCategory().toString());
                }
                panel.changeCombo(list);
                break;

            case USER:
                LinkedList<User> user = Database.getInstance().getListUsers();
                for (User us : user) {
                    list.add(us.getName());
                }
                panel.changeCombo(list);
                break;
        }
    }

}
