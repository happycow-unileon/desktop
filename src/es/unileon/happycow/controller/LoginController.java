package es.unileon.happycow.controller;

import es.unileon.happycow.application.JFrameController;
import es.unileon.happycow.application.Window;
import es.unileon.happycow.database.Database;
import es.unileon.happycow.gui.panels.PanelLogin;

/**
 *
 * @author dorian
 */
public class LoginController implements IController {

    /**
     * Panel concreto
     */
    private final PanelLogin login;
    /**
     * Controlador concreto del jframe
     */
    private JFrameController controller;

    public LoginController(PanelLogin login) {
        this.login = login;
    }

    public void exit() {
        controller.exit();
    }

    public void checkLogin(String user, String passwd) {
        if (Database.getInstance().login(user, passwd)) {
            //indicar al controlador general que se cambia de ventana
            switch (Database.getInstance().getUser().getRol()) {
                case ADMINISTRADOR:
                    controller.setState(Window.ADMINISTRATION);
                    break;
                case VETERINARIO:
                    controller.setState(Window.LIST_FARMS);
                    break;
            }
        } else {
            login.setMessageError("Error en los datos de logueo, pruebe otra vez");
        }
    }

    @Override
    public void setFrameController(JFrameController controller) {
        this.controller = controller;
    }
}
