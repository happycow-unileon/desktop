package es.unileon.happycow.controller;

import es.unileon.happycow.database.Database;
import es.unileon.happycow.gui.PanelLogin;

/**
 *
 * @author dorian
 */
public class LoginController implements InterfaceController{
    /**
     * Panel concreto
     */
    private final PanelLogin login;
    /**
     * Controlador concreto
     */
    private final JFrameController controller;

    public LoginController(PanelLogin login) {
        this.login = login;
        this.controller = JFrameController.getInstance();
    }
    
    public void exit(){
        controller.exit();
    }
    
    public void checkLogin(String user, String passwd){
        if(Database.getInstance().login(user, passwd)){
            //indicar al controlador general que se cambia de ventana
            switch (Database.getInstance().getUser().getRol()){
                case ADMINISTRADOR:
                    controller.administrator();
                    break;
                case VETERINARIO:
                    controller.seeListFarm();
                    break;
            }
        }else{
            login.setMessageError("Error en los datos de logueo, pruebe otra vez");
            
        }
    }
}
