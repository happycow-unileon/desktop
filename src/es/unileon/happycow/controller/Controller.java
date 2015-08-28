package es.unileon.happycow.controller;

import es.unileon.happycow.application.JFrameController;
import es.unileon.happycow.application.Parameters;

/**
 *
 * @author dorian
 */
public abstract class Controller {
    /**
     * Controlador concreto del jframe
     */
    protected JFrameController controller;
    
    public void setFrameController(JFrameController controller){
        this.controller = controller;
    };
    
    public void onResume(Parameters parameters){};
    public void onCreate(Parameters parameters){onResume(parameters);};
    public void onDestroy(){};
}
