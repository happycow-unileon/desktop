/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.unileon.happycow.application;

import es.unileon.happycow.controller.IController;
import es.unileon.happycow.handler.IdHandler;
import javax.swing.JPanel;

/**
 *
 * @author dorian
 */
public abstract class IWindow {
    private boolean unique;
    private boolean back;
    private IdHandler id;
    
    private final String title;
    private final IController controller;
    private final JPanel panel;

    public IWindow(String title, boolean unique, boolean back, IdHandler id, JPanel panel, IController controller) {
        this.unique = unique;
        this.title=title;
        this.id = id;
        this.back=back;
        this.panel=panel;
        this.controller=controller;
    }

    public String getTitle() {
        return title;
    }

    public boolean isBack() {
        return back;
    }

    public IdHandler getId() {
        return id;
    }

    public boolean isUnique() {
        return unique;
    }

    public void setUnique(boolean unique) {
        this.unique = unique;
    }
    
    public IController getController() {
        return controller;
    }

    public JPanel getPanel() {
        return panel;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof IWindow){
            IWindow window=(IWindow)obj;
            return window.getId().compareTo(id)==0;
        }else{
            return false;
        }
    }

    @Override
    public int hashCode() {
        return id.toString().hashCode();
    }
    
}
