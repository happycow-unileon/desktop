package es.unileon.happycow.windows;

import es.unileon.happycow.windows.factory.IFactory;
import es.unileon.happycow.handler.IdWindow;

/**
 *Window administrator
 * @author dorian
 */
public class Administrator extends IWindow{
    public static Window TYPE=Window.ADMINISTRATION;

    public Administrator(IFactory factory) {
        super("Administración",true, true, new IdWindow(TYPE, false), factory);
    } 
    
    @Override
    public Window getType() {
        return TYPE;
    }
}
