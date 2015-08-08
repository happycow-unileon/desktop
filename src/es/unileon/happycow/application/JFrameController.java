package es.unileon.happycow.application;

import es.unileon.happycow.database.Database;
import java.util.HashMap;
import java.util.Observable;

/**
 *
 * @author dorian
 */
public class JFrameController extends Observable{
    private Window state;
    private HashMap<String,String> parameters;

    public JFrameController() {
        state=Window.LOGIN;
        parameters=new HashMap<>();
    }

    public void setState(Window state) {
        this.state = state;
        notifyObservers();
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
}
