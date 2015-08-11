package es.unileon.happycow.handler;

import es.unileon.happycow.windows.Window;

/**
 *
 * @author dorian
 */
public class IdWindow implements IdHandler {
    private static int WINDOW_ID=0;
    
    private boolean withId;
    private Window type;
    private int id;
    
    public IdWindow(Window type){
        this.type=type;
        withId=true;
        id=WINDOW_ID;
        WINDOW_ID++;
    }

    public IdWindow(Window type, boolean withId) {
        this.withId = withId;
        this.type = type;
        id=WINDOW_ID;
        WINDOW_ID++;
    }

    public int getId() {
        return id;
    }

    public Window getType() {
        return type;
    }

    @Override
    public String toString() {
       return Integer.toString(id);
    }
    
    @Override
    public int compareTo(IdHandler another) {
        if(withId){
            return toString().compareTo(another.toString());
        }else{
            return toString().concat(type.name()).compareTo(another.toString());
        }
    }
    
}
