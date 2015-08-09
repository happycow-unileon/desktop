package es.unileon.happycow.application.windows;

import es.unileon.happycow.controller.IController;
import es.unileon.happycow.factory.Factory;
import es.unileon.happycow.handler.IdHandler;
import java.util.HashMap;
import javax.swing.JPanel;

/**
 *
 * @author dorian
 */
public abstract class IWindow {
    private boolean unique;
    private boolean back;
    private final String title;
    private IdHandler id;
    
    
    private Factory factory;
    
    private IController controller;
    private JPanel panel;

    public IWindow(String title,boolean unique, boolean back, IdHandler id, Factory factory) {
        this.title=title;
        this.unique=unique;
        this.back=back;
        this.id = id;
        this.factory=factory;
    }
    
    public abstract Window getType();

    public Factory getFactory() {
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
    
    public void onResume(HashMap<String,String> parameters){
        getController().onResume(parameters);
    };
    public void onCreate(HashMap<String,String> parameters){
        getController().onCreate(parameters);
    };
    public void onDestroy(){
        getController().onDestroy();
    };
    
}
