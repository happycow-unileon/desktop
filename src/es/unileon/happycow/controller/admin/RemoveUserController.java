package es.unileon.happycow.controller.admin;

import es.unileon.happycow.controller.Controller;
import es.unileon.happycow.database.Database;
import es.unileon.happycow.gui.admin.RemoveUser;
import es.unileon.happycow.model.Rol;
import es.unileon.happycow.model.User;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author dorian
 */
public class RemoveUserController extends Controller {

    private final RemoveUser panel;

    public RemoveUserController(RemoveUser panel) {
        this.panel = panel;

//                JFrameApplication.getInstance().getHelp().setHelpOnButton(panel.getButtonHelp(), "AyudaAdminEliminarUsuario");
//                JFrameApplication.getInstance().getHelp().setHelp(panel, "AyudaAdminEliminarUsuario");
    }

    public void remove() {
        String target = panel.getElement();
        boolean resultado;

        resultado = Database.getInstance().removeUser(new User(target, "something", Rol.VETERINARIO));
        if (resultado) {
            panel.removeElement(target);
        } else {
            JOptionPane.showMessageDialog(panel, "Error eliminando el usuario", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void update() {
        LinkedList<String> list = new LinkedList<>();

        LinkedList<User> user = Database.getInstance().getListUsers();
        for (User us : user) {
            list.add(us.getName());
        }
        panel.changeCombo(list);

    }

}
