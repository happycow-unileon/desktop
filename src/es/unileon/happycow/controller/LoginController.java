package es.unileon.happycow.controller;

import es.unileon.happycow.application.Parameters;
import es.unileon.happycow.database.Database;
import es.unileon.happycow.database.concreteDatabase.DatabaseObject;
import es.unileon.happycow.windows.Window;
import es.unileon.happycow.gui.PanelLogin;

/**
 * The controller of the login panel
 * @author dorian
 */
public class LoginController extends Controller {
    /**
     * Panel concreto
     */
    private final PanelLogin login;
    
    /**
     * Constructor
     * @param login 
     */
    public LoginController(PanelLogin login) {
        this.login = login;
    }

    /**
     * Exit of the aplication
     */
    public void exit() {
        controller.exit();
    }

    /**
     * Check the data login, if correct, continue
     * @param user the user
     * @param passwd the password
     */
    public void checkLogin(String user, String passwd) {
        if (Database.getInstance().login(user, DatabaseObject.encript(passwd))){
            //indicar al controlador general que se cambia de ventana
            switch (Database.getInstance().getUser().getRol()) {
                case ADMINISTRADOR:
                    controller.clearParameters();
                    controller.setState(Window.ADMINISTRATION);
                    break;
                case VETERINARIO:
                    controller.clearParameters();
                    controller.setState(Window.LIST_FARMS);
                    break;
            }
        } else {
            login.setMessageError("Error en los datos de logueo, pruebe otra vez");
        }
    }

    @Override
    public void onResume(Parameters parameters) {
        login.clearAll();
    }

    
}
