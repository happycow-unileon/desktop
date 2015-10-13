package es.unileon.happycow.controller;

import es.unileon.happycow.application.JFrameController;
import es.unileon.happycow.application.Parameters;

/**
 * Abstract class for a controller
 * @author dorian
 */
public abstract class Controller {
    /**
     * Controlador concreto del jframe
     */
    protected JFrameController controller;
    
    /**
     * Set the jframecontroller
     * @param controller 
     */
    public void setFrameController(JFrameController controller){
        this.controller = controller;
    };
    
    /**
     * When the windows is restored from the queue execute it
     * @param parameters 
     */
    public void onResume(Parameters parameters){};
    /**
     * When the windows is created (usualy when is pushed on the queue)
     * @param parameters 
     */
    public void onCreate(Parameters parameters){onResume(parameters);};
    /**
     * When is poped out of the queue
     */
    public void onDestroy(){};
}
