package es.unileon.happycow.application;

import es.unileon.happycow.gui.Window;
import es.unileon.happycow.database.Database;
import java.util.HashMap;
import java.util.Observable;

/**
 *
 * @author dorian
 */
public class JFrameController extends Observable{
    private ActionsFrame action;
    private Window state;
    private HashMap<String,String> parameters;

    public JFrameController() {
        state=Window.LOGIN;
        parameters=new HashMap<>();
    }

    public ActionsFrame getAction() {
        return action;
    }
    
    private void notifyChanges(){
        setChanged();
        notifyObservers();
    }

    public void setState(Window state) {
        this.state = state;
        action=ActionsFrame.STATE;
        notifyChanges();
    }

    public Window getState() {
        return state;
    }
    
    public void comeBack(){
        action=ActionsFrame.BACK;
        notifyChanges();
    }
    
    /**
     * Da por finalizado el programa
     */
    public void exit(){
        //operaciones antes de cerrar todo...
        Database.getInstance().closeDB();
        System.exit(0);
    }
    
    public void addParameter(String key, String value){
        parameters.put(key, value);
    }
    
    public void clearParameters(){
        parameters.clear();
    }

    public HashMap<String, String> getParameters() {
        return parameters;
    }
}
