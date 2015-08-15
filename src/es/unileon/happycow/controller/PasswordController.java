package es.unileon.happycow.controller;

import es.unileon.happycow.database2.Database;
import es.unileon.happycow.database2.DatabaseObject;
import es.unileon.happycow.gui.PanelPassword;
import es.unileon.happycow.model.User;

/**
 *
 * @author dorian
 */
public class PasswordController extends IController{
    private final PanelPassword panel;

    public PasswordController(PanelPassword panel) {
        this.panel = panel;
    }
    
    public void changePassword(){
        String message="";
        if(panel.isPasswordMatch()){
            String oldPassword=DatabaseObject.encript(panel.getOldPassword());
            User user=Database.getInstance().getUser();
            String userPassword=user.getPassword();
            if(oldPassword.compareTo(userPassword)==0){
                user.setPassword(panel.getNewPassword());
                if(!Database.getInstance().updateUser(user)){
                    message="Error cambiando la contraseña";
                }else{
                    controller.comeBack();
                }
            }else{
                message="La contraseña actual no es correcta";
            }
        }else{
            message="Las nuevas contraseñas no coinciden";
        }
        
        panel.setMessage(message);
    }
    
    public void cancel(){
        controller.comeBack();
    }

}
