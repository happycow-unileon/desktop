/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.unileon.happycow.factory;

import es.unileon.happycow.controller.IController;
import javax.swing.JPanel;

/**
 *
 * @author dorian
 */
public abstract class Factory {
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
