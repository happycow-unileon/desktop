package es.unileon.happycow.windows;

import es.unileon.happycow.application.Parameters;
import es.unileon.happycow.controller.IController;
import es.unileon.happycow.windows.factory.IFactory;
import es.unileon.happycow.handler.IdHandler;
import java.util.HashMap;
import javax.swing.JPanel;

/**
 *
 * @author dorian
 */
public abstract class IWindow {
    private boolean unique;
    private final boolean back;
    private final String title;
    private final IdHandler id;
    
    
    private IFactory factory;
    
    private IController controller;
    private JPanel panel;

    public IWindow(String title,boolean unique, boolean back, IdHandler id, IFactory factory) {
        this.title=title;
        this.unique=unique;
        this.back=back;
        this.id = id;
        this.factory=factory;
    }
    
    public abstract Window getType();

    public IFactory getFactory() {
        return factory;
    }

    public String getTitle() {
        return title;
    }
    
    public boolean isBack() {
        return back;
    }
    
    public boolean isUnique() {
        return unique;
    }

    public void setUnique(boolean unique) {
        this.unique = unique;
    }

    public IdHandler getId() {
        return id;
    }
    
    private void createWindow(){
        factory.createElements();
        this.panel=factory.getPanel();
        this.controller=factory.getController();
        factory=null;
    }
    
    public IController getController() {
        if(controller==null){
            createWindow();
        }
        return controller;
    }

    public JPanel getPanel() {
        if(panel==null){
            createWindow();
        }
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
    
    public void onResume(Parameters parameters){
        getController().onResume(parameters);
    };
    public void onCreate(Parameters parameters){
        getController().onCreate(parameters);
    };
    public void onDestroy(){
        getController().onDestroy();
    };
    
}
