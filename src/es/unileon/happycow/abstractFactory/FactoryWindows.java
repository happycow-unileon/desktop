package es.unileon.happycow.abstractFactory;

import es.unileon.happycow.controller.InterfaceController;
import javax.swing.JPanel;

/**
 * Factory interface
 * Create the concrete panel and controller for a specific situation/window
 * @author dorian
 */
public interface FactoryWindows {
    /**
     * Return the controller created
     * @return the controller
     */
    public InterfaceController getController();
    /**
     * Return the panel created
     * @return the panel
     */
    public JPanel getPanel();
    /**
     * Create the controller
     */
    public void createController();
    /**
     * Create the panel
     */
    public void createPanel();
    /**
     * Create every element of the factory
     */
    public void createElements();
}
