package es.unileon.happycow.controller;

import es.unileon.happycow.application.JFrameController;
import java.util.HashMap;

/**
 *
 * @author dorian
 */
public abstract class IController {
    /**
     * Controlador concreto del jframe
     */
    protected JFrameController controller;
    
    public void setFrameController(JFrameController controller){
        this.controller = controller;
    };
    
    public void onResume(HashMap<String,String> parameters){};
    public void onCreate(HashMap<String,String> parameters){onResume(parameters);};
    public void onDestroy(){};
}
