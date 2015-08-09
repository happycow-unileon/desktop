package es.unileon.happycow.factory;

import es.unileon.happycow.controller.IController;
import java.util.HashMap;
import javax.swing.JPanel;

/**
 *
 * @author dorian
 */
public abstract class Factory {
    protected HashMap<String,String> parameters;

    public Factory(HashMap<String, String> parameters) {
        this.parameters = parameters;
    }
    
    public HashMap<String, String> getParameters() {
        return parameters;
    }
    
     /**
     * Return the controller created
     * @return the controller
     */
    public abstract IController getController();
    /**
     * Return the panel created
     * @return the panel
     */
    public abstract JPanel getPanel();
    /**
     * Create the controller
     */
    public abstract void createController();
    /**
     * Create the panel
     */
    public abstract void createPanel();
    /**
     * Create every element of the factory
     */
    public void createElements(){
        createPanel();
        createController();
    }
}
