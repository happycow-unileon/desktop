package es.unileon.happycow.windows;

import es.unileon.happycow.windows.factory.IFactory;
import es.unileon.happycow.handler.IdWindow;

/**
 * Window password
 * @author dorian
 */
public class Password extends IWindow{
    public static Window TYPE=Window.PASSWORD;

    public Password(IFactory factory) {
        super("Login",true, false, new IdWindow(TYPE, false), factory);
    } 

    @Override
    public Window getType() {
        return TYPE;
    }
}
