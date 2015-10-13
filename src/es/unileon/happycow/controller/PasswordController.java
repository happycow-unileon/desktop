package es.unileon.happycow.controller;

import es.unileon.happycow.database.Database;
import es.unileon.happycow.database.concreteDatabase.DatabaseObject;
import es.unileon.happycow.gui.PanelPassword;
import es.unileon.happycow.model.User;

/**
 * Controller of the state password
 * @author dorian
 */
public class PasswordController extends Controller{
    /**
     * Panel associated
     */
    private final PanelPassword panel;

    /**
     * Constructor
     * @param panel the panel controlled 
     */
    public PasswordController(PanelPassword panel) {
        this.panel = panel;
    }
    
    /**
     * Change the password of the user or set a message when is incorrect
     */
    public void changePassword(){
        String message="";
        //if the password match
        if(panel.isPasswordMatch()){
            //check the actual password
            String oldPassword=DatabaseObject.encript(panel.getOldPassword());
            User user=Database.getInstance().getUser();
            String userPassword=user.getPassword();
            
            if(oldPassword.compareTo(userPassword)==0){
                //change the password
                user.setPassword(panel.getNewPassword());
                if(!Database.getInstance().updateUser(user)){
                    message="Error cambiando la contraseña";
                }else{
                    //if everything ok, come back to the window
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
    
    /**
     * Cancel the change and come back
     */
    public void cancel(){
        controller.comeBack();
    }

}
