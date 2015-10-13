package es.unileon.happycow.windows;

import es.unileon.happycow.windows.factory.IFactory;
import es.unileon.happycow.handler.IdWindow;

/**
 * window login
 * @author dorian
 */
public class Login extends IWindow{
    public static Window TYPE=Window.LOGIN;

    public Login(IFactory factory) {
        super("Login",true, true, new IdWindow(TYPE, false), factory);
    } 

    @Override
    public Window getType() {
        return TYPE;
    }
}
