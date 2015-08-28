package es.unileon.happycow.windows.factory;

import es.unileon.happycow.application.Parameters;
import es.unileon.happycow.controller.Controller;
import javax.swing.JPanel;

/**
 *
 * @author dorian
 */
public abstract class IFactory {
    protected Parameters parameters;

    public IFactory(Parameters parameters) {
        this.parameters = parameters;
    }
    
    public Parameters getParameters() {
        return parameters;
    }
    
     /**
     * Return the controller created
     * @return the controller
     */
    public abstract Controller getController();
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
