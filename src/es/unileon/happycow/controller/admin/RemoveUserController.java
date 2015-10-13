package es.unileon.happycow.controller.admin;

import es.unileon.happycow.controller.Controller;
import es.unileon.happycow.database.Database;
import es.unileon.happycow.gui.admin.RemoveUser;
import es.unileon.happycow.model.Rol;
import es.unileon.happycow.model.User;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 * Controller of the panel to remove users
 * @author dorian
 */
public class RemoveUserController extends Controller {
    /**
     * panel
     */
    private final RemoveUser panel;

    /**
     * Constructor
     * @param panel 
     */
    public RemoveUserController(RemoveUser panel) {
        this.panel = panel;
    }

    /**
     * Remove user
     */
    public void remove() {
        String target = panel.getElement();
        boolean resultado;

        //search the user in database, with a random password
        resultado = Database.getInstance().removeUser(new User(target, "something", Rol.VETERINARIO));
        if (resultado) {
            panel.removeElement(target);
        } else {
            JOptionPane.showMessageDialog(panel, "Error eliminando el usuario", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    /**
     * Update the information from model to panel
     */
    public void update() {
        LinkedList<String> list = new LinkedList<>();

        LinkedList<User> user = Database.getInstance().getListUsers();
        for (User us : user) {
            list.add(us.getName());
        }
        panel.changeCombo(list);

    }

}
